package br.com.caelum.livraria.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.service.LivroService;

@Model
public class LivroBean {
	
	@Inject
	private Livro livro;
	
	private Integer autorId;
	
	@Inject
	private LivroService livroService;

	public void cadastra() {
		
		System.out.println("LivroBean_cadastra");
		
		livroService.cadastra(livro, autorId);
		
		this.livro = new Livro();
	}

	public List<Autor> getAutores() {
		return livroService.todosAutores();
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public Integer getAutorId() {
		return autorId;
	}
	
	public List<Livro> getLivros() {
		return this.livroService.todosLivros();
	}
}
