package service;

import model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AgendamentoService {
  public static Atendimento agendar(Cliente cliente, Animal animal, Petshop petshop, Servico servico, String observacao, boolean corteUnha, boolean hidratacao){
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
    BigDecimal adicionais = BigDecimal.ZERO;

    if(hidratacao){
      adicionais = adicionais.add(BigDecimal.TEN);
      //petshop.getPrecoHidratacao();
    }
    if(corteUnha){
      adicionais = adicionais.add(BigDecimal.ONE);
      //petshop.getPrecoCorteUnha();
    }

    BigDecimal total = valorServico.add(adicionais);

    return new Atendimento(dataAtual, total, observacao, animal, servico, hidratacao, corteUnha, petshop);
  }
}
