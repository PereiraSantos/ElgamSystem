<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="icon" href="img/favicon.ico" type="image/x-icon" sizes="16x16">
<title>Elgam System</title>

<style>
#containe {
	width: 80%;
	height: 1300px;
	background:white;
	float: left;
	margin-top: 10px;
	margin-left: 20px;
	
}

#container1 {
	width: 15%;
	height: 600px;
	background: white;
	float: left;
	margin-top: 20px;
	margin-left: 10px;
	
}

.container_a {
	width: 250px;
	height: 250px;
	background: #49d049;
	float: left;
	margin-left: 15px;
	margin-top:10px;
}

.container_b {
	width: 250px;
	height: 250px;
	background: #ff3333;
	float: left;
	margin-left: 15px;
	margin-top:10px;
}

#container_m {
	width: 99%;
	height: 300px;
	background: white;
	float: left;
	margin-left: 10px;
	margin-top: 18px;
}
#container_mQ {
	width: 99%;
	height: 400px;
	background: white;
	float: left;
	margin-left: 10px;
	margin-top: 18px;
}
#container_mP {
	width: 99%;
	height: 420px;
	background: white;
	float: left;
	margin-left: 10px;
	margin-top: 18px;
	overflow: auto;
}
#container_mH {
	width: 90%;
	height: 450px;
	background: white;
	float: left;
	margin-left: 10px;
	margin-top: 18px;
	overflow: auto;
}
#container_Pessoa {
	width: 100%;
	height: 450px;
	background: white;
	float: left;
	margin-left: 10px;
	margin-top: 18px;
	overflow: auto;
}
#form {
	background:white;
	width: 60%;
	margin-left: auto;
	margin-right: auto;
}
h1{
	font-size: 18px;
	margin-left: 10px;
	margin-top:0px;
	color: white;
	font-weight: bold;
}

h2 {
	font-size: 25px;
	color: silver;
	text-align: center;
}
h3{
	font-size: 40px;
	color: white;
	text-align: left;
	margin-top:0px;
	margin-left:10px;
}
h5{
	font-size: 23px;
	margin-left: 10px;
	color: white;
	margin-top:15px;
}

label {
	font-size: 20px;
	color: silver;
}

input {
	width: 99%;
	font-size: 18px;
}

.sub {
	width: 40%;
	height: 40px;
	margin-left: 30%;
	color: white;
	background: #49d049;
	border-radius: 10%;
}



#formulario {
	float: right;
}

.aa {
	margin-left: 25%;
	
}
.btn{
	margin-top: 20px;
	font-size:20px;

	
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
		border:1px solid silver;
	}
	td{
		text-align: center;
		border:1px solid silver;
	}
	#mesage{
		width:100%;
		height:30px;
		background:#ff9999;
	}
	h4{
		text-align:center;
		font-size:20px;
	}
