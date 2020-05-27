
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
   <link rel="stylesheet" type="text/css" href="resources/css/dataTables.bootstrap4.min.css">   
	<link rel="stylesheet"   type="text/css" href="resources/css/jquery-ui.css" />
   <link rel="stylesheet" type="text/css" href="resources/css/header.css">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
</head>
<body>

<#include "header.ftl">

<div class='table-container'>
   <table id="example" class="table table-striped table-bordered" style="width:100%">
        <thead>
            <tr>
            	<th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>profile</th>
                <th>Gender</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Country</th>
                <th>State</th>
                <th>role</th>
                 <th>update</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>           
        </tbody>
        <tfoot>
            <tr>
               <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>profile</th>
                <th>Gender</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Country</th>
                <th>State</th>
                <th>role</th>
                <th>update</th>
                <th>Delete</th>
            </tr>
        </tfoot>
    </table>
   </div>

 		<#include "footer.ftl">
        <script src='resources/js/jquery-3.3.1.js'></script>
        <script src="resources/js/jquery-3.4.1.min.js"></script>
        <script src="resources/js/popper.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src='resources/js/jquery.dataTables.min.js'></script>
        <script src='resources/js/dataTables.bootstrap4.min.js'></script>
        <script src='resources/js/show-datatable.js'></script>
        <script src="resources/js/jquery-ui.min.js"></script>
</body>
</html>