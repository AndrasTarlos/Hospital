/**
 * view-controller for index.html
 *
 * @author Andras Tarlos
 */

/**
 * register listeners
 */
$(document).ready(function () {
    /**
     * listener for submitting the from sends the login data to the web service
     */
    $("#loginForm").submit(sendLogin);
    /**
     * listener for button [Abmelden]
     */
    $("#logout").click(sendLogout)
});

function sendLogin(form) {
    form.preventDefault();
    $
        .ajax( {
            url: "./resource/user/login",
            dataType: "text",
            type: "POST",
            data: $("#loginForm").serialize()
        })
        .done(function () {
            window.location.href = "./equipment.html"
        })
        .fail(function (xhr, status, errorThrown) {
            if (xhr.status == 404) {
                $("#message").text("Benutzername/Passwort unbekannt");
            } else {
                $("#message").text("Es ist ein Fehler aufgetreten");
            }
        })

}

/**
 * sends the logout-request
 */
function sendLogout(form) {
    console.log("Logout..")
    form.preventDefault();
    $
        .ajax( {
            url: "./resource/user/logout",
            dataType: "text",
            type: "DELETE"
        })
        .done(function () {
            window.location.href = "../index.html"
        })
        .fail(function (xhr, status, errorThrown) {

        })
}