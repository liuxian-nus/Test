/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Diana Wang
 */
@Entity
@XmlRootElement
@XmlType(name="promotionEntity")
public class PromotionEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long promotionId;//marketing campaign id
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date promotionStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date promotionEndDate;
    private String promotionRemarks;
    private String promotionCode;
    private String promotionCategory;
    private String promotionTitle;
    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<MemberEntity> mcMemberTargets;

    /*  public void create(Date startDate, Date endDate, String remarks, Set<MemberEntity> memberTargets) {
    this.setMcEndDate(endDate);
    this.setMcStartDate(startDate);
    this.setMcMemberTargets(memberTargets);
    this.setMcRemarks(remarks);
    this.setMcId(System.nanoTime());
    Random ran = new Random();
    int top = 3;
    char data = ' ';
    String dat = "";
    for (int i = 0; i <= top; i++) {
    data = (char) (ran.nextInt(25) + 97);
    dat = data + dat;
    }
    this.setMcPromotionCode(dat);
    System.out.println("MarketingCampaignEntity: a new marketing campaign has been added!");
    }
     */
    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Date getPromotionStartDate() {
        return promotionStartDate;
    }

    public void setPromotionStartDate(Date promotionStartDate) {
        this.promotionStartDate = promotionStartDate;
    }

    public Date getPromotionEndDate() {
        return promotionEndDate;
    }

    public void setPromotionEndDate(Date promotionEndDate) {
        this.promotionEndDate = promotionEndDate;
    }

    public String getPromotionRemarks() {
        return promotionRemarks;
    }

    public void setPromotionRemarks(String promotionRemarks) {
        this.promotionRemarks = promotionRemarks;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getPromotionCategory() {
        return promotionCategory;
    }

    public void setPromotionCategory(String promotionCategory) {
        this.promotionCategory = promotionCategory;
    }

    public String getPromotionTitle() {
        return promotionTitle;
    }

    public void setPromotionTitle(String promotionTitle) {
        this.promotionTitle = promotionTitle;
    }
    
    
    public Set<MemberEntity> getMcMemberTargets() {
        return mcMemberTargets;
    }

    public void setMcMemberTargets(Set<MemberEntity> mcMemberTargets) {
        this.mcMemberTargets = mcMemberTargets;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionId != null ? promotionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PromotionEntity)) {
            return false;
        }
        PromotionEntity other = (PromotionEntity) object;
        if ((this.promotionId == null && other.promotionId != null) || (this.promotionId != null && !this.promotionId.equals(other.promotionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRMS.entity.MarketingCampaignEntity[ id=" + promotionId + " ]";
    }
}
