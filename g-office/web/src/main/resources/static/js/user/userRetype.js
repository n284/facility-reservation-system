/**
 *
 */


$('#password , #passCheck').on('keyup', function() {

	console.log('passkeyup')
	console.log('password check start')

	var valPass = document.getElementById("password").value;
	var valPassCheck = document.getElementById("retypePassword").value;

	console.log('pass:' + valPass)
	console.log('check:' + valPassCheck)

	if (valPass != valPassCheck) {
		document.getElementById("password-check").innerHTML
			= "<span class='system-msg'>パスワードが一致しません</span>";

	} else {
		document.getElementById("password-check").innerHTML
			= "<span class='system-msg'></span>";

	}

});

$("#address , #addressCheck").on('keyup', function() {

	console.log('mailkeyup')
	console.log('mail check start')

	var valMail = document.getElementById("address").value;
	var valMailCheck = document.getElementById("retypeMailAddress").value;

	console.log('mail:' + valMail)
	console.log('check:' + valMailCheck)

	if (valMail != valMailCheck) {
		document.getElementById("address-check").innerHTML
			= "<span class='system-msg'>メールアドレスが一致しません</span>";

	} else {
		document.getElementById("address-check").innerHTML
			= "<span class='system-msg'></span>";

	}

});

$('*').on('keyup', function() {

	var valPass = document.getElementById("password").value;
	var valPassCheck = document.getElementById("passCheck").value;
	var valMail = document.getElementById("address").value;
	var valMailCheck = document.getElementById("addressCheck").value;

	if (valPass != valPassCheck) {
		document.getElementById("confirm").disabled = "disabled";

	} else if (valMail != valMailCheck) {
		document.getElementById("confirm").disabled = "disabled";

	} else {
		document.getElementById("confirm").disabled = "";

	}
});
