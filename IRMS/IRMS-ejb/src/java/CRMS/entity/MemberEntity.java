/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import javax.persistence.Temporal;

/**
 *
 * @author liuxian
 */
@Entity
public class MemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    protected String memberEmail;private String memberName;
    private String memberPassword;
    private String memberHP;
    private String gender;
    private String nationality;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date memberDob;
    private boolean maritalStatus;
    private boolean isVIP;
    private boolean isSubscriber;
    private Long point;
    private Long coin;
    
    public MemberEntity(){
    }
    
    //create a new MemberEntity instance
    public void create(String memberEmail,String memberName,String memberPassword,String memberHP,String gender,String nationality,Date memberDob,boolean maritalStatus,boolean isSuscriber) {
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
        this.setPoint(Long.valueOf(0)); //initial number
        this.setCoin(Long.valueOf(0)); //initial number
    }
    
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

    public boolean isMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(boolean maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public boolean isIsVIP() {
        return isVIP;
    }

    public void setIsVIP(boolean isVIP) {
        this.isVIP = isVIP;
    }

    public boolean isIsSubscriber() {
        return isSubscriber;
    }

    public void setIsSubscriber(boolean isSubscriber) {
        this.isSubscriber = isSubscriber;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public Long getCoin() {
        return coin;
    }

    public void setCoin(Long coin) {
        this.coin = coin;
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
