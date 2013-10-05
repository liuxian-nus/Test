/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Cookie
 */
@Entity
public class MerchantEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String merchantEmail;
    private String merchantName;
    private String merchantPassword;
    private String merchantHP;
    private String merchantAddress;
    private String securityQuestion;
    private String answer;
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<OutletEntity> merchantOutlet = new ArrayList<OutletEntity>();
    @OneToMany(cascade={CascadeType.PERSIST})
    private List<ContractEntity> merchantContract = new ArrayList<ContractEntity>();

    public List<OutletEntity> getMerchantOutlet() {
        return merchantOutlet;
    }

    public void setMerchantOutlet(List<OutletEntity> merchantOutlet) {
        this.merchantOutlet = merchantOutlet;
    }

    public List<ContractEntity> getMerchantContract() {
        return merchantContract;
    }

    public void setMerchantContract(List<ContractEntity> merchantContract) {
        this.merchantContract = merchantContract;
    }

    public String getMerchantEmail() {
        return merchantEmail;
    }

    public void setMerchantEmail(String merchantEmail) {
        this.merchantEmail = merchantEmail;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantPassword() {
        return merchantPassword;
    }

    public void setMerchantPassword(String merchantPassword) {
        this.merchantPassword = merchantPassword;
    }

    public String getMerchantHP() {
        return merchantHP;
    }

    public void setMerchantHP(String merchantHP) {
        this.merchantHP = merchantHP;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getId() {
        return merchantEmail;
    }

    public void setId(String id) {
        this.merchantEmail = merchantEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (merchantEmail != null ? merchantEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MerchantEntity)) {
            return false;
        }
        MerchantEntity other = (MerchantEntity) object;
        if ((this.merchantEmail == null && other.merchantEmail != null) || (this.merchantEmail != null && !this.merchantEmail.equals(other.merchantEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SMMS.entity.MerchantEntity[ id=" + merchantEmail + " ]";
    }

    private static class Arraylist<T> {

        public Arraylist() {
        }
    }
}
