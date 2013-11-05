/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import CRMS.entity.MemberEntity;
import Exception.ExistException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jieqiong
 */
@Stateless
@LocalBean
public class MemberSegmentationSessionBean {
    /* This session bean caters for managers to select the members who match with particular requirements for the attibutes.
     * For example, all subscribers, all female members, all Chinese members etc.
     * Manager may select "Any" to indicate that no specific requirement for an attribute
     * For example, if "Any" is seleted for gender, the all female, male and others members will be returned.
     */
    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    
    MemberEntity member;
    
    public MemberSegmentationSessionBean(){
        
    }
    
    public List<MemberEntity> getAllSubscribers() throws ExistException{
        System.out.println("memberSegmentationSessionBean:getAllSubscribers()");
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List selectedMembers = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
            if(m.isSubscriber()){
                System.out.println(m.getMemberName()+" is a subscriber");
                selectedMembers.add(m);
            }                
        }
        if(selectedMembers.isEmpty())
            throw new ExistException("no member has subscribed to the regular email service");
        System.out.println("all subscribers found");
        return selectedMembers;
    }
    
    public List<MemberEntity> getAllVIPs() throws ExistException{
        System.out.println("memberSegmentationSessionBean: getAllVIPs");
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        List selectedMembers = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
            if(m.isVIP()){
                System.out.println(m.getMemberName()+" is VIP");
                selectedMembers.add(m);
            }                
        }
        if(selectedMembers.isEmpty())
            throw new ExistException("no member is VIP");
        System.out.println("all selected member found");
        return selectedMembers;
    }
    
    public List<MemberEntity> getAllSelectedGenderMembers(String gender) throws ExistException{
        System.out.println("memberSegmentationSessionBean:getAllSelectedGenderMembers");
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        if(gender.equals("Any"))
            return q.getResultList();
        List selectedMembers = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
            if(m.getGender().equals(gender)){
                System.out.println(m.getMemberName()+" matches with the gender selected");
                selectedMembers.add(m);
            }                
        }
        if(selectedMembers.isEmpty())
            throw new ExistException("no member match with the selected gender");
        System.out.println("all selected member found");
        return selectedMembers;
    }
    
    public List<MemberEntity> getAllSelectedNationalityMembers(String nationality) throws ExistException{
        System.out.println("memberSegmentationSessionBean:getAllSelectedNationalityMembers");
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        if(nationality.equals("Any"))
            return q.getResultList();
        List selectedMembers = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
            if(m.getGender().equals(nationality)){
                System.out.println(m.getMemberName()+" matches with the nationality selected");
                selectedMembers.add(m);
            }                
        }
        if(selectedMembers.isEmpty())
            throw new ExistException("no member match with the selected nationality");
        System.out.println("all selected member found");
        return selectedMembers;
    }
    
    public List<MemberEntity> getAllSelectedMaritalStatusMembers(String maritalStatus) throws ExistException{
        System.out.println("memberSegmentationSessionBean: getAllSelectedMaritalStatusMembers");
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        if(maritalStatus.equals("Any"))
            return q.getResultList();
        List selectedMembers = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
            if(m.getMaritalStatus().equals(maritalStatus)){
                System.out.println(m.getMemberName()+" matches with the marital status selected");
                selectedMembers.add(m);
            }                
        }
        if(selectedMembers.isEmpty())
            throw new ExistException("no member match with the selected marital status");
        System.out.println("all selected member found");
        return selectedMembers;
    }
    
    public List<MemberEntity> getAllSelectedAgeRangeMembers(String range) throws ExistException{
        /* ranges of age to be selected by the managers are:
         * Below 20
         * 21-30
         * 31-40
         * 41-50
         * 51-60
         * Above 60
         */
        System.out.println("memberSegmentationSessionBean:getAllSelectedAgeRangeMembers");
        Query q = em.createQuery("SELECT m FROM MemberEntity m");
        if(range.equals("Any"))
            return q.getResultList();
        List selectedMembers = new ArrayList<MemberEntity>();
        for (Object o : q.getResultList()) {
            MemberEntity m = (MemberEntity) o;
            int age=calculateMemberAge(m);
            System.out.println("age: "+age);
            String ageRange=convertToAgeRange(age);
            System.out.println("age range: "+ageRange);
            if(ageRange.equals(range)){
                System.out.println(m.getMemberName()+" matches with the age range selected");
                selectedMembers.add(m);
            }                
        }
        if(selectedMembers.isEmpty())
            throw new ExistException("no member match with the selected age range");
        System.out.println("all selected member found");
        return selectedMembers;
    }
    
    public int calculateMemberAge(MemberEntity m){
        System.out.println("memberSegmentationSessionBean: calculateMemberAge");
        Date dateOfBirth=m.getMemberDob();
        int year=dateOfBirth.getYear();
        return 2013-year;
    }
    
    public String convertToAgeRange(int age){
        System.out.println("memberSegmentationSessionBean: convertToAgeRange");
        if(age<=20) return "Below 20";
        else if(age>=21&&age<=30) return "21-30";
        else if (age>=31&&age<=40) return "31-40";
        else if (age>=41&&age<=50) return "41-50";
        else if (age>=51&&age<=60) return "51-60";
        else return "Above 60";
    }
    

    public void persist(Object object) {
        em.persist(object);
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}
