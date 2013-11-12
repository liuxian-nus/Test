/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.entity.PromotionEntity;
import CRMS.session.EvaluationSessionBean;
import CRMS.session.PromotionSessionBean;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Iterator;
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
    @EJB
    PromotionSessionBean promotionSessionBean;
    List<MemberEntity> rfmMembers;
    List<MemberEntity> lifeValueMembers;
    List<MemberEntity> sizeOfWalletMembers;
    List<MemberEntity> shareOfWalletMember1;
    List<MemberEntity> shareOfWalletMember2;
    List<MemberEntity> shareOfWalletMember3;
    List<MemberEntity> shareOfWalletMember4;
    List<MemberEntity> shareOfWalletMember5;
    List<MemberEntity> shareOfWalletMember6;
    List<PromotionEntity> promotions;

    /**
     * Creates a new instance of TieredManagedBean
     */
    public TieredManagedBean() {
        rfmMembers = new ArrayList<MemberEntity>();
        lifeValueMembers = new ArrayList<MemberEntity>();
        sizeOfWalletMembers = new ArrayList<MemberEntity>();
        shareOfWalletMember1 = new ArrayList<MemberEntity>();
        shareOfWalletMember2 = new ArrayList<MemberEntity>();
        shareOfWalletMember3 = new ArrayList<MemberEntity>();
        shareOfWalletMember4 = new ArrayList<MemberEntity>();
        shareOfWalletMember5 = new ArrayList<MemberEntity>();
        shareOfWalletMember6 = new ArrayList<MemberEntity>();
        promotions = new ArrayList<PromotionEntity>();
    }

    @PostConstruct
    public void init() throws ExistException {
        rfmMembers = evaluationSessionBean.getTieredBasedOnRFM();
        lifeValueMembers = evaluationSessionBean.getTieredBasedOnCustLifeValue();
        sizeOfWalletMembers = evaluationSessionBean.getTieredBasedOnSizeOfWallet();
        shareOfWalletMember1 = evaluationSessionBean.getTieredBasedOnShareOfWallet("hotel");
        shareOfWalletMember2 = evaluationSessionBean.getTieredBasedOnShareOfWallet("entertainment show");
        shareOfWalletMember3 = evaluationSessionBean.getTieredBasedOnShareOfWallet("food and beverage");
        shareOfWalletMember4 = evaluationSessionBean.getTieredBasedOnShareOfWallet("convention center");
        shareOfWalletMember5 = evaluationSessionBean.getTieredBasedOnShareOfWallet("attraction");
        shareOfWalletMember6 = evaluationSessionBean.getTieredBasedOnShareOfWallet("shopping mall");
        promotions = generatePromotionList();
    }
    
    public List<PromotionEntity> getAllPromotions(){
        return promotions;
    }

    public List<PromotionEntity> generatePromotionList() throws ExistException {
        List<PromotionEntity> temp = promotionSessionBean.getAllPromotions();
        PromotionEntity promotion;
        Iterator<PromotionEntity> itr = temp.iterator();
        while (itr.hasNext()) {
            promotion = itr.next();
            evaluationSessionBean.evaluatePromotion(promotion.getPromotionId());
        }
        promotions = promotionSessionBean.getAllPromotions();
        return promotions;
    }

    public List<MemberEntity> getAllRFMMembers() {
        return rfmMembers;
    }

    public List<MemberEntity> getAllLifeValueMembers() {
        return lifeValueMembers;
    }

    public List<MemberEntity> getAllSizeOfWalletMembers() {
        return sizeOfWalletMembers;
    }

    public List<MemberEntity> getAllShareOfWalletMembers1() {
        return shareOfWalletMember1;
    }

    public List<MemberEntity> getAllShareOfWalletMembers2() {
        return shareOfWalletMember2;
    }

    public List<MemberEntity> getAllShareOfWalletMembers3() {
        return shareOfWalletMember3;
    }

    public List<MemberEntity> getAllShareOfWalletMembers4() {
        return shareOfWalletMember4;
    }

    public List<MemberEntity> getAllShareOfWalletMembers5() {
        return shareOfWalletMember5;
    }

    public List<MemberEntity> getAllShareOfWalletMembers6() {
        return shareOfWalletMember6;
    }
}
