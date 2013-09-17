/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commonInfrastructure.ERMS.managedbean;

import ERMS.entity.EmployeeEntity;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@RequestScoped
public class SearchResultManagedBean {

    private List<EmployeeEntity> employees;

    /**
     * Creates a new instance of SearchResultManagedBean
     */
    public SearchResultManagedBean() {
    }

    public List<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public void initView(PhaseEvent event) {
        employees = (List<EmployeeEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("Employees");
    }
}
