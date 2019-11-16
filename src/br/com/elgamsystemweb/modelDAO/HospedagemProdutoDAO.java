package br.com.elgamsystemweb.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.elgamsystemweb.model.HospedagemProduto;

public class HospedagemProdutoDAO {
	private Connection conexao = Conexao.getConnection();
	
	public void persistenciaSalvarHospedagemProduto(HospedagemProduto hospedagemProduto){
		String sql= "INSERT INTO HOSPEDAGEMPRODUTO(hospedagemid, produtoid, quantidadeconsumo, totalconsumo)VALUES(?,?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, hospedagemProduto.getHospedagemId());
			preparador.setInt(2, hospedagemProduto.getProdutoId());
			preparador.setInt(3, hospedagemProduto.getQuantidadeConsumo());
			preparador.setBigDecimal(4, hospedagemProduto.getTotalConsumo());

			preparador.execute();
			
			
			//JOptionPane.showMessageDialog(null, "CADASTRO REALIZADO COM SUCESSO.");
			
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "ERRO AO CADASTRAR!!! \n"+ e.getMessage());
				e.printStackTrace();
			}finally{
				if (conexao != null) {
					try { conexao.close(); } catch (SQLException e) {}
				}	  	  
			}
	}
	public void persistenciaAlterarHospedagemProduto(HospedagemProduto hospedagemProduto){
		String sql = "UPDATE HOSPEDAGEMPRODUTO SET consumoId=? WHERE hospedagemprodutoid=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, hospedagemProduto.getHospedagemId());
			preparador.setInt(2, hospedagemProduto.getProdutoId());
			preparador.setInt(3, hospedagemProduto.getHospedagemProdutoId());
			preparador.setInt(4, hospedagemProduto.getQuantidadeConsumo());
			preparador.setBigDecimal(5, hospedagemProduto.getTotalConsumo());
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
	public void persistenciaDesativarHospedagemProduto(HospedagemProduto hospedagemProduto){
		String sql = "DELETE FROM HOSPEDAGEMPRODUTO WHERE produtoid=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, hospedagemProduto.getHospedagemProdutoId());
			preparador.execute();
			
			//System.out.println("excluido com sucesso");
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO EXCLUIR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if (conexao != null) {
				try { conexao.close(); } catch (SQLException e) {}
			}
		}
	}
	
	public HospedagemProduto buscarHospedagemProdutoId(Integer hospedagemProdutoid){
		String sql = "SELECT FROM HOSPEDAGEMPRODUTO WHERE hospedagemprodutoid=?";
		HospedagemProduto hospedagemProduto = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, hospedagemProdutoid);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				hospedagemProduto = new HospedagemProduto();
				hospedagemProduto.setHospedagemProdutoId(resultado.getInt("hospedagemprodutoid"));
				hospedagemProduto.setHospedagemId(resultado.getInt("hospedagemid"));
				hospedagemProduto.setProdutoId(resultado.getInt("produtoid"));
				hospedagemProduto.setProdutoId(resultado.getInt("quantidadeconsumo"));
				hospedagemProduto.setTotalConsumo(resultado.getBigDecimal("totalconsumo"));
				
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return hospedagemProduto;
	}
	
	public List<HospedagemProduto> listarTodosHospedagemProduto(){
		String sql = "SELECT * FROM HOSPEDAGEMPRODUTO";
		List<HospedagemProduto> listaHospedagemProdutos = new ArrayList<HospedagemProduto>();
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				HospedagemProduto hospedagemProduto = new HospedagemProduto();
				hospedagemProduto.setHospedagemProdutoId(resultado.getInt("hospedagemprodutoid"));
				hospedagemProduto.setHospedagemId(resultado.getInt("hospedagemid"));
				hospedagemProduto.setProdutoId(resultado.getInt("produtoid"));
				hospedagemProduto.setProdutoId(resultado.getInt("quantidadeconsumo"));
				hospedagemProduto.setTotalConsumo(resultado.getBigDecimal("totalconsumo"));
				
				listaHospedagemProdutos.add(hospedagemProduto);
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return listaHospedagemProdutos;
	}


	public List<HospedagemProduto> listarTodosHospedagemProdutoId(Integer hospedagemid){
		String sql = "SELECT * FROM HOSPEDAGEMPRODUTO WHERE hospedagemid=?";
		List<HospedagemProduto> listaHospedagemProdutos = new ArrayList<HospedagemProduto>();
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, hospedagemid);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				HospedagemProduto produto = new HospedagemProduto();
				produto.setHospedagemProdutoId(resultado.getInt("hospedagemprodutoid"));
				produto.setHospedagemId(resultado.getInt("hospedagemid"));
				produto.setProdutoId(resultado.getInt("produtoid"));
				produto.setQuantidadeConsumo(resultado.getInt("quantidadeconsumo"));
				produto.setTotalConsumo(resultado.getBigDecimal("totalconsumo"));
				produto.setTotalConsumoQuarto(resultado.getBigDecimal("totalconsumo"));
			
				listaHospedagemProdutos.add(produto);
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return listaHospedagemProdutos;
	}
}