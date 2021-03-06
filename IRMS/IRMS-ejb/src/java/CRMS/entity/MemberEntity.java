/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.entity;

import ACMS.entity.ReservationEntity;
import ATMS.entity.ExpressPassPurchaseEntity;
import ATMS.entity.TicketPurchaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author liuxian
 */
@Entity
@XmlRootElement
@XmlType(name = "memberEntity")
@XmlAccessorType(XmlAccessType.NONE)
public class MemberEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement
    @Id
    protected String memberEmail;
    @XmlElement
    private String memberName;
    @XmlElement
    private String memberPassword;
    @XmlElement
    private String memberHP;
    @XmlElement
    private String gender;
    @XmlElement
    private String nationality;
    @XmlElement
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date memberDob;
    @XmlElement
    private String maritalStatus;
    private boolean isVIP;
    @XmlElement
    private boolean isSubscriber;
    @XmlElement
    private double point;
    @XmlElement
    private double coin;
    private String preferences;
    @XmlElement
    private String securityQuestion;
    @XmlElement
    private String answer;
    private boolean memberAccountStatus = true;
    @XmlTransient
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "rcMember")
    private Set<ReservationEntity> hotelReservation;
    @XmlTransient
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<MemberTransactionEntity> MemberTransactions = new HashSet<MemberTransactionEntity>();
    @XmlTransient
    @ManyToMany(cascade = {CascadeType.ALL}, mappedBy = "mcMemberTargets")
    private Set<PromotionEntity> Promotions;
    @XmlTransient
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "member")
    private List<TicketPurchaseEntity> ticketPurchases = new ArrayList<TicketPurchaseEntity>();
    @XmlTransient
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "member")
    private List<ExpressPassPurchaseEntity> expressPassPurchases = new ArrayList<ExpressPassPurchaseEntity>();
    @XmlTransient
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "couponOwner")
    private List<CouponEntity> coupons = new ArrayList<CouponEntity>();
    @XmlTransient
    @OneToMany(cascade = {CascadeType.MERGE}, mappedBy = "feedbackOwner")
    private List<FeedbackEntity> feedbacks = new ArrayList<FeedbackEntity>();
    private double sizeOfWallet;
    private boolean birthdayEmail = false;
    private String contactEmployee;

    public Set<PromotionEntity> getPromotions() {
        return Promotions;
    }

    public void setPromotions(Set<PromotionEntity> MarketingCampaigns) {
        this.Promotions = MarketingCampaigns;
    }

    public Set<MemberTransactionEntity> getMemberTransactions() {
        return MemberTransactions;
    }

    public void setMemberTransactions(Set<MemberTransactionEntity> MemberTransactions) {
        this.MemberTransactions = MemberTransactions;
    }

    public void addMemberTransaction(MemberTransactionEntity newTransaction) {
        System.out.println("into addMemberTransaction");
        this.MemberTransactions.add(newTransaction);
    }

    public MemberEntity() {
    }

    public List<TicketPurchaseEntity> getTicketPurchases() {
        return ticketPurchases;
    }

    public void setTicketPurchases(List<TicketPurchaseEntity> ticketPurchases) {
        this.ticketPurchases = ticketPurchases;
    }

    public List<ExpressPassPurchaseEntity> getExpressPassPurchases() {
        return expressPassPurchases;
    }

    public void setExpressPassPurchases(List<ExpressPassPurchaseEntity> expressPassPurchases) {
        this.expressPassPurchases = expressPassPurchases;
    }

    public List<FeedbackEntity> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackEntity> feedbacks) {
        this.feedbacks = feedbacks;
    }

    //create a new MemberEntity instance
   /* public void create(String memberEmail,String memberName,String memberPassword,String memberHP,String gender,String nationality,Date memberDob,boolean maritalStatus,boolean isSuscriber) {
     this.setMemberEmail(memberEmail);
     this.setMemberPassword(memberPassword);
     this.setMemberName(memberName);
     this.setMemberHP(memberHP);
     this.setGender(gender);
     this.setNationality(nationality);
     this.setMemberDob(memberDob);
     this.setMaritalStatus(maritalStatus);
     this.setIsSubscriber(isSubscriber);
     this.setIsVIP(false); //initial status
     this.setPoint(0); //initial number
     this.setCoin(0); //initial number
     }
     */
    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberHP() {
        return memberHP;
    }

    public void setMemberHP(String memberHP) {
        this.memberHP = memberHP;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getMemberDob() {
        return memberDob;
    }

    public void setMemberDob(Date memberDob) {
        this.memberDob = memberDob;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public String isVIPString() {
        if (isVIP) {
            return "yes";
        } else {
            return "no";
        }
    }

    public void setIsVIP(boolean isVIP) {
        this.isVIP = isVIP;
    }

    public boolean isSubscriber() {
        return isSubscriber;
    }

    public String isSubscriberString() {
        if (isSubscriber) {
            return "yes";
        } else {
            return "no";
        }
    }

    public void setIsSubscriber(boolean isSubscriber) {
        this.isSubscriber = isSubscriber;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getCoin() {
        return coin;
    }

    public void setCoin(double coin) {
        this.coin = coin;
    }

    public String getPreferences() {
        System.out.println("return preferences: " + preferences);
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    /*public void addPreferences(String newPreference) {
     this.preferences.add(newPreference);
     }
    
     public void removePreferences(String oldPreference) {
     this.preferences.remove(oldPreference);
     }*/
    public Set<ReservationEntity> getHotelReservation() {
        return hotelReservation;
    }

    public void setHotelReservation(Set<ReservationEntity> hotelReservation) {
        this.hotelReservation = hotelReservation;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String question) {
        securityQuestion = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isMemberAccountStatus() {
        return memberAccountStatus;
    }

    public void setMemberAccountStatus(boolean memberAccountStatus) {
        this.memberAccountStatus = memberAccountStatus;
    }

    public List<CouponEntity> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponEntity> coupons) {
        this.coupons = coupons;
    }

    public double getSizeOfWallet() {
        return sizeOfWallet;
    }

    public void setSizeOfWallet(double sizeOfWallet) {
        this.sizeOfWallet = sizeOfWallet;
    }

    public boolean isBirthdayEmail() {
        return birthdayEmail;
    }

    public void setBirthdayEmail(boolean birthdayEmail) {
        this.birthdayEmail = birthdayEmail;
    }    

    public String getContactEmployee() {
        return contactEmployee;
    }

    public void setContactEmployee(String contactEmployee) {
        this.contactEmployee = contactEmployee;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberEmail != null ? memberEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MemberEntity)) {
            return false;
        }
        MemberEntity other = (MemberEntity) object;
        if ((this.memberEmail == null && other.memberEmail != null) || (this.memberEmail != null && !this.memberEmail.equals(other.memberEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRMS.MemberEntity[ email=" + memberEmail + " ]";
    }
}
