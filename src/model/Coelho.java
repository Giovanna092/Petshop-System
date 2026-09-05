package model;

import java.time.LocalDate;

public class Coelho extends Animal{
  private final String raca;

  public Coelho(String nome, LocalDate nascimento, float peso, String genero, Cliente tutor, String raca) {
    super(nome, nascimento, peso, genero, tutor);
    this.raca = raca;
  }

  public String getRaca() {
    return raca;
  }
}
