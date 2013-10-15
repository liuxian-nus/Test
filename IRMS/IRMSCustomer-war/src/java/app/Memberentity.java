/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author liuxian
 */
@Entity
@Table(name = "memberentity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memberentity.findAll", query = "SELECT m FROM Memberentity m"),
    @NamedQuery(name = "Memberentity.findByMemberemail", query = "SELECT m FROM Memberentity m WHERE m.memberemail = :memberemail"),
    @NamedQuery(name = "Memberentity.findByAnswer", query = "SELECT m FROM Memberentity m WHERE m.answer = :answer"),
    @NamedQuery(name = "Memberentity.findByCoin", query = "SELECT m FROM Memberentity m WHERE m.coin = :coin"),
    @NamedQuery(name = "Memberentity.findByGender", query = "SELECT m FROM Memberentity m WHERE m.gender = :gender"),
    @NamedQuery(name = "Memberentity.findByIssubscriber", query = "SELECT m FROM Memberentity m WHERE m.issubscriber = :issubscriber"),
    @NamedQuery(name = "Memberentity.findByIsvip", query = "SELECT m FROM Memberentity m WHERE m.isvip = :isvip"),
    @NamedQuery(name = "Memberentity.findByMaritalstatus", query = "SELECT m FROM Memberentity m WHERE m.maritalstatus = :maritalstatus"),
    @NamedQuery(name = "Memberentity.findByMemberdob", query = "SELECT m FROM Memberentity m WHERE m.memberdob = :memberdob"),
    @NamedQuery(name = "Memberentity.findByMemberhp", query = "SELECT m FROM Memberentity m WHERE m.memberhp = :memberhp"),
    @NamedQuery(name = "Memberentity.findByMembername", query = "SELECT m FROM Memberentity m WHERE m.membername = :membername"),
    @NamedQuery(name = "Memberentity.findByMemberpassword", query = "SELECT m FROM Memberentity m WHERE m.memberpassword = :memberpassword"),
    @NamedQuery(name = "Memberentity.findByNationality", query = "SELECT m FROM Memberentity m WHERE m.nationality = :nationality"),
    @NamedQuery(name = "Memberentity.findByPoint", query = "SELECT m FROM Memberentity m WHERE m.point = :point"),
    @NamedQuery(name = "Memberentity.findByPreferences", query = "SELECT m FROM Memberentity m WHERE m.preferences = :preferences"),
    @NamedQuery(name = "Memberentity.findBySecurityquestion", query = "SELECT m FROM Memberentity m WHERE m.securityquestion = :securityquestion")})
public class Memberentity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MEMBEREMAIL")
    private String memberemail;
    @Size(max = 255)
    @Column(name = "ANSWER")
    private String answer;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COIN")
    private Double coin;
    @Size(max = 255)
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "ISSUBSCRIBER")
    private Boolean issubscriber;
    @Column(name = "ISVIP")
    private Boolean isvip;
    @Size(max = 255)
    @Column(name = "MARITALSTATUS")
    private String maritalstatus;
    @Column(name = "MEMBERDOB")
    @Temporal(TemporalType.DATE)
    private Date memberdob;
    @Size(max = 255)
    @Column(name = "MEMBERHP")
    private String memberhp;
    @Size(max = 255)
    @Column(name = "MEMBERNAME")
    private String membername;
    @Size(max = 255)
    @Column(name = "MEMBERPASSWORD")
    private String memberpassword;
    @Size(max = 255)
    @Column(name = "NATIONALITY")
    private String nationality;
    @Column(name = "POINT")
    private Double point;
    @Size(max = 255)
    @Column(name = "PREFERENCES")
    private String preferences;
    @Size(max = 255)
    @Column(name = "SECURITYQUESTION")
    private String securityquestion;
    @JoinTable(name = "marketingcampaignentity_memberentity", joinColumns = {
        @JoinColumn(name = "mcMemberTargets_MEMBEREMAIL", referencedColumnName = "MEMBEREMAIL")}, inverseJoinColumns = {
        @JoinColumn(name = "MarketingCampaigns_MCID", referencedColumnName = "MCID")})
    @ManyToMany
    private Collection<Marketingcampaignentity> marketingcampaignentityCollection;
    @OneToMany(mappedBy = "memberMemberemail")
    private Collection<Membertransactionentity> membertransactionentityCollection;

    public Memberentity() {
    }

    public Memberentity(String memberemail) {
        this.memberemail = memberemail;
    }

    public String getMemberemail() {
        return memberemail;
    }

    public void setMemberemail(String memberemail) {
        this.memberemail = memberemail;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Double getCoin() {
        return coin;
    }

    public void setCoin(Double coin) {
        this.coin = coin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getIssubscriber() {
        return issubscriber;
    }

    public void setIssubscriber(Boolean issubscriber) {
        this.issubscriber = issubscriber;
    }

    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public Date getMemberdob() {
        return memberdob;
    }

    public void setMemberdob(Date memberdob) {
        this.memberdob = memberdob;
    }

    public String getMemberhp() {
        return memberhp;
    }

    public void setMemberhp(String memberhp) {
        this.memberhp = memberhp;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getMemberpassword() {
        return memberpassword;
    }

    public void setMemberpassword(String memberpassword) {
        this.memberpassword = memberpassword;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getSecurityquestion() {
        return securityquestion;
    }

    public void setSecurityquestion(String securityquestion) {
        this.securityquestion = securityquestion;
    }

    @XmlTransient
    public Collection<Marketingcampaignentity> getMarketingcampaignentityCollection() {
        return marketingcampaignentityCollection;
    }

    public void setMarketingcampaignentityCollection(Collection<Marketingcampaignentity> marketingcampaignentityCollection) {
        this.marketingcampaignentityCollection = marketingcampaignentityCollection;
    }

    @XmlTransient
    public Collection<Membertransactionentity> getMembertransactionentityCollection() {
        return membertransactionentityCollection;
    }

    public void setMembertransactionentityCollection(Collection<Membertransactionentity> membertransactionentityCollection) {
        this.membertransactionentityCollection = membertransactionentityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (memberemail != null ? memberemail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memberentity)) {
            return false;
        }
        Memberentity other = (Memberentity) object;
        if ((this.memberemail == null && other.memberemail != null) || (this.memberemail != null && !this.memberemail.equals(other.memberemail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.Memberentity[ memberemail=" + memberemail + " ]";
    }
    
}
