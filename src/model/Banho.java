package model;

import java.math.BigDecimal;
import java.time.Duration;

public class Banho extends Servico{


  public Banho(String nome, Duration duracao, BigDecimal valor, String descricao) {
    super(nome, duracao, valor, descricao);
  }

}
