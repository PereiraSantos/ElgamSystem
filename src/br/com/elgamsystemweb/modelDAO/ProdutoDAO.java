package br.com.elgamsystemweb.modelDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.elgamsystemweb.model.Produto;

public class ProdutoDAO {
	private Connection conexao = Conexao.getConnection();
	
	public String persistenciaSalvarProduto(Produto produto){
		
		String retorno =  "";
		String sql= "INSERT INTO PRODUTO(tipoproduto, valorunitario, descricao)VALUES(?,?,?)";
		
		try {
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, produto.getTipoProduto());
			preparador.setBigDecimal(2, produto.getValorUnitario());
			preparador.setString(3, produto.getDescricao());
			preparador.execute();
			
			retorno = "sucesso";
			
			} catch (SQLException e) {
				retorno =  "falha";
				e.printStackTrace();
			}finally{
				if (conexao != null) {
					try { conexao.close(); } catch (SQLException e) {}
				}	  	  
			}
		return retorno;
	}
	public void persistenciaAlterarProduto(Produto produto){
		String sql = "UPDATE PRODUTO SET tipoproduto=?, valorunitario=?, descrica=? WHERE produtoid=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setString(1, produto.getTipoProduto());
			preparador.setBigDecimal(2, produto.getValorUnitario());
			preparador.setString(3, produto.getDescricao());
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
	public void persistenciaDesativarProduto(Produto produto){
		String sql = "DELETE FROM PRODUTO WHERE produtoid=?";
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, produto.getProdutoId());
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
	
	public Produto buscarProdutoId(Integer produtoid){
		String sql = "SELECT * FROM PRODUTO WHERE produtoid=?";
		Produto produto = null;
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			preparador.setInt(1, produtoid);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				produto = new Produto();
				produto.setProdutoId(resultado.getInt("produtoid"));
				produto.setTipoProduto(resultado.getString("tipoproduto"));
				produto.setValorUnitario(resultado.getBigDecimal("valorunitario"));
				produto.setDescricao(resultado.getString("descricao"));
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return produto;
	}
	
	public List<Produto> listarTodosProduto(){
		String sql = "SELECT * FROM PRODUTO";
		List<Produto> listaProdutos = new ArrayList<Produto>();
		
		try{
			PreparedStatement preparador = conexao.prepareStatement(sql);
			ResultSet resultado = preparador.executeQuery();
			while(resultado.next()){
				Produto produto = new Produto();
				produto.setProdutoId(resultado.getInt("produtoid"));
				produto.setTipoProduto(resultado.getString("tipoproduto"));
				produto.setValorUnitario(resultado.getBigDecimal("valorunitario"));
				produto.setDescricao(resultado.getString("descricao"));
				
				listaProdutos.add(produto);
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO AO BUSCAR!!! \n"+ e.getMessage());
			e.printStackTrace();
		}finally{
			if(conexao != null){
				try{conexao.close();}catch(SQLException e){}
			}
		}
		return listaProdutos;
	}
}
