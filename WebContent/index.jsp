<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="icon" href="img/favicon.ico" type="image/x-icon" sizes="16x16">
<title>Carregando...</title>
</head>
<body>
	<script>
		window.location.href = "listaquarto.do?acao=listarcarros&retorno=${requestScope.retorno}";
	</script>
	
</body>
</html>