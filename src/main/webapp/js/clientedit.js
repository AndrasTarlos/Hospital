/**
 * view-controller of clientedit.html
 * @author Andras Tarlos
 **/

document.addEventListener("DOMContentLoaded", () => {
    if (getQueryParam("firstname") != null) {
        readClient();
    }
    document.getElementById("clienteditForm").addEventListener("submit", saveClient);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
})

function readClient() {
    const firstname = getQueryParam("firstname")
    const name = getQueryParam("name")
    fetch("./resource/client/read?name=" + name + "&firstname=" + firstname)
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showEquipment(data);
        })
        .catch(function (error) {
            console.log(error)
        });
}

function saveClient(event) {
    event.preventDefault();

    const clientForm = document.getElementById("clienteditForm");
    const formData = new FormData(clientForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/client/";
    const firstname = getQueryParam("firstname");
    const name = getQueryParam("name")
    if (firstname == null || name == null) {
        method = "POST";
        url += "create";
    } else {
        method = "PUT";
        url += "update";
    }
    fetch(url,
        {
            method: method,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: data
        })
        .then(function (response) {
            if (!response.ok) {
                console.log(response);
            } else return response;
        })
        .then()
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of an client
 * @param data the client-data
 */

function showEquipment(data) {
    document.getElementById("firstname").value = data[0].firstname;
    document.getElementById("name").value = data[0].name;
    document.getElementById("sex").value = data[0].sex;
    document.getElementById("condition").value = data[0].condition;
    document.getElementById("phoneNumber").value = data[0].phoneNumber;
    document.getElementById("bill").value = data[0].bill;
    document.getElementById("ahvNumber").value = data[0].ahvNumber;
}

function cancelEdit(event) {
    window.location.href = "./client.html";
}