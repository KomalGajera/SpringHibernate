
$(document).ready(function() {
	
	
	
	$('#main_stateid').hide();
	var table = $("#example tbody");
	 
    $.ajax({
        url: 'displaystate',
        type: "GET",
        contentType : "application/json",
        success: function (data) {
            table.empty();
            $.each(data, function (key, value) {
            	table.append("<tr><td>"+value.state_id+"</td>" +
                		"<td>"+value.country.country_name+"</td>"+
                		"<td>"+value.state_name+"</td>"+
                		"<td><a href='#' onClick='$(this).update("+value.state_id+")'>update</a></td>"+
                    "<td><a href='#' onClick='$(this).deletestate("+value.state_id+")'>delete</a></td></tr>");
            }); 
            $("#example").DataTable();
        }
    });
    
    $.fn.update = function(paramater) {
    	$.ajax({    		
            url: 'stateupdate',data:"id="+paramater,
            type: "POST",
            success: function (data) {
            	$('#main_stateid').show();
            	var abc=JSON.stringify(data);
            	var obj = JSON.parse(abc);
            	$("#selectcountry").val(obj.country.country_name);
            	$(".form-group #state").val(obj.state_name); 	
            	alert(obj.state_name);
            	$("#state_id").val(obj.state_id).attr('readonly', true);             	
        }
        }); 
     };
     
     $.fn.deletestate = function(paramater) {
     	$.ajax({    		
             url: 'statedelete/'+paramater,
             type: "DELETE",
             success: function (data) {
            	 window.location.reload(); 
         },
     	error: function(data) {
            alert('woops!');
        } 
         }); 
      };
     
	$.ajax({url: "displaycountry",
			type:'GET',
			contentType : "application/json",
	        success: function(list){      	        	
	            var select = $('#selectcountry');           
	              $.each(list, function(index, value) {
	              $('<option>').val(value['country_name']).text(value['country_name']).appendTo(select);
	          });
	        }
	});     
});



