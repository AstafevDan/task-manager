function validateEmail(email) {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email);
}

async function validateRegistration(event) {
    event.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    const errorMessage = document.getElementById("error-message");

    errorMessage.textContent = "";

    if (!validateEmail(email)) {
        errorMessage.textContent = "Please enter a valid email address";
        return;
    }

    if (password !== confirmPassword) {
        errorMessage.textContent = "Passwords do not match!";
        return;
    }

    if (password.length < 8) {
        errorMessage.textContent = "Password should be at least 8 characters.";
        return;
    }

    const user = {
        username: email,
        passwordHash: password
    };

    try {
        const response = await fetch('/registration', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(user)
        });

        if (response.ok) {
            alert('Registration successful! Please log in.');
            window.location.href = '/login';
        } else if (response.status === 409) {
            errorMessage.textContent = 'User with this email already exists.';
        } else {
            errorMessage.textContent = 'Registration failed. Please try again.';
        }
    } catch (error) {
        console.error('Error:', error);
        errorMessage.textContent = 'An error occurred. Please try again later.';
    }
}
