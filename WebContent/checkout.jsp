<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="icon" href="img/favicon.ico" type="image/x-icon" sizes="16x16">
<title>Check-in</title>


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


<style>
	#containe{
		width:40%;
		height:500px;
		background:white;
		float:left;
		margin-top:10px;
		margin-left: 05px;
		border-left:1px solid silver;
	
	
	}
	#container1{
		width:15%;
		height:600px;
		background:white;
		float:left;
		margin-top:20px;
		margin-left: 10px;
	}
	#container_a{
		width:90%;
		height:600px;
		background:white;
		margin-left:auto;
		margin-right:auto;	
		
	}
	#container_produto{
		width:40%;
		height:600px;
		background:white;
		float:left;
		margin-top:10px;
		margin-left: 05px;
	}
	#container_busca{
		width:80%;
		height:150px;
		background:white;
		float:left;
		margin-top:20px;
		margin-left: 05px;
		overflow: auto;
	}

	#form{
		background:white;
		width:90%;
		height:320px;
		margin-left:auto;
		margin-right:auto;
	}
	#formChe{
		background:white;
		width:90%;
		height:250px;
		margin-left:auto;
		margin-right:auto;
		
	}
	#formB{
		background:white;
		width:90%;
		margin-left:auto;
		margin-right:auto;
		
	}

	h2{
		font-size:25px;
		color:#666666;
		text-align:center;
	}
	label{
		font-size:20px;
		color:#808080;
		
		
	}
	input{
		width:40%;
		float:right;
		font-size:16px;
		margin-right:30px;
		
	}
	#busca{
		width:100%;
		float:left;
		
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
		font-size:15px;
		margin-left:05px;
		color:white;
	}
	h3{
	float:right;	
	margin-right:10px;
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
	table{
		width:100%;
		border:1px solid silver;
	}
	tr{
		border:1px solid silver;
	}
	th{
		text-align: center;
		border:1px solid silver;
	}
	td{
		text-align: center;
		border:1px solid silver;
	}
	#invi{
		background:white;
		color:white;
		border: 1px solid white;
	}
	.btn{
		font-size:20px;
	}
	span{
		color:#ff6666;
		font-size:25px;
	}
</style>
</head>
<body>
	<div id="container1">
		<div class="vertical-menu">
		 	<a href="index.jsp" class="active">Home</a>	
		</div>
		<br>
		 	<form id="formB"  action="consumoproduto.do" method="GET">
			<input id="busca" type="number" name="txtCpf" value="" placeholder="Digite o CPF" required/><br><br>
			<input id="busca" class="btn btn-primary"  type="submit" value="Busca CPF">
		</form>	 
	</div>
	<div id="container_busca">
		<table>
			<tr>
				<th>Produto ID</th>
				<th>Tipo produto</th>
				<th>Valor unitário </th>
				<th>Descrição produto</th>
						
			</tr>
			<c:forEach items="${requestScope.listaProduto}" var="listaP">
			<tr>
				<td>${listaP.produtoId} </td>
				<td>${listaP.tipoProduto} </td>
				<td>R$ ${listaP.valorUnitario} </td>
				<td>${listaP.descricao}</td>
								
			</tr>
			</c:forEach>
			
	</table>
	</div>
	<div id="container_produto">
	
	<div id="container_a">
		<h2>Consumo quarto</h2>
			<form id="form" action="consumoproduto.do" method="POST">
				<label>CPF:</label>
				<input type="text" name="txtCpf" value="${requestScope.pessoa.cpf}" readonly="readonly"/><br><br>
				<label>Pessoa ID:</label>
				<input type="number" name="txtPessoaid" value="${requestScope.hospedagem3.pessoaId}"  readonly="readonly"/><br><br>
				<label>Hóspede:</label>
				<input type="text" name="txtNome" value="${requestScope.pessoa.nomeCompleto}"  readonly="readonly"/><br><br>	
				<label>Produto ID:</label>
				<input  type="number" name="txtProdutoId" value="" required/><br><br>
				<label>Qt consumo:</label>
				<input type="number" name="txtTotalConsumo" value=""  required/><br><br>
				<input class="btn btn-success" type="submit" value="Gravar">
				<input id="invi" type="text" name="txtHospedagemId" value="${requestScope.hospedagem3.hospedagemId}" readonly="readonly"/>
			</form>
		<br>
		<c:if test="${requestScope.hp.totalConsumoQuarto == 0}">
		<table>
			<tr>
				<th>Hospedagem </th>
				<th>Produto </th>
				<th>Quantidade </th>
				<th>Consumo</th>
		
			</tr>
			<c:forEach items="${requestScope.ListaConsumo}" var="lista">
			<tr>
				<td>${lista.hospedagemId} </td>
				<td>${lista.produtoId} </td>
				<td>${lista.quantidadeConsumo} </td>
				<td>R$: ${lista.totalConsumo}</td>
				
			</tr>
			</c:forEach>
			
		</table>
		</c:if>
		<c:if test="${requestScope.hp.totalConsumoQuarto > 0}">
				<table>
			<tr>
				<th>Hospedagem </th>
				<th>Produto </th>
				<th>Quantidade </th>
				<th>Consumo</th>
		
			</tr>
			<c:forEach items="${requestScope.ListaConsumo}" var="lista">
			<tr>
				<td>${lista.hospedagemId} </td>
				<td>${lista.produtoId} </td>
				<td>${lista.quantidadeConsumo} </td>
				<td>R$ ${lista.totalConsumo}</td>
				
			</tr>
			</c:forEach>
			
		</table>
			<h3>Valor total R$ <span>${requestScope.hp.totalConsumoQuarto}</span></h3>
		</c:if>
		

	</div>
	</div>
	<div id="containe">
		<div id="container_a">
			<h2>Check-out</h2>
			<form id="formChe" action="checkout.do" method="POST">
				<label>Hospedagem ID:</label>
				<input  type="number" name="txtHospedagemId" value="${requestScope.hospedagem3.hospedagemId}" readonly="readonly"/><br><br>
				<label>Quarto ID: </label>
				<input type="number" name="txtCodigoQuarto" value="${requestScope.hospedagem3.quartoId}" readonly="readonly" /><br><br>
				<label>Check-in:</label>
				<input  type="text" name="txtCheckin"  value="${requestScope.hospedagem3.checkIn}"  readonly="readonly" /><br><br>
				<label>Valor diária:</label>
				<input  type="number" name="txtValorDiaria"  value="${requestScope.quarto.valorDiaria}"  readonly="readonly" /><br><br>
				<label>Data check-out:</label>
				<input type="date" name="txtCheckout" value="${requestScope.datacheckout}" required/><br><br>
				<input class="btn btn-success" type="submit" value="Check-out"/>
				<input id="invi" type="text" name="txtPessoaId" value="${requestScope.hospedagem3.pessoaId}" readonly="readonly" />
			</form>

		</div>
	</div>
</body>
</html>