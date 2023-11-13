<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Owner
  Date: 13/11/2023
  Time: 9:28 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6">
            <table class="table">
                <thead>
                <tr>
                    <th>Mã Lớp</th>
                    <th>Tên Lớp</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${list}" var="st">
                    <tr>
                        <td>${st.id}</td>
                        <td>${st.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
