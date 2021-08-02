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

import com.farmacia.demo_farmacia.model.Produto;
import com.farmacia.demo_farmacia.repository.ProdutoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/farmacia-produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repositorio;
	
	@GetMapping("/all")
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.status(200).body(repositorio.findAll());
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<List<Produto>> GetByNome(String nome){
		return ResponseEntity.status(200).body(repositorio.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/{id_produto}")
	public ResponseEntity<Produto> GetById(@PathVariable Long id){
		return repositorio.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/adicionar-produto")
	public ResponseEntity<Produto> post (@RequestBody Produto produto){
		return ResponseEntity.status(200).body(repositorio.save(produto));
	}
	
	@PutMapping("/atualizar-produto")
	public ResponseEntity<Produto> put (@RequestBody Produto produto){
		return ResponseEntity.status(200).body(repositorio.save(produto));
	}
	
	@DeleteMapping("/deletar-produto")
	public ResponseEntity<String> delete (@PathVariable Long id){
		Optional<Produto> produtoExist = repositorio.findById(id);
		if(produtoExist.isPresent()) {
			return ResponseEntity.status(200).body("Produto Deletado.");
		} else {
			return ResponseEntity.status(201).body("Produto não Encontrado.");
		}
	}
}
