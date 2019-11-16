package br.com.elgamsystemweb.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elgamsystemweb.model.Pessoa;
import br.com.elgamsystemweb.modelDAO.PessoaDAO;


@WebServlet("/formulariocliente.do")
public class PessoaControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public PessoaControler() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cpf =  request.getParameter("txtCpf");
		String nomeCompleto = request.getParameter("txtNomeCompleto");
		String endereco = request.getParameter("txtEndereco");
		String telefone = request.getParameter("txtTelefone");
		String email = request.getParameter("txtEmail");
		
		if(cpf.equals("") || cpf.length() > 12 || nomeCompleto.equals("") || nomeCompleto.length() > 50 || 
				endereco.equals("") || endereco.length() > 50 || telefone.equals("") || telefone.length() > 20 ||
				email.equals("") || email.length() > 50){
			
			String retorno = "falha";
			request.setAttribute("retorno", retorno);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			Pessoa pessoa = new Pessoa();
			pessoa.setCpf(Integer.parseInt(cpf));
			pessoa.setNomeCompleto(nomeCompleto);
			pessoa.setEndereco(endereco);
			pessoa.setTelefone(telefone);
			pessoa.setEmail(email);
			
			PessoaDAO pessoaDAO = new PessoaDAO();
			String retorno = pessoaDAO.persistenciaSalvarPessoa(pessoa);
			request.setAttribute("retorno", retorno);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
