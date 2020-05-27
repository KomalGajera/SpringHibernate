 <meta http-equiv='cache-control' content='no-cache'>
<meta http-equiv='expires' content='0'>
<meta http-equiv='pragma' content='no-cache'>  
 <link rel="stylesheet" href="resources/css/font-awesome.min.css">
 
 	<script type="text/javascript">
     	  function disableBack() { window.history.forward() }
          window.onload = disableBack();
          window.onpageshow = function(evt) { if (evt.persisted) disableBack() }
	 </script>
		
		<nav class="navbar navbar-expand-lg" id="menu">
		<#if Session.user?exists>
		<input id="user"  type="hidden" name="user" value="${Session.user}">    
		<#else>
		<input id="user"  type="hidden" name="user" value="0"> 
		</#if>
		
		<#if Session.userid?exists>
		<input id="userid" type="hidden" name="userid" value="${Session.userid}">   
		<#else>
				<script type="text/javascript">
				    window.location.href = "http://localhost:8080/SpringHibernate/";
				</script> 
		</#if>
		
		<#if Session.username?exists>
		<a class="navbar-brand" href="#">welcome:${username}</a>  
		<#else>
				<script type="text/javascript">
				    window.location.href = "http://localhost:8080/SpringHibernate/";
				</script>
		
		</#if>




  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
       <li class="nav-item" id="alluser">
        <a class="nav-link" href="showuser">User</a>
      </li>
     <#--  <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>     -->   
      <#if Session.userid?exists>  
    <li class="nav-item" id="profile">
        <a class="nav-link" href="profile?id=${Session.userid}">Yourprofile</a>
      </li>  
      </#if>
      <li class="nav-item" id="country">
        <a class="nav-link" href="addcountry">Country</a>
      </li>
       <li class="nav-item" id="state">
        <a class="nav-link" href="addstate">State</a>
      </li>
    <#--    <li class="nav-item" id="updateprofile">
        <a class="nav-link" href="Register.jsp?id=${sessionScope.userid}">Updateprofile</a>
      </li>
       <c:if test="${sessionScope.username!=null}">-->
		 <li class="nav-item">
        <a class="nav-link" href="logout">Logout</a>
      </li>
     
    </ul>
   
  </div>
</nav>