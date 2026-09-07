import model.*;
import service.AgendamentoService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public class Main {
  public static void main(String[] args) {

    //criação de entidades
    Animal cachorro = new Cachorro(
            "Rex",
            LocalDate.of(2025, 10, 6),
            15.5f,
            "Macho",
            "Labrador",
            "Grande");

    Animal gato = new Gato(
            "Mia",
            LocalDate.of(2025, 4,22),
            5,
            "Femêa",
            "Persa");

    Petshop petshop1 = new Petshop(
      "4ever pets",
      "R. Armandinho Soares, 123",
      "(11) 9 5231-2123");

    Servico banhoCao = new Banho("Banho padrão Cachorro",
            Duration.ofMinutes(15),
            new BigDecimal("25.90"),
            "Banho comum para cães.");

    Servico banhoGato = new Banho("Banho padrão Gato",
            Duration.ofMinutes(15),
            new BigDecimal("20.90"),
            "Banho comum para gatos.");

    Servico tosaTesoura = new Tosa(
            "Tosa tesoura completa",
            Duration.ofHours(1L),
            new BigDecimal("65.90"),
            "Tosa tesoura para cães.",
            "Tesoura");

    Servico tosaMaquina = new Tosa(
            "Tosa máquina completa",
            Duration.ofMinutes(40),
            new BigDecimal("39.90"),
            "Tosa máquina para cães.",
            "Máquina");

    Servico tosaHigienica = new Tosa(
            "Tosa higiênica completa",
            Duration.ofMinutes(20),
            new BigDecimal("49.90"),
            "Tosa higiênica para cães.",
            "Higiênica");

    Cliente giovanna = new Cliente(
            "Giovanna",
            "(11) 92364-2361",
            "giovanna@email.com.br");

    Animal cachorro3 = new Cachorro(
            "Bella",
            LocalDate.of(2018,4,29),
            3.5f,
            "Fêmea",
            "Yorkshire",
            "Pequeno");

    Cliente cliente = new Cliente(
            "João",
            "(11) 92323-2323",
            "joao@email.com.br");

    Cliente cliente2 = new Cliente(
            "Maria",
            "(11) 92323-2322",
            "maria@email.com.br");

    Animal cachorro2 = new Cachorro(
            "Thor",
            null,
            10f,
            "Macho",
            "Vira-lata",
            "Médio");

    Petshop petshop2 = new Petshop(
            "PetShop Vila Sol",
            "R. Vila Sol, 231 ",
            "(11) 98765-9876");

    //definindo relações
    petshop1.adicionaServico(banhoCao);
    petshop1.adicionaServico(banhoGato);
    petshop1.adicionaServico(tosaTesoura);
    petshop1.adicionaServico(tosaHigienica);
    petshop1.adicionaServico(tosaMaquina);

    cliente.adicionaAnimal(cachorro);
    cliente2.adicionaAnimal(cachorro2);
    giovanna.adicionaAnimal(cachorro3);

    //testando a historia
    System.out.println("------ INICIO HISTORIA 1 ------");
    historia(cliente, cachorro, petshop1, banhoCao);
    System.out.println("------ FIM HISTORIA 1 ------");

    System.out.println("------ INICIO HISTORIA 2 ------");
    historia(giovanna, cachorro, petshop1, banhoCao);
    System.out.println("------ FIM HISTORIA 2 ------");

    System.out.println("------ INICIO HISTORIA 2 ------");
    historia(cliente, cachorro, petshop2, banhoCao);
    System.out.println("------ FIM HISTORIA 2 ------");
  }

  private static void historia(Cliente cliente, Animal animal, Petshop petshop, Servico servico) {
    //cliente quer marcar banho pra seu cachorro
    Atendimento agendar = AgendamentoService.agendar(cliente, animal, petshop, servico, "Não tocar no rabo, pois ele morde", true, true);

    if (agendar == null){
      return;
    }
    System.out.println("Serviço " + agendar.getStatus() + " Total a pagar = " + agendar.getValorCobrado());

    agendar.iniciar();
    agendar.concluir();

  }
}