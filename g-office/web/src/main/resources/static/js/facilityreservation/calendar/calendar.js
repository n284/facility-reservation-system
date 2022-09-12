$(function(){
	$("#year, #month").change(function(){
		var year = $("#year").val();
		var month = Number($("#month").val());
		var facility_id = $("#facilityId").val();
		var url = `/facilityreservation/remake/${facility_id}/${year}/${month}`;

		$.ajax({
			url:url,
			type:"GET",
			dataType:"html"
		}).done(function(data){
			history.replaceState(null, null, url);
			$("table").html($("table", data).html());

		}).fail(function(){
			alert("サーバーに接続できませんでした");
		});
	});

	$(".button").click(function(){
		let date = new Date();
		let year = date.getFullYear();
		let name = $(this).attr("name");
		let month;

		switch(name){
			case "before":
				month = date.getMonth() ;
				break;
			case "current":
				month = date.getMonth() + 1;
				break;
			case "next":
				month = date.getMonth() + 2;
				break;
		}

		var facility_id = $("#facilityId").val();
		var url = `/facilityreservation/remake/${facility_id}/${year}/${month}`;

		$.ajax({
			url:url,
			type:"GET",
			dataType:"html"
		}).done(function(data){
			$(`#year option[value=${year}]`).prop("selected", true);
			$(`#month option[value=${month}]`).prop("selected", true);
			history.replaceState(null, null, url);
			$("table").html($("table", data).html());

		}).fail(function(){
			alert("サーバーに接続できませんでした");
		});
	});
})