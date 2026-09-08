package model;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

public class Banho extends ServicoComAdicionais{

  public Banho(String nome, Duration duracao, BigDecimal valor, String descricao, Map<Adicional, BigDecimal> adicionais) {
    super(nome, duracao, valor, descricao, adicionais);
  }

}
