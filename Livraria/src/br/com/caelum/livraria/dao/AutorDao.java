package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Autor;

/*
 * **** Stateless Session Bean
 * 
 * O Session Bean é um componente Java que geralmente guarda a lógica de negócio da aplicação, 
 * este tipo de componente é executado dentro de um Container EJB. 
 * Podemos criar um EJB e deixar que diversas aplicações o utilizem. 
 * O Stateless Session Bean tem o ciclo de vida que dura apenas o tempo de uma simples chamada de método.
 * 
 * http://www.universidadejava.com.br/materiais/ejb-sessionbean/
 * http://www.devmedia.com.br/trabalhando-com-session-beans-stateless-e-stateful/28260
 * 
 * ================================================================================================
 * 
 * **** @PostConstruct
 * 
	Ao usar frameworks que gerenciam o ciclo de vida de suas classes, como é o caso do Spring, 
	é sempre importante entender que uma instância gerenciada pelo framework não é uma instância qualquer.
	Uma instância gerenciada pelo Spring é chamda de Spring Bean e não é um objeto comum. Ele agora tem superpoderes.
	
	** Instância Comum vs. Spring Bean
	
	Considere a seguinte classe:
	
	@Service
	public class MeuServico {
	
	    @Autowired MeuDao meuDao;
	
	    public void acao() {
	       meuDao.atualizarBanco();
	    }
	
	}
	
	O que acontece se você criar a classe manualmente?
	
	MeuServico meuServico = new MeuServico();
	meuServico.acao();
	
	O resultado será um NullPonterException na linha meuDao.atualizarBanco(), pois o atributo meuDao estará nulo.
	Um objeto normal não é gerenciado pelo Spring.
	Então, sempre que usar um Spring Bean você deve deixar o Spring lhe entregar a instância, 
	seja através de uma anotação, injeção através de XML, etc.
	
	** Construtor vs. Pós-Construtor
	
	Quando o Spring inicia o contexto (Spring Context), que contém todos os beans, 
	ele cria instâncias dos beans anotados ou declarados na configuração, processa as anotações, injeta as dependências e algumas coisas a mais.
	Após inicializar corretamente tudo, ele chama o método que esteja anotado com @PostConstruct.
	Note que no momento que a instância é criada, não há nada injetado ou inicializado.
	Portanto, o código abaixo também resultaria em um NullPointerException:
	
	@Service
	public class MeuServico {
	
	    @Autowired MeuDao meuDao;
	
	    public MeuServico() {
	       meuDao.iniciarAlgumaCoisaNoBanco();
	    }
	
	}
	
	Veja, o Spring não conseguirá injetar meuDao antes de instanciar a classe. 
	Portanto em qualquer framework não é possível injetar a dependência 
	ou fazer qualquer outra coisa na classe antes de chamar algum construtor.
	A solução é usar o pós-construtor, que permite então executar alguma ação logo após a inicialização do Spring, 
	porém antes do sistema executar alguma ação do usuário.

 * 
 * https://pt.stackoverflow.com/questions/58403/qual-a-diferen%C3%A7a-entre-postconstruct-e-o-construtor
 * 
 * */

@Stateless
public class AutorDao {

	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct // método callback 
	void aposCriacao() {
	    System.out.println("AutorDao foi criado");
	}

	public void salva(Autor autor) {
		manager.persist(autor);
	}
	
	public List<Autor> todosAutores() {
		return manager.createQuery("select a from Autor a", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		return manager.find(Autor.class, autorId);
	}
	
}
