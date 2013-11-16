/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.entity.PromotionEntity;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import CRMS.session.VIPSessionBean;
import ERMS.entity.EmployeeEntity;
import ERMS.session.EmailSessionBean;
import ERMS.session.EmployeeSessionBean;
import Exception.ExistException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Administrator
 */
@ManagedBean
@ViewScoped
public class MemberManagedBean {

    @EJB
    private EmployeeSessionBean employeeSessionBean;
    @EJB
    private VIPSessionBean vIPSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;
    @EJB
    private MemberTransactionSessionBean transactionSessionBean;
    @EJB
    private EmailSessionBean emailSessionBean;
    private MemberEntity member;
    private String searchEmail;
    private int birthMonth;
    private int ageYoung;
    private int ageOld;
    private String nationality;
    private String maritalStatus;
    private List<MemberEntity> memberList = new ArrayList<MemberEntity>();
    private List<MemberEntity> memberSelect = new ArrayList<MemberEntity>();
    private List<String> nationalityList;
    private List<MemberEntity> filteredMember = new ArrayList<MemberEntity>();
    private SelectItem[] nationalityOptions;
    private SelectItem[] genderOptions;
    private CartesianChartModel categoryModelMaritalStatus;
    private CartesianChartModel categoryModelNationality;
    private CartesianChartModel categoryModelAge;
    private PieChartModel pieModel;
    private List<MemberEntity> vips = new ArrayList<MemberEntity>();
    private List<MemberEntity> supervips = new ArrayList<MemberEntity>();
    private String employeeId;

    /**
     * Creates a new instance of SearchMemberManagedBean
     */
    public MemberManagedBean() {
        member = new MemberEntity();

    }

    @PostConstruct
    public void init() throws ExistException {
        memberList = new ArrayList<MemberEntity>();
        memberSelect = new ArrayList<MemberEntity>();
        vips = new ArrayList<MemberEntity>();
        supervips = new ArrayList<MemberEntity>();
        memberList = memberSessionBean.getAllMembers();
        Date today = new Date();
        memberSelect = memberSessionBean.getMemberByBirthMonth(today.getMonth());
        nationalityList = memberSessionBean.getAllNationalities();
        nationalityOptions = createNationalityOptions(nationalityList);
        genderOptions = createGenderOptions();
        System.out.println("MemberManagedBean init");
        vips = vIPSessionBean.getVIPs();
        supervips = vIPSessionBean.getSuperVIPs();
        System.err.println("size 1: " + vips.size() + "size 2: " + supervips.size());
        try {
            createCategoryModelAge();
            createCategoryModelMaritalStatus();
            createCategoryModelNationality();
            createPieModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> complete(String query) {
        List<String> results = new ArrayList<String>();
        List<MemberEntity> members = memberSessionBean.getAllMembers();

        for (Object o : members) {
            MemberEntity this_member = (MemberEntity) o;
            if (this_member.getMemberEmail().startsWith(query) && this_member.isVIP()) {
                results.add(this_member.getMemberEmail());
            }
        }
        return results;
    }

    public List<MemberEntity> getAllMembers() throws ExistException, IOException {
        System.err.println("in search member managed bean");
        return memberSessionBean.getAllMembers();
    }

    public List<EmployeeEntity> getCRMEmployees() throws ExistException {
        return employeeSessionBean.getCRMEmployees();
    }

    public void checkTransaction(ActionEvent event) throws IOException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().setAttribute("memberE", member);
        System.err.println("Put Member:" + member.getMemberEmail());
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberT", member);
        FacesContext.getCurrentInstance().getExternalContext().redirect("listTransactionResult.xhtml");
    }

    //wrong method implementation:  javax.el.MethodNotFoundException
    public List<MemberTransactionEntity> getTransactionsByMemberEmail(ActionEvent event) throws ExistException, IOException {
        System.err.println("in getting member transactions: member managed bean");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        System.out.println("member is: " + member.getMemberEmail());
        try {
            List<MemberTransactionEntity> transactions = transactionSessionBean.getTransactionsByMemberEmail(member.getMemberEmail());
            System.out.println("we are after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("transactions", transactions);
            request.getSession().setAttribute("member", member);
            FacesContext.getCurrentInstance().getExternalContext().redirect("listMemberTransactions.xhtml");
            return transactions;
        } catch (Exception e) {
            System.out.println("error occured when getting transactions");
            return null;
        }
    }

    public void assignEmployee(ActionEvent event) {
        System.out.println("inside assign employee function, now the employee ID is " + employeeId + "member is " + member.getMemberEmail());

        try {
            member.setContactEmployee(employeeId);
            memberSessionBean.updateMember(member);
            System.out.println("after setting member");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee has been assigned", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when assigning employee", ""));
        }

    }

