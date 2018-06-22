$(document).ready(function() {
	$('#slider').layerSlider({
		sliderVersion: '6.0.0',
		type: 'fullwidth',
		responsiveUnder: 1340,
		hideUnder: 0,
		hideOver: 100000,
		skin: 'roundedflat',
		globalBGColor: '#ffffff',
		navStartStop: false,
		allowFullscreen: false,
		skinsPath: '/EventPrestigeApp/assets/layerslider/skins/'
	});

	$('#table-1').DataTable();
	$('#table-2').DataTable();
	
	$('[data-toggle="datepicker"]').intimidatetime({
		format: 'yyyy-MM-dd hh:mm:ss',
		previewFormat: 'dd-MM-yyyy hh:mm'
	});
	
	$('[data-toggle="datepicker-i"]').datepicker({
		format: 'mm-dd-yyyy'
	});
	
	var isConfirmed = false;
	
	$('.delete-confirm').on('click',function(e){
		var target = $(this).attr("href");
		
		if(!isConfirmed){
			$.confirm({
		        title: 'Confirm Delete!',
		        content: 'Are you sure you want to delete ' + $(this).attr("data-title") + '?',
		        icon: 'far fa-question-circle',
		        animation: 'scale',
		        closeAnimation: 'scale',
		        useBootstrap: false,
		        opacity: 0.5,
		        buttons: {
		            'confirm': {
		                text: 'Proceed',
		                btnClass: 'button success',
		                action: function () {
		                	isConfirmed=true;
		                	location.href = target;
		                	return true;
		                }
		            },
		            cancel: function () {
		            	
		            }
		        }
		    });
			
			e.preventDefault();
            return false;
		}else{
			return true
		}
	});
	
	$('input[type=file]').on('change',function(e){
		var filename = $(this).val().split('\\').pop();
		$("label[for=FileUpload]").html("File(s) selected: " + filename);
	});
});

$(document).foundation();