</style>
</head>
<body>
	<c:if test="${requestScope.retorno == 'sucesso'}">
		
	</c:if>
	<c:if test="${requestScope.retorno == 'falha'}">
		<div  id="mesage">
		<h4>Falha ao salvar no banco</h4>
		<script>
		 	window.setTimeout("location.href='index.jsp'",3000);		 
		</script>
		</div>
	</c:if>
	<div id="container1">
		<div class="vertical-menu">
			<a href="index.jsp" class="active">Home</a>
			 <a href="#" data-toggle="modal" data-target="#myModalQuarto">Cadastro quarto</a> 
			 <a href="#" data-toggle="modal" data-target="#myModalPessoa">Cadastro hóspede</a> 
			 <a href="#" data-toggle="modal" data-target="#myModal">Cadastro produto</a> 
			 <a href="#" data-toggle="modal" data-target="#myModalListaHospedagem">Lista hospedagens</a> 
			 <a href="#" data-toggle="modal" data-target="#myModalListaPessoa">Lista hóspedes</a> 
			 <a href="#" data-toggle="modal" data-target="#myModalListaProduto">Lista produtos</a> 
			
		</div>
		
		<br><br>
		<img src="img/elgam.png" width="200px"  height="70px"/>
	</div>
	
	<div id="containe">
		<c:forEach items="${requestScope.listaquartos}" var="quarto">
			<c:if test="${quarto.disponivel == true}">
				<div class="container_a">
					<h3>${quarto.numeroQuarto}</h3>
					<hr>
					<h1>${quarto.tipoQuarto} com ${quarto.descricao}</h1>
					<h5>Diária R$ ${quarto.valorDiaria}</h5>
					
					<a class="aa"  href="hospedagem.do?acao=codigo&id=${quarto.quartoId}&txtCpf=0"><button
							class="btn btn-primary">Check-in</button></a>
							
				</div>
			</c:if>
			<c:if test="${quarto.disponivel == false}">
				<div class="container_b">
					<h3>${quarto.numeroQuarto}</h3>
					<hr>
					<h1>${quarto.tipoQuarto} com ${quarto.descricao}</h1>
					<h5>Diária R$ ${quarto.valorDiaria}</h5>
			
					<a class="aa" href="buscaquarto.do?acao=codigoproduto&id=${quarto.quartoId}"><button
					class="btn btn-light">Check-out</button></a>

				</div>
			</c:if>
		</c:forEach>
	</div>

	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Cadastro produto</h4>
					</div>
					<div class="modal-body">
						<div id="container_m">
							<form id="form" action="formularioproduto.do" method="POST">
								<label>Tipo produto:</label>
								<input type="text" name="txtTipoProduto"  required /><br>
								<label>Valor unitário:</label>
								<input type="number" name="txtValorUnitario"  required /><br>
								<label>Descrição:</label>
								<input type="text" name="txtDescricao" required /><br><br>
								<input class="btn btn-success" type="submit" value="Salvar" />
							</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					</div>
				</div>

			</div>
		</div>
	</div>

		<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModalQuarto" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Cadastro quarto</h4>
					</div>
					<div class="modal-body">
						<div id="container_mQ">
									<form id="form" action="listaquarto.do" method="POST">
										<label>Número quarto: </label>
										<input type="number" name="txtNumeroQuarto"  required /><br>
										
										<label>Tipo de quarto: </label>
										<input type="text" name="txtTipoQuarto"  required/><br>
										
										<label>Descrição: </label>
										<input type="text" name="txtDescricao"  required/><br>
										
										<label>Valor diária: </label>
										<input type="number" name="txtValorDiaria"  required/><br><br>
										
										<input class="btn btn-success" type="submit" value="Salvar"/>
									</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					</div>
				</div>

			</div>
		</div>
	</div>
	
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModalPessoa" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Cadastro hóspede</h4>
					</div>
					<div class="modal-body">
						<div id="container_mP">
								<form id="form" action="formulariocliente.do" method="POST">
									<label>CPF:</label>
									<input type="number" name="txtCpf" value=""  required/><br>
									
									<label>Nome:</label>
									<input type="text" name="txtNomeCompleto" value=""  required/><br>
									
									<label>Endereço:</label>
									<input type="text" name="txtEndereco" value=""  required/><br>
									
									<label>Telefone:</label>
									<input type="text" name="txtTelefone" value=""  required/><br>
									
									<label>E-mail:</label>
									<input type="text" name="txtEmail" value=""  required/><br><br>
									<input  class="btn btn-success" type="submit" value="Salvar"/>
								</form>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					</div>
				</div>

			</div>
		</div>
	</div>
	
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModalListaHospedagem" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Lista hospedagens</h4>
					</div>
					<div class="modal-body">
						<div id="container_mH">
							<table>
								<tr>
									<th>Hospedagem ID</th>
									<th>Quarto ID</th>
									<th>Pessoa ID </th>
									<th>Check-in</th>
						
								</tr>
							<c:forEach items="${requestScope.listaH}" var="listaH">
								<tr>
									<td>${listaH.hospedagemId} </td>
									<td>${listaH.quartoId} </td>
									<td>${listaH.pessoaId} </td>
									<td>${listaH.checkIn}</td>
								
							</tr>
							</c:forEach>
			
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					</div>
				</div>

			</div>
		</div>
	</div>
	
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModalListaPessoa" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Lista hóspedes</h4>
					</div>
					<div class="modal-body">
						<div id="container_Pessoa">
							<table>
								<tr>
									<th>ID</th>
									<th>CPF</th>
									<th>Nome completo </th>
									<th>Endereço</th>
									<th>Telefone</th>
									<th>E-mail</th>
						
								</tr>
							<c:forEach items="${requestScope.listaPessoa}" var="listaPe">
								<tr>
									<td>${listaPe.pessoaId} </td>
									<td>${listaPe.cpf} </td>
									<td>${listaPe.nomeCompleto} </td>
									<td>${listaPe.endereco}</td>
									<td>${listaPe.telefone} </td>
									<td>${listaPe.email}</td>
							</tr>
							</c:forEach>
			
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					</div>
				</div>

			</div>
		</div>
	</div>
	
	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModalListaProduto" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Lista produtos</h4>
					</div>
					<div class="modal-body">
						<div id="container_mH">
							<table>
								<tr>
									<th>Produto ID</th>
									<th>Tipo produto</th>
									<th>Valor unitário </th>
									<th>Descrição produto</th>
						
								</tr>
							<c:forEach items="${requestScope.listaProduto}" var="listaPro">
								<tr>
									<td>${listaPro.produtoId} </td>
									<td>${listaPro.tipoProduto} </td>
									<td>R$: ${listaPro.valorUnitario} </td>
									<td>${listaPro.descricao}</td>
								
							</tr>
							</c:forEach>
			
							</table>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>