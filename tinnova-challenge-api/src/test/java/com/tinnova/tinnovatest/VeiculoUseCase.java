package com.tinnova.tinnovatest;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.tinnova.challenge.api.entity.Veiculo;
import com.tinnova.challenge.api.repository.VeiculoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeiculoUseCase {

  private final VeiculoRepository veiculoRepository;

  public Veiculo criarVeiculo(Veiculo veiculo) {
    veiculo.setNome("Fiesta");
    veiculo.setMarca("Ford");
    veiculo.setDescricao("Carro semi-novo sem defeitos visuais | Cor: Preto Metálico");
    veiculo.setAno(2010);
    return veiculoRepository.save(veiculo);
  }

}