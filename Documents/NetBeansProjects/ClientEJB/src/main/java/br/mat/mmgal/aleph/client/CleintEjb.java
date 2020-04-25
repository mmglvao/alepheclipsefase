/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mat.mmgal.aleph.client;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Mauricio
 */
public class CleintEjb {
    
 
    private static Object lookupCalculatorEJB(String host,String jndi) throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,  "org.wildfly.naming.client.WildFlyInitialContextFactory");
                jndiProperties.put(Context.PROVIDER_URL,"http-remitialContextFactoryoting://"+host+":8080");
        final Context context = new InitialContext(jndiProperties);
 
        return context.lookup("ejb:/"+jndi);
    }
    public static void main(String args[]) throws NamingException{
        System.out.println(lookupCalculatorEJB("localhost","DbServerS-1.0/DbServer"));
    }
}
