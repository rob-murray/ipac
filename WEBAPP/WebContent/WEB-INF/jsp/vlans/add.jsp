<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>vlan add - IPAC</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
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
              <li><a href="${pageContext.request.contextPath}/">Home</a></li>
              <li><a href="${pageContext.request.contextPath}/hosts">Hosts</a></li>
              <li class="active"><a href="${pageContext.request.contextPath}/vlans">Vlans</a></li>              
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">

        <div class="row">
	        <div class="span12">
                <h3>Create new VLAN</h3>
                

		<c:if test="${!empty flashMessage}">
            <!-- session based flash message -->
            <div id="flash-message" class="alert alert-info"><p><c:out value="${flashMessage}" /></p></div>
            <!-- end -->
        </c:if>        
        
        <c:url var="saveUrl" value="/vlans/add" />
        <form:form modelAttribute="vlanAttribute" method="POST" action="${saveUrl}" class="form-horizontal">
        
        
        	<div class="control-group">
    			<form:label class="control-label" path="name">Name:</form:label>
    			<div class="controls">
    				<form:input path="name" />
    			</div>
    		</div>
    		
    		<div class="control-group">
    			<form:label class="control-label" path="descr">Description:</form:label>
    			<div class="controls">
    				<form:input path="descr" />
    			</div>
    		</div>
    		
    		<div class="control-group">
    			<form:label class="control-label" path="swVlanId">VLAN ID:</form:label>
    			<div class="controls">
    				<form:input path="swVlanId" type="number" />
    			</div>
    		</div>
    		
    		<div class="control-group">
    			<form:label class="control-label" path="routable">Routable?</form:label>
    			<div class="controls">
    				<form:checkbox path="routable" />
    			</div>
    		</div>
    		
    		<div class="control-group">
    			<form:label class="control-label" path="siteId">Site:</form:label>
    			<div class="controls">
    				<form:select path="siteId" items="${siteList}" itemLabel="name" itemValue="id"/>
    			</div>
    		</div>

			<div class="form-actions">
    			<button type="submit" class="btn btn-primary">Save changes</button>
    			<a class="btn" href="${pageContext.request.contextPath}/vlans" title="Cancel">Cancel</a>
    		</div>
            
        </form:form>
        
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