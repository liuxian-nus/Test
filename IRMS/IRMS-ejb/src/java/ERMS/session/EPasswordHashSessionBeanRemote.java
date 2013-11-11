/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import CRMS.session.CPasswordHashSessionBean;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface EPasswordHashSessionBeanRemote {

    CPasswordHashSessionBean getInstance();

    String hashPassword(String pwd);
    
}
