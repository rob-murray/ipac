<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Host List - IPAC</title>
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
                <h3>Hosts</h3>
                
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
                    <th>Id</th>
                    <th>Name</th>
                    <th>Notes</th>
                    <th>Site</th>
                    <th>Date Created</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${!empty pagedHostView.hosts}">
                    <c:forEach items="${pagedHostView.hosts}" var="host">
                        <c:url var="showUrl" value="/hosts/${host.id}" />
                        <c:url var="siteUrl" value="/hosts/search?siteId=${host.site.id}" />
                        <tr>
                            <td><c:out value="${host.id}" /></td>
                            <td><a href="${showUrl}"><c:out value="${host.name}" /></a></td>
                            <td><c:out value="${host.notes}" /></td>
                            <td><a href="${siteUrl}"><c:out value="${host.site.name}" /></a></td>
                            <td><c:out value="${host.getFormattedDateCreated()}" /></td>
                        </tr>
                    </c:forEach>
                </c:if>     
                        
                <c:if test="${empty pagedHostView.hosts}">
                    <tr><td colspan="7">No hosts found.</td></tr>
                </c:if>                        
            </tbody>
        </table>
 

         
<c:choose>
    <c:when test="${empty param.searchStr}">
        <c:url value="?" var="url"/>
    </c:when>
    <c:otherwise>
        <c:url value="?searchStr=${param.searchStr}&" var="url"/>
    </c:otherwise>
</c:choose>             
            <div id="paging-container" class="pagination">
                
                <ul id="page-list">
                    <c:if test="${pagedHostView.navInfo.pageCount >1}">                
						<c:if test="${!pagedHostView.navInfo.firstPage}">
    						<li><a class="next" href="${url}page=${pagedHostView.navInfo.currentPage -1}">Previous</a></li>
						</c:if>
						<c:forEach var="i" items="${pagedHostView.navInfo.indexList}">
	    					<c:choose>
	        					<c:when test="${i != pagedHostView.navInfo.currentPage}">
	            					<li><a href="${url}page=${i}">${i}</a></li>
	       						</c:when>
	        					<c:otherwise>
	            					<li class="active"><a href="#">${i}</a></li>
	        					</c:otherwise>
	    					</c:choose>
						</c:forEach>
						<c:if test="${!pagedHostView.navInfo.lastPage}">
	    					<li><a class="next" href="${url}page=${pagedHostView.navInfo.currentPage +1}">Next</a></li>
						</c:if>     
    				</c:if>
    			</ul>
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