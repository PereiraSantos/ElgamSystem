package br.com.elgamsystemweb.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.elgamsystem.convertedata.ConverteData;
import br.com.elgamsystemweb.model.Hospedagem;

public class HospedagemDAO {
	private Connection conexao = Conexao.getConnection();
	private ConverteData converteData = new ConverteData();
	
	public String persistenciaSalvarHospedagem(Hospedagem hospedagem){
		
		String retorno  = "";
		String sql= "INSERT INTO HOSPEDAGEM(quartoid, pessoaid, checkin, checout, horacheckin, horacheckout, valortotal, periodo)VALUES(?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, hospedagem.getQuartoId());
			preparador.setInt(2, hospedagem.getPessoaId());
			preparador.setDate(3, converteData.converteLocalDateToSqlDate(hospedagem.getCheckIn()));
			preparador.setDate(4, converteData.converteLocalDateToSqlDate(hospedagem.getCheckOut()));
			preparador.setTime(5, converteData.converteLocalDateToSqlTime(hospedagem.getHoraCheckIn()));
			preparador.setTime(6, converteData.converteLocalDateToSqlTime(hospedagem.getHoraCheckOut()));
			preparador.setBigDecimal(7, hospedagem.getValorTotal());
			preparador.setString(8, String.valueOf(hospedagem.getPeriodo()));
			preparador.execute();
			
			retorno  =  "sucesso";
			
			} catch (SQLException e) {
				retorno = "falha";
				e.printStackTrace();
			}finally{
				if (conexao != null) {
					try { conexao.close(); } catch (SQLException e) {}
				}	  	  
			}
		
		if(retorno.equals("sucesso")){
			AltearaDisponibilidadeQuartoF(hospedagem);
		}
		
