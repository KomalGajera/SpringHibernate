
function fname_valid() {
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
    } else if ((fname.length < 2) || (fname.length > 15)) {
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

}

function lname_valid() {
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
    } else if ((lname.length < 2) || (lname.length > 15)) {
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

}

function address_valid() {
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
}


function email_valid() {
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
}

function number_valid() {

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

function password_valid() {


    var psw = document.getElementById('psw').value;
    if (psw == "") {
        document.getElementById('password').innerHTML = "Please enter the password"
        return false;
    } else { document.getElementById('password').innerHTML = "" }

}

function confirm_valid() {
    var psw_confirm = document.getElementById('psw_confirm').value;
    var psw = document.getElementById('psw').value;
    if (psw_confirm == "") {
        document.getElementById('confirm_password').innerHTML = "Please confirm the password"
        return false;
    }
    if (psw != psw_confirm) {
        document.getElementById('confirm_password').innerHTML = "Please match the with above password"
        return false;
    } else { document.getElementById('confirm_password').innerHTML = "" }


}

function file_valid() {
    fileName = document.querySelector('#fileToUpload').value;
    extension = fileName.split('.').pop();
    document.querySelector('.uploader')
        .textContent = extension;


    const fi = document.getElementById('fileToUpload');

    if (fi.files.length > 0) {
        for (const i = 0; i <= fi.files.length - 1; i++) {

            const fsize = fi.files.item(i).size;
            const file = Math.round((fsize / 1024));
            // The size of the file. 
            if (file >= 4096) {
                alert(
                    "File too Big, please select a file less than 4mb");
            } else {
                document.getElementById('size').innerHTML = '<b>' +
                    file + '</b> KB';
            }
        }
    }
}
	 var myInput = document.getElementById("psw");
     var letter = document.getElementById("letter");
     var capital = document.getElementById("capital");
     var number = document.getElementById("number");
     var length = document.getElementById("length");
     var special = document.getElementById("special");

     myInput.onfocus = function() {
         document.getElementById("message").style.display = "block";
     }

     myInput.onblur = function() {
         console.log(letter.classList);
         if (letter.classList == "invalid") {
             document.getElementById("message").style.display = "block";
         }
         if (capital.classList == "invalid") {
             document.getElementById("message").style.display = "block";
         }
         if (number.classList == "invalid") {
             document.getElementById("message").style.display = "block";
         }
         if (length.classList == "invalid") {
             document.getElementById("message").style.display = "block";
         }
         if (special.classList == "invalid") {
             document.getElementById("message").style.display = "block";
         } else {
             document.getElementById("message").style.display = "none";
         }
     }

     myInput.onkeyup = function() {
         // Validate lowercase letters
         var lowerCaseLetters = /[a-z]/g;
         if (myInput.value.match(lowerCaseLetters)) {
             letter.classList.remove("invalid");
             letter.classList.add("valid");
         } else {
             letter.classList.remove("valid");
             letter.classList.add("invalid");
         }

         // Validate capital letters
         var upperCaseLetters = /[A-Z]/g;
         if (myInput.value.match(upperCaseLetters)) {
             capital.classList.remove("invalid");
             capital.classList.add("valid");
         } else {
             capital.classList.remove("valid");
             capital.classList.add("invalid");
         }

         // Validate numbers
         var numbers = /[0-9]/g;
         if (myInput.value.match(numbers)) {
             number.classList.remove("invalid");
             number.classList.add("valid");
         } else {
             number.classList.remove("valid");
             number.classList.add("invalid");
         }




         // Validate length
         if (myInput.value.length >= 8 && myInput.value.length <= 13) {
             length.classList.remove("invalid");
             length.classList.add("valid");
         } else {
             length.classList.remove("valid");
             length.classList.add("invalid");
         }



         // special numbers
         var specials = /[!/@/#/$/%/^/&/*/(/)/_+/~/]/g;
         if (myInput.value.match(specials)) {
             special.classList.remove("invalid");
             special.classList.add("valid");
         } else {
             special.classList.remove("valid");
             special.classList.add("invalid");
         }
     }

