package br.com.elgamsystemweb.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class Hospedagem {
	
	private int hospedagemId;
	private int quartoId;
	private int pessoaId;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private LocalTime horaCheckIn;
	private LocalTime horaCheckOut;
	private BigDecimal valorTotal;
	private Period periodo;
	
	public Hospedagem(){
		this.valorTotal = new BigDecimal(0);
	}
	public void calculaDias(){
		this.periodo = Period.between(this.getCheckIn(), this.checkOut);
		
		String periodoString = String.valueOf(this.getPeriodo());
		this.valorTotal = this.valorTotal.multiply(new BigDecimal(Integer.parseInt(periodoString.replaceAll("[^0-9]", ""))));

	}
	public void teste(){
		
	}
	
	public int getHospedagemId() {
		return hospedagemId;
	}
	public void setHospedagemId(int hospedagemId) {
		this.hospedagemId = hospedagemId;
	}
	public int getQuartoId() {
		return quartoId;
	}
	public void setQuartoId(int quartoId) {
		this.quartoId = quartoId;
	}
	public int getPessoaId() {
		return pessoaId;
	}
	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}
	public LocalDate getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	public LocalTime getHoraCheckIn() {
		return horaCheckIn;
	}
	public void setHoraCheckIn(LocalTime horaCheckIn) {
		this.horaCheckIn = horaCheckIn;
	}
	public LocalTime getHoraCheckOut() {
		return horaCheckOut;
	}
	public void setHoraCheckOut(LocalTime horaCheckOut) {
		this.horaCheckOut = horaCheckOut;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Period getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Period periodo) {
		this.periodo = periodo;
	}
}