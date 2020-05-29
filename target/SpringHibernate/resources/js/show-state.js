
$(document).ready(function() {
	
	
	
	$('#main_stateid').hide();
	var table = $("#example tbody");
	 
    $.ajax({
        url: 'displaystate',
        type: "GET",
        contentType : "application/json",
        success: function (data) {
        	
        	if(!data){
        		alert("sorry there is error of display state..")
        		
        	}else{
            table.empty();
            $.each(data, function (key, value) {
            	table.append("<tr><td>"+value.stateId+"</td>" +
                		"<td>"+value.country.countryName+"</td>"+
                		"<td>"+value.stateName+"</td>"+
                		"<td><a href='#' onClick='$(this).update("+value.stateId+")'>update</a></td>"+
                    "<td><a href='#' onClick='$(this).deletestate("+value.stateId+")'>delete</a></td></tr>");
            }); 
            $("#example").DataTable();
        	}
        }
    });
    
    $.fn.update = function(paramater) {
    	$.ajax({    		
            url: 'stateupdate',data:"id="+paramater,
            type: "POST",
            success: function (data) {
            	
            	if(!data){
            		alert("sorry there is error of get state by id..")
            		
            	}else{
            	$('#main_stateid').show();
            	var abc=JSON.stringify(data);
            	var obj = JSON.parse(abc);
            	$("#selectcountry").val(obj.country.countryName);
            	$(".form-group #state").val(obj.stateName); 	
            	$("#state_id").val(obj.stateId).attr('readonly', true);      
            	}
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
	              $('<option>').val(value['countryName']).text(value['countryName']).appendTo(select);
	          });
	        }
	});     
});



