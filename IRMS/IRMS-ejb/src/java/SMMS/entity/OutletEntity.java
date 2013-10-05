/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.entity;

import ACMS.entity.RoomServiceEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.transaction.UserTransaction;

/**
 *
 * @author Cookie
 */
@Entity
public class OutletEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int outletId;
    private String outletType; //jewelery,FNB,appareal,appliance,toys
    private String outletName;
    private String outletStatus;
    private String outletCondition;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date outletCStartingDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date outletCEndingDate;
    private int outletLevel;
    private int outletNo;
    private double outletArea;
    
    @OneToMany(mappedBy = "outlet")
    private List<OutletTransactionEntity> outletTransaction = new ArrayList<OutletTransactionEntity>();
    @ManyToOne
    private MerchantEntity outletMerchant = new MerchantEntity();
    @OneToMany(mappedBy = "outlet")
    private List<BillEntity> outletBill = new ArrayList<BillEntity>();

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public List<BillEntity> getOutletBill() {
        return outletBill;
    }

    public void setOutletBill(List<BillEntity> outletBill) {
        this.outletBill = outletBill;
    }

    public List<OutletTransactionEntity> getOutletTransaction() {
        return outletTransaction;
    }

    public void setOutletTransaction(List<OutletTransactionEntity> outletTransaction) {
        this.outletTransaction = outletTransaction;
    }

    public MerchantEntity getOutletMerchant() {
        return outletMerchant;
    }

    public void setOutletMerchant(MerchantEntity outletMerchant) {
        this.outletMerchant = outletMerchant;
    }

    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletLevel, int outletNo) {
        this.outletId = outletLevel * 100 + outletNo;
    }

    public String getOutletType() {
        return outletType;
    }

    public void setOutletType(String outletType) {
        this.outletType = outletType;
    }

    public String getOutletStatus() {
        return outletStatus;
    }

    public void setOutletStatus(String outletStatus) {
        this.outletStatus = outletStatus;
    }

    public String getOutletCondition() {
        return outletCondition;
    }

    public void setOutletCondition(String outletCondition) {
        this.outletCondition = outletCondition;
    }

    public Date getOutletCStartingDate() {
        return outletCStartingDate;
    }

    public void setOutletCStartingDate(Date outletCStartingDate) {
        this.outletCStartingDate = outletCStartingDate;
    }

    public Date getOutletCEndingDate() {
        return outletCEndingDate;
    }

    public void setOutletCEndingDate(Date outletCEndingDate) {
        this.outletCEndingDate = outletCEndingDate;
    }

    public int getOutletLevel() {
        return outletLevel;
    }

    public void setOutletLevel(int outletLevel) {
        this.outletLevel = outletLevel;
    }

    public int getOutletNo() {
        return outletNo;
    }

    public void setOutletNo(int outletNo) {
        this.outletNo = outletNo;
    }

    public double getOutletArea() {
        return outletArea;
    }

    public void setOutletArea(double outletArea) {
        this.outletArea = outletArea;
    }

    public int getId() {
        return outletId;
    }

    public void setId(int OutletId) {
        this.outletId = OutletId;
    }


    /*  @Override
     public boolean equals(Object object) {
     // TODO: Warning - this method won't work in the case the id fields are not set
     if (!(object instanceof OutletEntity)) {
     return false;
     }
     OutletEntity other = (OutletEntity) object;
     if ((this.OutletId == null && other.OutletId != null) || (this.OutletId != null && !this.OutletId.equals(other.OutletId))) {
     return false;
     }
     return true;
     }
     */
    @Override
    public String toString() {
        return "SMMS.entity.OutletEntity[ id=" + outletId + " ]";
    }

    public void persist(Object object) {
        /* Add this to the deployment descriptor of this module (e.g. web.xml, ejb-jar.xml):
         * <persistence-context-ref>
         * <persistence-context-ref-name>persistence/LogicalName</persistence-context-ref-name>
         * <persistence-unit-name>IRMS-ejbPU</persistence-unit-name>
         * </persistence-context-ref>
         * <resource-ref>
         * <res-ref-name>UserTransaction</res-ref-name>
         * <res-type>javax.transaction.UserTransaction</res-type>
         * <res-auth>Container</res-auth>
         * </resource-ref> */
        try {
            Context ctx = new InitialContext();
            UserTransaction utx = (UserTransaction) ctx.lookup("java:comp/env/UserTransaction");
            utx.begin();
            EntityManager em = (EntityManager) ctx.lookup("java:comp/env/persistence/LogicalName");
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
