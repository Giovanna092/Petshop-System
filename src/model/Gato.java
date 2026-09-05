package model;

import java.time.LocalDate;

public class Gato extends Animal{
  private final String raca;

  public Gato(String nome, LocalDate nascimento, float peso, String genero, String raca) {
    super(nome, nascimento, peso, genero);
    this.raca = raca;
  }

  public String getRaca() {
    return raca;
  }
}
