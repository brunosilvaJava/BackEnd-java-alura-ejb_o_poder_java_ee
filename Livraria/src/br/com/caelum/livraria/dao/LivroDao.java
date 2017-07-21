package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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

	public List<Livro> consultarLivrosPeloTitulo(String titulo) {
		
		TypedQuery<Livro> query = manager.createQuery("select l from Livro l where l.titulo like :pTitulo", Livro.class);
		
        query.setParameter("pTitulo", "%" + titulo + "%");

        return query.getResultList();
		
	}
	
}
