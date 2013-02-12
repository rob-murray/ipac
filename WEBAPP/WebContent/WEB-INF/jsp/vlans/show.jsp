<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>vlan show - IPAC</title>
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
                <h3>VLAN: <c:out value="${vlan.name}" /></h3>
                
				<div id="selection-menu">
		            <ul class="breadcrumb">
		                <li><a href="${pageContext.request.contextPath}/vlans" title="List vlans">List VLANs</a> <span class="divider">|</span></li>
		                <li><a href="${pageContext.request.contextPath}/vlans/add" title="Add vlan">Add a VLAN</a></li>
		            </ul>
		        </div>

<c:if test="${!empty flashScope.message}">
            <!-- session based flash message -->
            <div id="flash-message"><p><c:out value="${flashScope.message}" /></p></div>
            <!-- end -->
        </c:if>            
        
        <table class="table table-bordered">
            <thead>
		<tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Site</th>
                    <th>VLAN ID</th>
                    <th>Routeable</th>
                    <th>Subnets</th>
                </tr>
            </thead>
            <tbody>
                    <c:url var="addSubnetUrl" value="/subnets/add?vlanId=${vlan.id}" />
                    <c:url var="siteUrl" value="/vlans?siteId=${vlan.site.id}" />
                    <tr>
                        <td><c:out value="${vlan.id}" /></td>
                        <td><c:out value="${vlan.name}" /></td>
                        <td><c:out value="${vlan.descr}" /></td>
                        <td><a href="${siteUrl}"><c:out value="${vlan.site.name}" /></a></td>
                        <td><c:out value="${vlan.swVlanId}" /></td>
                        <td><c:out value="${vlan.routable}" /></td>
                        <td>
                            <c:if test="${!empty vlan.subnetList}">
                                <ul class="unstyled">
                                    <c:forEach items="${vlan.subnetList}" var="subnet">
                                        <c:url var="subnetShowUrl" value="/subnets/${subnet.id}" />
                                        <li><a href="${subnetShowUrl}"><c:out value="${subnet.ipAddress}" /></a></li>
                                    </c:forEach>
                                </ul>
                                
                            </c:if> 
                            <c:if test="${empty vlan.subnetList}">
                                There are no subnets attached to this VLAN.
                            </c:if>
                                Add new <a href="${addSubnetUrl}">Subnet</a> to this VLAN.
                        </td>
                    </tr>
            </tbody>
        </table>
 
        <c:if test="${empty vlan}">
         There is no vlan in the database matching this id.
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