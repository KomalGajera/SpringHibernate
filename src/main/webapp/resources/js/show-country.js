$(document).ready(function() {
	$('#main_countryid').hide();
	var table = $("#example tbody");
	 
    $.ajax({
        url: 'displaycountry',
        type : "GET",
		contentType : "application/json",
        success: function (data) {
        	
        	if(!data){
        		alert("sorry there is error of display country..")
        		
        	}
        	else{
        		table.empty();
                $.each(data, function (key, value) {              	
                	
                    table.append("<tr><td>"+value.country_id+"</td>" +
                        "<td>"+value.country_name+"</td>"+
                        "<td><a href='#' onClick='$(this).update("+value.country_id+")'>update</a></td>"+
                        "<td><a href='#' onClick='$(this).deletecountry("+value.country_id+")'>delete</a></td></tr>");
                }); 
                $("#example").DataTable();
        		
        	}
           
    }
    }); 

    $.fn.update = function(paramater) {
    	$.ajax({    		
            url: 'countryupdate',data:"id="+paramater,
            type: "POST",
            success: function (data) {
            	if(!data){
            		alert("sorry there is error of get country by id..")
            		
            	}else{
            	$('#main_countryid').show();
            	var abc=JSON.stringify(data);
            	var obj = JSON.parse(abc);
            	$(".form-group #country").val(obj.country_name); 	  
            	$("#country_id").val(obj.country_id).attr('readonly', true); 
            	}
        }
        }); 
     }; 
     
     $.fn.deletecountry = function(paramater) {
     	$.ajax({    		
             url: 'countrydelete/'+paramater,
             type: "DELETE",
             success: function (data) {
            	 window.location.reload();
         }
         }); 
      }; 
});

