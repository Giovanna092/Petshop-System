package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public abstract class Animal {
  private long id;
  private String nome;
  private final LocalDate nascimento;
  private float peso;
  private String genero;
  private Cliente tutor;

  public Animal(String nome, LocalDate nascimento, float peso, String genero) {
    this.nome = nome;
    this.nascimento = nascimento;
    this.peso = peso;
    this.genero = genero;
  }

  public Animal(String nome, LocalDate nascimento, float peso, String genero, Cliente tutor) {
    this.nome = nome;
    this.nascimento = nascimento;
    this.peso = peso;
    this.genero = genero;
    this.tutor = tutor;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public LocalDate getNascimento() {
    return nascimento;
  }

  public float getPeso() {
    return peso;
  }

  public String getGenero() {
    return genero;
  }

  public Cliente getTutor() {
    return tutor;
  }

  public void setTutor(Cliente tutor) {
    this.tutor = tutor;
  }

  public String getIdade(){
    return calcularIdade();
  }

  private String calcularIdade(){

    if(nascimento == null){
      return "Sem idade definida";
    }

    LocalDate dataAtual = LocalDate.now();

    long anos = ChronoUnit.YEARS.between(this.nascimento, dataAtual);

    // Se tiver menos de 1 ano, calcula e retorna em meses
    if (anos < 1) {
      long meses = ChronoUnit.MONTHS.between(this.nascimento, dataAtual);

      // Trata o caso de recém-nascido (menos de 1 mês)
      if (meses < 1) {
        long dias = ChronoUnit.DAYS.between(this.nascimento, dataAtual);
        return dias + (dias == 1 ? " dia" : " dias");
      }

      return meses + (meses == 1 ? " mês" : " meses");
    }

    return anos + (anos == 1 ? " ano" : " anos");
  }
}
