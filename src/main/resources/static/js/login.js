function validateEmail(email) {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailRegex.test(email);
}

function validateLogin(event) {
    event.preventDefault();

    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;
    const errorMessage = document.getElementById("login-error-message");



    if (!email || !password) {
        errorMessage.textContent = "Please fill in both fields.";
        return;
    }

    if (!validateEmail(email)) {
        errorMessage.textContent = "Please enter a valid email address";
        return;
    }

    if (password.length < 8) {
        errorMessage.textContent = "Password should be at least 8 characters.";
        return;
    }

    errorMessage.textContent = "";

    document.getElementById("login-form").submit();
}

