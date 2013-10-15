/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMMS.entity;

import ACMS.entity.ReservationEntity;
import ACMS.entity.RoomServiceEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Cookie
 */
@Entity
public class ContractEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long contractId;
   
    private int contractOutlet;
    @OneToMany(mappedBy = "contract",cascade={CascadeType.MERGE})
    private List<ContracteventEntity> contractEvent = new ArrayList<ContracteventEntity>();
    @ManyToOne (cascade={CascadeType.MERGE})
    private MerchantEntity merchant = new MerchantEntity();
    @OneToOne(cascade = {CascadeType.MERGE})
    private OutletEntity outlet;
    private String status;
    
    public ContractEntity(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
        
    public MerchantEntity getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantEntity merchant) {
        this.merchant = merchant;
        System.err.println("NO1: IN contractEntity: setting merchant");
    }

    public OutletEntity getOutlet() {
        return outlet;
    }

    public void setOutlet(OutletEntity outlet) {
      
        this.outlet = outlet;
        System.err.println("NO2: IN contractEntity: setting outlet");
    }

    public int getContractOutlet() {
        return contractOutlet;
    }

    public void setContractOutlet(int contractOutlet) {
        this.contractOutlet = contractOutlet;
    }

   
    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

   
    public List<ContracteventEntity> getContractEvent() {
        return contractEvent;
    }

    public void setContractEvent(List<ContracteventEntity> contractEvent) {
        this.contractEvent = contractEvent;
    }
    
     public void addContractEvent(ContracteventEntity contract){
       this.contractEvent.add(contract);
       System.out.println("ContractEventEntity-->new event added:" +contract.getContracteventId() );
    }
     
     public ContracteventEntity getLast()
     {
         int eventsize = this.getContractEvent().size();
         
         return this.getContractEvent().get(eventsize);
         
     }


    public Long getId() {
        return contractId;
    }

    public void setId(Long contractId) {
        this.contractId = contractId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contractId != null ? contractId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContractEntity)) {
            return false;
        }
        ContractEntity other = (ContractEntity) object;
        if ((this.contractId == null && other.contractId != null) || (this.contractId != null && !this.contractId.equals(other.contractId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SMMS.entity.ContractEntity[ id=" + contractId + " ]";
    }
}
