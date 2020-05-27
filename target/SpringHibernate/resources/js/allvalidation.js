function valid(){
	 var fname = document.getElementById('fname').value;
	    var number = false;
	    var specialcaseLetter = false;

	    for (var i = 0; i < fname.length; i++) {
	        var charCode = fname.charCodeAt(i);

	        if (charCode > 47 && charCode < 58)
	            number = true;

	        if ((charCode > 32 && charCode < 48) || (charCode > 57 && charCode < 65) || (charCode > 90 && charCode < 97) || (charCode > 122 && charCode <= 126) || charCode == 64)
	            specialcaseLetter = true;
	    }

	    if (fname == "") {
	        document.getElementById('firstname').innerHTML = "Please enter the firstname"
	        return false;
	    } else if ((fname.length < 2) || (fname.length > 20)) {
	        document.getElementById('firstname').innerHTML = "Please enter the username length between 2 to 20"
	        return false;
	    } else if (number) {
	        document.getElementById('firstname').innerHTML = "Please enter only character"
	        return false;
	    } else if (specialcaseLetter) {
	        document.getElementById('firstname').innerHTML = "special character not allow"
	        return false;
	    } else {
	        document.getElementById('firstname').innerHTML = ""
	    }
	    
	    
	    var lname = document.getElementById('lname').value;

	    var number = false;
	    var specialcaseLetter = false;

	    for (var i = 0; i < lname.length; i++) {
	        var charCode = lname.charCodeAt(i);

	        if (charCode > 47 && charCode < 58)
	            number = true;

	        if ((charCode > 32 && charCode < 48) || (charCode > 57 && charCode < 65) || (charCode > 90 && charCode < 97) || (charCode > 122 && charCode <= 126) || charCode == 64)
	            specialcaseLetter = true;
	    }


	    if (lname == "") {
	        document.getElementById('lastname').innerHTML = "Please enter the lastname"
	        return false;
	    } else if ((lname.length < 2) || (lname.length > 20)) {
	        document.getElementById('lastname').innerHTML = "Please enter the username length between 2 to 20"
	        return false;
	    } else if (number) {
	        document.getElementById('lastname').innerHTML = "Please enter only character"
	        return false;
	    } else if (specialcaseLetter) {
	        document.getElementById('lastname').innerHTML = "special character not allow"
	        return false;
	    } else {
	        document.getElementById('lastname').innerHTML = ""
	    }
	    
	    var address = document.getElementById('address').value;

	    var i;
	    var count = 0;
	    for (i = 0; i < address.length; i++) {
	        if (address[i] != ' ') {
	            count++;
	        }
	    }
	    console.log(count);
	    if (address == "") {
	        document.getElementById('addr').innerHTML = "Please enter the address"
	        return false;
	    } else if ((count < 30)) {
	        document.getElementById('addr').innerHTML = "address should be in 30 to 50 chrecter"
	        return false;
	    } else {
	        document.getElementById('addr').innerHTML = ""
	    }
	        
	    
	    
	    var email = document.getElementById('email').value;
	    var em = email.split('@');

	    var count = 0;
	    var count1 = 0;
	    var at = 0;
	    var specialcaseLetter = false;

	    for (var i = 0; i < email.length; i++) {
	        var charCode = email.charCodeAt(i);
	        if (charCode == 64) {
	            at++;
	        }
	        if ((charCode > 32 && charCode < 45) || (charCode > 47 && charCode < 48) || (charCode > 57 && charCode < 63) || (charCode == 65) || (charCode > 90 && charCode < 97) || (charCode > 122 && charCode <= 126))
	            specialcaseLetter = true;
	    }
	    for (var i = 0; i < em[0].length; i++) {
	        if (em[0][i] == '.') {
	            count++;
	        }
	    }
	    for (var i = 0; i < em[1].length; i++) {
	        if (em[1][i] == '.') {
	            count1++;
	        }
	    }
	    console.log(count)
	    var garray = email.split('@');
	    console.log(email);
	    if (email == "") {
	        document.getElementById('emailid').innerHTML = "Please enter the email id"
	        return false;
	    } else if (email.indexOf('@') <= 2) {
	        document.getElementById('emailid').innerHTML = "Please enter @ into right place"
	        return false;
	    } else if (specialcaseLetter) {
	        document.getElementById('emailid').innerHTML = "only @ and . special char allow"
	        return false;
	    } else if ((email.charAt(email.length - 4) != '.') && (email.charAt(email.length - 3) != '.')) {
	        document.getElementById('emailid').innerHTML = "dont add more than 3 character after .  "
	        return false;
	    } else if ((garray[1].split('.'))[0].length < 3) {
	        document.getElementById('emailid').innerHTML = "Please enter @ into right place"
	        return false;
	    } else if (count > 1) {
	        document.getElementById('emailid').innerHTML = "before @ one dot allow"
	        return false;

	    } else if (count1 > 2) {
	        document.getElementById('emailid').innerHTML = "after  @ only two . allow"
	        return false;

	    } else if (at > 1) {
	        document.getElementById('emailid').innerHTML = "only 1 @ allow"
	        return false;

	    } else {
	        document.getElementById('emailid').innerHTML = ""
	    }
	    
	    
	    var number = document.getElementById('contact_no').value;

	    if (number == "") {
	        document.getElementById('no').innerHTML = "Please enter contact number"
	        return false;
	    }
	    if ((number.length < 10) || number.length > 13) {
	        document.getElementById('no').innerHTML = "Please enter 10 number"
	        return false;
	    }
	    if (isNaN(number)) {
	        document.getElementById('no').innerHTML = "Please enter only number"
	        return false;
	    } else {
	        document.getElementById('no').innerHTML = ""
	    }

}
