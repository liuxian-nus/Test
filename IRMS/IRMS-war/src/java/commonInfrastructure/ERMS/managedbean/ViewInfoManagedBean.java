/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.ERMS.managedbean;

import ERMS.entity.EmployeeEntity;
import ERMS.session.EmployeeSessionRemote;
import Exception.ExistException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ViewInfoManagedBean {
    @EJB
    private EmployeeSessionRemote em;
    @EJB
    private EmployeeEntity selectedEmployee;
    private boolean editMode;
    private Long id;
    
    /** Creates a new instance of ViewInfoManagedBean */
    public ViewInfoManagedBean() {
        selectedEmployee = new EmployeeEntity();
    }
    
    public boolean isEditMode() {  
        return editMode;  
    }
    
    public EmployeeEntity getEmployees() throws ExistException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Long loginId = (Long) request.getSession().getAttribute("userId");
        return em.getEmployeeById(loginId);
    }

    public void deleteEmployee(ActionEvent event) throws ExistException {
        setId((Long)event.getComponent().getAttributes().get("code1"));
        getEm().removeEmployee(getId());
    }
    
    public void saveChanges(ActionEvent event) throws ExistException
    {
        em.updateEmployee(selectedEmployee.getEmployeeId(),selectedEmployee.getEmployeeName(),selectedEmployee.getEmployeeDob(),selectedEmployee.getEmployeeDepartment(),selectedEmployee.getEmployeeSchedule(),selectedEmployee.getRoles(),selectedEmployee.getEmployeeGender(),selectedEmployee.getEmployeePassword());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Changes saved.", ""));        
    }

    public EmployeeSessionRemote getEm() {
        return em;
    }

    public void setEm(EmployeeSessionRemote em) {
        this.em = em;
    }

    public EmployeeEntity getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(EmployeeEntity selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
