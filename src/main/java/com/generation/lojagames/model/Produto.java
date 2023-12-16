package com.generation.lojagames.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100)
	@NotBlank(message = "O produto deve ter um nome!!")
	@Size(min = 5, max = 100, message = "O nome deve conter entre 5 e 100 caracteres!")
	private String nome;

	@Column(length = 50)
	@NotBlank(message = "Defina a faixa etária indicada para o jogo!")
	@Size(min = 5, max = 50, message = "A definição de faixa etária deve conter entre 5 e 50 caracteres")
	private String faixaEtaria;

	@Column(length = 255)
	@NotBlank(message = "Insira a descrição do jogo!")
	@Size(max = 255, message = "A descrição não deve ultrapassar os 255 caracteres.")
	private String descricao;

	@Column(length = 100)
	@NotNull(message = "Insira opreço do jogo!")
	@DecimalMin(value = "0.0", inclusive = false, message = "O preço deve ser diferente de 0")
	@Digits(integer = 5, fraction = 2)
	private BigDecimal preco;

	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(String faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
