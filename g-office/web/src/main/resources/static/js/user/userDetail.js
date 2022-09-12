jQuery(function($) {
	$(function() {
		$('.one').on('click', function() {
			if ($(this).prop('checked')) {
				// 一旦全てをクリアして再チェックする
				$('.one').prop('checked', false);
				$(this).prop('checked', true);
			}
		});
	})
});

