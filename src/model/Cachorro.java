package model;

import java.time.LocalDate;

public class Cachorro extends Animal{
  private final String raca;
  private final String porte;

  public Cachorro(String nome, LocalDate nascimento, float peso, String genero, String raca, String porte) {
    super(nome, nascimento, peso, genero);
    this.raca = raca;
    this.porte = porte;
  }

  public String getRaca() {
    return raca;
  }

  public String getPorte() {
    return porte;
  }

  @Override
  public String toString() {
    return "Detalhes do cachorro: "+ getNome() + ", " + getIdade() + ", " + getRaca() + ", " + getGenero();
  }
}
