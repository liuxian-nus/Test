/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import SMMS.entity.ContracteventEntity;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cookie///lalalalalalal
 */
@Stateless
@LocalBean
public class ContracteventSessionBean implements ContracteventSessionBeanRemote {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;

    @Override
    public void persist(Object object) {
        em.persist(object);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    ContracteventEntity newevent = new ContracteventEntity();

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public ContracteventEntity addContractevent(ContracteventEntity contractevent) {
        System.out.println("ContractEvent Session bean: add contractevent called");
//        ContracteventEntity thisevent = contractevent;
//        thisevent.getEventStartDate().setYear(contractevent.getEventStartDate().getYear() - 1990);
//        thisevent.getEventStartDate().setMonth(contractevent.getEventStartDate().getMonth() - 1);
//
//        thisevent.getEventEndDate().setYear(contractevent.getEventEndDate().getYear() - 1990);
//        thisevent.getEventEndDate().setMonth(contractevent.getEventEndDate().getMonth() - 1);
//
//        thisevent.getEventTime().setYear(contractevent.getEventTime().getYear() - 1990);
//        thisevent.getEventTime().setMonth(contractevent.getEventTime().getMonth() - 1);

        em.persist(contractevent);
        em.flush();
        return contractevent;
    }

    @Override
    public boolean updateContractEvent(ContracteventEntity cevent) {

//        ContracteventEntity thisevent = cevent;
//        thisevent.getEventStartDate().setYear(cevent.getEventStartDate().getYear() - 1990);
//        thisevent.getEventStartDate().setMonth(cevent.getEventStartDate().getMonth() - 1);
//
//        thisevent.getEventEndDate().setYear(cevent.getEventEndDate().getYear() - 1990);
//        thisevent.getEventEndDate().setMonth(cevent.getEventEndDate().getMonth() - 1);
//
//        thisevent.getEventTime().setYear(cevent.getEventTime().getYear() - 1990);
//        thisevent.getEventTime().setMonth(cevent.getEventTime().getMonth() - 1);

        em.merge(cevent);
        System.out.println("CEventSessionBean: outlet " + cevent.getContracteventId() + " is successfully updated");
        return true;
    }
}
