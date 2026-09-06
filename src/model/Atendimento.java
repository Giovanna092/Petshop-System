package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Atendimento {
  private int id;
  private LocalDateTime dataHora;
  private BigDecimal valorCobrado;
  private String observacao;
  private Animal animal;
  private Servico servico;
  private boolean hidratacao;
  private boolean corteUnha;
  private String status;

  public Atendimento(LocalDateTime dataHora, BigDecimal valorCobrado, String observacao, Animal animal, Servico servico, boolean hidratacao, boolean corteUnha) {
    this.dataHora = dataHora;
    this.valorCobrado = valorCobrado;
    this.observacao = observacao;
    this.animal = animal;
    this.servico = servico;
    this.hidratacao = hidratacao;
    this.corteUnha = corteUnha;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  public BigDecimal getValorCobrado() {
    return valorCobrado;
  }

  public String getObservacao() {
    return observacao;
  }

  public Animal getAnimal() {
    return animal;
  }

  public Servico getServico() {
    return servico;
  }

  public boolean isHidratacao() {
    return hidratacao;
  }

  public boolean isCorteUnha() {
    return corteUnha;
  }

  public String getStatus() {
    return status;
  }

  public void setDataHora(LocalDateTime dataHora) {
    this.dataHora = dataHora;
  }

  public void setValorCobrado(BigDecimal valorCobrado) {
    this.valorCobrado = valorCobrado;
  }

  public void setObservacao(String observacao) {
    this.observacao = observacao;
  }

  public void setAnimal(Animal animal) {
    this.animal = animal;
  }

  public void setServico(Servico servico) {
    this.servico = servico;
  }

  public void setHidratacao(boolean hidratacao) {
    this.hidratacao = hidratacao;
  }

  public void setCorteUnha(boolean corteUnha) {
    this.corteUnha = corteUnha;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void iniciar(){
    if(!status.equalsIgnoreCase("AGENDADO")){
      System.out.println("Erro. É necessário o atendimento estar agendado para iniciar o atendimento.");
    } else{
      System.out.println("Iniciando atendimento...");

      this.setStatus("EM_PROGRESSO");
      System.out.println("Status alterado para EM_PROGRESSO");
    }
  }
}
