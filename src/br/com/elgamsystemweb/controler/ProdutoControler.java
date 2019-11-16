package br.com.elgamsystemweb.controler;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elgamsystemweb.model.Produto;
import br.com.elgamsystemweb.modelDAO.ProdutoDAO;


@WebServlet("/formularioproduto.do")
public class ProdutoControler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProdutoControler() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tipoProduto = request.getParameter("txtTipoProduto");
		String valorUnitario = request.getParameter("txtValorUnitario");
		String descricao = request.getParameter("txtDescricao");
		
		if(tipoProduto.equals("") || tipoProduto.length() > 20 || valorUnitario.equals("") || valorUnitario.length() > 10 ||
				Integer.parseInt(valorUnitario) > 20 || descricao.equals("") || descricao.length() > 50){
			
			String retorno = "falha";
			request.setAttribute("retorno", retorno);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			Produto produto = new Produto();
			produto.setTipoProduto(tipoProduto);
			produto.setValorUnitario(new BigDecimal(valorUnitario));
			produto.setDescricao(descricao);
			
			ProdutoDAO produtoDAO = new ProdutoDAO();
			String retorno = produtoDAO.persistenciaSalvarProduto(produto);
			request.setAttribute("retorno", retorno);
	
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
