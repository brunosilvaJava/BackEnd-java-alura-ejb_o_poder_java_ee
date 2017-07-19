package br.com.caelum.livraria.service;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.dao.LivroDao;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

public class LivroService {
	
	@Inject
	private LivroDao livroDao;
	
	@Inject
	private AutorDao autorDao;

	public void cadastra(Livro livro, Integer autorId) {
		
		Autor autor = autorDao.buscaPelaId(autorId);
		livro.setAutor(autor);
		
		livroDao.salva(livro);
				
	}

	public List<Autor> todosAutores() {
		return autorDao.todosAutores();
	}
	
	public List<Livro> todosLivros() {
		return livroDao.todosLivros();
	}
}
