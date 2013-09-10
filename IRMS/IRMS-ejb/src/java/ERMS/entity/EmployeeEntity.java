/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Diana Wang
 */
@Entity
public class EmployeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String employeeId;
    private String employeeLastName;
    private String employeeFirstName;
    private String employeeGender;
    private Integer employeeBirthyear;
    private String employeePassword;
    private String employeeDepartment;
    private String employeePosition;
    private Integer employeeSchedule; //there are 3 shifts in a day represented by 1/2/3

    public EmployeeEntity() {
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public Integer getEmployeeBirthyear() {
        return employeeBirthyear;
    }

    public void setEmployeeBirthyear(Integer employeeBirthyear) {
        this.employeeBirthyear = employeeBirthyear;
    }

    
   /* public boolean create(String employeeId,String employeePassword,String employeeDepartment,String employeePosition,Integer employeeSchedule){
        this.setEmployeeDepartment(employeeDepartment);
        this.setEmployeePassword(employeePassword);
        this.setEmployeePosition(employeePosition);
        this.setEmployeeSchedule(employeeSchedule);
        this.setId(employeeId);
        this.setEmployeeBirthyear(employeeBirthyear);
        this.setEmployeeFirstName(employeeFirstName);
        this.setEmployeeGender(employeeGender);
        this.setEmployeeLastName(employeeLastName);
        return true;
    }
    */
    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public Integer getEmployeeSchedule() {
        return employeeSchedule;
    }

    public void setEmployeeSchedule(Integer employeeSchedule) {
        this.employeeSchedule = employeeSchedule;
    }
    

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeId != null ? employeeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeEntity)) {
            return false;
        }
        EmployeeEntity other = (EmployeeEntity) object;
        if ((this.employeeId == null && other.employeeId != null) || (this.employeeId != null && !this.employeeId.equals(other.employeeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ERMS.entity.employeeEntity[ id=" + employeeId+ " ]";
    }
    
}
