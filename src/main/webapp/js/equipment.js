/**
 * view-controller for equipment.html
 * @author Andras Tarlos
 */
document.addEventListener("DOMContentLoaded", () => {
    readEquipments();
});

/**
 * reads all equipment
 */
function readEquipments() {
    fetch("./resource/equipment/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showEquipmentList(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the equipmentList as a table
 * @param data  the equipment
 */
function showEquipmentList(data) {
    let tBody = document.getElementById("equipmentlist");
    data.forEach(equipment => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = equipment.name;
        row.insertCell(-1).innerHTML = equipment.description;
        row.insertCell(-1).innerHTML = equipment.amount;
        row.insertCell(-1).innerHTML = equipment.storageRoom;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editEquipment";
        button.setAttribute("data-name", equipment.name);
        button.addEventListener("click", editEquipment);
        row.insertCell(-1).appendChild(button);

        button = document.createElement("button");
        button.innerHTML = "Löschen ...";
        button.type = "button";
        button.name = "deleteEquipment";
        button.setAttribute("data-name", equipment.name);
        button.addEventListener("click", deleteEquipment);
        row.insertCell(-1).appendChild(button);

    });
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editEquipment(event) {
    const button = event.target;
    const name = button.getAttribute("data-name");
    window.location.href = "./equipmentedit.html?name=" + name;
}

/**
 * deletes an equipment
 * @param event  the click-event
 */
function deleteEquipment(event) {
    const button = event.target;
    const name = button.getAttribute("data-name");

    fetch("./resource/equipment/delete?name=" + name,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./equipment.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}