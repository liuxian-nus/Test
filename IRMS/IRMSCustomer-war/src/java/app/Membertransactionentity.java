/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author liuxian
 */
@Entity
@Table(name = "membertransactionentity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membertransactionentity.findAll", query = "SELECT m FROM Membertransactionentity m"),
    @NamedQuery(name = "Membertransactionentity.findByMtid", query = "SELECT m FROM Membertransactionentity m WHERE m.mtid = :mtid"),
    @NamedQuery(name = "Membertransactionentity.findByMtamount", query = "SELECT m FROM Membertransactionentity m WHERE m.mtamount = :mtamount"),
    @NamedQuery(name = "Membertransactionentity.findByMtdate", query = "SELECT m FROM Membertransactionentity m WHERE m.mtdate = :mtdate"),
    @NamedQuery(name = "Membertransactionentity.findByMtdepartment", query = "SELECT m FROM Membertransactionentity m WHERE m.mtdepartment = :mtdepartment"),
    @NamedQuery(name = "Membertransactionentity.findByMtmode", query = "SELECT m FROM Membertransactionentity m WHERE m.mtmode = :mtmode"),
    @NamedQuery(name = "Membertransactionentity.findByMtpromotion", query = "SELECT m FROM Membertransactionentity m WHERE m.mtpromotion = :mtpromotion"),
    @NamedQuery(name = "Membertransactionentity.findByPaymentstatus", query = "SELECT m FROM Membertransactionentity m WHERE m.paymentstatus = :paymentstatus")})
public class Membertransactionentity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MTID")
    private Long mtid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MTAMOUNT")
    private Double mtamount;
    @Column(name = "MTDATE")
    @Temporal(TemporalType.DATE)
    private Date mtdate;
    @Size(max = 255)
    @Column(name = "MTDEPARTMENT")
    private String mtdepartment;
    @Column(name = "MTMODE")
    private Boolean mtmode;
    @Size(max = 255)
    @Column(name = "MTPROMOTION")
    private String mtpromotion;
    @Column(name = "PAYMENTSTATUS")
    private Boolean paymentstatus;
    @JoinColumn(name = "MEMBER_MEMBEREMAIL", referencedColumnName = "MEMBEREMAIL")
    @ManyToOne
    private Memberentity memberMemberemail;

    public Membertransactionentity() {
    }

    public Membertransactionentity(Long mtid) {
        this.mtid = mtid;
    }

    public Long getMtid() {
        return mtid;
    }

    public void setMtid(Long mtid) {
        this.mtid = mtid;
    }

    public Double getMtamount() {
        return mtamount;
    }

    public void setMtamount(Double mtamount) {
        this.mtamount = mtamount;
    }

    public Date getMtdate() {
        return mtdate;
    }

    public void setMtdate(Date mtdate) {
        this.mtdate = mtdate;
    }

    public String getMtdepartment() {
        return mtdepartment;
    }

    public void setMtdepartment(String mtdepartment) {
        this.mtdepartment = mtdepartment;
    }

    public Boolean getMtmode() {
        return mtmode;
    }

    public void setMtmode(Boolean mtmode) {
        this.mtmode = mtmode;
    }

    public String getMtpromotion() {
        return mtpromotion;
    }

    public void setMtpromotion(String mtpromotion) {
        this.mtpromotion = mtpromotion;
    }

    public Boolean getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(Boolean paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public Memberentity getMemberMemberemail() {
        return memberMemberemail;
    }

    public void setMemberMemberemail(Memberentity memberMemberemail) {
        this.memberMemberemail = memberMemberemail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mtid != null ? mtid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membertransactionentity)) {
            return false;
        }
        Membertransactionentity other = (Membertransactionentity) object;
        if ((this.mtid == null && other.mtid != null) || (this.mtid != null && !this.mtid.equals(other.mtid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "app.Membertransactionentity[ mtid=" + mtid + " ]";
    }
    
}
