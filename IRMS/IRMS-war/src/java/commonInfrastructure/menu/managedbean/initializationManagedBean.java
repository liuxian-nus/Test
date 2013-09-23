/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.menu.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.entity.RoleEntity;
import ERMS.session.EPasswordHashSessionBean;
import ERMS.session.EmployeeSessionBean;
import ERMS.session.RoleSessionBean;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@RequestScoped
public class initializationManagedBean implements Serializable {
    @EJB
    private EmployeeSessionBean employeeSessionBean = new EmployeeSessionBean();;
    @EJB
    private RoleSessionBean roleSessionBean;
    @EJB
    private EPasswordHashSessionBean ePasswordHashSessionBean;

    private EmployeeEntity employee;
    private RoleEntity role;
    
    @PostConstruct
    public void init() {
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
    
    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeEntity employee) {
        this.employee = employee;
    }
    
    public RoleEntity getRole(){
        return role;
    }
    
    public void setRole (RoleEntity role){
        this.role = role;
    }
    
    public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
    
    public void createSuperAdmin(){
        System.out.println("go to create super admin");
        
        role = new RoleEntity();
        role.setRoleId(10);
        role.setRoleName("SuperAdmin");
        System.out.println("Create role :"+role.getRoleName());
        
        employee = new EmployeeEntity();
        employee.setEmployeeId("A0000"); //business assumption: maximum employee number 9999
        employee.setEmployeeName("SuperAdmin");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("A0000"));
        System.out.println("finished hashing");
        employee.setEmployeeEmail("is3102.it09@gmail.com");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId()+","+ employee.getEmployeeName()+","+employee.getEmployeePassword());
        
        try {
            System.out.println("Saving Super Admin....");
            
            employeeSessionBean.addEmployee(employee);
            System.out.println("Super Admin saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.out.println("Insert Employee into database");
        
        addMessage("Super Admin Created!");
    }
    
    public void createSystemUser(){
        System.out.println("go to create ACMS user");
        
        role = new RoleEntity();
        role.setRoleId(20);
        role.setRoleName("ACMSAdmin");
        System.out.println("Create role :"+role.getRoleName());
        
        employee = new EmployeeEntity();
        employee.setEmployeeId("B0000"); //business assumption: maximum employee number 9999
        employee.setEmployeeName("ACMSAdmin");
        employee.setEmployeePassword(ePasswordHashSessionBean.hashPassword("B0000"));
        System.out.println("finished hashing");
        employee.addRole(role);
        employee.setIsFirstTimeLogin(false);
        System.out.println("Create employee :" + employee.getEmployeeId()+","+ employee.getEmployeeName()+","+employee.getEmployeePassword());
        
        try {
            System.out.println("Saving ACMSAdmin....");
            
            employeeSessionBean.addEmployee(employee);
            System.out.println("ACMSAdmin saved.....");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding admin", ""));
            return;
        }
        System.out.println("Insert Employee into database");
        
        addMessage("ACMSAdmin Created!");
    }
    
    
    
    //Add new test cases below!!!!!!!!!
}
