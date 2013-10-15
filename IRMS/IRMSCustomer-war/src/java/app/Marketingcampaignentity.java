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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "marketingcampaignentity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Marketingcampaignentity.findAll", query = "SELECT m FROM Marketingcampaignentity m"),
    @NamedQuery(name = "Marketingcampaignentity.findByMcid", query = "SELECT m FROM Marketingcampaignentity m WHERE m.mcid = :mcid"),
    @NamedQuery(name = "Marketingcampaignentity.findByMcenddate", query = "SELECT m FROM Marketingcampaignentity m WHERE m.mcenddate = :mcenddate"),
    @NamedQuery(name = "Marketingcampaignentity.findByMcpromotioncode", query = "SELECT m FROM Marketingcampaignentity m WHERE m.mcpromotioncode = :mcpromotioncode"),
    @NamedQuery(name = "Marketingcampaignentity.findByMcremarks", query = "SELECT m FROM Marketingcampaignentity m WHERE m.mcremarks = :mcremarks"),
    @NamedQuery(name = "Marketingcampaignentity.findByMcstartdate", query = "SELECT m FROM Marketingcampaignentity m WHERE m.mcstartdate = :mcstartdate")})
public class Marketingcampaignentity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MCID")
    private Long mcid;
    @Column(name = "MCENDDATE")
    @Temporal(TemporalType.DATE)
    private Date mcenddate;
    @Size(max = 255)
    @Column(name = "MCPROMOTIONCODE")
    private String mcpromotioncode;
    @Size(max = 255)
    @Column(name = "MCREMARKS")
    private String mcremarks;
    @Column(name = "MCSTARTDATE")
    @Temporal(TemporalType.DATE)
    private Date mcstartdate;
    @ManyToMany(mappedBy = "marketingcampaignentityCollection")
    private Collection<Memberentity> memberentityCollection;

    public Marketingcampaignentity() {
    }

    public Marketingcampaignentity(Long mcid) {
        this.mcid = mcid;
    }

    public Long getMcid() {
        return mcid;
    }

    public void setMcid(Long mcid) {
        this.mcid = mcid;
    }

    public Date getMcenddate() {
        return mcenddate;
    }

    public void setMcenddate(Date mcenddate) {
        this.mcenddate = mcenddate;
    }

    public String getMcpromotioncode() {
        return mcpromotioncode;
    }

    public void setMcpromotioncode(String mcpromotioncode) {
        this.mcpromotioncode = mcpromotioncode;
    }

    public String getMcremarks() {
        return mcremarks;
    }

    public void setMcremarks(String mcremarks) {
        this.mcremarks = mcremarks;
    }

    public Date getMcstartdate() {
        return mcstartdate;
    }

    public void setMcstartdate(Date mcstartdate) {
        this.mcstartdate = mcstartdate;
    }

    @XmlTransient
    public Collection<Memberentity> getMemberentityCollection() {
        return memberentityCollection;
    }

    public void setMemberentityCollection(Collection<Memberentity> memberentityCollection) {
        this.memberentityCollection = memberentityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mcid != null ? mcid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Marketingcampaignentity)) {
            return false;
        }
        Marketingcampaignentity other = (Marketingcampaignentity) object;
        if ((this.mcid == null && other.mcid != null) || (this.mcid != null && !this.mcid.equals(other.mcid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.Marketingcampaignentity[ mcid=" + mcid + " ]";
    }
    
}
