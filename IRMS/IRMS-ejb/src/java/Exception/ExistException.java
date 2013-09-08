/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Exception;
import javax.ejb.ApplicationException;
/**
 *
 * @author liuxian
 */
@ApplicationException(rollback=true)
public class ExistException extends Exception{
    public ExistException() {}
    public ExistException(String msg) {
        super(msg);
    }
}
