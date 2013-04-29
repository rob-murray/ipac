<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>subnet show - IPAC</title>
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
                <h3>Subnet: <c:out value="${subnet.ipAddress}" /></h3>

		<c:if test="${!empty flashMessage}">
            <!-- session based flash message -->
            <div id="flash-message" class="alert alert-info"><p><c:out value="${flashMessage}" /></p></div>
            <!-- end -->
        </c:if>     
        
        <table class="table table-bordered">
            <thead>
		<tr>
                    <th>Id</th>
                    <th>IP Address</th>
                </tr>
            </thead>
            <tbody>
                    <tr>
                        <td><c:out value="${subnet.id}" /></td>
                        <td><c:out value="${subnet.ipAddress}" /></td>
                    </tr>
            </tbody>
        </table>
                    
        <h4>IP Address and interfaces for this subnet</h4>
        <table class="table table-bordered">            
            <thead>
		<tr>
                    <th>IP Address</th>
                    <th>Interface Name</th>
                    <th>Host</th>
                </tr>
            </thead>
            <tbody>
                    <c:if test="${!empty interfaceList}">
                        <c:forEach items="${interfaceList}" var="interfaceObj">
                            <c:url var="showHostUrl" value="/hosts/${interfaceObj.hostid}" />
                            <tr>
                                <td><c:out value="${interfaceObj.interfaceipdddr}" /></td>
                                <td><c:out value="${interfaceObj.interfacename}" /></td>
                                <td><a href="${showHostUrl}"><c:out value="${interfaceObj.hostname}" /></a></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                            
                    <c:if test="${empty interfaceList}">
                        <tr><td colspan="3">No IP addresses allocated on this subnet.</td></tr>
                    </c:if>
                            

                    
            </tbody>
        </table>
 
        <c:if test="${empty subnet}">
         There is no subnet in the database matching this id.
        </c:if>

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