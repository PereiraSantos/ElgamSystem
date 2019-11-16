<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="icon" href="img/favicon.ico" type="image/x-icon" sizes="16x16">
<title>Caixa</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<style>
	#containe{
		width:100%;
		height:700px;
		background:white;
		float:left;
	}
	#container_a{
		width:40%;
		height:650px;
		background:white;
		margin-right:auto;
		margin-left: auto;
	}
	h1{
		text-align:center;
		color:#808080;
		font-size:25px;
	}
	span{
		font-size:20px;
		color:#808080;
		margin-left:20px;
		font-weight: bold;
	}
	table{
		width:95%;
		border:1px solid silver;
		margin-left:05px
	}
	tr{
		border:1px solid silver;
		color:#666666;
		
	}
	td{
		font-size:20px;
		border:1px solid silver;
	}
	a{
		margin-left:10px;
	
	}
	label{
		margin-left:20px;
		font-size:20px;
		color:#808080;
	}
	input{
		width:40%;
		margin-right:10px;
		float:right;
		font-size:16px;
	}
	.btn{
		font-size:20px;
	}
</style>
</head>
<body>

 <div id="containe">
 	<div id="container_a">
	
		<h1>Caixa</h1>
		
			<label>Hospedagem ID</label>
				<input type="text" value="${requestScope.hospedagem3.hospedagemId}" readonly="readonly"/><br><br>
			<label>CPF</label>
				<input type="text" value="${requestScope.pessoa.cpf}" readonly="readonly"/><br><br>
			<label>Nome completo</label>
				<input type="text" value="${requestScope.pessoa.nomeCompleto}" readonly="readonly"/><br><br>
			<label>Número quarto</label>
				<input type="text" value="${requestScope.hospedagem3.quartoId}" readonly="readonly"/><br><br>
			
			<label>Data  e hora check-in</label>
				<input type="text" value="${requestScope.hospedagem3.checkIn} | ${requestScope.hospedagem3.horaCheckIn}" readonly="readonly"/><br><br>
			
			<label>Data e hora check-out</label>
				<input type="text" value="${requestScope.hospedagem3.checkOut} | ${requestScope.hospedagem3.horaCheckOut}" readonly="readonly"/><br><br>
			
			<label>Período </label>
			<input type="text" value="${requestScope.periodo} dias" readonly="readonly"/><br><br>
	 		
	 			<label>Total diárias </label>
	 			<input type="text" value="R$ ${requestScope.hospedagem3.valorTotal}" readonly="readonly"/><br><br>
	 			
	 		
	 			<label>Total consumo quarto</label>
	 			<input type="text" value="R$ ${requestScope.hp.totalConsumoQuarto}" readonly="readonly"/><br><br>
	 			
	 		
	 			<label>Total a pagar</label>
	 			<input type="text" value="R$ ${requestScope.hp.totalConsumoQuarto + requestScope.hospedagem3.valorTotal}" readonly="readonly"/><br><br>
	 			
	
 		<span>Forma de pagamento</span> 
 		<a href="#"><button class="btn btn-primary"  data-toggle="modal" data-target="#myModalDinheiro">Dinheiro</button></a>
 		<a href="#"><button class="btn btn-primary" data-toggle="modal" data-target="#myModalCredito">Crédito</button></a>
 		<a href="#"><button class="btn btn-primary" data-toggle="modal" data-target="#myModalDebito">Débito</button></a>
 	
 		
  	</div>
 </div>
 	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModalDinheiro" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Forma de pagamento</h4>
					</div>
					<div class="modal-body">
						<div id="container_mH">
							<h1>Dinheiro </h1>
							<h1>Desconto 10%</h1>
							<h1>R$ ${(requestScope.hp.totalConsumoQuarto + requestScope.hospedagem3.valorTotal) -
							(10 *(requestScope.hp.totalConsumoQuarto + requestScope.hospedagem3.valorTotal) / 100)}</h1>
							<h1><a href="index.jsp"><button class="btn btn-success">Finalizar hospedagem</button></a></h1>
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
		<div class="modal fade" id="myModalCredito" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Forma de pagamento</h4>
					</div>
					<div class="modal-body">
						<div id="container_mH">
							<h1>Crédito</h1>
							<h1>Pagamento em 6 vezes</h1>
							<h1>R$ ${(requestScope.hp.totalConsumoQuarto + requestScope.hospedagem3.valorTotal) / 6}</h1>
							<h1><a href="index.jsp"><button class="btn btn-success">Finalizar hospedagem</button></a></h1>
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
		<div class="modal fade" id="myModalDebito" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Forma pagamento</h4>
					</div>
					<div class="modal-body">
						<div id="container_mH">
							<h1>Débito</h1>
							<h1>Desconto 0%</h1>
							<h1>R$ ${requestScope.hp.totalConsumoQuarto + requestScope.hospedagem3.valorTotal}</h1>
							<h1><a href="index.jsp"><button class="btn btn-success">Finalizar hospedagem</button></a></h1>
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