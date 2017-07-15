function closeRegModal() {
    $('#registrationModal').modal('hide');
    $("#reg-form-error").text("");
    document.forms.regForm.reset();
}

function closeLogModal() {
    $('#loginModal').modal('hide');
    $("#log-form-error").text("");
    document.forms.logForm.reset();
}

function openLoginModal() {
    $('#loginModal').modal('show');
}

function validateRegForm() {
    resetError("#name-error");
    resetError("#email-error");
    resetError("#conf-pass-error");

    var elems = document.forms[0].elements;
    if (!elems.inputName.value) {
        $("#name-error").text("Name field can't be empty");
    } else if (!elems.inputEmail.value) {
        $("#email-error").text("E-mail field can't be empty");
    } else if (!elems.inputEmail.value
            .match(/[a-z0-9._-]*@[a-z0-9]+\.[a-z0-9]+(.[a-z0-9]+)?/i)) {
        $("#email-error").text("Email field is incorrect");
    }

    else if (!elems.inputPassword.value || !elems.inputPasswordConfirm.value) {
        $("#conf-pass-error").text("Fill up password fields");
    } else if (!elems.inputPassword.value.match(/.{6,15}/)) {
        $("#conf-pass-error").text("Password field is incorrect");
    } else if (elems.inputPassword.value !== elems.inputPasswordConfirm.value) {
        $("#conf-pass-error").text("Passwords don't match");
    } else {
        registr(elems);
    }
}

function resetError(container) {
    $(container).text('');
}

function registr(elems) {
    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "signUp",
            password: elems.inputPassword.value,
            passwordConfirmation: elems.inputPasswordConfirm.value,
            name: elems.inputName.value,
            email: elems.inputEmail.value
        },
        statusCode: {
            200: function () {
                closeRegModal();
                openLoginModal();
            },
            409: function () {
                $("#reg-form-error").text("Sorry, email is already in use");
            },
            400: function () {
                $("#reg-form-error").text("Fill up all fields in a correct way");
            },
            500: function () {
                $("#reg-form-error").text("Oops some errors occurred on server");
            }
        }
    });
}

function signIn() {

    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "signIn",

            password: $("#log-InputPassword").val(),
            email: $("#log-InputEmail").val()
        },
        statusCode: {
            200: function () {
                closeLogModal();
                redirect();
            },
            403: function () {
                $("#log-form-error").text("You are banned");
            },
            406: function () {
                $("#log-form-error").text("Wrong email");
            },
            423: function () {
                $("#log-form-error").text("Wrong password");
            },
            500: function () {
                $("#log-form-error").text("Oops some server errors occurred");
            }

        }

    });
}

function redirect() {
    window.location.href = "Controller?command=redirect"
}

function closeChangeModal() {
    $(".modal").modal("hide");
}


function changeUserName() {
    var newName = $("#change-name").val();

    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "changeUser",
            field: "name",
            name: newName,
            password: $("#change-name-password").val()
        },
        statusCode: {
            200: function () {
                $("span#name").text(newName);
                closeChangeModal();
            },
            423: function () {
                $("#change-pass-error").text("Wrong password");
            },
            500: function () {
                $("#change-pass-error").text("Oops some server errors occurred");
            }

        }
    });
}

function changeUserEmail() {
    var email = $("#change-email").val();


    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "changeUser",
            field: "email",
            email: email,
            password: $("#change-email-password").val()
        },
        statusCode: {
            200: function () {
                $("span#email").text(email);
                closeChangeModal();
            },
            423: function () {
                $("#change-pass-error").text("Wrong password");
            },
            500: function () {
                $("#change-pass-error").text("Oops some server errors occurred");
            }

        }
    });

}

function changeUserPass() {
    var newpass1 = $("#new-pass").val();
    var newpass2 = $("#conf-new-pass").val();


    if (!newpass1.match(/.{6,25}/)) {
        $("#change-newPass-error").text("Password is incorrect");
    } else if (!(newpass1 === newpass2)) {
        $("#change-newPass-error").text("Passwords don't match");
    } else {
        $.ajax({
            url: "Controller",
            cache: false,
            type: 'POST',
            data: {
                command: "changeUser",
                field: "password",
                password: $("#old-pass").val(),
                newPassword1: newpass1,
                newPassword2: newpass2
            },
            statusCode: {
                200: function () {
                    closeChangeModal();
                },
                423: function () {
                    $("#change-pass-error").text("Wrong password");
                },
                500: function () {
                    $("#change-pass-error").text("Oops some server errors occurred");
                }

            }

        });
    }
}

function librModal(librId) {
    $('#librarain-id').val(librId);
}

function deleteLibr() {
    var librId = $("#librarain-id").val();
    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "deleteLibrarian",
            userId: librId
        },
        success: function () {
            $('#delete-libr-modal').modal('hide');
            $("#libr" + librId).remove();
        }
    });
}

function validateLibrRegForm() {
    resetError("#libr-name-error");
    resetError("#libr-email-error");
    resetError("#conf-libr-pass-error");

    var elems = document.forms.regLibrForm.elements;
    if (!elems.inputLibrName.value) {
        $("#libr-name-error").text("Name field can't be empty");
    } else if (!elems.inputLibrEmail.value) {
        $("#libr-email-error").text("E-mail field can't be empty");
    } else if (!elems.inputLibrEmail.value
            .match(/[a-z0-9._-]*@[a-z0-9]+\.[a-z0-9]+(.[a-z0-9]+)?/i)) {
        $("#libr-email-error").text("Email field is incorrect");
    }

    else if (!elems.inputLibrPassword.value || !elems.inputLibrPasswordConfirm.value) {
        $("#conf-libr-pass-error").text("Fill up password fields");
    } else if (!elems.inputLibrPassword.value.match(/.{6,15}/)) {
        $("#conf-libr-pass-error").text("Password field is incorect");
    } else if (elems.inputLibrPassword.value !== elems.inputLibrPasswordConfirm.value) {
        $("#conf-libr-pass-error").text("Passwords don't match");
    } else {
        registrLibr(elems);
    }
}

function registrLibr(elems) {
    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "createLibrarian",
            name: elems.inputLibrName.value,
            email: elems.inputLibrEmail.value,
            password: elems.inputLibrPassword.value,
            passwordConfirmation: elems.inputLibrPasswordConfirm.value
        },
        statusCode: {
            200: function () {
                window.location.reload();
            },
            409: function () {
                $("#reg-libr-form-error").text("Sorry, email is already in use");
            },
            400: function () {
                $("#reg-libr-form-error").text("Fill up all fields in a correct way");
            },
            500: function () {
                $("#reg-libr-form-error").text("Oops some errors occured on server");
            }
        }
    });
}

function banUser(userId, name, email) {
    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "banUser",
            userId: userId
        },
        success: function () {
            $("#vUser" + userId).remove();
            var td_data = "<td>" + userId + "</td> " +
                "<td>" + name + "</td> " +
                "<td>" + email + "</td> " +
                "<td> <a class='btn btn-success' onclick='unblockUser(" + userId + ")'>Unblock </a></td>";
            $("#bannedUsersTable").find("tbody").append("<tr>" + td_data + "</tr>");

        }
    });
}

function unblockUser(userId) {
    $.ajax({
        url: "Controller",
        cache: false,
        type: 'POST',
        data: {
            command: "unBanUser",
            userId: userId
        },
        success: function () {
            window.location.reload();
        }
    });
}

function changeLocale(lang) {

    $.ajax({
        url: "changeLocale.jsp",
        cache: false,
        type: 'POST',
        data: {
            lang: lang
        },
        success: function () {
            location.reload();
        }
    });

}


