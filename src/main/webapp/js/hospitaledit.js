/**
 * view-controller of hospitaledit.html
 * @author Andras Tarlos
 **/

document.addEventListener("DOMContentLoaded", () => {
        readHospital();
    }
)

function readHospital() {
    const name = getQueryParam("name")
    fetch("./resource/hospital/read?name=" + name)
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

/**
 * show the data of an equipment
 * @param data the equipment-data
 */

function showEquipment(data) {
    document.getElementById("name").value = data[0].name;
    document.getElementById("address").value = data[0].address;
    document.getElementById("owner").value = data[0].owner;
    document.getElementById("numberOfEmployees").value = data[0].numberOfEmployees;
}