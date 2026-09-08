package model;

import java.math.BigDecimal;
import java.time.Duration;

public abstract class Servico {
  private int id;
  private String nome;
  private Duration duracao;
  private BigDecimal valor;
  private String descricao;

  public Servico(String nome, Duration duracao, BigDecimal valor, String descricao) {
    this.nome = nome;
    this.duracao = duracao;
    this.valor = valor;
    this.descricao = descricao;
  }

  public String getNome() {
    return nome;
  }

  public Duration getDuracao() {
    return duracao;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public String getDescricao() {
    return descricao;
  }

  @Override
  public String toString() {
    return  "Serviço: " + nome +
            ", duracao: " + duracao.toMinutes() + " minutos" +
            ", valor: " + valor +
            ", descricao: " + descricao;
  }
}
