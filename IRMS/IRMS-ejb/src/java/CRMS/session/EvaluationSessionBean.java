/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.entity.PromotionEntity;
import CRMS.entity.RFMModelEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Diana Wang
 */
@Stateless
@LocalBean
public class EvaluationSessionBean {

    @EJB
    MemberSessionBean memberSessionBean;
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    MemberTransactionEntity mte;
    MemberEntity member;
    RFMModelEntity rfmModel;

    //1. JSF Done!
    public double calculateSizeOfWallet(String memberEmail) {
        System.out.println("calculateSizeOfWallet");
        double sizeOfWallet;
        Query q = em.createQuery("SELECT m FROM MemberTransactionEntity m");
        List<MemberTransactionEntity> allTrans = q.getResultList();
        Iterator<MemberTransactionEntity> itr = allTrans.iterator();
        List<MemberTransactionEntity> resultList = new ArrayList();


        while (itr.hasNext()) {
            MemberTransactionEntity current = itr.next();
            if (current.getMemberEmail().equalsIgnoreCase(memberEmail)) {
                resultList.add(current);
            }
        }

        Iterator<MemberTransactionEntity> itr2 = resultList.iterator();
        Double memberTotal = 0.00;

        if (!resultList.isEmpty()) {
            while (itr2.hasNext()) {
                MemberTransactionEntity current2 = itr2.next();
                memberTotal += current2.getMtAmount();
            }
        }
        sizeOfWallet = memberTotal;
        if (sizeOfWallet != 0.0) {
            member = em.find(MemberEntity.class, memberEmail);
            member.setSizeOfWallet(sizeOfWallet);
            memberSessionBean.updateMember(member);
        }
        return sizeOfWallet;
    }

    //2. JSF Done!
    public double calculateShareOfWallet(String memberEmail, String mtDepartment) {
        double shareOfWallet = 0.00;
        System.out.println("calculateShareOfWallet");

        Query q = em.createQuery("SELECT m FROM MemberTransactionEntity m");
        List<MemberTransactionEntity> allTrans = q.getResultList();
        Iterator<MemberTransactionEntity> itr = allTrans.iterator();
        List<MemberTransactionEntity> resultList = new ArrayList();
        Double memberTotal = 0.00;
        Double dpmtMemberTotal = 0.00;

        while (itr.hasNext()) {
            MemberTransactionEntity current = itr.next();
            if (current.getMemberEmail().equals(memberEmail)) {
                memberTotal += current.getMtAmount();
                if (current.getMtDepartment().equalsIgnoreCase(mtDepartment)) {
                    dpmtMemberTotal += current.getMtAmount();
                }
            }
        }
        if (memberTotal != 0) {
            shareOfWallet = dpmtMemberTotal / memberTotal;
        }
        return shareOfWallet;
    }

    //3. RFMMedel
    public RFMModelEntity addRFMModel(RFMModelEntity rfmModel) {
        em.persist(rfmModel);
        return rfmModel;
    }
    //买一送一，不新建session bean了
    public List<RFMModelEntity> getAllRFMs() throws NoResultException {
        Query q = em.createQuery("SELECT m FROM RFMModelEntity m");
        return q.getResultList();
    }
    //Model number currently is 1 since only one model is available
    public boolean setRFMParameter(Double Recency, Double Frequency, Double Monetary, int ModelNumber) {
        boolean completed = false;
        RFMModelEntity current = em.find(RFMModelEntity.class, ModelNumber);
        if (current != null) {
            current.setFrequency(Frequency);
            current.setMonetary(Monetary);
            current.setRecency(Recency);
            System.out.println("set completed!");
            completed = true;
        }
        return completed;
    }

