/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ATMS.managedBean;

import ATMS.entity.AttrComboEntity;
import ATMS.entity.AttrTicketEntity;
import ATMS.session.AttrComboSessionBean;
import ATMS.session.TicketSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Jieqiong
 */
@ManagedBean
@RequestScoped
public class addComboManagedBean {
    @EJB
    private AttrComboSessionBean attrComboSessionBean;
    @EJB
    private TicketSessionBean ticketSessionBean;
    private AttrComboEntity attrCombo;
    private List<String> tickets=new ArrayList<String>();
    private List<AttrTicketEntity> ticketEntities;
    //private List<AttrTicketEntity> selectedTickets;
    private List<AttrTicketEntity> selectedTickets=new ArrayList<AttrTicketEntity>();
    private AttrTicketEntity attrTicket;
    private boolean editMode;
    
    @PostConstruct
    public void init()
    {
        System.out.println("init()");
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    /**
     * Creates a new instance of addComboManagedBean
     */
    public addComboManagedBean() {
        System.out.println("addComboManagedBean()");
        attrCombo=new AttrComboEntity();
        attrTicket=new AttrTicketEntity();
    }
    
    public List<AttrTicketEntity> chooseSelectedTickets(){
        System.out.println("chooseSelectedTickets");
   //     selectedTickets=(List<AttrTicketEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectedTickets");
   //     System.out.println("get selectedTicket flash successfully");
 //       selectedTickets=new ArrayList<AttrTicketEntity>();
        List<AttrTicketEntity> tkts;
        tkts=ticketSessionBean.getAllTickets();
        
        for(int i=0;i<tkts.size();i++){
            
            attrTicket=tkts.get(i);
            System.out.println("attrTicket name: "+attrTicket.getAttrTicketName());
            if(matchedTicket(attrTicket)){
                System.out.println("chooseSelectedTickets: is a matched ticket");
                selectedTickets.add(attrTicket);    
                System.out.println("add ticket successfully");
            }           
        }    
        System.out.println("selectedTickets size: "+selectedTickets.size());
        System.out.println("selected ticket prepared");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedTickets",selectedTickets);
        System.out.println("selectedTicket flash set up successfully");

        System.out.println("tickets size: "+tickets.size());
        return selectedTickets;
        
    }


    
    
    
    public boolean matchedTicket(AttrTicketEntity attrTicket){   
        System.out.println("matchedTicket...");
        boolean b1=false;
        boolean b2=false;
        
        if(attrTicket.getAttrTicketType().equals(attrCombo.getAttrComboType())){
            System.out.println("type matched");
            b1=true;
        }
        
        if(attrTicket.getAttrTicketCluster().equals(attrCombo.getAttrComboCluster())){
            System.out.println("cluster matched");
            b2=true;
        }       
        if(b1&&b2){
            System.out.println("matched");
            return true;
        }
          
        else
            return false;
    }
    
    public void saveNewCombo(ActionEvent event) throws IOException{
        try{
            System.out.println("into addComboManagedBean: saveNewCombo"); 
  //          selectedTickets = (List<AttrTicketEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectedTickets");
            System.out.println("size of tickets: "+tickets.size());
        //    pushToTicket();
            ticketEntities=pushToTicketEntity();
            System.out.println("pushed to ticket entities");
            System.out.println("start check ticket type");
            if(!checkTicketType(ticketEntities)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket type doesn't match", ""));
                return;
            }
            if(!checkTicketCluster(ticketEntities)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ticket cluster doesn't match", ""));
                return;
            }
            attrCombo.setAttrTickets(ticketEntities);
            attrComboSessionBean.addAttrCombo(attrCombo);
            System.out.println("new combo added");
        }catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding a new combo", ""));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Combo Saved.", ""));
        attrCombo=new AttrComboEntity();
    }
    
    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addAttrCombos.xhtml");
    }
    
    public boolean checkTicketType(List<AttrTicketEntity> ticketEntities){
        System.out.println("addComboManagedBean: checkTicketType");
        String type1=attrCombo.getAttrComboType();
        String type2="";
        System.out.println("type1:"+type1);
        for(int i=0;i<ticketEntities.size();i++){
            System.out.println("i: "+i);
            System.out.println("get ticket");
        //    attrTicket=tickets.get(1);
            attrTicket=ticketEntities.get(i);
            System.out.println("attrTicket name: "+attrTicket.getAttrTicketName());
            type2=attrTicket.getAttrTicketType();
            System.out.println("type2:"+type2);
            if(!type1.equals(type2)) {
                System.out.println("unmatched type");
                return false;
            }
        }
        System.out.println("type matched");
        return true;
    }
    
    
    public boolean checkTicketCluster(List<AttrTicketEntity> ticketEntities){
        System.out.println("addComboManagedBean: checkTicketCluster");
        String cluster1=attrCombo.getAttrComboCluster();
        String cluster2="";
    //    System.out.println("type1:"+type1);
        for(int i=0;i<ticketEntities.size();i++){
            System.out.println("i: "+i);
            System.out.println("get ticket");
        //    attrTicket=tickets.get(1);
            attrTicket=ticketEntities.get(i);
            System.out.println("attrTicket name: "+attrTicket.getAttrTicketName());
            cluster2=attrTicket.getAttrTicketCluster();
       //     System.out.println("type2:"+type2);
            if(!cluster1.equals(cluster2)) {
                System.out.println("unmatched type");
                return false;
            }
        }
        System.out.println("type matched");
        return true;
    }
    
    public void pushToTicket() throws ExistException{
        int i = 0;
        Long id;
        System.out.println("pushToTicket...");
        System.out.println("ticket.get(i) "+tickets.get(i));
        id = Long.valueOf(tickets.get(i));
        attrCombo.getAttrTickets().add(ticketSessionBean.getTicketById(id));
        System.out.println("ticketName: "+attrCombo.getAttrTickets().get(i).getAttrTicketName());
        
        while(i < (tickets.size()-1)){
            i++;
            id = Long.valueOf(tickets.get(i));
            attrCombo.getAttrTickets().add(ticketSessionBean.getTicketById(id));
            System.out.println("ticketName: "+attrCombo.getAttrTickets().get(i).getAttrTicketName());
        }      
    }
    
    public List<AttrTicketEntity> pushToTicketEntity(){
        System.out.println("pushToTicketEntity...");
        Long id;
        List<AttrTicketEntity> tkts=new ArrayList<AttrTicketEntity>();
        for(int i=0;i<tickets.size();i++){
            id= Long.valueOf(tickets.get(i));
            attrTicket=ticketSessionBean.getTicketById(id);
            System.out.println("attrTicket name: "+attrTicket.getAttrTicketName());
            tkts.add(attrTicket);
        }
        return tkts;
    }
    
    public void changeToAttrTicketEntity(){
        
    }

    public AttrComboSessionBean getAttrComboSessionBean() {
        return attrComboSessionBean;
    }

    public void setAttrComboSessionBean(AttrComboSessionBean attrComboSessionBean) {
        this.attrComboSessionBean = attrComboSessionBean;
    }

    public TicketSessionBean getTicketSessionBean() {
        return ticketSessionBean;
    }

    public void setTicketSessionBean(TicketSessionBean ticketSessionBean) {
        this.ticketSessionBean = ticketSessionBean;
    }

    public AttrComboEntity getAttrCombo() {
        return attrCombo;
    }

    public void setAttrCombo(AttrComboEntity attrCombo) {
        this.attrCombo = attrCombo;
    }

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }

    

    public AttrTicketEntity getAttrTicket() {
        return attrTicket;
    }

    public void setAttrTicket(AttrTicketEntity attrTicket) {
        this.attrTicket = attrTicket;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    
        public List<AttrTicketEntity> getTicketEntities() {
        return ticketEntities;
    }

    public void setTicketEntities(List<AttrTicketEntity> ticketEntities) {
        this.ticketEntities = ticketEntities;
    }

    public List<AttrTicketEntity> getSelectedTickets() {
        System.out.println("getSelectedTickets()");
        return selectedTickets;
    }

    public void setSelectedTickets(List<AttrTicketEntity> selectedTickets) {
        //selectedTickets=(List<AttrTicketEntity>) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("selectedTickets");
        this.selectedTickets = selectedTickets;
    }
    
    
}
