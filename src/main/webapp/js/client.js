/**
 * view-controller for client.html
 * @author Andras Tarlos
 */
document.addEventListener("DOMContentLoaded", () => {
    readClients();
});

/**
 * reads all clients
 */
function readClients() {
    fetch("./resource/client/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showClientList(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the clientList as a table
 * @param data  the client
 */
function showClientList(data) {
    let tBody = document.getElementById("clientlist");
    data.forEach(client => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = client.firstname;
        row.insertCell(-1).innerHTML = client.name;
        row.insertCell(-1).innerHTML = client.sex;
        row.insertCell(-1).innerHTML = client.condition;
        row.insertCell(-1).innerHTML = client.phoneNumber;
        row.insertCell(-1).innerHTML = client.bill;
        row.insertCell(-1).innerHTML = client.ahvNumber;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editClient";
        button.setAttribute("data-firstname", client.firstname);
        button.setAttribute("data-name", client.name);
        button.addEventListener("click", editClient);
        row.insertCell(-1).appendChild(button);

        button = document.createElement("button");
        button.innerHTML = "Löschen ...";
        button.type = "button";
        button.name = "deleteEquipment";
        button.setAttribute("data-firstname", client.firstname);
        button.setAttribute("data-name", client.name);
        button.addEventListener("click", deleteClient);
        row.insertCell(-1).appendChild(button);

    });
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editClient(event) {
    const button = event.target;
    const firstname = button.getAttribute("data-firstname");
    const name = button.getAttribute("data-name");
    window.location.href = "./clientedit.html?firstname=" + firstname + "&name=" + name;
}

/**
 * deletes a client
 * @param event  the click-event
 */
function deleteClient(event) {
    const button = event.target;
    const name = button.getAttribute("data-name");
    const firstname = button.getAttribute("data-firstname");

    fetch("./resource/client/delete?firstname=" + firstname + "&name=" + name,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./client.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}