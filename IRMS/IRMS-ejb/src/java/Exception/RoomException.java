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
public class RoomException extends Exception{
    public RoomException() {}
    public RoomException(String msg) {
        super(msg);
    }
}
