package model;

import java.math.BigDecimal;
import java.time.Duration;

public class Tosa extends Servico{
  private final String tipoTosa;

  public Tosa(String nome, Duration duracao, BigDecimal valor, String descricao, String tipoTosa) {
    super(nome, duracao, valor, descricao);
    this.tipoTosa = tipoTosa;
  }

  public String getTipoTosa() {
    return tipoTosa;
  }

}
