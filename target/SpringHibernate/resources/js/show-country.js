$(document).ready(function() {
	$('#main_countryid').hide();
	var table = $("#example").DataTable();
	
    $.ajax({
        url: 'displaycountry',
        type : "GET",
		contentType : "application/json",
        success: function (data) {
        	table.clear();
        	if(!data){
        		alert("sorry there is error of display country..")        		
        	}
        	else{ 
        		$.each(data, function (key, value) {   
                	
                	table.row.add([value['countryId'],
                		value['countryName'],
                		'<a href="#" onClick="$(this).update('+value['countryId']+')">update</a>',
                		'<a href="#" onClick="$(this).deletecountry('+value['countryId']+')">delete</a>']).draw(true);
                	
                   /* table.append("<tr><td>"+value.countryId+"</td>" +
                        "<td>"+value.countryName+"</td>"+
                        "<td><a href='#' onClick='$(this).update("+value.countryId+")'>update</a></td>"+
                        "<td><a href='#' onClick='$(this).deletecountry("+value.countryId+")'>delete</a></td></tr>");*/
                }); 
        		
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
            	$(".form-group #country").val(obj.countryName); 	  
            	$("#country_id").val(obj.countryId).attr('readonly', true); 
            	}
        }
        }); 
     }; 
     
     $.fn.deletecountry = function(paramater) {
    	var deleterow= table.row($(this).closest("tr"));
     	$.ajax({    		
             url: 'countrydelete/'+paramater,
             type: "DELETE",
             success: function (data) {
            	 deleterow.remove();
            	 table.draw();
         }
         }); 
      }; 
});

