package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Atendimento {
  private int id;
  private LocalDateTime dataHora;
  private BigDecimal valorCobrado;
  private String observacao;
  private Animal animal;
  private Servico servico;
  private List<Adicional> adicionals;
  private Status status;
  private Petshop petshop;

  public Atendimento(LocalDateTime dataHora, BigDecimal valorCobrado, String observacao, Animal animal, Servico servico, List<Adicional> adicionals, Petshop petshop) {
    this.dataHora = dataHora;
    this.valorCobrado = valorCobrado;
    this.observacao = observacao;
    this.animal = animal;
    this.servico = servico;
    this.adicionals = adicionals;
    this.petshop = petshop;
    this.status = Status.AGENDADO;
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

  public Status getStatus() {
    return status;
  }

  public Petshop getPetshop() {
    return petshop;
  }

  public List<Adicional> getAdicionals() {
    return adicionals;
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

  public void iniciar(){
    if(status != Status.AGENDADO){
      System.out.println("Erro. É necessário o atendimento estar agendado para iniciar o atendimento.");
    } else{
      System.out.println("Iniciando atendimento...");

      status = Status.EM_ANDAMENTO;
      System.out.println("Status alterado para EM_ANDAMENTO");
    }
  }

  public void concluir(){
    if(status != Status.EM_ANDAMENTO){
      System.out.println("Erro. O Atendimento precisa estar em andamento para ser finalizado.");
    }
    else{
      status = Status.CONCLUIDO;
      System.out.println("Atendimento finalizado.");
    }
  }

  public void cancelar(){
    if(status != Status.AGENDADO){
      System.out.println("Erro. O atendimento precisa estar agendado para ser cancelado.");
    }
    else{
      status = Status.CANCELADO;
      System.out.println("Atendimento cancelado.");
    }
  }
}
