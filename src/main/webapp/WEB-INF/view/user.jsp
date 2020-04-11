<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>User Page</title>
    <title>Main Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@600&display=swap" rel="stylesheet">
    <link type="text/css" 
         rel="stylesheet" 
         href="${pageContext.request.contextPath}/resources/css/style.css" />
  </head>
  <body>
    <div class="header">

      <h1>Welcome User Page</h1>

    </div>

    <div class="menuBar">
    
        <!-- add a new user -->
        <input class="search" type="button" value="Add Customer"
               onClick="window.location.href='userForm';
                         return false;"/>
        <br><br>                 
        
        <!-- search any user created -->                 
       <form:form class="search" action="searchUser" method="GET">
            
               Search User: <input type="text" name="searchUname" class="search"/>
                
                <input type="submit" value="Search" class="search"/>
           
      </form:form>                   
    
    </div>

    <div class="content">
    
         <!--show all users created and allow to update and delete  -->
         <form:form class="userList">
             <table>
                 <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>ID Number</th>
                    <th>Address</th>
                    <th>Phone Number</th>
                    <th>Email</th>
                 </tr>
                 
                 <c:forEach var="userList" items="${users}">
                 
                    <c:url var="updateLink" value="/user/updateUser">
                         <c:param name="userId" value="${userList.id}"/> 
                    </c:url>
                    
                    <c:url var="deleteLink" value="/user/deleteUser">
                         <c:param name="id" value="${userList.id}"/> 
                    </c:url>
                    
                 <tr>
                    <td>${userList.firstName}</td>
                    <td>${userList.lastName}</td>
                    <td>${userList.idNumber}</td>
                    <td>${userList.address}</td>
                    <td>${userList.phoneNumber}</td>
                    <td>${userList.email}</td>
                    
                    <td><a href="${updateLink}">Update</a></td>
                    
                    <td>
                    <a href="${deleteLink}" onClick="if (!(confirm
                        ('Are you want to delete this customer?')))
                        return false">Delete</a>
                    </td>
                    
                 </tr>   
               </c:forEach>
             </table>
         </form:form>

    </div>
    

  </body>
</html>