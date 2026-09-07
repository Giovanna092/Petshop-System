package service;

import model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoService {
  public static Atendimento agendar(Cliente cliente, Animal animal, Petshop petshop, Servico servico, String observacao, boolean corteUnha, boolean hidratacao){

    List<Animal> animais = cliente.getAnimais();
    boolean animalPertenceATutor = false;

    for (Animal a : animais){
      if(a.equals(animal)){
        animalPertenceATutor = true;
        break;
      }
    }

    if (!animalPertenceATutor){
      System.out.println("Erro. O animal não pertence ao tutor.");
      return null;
    }

    List<Servico> servicos = petshop.getServicos();
    boolean servicoDisponivelNoPetshop = false;

    for (Servico value : servicos) {
      if (value.equals(servico)) {
        servicoDisponivelNoPetshop = true;
        break;
      }
    }

    if (!servicoDisponivelNoPetshop){
      System.out.println("Não encontramos petshop com o servico selecionado disponivel");
      return null;
    }

    LocalDateTime dataAtual = LocalDateTime.now();

    BigDecimal valorServico = servico.getValor();
    float adicionais = 0;

    if(hidratacao){
      adicionais += 10;
      //petshop.getPrecoHidratacao();
    }
    if(corteUnha){
      adicionais += 5;
      //petshop.getPrecoCorteUnha();
    }

    float valorSoma = adicionais + valorServico.floatValue();
    BigDecimal total = BigDecimal.valueOf(valorSoma);

    return new Atendimento(dataAtual, total, observacao, animal, servico, hidratacao, corteUnha);
  }
}
