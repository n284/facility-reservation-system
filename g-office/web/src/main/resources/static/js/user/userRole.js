window.onload = function() {

	const admin = document.getElementById('admin');
	admin.checked = false;


	const userRole = document.getElementsByClassName('userRole')

	for (var i = 0; i < userRole.length; i++) {
		var j = $(".userRole").val();
		if (j == 'admin') {
			admin.checked = true;
		}
	}
}


$(function() {
	$(document).on('click', '.role', function() {

		const general = document.getElementById('general');
		general.checked = true;

	});

});
