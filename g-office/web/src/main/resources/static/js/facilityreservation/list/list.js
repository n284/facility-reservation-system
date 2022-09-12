$(function(){
	$("select.type").change(function(){
		var facility_type_id = $("select").val();
		var url = "/facilityreservation/remake/" + facility_type_id;
		$.ajax({
			type:"GET",
			url:url,
			dataType : "html",
			success : function(data, status, xhr){
				if(facility_type_id == 0){
					url = "/facilityreservation/list/";
				}
				history.replaceState( null, null, url);
				$(".col_3").html($('.col_3', data).html());
			},
			error : function(XMLHttpRequest, status, errorThrown){
				alert("サーバーに接続できませんでした");
			}
		});
	});
/*	$("select.type").change(function(){
		var facility_type_id = $("select").val();
		var url = "/facilityreservation/remake/" + facility_type_id;
		$.ajax({
			type:"GET",
			url:url,
			dataType : "JSON",
			success : function(data, status, xhr){
				if(facility_type_id == 0){
					url = "/facilityreservation/list/";
				}
				history.replaceState( null, null, url);
				var source = "";
				for(var i = 0; i < data.facilityFormList.length; i++){
					source +=`<div class="r-col">
						<a href="/facilityreservation/calendar/${data.facilityFormList[i].facilityId}">
							<span>${data.facilityFormList[i].name}</span>
							<br>
							<span>定員${data.facilityFormList[i].capacity}</span>
						</a>
					</div>`;
				}
				console.log(source);
				$(".col_3").html(source);

			},
			error : function(XMLHttpRequest, status, errorThrown){
				alert("データベースにアクセスできませんでした");
			}
		});
	});*/

	$("select.year-month").change(function(){
		var facility_id = $("p.info").attr("id");
		var year = $(".year").val();
		var month = $(".month").val();
		var url = "/facilityreservation/calendar/" + facility_id+"/"+year+"/"+month;
		console.log(url)
		$.ajax({
			type:"GET",
			url:url,
			dataType : "html",
			success : function(data, status, xhr){
/*				if(facility_type_id == 0){
					url = "/facilityreservation/list/";
				}*/
				history.replaceState( null, null, url);
				$("table").html($('table', data).html());
			},
			error : function(XMLHttpRequest, status, errorThrown){
				alert("データベースにアクセスできませんでした");
			}
		});
	});
});