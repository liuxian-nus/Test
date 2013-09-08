/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exception;
import javax.ejb.ApplicationException;
/**
 *
 * @author liudazhi
 */
@ApplicationException(rollback=true)
public class ExistException extends Exception{
    public ExistException() {}
    public ExistException(String msg) {
        super(msg);
    }
}
