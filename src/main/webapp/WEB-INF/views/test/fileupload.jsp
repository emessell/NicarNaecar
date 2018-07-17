<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css">
<script src="/webjars/jquery/3.3.1/dist/jquery.js"></script>
<script src="/webjars/bootstrap/4.1.1/js/bootstrap.js"></script>
<script src="/webjars/popper.js/1.14.1/umd/popper.js"></script>
<title>Insert title here</title>
</head>
<body>

<form action="/test/fileupload?${_csrf.parameterName}=${_csrf.token}" method= "post" enctype="multipart/form-data">
	<input type="file" name="file" />
	<button type="submit">OK</button>
</form>

</body>
</html>