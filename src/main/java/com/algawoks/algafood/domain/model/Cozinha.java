package com.algawoks.algafood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algawoks.algafood.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// @JsonRootName ("culinária")
// A chave da resposta REST será "culinária" em vez de "Cozinha" (somente xml)
public class Cozinha {

	@Id
	@NotNull (groups = Groups.CozinhaId.class)
	@EqualsAndHashCode.Include()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// @JsonIgnore
	// Parâmetro não será exibido na resposta REST (xml & json)
	// @JsonProperty("gastronomia")
	// A chave da resposta REST será "gastronomia" em vez de "nome"
	@NotBlank
	private String nome;
	
	@OneToMany(mappedBy = "cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>();
	
}
