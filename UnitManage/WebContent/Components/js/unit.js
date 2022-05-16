/**
 * 
 */

var http_path = "http://localhost:8080/UnitManage";
$(document).ready(function() {
	getAllUnits();

	$("#unit_add_form").submit(function() {
		$.ajax({
			type : "POST",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			data : $("#unit_add_form").serialize(),
			url : http_path + "/Unit/Unit",
			success : function(res) {
				backtoList();
			}
		});
	});
	
	$("#unit_update_form").submit(function() {
		$.ajax({
			type : "PUT",
			contentType : "application/x-www-form-urlencoded;charset=utf-8",
			data : $("#unit_update_form").serialize(),
			url : http_path + "/Unit/Unit",
			success : function(res) {
				backtoList();
			}
		});
		
	});
	
	
	$("#price_per_unit").keydown(function(){
		$("#total_price").val(parseFloat($(this).val()) * parseFloat($("#used_units").val()));
	  });
	
	$("#price_per_unit_u").keydown(function(){
		$("#total_price_u").val(parseFloat($(this).val()) * parseFloat($("#used_units_u").val()));
	  });
	
});


function removeRow(row){
	$.ajax({
		type : "DELETE",
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		data : {id : row},
		url : http_path + "/Unit/Unit",
		success : function(res) {
			$("#row_"+row).remove();
			getAllUnits();
		}
	});
}

function getAllUnits() {
	$.ajax({
		type : "GET",
		url : http_path + "/Unit/Unit",
		success : function(res) {
			$("#tbl_body_day").html('');
			$("#tbl_body_day").html(res);
		}
	});
}

function addFormView() {
	$("#list_view").hide();
	$("#add_form").show();
}

function backtoList() {
	$("#list_view").show();
	$("#add_form").hide();
	$("#update_form").hide();
	getAllUnits();
}

function showUpdateForm(id){
	$.ajax({
		type : "GET",
		data : {id: id},
		url : http_path + "/Unit/Unit/Update",
		success : function(res) {
			$("#unit_update_form").html('');
			$("#unit_update_form").html(res);
			$("#tbl_body_day").html('');
			$("#tbl_body_day").html(res);
		}
	});
	$("#list_view").hide();
	$("#update_form").show();
}
