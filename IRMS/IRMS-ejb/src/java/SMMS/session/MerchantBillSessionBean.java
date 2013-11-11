/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.BillEntity;
import SMMS.entity.ContractEntity;
import SMMS.entity.OutletTransactionEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.joda.time.DateTime;

/**
 *
 * @author Cookie
 */
@Stateless
@LocalBean
public class MerchantBillSessionBean implements MerchantBillSessionBeanRemote {

    @EJB
    private ContractSessionBean contractSessionBean;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    @Resource
    private SessionContext ctx;
    private BillEntity bill = new BillEntity();
    private ContractEntity contract;

    public MerchantBillSessionBean() {
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public void persist(Object object) {
        em.persist(object);
    }

    //onetime bill would be overdue  one minute will be set to overdue
    @Override
    public void createOverDueTimers(Date overdue) {
        System.out.println("in session bean create timers");
        TimerService timerService = ctx.getTimerService();

        TimerConfig config = new TimerConfig("setOverdue", true);
        Timer timer = (Timer) timerService.createSingleActionTimer(overdue, config);
        System.out.println("in session bean test" + timer.getInfo().toString());
    }

    //one time, bill would be set at that contract start date 4 minutes will be set to active
    @Override
    public void createActiveTimers(Date startDate) {
        System.out.println("in session bean create timers");
        TimerService timerService = ctx.getTimerService();
        TimerConfig config = new TimerConfig("setActive", true);
        Timer timer = (Timer) timerService.createSingleActionTimer(startDate, config);
        System.out.println("in session bean test" + timer.getInfo().toString());
    }

//    // generate
//    public void createMonthlyBillTimers(Date startdate) {
//        //method1
//        DateTime as = new DateTime(startdate);
//        DateTime plusone = as.plusMonths(1);
//        System.out.println("in creating monthsly bills timers" + plusone);
//        
//        //method 2
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(startdate);
//        cal.add(Calendar.MONTH, 1);
//        startdate=cal.getTime();
//        System.out.println("in setting due date" + startdate);
//        
//        System.out.println("in session bean create timers");
//        TimerService timerService = ctx.getTimerService();             
//        TimerConfig config = new TimerConfig("addBillItem", true);
//        Timer timer = (Timer) timerService.createIntervalTimer(startdate, 1000*60*60*24*30, config);
//        System.out.println("in session bean test" + timer.getInfo().toString());
//    }
//    
    @Timeout
    @Override
    public void handleTimeout(Timer timer) throws ExistException {

        System.out.println("in handle timeout test");
        if (timer.getInfo().toString().equals("setOverdue")) {
            System.err.println("in setting overdue time lah ahahah");
            System.err.println("in setting overdue time lah ahahah");
            System.err.println("in setting overdue time lah ahahah");
            System.err.println("in setting overdue time lah ahahah");
            System.err.println("in setting overdue time lah ahahah");
        }

        if (timer.getInfo().toString().equals("setActive")) {
            System.out.println("in setting ACTIVE time ah lah ahahah");
            System.out.println("in setting ACTIVE time ah lah ahahah");
            System.out.println("in setting ACTIVE time ah lah ahahah");
            System.out.println("in setting ACTIVE time ah lah ahahah");
            System.out.println("in setting ACTIVE time ah lah ahahah" + contract.getContractId());
            contract.setStatus("newApproved");
            contractSessionBean.updateContract(contract);
            System.err.println("the current status is " + contract.getStatus());
        }



//        }
    }

    @Override
    public void cancelTimers() {
        TimerService timerService = ctx.getTimerService();
        Collection timers = timerService.getTimers();
        for (Object obj : timers) {
            Timer timer = (Timer) obj;
            if (timer.getInfo().toString().equals(obj)) {
                timer.cancel();
            }
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public BillEntity addBill(BillEntity bill) {
//        BillEntity thisbill = bill;
//        thisbill.getBillDate().setYear(bill.getBillDate().getYear() - 1990);
//        thisbill.getBillDate().setMonth(bill.getBillDate().getMonth() - 1);
//        
//        thisbill.getDueDate().setYear(bill.getDueDate().getYear() - 1990);
//        thisbill.getDueDate().setMonth(bill.getDueDate().getMonth() - 1);

        em.persist(bill);
        return bill;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void removeBill(BillEntity bill) throws ExistException {
        if (bill == null) {
            throw new ExistException("bill doesn't exist!");
        }
        if (bill.getBillStatus() == "paid") {
            throw new ExistException("bill cannot be removed because it has been paid!");
        }
        em.remove(bill);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public BillEntity updateBill(BillEntity bill) throws ExistException {
       
        em.merge(bill);
        return bill;
    }

    @Override
    public List<BillEntity> getBillByContract(Long contractId) {
        System.err.println("in get bill by contract session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<BillEntity>();
        for (Object o : q.getResultList()) {
            BillEntity m = (BillEntity) o;
            if (m.getContract().getContractId() == contractId) {
                TransactionList.add(m);
            }
        }
        System.err.println("in get bill by contract sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }

    @Override
    public List<BillEntity> getBillByMerchant(String merchantId) {
        System.err.println("in get bill by merchant session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<BillEntity>();
        for (Object o : q.getResultList()) {
            BillEntity m = (BillEntity) o;
            if (m.getContract().getMerchant().getMerchantEmail() == merchantId) {
                TransactionList.add(m);
            }
        }
        System.err.println("in get bill by merchant sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }

    @Override
    public List<BillEntity> getAvailableBills() {
        System.err.println("in get bill by merchant session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<BillEntity>();
        for (Object o : q.getResultList()) {
            BillEntity m = (BillEntity) o;
            if (m.getBillStatus() == "available") {
                TransactionList.add(m);
            }
        }
        System.err.println("in get bill by merchant sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }

    @Override
    public List<BillEntity> getUnpaidBills() {
        System.err.println("in get bill by merchant session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<BillEntity>();
        for (Object o : q.getResultList()) {
            BillEntity m = (BillEntity) o;
            if (m.getBillStatus() == "unpaid") {
                TransactionList.add(m);
            }
        }
        System.err.println("in get bill by merchant sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }

    @Override
    public List<BillEntity> getOverdueBills() {
        System.err.println("in get bill by merchant session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<BillEntity>();
        for (Object o : q.getResultList()) {
            BillEntity m = (BillEntity) o;
            if (m.getBillStatus() == "overdue") {
                TransactionList.add(m);
            }
        }
        System.err.println("in get bill by merchant sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }

    @Override
    public List<BillEntity> getUnpaidBillsByMerchant(String merchantId) {
        System.err.println("in get bill by merchant session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<BillEntity>();
        for (Object o : q.getResultList()) {
            BillEntity m = (BillEntity) o;
            if (m.getContract().getMerchant().getMerchantEmail() == null ? merchantId == null : m.getContract().getMerchant().getMerchantEmail().equals(merchantId)) {
                if (m.getBillStatus().equals("unpaid") || m.getBillStatus().equals("overdue")) {
                    TransactionList.add(m);
                }
            }
        }
        System.err.println("in get bill by merchant sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }

    @Override
    public List<BillEntity> getPaidBillsByMerchant(String merchantId) {
       System.err.println("in get bill by merchant session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<BillEntity>();
        for (Object o : q.getResultList()) {
            BillEntity m = (BillEntity) o;
            if (m.getContract().getMerchant().getMerchantEmail() == null ? merchantId == null : m.getContract().getMerchant().getMerchantEmail().equals(merchantId)) {
                if (m.getBillStatus().equals("paid")) {
                    TransactionList.add(m);
                }
            }
        }
        System.err.println("in get bill by merchant sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }

    @Override
    public List<BillEntity> getAllBills() {
        System.err.println("in get bill by merchant session bean");
        Query q = em.createQuery("SELECT m FROM BillEntity m");
        List TransactionList = new ArrayList<BillEntity>();
        for (Object o : q.getResultList()) {
            BillEntity m = (BillEntity) o;
            TransactionList.add(m);
        }
        System.err.println("in get bill by merchant sessionbean: Transaction List size=" + TransactionList.size());
        return TransactionList;
    }

    @Override
    public BillEntity getBillById(Long billId) throws ExistException {
        bill = em.find(BillEntity.class, billId);
        if (bill == null) {
            throw new ExistException("Bill does not exist!");
        }
        return bill;
    }

    @Override
    public BillEntity getBill() {
        return bill;
    }

    @Override
    public void setBill(BillEntity bill) {
        this.bill = bill;
    }

    @Override
    public ContractEntity getContract() {
        return contract;
    }

    @Override
    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }
}
