$(document).ready(function() {

	$("#email").blur(function(){			
	 	var email=$( "#email" ).val();    	
			$.ajax({url: "checkemail",type:'POST',data:'email='+email,
			
	        success: function(list){   
	        	if(list==0){
	        		$(".error").html("email does not exits");
	        		
	        	}else{
	        		$(".error").html("");
	        	}	 	         
	        },
	        error: function(data) {
	            alert('woops!');
	        } 
		});
});
});