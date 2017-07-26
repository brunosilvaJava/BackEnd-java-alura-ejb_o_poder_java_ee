# Curso Alura - EJB: O poder da Java EE / Nível Avançado

## Apresentação do projeto

A aplicação livraria usa JSF e Primefaces para definir a interface. 
Há uma página de login: o login é admin a senha é pass. 
Após efetuado o login somos redirecionados para a página principal de aplicação. 
Trata-se de um cadastro de livros e autores, com abas para cada funcionalidade além do logout. 

## Aulas

* [01 - Introdução aos Enterprise Java Bean](#01---introdução-aos-enterprise-java-bean-)
* [02 - Ciclo de vida dos Session Beans](#02---ciclo-de-vida-dos-session-beans-)
* [03 - Integração do JPA com Pool e DataSource](#03---integração-do-jpa-com-pool-e-datasource-)
* [04 - Gerenciamento de Transações com JTA](#04---gerenciamento-de-transações-com-jta-)
* [05 - Lidando com Exceções](#05---lidando-com-exceções-)
* [06 - Novos serviços com Interceptadores](#06---novos-serviços-com-interceptadores-)
* [07 - Integração com Web Services](#07---integração-com-web-services-)
* [08 - Agendamento e EAR](#08---agendamento-e-ear-)

- ### 01 - Introdução aos Enterprise Java Bean [↑](#aulas)
    
    * **Servidor de Aplicação**
       * A tarefa de um servidor de aplicações é livrar o desenvolvedor de preocupações como: segurança na aplicação, conecção e persistência das informações no banco de dados, acesso a serviços, etc, e fornecer uma infra-estrutura pronta para que o desenvolvedor possa aproveitar. Ou seja, não é a aplicação que vai gerenciar a transação, a conexão com o banco de dados ou se preocupar com o agendamento de tarefas. A inversão de controle (IoC) faz com que o servidor de aplicação cuide dessas atividades.
   
   * **Session Beans**
      * Onde geralmente ficam as regras de negócio
   
   * **JNDI**
      * Registro de componentes administrados pelo EJB
      
   * **Session Bean Stateless**
      * @Stateless -> Especificação EJB 3.1
      * Classe EJB econômica
      * Não mantém estado de conversação com o cliente, é reaproveitado entre clientes
      * Intercambiáveil e pode ser alocado de um pool
      
   * **Injeção de dependência**
      * @Inject -> Especificação CDI
      

- ### 02 - Ciclo de vida dos Session Beans [↑](#aulas)

   * **Pós-Construtor**
      * ~~Assim que o EJB Container cria e inicializa o Session Bean, o método anotado com @PostConstruct é executado~~. Esse tipo de método ligado ao ciclo de vida do Session Bean também é chamado de Callback. _Na verdade não é bem assim..._
      * [**Para saber mais**](https://pt.stackoverflow.com/questions/58403/qual-a-diferen%C3%A7a-entre-postconstruct-e-o-construtor):
         > Ao usar frameworks que gerenciam o ciclo de vida de suas classes, como é o caso do Spring, 
         é sempre importante entender que uma instância gerenciada pelo framework não é uma instância qualquer.
         Uma instância gerenciada pelo Spring é chamda de Spring Bean e não é um objeto comum. Ele agora tem 'superpoderes'.
         >
         > ***Instância Comum vs. Spring Bean***
         >
         > Considere a seguinte classe:
         >
		 >	 ```java
		 >	 @Service
		 >	 public class MeuServico {
		 >
		 >	     @Autowired MeuDao meuDao;
		 >
		 >	     public void acao() {
		 >			meuDao.atualizarBanco();
		 >	     }
		 >
		 >	 }
		 >	 ```
         >
         > O que acontece se você criar a classe manualmente?
         >
		 >	 ```java
		 >	 MeuServico meuServico = new MeuServico();
		 >	 meuServico.acao();
		 >	 ```
         >
         > O resultado será um **```NullPonterException```** na linha **```meuDao.atualizarBanco()```**, pois o atributo meuDao estará nulo.
         > Um objeto normal não é gerenciado pelo Spring.
         Então, sempre que usar um Spring Bean você deve deixar o Spring lhe entregar a instância, 
         seja através de uma anotação, injeção através de XML, etc.
         >
         > ***Construtor vs. Pós-Construtor***
         >
         > Quando o Spring inicia o contexto (Spring Context), que contém todos os beans, 
         ele cria instâncias dos beans anotados ou declarados na configuração, processa as anotações, injeta as dependências e algumas coisas a mais.
         > _Após inicializar corretamente tudo, ele chama o método que esteja anotado com @PostConstruct.
         > Note que no momento que a instância é criada, não há nada injetado ou inicializado_.
         > Portanto, o código abaixo também resultaria em um **```NullPointerException```**:	     
		 >	 ```java
		 >	 @Service
		 >	 public class MeuServico {
 		 >
		 >	     @Autowired MeuDao meuDao;
		 >
		 >	     public MeuServico() {
		 >			meuDao.iniciarAlgumaCoisaNoBanco();
		 >	     }
		 >
		 >	 }
		 >	 ```
         > Veja, o Spring não conseguirá injetar meuDao antes de instanciar a classe. 
         Portanto em qualquer framework não é possível injetar a dependência 
         ou fazer qualquer outra coisa na classe antes de chamar algum construtor.
         A solução é usar o pós-construtor, que permite então executar alguma ação logo após a inicialização do Spring, 
         porém antes do sistema executar alguma ação do usuário.
	 >
	 
   * **Thread safety**
		* Um EJB Session Bean não é compartilhado entre Threads. Ou seja, quando um Session Bean estiver em uso, o EJB Container decide criar mais um Session Bean para atender uma nova chamada. Uma estratégia usada pelos servidores de aplicação para isso é o ***Pooling for Stateless Session EJBs***.
      
   * **Pool de Objetos**
      * O EJB Container automaticamente fornece um pool de objetos que gerencia a quantidade do Session Beans.
      * A configuração desse pool se encontra no arquivo de configuração do servidor de aplicação -> _standalone.xml_
      * pool -> Dentro dessa seção há um elemento **```<strict-max-pool ... >```**, para os Stateless Session Beans **```(name="slsb-strict-max-pool")```** que controla o número máximo de EJBs no pool **```(max-pool-size="20")```**, nesse caso são 20 instâncias.
		```xml
		<pools>
			<bean-instance-pools>
				<strict-max-pool name="slsb-strict-max-pool" max-pool-size="20" instance-acquisition-timeout="5" instance-acquisition-timeout-unit="MINUTES"/>
				<!-- max-pool-size -> Define a quantidade de objetos no pool -->
				<!-- outros elementos omitidos -->
			</bean-instance-pools>
		</pools>
		```
	
   * **Session Bean Singleton**
		* @Singleton -> Especificação EJB 3.1
		* Garante que haverá somente uma instância do Session Bean.
		* Tipicamente usado para inicializar alguma configuração ou agendar algum serviço, coisas que só fazem sentido no início da aplicação.
	
   * **Eager Initialization**   
		* @Startup
		* Por padrão um EJB é carregado sob demanda (lazy), mas através da anotação ***@Startup*** podemos definir que queremos usar o Singleton Bean desde o início da aplicação:
	
			```java
			@Singleton //do package javax.ejb
			@Startup
			public class MeuServico {
			```
	
   * **Session Bean Stateful (SBSF)**
		* Parecido com o objeto HttpSession do mundo de Servlets, é exclusivo do cliente
		* Pouco usado. Isto porque normalmente se usa o objeto HttpSession para guardar dados do cliente
		* Não tem pool de conexão


- ### 03 - Integração do JPA com Pool e DataSource [↑](#aulas)
	
   * **Configuração**
   		* Não configurar os dados de conexão no **```persistence.xml```** (JPA). O EJB Container irá disponibilizar um serviço para realizar a conexão.
		* Uma conexão é feita através de um driver connector, portanto é preciso registrar o driver do banco MySQL como módulo no servidor de aplicação, este módulo consiste de um arquivo XML e um JAR do connector.
		* Internamente o servidor de aplicação organiza seus módulos em pacotes. Dentro da pasta modules/com criar uma nova pasta mysql e dentro dela uma pasta main. Dentro da pasta main colocar o arquivo XML e o JAR.
		* No arquivo **```standalone.xml```** informar ao servidor de aplicação o módulo que representa um driver connector, isso é feito no elemento **```<drivers>```**.
		* A configuração do driver refere-se ao módulo definido anteriormente e fornece um nome para esse driver, além de especificar o nome da classe.
		```xml
			<driver name="com.mysql" module="com.mysql">
			    <xa-datasource-class>
				com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
			    </xa-datasource-class>
			</driver>
		```
		* Configurar o componente que no JavaEE chamamos de _DataSource_. Em uma aplicação mais robusta, é boa prática utilizar um _pool_ de conexões. Cabe ao _pool_ gerenciar e verificar as conexões disponíveis. Como existem várias implementações de _pool_ no mercado, o JavaEE define um padrão que se chama _DataSource_. Podemos dizer de maneira simplificada que um _DataSource_ é a interface do _pool_ de conexões. Dentro da _tag_ **```<datasources>```**, além da quantidade de conexões para o _pool_, também são configurados os dados da conexão com o banco e o _driver_ responsável. 
		```xml
			<datasource jndi-name="java:/livrariaDS" pool-name="livrariaDS" enabled="true" use-java-context="true">
			    <connection-url>jdbc:mysql://localhost:3306/livraria</connection-url>
			    <driver>com.mysql</driver>
			    <pool>
				<min-pool-size>10</min-pool-size>
				<max-pool-size>100</max-pool-size>
				<prefill>true</prefill>
			    </pool>
			    <security>
				<user-name>root</user-name>
				<password></password>
			    </security>
			</datasource>
		```
		* No **```persistence.xml```** configurar o endereço do serviço, definido no atributo **```jndi-name```** do **```datasource```**. Para isso, existe a configuração **```<jta-data-source>```**.
		```xml
			<jta-data-source>java:/livrariaDS</jta-data-source>
		```
   * **@PersistenceContext**
		* Quando injetamos um EntityManager não podemos utilizar a anotação @Inject. Nesse caso, o Contexts and Dependency Injection (CDI), outra especificação com o foco na injeção de dependência, buscaria o EntityManager. No entanto não encontraria o objeto e causaria uma exceção. Como o EJB Container administrará o JPA, é preciso usar uma anotação especifica do mundo EJB, nesse caso @PersistenceContext.


- ### 04 - Gerenciamento de Transações com JTA [↑](#aulas)


- ###  05 - Lidando com Exceções [↑](#aulas)

  * **EJBTransactionRolledbackException**
  		* Causada por System Exception.
  
  * **System Exception**
		* Unchecked
		* Normalmente exceções de infra-estrutura
		* Rollback
		* Invalida o Session Bean e tira ele do Pool de objetos
     
   * **Application Exception**
		* Checked
		* Relacionada ao domínio
		* Não causa rollback
		* Não invalida o Session Bean
     
   * **@ApplicationException**
   		* Reconfigura o padrão para Application Exceptions.

- ### 06 - Novos serviços com Interceptadores [↑](#aulas)

   * @AroundInvoke
     * Anotação para informar o método interceptador
     * Método interceptador retorna Object
     * Método recebe como parâmetro InvocationContext, este contém informações do método interceptado e o método proceed que prossegue com a execução do método interceptado e retorna o mesmo que ele
     
   * @Interceptors
     * Anotação para informar qual classe terá seus métodos interceptados, ou qual método.
     * Recebe um Array de classes interceptadoras
     * A configuração pode ser feita via xml, melhor nos casos onde o interceptador é utilizado em várias classes

- ### 07 - Integração com Web Services [↑](#aulas)

- ### 08 - Agendamento e EAR [↑](#aulas)
