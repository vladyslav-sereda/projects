function openRegModal() {
	$('#registration-modal').modal();
}

function openLoginModal() {
	$('#login-modal').modal();
}

function closeModal() {
	$('#registration-modal').modal('close');
	$('#login-modal').modal('close');

	$("#form-error").text("");
	$("#log-form-error").text("");

	document.forms[0].reset();
	document.forms[1].reset();
}

function validateRegForm() {
	resetError("#name-error");
	resetError("#phone-error");
	resetError("#email-error");
	resetError("#password-confirm-error");

	var elems = document.forms[0].elements;
	if (!elems.inputName.value) {
		$("#name-error").text("Name field can't be empty");
	} else if (!elems.inputName.value.match(/^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$/)) {
		$("#name-error").text("Name is incorrect");
	}

	else if (!elems.inputPhone.value) {
		$("#phone-error").text("Phone field can't be empty");
	} else if (!elems.inputPhone.value.match(/[+]?[0-9 ]{6,15}/)) {
		$("#phone-error").text("Phone field is incorect");
	}

	else if (!elems.inputEmail.value) {
		$("#email-error").text("E-mail field can't be empty");
	} else if (!elems.inputEmail.value.match(/[a-z0-9._-]*@[a-z0-9]+\.[a-z0-9]+(.[a-z0-9]+)?/i)) {
		$("#email-error").text("Email field is incorrect");
	}

	else if (!elems.inputPassword.value || !elems.inputPasswordConfirm.value) {
		$("#password-confirm-error").text("Fill up password fields");
	} else if (!elems.inputPassword.value.match(/.{6,15}/)) {
		$("#password-confirm-error").text("Password field is incorect");
	} else if (elems.inputPassword.value !== elems.inputPasswordConfirm.value) {
		$("#password-confirm-error").text("Passwords don't match");
	} else {
		registr(elems);
	}
}

function resetError(container) {
	$(container).text('');
}

function registr(elems) {
	$.ajax({
		url : "Controller",
		cache : false,
		type : 'POST',
		data : {
			command : "signUp",
			phone : elems.inputPhone.value,
			password : elems.inputPassword.value,
			passwordConfirmation : elems.inputPasswordConfirm.value,
			name : elems.inputName.value,
			email : elems.inputEmail.value
		},
		statusCode : {
			200 : function() {
				closeModal();
			},
			409 : function() {
				$("#form-error").text("Sory, email is already in use");
			},
			400 : function() {
				$("#form-error").text("Fill up all fields in a correct way");
			},
			500 : function() {
				$("#form-error").text("Oops some errors occured on server");
			}
		}
	});
}

function signIn() {

	$.ajax({
		url : "Controller",
		cache : false,
		type : 'POST',
		data : {
			command : "signIn",

			password : $("#log-input-password").val(),
			email : $("#log-input-email").val()
		},
		statusCode : {
			200 : function() {
				closeModal();
				redirect();
			},
			406 : function() {
				$("#log-form-error").text("Wrong email");
			},
			423 : function() {
				$("#log-form-error").text("Wrong password");
			},
			500 : function() {
				$("#log-form-error").text("Oops some server errors occurred");
			}

		}

	});
}

function redirect() {
	window.location.href = "Controller?command=redirect"
}

function closeChangeModal() {
	$(".modal").modal("close");
}

function openNameModal() {
	$("#change-name-modal").modal();
}

function openPhoneModal() {
	$("#change-phone-modal").modal();
}

function openEmailModal() {
	$("#change-email-modal").modal();
}

function openPassModal() {
	$("#change-pass-modal").modal();
}

function changeName() {
	var newName = $("#change-name").val();
	
	if (!newName) {
		$("#change-name-form-error").text("Name field can't be empty");
	} else if (!newName.match(
			/^[a-zA-Zа-яА-ЯїЇіІєЄ][a-zA-Zа-яА-ЯїЇіІєЄ `'-]{1,20}$/)) {
		$("#change-name-form-error").text("Name is incorrect");
	} else {
		$.ajax({
			url : "Controller",
			cache : false,
			type : 'POST',
			data : {
				command : "changeUserInf",
				field : "name",
				name : newName,
				password : $("#change-name-password").val()
			},
			success : function() {
				$("span#name").text(newName);
				closeChangeModal();
			}
		});
	}
}

function changePhone() {
	var phone = $("#change-phone").val();
	
	if (!phone) {
		$("#change-phone-form-error").text("Phone field can't be empty");
	} else if (!phone.match(/[+]?[0-9 ]{6,15}/)) {
		$("#change-phone-form-error").text("Phone is incorrect");
	} else {
		$.ajax({
			url : "Controller",
			cache : false,
			type : 'POST',
			data : {
				command : "changeUserInf",
				field : "phone",
				phone : phone,
				password : $("#change-phone-password").val()
			},
			success : function() {
				$("span#phone").text(phone);
				closeChangeModal();
			}
		});
	}
}

function changeEmail() {
	var email = $("#change-email").val();
	
	if (!email) {
		$("#change-email-form-error").text("Email field can't be empty");
	} else if (!email.match(/[a-z0-9._-]*@[a-z0-9]+\.[a-z0-9]+(.[a-z0-9]+)?/i)) {
		$("#change-email-form-error").text("Email is incorrect");
	} else {
		$.ajax({
			url : "Controller",
			cache : false,
			type : 'POST',
			data : {
				command : "changeUserInf",
				field : "email",
				email : email,
				password : $("#change-email-password").val()
			},
			success : function() {
				$("span#email").text(email);
				closeChangeModal();
			}
		});
	}
}

function changePass() {
	var newpass1 = $("#new-pass").val();
	var newpass2 = $("#conf-new-pass").val();

	
	if (!newpass1 || !newpass2) {
		$("#change-pass-form-error").text("Password field can't be empty");
	} else if (!newpass1.match(/.{6,15}/)) {
		$("#change-pass-form-error").text("Password is incorrect");
	}else if (! (newpass1 === newpass2)) {
		$("#change-pass-form-error").text("Passwords don't match");
	} else {
		$.ajax({
			url : "Controller",
			cache : false,
			type : 'POST',
			data : {
				command : "changeUserInf",
				field : "password",
				password : $("#old-pass").val(),
				newPassword1 : newpass1,
				newPassword2 : newpass2			
			},
			success : function() {
				closeChangeModal();
			}
		});
	}
}