    public Integer calculateRFMValue(String memberEmail, int ModelNumber) throws ExistException {
        Integer RFMValue = 0;
        double f = 0.00;//frequency
        double r = 0.00;//recency
        double m = 0.00;//monetary
        RFMModelEntity model = getRFMModel(ModelNumber);
        System.out.println("calculateRFMValue");

        Query q = em.createQuery("SELECT m FROM MemberTransactionEntity m");
        List<MemberTransactionEntity> allTrans = q.getResultList();
        Iterator<MemberTransactionEntity> itr = allTrans.iterator();
        double memberMoneyTotal = 0.00;
        double moneyTotal = 0.00;
        Integer memberVisitTotal = 0;
        Integer visitTotal = 0;
//        boolean memberFrequent = false;
        Date memberLastVisitDate = new Date(1900, 0, 1);
        Date currentTransDate;

        //get a description statistics
        DescriptiveStatistics stats = new DescriptiveStatistics();

        while (itr.hasNext()) {
            MemberTransactionEntity current = itr.next();
            moneyTotal += current.getMtAmount();
            visitTotal += 1;

            //add values to a stats
            stats.addValue(current.getMtAmount());

            if (current.getMemberEmail().equalsIgnoreCase(memberEmail)) {
                memberMoneyTotal += current.getMtAmount();
                memberVisitTotal += 1;
                currentTransDate = current.getMtDate();
                if (currentTransDate.after(memberLastVisitDate)) {
                    memberLastVisitDate = currentTransDate;
                }
            }
        }

        //calculate m
        double memberAverageMoney = memberMoneyTotal / memberVisitTotal;

        if (memberAverageMoney >= stats.getPercentile(80)) {
            m = 5;
        }
        if (memberAverageMoney >= stats.getPercentile(60) && memberAverageMoney < stats.getPercentile(80)) {
            m = 4;
        }
        if (memberAverageMoney >= stats.getPercentile(40) && memberAverageMoney < stats.getPercentile(60)) {
            m = 3;
        }
        if (memberAverageMoney >= stats.getPercentile(20) && memberAverageMoney < stats.getPercentile(40)) {
            m = 2;
        }
        if (memberAverageMoney < stats.getPercentile(20)) {
            m = 1;
        }

        //calculate f
        if ((memberVisitTotal / visitTotal) >= 0.1) {
            f = 5;
        }
        if ((memberVisitTotal / visitTotal) >= 0.05 && (memberVisitTotal / visitTotal) < 0.1) {
            f = 4;
        }
        if ((memberVisitTotal / visitTotal) >= 0.01 && (memberVisitTotal / visitTotal) < 0.05) {
            f = 3;
        }
        if ((memberVisitTotal / visitTotal) >= 0.005 && (memberVisitTotal / visitTotal) < 0.01) {
            f = 2;
        }
        if ((memberVisitTotal / visitTotal) < 0.005) {
            f = 1;
        }

        //calculate r
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DAY_OF_YEAR, -10);//within 10 days
        Date dateR5 = currentDate.getTime();
        currentDate.add(Calendar.DAY_OF_YEAR, -10);//within 20 days
        Date dateR4 = currentDate.getTime();
        currentDate.add(Calendar.DAY_OF_YEAR, -10);//within 30 days
        Date dateR3 = currentDate.getTime();
        currentDate.add(Calendar.DAY_OF_YEAR, -10);//within 40 days
        Date dateR2 = currentDate.getTime();
        currentDate.add(Calendar.DAY_OF_YEAR, -10);//within 50 days
        Date dateR1 = currentDate.getTime();

        if (memberLastVisitDate.after(dateR5)) {
            r = 5;
        }
        if (memberLastVisitDate.after(dateR4) && (memberLastVisitDate.before(dateR5) || memberLastVisitDate.equals(dateR5))) {
            r = 4;
        }
        if (memberLastVisitDate.after(dateR3) && (memberLastVisitDate.before(dateR4) || memberLastVisitDate.equals(dateR4))) {
            r = 3;
        }
        if (memberLastVisitDate.after(dateR2) && (memberLastVisitDate.before(dateR3) || memberLastVisitDate.equals(dateR3))) {
            r = 2;
        }
        if ((memberLastVisitDate.before(dateR2) || memberLastVisitDate.equals(dateR2))) {
            r = 1;
        }

