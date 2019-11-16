package br.com.elgamsystemweb.controler;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elgamsystem.convertedata.ConverteData;
import br.com.elgamsystemweb.model.Hospedagem;
import br.com.elgamsystemweb.model.HospedagemProduto;
import br.com.elgamsystemweb.model.Pessoa;
import br.com.elgamsystemweb.modelDAO.HospedagemDAO;
import br.com.elgamsystemweb.modelDAO.HospedagemProdutoDAO;
import br.com.elgamsystemweb.modelDAO.PessoaDAO;

@WebServlet("/checkout.do")
public class CheckOutControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConverteData converteData = new ConverteData();
    public CheckOutControler() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codigoQuarto = request.getParameter("txtCodigoQuarto");
		String pessoaId = request.getParameter("txtPessoaId");
		String dataCheckout  =  request.getParameter("txtCheckout");
		String valorQuarto = request.getParameter("txtValorDiaria");
		
		if(codigoQuarto.equals("") || codigoQuarto.length() > 10 || pessoaId.equals("") || pessoaId.length() > 10 ||
				dataCheckout.equals("") || dataCheckout.length() > 20 || valorQuarto.equals("") || valorQuarto.length() > 10){
			
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
		}else{
			BigDecimal valor= new BigDecimal(0);
			LocalTime horacheout = LocalTime.now();
			
			Hospedagem hospedagem = new Hospedagem();
			hospedagem.setQuartoId(Integer.parseInt(codigoQuarto));
			hospedagem.setPessoaId(Integer.parseInt(pessoaId));
			hospedagem.setValorTotal(new BigDecimal(valorQuarto));
			hospedagem.setCheckOut(converteData.converteStringToLocalDate(dataCheckout));
			hospedagem.setHoraCheckOut(horacheout);
			
			HospedagemDAO hospedagemDAO = new HospedagemDAO();
			hospedagemDAO.persistenciaAlterarHospedagem(hospedagem);
			
			HospedagemDAO hospedagemDAO1 = new HospedagemDAO();
			Hospedagem hospedagem1 = hospedagemDAO1.buscarHospedagemId(Integer.parseInt(pessoaId));
			int hospedagem2 = hospedagem1.getHospedagemId();
			int pessoaco  = hospedagem1.getPessoaId();
			hospedagem1.calculaDias();
			
			HospedagemDAO hospedagemDAO2 = new HospedagemDAO();
			hospedagemDAO2.persistenciaAlterarHospedagemValor(hospedagem1);
			
			HospedagemDAO hospedagemDAO3 = new HospedagemDAO();
			Hospedagem hospedagem3 = hospedagemDAO3.buscarHospedagemId(Integer.parseInt(pessoaId));
			String periodoString = String.valueOf(hospedagem3.getPeriodo());
			int periodo  =  Integer.parseInt(periodoString.replaceAll("[^0-9]", ""));
			request.setAttribute("periodo", periodo);
			request.setAttribute("hospedagem3", hospedagem3);
			
			HospedagemProdutoDAO hospedagemProdutoDAO4 = new HospedagemProdutoDAO();
			List<HospedagemProduto>ListaConsumo = hospedagemProdutoDAO4.listarTodosHospedagemProdutoId(hospedagem2);
			request.setAttribute("ListaConsumo", ListaConsumo);
			for (HospedagemProduto hos: ListaConsumo) {
				valor = valor.add(hos.getTotalConsumo());
			}
			
			HospedagemProduto hp = new HospedagemProduto();
			hp.setTotalConsumoQuarto(valor);
			request.setAttribute("hp", hp);
			
			PessoaDAO pessoaDAO =  new PessoaDAO();
			Pessoa pessoa =  pessoaDAO.buscarPessoa(pessoaco);
			request.setAttribute("pessoa", pessoa);
			
			
			request.getRequestDispatcher("caixa.jsp").forward(request, response);
		}
	}

}
