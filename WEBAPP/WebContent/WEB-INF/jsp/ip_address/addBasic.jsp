<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Add IP Address - IPAC</title>
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
                <h3>Add IP Address</h3>

				<p>Select interface VLAN and add IP Address</p>
        
        <c:if test="${!empty flashScope.message}">
            <!-- session based flash message -->
            <div id="flash-message"><p><c:out value="${flashScope.message}" /></p></div>
            <!-- end -->
        </c:if>
        
        <c:url var="saveUrl" value="/ipaddress/addBasic?interfaceId=${interfaceId}&siteId=${siteId}" />
        <form:form modelAttribute="interfaceIpAttribute" method="POST" action="${saveUrl}">
            
            <table class="table table-bordered">
                <tr>
                    <td><label for="vlanid">Vlan:</label></td>
                    <td>
                        <select name="vlanId" id="vlanId" onchange="ipac.getListSubnets(); return false;">
                            <option value="0">- - -</option>
                        <c:forEach items="${vlanList}" var="vlan" varStatus="status">
                            <option value="<c:out value="${vlan.id}" />"><c:out value="${vlan.name}" /></option>
                        </c:forEach>
                        </select>

                </tr>
                
		<tr>
			<td><form:label path="subnetId">Subnet:</form:label></td>
			<td><form:hidden path="subnetId"  /> <input readonly="readonly" id="subnetName" name="subnetName" /></td>
		</tr> 

                
		<tr>
			<td><form:label path="ipAddress">ipAddress:</form:label></td>
			<td><form:input path="ipAddress"/></td>
		</tr>
	
            </table>
            
            <p>Get <a href="#" title="Get next ip" onclick="ipac.getNextAvailableIp();return false;">next available IP</a></p>
            <p id="status-update"></p>
	
            <input type="submit" value="Save" />
            
            <p><a class="btn" href="javascript:history.back();" title="Cancel IP address">Cancel</a></p>
            
        </form:form>
            
            
            
        

            </div>
        </div>
        
		<footer>
	    	<hr>
	        <p class="text-right"><small>IPAC @version: <c:out value="${ipacVersion}" /></small></p>
	    </footer>        
      

    </div> <!-- /container -->
    
    <script type="text/javascript">
    
    </script>

    <!-- javascript -->
    <script src="${pageContext.request.contextPath}/resources/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/ipac.js"></script>

  </body>
</html>