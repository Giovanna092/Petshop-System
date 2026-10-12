package service;

import exceptions.AdicionalNaoDisponivelException;
import exceptions.AnimalJaPossuiAtendimentoException;
import model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoService {

    public static List<Atendimento> atendimentos = new ArrayList<>();

    public static Atendimento agendar(Cliente cliente, Animal animal, Petshop petshop, Servico servico, String observacao, List<Adicional> adicionais) {
        if(animalPossuiAtendimentoAtivo(animal)){
            throw new AnimalJaPossuiAtendimentoException("Esse animal já possui um agendamento ativo.");
        }

        if (!cliente.possuiAnimal(animal)) {
            System.out.println("Erro. O animal não pertence ao tutor.");
            return null;
        }

        if (!petshop.possuiServico(servico)) {
            System.out.println("O petshop não tem o serviço escolhido disponível.");
            return null;
        }

        LocalDateTime dataAtual = LocalDateTime.now();

        BigDecimal valorServico = servico.getValor();
        BigDecimal adicionaisValor = BigDecimal.ZERO;

        if (servico instanceof ServicoComAdicionais servicoComAdicionais) {
            if (servicoComAdicionais.getAdicionais().isEmpty()) {
                throw new AdicionalNaoDisponivelException("Esse serviço não possui adicionais.");
            } else {
                for (Adicional adicional : adicionais) {
                    if (servicoComAdicionais.possuiAdicional(adicional)) {
                        BigDecimal valorAdic = servicoComAdicionais.obterValorAdicionais(adicional);
                        adicionaisValor = adicionaisValor.add(valorAdic);
                    } else {
                        throw new AdicionalNaoDisponivelException("O adicional " + adicional + " não está disponivel no " + petshop.getNome());
                    }
                }
            }
        } else {

            System.out.println("Não existe adicionais para esse tipo de serviço.");
        }

        BigDecimal total = valorServico.add(adicionaisValor);

        Atendimento atendimento = new Atendimento(dataAtual, total, observacao, animal, servico, adicionais, petshop);

        //salva em memtoria uma lista com todos os atendimentos criados
        AgendamentoService.atendimentos.add(atendimento);

        return atendimento;
    }

    private static boolean animalPossuiAtendimentoAtivo(Animal animal){
        for (Atendimento atendimento : atendimentos) {
            if ((atendimento.getStatus().equals(Status.EM_ANDAMENTO) || atendimento.getStatus().equals(Status.AGENDADO))
                    && atendimento.getAnimal().equals(animal)) {
                return true;
            }
        }
        return false;
    }
}
