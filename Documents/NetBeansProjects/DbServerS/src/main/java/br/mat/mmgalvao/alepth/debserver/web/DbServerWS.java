/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mat.mmgalvao.alepth.debserver.web;

import br.mat.mmgalvao.aleph.dbserver.interfaces.DbServerLocal;
import br.mat.mmgalvao.common.aleph.DataSet;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;


/**
 *
 * @author Mauricio
 */
@WebService(serviceName = "DbServerWS")
@Stateless()
public class DbServerWS {
    @EJB(name="ejb/DbServer")
    DbServerLocal dbserver;
    /**
     * Web service operation
     * @param resourceName
     * @param query
     * @param params
     * @return 
     */
    @WebMethod(operationName = "execute")
    public DataSet execute(@WebParam(name = "Resource") String resourceName, @WebParam(name = "query") String query, @WebParam(name = "params") Object[] params) {
      
                return dbserver.execute(resourceName, query, params);

      
        
    }

    /**
     * This is a sample web service operation
     */
   
}
