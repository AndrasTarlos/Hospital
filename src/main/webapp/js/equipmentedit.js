/**
 * view-controller of equipmentedit.html
 * @author Andras Tarlos
 **/

document.addEventListener("DOMContentLoaded", () => {
    readEquipment();
    }
)

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

function readPerson() {
    fetch("./person.json")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showPerson(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * show the data of an equipment
 * @param data the equipment-data
 */

function showEquipment(data) {
    document.getElementById("name").value = data.name;
    document.getElementById("description").value = data.description;
    document.getElementById("amount").value = data.amount;
    document.getElementById("storageRoom").value = data.storageRoom;
}