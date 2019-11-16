package br.com.elgamsystemweb.controler;

import java.io.IOException;
import java.time.LocalDate;
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


@WebServlet("/buscaquarto.do")
public class BuscaQuartoControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public BuscaQuartoControler() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String acao = request.getParameter("acao");
    	String id = request.getParameter("id");
    	
    	if(acao.equals("") || acao.length() > 20 || id.equals("") || id.length() > 10){
    		
    		request.getRequestDispatcher("checkout.jsp").forward(request, response);
    	}else{
	    	if(acao.equals("codigoproduto")){    		
	    		QuartoDAO quartoDAO = new QuartoDAO();
	    		Quarto quarto = quartoDAO.buscarQuartoId(Integer.parseInt(id));
	    		request.setAttribute("quarto", quarto);
	    		
	    		HospedagemDAO hospedagemDAO = new HospedagemDAO();
	    		Hospedagem hospedagem3 = hospedagemDAO.buscarHospedagemQuartoId(Integer.parseInt(id));
	    	
	    		int pessoaId =  hospedagem3.getPessoaId();
	    		
	    		PessoaDAO pessoaDAO = new PessoaDAO();
	    		Pessoa pessoa = pessoaDAO.buscarPessoa(pessoaId);
	    		
	    		
	    		request.setAttribute("pessoa", pessoa);
	    		
	    		request.setAttribute("hospedagem3", hospedagem3);
	    		
	    		ProdutoDAO produtoDAO = new ProdutoDAO();
	    		List<Produto> listaProduto  = produtoDAO.listarTodosProduto();
	    		request.setAttribute("listaProduto", listaProduto);
	    		
	    		LocalDate datacheckout = LocalDate.now();
				request.setAttribute("datacheckout", datacheckout);
	    		
	    		request.getRequestDispatcher("checkout.jsp").forward(request, response);
	    	}	
    	}
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	}

}
