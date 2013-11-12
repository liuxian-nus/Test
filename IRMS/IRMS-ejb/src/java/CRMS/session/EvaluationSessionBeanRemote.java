/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberEntity;
import CRMS.entity.RFMModelEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.Remote;
import javax.persistence.NoResultException;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface EvaluationSessionBeanRemote {

    //3. RFMMedel Done!
    RFMModelEntity addRFMModel(RFMModelEntity rfmModel);

    //到这里啦！
    double calculateCustLifeValue(String memberEmail);

    Integer calculateRFMValue(String memberEmail, int ModelNumber) throws ExistException;

    //2. JSF Done!
    double calculateShareOfWallet(String memberEmail, String mtDepartment);

    //1. JSF Done!
    double calculateSizeOfWallet(String memberEmail);

    //Below evaluate the response rate of a promotion
    double evaluatePromotion(Long promotionId) throws ExistException;

    boolean findRFMModel(int ModelNumber) throws ExistException;

    //买一送一，不新建session bean了
    List<RFMModelEntity> getAllRFMs() throws NoResultException;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    RFMModelEntity getRFMModel(int ModelNumber) throws ExistException;

    List<MemberEntity> getTieredBasedOnCustLifeValue();

    List<MemberEntity> getTieredBasedOnRFM() throws ExistException;

    //Identify the most valuable customer to each department
    List<MemberEntity> getTieredBasedOnShareOfWallet(String mtDepartment);

    List<MemberEntity> getTieredBasedOnSizeOfWallet();

    void persist(Object object);

    //Model number currently is 1 since only one model is available
    boolean setRFMParameter(Double Recency, Double Frequency, Double Monetary, int ModelNumber);
    
}
