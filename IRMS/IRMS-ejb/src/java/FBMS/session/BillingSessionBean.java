/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.InvoiceEntity;
import FBMS.entity.OrderEntity;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class BillingSessionBean {
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    InvoiceEntity ie;
    OrderEntity oe;
    
    
    public BillingSessionBean(){}
    
    

    public InvoiceEntity createInvoice(Long orderId,Date date)
    {
        oe = em.find(OrderEntity.class, orderId);
        if(oe!=null)
        {
            ie = new InvoiceEntity();
            ie.setInvoiceDate(date);
            ie.setOrder(oe);
            System.out.println("BillingSessioBean: createInvoice: The order invoiceDate and associated order "
                    + "has been found"+orderId);
            Double amount = oe.getSalePrice();
            ie.setInvoiceValue(amount);
            System.out.println("BillingSessioBean: createInvoice: Invoice value has been determined to be "+ie.getInvoiceValue());
            
            
            oe.setInvoice(ie);
            em.persist(ie);
            em.merge(oe);
            System.out.println("BillingSessioBean: createInvoice: Invoice has been generated "+ie.getInvoiceId());
            return ie;
        }
        else
        {
            System.out.println("BillingSessioBean: createInvoice: The order does not exist! "+orderId);
            return null;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

}
