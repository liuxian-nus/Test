/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.AccountEntity;
import FBMS.entity.InvoiceEntity;
import FBMS.entity.ReceiptEntity;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface BillingSessionBeanRemote {

    boolean createAccount(String accountName, Double accountAmount);

    InvoiceEntity createInvoice(Long orderId, Date date);

    ReceiptEntity createReceipt(Long invoiceId, Date date);

    void persist(Object object);

    Double postPayment(double amount, Long accountId);

    Double receivePayment(double amount, Long accountId);

    AccountEntity viewAccount(Long accountId);
    
}
