/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.FeedbackEntity;
import CRMS.entity.MemberEntity;
import CRMS.session.FeedbackSessionBean;
import ERMS.session.EmailSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.io.Serializable;
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
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class FeedbackManagedBean implements Serializable {

    @EJB
    private FeedbackSessionBean feedbackSessionBean;
    @EJB
    EmailSessionBean emailSessionBean;
    private FeedbackEntity thisFeedback;
    private List<FeedbackEntity> feedbackList;
    private SelectItem[] ratingOption;
    private SelectItem[] departmentOption;
    private SelectItem[] statusOption;
    private CartesianChartModel categoryModel;
    private PieChartModel pieModel;
    private String feedbackReply;
    private String receiverEmail;

    public FeedbackManagedBean() {
    }

    private void createCategoryModel() {
        try {
            System.out.println("creating bar chart....");
            categoryModel = new CartesianChartModel();

            ChartSeries feedbackChart = new ChartSeries();
            feedbackChart.setLabel("Department");

            feedbackChart.set("hotel", feedbackSessionBean.calculateAverageRatingByDepartment("hotel"));
            feedbackChart.set("entertainment show", feedbackSessionBean.calculateAverageRatingByDepartment("entertainment show"));
            feedbackChart.set("food and beverage", feedbackSessionBean.calculateAverageRatingByDepartment("food and beverage"));
            feedbackChart.set("convention center", feedbackSessionBean.calculateAverageRatingByDepartment("convention center"));
            feedbackChart.set("attraction", feedbackSessionBean.calculateAverageRatingByDepartment("attraction"));
            feedbackChart.set("shopping mall", feedbackSessionBean.calculateAverageRatingByDepartment("shopping mall"));

            categoryModel.addSeries(feedbackChart);
        } catch (Exception e) {
            System.err.println("error when creating bar chart");
            e.printStackTrace();
        }
    }

    private void createPieModel() {
        pieModel = new PieChartModel();

        pieModel.set("hotel", feedbackSessionBean.countFeedbackByDepartment("hotel"));
        pieModel.set("shopping Mall", feedbackSessionBean.countFeedbackByDepartment("shopping mall"));
        pieModel.set("entertainment show", feedbackSessionBean.countFeedbackByDepartment("entertainment show"));
        pieModel.set("food and beverage", feedbackSessionBean.countFeedbackByDepartment("food and beverage"));
        pieModel.set("attraction", feedbackSessionBean.countFeedbackByDepartment("attraction"));
        pieModel.set("convention center", feedbackSessionBean.countFeedbackByDepartment("convention center"));
    }

    @PostConstruct
    public void init() throws ExistException {
        feedbackList = feedbackSessionBean.getAllFeedbacks();
        ratingOption = this.createRatingOption();
        departmentOption = this.createDepartmentOption();
        statusOption = this.createStatusOption();
        createCategoryModel();
        createPieModel();

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

    public void replyFeedback(ActionEvent event) throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.err.println("feedback: " + thisFeedback.getFeedbackOwnerEmail());
        request.getSession().setAttribute("feedback", thisFeedback);
        FacesContext.getCurrentInstance().getExternalContext().redirect("feedbackReply.xhtml");
    }

    public void feedbackReplyInit(PhaseEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        thisFeedback = (FeedbackEntity) request.getSession().getAttribute("feedback");
    }
    
    public void sendFeedbackReply(ActionEvent event) throws IOException{
        System.err.println("thisFeedback: "+thisFeedback.getFeedbackOwnerEmail());
        System.err.println("feedbackReply: "+feedbackReply);
        emailSessionBean.createFeedbackReply(thisFeedback, feedbackReply);
        FacesContext.getCurrentInstance().getExternalContext().redirect("feedbackManagement.xhtml");
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
    
    private SelectItem[] createStatusOption() {
        SelectItem[] options = new SelectItem[3];
        System.out.println("Creating status options");
        options[0] = new SelectItem("", "Select");
        options[1] = new SelectItem(1, "new");
        options[2] = new SelectItem(2, "handled");
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
        System.out.println("get feedbackList from managed bean");
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

    public CartesianChartModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CartesianChartModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Model:" + ((FeedbackEntity) event.getData()).getFeedbackOwnerEmail());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getFeedbackReply() {
        return feedbackReply;
    }

    public void setFeedbackReply(String feedbackReply) {
        this.feedbackReply = feedbackReply;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public EmailSessionBean getEmailSessionBean() {
        return emailSessionBean;
    }

    public void setEmailSessionBean(EmailSessionBean emailSessionBean) {
        this.emailSessionBean = emailSessionBean;
    }

    public SelectItem[] getStatusOption() {
        return statusOption;
    }

    public void setStatusOption(SelectItem[] statusOption) {
        this.statusOption = statusOption;
    }
}
