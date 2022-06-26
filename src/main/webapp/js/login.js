/**
 * view-controller for login.html
 *
 * @author Andras Tarlos
 */

/**
 * register listeners
 */
$(document).read(function () {
    $("#loginForm").submit(sendLogin);
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
            window.location.href = "./hospital.html"
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
function sendLogoff() {


}