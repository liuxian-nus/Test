/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.BillEntity;
import SMMS.entity.BillItemEntity;
import SMMS.entity.ContractEntity;
import SMMS.entity.OutletTransactionEntity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
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
    private OutletSessionBean outletSessionBean;
    @EJB
    private ContractSessionBean contractSessionBean;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    @Resource
    private SessionContext ctx;
    private BillEntity bill = new BillEntity();
    private ContractEntity contract;
    private BillItemEntity item;
    private Date currentDate = new Date();

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

    public void createTerminationTimers(Date endDate) { //when it comes to end date
        System.out.println("in session bean create timers");
        TimerService timerService = ctx.getTimerService();
        TimerConfig config = new TimerConfig("termination", true);
        Timer timer = (Timer) timerService.createSingleActionTimer(endDate, config);
        System.out.println("in session bean test" + timer.getInfo().toString());
    }

    public void createMonthlyTimers(Date startDate) { // create monthly bill lalala 
        System.out.println("in session bean create timers");
        TimerService timerService = ctx.getTimerService();
        TimerConfig config = new TimerConfig("monthly", true);
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
            System.err.println("in setting overdue time lah ahahah!!!!!!!!!!!!!!");
            System.err.println("in setting overdue time lah ahahah" + bill.getBillId());
            bill.setBillStatus("overdue");
            updateBill(bill);
            System.err.println("after setting product lalala" + bill.getBillStatus());
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

        if (timer.getInfo().toString().equals("termination")) {
            System.err.println("in setting overdue time lah ahahah");
            System.err.println("in setting overdue time lah ahahah");
            System.err.println("in setting overdue time lah ahahah");
            System.err.println("in setting overdue time lah ahahah!!!!!!!!!!!!!!");
            System.err.println("in setting overdue time lah ahahah" + contract.getContractId());
            addTerminationBill(contract);
            System.err.println("after setting product lalala" + bill.getBillId());


            bill.setBillStatus("overdue");
            updateBill(bill);
            System.err.println("after setting product lalala" + bill.getBillStatus());
        }

        if (timer.getInfo().toString().equals("monthly")) {
            System.err.println("in setting overdue time lah ahahah");
            System.err.println("in setting overdue time lah ahahah");
            System.err.println("in setting overdue time lah ahahah");
            System.err.println("in setting overdue time lah ahahah!!!!!!!!!!!!!!");
            System.err.println("in setting overdue time lah ahahah" + contract.getContractId());
            addTerminationBill(contract);
            System.err.println("after setting product lalala" + bill.getBillId());
        }

//        }
    }

    public void addTerminationBill(ContractEntity contract) throws ExistException { //until today
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 8);  //here expire after 2 minutes
        Date dueDate = cal.getTime();
        System.out.println("in setting due date" + dueDate);

        Date today = cal.getTime();

        List<BillItemEntity> items = new ArrayList<BillItemEntity>();
        double total = 0.0;

        bill.setBillStatus("available");
        bill.setBillType("Termination");
        bill.setBillDate(today);
        bill.setContract(contract);
        bill.setDueDate(dueDate);
        addBill(bill); //persisting the bill first lah

        item = new BillItemEntity();
        item.setType("Additional Administration fee");
        item.setAmount(2304.00);
        item.setBill(bill);
        addBillItem(item); // persisting the additional admin charge charge
        items.add(item);
        total = total + item.getAmount();
        System.err.println("here in adding first billitem" + total + item.getAmount());


        BillItemEntity item2 = new BillItemEntity();
        item2.setType("monthsly bill");
        item2.setBill(bill);
        item2.setAmount(calculateMonthRate(contract));
        addBillItem(item2);
        items.add(item2);
        total = total + item2.getAmount();
        System.err.println("here in adding first billitem" + total + item2.getAmount());

        BillItemEntity item3 = new BillItemEntity();
        item3.setType("commission fee");
        item3.setBill(bill);
        item3.setAmount(calculateCommission(contract));
        addBillItem(item3);
        items.add(item3);
        total = total + item3.getAmount();
        System.err.println("here in adding first billitem" + total + item3.getAmount());

        bill.setBillItem(items);
        bill.setBillAmount(total);
        updateBill(bill);

        setBill(bill);
        System.err.println("before creating timer" + dueDate);

    }

    public double calculateMonthRate(ContractEntity contract) {  //if monthly: check if the start date is now   if termination: to first day of year
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 0, 1);
        Date first = cal.getTime();

        double monthAmount = 0.0;
        double monthly = contract.getLast().getEventMonthRate();
        int month = currentDate.getMonth() + 2;  // asume only after first year can start to early terminate
        if (contract.getLast().getEventStartDate().after(first)) {
            int currentMonth = currentDate.getMonth() + 1;
            monthAmount = currentMonth * monthly;
            return monthAmount;
        } else {
            System.err.println("!!!!!!!!!!!!!!!!! what is the month left?" + month);
            monthAmount = month * monthly;
            System.err.println("!!!!!!!!!!!!!!!!! what is the month left?" + monthAmount);
            return monthAmount;
        }
    }

    public double calculateCommission(ContractEntity contract) {//if termination calcaulte to first year, if monthly calculate to first and chel

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 0, 1);
        Date first = cal.getTime();
        System.err.println("here now in getting first date" + first);

        double totalcommission = 0.0;
        double rate = contract.getLast().getEventCommissionRate();
        System.err.println("what is the rate" + rate);
        List<OutletTransactionEntity> transactions = outletSessionBean.getTransactions(contract.getOutlet().getOutletId());
        Iterator<OutletTransactionEntity> itr = transactions.iterator();
        while (itr.hasNext()) {
            OutletTransactionEntity current = itr.next();
            if (contract.getLast().getEventStartDate().after(first)) {
                if (current.getTransactionDate().after(contract.getLast().getEventStartDate())) {
                    totalcommission = totalcommission + current.getTransactionAmount();
                    System.out.println("Here we are in total commission" + totalcommission);
                } // if the contract starts after the first year
            } else {
                //if its after this first year, only to this year
                if (current.getTransactionDate().after(first)) {
                    totalcommission = totalcommission + current.getTransactionAmount();
                    System.out.println("Here we are in total commission" + totalcommission);
                }
            } //if the contract starts before the first date. lalala 
        }
        totalcommission = totalcommission * rate;
        System.out.println("here in getting calculate" + totalcommission);
        return totalcommission;
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
    public BillItemEntity addBillItem(BillItemEntity item) {
        em.persist(item);
        return item;
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

    public BillItemEntity updateBillItem(BillItemEntity item) {
        em.merge(item);
        return item;
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

    public BillItemEntity addBillItemToBill(Long billId, Long itemId) throws ExistException {
        bill = em.find(BillEntity.class, billId);
        item = em.find(BillItemEntity.class, itemId);
        if (item == null) {
            throw new ExistException("ContractSessionBean-->ExistException-->Invalid bill item!");
        }
        bill.addBillItem(item);
        em.merge(bill);
        System.out.println("BillSessionBean--> " + bill.getBillId() + " new include new service " + bill.getBillItem().size());
        return item;
    }

    @Override
    public BillEntity getBillById(Long billId) throws ExistException {
        bill = em.find(BillEntity.class, billId);
        if (bill == null) {
            throw new ExistException("Bill does not exist!");
        }
        return bill;
    }

    public BillItemEntity getItem() {
        return item;
    }

    public void setItem(BillItemEntity item) {
        this.item = item;
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

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}
