package br.com.elgamsystemweb.controler;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elgamsystemweb.model.Hospedagem;
import br.com.elgamsystemweb.model.Pessoa;
import br.com.elgamsystemweb.model.Produto;
import br.com.elgamsystemweb.model.Quarto;
import br.com.elgamsystemweb.modelDAO.HospedagemDAO;
import br.com.elgamsystemweb.modelDAO.PessoaDAO;
import br.com.elgamsystemweb.modelDAO.ProdutoDAO;
import br.com.elgamsystemweb.modelDAO.QuartoDAO;


@WebServlet("/listaquarto.do")
public class QuartoControler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
   
    public QuartoControler() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		String retorno = request.getParameter("retorno");
		
		if(retorno.equals("")){
			retorno = "sucesso";
		}
		
		if(acao.equals("listarcarros")){
			
			QuartoDAO  quartoDAO = new QuartoDAO();
			List<Quarto> listaquartos = quartoDAO.listarTodosQuarto();
			request.setAttribute("listaquartos", listaquartos);
			
			
			
			HospedagemDAO hospedagemDAO = new HospedagemDAO();
			List<Hospedagem>listaH = hospedagemDAO.listarTodosHospedagem();
			request.setAttribute("listaH", listaH);
			

			PessoaDAO pessoaDAO = new PessoaDAO();
			List<Pessoa> listaPessoa = pessoaDAO.listarTodosCliente();
			request.setAttribute("listaPessoa", listaPessoa);
			
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			List<Produto> listaProduto  = produtoDAO.listarTodosProduto();
			request.setAttribute("listaProduto", listaProduto);
			
			request.setAttribute("retorno", retorno);
			
			request.getRequestDispatcher("listaquarto.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String numeroQuarto  = request.getParameter("txtNumeroQuarto");
		String tipoQuarto = request.getParameter("txtTipoQuarto");
		String descricao = request.getParameter("txtDescricao");
		String valorDiaria = request.getParameter("txtValorDiaria");
		
		if(numeroQuarto.equals("") || numeroQuarto.length() > 10 || tipoQuarto.length() > 50 || tipoQuarto.equals("") ||
				descricao.length() > 50 || descricao.equals("") || valorDiaria.length() > 10 || valorDiaria.equals("") ||
				Integer.parseInt(valorDiaria) > 500){
			
			String retorno = "falha";
			request.setAttribute("retorno", retorno);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
		
			Quarto quarto = new Quarto();
			quarto.setNumeroQuarto(Integer.parseInt(numeroQuarto));
			quarto.setTipoQuarto(tipoQuarto);
			quarto.setDescricao(descricao);
			quarto.setValorDiaria(new BigDecimal(valorDiaria));
			
			QuartoDAO  quartoDAO = new QuartoDAO();
			String retorno = quartoDAO.persistenciaSalvarQuarto(quarto);
			
			request.setAttribute("retorno", retorno);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}
	
	
	

}
