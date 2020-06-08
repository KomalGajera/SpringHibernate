$(document).ready(function() {
	
	var table = $("#example tbody").dataTable();
	 
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
            		if(user_id==value.userId){
            		 table.append("<tr><td>"+value.userId+"</td>" +
                      		"<td><a href='profile?id="+value.userId+"'>"+value.fname+"</a></td>"+
                      		"<td>"+value.lname+"</td>"+
                      		"<td><img src='image/"+value.userId+"' width='100' height='100'/></td>"+
                      		"<td>"+value.gender+"</td>"+
                      		"<td>"+value.email+"</td>"+
                      		"<td>"+value.number+"</td>"+
                      		"<td>"+value.country+"</td>"+
                      		"<td>"+value.state+"</td>"+
                      		"<td>"+value.role+"</td>"+
                      		"<td><a href='register?id="+value.userId+"'>update</a></td><td></td>"+
                      		"</tr>");
            		}
            		
            	}
            	else{
            		 table.append("<tr><td>"+value.userId+"</td>" +
                     		"<td><a href='profile?id="+value.userId+"'>"+value.fname+"</a></td>"+
                     		"<td>"+value.lname+"</td>"+
                     		"<td><img src='image/"+value.userId+"' width='100' height='100'/></td>"+
                     		"<td>"+value.gender+"</td>"+
                     		"<td>"+value.email+"</td>"+
                     		"<td>"+value.number+"</td>"+
                     		"<td>"+value.country+"</td>"+
                     		"<td>"+value.state+"</td>"+
                     		"<td>"+value.role+"</td>"+
                     		"<td><a href='register?id="+value.userId+"'>update</a></td>"+
                     		(value.role =='user' ? "<td><a href='#' onClick='$(this).deleteuser("+value.userId+")'>delete</a></td></tr>": "<td></td></tr>"));  
	            		 	               
                
            	}    	
            	
            });    
          
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