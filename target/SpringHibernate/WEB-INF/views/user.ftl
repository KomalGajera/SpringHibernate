<#-- <!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">

<title>BORAJI.COM</title>
</head>
<body>
   <h1>This is register form</h1>
   <p th:text="${message}"></p>
</body> -->

<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>Registration form</title>
    <!-- Icons font CSS-->
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link href="resources/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link href="resources/css/jquerysctipttop.css" rel="stylesheet" type="text/css">

    <!-- Main CSS-->
    <link href="resources/css/style.css" rel="stylesheet" media="all">
</head>

<body>
 	<script type="text/javascript">
	        function preventBack() { window.history.forward(); }
	        setTimeout("preventBack()", 0);
	        window.onunload = function () { null };
	    </script>
    <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
            <div class="card card-4">
                <div class="card-body">
                    <h2 class="title p-b-49" id="title">Registration Form</h2>
                    <form method="POST" action="user/add" enctype="multipart/form-data">
                     <input id="user_id" name="user_id" value="0" type="hidden">
                     <input name="length" id="length" type="hidden" >
                     <input name="oldadd" id="oldadd" type="hidden">
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">first name</label> <input class="input--style-4" type="text" name="fname"  id="fname" onblur=" return fname_valid()" onKeyDown="if(event.keyCode===32) return false;">
                                    <span id="firstname"></span><br>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">last name</label> <input class="input--style-4" type="text" name="lname" id="lname" onblur=" return lname_valid()" onKeyDown="if(event.keyCode===32) return false;">
                                    <span id="lastname"></span><br>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <label class="label">Profile</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                <img id="profileimg" src="resources/images/profile.jpg" alt="your image" style="height:120px; width:120px;" />
                                <input type="file" placeholder="select profile" name="profile" id="profile" accept="image/x-png,image/gif,image/jpeg">
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Email</label> <input class="input--style-4" type="email" name="email" id="email" onblur="return email_valid()" onKeyDown="if(event.keyCode===32) return false;">
                                    <span class="error"></span>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Phone Number</label> <input class="input--style-4" type="text" name="number" maxlength="10" id="contact_no" onKeyDown="if(event.keyCode===32) return false;"
                                        onblur=" return number_valid()">
                                    <span id="no"></span><br>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Password</label> <input class="input--style-4" type="password" name="password" id="psw" onKeyDown="if(event.keyCode===32) return false;">
 								<span id="confirm_password"></span><br>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Confirm password</label> <input class="input--style-4" type="password" name="cpassword" id="psw_confirm" onblur=" return confirm_valid()">
                                    <span id="confirm_password"></span><br>
                                </div>
                            </div>
                        </div>
                        <div id="message">
                            <h3>Password must contain the following:</h3>
                            <p id="letter" class="invalid">A <b>lowercase</b> letter</p>
                            <p id="capital" class="invalid">A <b>capital (uppercase)</b> letter</p>
                            <p id="number" class="invalid">A <b>number</b></p>
                            <p id="length" class="invalid">Minimum <b>8 characters</b> Maximum <b>13 length</b></p>
                            <p id="special" class="invalid">A <b>special</b></p>
                        </div>

                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Birthday</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4 js-datepicker" type="text" name="birthdate" id="birthdate"> <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                                   		 <span id="lastname"></span><br>
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Gender</label>
                                    <div class="p-t-10">
                                        <label class="radio-container m-r-45">Male <input
											type="radio" checked="checked" name="gender" value="male" id="male"> <span
											class="checkmark"></span>
										</label> <label class="radio-container">Female <input
											type="radio" name="gender" value="female" id="female"> <span class="checkmark"></span>
										</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <label class="label">Hobby</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                <div>
                                    <input type="checkbox" id="dance" name="hobby" value="dance"> <label for="dancing"> dancing</label>
                                </div>
                                <div>
                                    <input type="checkbox" id="sing" name="hobby" value="sing"> <label for="singing"> singing</label>
                                </div>
                                <div>
                                    <input type="checkbox" id="travel" name="hobby" value="travel"> <label for="travelling">travelling</label>
                                </div>
                            </div>
                        </div>
                        <div id="example1">
                            <button type="button" class="r-btnAdd btn btn btn--radius-2 btn--green ">Add
								+</button>
                            <div class="r-group">
                                <p>
                                    <div class="address1">
                                        <div class="input-group">
                                            <label class="label">address</label>
                                            <div class="rs-select2 js-select-simple select--no-search">
                                                <textarea class="input--style-4" type="text" rows="4" placeholder="Enter address" name="address[0][name]" id="address_0_name" data-pattern-id="address_++_name" data-pattern-name="address[++][name]"  onblur=" return address_valid()"></textarea>
                                                <span id="addr"></span><br>
                                            </div>
                                        </div>
                                    </div>
                                </p>
                                <p>
                                    <!-- Add a remove button for the item. If one didn't exist, it would be added to overall group -->
                                    <button type="button" class="r-btnRemove btn btn btn--radius-2 btn--red">Remove
										-</button>
                                </p>
                            </div>
                        </div>
					  <div class="input-group">
					       <label class="label">Country</label>
					       <div class="rs-select2 js-select-simple">
					       <select name="country" id="country" class="form-control">
						   <option disabled="disabled" selected="selected" value="0">Choose Country</option>
						 	</select>
							 <span id="lastname"></span><br>
					        <div class="select-dropdown"></div>
					 	 </div>
					   </div>
					     <div class="input-group">
					       <label class="label">state</label>
					       <div class="rs-select2 js-select-simple">
					       <select name="state" id="state" class="form-control">
						   <option disabled="disabled" selected="selected" value="0">Choose State</option>
						  <#--    <option th:each="state : ${listState}"
						             th:value="${state.state_name}"
						             th:text="${state.state_name}">
						     </option> -->			  
							</select>
							 <span id="lastname"></span><br>
					        <div class="select-dropdown"></div>
					 	 </div>
					   </div>
                        <span id="countrymessage"> </span><br>
                        <span id="statemessage"> </span><br>
                        <div class="p-t-15">
                            <button id="regis" class="btn btn--radius-2 btn--blue" type="submit" onclick="return valid()">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Jquery JS-->
    <script src="resources/vendor/jquery/jquery.min.js"></script>
    <script src="resources/js/validation.js"></script>
     <script src="resources/js/allvalidation.js"></script>
    <!-- Vendor JS-->
    <script src="resources/vendor/select2/select2.min.js"></script>
    <script src="resources/vendor/daterangepicker/moment.min.js"></script>
    <script src="resources/vendor/daterangepicker/daterangepicker.js"></script>

    <!-- Main JS-->
    <script src="resources/js/custom.js"></script>
    <script src="resources/js/jquery-1.12.4.min.js"></script>

 

</body>


</html>