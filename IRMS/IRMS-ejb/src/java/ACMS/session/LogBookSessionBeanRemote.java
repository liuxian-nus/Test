/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.LogBookEntity;
import Exception.ExistException;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface LogBookSessionBeanRemote {

    LogBookEntity addLog(LogBookEntity thisLog);

    List<LogBookEntity> getAllLogs();

    List<LogBookEntity> getLogByShift(int logShift) throws ExistException;

    void markResolved(LogBookEntity thisLog);

    LogBookEntity removeLog(Long logId);

    LogBookEntity updateLog(Long logId, String title, String content, String remark);
    
}
