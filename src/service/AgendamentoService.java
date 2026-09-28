package service;

import model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoService {
    public static Atendimento agendar(Cliente cliente, Animal animal, Petshop petshop, Servico servico, String observacao, List<Adicional> adicionais) {
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
                throw new RuntimeException("Esse serviço não possui adicionais.");
            } else {
                for (Adicional adicional : adicionais) {
                    if (servicoComAdicionais.possuiAdicional(adicional)) {
                        BigDecimal valorAdic = servicoComAdicionais.obterValorAdicionais(adicional);
                        adicionaisValor = adicionaisValor.add(valorAdic);
                    } else {
                        throw new RuntimeException("O adicional " + adicional + " não está disponivel no " + petshop.getNome());
                    }
                }
            }
        } else {

            System.out.println("Não existe adicionais para esse tipo de serviço.");
        }

        BigDecimal total = valorServico.add(adicionaisValor);

        return new Atendimento(dataAtual, total, observacao, animal, servico, adicionais, petshop);
    }
}
