package br.com.elgamsystemweb.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elgamsystemweb.model.Hospedagem;
import br.com.elgamsystemweb.model.HospedagemProduto;
import br.com.elgamsystemweb.modelDAO.HospedagemDAO;

@WebServlet("/caixa.do")
public class CaixaControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public CaixaControler() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String id = request.getParameter("id");
	
		if(acao.equals("lista")){
			HospedagemDAO hospedagemDAO = new HospedagemDAO();
			Hospedagem hospedagem = hospedagemDAO.buscarHospedagemId(Integer.parseInt(id));
			request.setAttribute("hospedagem", hospedagem);
			
			HospedagemProduto hp = new HospedagemProduto();
			hp.getTotalConsumoQuarto();
			request.setAttribute("hp", hp);
			
			request.getRequestDispatcher("caixa.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
