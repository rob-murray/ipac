<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Host Search - IPAC</title>
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
              <li class="active"><a href="${pageContext.request.contextPath}/hosts">Hosts</a></li>
              <li><a href="${pageContext.request.contextPath}/vlans">Vlans</a></li> 
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">

        <div class="row">
	        <div class="span12">
                <h3>Search hostnames</h3>
                
                <div id="selection-menu">
		            <ul class="breadcrumb">
		                <li><a href="${pageContext.request.contextPath}/hosts" title="List hosts">List all hosts</a> <span class="divider">|</span></li>
		                <li><a href="${pageContext.request.contextPath}/hosts/search" title="Search hosts">Search hosts</a> <span class="divider">|</span></li>
		                <li><a href="${pageContext.request.contextPath}/hosts/add" title="Add host">Add a host</a></li>
		            </ul>    
		        </div>                

		<c:if test="${!empty flashMessage}">
            <!-- session based flash message -->
            <div id="flash-message" class="alert alert-info"><p><c:out value="${flashMessage}" /></p></div>
            <!-- end -->
        </c:if>        
            
            
        <c:url var="searchUrl" value="/hosts/search" />
        <form:form method="GET" action="${searchUrl}">
            
            <table>
		<tr>
			<td><label for="searchStr">Name</label></td>
                        <td><input id="searchStr" name="searchStr" type="text" value=""/></td>
		</tr>
		
            </table>
	
            <input type="submit" value="Search" />
            
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