    public void searchByEmail() throws IOException, ExistException {
        System.out.println("SearchMemberManagedBean: search by email: " + searchEmail);

        //      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //      HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        member = memberSessionBean.getMemberByEmail(getSearchEmail());
        //      System.out.println("before search by member email:" + member.getMemberEmail());
        if (member == null) {
            System.out.println("in SearchMemberManagedBean: member doesn't exist");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "member doesn't exist", ""));
            return;
        } else if (member.isVIP()) {
            System.out.println("isVIP");
            setMember(member);
            System.out.println("after search by member email:" + member.getMemberEmail());
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("Member", member);
            System.out.println("after setting parameter");
            FacesContext.getCurrentInstance().getExternalContext().redirect("searchMemberResult.xhtml");
        } else {
            System.out.println("member is not VIP yet");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "member is not VIP yet", ""));
            return;
        }

    }

    public void getMemberByBirthMonth(ActionEvent event) throws IOException, ExistException {
        System.out.println("In get member by dob month: " + birthMonth);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            memberList = memberSessionBean.getMemberByBirthMonth(birthMonth);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberList", memberList);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching by birthday month", ""));
            return;
        }
    }

    public void getMemberByNationality(ActionEvent event) throws IOException, ExistException {
        System.out.println("In get member by nationality: " + nationality);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            memberList = memberSessionBean.getMemberByNationality(nationality);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberList", memberList);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching by nationality", ""));
            return;
        }
    }

    public void getMemberByMaritalStatus(ActionEvent event) throws IOException, ExistException {
        System.out.println("In get member by maritalStatus: " + maritalStatus);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            memberList = memberSessionBean.getMemberByMaritalStatus(maritalStatus);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberList", memberList);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching by nationality", ""));
            return;
        }
    }

    public void getMemberByAgeRange(ActionEvent event) throws IOException, ExistException {
        System.out.println("In get member by age range: " + ageYoung + " " + ageOld);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            memberList = memberSessionBean.getMemberByAgeRange(ageYoung, ageOld);
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberList", memberList);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching by nationality", ""));
            return;
        }
    }

    public void getMemberBySubscription(ActionEvent event) throws IOException, ExistException {
        System.out.println("In get member by subscription");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        try {
            memberList = memberSessionBean.getMemberBySubscription();
            FacesContext.getCurrentInstance().getExternalContext().getFlash().put("memberList", memberList);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching by nationality", ""));
            return;
        }
    }

    private SelectItem[] createNationalityOptions(List<String> nationalityList) {
        SelectItem[] options = new SelectItem[nationalityList.size() + 1];

        System.err.println("Creating filter options");
        options[0] = new SelectItem("", "Select");
        for (int i = 0; i < nationalityList.size(); i++) {
            options[i + 1] = new SelectItem(nationalityList.get(i), nationalityList.get(i));
        }
        return options;
    }

    private SelectItem[] createGenderOptions() {
        SelectItem[] options = new SelectItem[4];
        System.out.println("Creating gender options");
        options[0] = new SelectItem("", "Select");
        options[1] = new SelectItem("Male", "Male");
        options[2] = new SelectItem("Female", "Female");
        options[3] = new SelectItem("Not Specified", "Not Specified");
        return options;
    }

    private void createCategoryModelMaritalStatus() {
        try {
            System.out.println("creating marital status bar chart....");
            categoryModelMaritalStatus = new CartesianChartModel();

            ChartSeries maritalChart = new ChartSeries();
            maritalChart.setLabel("MaritalStatus");

            maritalChart.set("Single", memberSessionBean.getMemberByMaritalStatus("Single").size());
            maritalChart.set("Married", memberSessionBean.getMemberByMaritalStatus("Married").size());
            maritalChart.set("Divorced", memberSessionBean.getMemberByMaritalStatus("Divorced").size());
            maritalChart.set("Widowed", memberSessionBean.getMemberByMaritalStatus("Widowed").size());

            categoryModelMaritalStatus.addSeries(maritalChart);
        } catch (Exception e) {
            System.err.println("error when creating bar chart");
            e.printStackTrace();
        }
    }

    public void sendBirthdayPromotion(ActionEvent event) throws IOException {
        try {
            System.out.println("in sending birthday promotion" + member.getMemberName());
            emailSessionBean.sendBirthdayCongrats(member);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when sending birthday promotion", ""));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A birthday promotion has sent to the member ", ""));
        memberSessionBean.updateBirthdayEmail(member);
    }

    private void createCategoryModelNationality() {
        try {
            System.out.println("creating nationality bar chart....");
            categoryModelNationality = new CartesianChartModel();

            ChartSeries nationalityChart = new ChartSeries();
            nationalityChart.setLabel("Nationality");
            System.out.println("count nationality Singaporean: " + memberSessionBean.getMemberByNationality("Singaporean").size());
            nationalityChart.set("Singaporean", memberSessionBean.getMemberByNationality("Singaporean").size());
            System.out.println("count nationality Chinese: " + memberSessionBean.getMemberByNationality("Chinese").size());
            nationalityChart.set("Chinese", memberSessionBean.getMemberByNationality("Chinese").size());
            System.out.println("count nationality Malaysian: " + memberSessionBean.getMemberByNationality("Malaysian").size());
            nationalityChart.set("Malaysian", memberSessionBean.getMemberByNationality("Malaysian").size());
            nationalityChart.set("American", memberSessionBean.getMemberByNationality("American").size());

            categoryModelNationality.addSeries(nationalityChart);
        } catch (Exception e) {
            System.err.println("error when creating bar chart");
            e.printStackTrace();
        }
    }

    private void createCategoryModelAge() {
        try {
            System.out.println("creating age bar chart....");
            categoryModelAge = new CartesianChartModel();

            ChartSeries ageChart = new ChartSeries();
            ageChart.setLabel("Year of Birth");
            System.out.println("looking for mapping year of birth");
            ageChart.set("1970", memberSessionBean.getMemberByAge(70).size());
            ageChart.set("1975", memberSessionBean.getMemberByAge(75).size());
            ageChart.set("1980", memberSessionBean.getMemberByAge(80).size());
            ageChart.set("1981", memberSessionBean.getMemberByAge(81).size());
            ageChart.set("1982", memberSessionBean.getMemberByAge(82).size());
            ageChart.set("1983", memberSessionBean.getMemberByAge(83).size());
            ageChart.set("1984", memberSessionBean.getMemberByAge(84).size());
            ageChart.set("1985", memberSessionBean.getMemberByAge(85).size());
            ageChart.set("1986", memberSessionBean.getMemberByAge(86).size());
            ageChart.set("1987", memberSessionBean.getMemberByAge(87).size());
            ageChart.set("1988", memberSessionBean.getMemberByAge(88).size());
            ageChart.set("1989", memberSessionBean.getMemberByAge(89).size());
            ageChart.set("1990", memberSessionBean.getMemberByAge(90).size());
            ageChart.set("1991", memberSessionBean.getMemberByAge(91).size());

            categoryModelAge.addSeries(ageChart);
        } catch (Exception e) {
            System.err.println("error when creating bar chart");
            e.printStackTrace();
        }
    }

    private void createPieModel() {

        System.out.println("creating gender pie chart...");
        pieModel = new PieChartModel();

        pieModel.set("male", memberSessionBean.getMemberByGender("male").size());
        pieModel.set("female", memberSessionBean.getMemberByGender("female").size());

    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public String getSearchEmail() {
        return searchEmail;
    }

    public void setSearchEmail(String email) {
        searchEmail = email;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getAgeYoung() {
        return ageYoung;
    }

    public void setAgeYoung(int ageYoung) {
        this.ageYoung = ageYoung;
    }

    public int getAgeOld() {
        return ageOld;
    }

    public void setAgeOld(int ageOld) {
        this.ageOld = ageOld;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public List<MemberEntity> getMemberList() {
        System.err.println("Size of the memberlist is !!!!!!!!!!!!!!!!!" + memberList.size());
        return memberList;
    }

    public List<MemberEntity> getFilteredMember() {
        System.err.println("We come to get filteredmember?????" + filteredMember.size());
        return filteredMember;
    }

    public void setFilteredMember(List<MemberEntity> filteredMember) {
        this.filteredMember = filteredMember;
    }

    public SelectItem[] getNationalityOptions() {
        return nationalityOptions;
    }

    public void setNationalityOptions(SelectItem[] nationalityOptions) {
        this.nationalityOptions = nationalityOptions;
    }

    public void setMemberList(List<MemberEntity> memberList) {
        this.memberList = memberList;
    }

    public MemberSessionBean getMemberSessionBean() {
        return memberSessionBean;
    }

    public void setMemberSessionBean(MemberSessionBean memberSessionBean) {
        this.memberSessionBean = memberSessionBean;
    }

    public List<String> getNationalityList() {
        return nationalityList;
    }

    public void setNationalityList(List<String> nationalityList) {
        this.nationalityList = nationalityList;
    }

    public SelectItem[] getGenderOptions() {
        return genderOptions;
    }

    public void setGenderOptions(SelectItem[] genderOptions) {
        this.genderOptions = genderOptions;
    }

    public MemberTransactionSessionBean getTransactionSessionBean() {
        return transactionSessionBean;
    }

    public void setTransactionSessionBean(MemberTransactionSessionBean transactionSessionBean) {
        this.transactionSessionBean = transactionSessionBean;
    }

    public CartesianChartModel getCategoryModelNationality() {
        return categoryModelNationality;
    }

    public void setCategoryModelNationality(CartesianChartModel categoryModelNationality) {
        this.categoryModelNationality = categoryModelNationality;
    }

    public CartesianChartModel getCategoryModelAge() {
        return categoryModelAge;
    }

    public void setCategoryModelAge(CartesianChartModel categoryModelAge) {
        this.categoryModelAge = categoryModelAge;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public CartesianChartModel getCategoryModelMaritalStatus() {
        return categoryModelMaritalStatus;
    }

    public void setCategoryModelMaritalStatus(CartesianChartModel categoryModelMaritalStatus) {
        this.categoryModelMaritalStatus = categoryModelMaritalStatus;
    }

    public List<MemberEntity> getMemberSelect() {
        return memberSelect;
    }

    public void setMemberSelect(List<MemberEntity> memberSelect) {
        this.memberSelect = memberSelect;
    }

    public EmployeeSessionBean getEmployeeSessionBean() {
        return employeeSessionBean;
    }

    public void setEmployeeSessionBean(EmployeeSessionBean employeeSessionBean) {
        this.employeeSessionBean = employeeSessionBean;
    }

    public VIPSessionBean getvIPSessionBean() {
        return vIPSessionBean;
    }

    public void setvIPSessionBean(VIPSessionBean vIPSessionBean) {
        this.vIPSessionBean = vIPSessionBean;
    }

    public EmailSessionBean getEmailSessionBean() {
        return emailSessionBean;
    }

    public void setEmailSessionBean(EmailSessionBean emailSessionBean) {
        this.emailSessionBean = emailSessionBean;
    }

    public List<MemberEntity> getVips() {
        return vips;
    }

    public void setVips(List<MemberEntity> vips) {
        this.vips = vips;
    }

    public List<MemberEntity> getSupervips() {
        return supervips;
    }

    public void setSupervips(List<MemberEntity> supervips) {
        this.supervips = supervips;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void onRowToggle(ToggleEvent event) {
        try {
            System.out.println("List member on row toggle" + ((MemberEntity) event.getData()).getMemberEmail());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Row State " + event.getVisibility(),
                    "Model:" + ((MemberEntity) event.getData()).getMemberEmail());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            System.out.println("exception in onRowToggle event");
            e.printStackTrace();
        }
    }
}