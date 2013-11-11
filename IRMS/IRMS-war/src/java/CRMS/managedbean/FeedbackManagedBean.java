/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.FeedbackEntity;
import CRMS.entity.MemberEntity;
import CRMS.session.FeedbackSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class FeedbackManagedBean {

    @EJB
    private FeedbackSessionBean feedbackSessionBean;
    private FeedbackEntity thisFeedback;
    private List<FeedbackEntity> feedbackList;
    private SelectItem[] ratingOption;
    private SelectItem[] departmentOption;

    public FeedbackManagedBean() {
    }

    @PostConstruct
    public void init() throws ExistException {
        feedbackList = feedbackSessionBean.getAllFeedbacks();
        ratingOption = this.createRatingOption();
        departmentOption = this.createDepartmentOption();
    }

    public void updateFeedbackStatus(ActionEvent event) throws IOException {
        try {
            System.out.println("in changing status" + thisFeedback.getFeedbackTitle());
            feedbackSessionBean.updateFeedbackStatus(thisFeedback, thisFeedback.getFeedbackStatus());
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when updating status", ""));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Now this feedback has changed its status", ""));
    }

    private SelectItem[] createRatingOption() {
        SelectItem[] options = new SelectItem[6];
        System.out.println("Creating rating  options");
        options[0] = new SelectItem("", "Select");
        options[1] = new SelectItem(1, "1");
        options[2] = new SelectItem(2, "2");
        options[3] = new SelectItem(3, "3");
        options[4] = new SelectItem(4, "4");
        options[5] = new SelectItem(5, "5");
        return options;
    }

    private SelectItem[] createDepartmentOption() {
        SelectItem[] options = new SelectItem[6];
        System.out.println("Creating feedback department options");
        options[0] = new SelectItem("", "Select");
        options[1] = new SelectItem("hotel", "hotel");               
        options[2] = new SelectItem("food&beverage", "food&beverage");                       
        options[3] = new SelectItem("entertainment & show", "entertainment & show");
        options[4] = new SelectItem("attraction", "attraction");
        options[5] = new SelectItem("shopping mall", "shopping mall");
        return options;
    }

    public FeedbackSessionBean getFeedbackSessionBean() {
        return feedbackSessionBean;
    }

    public void setFeedbackSessionBean(FeedbackSessionBean feedbackSessionBean) {
        this.feedbackSessionBean = feedbackSessionBean;
    }

    public FeedbackEntity getThisFeedback() {
        return thisFeedback;
    }

    public void setThisFeedback(FeedbackEntity thisFeedback) {
        this.thisFeedback = thisFeedback;
    }

    public List<FeedbackEntity> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<FeedbackEntity> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public SelectItem[] getRatingOption() {
        return ratingOption;
    }

    public void setRatingOption(SelectItem[] ratingOption) {
        this.ratingOption = ratingOption;
    }

    public SelectItem[] getDepartmentOption() {
        return departmentOption;
    }

    public void setDepartmentOption(SelectItem[] departmentOption) {
        this.departmentOption = departmentOption;
    }
    
    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Model:" + ((FeedbackEntity) event.getData()).getFeedbackOwnerEmail());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
