/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.AccountEntity;
import FBMS.entity.InvoiceEntity;
import FBMS.entity.OrderEntity;
import FBMS.entity.ReceiptEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    ReceiptEntity re;
    AccountEntity ae;

    public BillingSessionBean() {
    }

    public InvoiceEntity createInvoice(Long orderId, Date date) {
        oe = em.find(OrderEntity.class, orderId);
        if (oe != null) {
            ie = new InvoiceEntity();
            ie.setInvoiceDate(date);
            ie.setOrder(oe);
            System.out.println("BillingSessioBean: createInvoice: The order invoiceDate and associated order "
                    + "has been found" + orderId);
            Double amount = oe.getSalePrice();
            ie.setInvoiceValue(amount);
            System.out.println("BillingSessioBean: createInvoice: Invoice value has been determined to be " + ie.getInvoiceValue());


            oe.setInvoice(ie);
            em.persist(ie);
            oe.setStatus("Invoiced");
            em.merge(oe);
            System.out.println("BillingSessioBean: createInvoice: Invoice has been generated " + ie.getInvoiceId());
            return ie;
        } else {
            System.out.println("BillingSessioBean: createInvoice: The order does not exist! " + orderId);
            return null;
        }
    }

    public ReceiptEntity createReceipt(Long invoiceId, Date date) {
        ie = em.find(InvoiceEntity.class, invoiceId);
        if (ie != null) {
            System.out.println("BillingSessioBean: createReceipt: The invoice has been found! " + invoiceId);
            re = new ReceiptEntity();
            re.setReceiptDate(date);
            re.setInvoice(ie);

            System.out.println("BillingSessioBean: createReceipt: The receipt has been set " + re.getId());

            ie.setReceipt(re);
            System.out.println("BillingSessioBean: createReceipt: The invoice has been set " + ie.getInvoiceId());

            em.persist(re);
            em.merge(ie);
            System.out.println("BillingSessioBean: createReceipt: The receipt and invoice has been updated! " + re.getId() + ie.getInvoiceId());

            return re;
        } else {
            System.out.println("BillingSessioBean: createReceipt: The invoice does not exist! " + invoiceId);
            return null;
        }
    }

    public Double postPayment(double amount, int accountId) //make payment
    {
        ae = em.find(AccountEntity.class, accountId);
        if (ae != null) {
            System.out.println("BillingSessionBean:postPayment: the account has been found! " + ae.getId());
            Double currentAmount = ae.getAccountAmount() - amount;
            ae.setAccountAmount(currentAmount);
            em.merge(ae);
            System.out.println("BillingSessionBean:postPayment: the account has been reset! "
                    + ae.getId() + ae.getAccountName() + "by " + ae.getAccountAmount());
            return currentAmount;
        } else {
            System.out.println("BillingSessionBean:postPayment: the account does not exist! " + ae.getId());
            return null;
        }
    }

    public Double receivePayment(double amount, int accountId) //receive payment
    {
        ae = em.find(AccountEntity.class, accountId);
        if (ae != null) {
            System.out.println("BillingSessionBean:receivePayment: the account has been found! " + ae.getId());
            Double currentAmount = ae.getAccountAmount() + amount;
            ae.setAccountAmount(currentAmount);
            em.merge(ae);
            System.out.println("BillingSessionBean:receivePayment: the account has been reset! "
                    + ae.getId() + ae.getAccountName() + "by " + ae.getAccountAmount());
            return currentAmount;
        } else {
            System.out.println("BillingSessionBean:receivePayment: the account does not exist! " + ae.getId());
            return null;
        }
    }

    public boolean createAccount(String accountName, Double accountAmount) {
        ae = new AccountEntity();
        ae.setAccountAmount(accountAmount);
        ae.setAccountName(accountName);
        em.persist(ae);

        System.out.println("BillingSessionBean:createAccount: the account has been created! " + ae.getId());
        return true;

    }
    
    public boolean addAccount(AccountEntity account)
    {
        em.persist(account);
        System.out.println("BillingSessionBean:createAccount: the account has been created! " + account.getId());
        return true;
    }

    public AccountEntity viewAccount(int accountId) {
        ae = em.find(AccountEntity.class, accountId);
        if (ae != null) {
            System.out.println("BillingSessionBean:viewAccount: the account has been found! "
                    + ae.getAccountName() + ae.getId());
            return ae;
        } else {
            System.out.println("BillingSessionBean:viewAccount: the account does not exist! "
                    + accountId);
            return null;
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }

    public List<AccountEntity> getReceivableAccounts() {
        Query q = em.createQuery("SELECT m FROM AccountEntity m");
        List ContractList = new ArrayList<AccountEntity>();
        for (Object o : q.getResultList()) {
            AccountEntity m = (AccountEntity) o;
            if ("Receivable".equals(m.getAccountName())) {
                ContractList.add(m);
            }
        }
        return ContractList;
    }

    public List<AccountEntity> getCashAccounts() {
        Query q = em.createQuery("SELECT m FROM AccountEntity m");
        List ContractList = new ArrayList<AccountEntity>();
        for (Object o : q.getResultList()) {
            AccountEntity m = (AccountEntity) o;
            if ("Cash".equals(m.getAccountName())) {
                ContractList.add(m);
            }
        }
        return ContractList;
    }

    public List<AccountEntity> getAllAccounts() {
        Query q = em.createQuery("SELECT m FROM AccountEntity m");
        List ContractList = new ArrayList<AccountEntity>();
        for (Object o : q.getResultList()) {
            AccountEntity m = (AccountEntity) o;

            ContractList.add(m);

        }
        return ContractList;
    }
}
