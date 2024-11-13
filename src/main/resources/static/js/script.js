document.addEventListener("DOMContentLoaded", fetchTasks);

const apiUrl = "/api/v1/tasks";
let currentUpdateId = null;

async function fetchTasks() {
    const response = await fetch(apiUrl);
    const tasks = await response.json();
    renderTasks(tasks);
}

function renderTasks(tasks) {
    const taskList = document.getElementById("task-list");
    taskList.innerHTML = "";

    tasks.forEach(task => {
        const taskElement = document.createElement("div");
        taskElement.className = "task-item";
        taskElement.setAttribute("data-id", task.id);

        taskElement.innerHTML = `
            <div class="task-info">
                <div class="task-title">${task.title}</div>
                <div class="task-status">${task.completed ? "Completed" : "Not Completed"}</div>
                <div class="task-date">${new Date(task.createdAt).toLocaleString()}</div>
            </div>
            <div class="task-actions">
                <button class="mark-completed ${task.completed ? 'completed' : ''}" onclick="toggleComplete(${task.id})">
                    ${task.completed ? 'Completed' : 'Mark as Completed'}
                </button>
                <button class="edit" onclick="openModal(${task.id}, '${task.title}')">Edit</button>
                <button class="delete" onclick="deleteTask(${task.id})">Delete</button>
            </div>
        `;
        taskList.appendChild(taskElement);
    });
}

async function createTask() {
    const title = document.getElementById("task-title").value;
    const completed = false;
    const response = await fetch(apiUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, completed })
    });

    if (response.ok) {
        await fetchTasks();
        document.getElementById("task-title").value = "";
    }
}

async function toggleComplete(id) {
    const taskElement = document.querySelector(`.task-item[data-id='${id}']`);
    const completed = !taskElement.querySelector(".mark-completed").classList.contains("completed");

    const updateResponse = await fetch(`${apiUrl}/${id}/completed`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ completed })
    });

    if (updateResponse.ok) {
        const button = taskElement.querySelector(".mark-completed");
        button.classList.toggle("completed", completed);
        button.textContent = completed ? "Completed" : "Mark as Completed";

        const statusElement = taskElement.querySelector(".task-status");
        statusElement.textContent = completed ? "Completed" : "Not Completed";
    }
}


async function deleteTask(id) {
    if (confirm("Are you sure you want to delete this task?")) {
        const response = await fetch(`${apiUrl}/${id}`, { method: "DELETE" });
        if (response.ok) await fetchTasks();
    }
}

function openModal(id, title) {
    currentUpdateId = id;
    document.getElementById("update-title").value = title;
    document.getElementById("updateModal").style.display = "flex";
}

function closeModal() {
    document.getElementById("updateModal").style.display = "none";
}

async function updateTask() {
    const title = document.getElementById("update-title").value;

    const updateResponse = await fetch(`${apiUrl}/${currentUpdateId}/title`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title })
    });

    if (updateResponse.ok) {
        await fetchTasks();
        closeModal();
    }
}


function closeModalOnOutsideClick(event) {
    document.querySelector(".modal-content");
    if (event.target === document.getElementById("updateModal")) {
        closeModal();
    }
}

