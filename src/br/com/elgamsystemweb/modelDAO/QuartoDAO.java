package br.com.elgamsystemweb.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.elgamsystemweb.model.Quarto;

public class QuartoDAO {
	private Connection conexao = Conexao.getConnection();
	
	public String persistenciaSalvarQuarto(Quarto quarto){
		
		String retorno  = "";
		String sql = "INSERT INTO QUARTO(numeroquarto, tipoquarto, descricao, disponivel, valordiaria)VALUES(?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, quarto.getNumeroQuarto());
			preparador.setString(2, quarto.getTipoQuarto());
			preparador.setString(3, quarto.getDescricao());
			preparador.setBoolean(4, quarto.isDisponivel());
			preparador.setBigDecimal(5, quarto.getValorDiaria());
			preparador.execute();
			
			retorno = "sucesso";
			} catch (SQLException e) {
			retorno = "falha";
			e.printStackTrace();
		}finally{
			if (conexao != null) {
				try { conexao.close(); } catch (SQLException e) {}
			}	  	  
		}
		return retorno;
	}
	
	public void persistenciaAlterarQuarto(int quartoid, boolean disponivel){
		String sql = "UPDATE QUARTO SET disponivel=? WHERE quartoid=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setBoolean(1, disponivel);
			preparador.setInt(2, quartoid);
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
	}
	public void persistenciaDesativarQuarto(Quarto quarto){
		String sql = "DELETE * FROM QUARTO WHERE quartoid=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, quarto.getQuartoId());
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
	
	public Quarto buscarQuartoId(int quartoid){
		String sql = "SELECT * FROM QUARTO WHERE quartoid=?";
		Quarto quarto = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, quartoid);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				quarto =  new Quarto();
				quarto.setQuartoId(resultado.getInt("quartoid"));
				quarto.setNumeroQuarto(resultado.getInt("numeroquarto"));
				quarto.setTipoQuarto(resultado.getString("tipoquarto"));
				quarto.setDescricao(resultado.getString("descricao"));
				quarto.setDisponivel(resultado.getBoolean("disponivel"));
				quarto.setValorDiaria(resultado.getBigDecimal("valordiaria"));
				
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return quarto;
	}
	
	public List<Quarto> listarTodosQuarto(){
		String sql = "SELECT * FROM QUARTO ORDER BY quartoid ASC";
		List<Quarto> listaQuartos= new ArrayList<Quarto>();
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Quarto quarto =  new Quarto();
				quarto.setQuartoId(resultado.getInt("quartoid"));
				quarto.setNumeroQuarto(resultado.getInt("numeroquarto"));
				quarto.setTipoQuarto(resultado.getString("tipoquarto"));
				quarto.setDescricao(resultado.getString("descricao"));
				quarto.setDisponivel(resultado.getBoolean("disponivel"));
				quarto.setValorDiaria(resultado.getBigDecimal("valordiaria"));
				listaQuartos.add(quarto);
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return listaQuartos;
	}
}
///SELECT numeroquarto FROM quarto ORDER BY numeroquarto DESC LIMIT 1