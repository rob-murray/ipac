<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Home - IPAC</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- styles -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/common.css" rel="stylesheet">
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>

  </head>

  <body>


    
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <p class="navbar-text pull-right">
          	
          	<c:choose>
  				<c:when test="${!empty username}">
  					<c:out value="${username}" /> <a href="<c:url value="j_spring_security_logout" />" > Logout</a>
  				</c:when>
          		<c:otherwise>
          			<a href="<c:url value="login" />" > Login</a> 
          		</c:otherwise>
			</c:choose>

          </p>
          <a class="brand" href="${pageContext.request.contextPath}/">IPAC</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
              <li><a href="${pageContext.request.contextPath}/hosts">Hosts</a></li>
              <li><a href="${pageContext.request.contextPath}/vlans">Vlans</a></li>               
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
    
	    <div class="row">
	        <div class="span12">
	        
	         <h3>Home</h3>
		      <div class="selection-menu" id="host-actions">
		      	
		      	<div>
		      		<h5>Host management</h5>
		      	
		            <ul class="breadcrumb">
		                <li><a href="${pageContext.request.contextPath}/hosts" title="List all hosts">All hosts</a> <span class="divider">|</span></li>
		                <li><a href="${pageContext.request.contextPath}/hosts/search" title="Search hostnames">Search hostnames</a> <span class="divider">|</span></li>
		                <li><a href="${pageContext.request.contextPath}/hosts/add">Create a new host</a></li>
		            </ul>
		      	
		      	
		      	</div>
		      	
		      	<div>
		      		<h5>VLAN management</h5>
		      	
		            <ul class="breadcrumb">
		                <li><a href="${pageContext.request.contextPath}/vlans" title="List vlans">All VLANs</a> <span class="divider">|</span></li>
		                <li><a href="${pageContext.request.contextPath}/vlans/add">Create a new VLAN</a></li>
		            </ul>
		      	
		      	
		      	</div>
		     </div>
	
			        
			        
			</div>
		</div>
		
		<footer>
	    	<hr>
	        <p class="text-right"><small>IPAC @version: <c:out value="${ipacVersion}" /></small></p>
	    </footer>

    </div> <!-- /container -->

    <!-- javascript -->
    <script src="${pageContext.request.contextPath}/resources/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/ipac.js"></script>

  </body>
</html>