package br.com.caelum.livraria.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.AutorDao;
import br.com.caelum.livraria.modelo.Autor;

@Stateless
public class AutorService {

    @Inject
    AutorDao dao;

    public void salva(Autor autor) {
    	System.out.println("AutorService_salva");
        this.dao.salva(autor);
    }

    public List<Autor> todosAutores() {
    	System.out.println("AutorService_todosAutores");
        return this.dao.todosAutores();
    }
    
}