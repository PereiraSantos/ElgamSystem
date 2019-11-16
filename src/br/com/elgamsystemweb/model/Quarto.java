package br.com.elgamsystemweb.model;

import java.math.BigDecimal;

public class Quarto {
	
	private int quartoId;
	private int numeroQuarto;
	private String tipoQuarto;
	private String descricao;
	private boolean disponivel;
	private BigDecimal valorDiaria;
	
	public Quarto(){
		this.disponivel = true;
	}

	public int getQuartoId() {
		return quartoId;
	}
	public void setQuartoId(int quartoId) {
		this.quartoId = quartoId;
	}
	
	public int getNumeroQuarto() {
		return numeroQuarto;
	}

	public void setNumeroQuarto(int numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
	}

	public String getTipoQuarto() {
		return tipoQuarto;
	}
	public void setTipoQuarto(String tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public BigDecimal getValorDiaria() {
		return valorDiaria;
	}
	public void setValorDiaria(BigDecimal valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
}
