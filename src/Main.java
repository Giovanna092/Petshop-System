import model.*;
import service.AgendamentoService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                LocalDate.of(2025, 4, 22),
                5,
                "Femêa",
                "Persa");

        Petshop petshop1 = new Petshop(
                "4ever pets",
                "R. Armandinho Soares, 123",
                "(11) 9 5231-2123");

        Map<Adicional, BigDecimal> adicionaisBanhoCao = new HashMap<>();
        adicionaisBanhoCao.put(Adicional.HIDRATACAO, BigDecimal.TEN);
        adicionaisBanhoCao.put(Adicional.CORTE_DE_UNHA, BigDecimal.ONE);
        adicionaisBanhoCao.put(Adicional.LIMPEZA_OUVIDO, BigDecimal.ONE);

        Map<Adicional, BigDecimal> adicionaisVazio = new HashMap<>();

        Servico banhoCao = new Banho("Banho padrão Cachorro",
                Duration.ofMinutes(15),
                new BigDecimal("25.90"),
                "Banho comum para cães.",
                adicionaisBanhoCao);

        Servico banhoCao2 = new Banho("Banho padrão Cachorro",
                Duration.ofMinutes(15),
                new BigDecimal("20.90"),
                "Banho comum para cães.",
                adicionaisVazio);

        Servico banhoGato = new Banho("Banho padrão Gato",
                Duration.ofMinutes(15),
                new BigDecimal("20.90"),
                "Banho comum para gatos.",
                adicionaisBanhoCao);

        Servico tosaTesoura = new Tosa(
                "Tosa tesoura completa",
                Duration.ofHours(1L),
                new BigDecimal("65.90"),
                "Tosa tesoura para cães.",
                adicionaisBanhoCao,
                "Tesoura");

        Servico tosaMaquina = new Tosa(
                "Tosa máquina completa",
                Duration.ofMinutes(40),
                new BigDecimal("39.90"),
                "Tosa máquina para cães.",
                adicionaisBanhoCao,
                "Máquina");

        Servico tosaHigienica = new Tosa(
                "Tosa higiênica completa",
                Duration.ofMinutes(20),
                new BigDecimal("49.90"),
                "Tosa higiênica para cães.",
                adicionaisBanhoCao,
                "Higiênica");

        Servico consultaVeterinaria = null;

        Cliente giovanna = new Cliente(
                "Giovanna",
                "(11) 92364-2361",
                "giovanna@email.com.br");

        Animal cachorro3 = new Cachorro(
                "Bella",
                LocalDate.of(2018, 4, 29),
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

        Petshop petshop3 = new Petshop(
                "PetShop aumigos",
                "R. Pinheiros, 432 ",
                "(11) 98755-9875");

        //definindo relações
        petshop1.adicionaServico(banhoCao);
        petshop1.adicionaServico(banhoGato);
        petshop1.adicionaServico(tosaTesoura);
        petshop1.adicionaServico(tosaHigienica);
        petshop1.adicionaServico(tosaMaquina);

        petshop3.adicionaServico(banhoCao2);

        cliente.adicionaAnimal(cachorro);
        cliente2.adicionaAnimal(cachorro2);
        giovanna.adicionaAnimal(cachorro3);

        //testando a historia
        System.out.println("------ AGENDAMENTO SIMPLES ------");
        historia(cliente, cachorro, petshop1, banhoCao, null);
        System.out.println("------ FIM HISTORIA 1 ------");

        System.out.println("------ AGENDAMENTO COM ADICIONAIS ------");
        historia(giovanna, cachorro3, petshop1, banhoCao, List.of(Adicional.HIDRATACAO, Adicional.CORTE_DE_UNHA));
        System.out.println("------ FIM HISTORIA 2 ------");

        System.out.println("------ ADICIONAL NÃO OFERECIDO ------");
        adicionaisVazio.put(Adicional.HIDRATACAO, new BigDecimal("15"));
        historia(cliente, cachorro, petshop3, banhoCao2, List.of(Adicional.ANTI_VERME));
        System.out.println("------ FIM HISTORIA 3 ------");

        System.out.println("------ ANIMAL NÃO PERTENCE AO CLIENTE ------");
        historia(cliente, cachorro2, petshop1, banhoCao, List.of());
        System.out.println("------ FIM HISTORIA 4 ------");

        System.out.println("------ SERVIÇO NÃO OFERECIDO PELO PETSHOP ------");
        historia(cliente, cachorro, petshop3, banhoCao, List.of(Adicional.HIDRATACAO));
        System.out.println("------ FIM HISTORIA 5 ------");

        System.out.println("------ CANCELAMENTO ------");
        historia(cliente2, cachorro2, petshop3, banhoCao2, List.of());
        System.out.println("------ FIM HISTORIA 6 ------");

        System.out.println("------ TOSA ------");
        historia(cliente, cachorro, petshop1, tosaHigienica, List.of());
        System.out.println("------ FIM HISTORIA 7 ------");
    }

    private static void historia(Cliente cliente, Animal animal, Petshop petshop, Servico servico, List<Adicional> adicionais) {
        //cliente quer marcar banho pra seu cachorro

        if (adicionais == null) {
            adicionais = List.of();
        }

        Atendimento agendar;

        try {
            agendar = AgendamentoService.agendar(cliente, animal, petshop, servico, "Não tocar no rabo, pois ele morde", adicionais);
        } catch (RuntimeException e) {
            System.err.println("Não foi possível agendar: " + e.getMessage());
            return;
        }

        if(agendar == null){
            System.err.println("O agendamento não foi concluido, tente novamente.");
            return;
        }

        System.out.println("Serviço " + agendar.getStatus() + " Total a pagar = " + agendar.getValorCobrado());

        acaoAgendar(agendar, "iniciar");
    }

    public static void acaoAgendar(Atendimento atendimento, String acao) {
        if (acao.equalsIgnoreCase("cancelar")) {
            atendimento.cancelar();
        } else {
            atendimento.iniciar();
        }

        if (atendimento.getStatus().equals(Status.EM_ANDAMENTO)) {
            atendimento.concluir();
        }
    }
}