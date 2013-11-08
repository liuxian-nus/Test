/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FBMS.session;

import FBMS.entity.IndReservationEntity;
import FBMS.entity.OrderEntity;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface FBEmailSessionBeanRemote {

    boolean sendConfirmation(String toEmailAddress, IndReservationEntity ire);
    boolean sendConfirmation (String toEmailAddress,OrderEntity oe);
}
