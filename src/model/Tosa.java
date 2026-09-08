package model;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

public class Tosa extends ServicoComAdicionais{
  private final String tipoTosa;

  public Tosa(String nome, Duration duracao, BigDecimal valor, String descricao, Map<Adicional, BigDecimal> adicionais, String tipoTosa) {
    super(nome, duracao, valor, descricao, adicionais);
    this.tipoTosa = tipoTosa;
  }

  public String getTipoTosa() {
    return tipoTosa;
  }

}
