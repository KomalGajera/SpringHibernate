
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="resources/css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/header.css">
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
</head>

<body>
    	<#include "header.ftl">
    	  <h1 class="error">  <#if Delete?exists>  ${Delete}  </#if>  </h1>
		<div class='table-container'>		 
            <table id="example" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>City Name</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
               <#--  <#list listCountry as u>	
		        
		        <tr>	      
				<td>${u.getCountry_id()}</td>
				<td>${u.getCountry_name()}</td>  
				<td>Update</td>
				<td>Delete</td> 
				</tr>
				</#list>	 -->	
                </tbody>
                <tfoot>
                    <tr>
                        <th>ID</th>
                        <th>City Name</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </tfoot>
            </table>
        </div>
        
		<fieldset>
        <div class="container" id="country12">
      
            <form name="country" action="countryadd" method="post">
              <h1 class="error">  <#if Message?exists>  ${Message}  </#if>  </h1>
             
                <h3>Country-Form</h3>
                <div class="form-group" id="main_countryid">
                    <label for="countryid">county id:</label>
                    <input type="text" class="form-control" id="country_id" name="countryId" value="0">
                </div> 
                <div class="form-group">
                    <label for="country">Add Country:</label>
                    <input type="text" class="form-control" id="country" placeholder="Enter country" name="countryName" >
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
		</fieldset>
        <#include "footer.ftl">
        <script src='resources/js/jquery-3.3.1.js'></script>
        <script src="resources/js/jquery-3.4.1.min.js"></script>
        <script src="resources/js/popper.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src='resources/js/jquery.dataTables.min.js'></script>
        <script src='resources/js/dataTables.bootstrap4.min.js'></script>
        <script src='resources/js/show-country.js'></script>
        <#--  <script>
  $(function(){
  	$('#main_countryid').hide();
    $("#example").dataTable();
  })
  </script> -->
</body>

</html>