package br.com.caelum.livraria.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Model
public class AutorBean {
	
	/* ****  Injeção de dependências
	 * @Inject -> CDI
	 * @EJB -> EJB
	 * 
	 * https://netbeans.org/kb/docs/javaee/cdi-intro_pt_BR.html
	 * https://cursos.alura.com.br/forum/topico-diferecao-entre-anotacao-64-ejb-e-64-inject-22689 
	 * http://blog.triadworks.com.br/nao-misture-anotacoes-do-jsf-com-anotacoes-do-cdi
	 * 
	 */
	
	@Inject
	private Autor autor;
	
	@Inject // Injeção de dependências pelo EJB Container - Anotação CDI
	private AutorDao dao;
	
	public Autor getAutor() {
		return autor;
	}
	
	public void cadastra() {
		this.dao.salva(autor);
	}
	
	public List<Autor> getAutores() {
		return this.dao.todosAutores();
	}
	
}
