package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Petshop {
  private int id;
  private String nome;
  private String endereco;
  private String telefone;
  private final List<Servico> servicos  = new ArrayList<>();

  public Petshop(String nome, String endereco, String telefone) {
    this.nome = nome;
    this.endereco = endereco;
    this.telefone = telefone;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public List<Servico> getServicos() {
    return servicos;
  }

  public void adicionaServico(Servico servico){
    this.servicos.add(servico);
  }

  public void listarServicos(){
    servicos.forEach(System.out::println);
  }

  public boolean possuiServico(Servico servico) {
    for (Servico value : servicos) {
      if (value.equals(servico)) {
        return true;
      }
    }
    return false;
  }

  public boolean possuiAdicional(Adicional adicional, Servico servico){
    if(servico instanceof Banho){
      Map<Adicional, BigDecimal> adicionaisPetshop = ((Banho) servico).getAdicionais();
      if(adicionaisPetshop.isEmpty()){
        System.out.println("Petshop não possui adicionais");
      } else{
        if(adicionaisPetshop.containsKey(adicional)){
          return true;
        }
      }
    }
    return false;
  }


  public BigDecimal obterValorAdicionais(Adicional adicional, Servico servico) {
    if(servico instanceof Banho banho){
      return banho.getAdicionais().get(adicional);
    }
    return BigDecimal.ZERO;
  }
}
