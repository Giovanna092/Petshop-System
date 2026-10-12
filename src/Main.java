import model.*;
import service.AgendamentoService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner entrada = new Scanner(System.in);
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Animal> animais = new ArrayList<>();
    private static final List<Petshop> petshops = new ArrayList<>();
    private static final List<Servico> servicos = new ArrayList<>();
    private static final List<Atendimento> atendimentos = new ArrayList<>();

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

        clientes.addAll(List.of(giovanna, cliente, cliente2));
        animais.addAll(List.of(cachorro, gato, cachorro2, cachorro3));
        petshops.addAll(List.of(petshop1, petshop2, petshop3));
        servicos.addAll(List.of(banhoCao, banhoCao2, banhoGato, tosaTesoura, tosaMaquina, tosaHigienica));

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


        exibeMenu();
    }

    private static void exibeMenu() {
        System.out.println("========================= Bem-Vindo =========================");
        int opcao;

        do {
            System.out.println("""
                    Escolha uma opção:
                    1 - Cadastrar Tutor
                    2 - Cadastrar Animal
                    3 - Cadastrar Serviço
                    4 - Cadastrar Petshop
                    5 - Agendar Banho
                    6 - Agendar Banho e Tosa
                    7 - Cancelar Agendamento
                    8 - Iniciar Agendamento
                    9 - Finalizar Agendamento
                    10 - Consultar Atendimentos Cadastrados
                    0 - Sair
                    """);

            opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao){
                case 1 -> cadastraTutor();
                case 2 -> cadastraAnimal();
                case 3 -> cadastraServico();
                case 4 -> cadastrarPetshop();
                case 5 -> agendaBanho();
                case 6 -> agendaTosa();
                case 7 -> cancelarAgendamento();
                case 8 -> iniciarAgendamento();
                case 9 -> finalizarAgendamento();
                case 10 -> consultarAtendimentos();
                default -> opcao = 0;
            }
        } while (!(opcao == 0));


    }

    private static void cadastraTutor() {
        String nome;
        String telefone;
        String email;

        System.out.println("Preencha os dados para cadastro");
        System.out.println("Nome: ");
        nome = entrada.nextLine();

        System.out.println("Telefone: ");
        telefone = entrada.nextLine();

        System.out.println("E-mail: ");
        email = entrada.nextLine();

        Cliente cliente = new Cliente(nome, telefone, email);
        clientes.add(cliente);

        System.out.println("Cliente " + cliente.getNome() + " cadastrado!");
    }

    private static void cadastraAnimal() {
        Cliente tutor = escolheCliente();

        if (tutor == null) {
            return;
        }

        System.out.println("Preencha os dados para cadastro");
        System.out.println("Tipo do animal (1 - Cachorro, 2 - Gato): ");
        int tipo = Integer.parseInt(entrada.nextLine());

        System.out.println("Nome: ");
        String nome = entrada.nextLine();

        System.out.println("Data de nascimento (AAAA-MM-DD): ");
        LocalDate nascimento = LocalDate.parse(entrada.nextLine());

        System.out.println("Peso: ");
        float peso = Float.parseFloat(entrada.nextLine());

        System.out.println("Genero: ");
        String genero = entrada.nextLine();

        System.out.println("Raca: ");
        String raca = entrada.nextLine();

        Animal animal;

        if (tipo == 1) {
            System.out.println("Porte: ");
            String porte = entrada.nextLine();
            animal = new Cachorro(nome, nascimento, peso, genero, raca, porte);
        } else {
            animal = new Gato(nome, nascimento, peso, genero, raca);
        }

        animais.add(animal);
        tutor.adicionaAnimal(animal);
        System.out.println("Animal " + animal.getNome() + " cadastrado para " + tutor.getNome() + "!");
    }

    private static void cadastraServico() {
        System.out.println("Preencha os dados para cadastro");
        System.out.println("Tipo do servico (1 - Banho, 2 - Tosa): ");
        int tipo = Integer.parseInt(entrada.nextLine());

        System.out.println("Nome: ");
        String nome = entrada.nextLine();

        System.out.println("Duracao em minutos: ");
        long duracao = Long.parseLong(entrada.nextLine());

        System.out.println("Valor: ");
        BigDecimal valor = new BigDecimal(entrada.nextLine());

        System.out.println("Descricao: ");
        String descricao = entrada.nextLine();

        Map<Adicional, BigDecimal> adicionais = cadastraAdicionais();
        Servico servico;

        if (tipo == 2) {
            System.out.println("Tipo da tosa: ");
            String tipoTosa = entrada.nextLine();
            servico = new Tosa(nome, Duration.ofMinutes(duracao), valor, descricao, adicionais, tipoTosa);
        } else {
            servico = new Banho(nome, Duration.ofMinutes(duracao), valor, descricao, adicionais);
        }

        servicos.add(servico);
        System.out.println("Servico " + servico.getNome() + " cadastrado!");
    }

    private static void cadastrarPetshop() {
        System.out.println("Preencha os dados para cadastro");
        System.out.println("Nome: ");
        String nome = entrada.nextLine();

        System.out.println("Endereco: ");
        String endereco = entrada.nextLine();

        System.out.println("Telefone: ");
        String telefone = entrada.nextLine();

        Petshop petshop = new Petshop(nome, endereco, telefone);
        petshops.add(petshop);
        System.out.println("Petshop " + petshop.getNome() + " cadastrado!");
    }

    private static void agendaBanho() {
        agendaServico(Banho.class);
    }

    private static void agendaTosa() {
        agendaServico(Tosa.class);
    }

    private static void agendaServico(Class<? extends Servico> tipoServico) {
        Cliente cliente = escolheCliente();
        Petshop petshop = escolhePetshop();
        Animal animal = escolheAnimal(cliente);
        Servico servico = escolheServico(tipoServico);

        if (cliente == null || petshop == null || animal == null || servico == null) {
            return;
        }

        petshop.adicionaServico(servico);

        System.out.println("Observacao: ");
        String observacao = entrada.nextLine();

        Atendimento atendimento;

        try {
            atendimento = AgendamentoService.agendar(cliente, animal, petshop, servico, observacao, List.of());
        } catch (RuntimeException e) {
            System.err.println("Nao foi possivel agendar: " + e.getMessage());
            return;
        }

        if (atendimento == null) {
            System.err.println("O agendamento nao foi concluido, tente novamente.");
            return;
        }

        atendimentos.add(atendimento);
        System.out.println("Servico " + atendimento.getStatus() + " Total a pagar = " + atendimento.getValorCobrado());
    }

    private static void cancelarAgendamento() {
        Atendimento atendimento = escolheAtendimento();

        if (atendimento == null) {
            return;
        }

        atendimento.cancelar();
    }

    private static void iniciarAgendamento() {
        Atendimento atendimento = escolheAtendimento();

        if (atendimento == null) {
            return;
        }

        atendimento.iniciar();
    }

    private static void finalizarAgendamento() {
        Atendimento atendimento = escolheAtendimento();

        if (atendimento == null) {
            return;
        }

        atendimento.concluir();
    }

    private static void consultarAtendimentos() {
        if (atendimentos.isEmpty()) {
            System.out.println("Nenhum atendimento cadastrado.");
            return;
        }

        for (int i = 0; i < atendimentos.size(); i++) {
            Atendimento atendimento = atendimentos.get(i);
            System.out.println((i + 1) + " - " + atendimento.getServico().getNome()
                    + " | " + atendimento.getAnimal().getNome()
                    + " | " + atendimento.getPetshop().getNome()
                    + " | " + atendimento.getStatus());
        }
    }

    private static Map<Adicional, BigDecimal> cadastraAdicionais() {
        Map<Adicional, BigDecimal> adicionais = new HashMap<>();

        System.out.println("Deseja cadastrar adicionais? (s/n): ");
        String resposta = entrada.nextLine();

        while (resposta.equalsIgnoreCase("s")) {
            System.out.println("Adicional: 1 - HIDRATACAO, 2 - CORTE_DE_UNHA, 3 - LIMPEZA_OUVIDO, 4 - ANTI_VERME, 5 - ANTI_PULGA");
            int opcao = Integer.parseInt(entrada.nextLine());

            System.out.println("Valor do adicional: ");
            BigDecimal valor = new BigDecimal(entrada.nextLine());

            adicionais.put(Adicional.values()[opcao - 1], valor);

            System.out.println("Cadastrar outro adicional? (s/n): ");
            resposta = entrada.nextLine();
        }

        return adicionais;
    }

    private static Cliente escolheCliente() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
            return null;
        }

        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + " - " + clientes.get(i).getNome());
        }

        System.out.println("Escolha o tutor: ");
        return clientes.get(Integer.parseInt(entrada.nextLine()) - 1);
    }

    private static Animal escolheAnimal(Cliente cliente) {
        if (cliente == null || cliente.getAnimais().isEmpty()) {
            System.out.println("Nenhum animal cadastrado para o tutor.");
            return null;
        }

        for (int i = 0; i < cliente.getAnimais().size(); i++) {
            System.out.println((i + 1) + " - " + cliente.getAnimais().get(i).getNome());
        }

        System.out.println("Escolha o animal: ");
        return cliente.getAnimais().get(Integer.parseInt(entrada.nextLine()) - 1);
    }

    private static Petshop escolhePetshop() {
        if (petshops.isEmpty()) {
            System.out.println("Nenhum petshop cadastrado.");
            return null;
        }

        for (int i = 0; i < petshops.size(); i++) {
            System.out.println((i + 1) + " - " + petshops.get(i).getNome());
        }

        System.out.println("Escolha o petshop: ");
        return petshops.get(Integer.parseInt(entrada.nextLine()) - 1);
    }

    private static Servico escolheServico(Class<? extends Servico> tipoServico) {
        List<Servico> servicosFiltrados = new ArrayList<>();

        for (Servico servico : servicos) {
            if (tipoServico.isInstance(servico)) {
                servicosFiltrados.add(servico);
            }
        }

        if (servicosFiltrados.isEmpty()) {
            System.out.println("Nenhum servico cadastrado para essa opcao.");
            return null;
        }

        for (int i = 0; i < servicosFiltrados.size(); i++) {
            System.out.println((i + 1) + " - " + servicosFiltrados.get(i).getNome());
        }

        System.out.println("Escolha o servico: ");
        return servicosFiltrados.get(Integer.parseInt(entrada.nextLine()) - 1);
    }

    private static Atendimento escolheAtendimento() {
        consultarAtendimentos();

        if (atendimentos.isEmpty()) {
            return null;
        }

        System.out.println("Escolha o atendimento: ");
        return atendimentos.get(Integer.parseInt(entrada.nextLine()) - 1);
    }

    private static void criaAgendamento(Cliente cliente, Animal animal, Petshop petshop, Servico servico, List<Adicional> adicionais) {
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
