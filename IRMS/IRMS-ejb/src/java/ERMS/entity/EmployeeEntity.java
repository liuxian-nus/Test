/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.entity;

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
 * @author Diana Wang
 */
@Entity
public class EmployeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;
    private String employeeName;
    private String employeeGender;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date employeeDob;
    private String employeePassword;
    private String employeeDepartment;
    private Integer employeeSchedule; //there are 3 shifts in a day represented by 1/2/3
    private boolean isFirstTimeLogin = true;
    private String securityQuestion;
    private String answer;
    
    @OneToMany(targetEntity = RoleEntity.class, cascade = {CascadeType.MERGE})
    private List<RoleEntity> roles = new ArrayList<RoleEntity>();

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public EmployeeEntity() {
    }


    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
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
    
    
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getEmployeeDob() {
        return employeeDob;
    }

    public void setEmployeeDob(Date employeeDob) {
        this.employeeDob = employeeDob;
    }

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

    public Integer getEmployeeSchedule() {
        return employeeSchedule;
    }

    public void setEmployeeSchedule(Integer employeeSchedule) {
        this.employeeSchedule = employeeSchedule;
    }
    

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public boolean isFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public void setIsFirstTimeLogin(boolean isFirstTimeLogin) {
        this.isFirstTimeLogin = isFirstTimeLogin;
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
