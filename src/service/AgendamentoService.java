package service;

import model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoService {
  public static Atendimento agendar(Cliente cliente, Animal animal, Petshop petshop, Servico servico, String observacao, List<Adicional> adicionais){
    if(!cliente.possuiAnimal(animal)){
      System.out.println("Erro. O animal não pertence ao tutor.");
      return null;
    }

    if (!petshop.possuiServico(servico)){
      System.out.println("O petshop não tem o serviço escolhido disponível.");
      return null;
    }

    LocalDateTime dataAtual = LocalDateTime.now();

    BigDecimal valorServico = servico.getValor();
    BigDecimal adicionaisValor = BigDecimal.ZERO;

    for (int i = 0; i < adicionais.size(); i++) {
      Adicional adicional = adicionais.get(i);
      if (petshop.possuiAdicional(adicional, servico)){
        BigDecimal valorAdic = petshop.obterValorAdicionais(adicional, servico);
        adicionaisValor = adicionaisValor.add(valorAdic);
      }
    }

    BigDecimal total = valorServico.add(adicionaisValor);

    return new Atendimento(dataAtual, total, observacao, animal, servico, adicionais, petshop);
  }
}
