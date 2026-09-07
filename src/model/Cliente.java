package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
  private int id;
  private String nome;
  private String telefone;
  private String email;
  private final List<Animal> animais = new ArrayList<>();

  public Cliente(String nome, String telefone, String email) {
    this.nome = nome;
    this.telefone = telefone;
    this.email = email;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Animal> getAnimais() {
    return animais;
  }

  public void adicionaAnimal(Animal animal){
    this.animais.add(animal);
    animal.setTutor(this);
  }

  public void listarAnimais(){
    animais.forEach(System.out::println);
  }

  public boolean possuiAnimal(Animal animal) {
    for (Animal a : animais){
      if(a.equals(animal)){
        return true;
      }
    }
    return false;
  }
}
