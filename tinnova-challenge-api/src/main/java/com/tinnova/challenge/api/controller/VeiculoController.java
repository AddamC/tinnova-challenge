package com.tinnova.challenge.api.controller;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tinnova.challenge.api.entity.Veiculo;
import com.tinnova.challenge.api.repository.VeiculoRepository;
import com.tinnova.challenge.api.service.VeiculoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private VeiculoService veiculoService;
	
	@GetMapping
	public List<Veiculo> listar() {
		return veiculoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPeloId(@PathVariable Long id) {
		Optional<Veiculo> retorno = veiculoRepository.findById(id);
		if (retorno.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(retorno.get());
		}
	}
	
	@GetMapping("/nome")
	public ResponseEntity<List<Veiculo>> buscarPeloNome(@RequestParam("nome") String nome){
		List<Veiculo> veiculos = veiculoRepository.findByNome(nome);
		return ResponseEntity.status(HttpStatus.OK).body(veiculos);
	}
	
	@GetMapping("/marca")
	public ResponseEntity<List<Veiculo>> buscarPelaMarca(@RequestParam("marca") String marca){
		List<Veiculo> veiculos = veiculoRepository.findByMarca(marca);
		return ResponseEntity.status(HttpStatus.OK).body(veiculos);
	}
	
	@GetMapping("/ano")
	public ResponseEntity<List<Veiculo>> buscarPeloAno(@RequestParam("ano") int ano){
		List<Veiculo> veiculos = veiculoRepository.findByAno(ano);
		return ResponseEntity.status(HttpStatus.OK).body(veiculos);
	}
	
	@GetMapping("/vendido")
	public ResponseEntity<List<Veiculo>> buscarPorDisponibilidade(@RequestParam("vendido") boolean vendido){
		List<Veiculo> veiculos = veiculoRepository.findByVendido(vendido);
		return ResponseEntity.status(HttpStatus.OK).body(veiculos);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veiculo> criar(@Valid @RequestBody Veiculo veiculo, HttpServletResponse response) {
		veiculo.setCreated(new Date());
		veiculo.setUpdated(new Date());
		Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

		return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {
		Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
		
		if (optionalVeiculo.isEmpty()) { return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); }

		veiculo.setId(id);
		veiculo.setCreated(new Date());
		veiculo.setUpdated(new Date());
		Veiculo veiculoSalvo = veiculoRepository.save(veiculo);

		return ResponseEntity.status(HttpStatus.OK).body(veiculoSalvo);
	}

	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Veiculo> atualizarParcialmente(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
	    Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
		Veiculo veiculo = optionalVeiculo.isEmpty() ? null : optionalVeiculo.get();
		
		if (veiculo == null) { return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); }
		
	    fields.forEach((k, v) -> {
	        Field field = ReflectionUtils.findField(Veiculo.class, k);
	        field.setAccessible(true);
	        ReflectionUtils.setField(field, veiculo, v);
	    });
	    veiculoRepository.save(veiculo);
		return ResponseEntity.status(HttpStatus.OK).body(veiculo);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		veiculoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}