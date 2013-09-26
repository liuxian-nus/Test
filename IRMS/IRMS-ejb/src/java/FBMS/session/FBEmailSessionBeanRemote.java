/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.IndReservationEntity;

/**
 *
 * @author Diana Wang
 */
public interface FBEmailSessionBeanRemote {

    boolean sendConfirmation(String toEmailAddress, IndReservationEntity ire);
    
}
