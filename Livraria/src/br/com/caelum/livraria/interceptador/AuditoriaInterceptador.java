package br.com.caelum.livraria.interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class AuditoriaInterceptador {

	  @AroundInvoke
	  public Object audita(InvocationContext context) throws Exception {

		  	System.out.println("ANT InvocationContext - " + context.getTarget().getClass().getSimpleName() +" / "+context.getMethod().getName() );
			
			Object objeto = context.proceed();
			
			System.out.println("PÓS InvocationContext - " + context.getTarget().getClass().getSimpleName() +" / "+context.getMethod().getName() );
			
		    //System.out.println(context.getTarget().getClass().getSimpleName() +" / "+context.getMethod().getName() + " - Tempo gasto:  " + (System.currentTimeMillis() - millis));
			
			return objeto;
	      
	  }
	  
	}
