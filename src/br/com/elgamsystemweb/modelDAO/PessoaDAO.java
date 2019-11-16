package br.com.elgamsystemweb.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.elgamsystemweb.model.Pessoa;

public class PessoaDAO {
	private Connection conexao = Conexao.getConnection();
	
	public String persistenciaSalvarPessoa(Pessoa pessoa){
		String retorno = "";
		
		String sql= "INSERT INTO PESSOA(cpf, nomecompleto, endereco, telefone, email)VALUES(?,?,?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, pessoa.getCpf());
			preparador.setString(2, pessoa.getNomeCompleto());
			preparador.setString(3, pessoa.getEndereco());
			preparador.setString(4, pessoa.getTelefone());
			preparador.setString(5, pessoa.getEmail());
			preparador.execute();
			
			
			retorno = "sucesso";
			
			} catch (SQLException e) {
				retorno  = "falha";
				e.printStackTrace();
				
			}finally{
				if (conexao != null) {
					try { conexao.close(); } catch (SQLException e) {}
				}	  	  
			}
		return retorno;
	}
	public void persistenciaAlterarPessoa(Pessoa pessoa){
		String sql = "UPDATE CLIENTE SET nomeCompleto=?, endereco=?, telefone=?, email=? WHERE cpf=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, pessoa.getNomeCompleto());
			preparador.setString(2, pessoa.getEndereco());
			preparador.setString(3, pessoa.getTelefone());
			preparador.setString(4, pessoa.getEmail());
			preparador.setInt(5, pessoa.getCpf());
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
	public void persistenciaDesativarPessoa(Pessoa pessoa){
		String sql = "DELETE FROM CLIENTE WHERE pessoaid=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, pessoa.getPessoaId());
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
	
	public Pessoa buscarPessoaId(Integer cpf){
		String sql = "SELECT * FROM PESSOA WHERE cpf=?";
		Pessoa  pessoa = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, cpf);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				pessoa = new Pessoa();
				pessoa.setPessoaId(resultado.getInt("pessoaid"));
				pessoa.setCpf(resultado.getInt("cpf"));
				pessoa.setNomeCompleto(resultado.getString("nomecompleto"));
				pessoa.setEndereco(resultado.getString("endereco"));
				pessoa.setTelefone(resultado.getString("telefone"));
				pessoa.setEmail(resultado.getString("email"));
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return pessoa;
	}
	public Pessoa buscarPessoa(Integer pessoaid){
		String sql = "SELECT * FROM PESSOA WHERE pessoaid=?";
		Pessoa  pessoa = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, pessoaid);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				pessoa = new Pessoa();
				pessoa.setPessoaId(resultado.getInt("pessoaid"));
				pessoa.setCpf(resultado.getInt("cpf"));
				pessoa.setNomeCompleto(resultado.getString("nomecompleto"));
				pessoa.setEndereco(resultado.getString("endereco"));
				pessoa.setTelefone(resultado.getString("telefone"));
				pessoa.setEmail(resultado.getString("email"));
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return pessoa;
	}
	public List<Pessoa> listarTodosCliente(){
		String sql = "SELECT * FROM PESSOA";
		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Pessoa pessoa = new Pessoa();
				pessoa.setPessoaId(resultado.getInt("pessoaid"));
				pessoa.setCpf(resultado.getInt("cpf"));
				pessoa.setNomeCompleto(resultado.getString("nomecompleto"));
				pessoa.setEndereco(resultado.getString("endereco"));
				pessoa.setTelefone(resultado.getString("telefone"));
				pessoa.setEmail(resultado.getString("email"));
				
				listaPessoas.add(pessoa);
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return listaPessoas;
	}
	
}
