package br.com.caelum.livraria.interceptador;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptador {

	@AroundInvoke
	public Object intercepta(InvocationContext context) throws Exception {

		//long millis = System.currentTimeMillis();
		
		System.out.println("ANT LogInterceptador - " + context.getTarget().getClass().getSimpleName() +" / "+context.getMethod().getName() );
		
		Object objeto = context.proceed();
		
		System.out.println("PÓS LogInterceptador - " + context.getTarget().getClass().getSimpleName() +" / "+context.getMethod().getName() );
		
	    //System.out.println(context.getTarget().getClass().getSimpleName() +" / "+context.getMethod().getName() + " - Tempo gasto:  " + (System.currentTimeMillis() - millis));
		
		return objeto;
		
    }
		
}
