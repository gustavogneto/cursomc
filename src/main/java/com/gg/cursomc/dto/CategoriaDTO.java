package com.gg.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gg.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L; 
	
	
	private Integer id;
	@NotEmpty(message="Este campo não pode ser nulo")
	@Length(min=5, max=80, message = "tamanho entre 5 a 80 caracteres")
	private String name;
	
	public CategoriaDTO() {}
	public CategoriaDTO(Categoria obj) {
		id = obj.getId();
		name =obj.getName();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
