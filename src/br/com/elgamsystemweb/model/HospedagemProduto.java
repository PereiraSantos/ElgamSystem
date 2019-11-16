package br.com.elgamsystemweb.model;

import java.math.BigDecimal;

public class HospedagemProduto {
	
	private int hospedagemProdutoId;
	private int hospedagemId;
	private int produtoId;
	private int quantidadeConsumo;
	private BigDecimal totalConsumo;
	private BigDecimal totalConsumoQuarto;
	
	
	public HospedagemProduto(){
		
	}
	
	public void calcularConsumoProduto(){
		this.totalConsumo = this.totalConsumo.multiply(new BigDecimal(this.quantidadeConsumo));
	}
	
	public int getHospedagemProdutoId() {
		return hospedagemProdutoId;
	}
	public void setHospedagemProdutoId(int hospedagemProdutoId) {
		this.hospedagemProdutoId = hospedagemProdutoId;
	}
	public int getHospedagemId() {
		return hospedagemId;
	}
	public void setHospedagemId(int hospedagemId) {
		this.hospedagemId = hospedagemId;
	}
	public int getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(int produtoId) {
		this.produtoId = produtoId;
	}
	public int getQuantidadeConsumo() {
		return quantidadeConsumo;
	}
	public void setQuantidadeConsumo(int quantidadeConsumo) {
		this.quantidadeConsumo = quantidadeConsumo;
	}
	public BigDecimal getTotalConsumo() {
		return totalConsumo;
	}
	public void setTotalConsumo(BigDecimal totalConsumo) {
		this.totalConsumo = totalConsumo;
	}

	public BigDecimal getTotalConsumoQuarto() {
		return totalConsumoQuarto;
	}

	public void setTotalConsumoQuarto(BigDecimal totalConsumoQuarto) {
		this.totalConsumoQuarto = totalConsumoQuarto;
	}
	
}
