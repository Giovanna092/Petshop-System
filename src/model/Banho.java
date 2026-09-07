package model;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Banho extends Servico{
  private Map<Adicional, BigDecimal> adicionais = new HashMap<>();

  public Banho(String nome, Duration duracao, BigDecimal valor, String descricao, Map<Adicional, BigDecimal> adicionais) {
    super(nome, duracao, valor, descricao);
    this.adicionais = adicionais;
  }

  public Map<Adicional, BigDecimal> getAdicionais() {
    return adicionais;
  }

  public void adicionaAdicional(Adicional adicional, BigDecimal valor){
    adicionais.put(adicional, valor);
  }
  public void adicionaAdicionais(Map<Adicional, BigDecimal> adicionais){
    this.adicionais.putAll(adicionais);
  }

  public BigDecimal obterValorAdicional(Adicional adicional){
    return adicionais.get(adicional);
  }

}
