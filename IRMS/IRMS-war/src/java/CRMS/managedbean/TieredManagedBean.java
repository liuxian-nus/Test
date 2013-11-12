/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.session.EvaluationSessionBean;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class TieredManagedBean {
    
    @EJB
    EvaluationSessionBean evaluationSessionBean;
    
    List<MemberEntity> rfmMembers;
    List<MemberEntity> lifeValueMembers;
    List<MemberEntity> sizeOfWalletMembers;
//    List<MemberEntity> shareOfWalletMember;
//    private String department;

    /**
     * Creates a new instance of TieredManagedBean
     */
    public TieredManagedBean() {
        rfmMembers = new ArrayList<MemberEntity>();
        lifeValueMembers = new ArrayList<MemberEntity>();
        sizeOfWalletMembers = new ArrayList<MemberEntity>();
                
    }
    
    @PostConstruct
    public void init() throws ExistException{
        rfmMembers = evaluationSessionBean.getTieredBasedOnRFM();
        lifeValueMembers = evaluationSessionBean.getTieredBasedOnCustLifeValue();
        sizeOfWalletMembers = evaluationSessionBean.getTieredBasedOnSizeOfWallet();
//        shareOfWalletMember = evaluationSessionBean.getTieredBasedOnShareOfWallet(department);
    }
    
    public List<MemberEntity> getAllRFMMembers(){
        return rfmMembers;
    }
    
    public List<MemberEntity> getAllLifeValueMembers(){
        return lifeValueMembers;
    }
    
    public List<MemberEntity> getAllSizeOfWalletMembers(){
        return sizeOfWalletMembers;
    }
}
