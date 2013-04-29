<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Host Show - IPAC</title>
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
                <h3>Host: <c:out value="${host.name}" /></h3>
                
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
            
        <table class="table table-bordered">
            <thead>
		<tr>
                    <th>Actions</th>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Notes</th>
                    <th>Site</th>
                    <th>Created by</th>
                    <th>Updated by</th>
                </tr>
            </thead>
            <tbody>
                    <c:url var="editHostUrl" value="/hosts/${host.id}/edit" />
                    <c:url var="deleteHostUrl" value="/hosts/${host.id}/delete" />
                    <c:url var="siteUrl" value="/hosts/search?siteId=${host.site.id}" />
                    <tr>
                        <td><a href="${editHostUrl}" >Edit</a> | <a href="${deleteHostUrl}" onclick="return confirm('Are you sure you want to delete object?')">Delete</a></td>
                        <td><c:out value="${host.id}" /></td>
                        <td><c:out value="${host.name}" /></td>
                        <td><c:out value="${host.notes}" /></td>
                        <td><a href="${siteUrl}"><c:out value="${host.site.name}" /></a></td>
                        <td><c:out value="${host.createdBy}" /></td>
                        <td><c:out value="${host.updatedBy}" /></td>
                    </tr>
                    <tr>
                        <td colspan="3">Date created: <c:out value="${host.getFormattedDateCreated()}" /></td>
                        <td colspan="3">Date updated: <c:out value="${host.getFormattedDateUpdated()}" /></td>
                    </tr>                    
            </tbody>     
        </table>            
         
        <h4>Interfaces</h4>
        <table class="table table-bordered">
            <thead>
                    
                    <c:if test="${!empty host.interfaceList}">
                        
                            <tr>
                                <th>Actions</th>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Notes</th>
                                <th>Teamed?</th>
                                <th>IP</th>
                                <th>VLAN</th>
                                <th>Connected to</th>
                            </tr>
                        
                        <c:forEach items="${host.interfaceList}" var="interfaceObj">
                            <c:url var="editInterfaceUrl" value="/interface/${interfaceObj.id}/edit" />
                            <c:url var="deleteInterfaceUrl" value="/interface/${interfaceObj.id}/delete" />
                            <c:url var="addIpUrl" value="/ipaddress/addBasic?interfaceId=${interfaceObj.id}&siteId=${host.site.id}" />
                            <c:url var="connectInterfaceUrl" value="/switchport/connect?interfaceId=${interfaceObj.id}" />
                            <tr>
                                <td><a href="${editInterfaceUrl}">Edit</a> - <a href="${deleteInterfaceUrl}" onclick="return confirm('Are you sure you want to delete object?')">Delete</a></td>
                                <td><c:out value="${interfaceObj.name}" /></td>
                                <td><c:out value="${interfaceObj.interfaceType.name}" /></td>
                                <td>
                                    <c:if test="${!empty interfaceObj.notes}">
                                        <c:out value="${interfaceObj.notes}" />
                                    </c:if>
                                    <c:if test="${empty interfaceObj.notes}">
                                        No description.
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${!empty interfaceObj.teamedInterface}">
                                        Teamed as <c:out value="${interfaceObj.teamedInterface.name}" />
                                    </c:if>
                                    
                                </td>
                                <td>
                                    <c:if test="${empty interfaceObj.teamedInterface}">
                                        <c:if test="${!empty interfaceObj.interfaceIp.ipAddress}">
                                            <c:out value="${interfaceObj.interfaceIp.ipAddress}" /> -
                                            <a href="${pageContext.request.contextPath}/ipaddress/${interfaceObj.interfaceIp.id}/delete" onclick="return confirm('Are you sure you want to delete object?')">Delete</a>
                                        </c:if>
                                        <c:if test="${empty interfaceObj.interfaceIp.ipAddress}">
                                            There is no IP address attached to this interface. <a href="${addIpUrl}">Add</a>.
                                        </c:if>
                                    </c:if>
                                    <c:if test="${!empty interfaceObj.teamedInterface}">
                                        Not available. See teamed interface.
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${!empty interfaceObj.vlan}">
                                        <a href="/vlans/${interfaceObj.vlan.id}"><c:out value="${interfaceObj.vlan.name}" /> - <c:out value="${interfaceObj.vlan.swVlanId}" /></a>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${!empty interfaceObj.switchportDto}">
                                        <c:out value="${interfaceObj.switchportDto.getAsString()}" />
                                        
                                    </c:if>
                                    <c:if test="${empty interfaceObj.switchportDto}">
                                        <c:if test="${interfaceObj.interfaceType.isVirtual == false}">
                                            
                                            Not connected. <a href="${connectInterfaceUrl}">Connect</a>.
                                        
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                            
                        </c:forEach>
                        
                    </c:if>
                            
                    <c:if test="${empty host.interfaceList}">
                        <td colspan="7">There are currently no interfaces attached to this host</td>
                    </c:if>    
                        
                    <tr>
                        <td colspan="7">
                            <a href="${pageContext.request.contextPath}/interface/add?hostId=${host.id}" >Add Interface</a><br />
                            <a href="${pageContext.request.contextPath}/interface/team?hostId=${host.id}" >Bond/Team Interfaces</a>
                        </td>    
                    </tr>    
                      
            </tbody>
        </table>
 
        <c:if test="${empty host}">
         There are currently no hosts in the database with this id: ${host.id}.
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
