<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>hello</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<body>
<%@include file="hello2.jsp"%>
<button type="button" class="btn btn-primary">Primary</button>
<h1>
    <c:forEach items="${ingredients}" var="item">
    <p><c:out value="${item.description}"/></p>
    </c:forEach>
    ${ingredient.description}
</h1>
<h2>jnjnjn</h2>
</body>
</html>