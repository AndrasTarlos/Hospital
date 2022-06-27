/**
 * view-controller for hospital.html
 * @author Andras Tarlos
 */
document.addEventListener("DOMContentLoaded", () => {
    readHospitals();
});

/**
 * reads all hospitals
 */
function readHospitals() {
    fetch("./resource/hospital/list")
        .then(function (response) {
            if (response.ok) {
                return response;
            } else {
                console.log(response);
            }
        })
        .then(response => response.json())
        .then(data => {
            showHospitalList(data);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * shows the hospitalList as a table
 * @param data  the hospital
 */
function showHospitalList(data) {
    let tBody = document.getElementById("hospitallist");
    data.forEach(hospital => {
        let row = tBody.insertRow(-1);
        row.insertCell(-1).innerHTML = hospital.name;
        row.insertCell(-1).innerHTML = hospital.address;
        row.insertCell(-1).innerHTML = hospital.owner;
        row.insertCell(-1).innerHTML = hospital.numberOfEmployees;

        let button = document.createElement("button");
        button.innerHTML = "Bearbeiten ...";
        button.type = "button";
        button.name = "editEquipment";
        button.setAttribute("data-name", hospital.name);
        button.addEventListener("click", editHospital);
        row.insertCell(-1).appendChild(button);

        button = document.createElement("button");
        button.innerHTML = "Löschen ...";
        button.type = "button";
        button.name = "deleteEquipment";
        button.setAttribute("data-name", hospital.name);
        button.addEventListener("click", deleteHospital);
        row.insertCell(-1).appendChild(button);

    });
}

/**
 * redirects to the edit-form
 * @param event  the click-event
 */
function editHospital(event) {
    const button = event.target;
    const name = button.getAttribute("data-name");
    window.location.href = "./hospitaledit.html?name=" + name;
}

/**
 * deletes a hospital
 * @param event  the click-event
 */
function deleteHospital(event) {
    const button = event.target;
    const name = button.getAttribute("data-name");

    fetch("./resource/hospital/delete?name=" + name,
        {
            method: "DELETE"
        })
        .then(function (response) {
            if (response.ok) {
                window.location.href = "./hospital.html";
            } else {
                console.log(response);
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}