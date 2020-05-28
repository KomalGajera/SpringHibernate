$(document).ready(function() {
	
	var table = $("#example tbody");
	 
    $.ajax({
        url: 'displayuser',
        type: "GET",
        contentType : "application/json",
        success: function (data) {
        	
        	if(!data){
        		alert("sorry there is error of display user..")
        		
        	}else{
            table.empty();
            $.each(data, function (key, value) {
            	
            	var user=$( "#user" ).val(); 
            	var user_id=$( "#userid" ).val(); 
            	if(user=='user')
            	{
            		if(user_id==value.id){
            		 table.append("<tr><td>"+value.id+"</td>" +
                      		"<td><a href='profile?id="+value.id+"'>"+value.fname+"</a></td>"+
                      		"<td>"+value.lname+"</td>"+
                      		"<td><img src='image/"+value.id+"' width='100' height='100'/></td>"+
                      		"<td>"+value.gender+"</td>"+
                      		"<td>"+value.email+"</td>"+
                      		"<td>"+value.number+"</td>"+
                      		"<td>"+value.country+"</td>"+
                      		"<td>"+value.state+"</td>"+
                      		"<td>"+value.role+"</td>"+
                      		"<td><a href='register?id="+value.id+"'>update</a></td><td></td>"+
                      		"</tr>");
            		}
            		
            	}
            	else{
            		 table.append("<tr><td>"+value.id+"</td>" +
                     		"<td><a href='profile?id="+value.id+"'>"+value.fname+"</a></td>"+
                     		"<td>"+value.lname+"</td>"+
                     		"<td><img src='image/"+value.id+"' width='100' height='100'/></td>"+
                     		"<td>"+value.gender+"</td>"+
                     		"<td>"+value.email+"</td>"+
                     		"<td>"+value.number+"</td>"+
                     		"<td>"+value.country+"</td>"+
                     		"<td>"+value.state+"</td>"+
                     		"<td>"+value.role+"</td>"+
                     		"<td><a href='register?id="+value.id+"'>update</a></td>"+
                     		(value.role =='user' ? "<td><a href='#' onClick='$(this).deleteuser("+value.id+")'>delete</a></td></tr>": "<td></td></tr>"));  
	            		 	               
                
            	}    	
            	
            });    
            $("#example").DataTable();
        	}
        },
        error: function(data) {
            alert('woops!');
        } 
        
    });   
    
    
    $.fn.deleteuser = function(paramater) {
    	    	
	    	$('<div></div>').appendTo('body')
	        .html('<div><h6>Are you sure to delete userId:'+paramater+'??</h6></div>')
	        .dialog({
	          modal: true,
	          title: 'Delete message',
	          zIndex: 10000,
	          autoOpen: true,
	          width: 'auto',
	          resizable: false,
	          buttons: {
	            Yes: function() {            
	              $.ajax({    		
	                  url: 'userdelete/'+paramater,
	                  type: "DELETE",
	                  success: function (data) {
	                 	 window.location.reload();
	              }
	              }); 
	              $(this).dialog("close");
	            },
	            No: function() {
	              $(this).dialog("close");
	            }
	          }
	        });
    	
    	
      }; 
});