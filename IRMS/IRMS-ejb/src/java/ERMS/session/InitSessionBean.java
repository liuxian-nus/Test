/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ser3na
 */
@Singleton
@LocalBean
@Startup
public class InitSessionBean {
    @PersistenceContext
    private EntityManager em;
    
    

    @PostConstruct
    public void init()
    {
        System.err.println("InitSessionBean.init");
    }


}
