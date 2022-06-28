/**
 * view-controller of equipmentedit.html
 * @author Andras Tarlos
 **/

document.addEventListener("DOMContentLoaded", () => {
    if (getQueryParam("name") != null) {
        readEquipment();
    }
    document.getElementById("equipmenteditForm").addEventListener("submit", saveEquipment);
    document.getElementById("cancel").addEventListener("click", cancelEdit);
})

function readEquipment() {
    const name = getQueryParam("name")
    fetch("./resource/equipment/read?name=" + name)
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
    console.log(name)
}

function saveEquipment(event) {
    event.preventDefault();

    const equipmentForm = document.getElementById("equipmenteditForm");
    const formData = new FormData(equipmentForm);
    const data = new URLSearchParams(formData);

    let method;
    let url = "./resource/equipment/";
    const name = getQueryParam("name");
    if (name == null) {
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
 * show the data of an equipment
 * @param data the equipment-data
 */

function showEquipment(data) {
    document.getElementById("name").value = data[0].name;
    document.getElementById("description").value = data[0].description;
    document.getElementById("amount").value = data[0].amount;
    document.getElementById("storageRoom").value = data[0].storageRoom;
}

function cancelEdit(event) {
    window.location.href = "./equipment.html";
}