        double RFMValueAbsolute = model.getFrequency() * f + model.getMonetary() * m + model.getRecency() * r;
        double fullRFMValueAbsolute = model.getFrequency() * 5 + model.getMonetary() * 5 + model.getRecency() * 5;
        RFMValue = (int) (RFMValueAbsolute / fullRFMValueAbsolute) * 5;
        return RFMValue;
    }

    public double calculateCustLifeValue(String memberEmail) {
        double custLifeValue = 0.00;
        double discountRate = 0.05;
        double currentTransValue;
        double currentRealTransValue;
        Date today = new Date();
        int compoundedYear = 0;
        System.out.println("calculateCustLifeValue");

        Query q = em.createQuery("SELECT m FROM MemberTransactionEntity m");
        List<MemberTransactionEntity> allTrans = q.getResultList();
        Iterator<MemberTransactionEntity> itr = allTrans.iterator();

        while (itr.hasNext()) {
            MemberTransactionEntity current = itr.next();
            if (current.getMemberEmail().equalsIgnoreCase(memberEmail)) {
                currentTransValue = current.getMtAmount();
                compoundedYear = today.getYear() - current.getMtDate().getYear();//pay attention to "minus 1900"
                currentRealTransValue = currentTransValue * Math.pow(1 + discountRate, compoundedYear);
                custLifeValue += currentRealTransValue;
            }
        }
        return custLifeValue;
    }

    public List<MemberEntity> getTieredBasedOnRFM() throws ExistException {
        List<MemberEntity> tiered = new ArrayList();

        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List<MemberEntity> allMembers = q.getResultList();
        Iterator<MemberEntity> itr = allMembers.iterator();
        //get a description statistics
        DescriptiveStatistics stats = new DescriptiveStatistics();

        while (itr.hasNext()) {
            MemberEntity current = itr.next();
            int currentRFMValue = calculateRFMValue(current.getMemberEmail(), 1);
            stats.addValue(currentRFMValue);
        }

        int tieredValue = (int) stats.getPercentile(80);

        Query q2 = em.createQuery("SELECT m FROM MemberEntity m");
        List<MemberEntity> allMembers2 = q2.getResultList();
        Iterator<MemberEntity> itr2 = allMembers2.iterator();
        List<MemberEntity> resultList = new ArrayList();

        while (itr2.hasNext()) {
            MemberEntity current = itr.next();
            int currentRFMValue = calculateRFMValue(current.getMemberEmail(), 1);
            if (currentRFMValue >= tieredValue) {
                resultList.add(current);
            }
        }
        tiered = resultList;

        return tiered;
    }

    public List<MemberEntity> getTieredBasedOnCustLifeValue() {
        List<MemberEntity> tiered = new ArrayList();

        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List<MemberEntity> allMembers = q.getResultList();
        Iterator<MemberEntity> itr = allMembers.iterator();
        //get a description statistics
        DescriptiveStatistics stats = new DescriptiveStatistics();

        while (itr.hasNext()) {
            MemberEntity current = itr.next();
            double currentCustLifeValue = calculateCustLifeValue(current.getMemberEmail());
            stats.addValue(currentCustLifeValue);
        }

        double tieredValue = stats.getPercentile(80);

        Query q2 = em.createQuery("SELECT m FROM MemberEntity m");
        List<MemberEntity> allMembers2 = q2.getResultList();
        Iterator<MemberEntity> itr2 = allMembers2.iterator();
        List<MemberEntity> resultList = new ArrayList();

        while (itr2.hasNext()) {
            MemberEntity current = itr.next();
            double currentCustLifeValue = calculateCustLifeValue(current.getMemberEmail());
            if (currentCustLifeValue >= tieredValue) {
                resultList.add(current);
            }
        }

        tiered = resultList;

        return tiered;
    }

    public List<MemberEntity> getTieredBasedOnSizeOfWallet() {
        List<MemberEntity> tiered = new ArrayList();

        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List<MemberEntity> allMembers = q.getResultList();
        Iterator<MemberEntity> itr = allMembers.iterator();
        //get a description statistics
        DescriptiveStatistics stats = new DescriptiveStatistics();

        while (itr.hasNext()) {
            MemberEntity current = itr.next();
            double currentSizeOfWallet = this.calculateSizeOfWallet(current.getMemberEmail());
            stats.addValue(currentSizeOfWallet);
        }

        double tieredValue = stats.getPercentile(80);

        Query q2 = em.createQuery("SELECT m FROM MemberEntity m");
        List<MemberEntity> allMembers2 = q2.getResultList();
        Iterator<MemberEntity> itr2 = allMembers2.iterator();
        List<MemberEntity> resultList = new ArrayList();

        while (itr2.hasNext()) {
            MemberEntity current = itr.next();
            double currentSizeOfWallet = this.calculateSizeOfWallet(current.getMemberEmail());
            if (currentSizeOfWallet >= tieredValue) {
                resultList.add(current);
            }
        }

        tiered = resultList;

        return tiered;
    }

    //Identify the most valuable customer to each department
    public List<MemberEntity> getTieredBasedOnShareOfWallet(String mtDepartment) {
        List<MemberEntity> tiered = new ArrayList();

        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List<MemberEntity> allMembers = q.getResultList();
        Iterator<MemberEntity> itr = allMembers.iterator();
        //get a description statistics
        DescriptiveStatistics stats = new DescriptiveStatistics();

        while (itr.hasNext()) {
            MemberEntity current = itr.next();
            double currentShareOfWallet = this.calculateShareOfWallet(current.getMemberEmail(), mtDepartment);
            stats.addValue(currentShareOfWallet);
        }

        double tieredValue = stats.getPercentile(80);

        Query q2 = em.createQuery("SELECT m FROM MemberEntity m");
        List<MemberEntity> allMembers2 = q2.getResultList();
        Iterator<MemberEntity> itr2 = allMembers2.iterator();
        List<MemberEntity> resultList = new ArrayList();

        while (itr2.hasNext()) {
            MemberEntity current = itr.next();
            double currentShareOfWallet = this.calculateShareOfWallet(current.getMemberEmail(), mtDepartment);
            if (currentShareOfWallet >= tieredValue) {
                resultList.add(current);
            }
        }

        tiered = resultList;

        return tiered;
    }

    //Below evaluate the response rate of a promotion
    public double evaluatePromotion(Long promotionId) throws ExistException {
        double responseRate = 0.00;
        PromotionEntity thisP = em.find(PromotionEntity.class, promotionId);
        if (thisP == null) {
            throw new ExistException();
        }

        Query q = em.createQuery("SELECT m FROM MemberTransactionEntity m");
        List<MemberTransactionEntity> allMemberTrans = q.getResultList();
        Iterator<MemberTransactionEntity> itr = allMemberTrans.iterator();
        List<MemberTransactionEntity> respondedSales = new ArrayList();
        List<MemberEntity> respondedTargets = new ArrayList();

        while (itr.hasNext()) {
            MemberTransactionEntity current = itr.next();
            if (current.getMtPromotion().equalsIgnoreCase(thisP.getPromotionCode())) {
                respondedSales.add(current);
                MemberEntity currentMember = em.find(MemberEntity.class, current.getMemberEmail());
                if (!respondedTargets.contains(currentMember)) {
                    respondedTargets.add(currentMember);
                }
            }
        }

        int expectedSize = thisP.getMcMemberTargets().size();

//        responseRate = respondedSales.size()/expectedSize;
        responseRate = respondedTargets.size() / expectedSize;

        return responseRate;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public RFMModelEntity getRFMModel(int ModelNumber) throws ExistException {
        RFMModelEntity current = em.find(RFMModelEntity.class, ModelNumber);
        if (current != null) {
            return current;
        } else {
            throw new ExistException();
        }
    }
    
    public boolean findRFMModel(int ModelNumber) throws ExistException {
        RFMModelEntity current = em.find(RFMModelEntity.class, ModelNumber);
        if (current == null) {
            return false;
        } else {
            return true;
        }
    }
}
