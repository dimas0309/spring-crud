<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>User Form</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@600&display=swap" rel="stylesheet">
    <link type="text/css" 
         rel="stylesheet" 
         href="${pageContext.request.contextPath}/resources/css/style.css" />
  </head>
  <body>
     
    <div class="header">

      <h3>User Form</h3>

    </div>

    <div class="menuBar">
    
    <!-- back to user web page -->
    <a href="${pageContext.request.contextPath}/user/userPage">Back</a>

    </div>

    <div class="content">
    
        <!-- show a form to create a new user -->
        <form:form class="userForm" action="saveUser" modelAttribute="user"
                   method="post">
              <form:hidden path="id" />
              
              <table>
                  <tbody>
                      <tr>
                         <td><label>First Name</label></td>
                         <td><form:input path="firstName"/></td>
                      </tr>
                   
                      <tr>
                         <td><label>Last Name</label></td>
                         <td><form:input path="lastName"/></td>
                      </tr>
                      
                      <tr>
                         <td><label>ID Number</label></td>
                         <td><form:input path="idNumber"/></td>
                      </tr>
                      
                      <tr>
                         <td><label>Address</label></td>
                         <td><form:input path="address"/></td>
                      </tr>
                      
                      <tr>
                         <td><label>Phone Number</label></td>
                         <td><form:input path="phoneNumber"/></td>
                      </tr>
                      
                      <tr>
                         <td><label>Email</label></td>
                         <td><form:input path="email"/></td>
                      </tr>
                      
                       <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Save" class="save"/>
                            </td>
                       </tr>
                    
                  </tbody>
              </table>     
        </form:form>

    </div>
    

  </body>
</html>
