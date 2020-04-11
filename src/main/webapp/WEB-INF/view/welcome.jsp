<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Main Page</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@600&display=swap" rel="stylesheet">
    <link type="text/css" 
         rel="stylesheet" 
         href="${pageContext.request.contextPath}/resources/css/style.css" />
  </head>
  <body>
     
    <div class="header">

      <h1>Welcome To My Site</h1>

    </div>

    <div class="menuBar">
    
    <a href="${pageContext.request.contextPath}/user/userPage">User Page</a>

    </div>

    <div class="content">

    </div>
    

  </body>
</html>
