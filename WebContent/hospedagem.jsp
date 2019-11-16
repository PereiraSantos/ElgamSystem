<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="icon" href="img/favicon.ico" type="image/x-icon" sizes="16x16">
<title>Hospedagem</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<style>
	#container{
		width:80%;
		height:600px;
		background:white;
		float:left;
		margin-top:10px;
		margin-left: 5px;
	
	
	}
	#container1 {
		width: 15%;
		height: 600px;
		background: white;
		float: left;
		margin-top: 20px;
		margin-left: 10px;
	
}
	#container_a{
		width:40%;
		height:600px;
		background:white;
		margin-left:auto;
		margin-right:auto;	
	}

	#form{
		background:white;
		width:70%;
		margin-left:auto;
		margin-right:auto;
	}
	#formB{
		background:white;
		width:70%;
		margin-left:auto;
		margin-right:auto;
	}
	h2{
		font-size:30px;
		color:#595959;
		text-align:center;
	}
	label{
		font-size:20px;
		color:#808080;
	}
	input{
		width:95%;
		font-size:16px;
		
	}
	.sub{
		width:40%;
		height:40px;
		margin-left:30%;
		color:white;
		background:#49d049;
		border-radius:10%;
	
	}

	h1{
		font-size:16px;
		margin-left:05px;
		color:white;
	}
	#formulario{
		float:right;
	}
	.aa{
		margin-left: 25%;
    	
	}
	.vertical-menu {
	  width: 200px;
	}
	
	.vertical-menu a {
	  background-color: #eee;
	  color: black;
	  display: block;
	  padding: 12px;
	  text-decoration: none;
	  font-size:20px;
	}
	
	.vertical-menu a:hover {
	  background-color: #ccc;
	}
	
	.vertical-menu a.active {
	  background-color: #4CAF50;
	  color: white;
	}
	#invi{
		background:white;
		color:white;
		border: 1px solid white;
	}
	.btn{
		font-size:20px;
	}
	hr{
		width:40%;
		border-color:green;
	}
</style>
</head>
<body>
	<div id="container1">
		<div class="vertical-menu">
		 	<a href="index.jsp" class="active">Home</a>
		
		</div>
	</div>
	<div id="container">
		<div id="container_a">
		
		<form id="formB"  action="hospedagem.do" method="GET">
			<input id="busca" type="number" name="txtCpf" value="" placeholder="Digite o CPF" required />
			<input id="invi" type="text"  name="id" value="${requestScope.quarto.quartoId}" readonly="readonly" />
			<input id="busca" class="btn btn-success"  type="submit" value="Busca por CPF"/>
		</form>
		<br>
		
		<hr>
			<form id="form" action="hospedagem.do" method="POST">
			
				<label>Número quarto: </label><br>
				<input type="number" name="txtQuarto" value="${requestScope.quarto.quartoId}" readonly="readonly"/><br><br>
				
				<label>Pessoa ID: </label><br>
				<input type="number" name="txtPessoaId" value="${requestScope.pessoa.pessoaId}" readonly="readonly"/><br><br>
				
				<label>Nome completo: </label><br>
				<input type="text" name="txtPessoaId" value="${requestScope.pessoa.nomeCompleto}" readonly="readonly"/><br><br>
				
				<label>Hora check-in:</label><br>
				<input type="date" name="txtDataCheckin" value="${requestScope.datacheckout}"  required/><br><br>

				
				<input class="btn btn-success" type="submit" value="Check-in"/>
			</form>
		</div>
	</div>
</body>
</html>