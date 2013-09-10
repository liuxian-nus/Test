/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import Exception.ExistException;
import java.util.Date;

/**
 *
 * @author Diana Wang
 */
public interface MemberTransactionSessionRemote {

    void addCoin(String memberEmail, double mtAmount) throws ExistException;

    /**
     *
     * @param memberEmail
     * @param mtAmount
     * @param mtDate
     * @param mtDepartment
     * @param mtMode
     * @param coinPay
     * @return
     * @throws ExistException
     */
    double addMemberTransaction(String memberEmail, double mtAmount, Date mtDate, String mtDepartment, boolean mtMode, boolean coinPay) throws ExistException;

    void addPoint(String memberEmail, double mtAmount) throws ExistException;
    
}
