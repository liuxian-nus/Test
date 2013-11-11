/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import sun.misc.BASE64Encoder;

/**
 *
 * @author liuxian
 */
@Stateless
@LocalBean
public class EPasswordHashSessionBean implements EPasswordHashSessionBeanRemote {

      CRMS.session.CPasswordHashSessionBean instance;

    private void EPasswordHashSessionBean() {
    }

    @Override
    public String hashPassword(String pwd) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");  
        } catch (NoSuchAlgorithmException e) {
        }
        try {
            md.update(pwd.getBytes("UTF-8"));  
        } catch (UnsupportedEncodingException e) {
        }

        byte raw[] = md.digest();  
        String hash = (new BASE64Encoder()).encode(raw);  
        return hash;  
    }

    @Override
    public CRMS.session.CPasswordHashSessionBean getInstance()  
    {
        if (instance == null) {
            instance = new CRMS.session.CPasswordHashSessionBean();
        }
        return instance;
    }

}