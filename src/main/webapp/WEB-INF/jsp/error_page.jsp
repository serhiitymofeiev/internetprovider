<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<!doctype html>
<html>
<c:set var="title" value="Страница входа" scope="page"/>
<jsp:include page="/WEB-INF/templates/head.jsp"></jsp:include>
<body>
<jsp:include page="/WEB-INF/templates/menu.jsp"></jsp:include>
<div class="container">
    <div class="row  mt-5">
        <div class="col">
            <div class="d-flex justify-content-center">
                <img src="img/error_page.jpg" style="width: 100%">
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/templates/scripts.jsp"></jsp:include>
</body>
</html>