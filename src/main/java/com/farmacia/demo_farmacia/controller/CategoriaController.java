package com.farmacia.demo_farmacia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.demo_farmacia.model.Categoria;
import com.farmacia.demo_farmacia.repository.CategoriaRepository;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/farmacia-categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repositorio;
	
	@GetMapping("/all")
	public ResponseEntity<List<Categoria>> GetAll(){
		return ResponseEntity.status(200).body(repositorio.findAll());
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<List<Categoria>> GetByNome(String nome){
		return ResponseEntity.status(200).body(repositorio.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/{id_categoria}")
	public ResponseEntity<Categoria> GetById(@PathVariable Long id){
		return repositorio.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/adicionar-categoria")
	public ResponseEntity<Categoria> post (@RequestBody Categoria categoria){
		return ResponseEntity.status(200).body(repositorio.save(categoria));
	}
	
	@PutMapping("/atualizar-categoria")
	public ResponseEntity<Categoria> put (@RequestBody Categoria categoria){
		return ResponseEntity.status(201).body(repositorio.save(categoria));
	}
	
	@DeleteMapping("/deletar-categoria")
	public ResponseEntity<String> delete(@PathVariable Long id){
		Optional<Categoria> categoriaExist = repositorio.findById(id);
		if(categoriaExist.isPresent()) {
			return ResponseEntity.status(200).body("Categoria Deletada");
		} else {
			return ResponseEntity.status(201).body("Categoria não Encontrada");
		}
	}
}
