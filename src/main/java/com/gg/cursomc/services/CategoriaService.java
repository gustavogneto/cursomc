package com.gg.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.gg.cursomc.domain.Categoria;
import com.gg.cursomc.dto.CategoriaDTO;
import com.gg.cursomc.repositories.CategoriaRepository;
import com.gg.cursomc.services.exceptions.DataIntegrityException;
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

	public Categoria update(Categoria obj) {	
		
		find(obj.getId()); //buscar objeto
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel executar delete em categorias que possuem produtos");
		}
		
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
		}
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getName());
	}
	

}


