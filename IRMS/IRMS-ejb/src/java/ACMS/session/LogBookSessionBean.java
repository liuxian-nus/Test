/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ACMS.session;

import ACMS.entity.LogBookEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author liuxian
 */
@Stateless
@LocalBean
public class LogBookSessionBean implements LogBookSessionBeanRemote {

    @PersistenceContext(unitName="IRMS-ejbPU")
    private EntityManager em;
    private LogBookEntity log = new LogBookEntity();

    public LogBookSessionBean() {
    }

    @Override
    public List<LogBookEntity> getAllLogs(){
        Query q = em.createQuery("SELECT l FROM LogBookEntity l");
        List logList = new ArrayList<LogBookEntity>();
        for (Object o : q.getResultList()) {
            LogBookEntity l = (LogBookEntity) o;
            logList.add(l);
        }
        return logList;
    }

    @Override
    public List<LogBookEntity> getLogByShift(int logShift) throws ExistException {
        Query query = em.createQuery("SELECT l FROM LogBookEntity l WHERE l.logShift = " + logShift);
        System.err.println("getLogByShift: " + logShift);
        //query.setParameter("employeeName", employeeName);
        List<LogBookEntity> thisLogList = null;
        thisLogList = query.getResultList();
        if (thisLogList == null) {
            throw new ExistException("This Shift does not have logs yet!");
        }
        return thisLogList;
    }

    @Override
    public LogBookEntity addLog(LogBookEntity thisLog) {
        System.out.println("add log session bean");
        em.persist(thisLog);
        return thisLog;
    }
    
    @Override
        public void markResolved(LogBookEntity thisLog) {
        System.out.println("mark resolve session bean");
        thisLog.setResolved(true);
        em.merge(thisLog);
    }
    
    @Override
    public LogBookEntity removeLog(Long logId) {
        LogBookEntity thisLog = em.find(LogBookEntity.class, logId);
        System.out.println("Remove this log" + logId);
        em.remove(thisLog);
        return thisLog;
    }
    
    @Override
    public LogBookEntity updateLog(Long logId, String title, String content, String remark) {
        LogBookEntity thisLog = em.find(LogBookEntity.class, logId);
        System.out.println("Update this log" + logId);
        thisLog.setLogTitle(title);
        thisLog.setLogText(content);
        thisLog.setRemark(remark);
        em.merge(thisLog);
        return thisLog;
    }
}
