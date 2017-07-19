# Curso Alura - EJB: O poder da Java EE / Nível Avançado

## Apresentação do projeto

A aplicação livraria usa JSF e Primefaces para definir a interface. 
Há uma página de login: o login é admin a senha é pass. 
Após efetuado o login somos redirecionados para a página principal de aplicação. 
Trata-se de um cadastro de livros e autores, com abas para cada funcionalidade além do logout. 

## Aulas

01 - Introdução aos Enterprise Java Beans
> Inversão de controle / Injeção de dependências pelo EJB

02 - Ciclo de vida dos Session Beans

03 - Integração do JPA com Pool e DataSource

04 - Gerenciamento de Transações com JTA

05 - Lidando com Exceções

> * EJBTransactionRolledbackException causada por System Exception.
> * System Exception
>> * Unchecked
>> * Normalmente exceções de infra-estrutura
>> * Rollback
>> * Invalida o Session Bean e tira ele do Pool de objetos
> * Application Exception
>> * Checked
>> * Relacionada ao domínio
>> * Não causa rollback
>> * Não invalida o Session Bean
> * @ApplicationException reconfigura o padrão para Application Exceptions.

06 - Novos serviços com Interceptadores

07 - Integração com Web Services

08 - Agendamento e EAR
