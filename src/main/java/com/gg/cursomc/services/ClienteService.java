package com.gg.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gg.cursomc.domain.Cliente;
import com.gg.cursomc.domain.Cliente;
import com.gg.cursomc.dto.ClienteDTO;
import com.gg.cursomc.repositories.ClienteRepository;
import com.gg.cursomc.services.exceptions.DataIntegrityException;
import com.gg.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	public Cliente insert(Cliente obj) {
		obj.setId(null); // sem null é considerado atualização não inserção, o objeto novo tem que ter ID
							// nulo
		return repo.save(obj);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		find(obj.getId()); // buscar objeto
		updateData(newObj, obj);
		return repo.save(obj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel executar operação, pois possuem entidades relacionadas");
		}

	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente fromDTO(ClienteDTO objDto) {
	//	throw new UnsupportedOperationException();
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);

	}

	// controla paginação
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	}


