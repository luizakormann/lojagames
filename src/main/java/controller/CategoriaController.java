package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import model.Categoria;
import repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id) {
		return categoriaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Categoria>> getByCategoria(@PathVariable String categoria) {
		return ResponseEntity.ok(categoriaRepository.findAllByCategoriaContainingIgnoreCase(categoria));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {

		if (categoriaRepository.existsById(categoria.getId()))
			return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria));
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {

		if (categoriaRepository.existsById(id))
			categoriaRepository.deleteById(id);

		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
