package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.modelo.Autor;

/*
 * **** Stateless Session Bean
 * 
 * O Session Bean é um componente Java que guarda a lógica de negócio da aplicação, 
 * este tipo de componente é executado dentro de um Container EJB. 
 * Podemos criar um EJB e deixar que diversas aplicações o utilizem. 
 * O Stateless Session Bean tem o ciclo de vida que dura apenas o tempo de uma simples chamada de método.
 * 
 * http://www.universidadejava.com.br/materiais/ejb-sessionbean/
 * http://www.devmedia.com.br/trabalhando-com-session-beans-stateless-e-stateful/28260
 * 
 * */

@Stateless 
public class AutorDao {

	@Inject
	private Banco banco;

	public void salva(Autor autor) {
		banco.save(autor);
	}
	
	public List<Autor> todosAutores() {
		return banco.listaAutores();
	}

	public Autor buscaPelaId(Integer autorId) {
		Autor autor = this.banco.buscaPelaId(autorId);
		return autor;
	}
	
}
