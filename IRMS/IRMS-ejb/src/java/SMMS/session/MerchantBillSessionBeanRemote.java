/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.session;

import Exception.ExistException;
import SMMS.entity.BillEntity;
import SMMS.entity.ContractEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface MerchantBillSessionBeanRemote {

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    BillEntity addBill(BillEntity bill);

    void cancelTimers();

    //one time, bill would be set at that contract start date 4 minutes will be set to active
    void createActiveTimers(Date startDate);

    //onetime bill would be overdue  one minute will be set to overdue
    void createOverDueTimers(Date overdue);

    List<BillEntity> getAllBills();

    List<BillEntity> getAvailableBills();

    BillEntity getBill();

    List<BillEntity> getBillByContract(Long contractId);

    BillEntity getBillById(Long billId) throws ExistException;

    List<BillEntity> getBillByMerchant(String merchantId);

    ContractEntity getContract();

    List<BillEntity> getOverdueBills();

    List<BillEntity> getPaidBillsByMerchant(String merchantId);

    List<BillEntity> getUnpaidBills();

    List<BillEntity> getUnpaidBillsByMerchant(String merchantId);

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
    void handleTimeout(Timer timer) throws ExistException;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    void persist(Object object);

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    void removeBill(BillEntity bill) throws ExistException;

    void setBill(BillEntity bill);

    void setContract(ContractEntity contract);

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    BillEntity updateBill(BillEntity bill) throws ExistException;
    
}
