const apiUrl = "http://localhost:5000/users"


async function listUsers() {
    const response = await fetch(apiUrl);
    const users = await response.json();
    const usersList = document.getElementById('users-list');
    usersList.innerHTML = '';
    users.forEach(user => {
        const userDiv = document.createElement('div');
        userDiv.innerHTML = `
            <p>ID: ${user.id}</p>
            <p>Nombre: ${user.name}</p>
            <p>Email: ${user.email}</p>
            <button onclick="editUser(${user.id})">Editar</button>
            <button onclick="deleteUser(${user.id})">Eliminar</button>
            <hr>
        `;
        usersList.appendChild(userDiv);
    });
}

async function deleteUser(id) {
    await fetch(`${apiUrl}/${id}`, { method: 'DELETE' })
    listUsers()
}

document.getElementById('user-form').onsubmit = async (e) => {
    e.preventDefault();
    const id = document.getElementById('user-id').value;
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const userData = { name, email };

    if (id) {
        await fetch(`${apiUrl}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });
    } else {
        await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        });
    }
    listUsers();
    resetForm();
};

async function editUser(id){
    const response = await fetch(`${apiUrl}/${id}`)
    const user = await response.json()

    document.getElementById('user-id').value = user.id
    document.getElementById('name').value = user.name
    document.getElementById('email').value = user.email
}

function resetForm(){
    document.getElementById('user-id').value = ''
    document.getElementById('name').value = ''
    document.getElementById('email').value = ''
}

window.onload = () => {
    listUsers()
}
