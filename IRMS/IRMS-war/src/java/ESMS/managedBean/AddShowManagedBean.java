/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.managedBean;

import ESMS.entity.ShowEntity;
import ESMS.entity.ShowScheduleEntity;
import ESMS.entity.ShowTicketEntity;
import ESMS.session.ShowScheduleSessionBean;
import ESMS.session.ShowSessionBean;
import ESMS.session.ShowTicketSessionBean;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class AddShowManagedBean {

    @EJB
    ShowSessionBean showSessionBean;
    @EJB
    ShowScheduleSessionBean showScheduleSessionBean;
    @EJB
    ShowTicketSessionBean showTicketSessionBean;
    private ShowEntity show;
    private ShowScheduleEntity showSchedule;
    private ShowTicketEntity showTicket;
    private Long showId;
    private Date currentDate = new Date();
    private static final int BUFFER_SIZE = 1024;
    private UploadedFile file;
    private Boolean uploadMode;
    private Long id;

    public AddShowManagedBean() {
        show = new ShowEntity();
        showSchedule = new ShowScheduleEntity();
        showTicket = new ShowTicketEntity();
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {

        System.err.println("Uploading Image...");
        System.err.println(showId);

        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();

        System.out.println(extContext.getRealPath("handleFileUpload: " + event.getFile().getFileName()));

        byte[] buffer = new byte[BUFFER_SIZE];
        InputStream inputStream = event.getFile().getInputstream();
        inputStream.read(buffer);

        System.out.println("handleFileUpload: " + buffer);
        System.out.println("showId:" + showId);

        showSessionBean.uploadFile(showId, buffer);

        FacesMessage msg = new FacesMessage("File Description", "file name: " + event.getFile().getFileName() + " file size: " + event.getFile().getSize() / 1024 + " Kb content type: " + event.getFile().getContentType() + "The file was uploaded.");
//            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void saveNewShow(ActionEvent event) throws IOException {
        System.err.println("Saving New Show...");
        System.err.println("Ticket Commission percentage=" + show.getTicketCommission());
        System.err.println("Ticket Type=" + show.getShowType());
        showSessionBean.addShow(getShow());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().setAttribute("showId", Long.valueOf(getShow().getShowId()));

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Show saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("addShow.xhtml");
    }

    public void saveNewShowSchedule(ActionEvent event) throws IOException {
        System.err.println("Saving New Show Schedule...");
        showScheduleSessionBean.addShowSchedule(getShowSchedule());
        System.err.println(showId);
        System.err.println(showSchedule.getShowDate());
        showSessionBean.addShowSchedule(showId, showSchedule);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Show Schedule saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("addShow.xhtml");
    }

    public void saveNewShowTicket(ActionEvent event) throws IOException {
        System.err.println("Saving New Show Ticket...");
        showTicketSessionBean.addShowTicket(getShowTicket());
        showSessionBean.addShowTicket(showId, showTicket);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Show Ticket saved.", ""));
        FacesContext.getCurrentInstance().getExternalContext().redirect("addShow.xhtml");
    }

    public void oneMore(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("addShow.xhtml");
    }

    public boolean isExternal() {
        return ("External".equals(show.getShowType()));
    }

    //Getter and Setters
    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Long getShowId() {
        System.err.println("show id: " + showId);
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public ShowEntity getShow() {
        return show;
    }

    public void setShow(ShowEntity show) {
        this.show = show;
    }

    public ShowScheduleEntity getShowSchedule() {
        return showSchedule;
    }

    public void setShowSchedule(ShowScheduleEntity showSchedule) {
        this.showSchedule = showSchedule;
    }

    public ShowSessionBean getShowSessionBean() {
        return showSessionBean;
    }

    public void setShowSessionBean(ShowSessionBean showSessionBean) {
        this.showSessionBean = showSessionBean;
    }

    public ShowScheduleSessionBean getShowScheduleSessionBean() {
        return showScheduleSessionBean;
    }

    public void setShowScheduleSessionBean(ShowScheduleSessionBean showScheduleSessionBean) {
        this.showScheduleSessionBean = showScheduleSessionBean;
    }

    public ShowTicketSessionBean getShowTicketSessionBean() {
        return showTicketSessionBean;
    }

    public void setShowTicketSessionBean(ShowTicketSessionBean showTicketSessionBean) {
        this.showTicketSessionBean = showTicketSessionBean;
    }

    public ShowTicketEntity getShowTicket() {
        return showTicket;
    }

    public void setShowTicket(ShowTicketEntity showTicket) {
        this.showTicket = showTicket;
    }

    public UploadedFile getFile() {
        System.err.println("Uploaded file:" + file.getFileName());
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Boolean getUploadMode() {
        return uploadMode;
    }

    public void setUploadMode(Boolean uploadMode) {
        this.uploadMode = uploadMode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
