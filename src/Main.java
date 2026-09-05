import model.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
  public static void main(String[] args) {

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

    Atendimento atendimento1 = new Atendimento(
            LocalDateTime.now(),
            new BigDecimal("35.90"),
            "",
            cachorro,
            banhoCao,
            true,
            true);

    petshop1.adicionaServico(banhoCao);
    petshop1.adicionaServico(banhoGato);
    petshop1.adicionaServico(tosaTesoura);
    petshop1.adicionaServico(tosaHigienica);
    petshop1.adicionaServico(tosaMaquina);

    giovanna.adicionaAnimal(new Cachorro(
            "Bella",
            LocalDate.of(2018,4,29),
            3.5f,
            "Fêmea",
            "Yorkshire",
            "Pequeno"));

    giovanna.listarAnimais();

    Cliente cliente = new Cliente(
            "João",
            "(11) 92323-2323",
            "joao@email.com.br");

    cliente.adicionaAnimal(cachorro);
    cliente.listarAnimais();

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
    cliente2.adicionaAnimal(cachorro2);

    cliente2.listarAnimais();
    System.out.println("Tutor do cachorro Thor: " + cachorro2.getTutor().getNome());
    System.out.println("Cachorro de Maria: " + cliente2.getAnimais());

  }
}