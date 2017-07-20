package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class LivroDao {

	@PersistenceContext
	private EntityManager manager;
	
	public void salva(Livro livro) {
		System.out.println("LivroDao_salva");
		manager.persist(livro);
	}
	
	public List<Livro> todosLivros() {
		System.out.println("LivroDao_todosLivros");
		return manager.createQuery("select a from Livro a", Livro.class).getResultList();
	}
	
}
