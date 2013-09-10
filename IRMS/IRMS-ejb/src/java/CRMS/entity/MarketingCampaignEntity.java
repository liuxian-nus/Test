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

/**
 *
 * @author Diana Wang
 */
@Entity
public class MarketingCampaignEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mcId;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mcStartDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mcEndDate;
    private String mcRemarks;
    private String mcPromotionCode;

    public String getMcPromotionCode() {
        return mcPromotionCode;
    }

    public void setMcPromotionCode(String mcPromotionCode) {
        this.mcPromotionCode = mcPromotionCode;
    }
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
    public Date getMcStartDate() {
        return mcStartDate;
    }

    public void setMcStartDate(Date mcStartDate) {
        this.mcStartDate = mcStartDate;
    }

    public Date getMcEndDate() {
        return mcEndDate;
    }

    public void setMcEndDate(Date mcEndDate) {
        this.mcEndDate = mcEndDate;
    }

    public String getMcRemarks() {
        return mcRemarks;
    }

    public void setMcRemarks(String mcRemarks) {
        this.mcRemarks = mcRemarks;
    }

    public Set<MemberEntity> getMcMemberTargets() {
        return mcMemberTargets;
    }

    public void setMcMemberTargets(Set<MemberEntity> mcMemberTargets) {
        this.mcMemberTargets = mcMemberTargets;
    }

    public Long getMcId() {
        return mcId;
    }

    public void setMcId(Long mcId) {
        this.mcId = mcId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mcId != null ? mcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MarketingCampaignEntity)) {
            return false;
        }
        MarketingCampaignEntity other = (MarketingCampaignEntity) object;
        if ((this.mcId == null && other.mcId != null) || (this.mcId != null && !this.mcId.equals(other.mcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CRMS.entity.MarketingCampaignEntity[ id=" + mcId + " ]";
    }
}
