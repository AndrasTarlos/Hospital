/**
 * view-controller for login.html
 *
 * @author Andras Tarlos
 */

/**
 * register listeners
 */
$(document).ready(function () {
    /**
     * listener for submitting the ofrm sends the login data to the web service
     */
    $("#loginForm").submit(sendLogin);
    /**
     * listener for button [Abmelden]
     */
    $("#logoff").click(sendLogoff)
});

function sendLogin(form) {
    form.preventDefault();
    $
        .ajax( {
            urL: "./resource/user/login",
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
 * sends the logoff-request
 */
function sendLogoff(form) {
    form.preventDefault();
    $
        .ajax( {
            urL: "./resource/user/logout",
            dataType: "text",
            type: "DELETE"
        })
        .done(function () {
            window.location.href = "./login.html"
        })
        .fail(function (xhr, status, errorThrown) {

        })

}