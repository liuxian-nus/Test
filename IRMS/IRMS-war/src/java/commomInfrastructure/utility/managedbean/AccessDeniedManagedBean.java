
package commomInfrastructure.utility.managedbean;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class AccessDeniedManagedBean {

   /** Creates a new instance of AccessDeniedManagedBean */
    public AccessDeniedManagedBean() {
    }
    public void reDirect(ActionEvent event) throws IOException{
         FacesContext.getCurrentInstance().getExternalContext().redirect("/index.xhtml");
    }
}