		return retorno;
	}
	public void AltearaDisponibilidadeQuartoF(Hospedagem hospedagem){
		QuartoDAO quartoDAO = new QuartoDAO();
		boolean disponivel = false;
		int quartoid = hospedagem.getQuartoId();
		quartoDAO.persistenciaAlterarQuarto(quartoid, disponivel);
		
	}
	
	public void persistenciaAlterarHospedagem(Hospedagem hospedagem){
		
		String sql = "UPDATE HOSPEDAGEM SET checout=?, horacheckout=?, valortotal=? WHERE pessoaid=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setDate(1, converteData.converteLocalDateToSqlDate(hospedagem.getCheckOut()));
			preparador.setTime(2, converteData.converteLocalDateToSqlTime(hospedagem.getHoraCheckOut()));
			preparador.setBigDecimal(3, hospedagem.getValorTotal());
			preparador.setInt(4, hospedagem.getPessoaId());
			preparador.execute();
			
			System.out.println("atualizado com sucesso");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR!!! \n"+ e.getMessage());
				e.printStackTrace();
		}finally{
			if (conexao != null) {
				try { conexao.close(); } catch (SQLException e) {}
			}
		}
		AltearaDisponibilidadeQuartoT(hospedagem);
	}
	public void AltearaDisponibilidadeQuartoT(Hospedagem hospedagem){
		QuartoDAO quartoDAO = new QuartoDAO();
		boolean disponivel = true;
		int quartoid = hospedagem.getQuartoId();
		quartoDAO.persistenciaAlterarQuarto(quartoid, disponivel);
		
	}
	public void persistenciaAlterarHospedagemValor(Hospedagem hospedagem){
		String sql = "UPDATE HOSPEDAGEM SET valortotal=?, periodo=? WHERE pessoaid=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setBigDecimal(1, hospedagem.getValorTotal());
			preparador.setString(2, String.valueOf(hospedagem.getPeriodo()));
			preparador.setInt(3, hospedagem.getPessoaId());
			preparador.execute();
			
			System.out.println("atualizado com sucesso");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO ALTERAR!!! \n"+ e.getMessage());
				e.printStackTrace();
		}finally{
			if (conexao != null) {
				try { conexao.close(); } catch (SQLException e) {}
			}
		}
		AltearaDisponibilidadeQuartoT(hospedagem);
	}
	public void persistenciaDesativarHospedagem(Hospedagem hospedagem){
		String sql = "DELETE FROM HOSPEDAGEMPRODUTO WHERE hospedagemid=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, hospedagem.getHospedagemId());
			preparador.execute();
			
			System.out.println("excluido com sucesso");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if (conexao != null) {
				try { conexao.close(); } catch (SQLException e) {}
			}
		}
	}
	
	public Hospedagem buscarHospedagemId(Integer pessoaid){
		String sql = "SELECT * FROM HOSPEDAGEM WHERE pessoaid=?";
		Hospedagem hospedagem = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, pessoaid);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				hospedagem = new Hospedagem();
				hospedagem.setHospedagemId(resultado.getInt("hospedagemid"));
				hospedagem.setQuartoId(resultado.getInt("quartoid"));
				hospedagem.setPessoaId(resultado.getInt("pessoaid"));
				hospedagem.setCheckIn(converteData.converteSqlDateToLocalDate(resultado.getDate("checkin")));
				hospedagem.setCheckOut(converteData.converteSqlDateToLocalDate(resultado.getDate("checout")));
				hospedagem.setHoraCheckIn(converteData.converteSqlDateToLocalTime(resultado.getTime("horacheckin")));
				hospedagem.setHoraCheckOut(converteData.converteSqlDateToLocalTime(resultado.getTime("horacheckout")));
				hospedagem.setValorTotal(resultado.getBigDecimal("valortotal"));
				hospedagem.setPeriodo(Period.parse(resultado.getString("periodo")));
				
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return hospedagem;
	}
	
	public Hospedagem buscarHospedagemQuarto(Integer quartoid){
		String sql = "SELECT * FROM HOSPEDAGEM WHERE quartoid=?";
		Hospedagem hospedagem = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, quartoid);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				hospedagem = new Hospedagem();
				hospedagem.setHospedagemId(resultado.getInt("hospedagemid"));
				hospedagem.setQuartoId(resultado.getInt("quartoid"));
				hospedagem.setPessoaId(resultado.getInt("pessoaid"));
				hospedagem.setCheckIn(converteData.converteSqlDateToLocalDate(resultado.getDate("checkin")));
				hospedagem.setCheckOut(converteData.converteSqlDateToLocalDate(resultado.getDate("checout")));
				hospedagem.setHoraCheckIn(converteData.converteSqlDateToLocalTime(resultado.getTime("horacheckin")));
				hospedagem.setHoraCheckOut(converteData.converteSqlDateToLocalTime(resultado.getTime("horacheckout")));
				hospedagem.setValorTotal(resultado.getBigDecimal("valortotal"));
				hospedagem.setPeriodo(Period.parse(resultado.getString("periodo")));
				
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return hospedagem;
	}

	public List<Hospedagem> listarTodosHospedagem(){
		String sql = "SELECT * FROM HOSPEDAGEM ORDER BY hospedagemid ASC";
		List<Hospedagem> listaHospedagem = new ArrayList<Hospedagem>();
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Hospedagem hospedagem = new Hospedagem();
				hospedagem.setHospedagemId(resultado.getInt("hospedagemid"));
				hospedagem.setQuartoId(resultado.getInt("quartoid"));
				hospedagem.setPessoaId(resultado.getInt("pessoaid"));
				hospedagem.setCheckIn(converteData.converteSqlDateToLocalDate(resultado.getDate("checkin")));
				hospedagem.setCheckOut(converteData.converteSqlDateToLocalDate(resultado.getDate("checout")));
				hospedagem.setHoraCheckIn(converteData.converteSqlDateToLocalTime(resultado.getTime("horacheckin")));
				hospedagem.setHoraCheckOut(converteData.converteSqlDateToLocalTime(resultado.getTime("horacheckout")));
				hospedagem.setValorTotal(resultado.getBigDecimal("valortotal"));
				hospedagem.setPeriodo(Period.parse(resultado.getString("periodo")));
				
				listaHospedagem.add(hospedagem);
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return listaHospedagem;
	}
	
	public Hospedagem buscarHospedagemQuartoId(Integer quartoid){
		//select * from hospedagem where quartoid  =4 ORDER BY hospedagemid ASC 
		String sql = "SELECT * FROM HOSPEDAGEM WHERE quartoid=? ORDER BY hospedagemid ASC";
		Hospedagem hospedagem = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, quartoid);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				hospedagem = new Hospedagem();
				hospedagem.setHospedagemId(resultado.getInt("hospedagemid"));
				hospedagem.setQuartoId(resultado.getInt("quartoid"));
				hospedagem.setPessoaId(resultado.getInt("pessoaid"));
				hospedagem.setCheckIn(converteData.converteSqlDateToLocalDate(resultado.getDate("checkin")));
				hospedagem.setCheckOut(converteData.converteSqlDateToLocalDate(resultado.getDate("checout")));
				hospedagem.setHoraCheckIn(converteData.converteSqlDateToLocalTime(resultado.getTime("horacheckin")));
				hospedagem.setHoraCheckOut(converteData.converteSqlDateToLocalTime(resultado.getTime("horacheckout")));
				hospedagem.setValorTotal(resultado.getBigDecimal("valortotal"));
				hospedagem.setPeriodo(Period.parse(resultado.getString("periodo")));
				
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return hospedagem;
	}
}
