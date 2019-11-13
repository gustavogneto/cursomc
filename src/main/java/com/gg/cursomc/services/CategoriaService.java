package com.gg.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gg.cursomc.domain.Categoria;
import com.gg.cursomc.repositories.CategoriaRepository;
import com.gg.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	
	public Categoria insert(Categoria obj) {		
		obj.setId(null); //sem null é considerado atualização não inserção, o objeto novo tem que ter ID nulo 
		return repo.save(obj);
	}


}


