package com.tinnova.challenge.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tinnova.challenge.api.entity.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
	@Query("SELECT v FROM Veiculo v WHERE v.nome LIKE %:nome%")
	List<Veiculo> findByNome(@Param("nome") String nome);

	@Query("SELECT v FROM Veiculo v WHERE v.marca LIKE %:marca%")
	List<Veiculo> findByMarca(@Param("marca") String marca);

	@Query("SELECT v FROM Veiculo v WHERE v.ano = :ano")
	List<Veiculo> findByAno(@Param("ano") int ano);

	@Query("SELECT v FROM Veiculo v WHERE v.vendido = :vendido")
	List<Veiculo> findByVendido(@Param("vendido") boolean vendido);
}