$(function() {
	$('#side-menu').metisMenu();
});

// Loads the correct sidebar on window load,
// collapses the sidebar on window resize.
$(function() {
	$(window).bind("load resize", function() {
		if ($(this).width() < 768) {
			$('div.sidebar-collapse').addClass('collapse');
		} else {
			$('div.sidebar-collapse').removeClass('collapse');
		}
	});
});

$('#birthdayDate').datepicker();
$('.input-daterange').datepicker();

$(document).ready(function() {
//	$("#select2Form").prepend("<option selected='selected'></option>");
	$("#select2Form").select2({
		allowClear : true
	});
	$('#dataTable').dataTable({
		"aaSorting" : [],
		"language" : {
			"search" : "Search _INPUT_"
		}
	});
	$('input[type=file]').bootstrapFileInput();
	$('.file-inputs').bootstrapFileInput();
	
	if ($('#randomCheckbox').attr('checked')) {
		$("#questionsCount").show();
	}else{
		$("#questionsCount").hide();
	}
});

$("#randomCheckbox").change(function() {
    if(this.checked) {
    	$("#questionsCount").show();
    }else{
		$("#questionsCount").hide();
    }
});
