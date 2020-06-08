
$(document).ready(function() {
	$('#main_stateid').hide();
	var table = $("#example").DataTable();
	 
    $.ajax({
        url: 'displaystate',
        type: "GET",
        contentType : "application/json",
        success: function (data) {
        	var abc=JSON.stringify(data);
        	table.clear();
        	if(!data){
        		alert("sorry there is error of display state..")
        		
        	}else{
        		 
           
        		 $.each(data, function (key, value) {
        			 table.row.add([value['stateId'],
            		value.country.countryName,value['stateName'],
            		'<a href="#" onClick="$(this).update('+value['stateId']+')">update</a>',
            		'<a href="#" onClick="$(this).deletestate('+value['stateId']+')">delete</a>']).draw(true);
            	
            	  /* table.append("<tr><td>"+value.stateId+"</td>" +
                		"<td>"+value.country.countryName+"</td>"+
                		"<td>"+value.stateName+"</td>"+
                		"<td><a href='#' onClick='$(this).update("+value.stateId+")'>update</a></td>"+
                    "<td><a href='#' onClick='$(this).deletestate("+value.stateId+")'>delete</a></td></tr>");*/
            }); 
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
    	 var deleterow= table.row($(this).closest("tr"));
     	$.ajax({    		
             url: 'statedelete/'+paramater,
             type: "DELETE",
             success: function (data) {
            	 deleterow.remove();
            	 table.draw();
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

/*var table=$('#example').DataTable( {
 ajax: abc,
 columns: [
     { "data": "stateId" },
     { "data": "country.countryName" },
     { "data": "stateName" },
     {
         mRender: function (data, type, row) {
             return '<a href="#" onClick="$(this).update('+value['stateId']+')">update</a>'
         }
     },
      DELETE  {
         mRender: function (data, type, row) {
             return '<a href="#" onClick="$(this).deletestate('+value['stateId']+')">delete</a>'
         }
     }     
 ],
paging: false,
searching: false
} ).draw(true);*/

