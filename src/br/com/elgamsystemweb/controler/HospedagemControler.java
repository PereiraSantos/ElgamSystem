package br.com.elgamsystemweb.controler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.elgamsystem.convertedata.ConverteData;
import br.com.elgamsystemweb.model.Hospedagem;
import br.com.elgamsystemweb.model.Pessoa;
import br.com.elgamsystemweb.model.Quarto;
import br.com.elgamsystemweb.modelDAO.HospedagemDAO;
import br.com.elgamsystemweb.modelDAO.PessoaDAO;
import br.com.elgamsystemweb.modelDAO.QuartoDAO;


@WebServlet("/hospedagem.do")
public class HospedagemControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ConverteData converteData = new ConverteData();
 
    public HospedagemControler() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codigoquarto = request.getParameter("id");
		String cpf = request.getParameter("txtCpf");
		
		if(codigoquarto.equals("") || codigoquarto.length() > 10 || cpf.equals("") || cpf.length() > 12){
			
			request.getRequestDispatcher("hospedagem.jsp").forward(request, response);
		}else{
			QuartoDAO quartoDAO = new QuartoDAO();
			Quarto quarto  = quartoDAO.buscarQuartoId((Integer.parseInt(codigoquarto)));
			request.setAttribute("quarto", quarto);
				
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.buscarPessoaId(Integer.parseInt(cpf));
			request.setAttribute("pessoa", pessoa);
			
			LocalDate datacheckout = LocalDate.now();
			request.setAttribute("datacheckout", datacheckout);
			
			request.getRequestDispatcher("hospedagem.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String codigoQuarto = request.getParameter("txtQuarto");
		String pessoaId =  request.getParameter("txtPessoaId");
		String datatDataCheckin = request.getParameter("txtDataCheckin");
		
		if(codigoQuarto.equals("") || codigoQuarto.length() > 20 || pessoaId.equals("") || pessoaId.length() > 50 ||
				datatDataCheckin.equals("") || datatDataCheckin.length() > 15){
			
			String retorno = "falha";
			request.setAttribute("retorno", retorno);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
		
			LocalDate datacheckout = LocalDate.now();
			LocalTime horacheckin = LocalTime.now();
			LocalTime horacheout = LocalTime.now();
			
			Hospedagem hospedagem = new Hospedagem();
			hospedagem.setQuartoId(Integer.parseInt(codigoQuarto));
			hospedagem.setPessoaId(Integer.parseInt(pessoaId));
			hospedagem.setCheckIn(converteData.converteStringToLocalDate(datatDataCheckin));
			hospedagem.setCheckOut(datacheckout);
			hospedagem.setHoraCheckIn(horacheckin);
			hospedagem.setHoraCheckOut(horacheout);
			hospedagem.calculaDias();
			
			HospedagemDAO hospedagemDAO = new HospedagemDAO();
			String retorno =  hospedagemDAO.persistenciaSalvarHospedagem(hospedagem);
			request.setAttribute("retorno", retorno);
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
