package br.com.elgamsystemweb.controler;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elgamsystemweb.model.Hospedagem;
import br.com.elgamsystemweb.model.HospedagemProduto;
import br.com.elgamsystemweb.model.Pessoa;
import br.com.elgamsystemweb.model.Produto;
import br.com.elgamsystemweb.model.Quarto;
import br.com.elgamsystemweb.modelDAO.HospedagemDAO;
import br.com.elgamsystemweb.modelDAO.HospedagemProdutoDAO;
import br.com.elgamsystemweb.modelDAO.PessoaDAO;
import br.com.elgamsystemweb.modelDAO.ProdutoDAO;
import br.com.elgamsystemweb.modelDAO.QuartoDAO;


@WebServlet("/consumoproduto.do")
public class HospedagemProdutoControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String copia;
    
    public HospedagemProdutoControler() {
        super();
    }

	
	
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cpf = request.getParameter("txtCpf");
		
		if(cpf.equals("") || cpf.length() > 12){
			
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
		}else{
		
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscarPessoaId(Integer.parseInt(cpf));
			request.setAttribute("pessoa", pessoa);
			
			if(pessoa == null){
				request.getRequestDispatcher("checkout.jsp").forward(request, response);
			}
			int pessoaId =  pessoa.getPessoaId();
			
			
			HospedagemDAO hospedagemDAO3 = new HospedagemDAO();
			Hospedagem hospedagem3 = hospedagemDAO3.buscarHospedagemId(pessoaId);
			int numero = hospedagem3.getQuartoId();
			request.setAttribute("hospedagem3", hospedagem3);
			
			QuartoDAO quartoDAO = new QuartoDAO();
			Quarto quarto = quartoDAO.buscarQuartoId(numero);
			request.setAttribute("quarto", quarto);
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			List<Produto> listaProduto  = produtoDAO.listarTodosProduto();
			request.setAttribute("listaProduto", listaProduto);
			
			LocalDate datacheckout = LocalDate.now();
			request.setAttribute("datacheckout", datacheckout);
						
			request.setAttribute("hospedagem3", hospedagem3);
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
		}
   }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pessoaId = request.getParameter("txtPessoaid");
		String hospedagemId = request.getParameter("txtHospedagemId");
		String produtoId = request.getParameter("txtProdutoId");
		String quantidadeConsumo = request.getParameter("txtTotalConsumo");
		String cpf = request.getParameter("txtCpf");
		
		if(pessoaId.equals("") || pessoaId.length() > 10 || hospedagemId.equals("") || hospedagemId.length() > 10 || 
				produtoId.equals("") || produtoId.length() > 10 || quantidadeConsumo.equals("") || quantidadeConsumo.length() > 3 ||
				Integer.parseInt(quantidadeConsumo) > 3 || cpf.equals("") || cpf.length() > 12){
			
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
		}else{
		
			BigDecimal valor= new BigDecimal(0);
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			Produto produto = produtoDAO.buscarProdutoId(Integer.parseInt(produtoId));
			if(produto == null){
				request.getRequestDispatcher("checkout.jsp").forward(request, response);
			}
			BigDecimal valorUnitario = produto.getValorUnitario();
			
			HospedagemProduto hospedagemProduto= new HospedagemProduto();
			hospedagemProduto.setHospedagemId(Integer.parseInt(hospedagemId));
			hospedagemProduto.setProdutoId(Integer.parseInt(produtoId));
			hospedagemProduto.setQuantidadeConsumo(Integer.parseInt(quantidadeConsumo));
			hospedagemProduto.setTotalConsumo(valorUnitario);
			hospedagemProduto.setTotalConsumoQuarto(new BigDecimal(0));
			
			hospedagemProduto.calcularConsumoProduto();
			
			HospedagemProdutoDAO hospedagemProdutoDAO = new HospedagemProdutoDAO();
			hospedagemProdutoDAO.persistenciaSalvarHospedagemProduto(hospedagemProduto);
			
			HospedagemDAO hospedagemDAO3 = new HospedagemDAO();
			Hospedagem hospedagem3 = hospedagemDAO3.buscarHospedagemId(Integer.parseInt(pessoaId));
			int numero = hospedagem3.getQuartoId();
			
			
			request.setAttribute("hospedagem3", hospedagem3);
			
			HospedagemProdutoDAO hospedagemProdutoDAO4 = new HospedagemProdutoDAO();
			List<HospedagemProduto>ListaConsumo = hospedagemProdutoDAO4.listarTodosHospedagemProdutoId(Integer.parseInt(hospedagemId));
			request.setAttribute("ListaConsumo", ListaConsumo);
			for (HospedagemProduto hos: ListaConsumo) {
				valor = valor.add(hos.getTotalConsumo());
			}
			
			HospedagemProduto hp = new HospedagemProduto();
			hp.setTotalConsumoQuarto(valor);
			request.setAttribute("hp", hp);
			
			
			QuartoDAO quartoDAO = new QuartoDAO();
			Quarto quarto = quartoDAO.buscarQuartoId(numero);
			request.setAttribute("quarto", quarto);
			
			ProdutoDAO produtoDAO1 = new ProdutoDAO();
			List<Produto> listaProduto  = produtoDAO1.listarTodosProduto();
			request.setAttribute("listaProduto", listaProduto);
			
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscarPessoaId(Integer.parseInt(cpf));
			request.setAttribute("pessoa", pessoa);
			
			LocalDate datacheckout = LocalDate.now();
			request.setAttribute("datacheckout", datacheckout);
			
			request.getRequestDispatcher("checkout.jsp").forward(request, response);
		}
	}

}
