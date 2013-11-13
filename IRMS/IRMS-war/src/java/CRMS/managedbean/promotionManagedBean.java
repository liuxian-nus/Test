/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.managedbean;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.entity.PromotionEntity;
import CRMS.session.EvaluationSessionBean;
import CRMS.session.MemberSessionBean;
import CRMS.session.PromotionSessionBean;
import CRMS.session.VIPSessionBean;
import Exception.ExistException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ToggleEvent;
//import org.netbeans.api.project.ui.OpenProjects;
//import org.netbeans.api.project.Project;
//import org.openide.filesystems.FileObject; 

/**
 *
 * @author liuxian
 */
@ManagedBean
@ViewScoped
public class promotionManagedBean {

    @EJB
    private PromotionSessionBean promotionSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;
    @EJB
    private VIPSessionBean vipSessionBean;
    @EJB
    private EvaluationSessionBean evaluationSessionBean;
    private List<PromotionEntity> promotions = new ArrayList<PromotionEntity>();
    private List<PromotionEntity> exclusivePromotions = new ArrayList<PromotionEntity>();
    private PromotionEntity newPromotion = new PromotionEntity();
    private PromotionEntity thisPromotion = new PromotionEntity();
    private Long promotionId;
    private List<MemberEntity> participateMembers = new ArrayList<MemberEntity>();
    private MemberEntity member;
    private String targetGroup;
    private String imageName;//image name 
    private String fileName; //full path for preview

    public promotionManagedBean() {
        newPromotion = new PromotionEntity();
        thisPromotion = new PromotionEntity(); 
        member = new MemberEntity();
        
    }

    @PostConstruct
    public void init() throws ExistException {
        promotions = promotionSessionBean.getAllPromotions();
        exclusivePromotions = promotionSessionBean.getMemberExclusivePromotions();
    }

    public void initViewSelect(PhaseEvent event) {
        System.err.println("in initial view function");
        this.member = (MemberEntity) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("member");
        System.err.println("select transaction with this member: " + member.getMemberName());
    }

    public void saveNewPromotion(ActionEvent event) throws IOException, ExistException {
        System.err.println("targetGroup: " + targetGroup);
        System.err.println("Saving New promotion...");
        if (targetGroup.equalsIgnoreCase("customers")) {
            setNotMemberExclusive();
        }
        if (targetGroup.equalsIgnoreCase("members")) {
            setMemberExclusive();
        }
        if (targetGroup.equalsIgnoreCase("VIPs")) {
            setVIPExclusive();
        }
        if (targetGroup.equalsIgnoreCase("RFM")) {
            setRFMExclusive();
        }
        if (targetGroup.equalsIgnoreCase("lifeValue")) {
            setLifeValueExclusive();
        }
        if (targetGroup.equalsIgnoreCase("sizeOW")) {
            setSizeOWExclusive();
        }
        try {
            promotionSessionBean.createPromotion(newPromotion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New promotion saved.", ""));
            FacesContext.getCurrentInstance().getExternalContext().redirect("listPromotions.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new promotion", ""));
            e.printStackTrace();
        }
    }
    
        public void cancelPromotion(ActionEvent event)throws IOException {
        try {
            System.out.println("in terminating promotion" + thisPromotion.getPromotionTitle());
            promotionSessionBean.cancelPromotion(thisPromotion);
        }catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when terminating promotion", ""));
         }
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Now this promotion is terminated", ""));

    }

    public List<MemberEntity> getPromotionMembers(ActionEvent event) throws IOException, ExistException {
        System.err.println("looking for participating members of given promotion id");
        try {
            participateMembers = promotionSessionBean.getPromotionMembers(promotionId);
            FacesContext.getCurrentInstance().getExternalContext().redirect("listAllMembers.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when adding new promotion", ""));
            e.printStackTrace();
        }
        return participateMembers;
    }

    public void onRowToggle(ToggleEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Row State " + event.getVisibility(),
                "Model:" + ((PromotionEntity) event.getData()).getPromotionId());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void setMemberExclusive() throws IOException, ExistException {
        try {
            newPromotion.setPromotionMemberExclusive(true);
            newPromotion.setMcMemberTargets(memberSessionBean.getAllMembers());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion set to target to all members.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void setNotMemberExclusive() throws IOException, ExistException {
        try {
            newPromotion.setPromotionMemberExclusive(false);
            newPromotion.setMcMemberTargets(null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion set to target to all customers.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs when searching", ""));
            return;
        }
    }

    public void setVIPExclusive() throws IOException, ExistException {
        try {
            newPromotion.setPromotionMemberExclusive(true);
            newPromotion.setMcMemberTargets(vipSessionBean.getVIPs());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion is set to target to VIP only.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs setting target customer", ""));
            return;
        }
    }

    public void setRFMExclusive() throws IOException, ExistException {
        try {
            newPromotion.setPromotionMemberExclusive(true);
            newPromotion.setMcMemberTargets(evaluationSessionBean.getTieredBasedOnRFM());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion is set to target to RFM only.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs setting target customer", ""));
            return;
        }
    }

    public void setLifeValueExclusive() throws IOException, ExistException {
        try {
            newPromotion.setPromotionMemberExclusive(true);
            newPromotion.setMcMemberTargets(evaluationSessionBean.getTieredBasedOnCustLifeValue());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion is set to target to life value only.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs setting target customer", ""));
            return;
        }
    }

    public void setSizeOWExclusive() throws IOException, ExistException {
        try {
            newPromotion.setPromotionMemberExclusive(true);
            newPromotion.setMcMemberTargets(evaluationSessionBean.getTieredBasedOnSizeOfWallet());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion is set to target to size of wallet only.", ""));

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error occurs setting target customer", ""));
            return;
        }
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        System.err.println("Uploading Image...");
        String[] fileNameParts = event.getFile().getFileName().split("\\.");
        
        //Get Path!!!!!!!yeah!!!!!!!!
        String path2 = promotionManagedBean.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String[] fileNamePartsTest = path2.split("IRMS");
        String part1 = fileNamePartsTest[0];
        part1 = part1.replaceAll("%20", " ");
        System.err.println("part 1:"+part1);
        File result = new File(part1 + "IRMS\\IRMS-war\\web\\images\\" + fileNameParts[0] + "." + fileNameParts[1]);
        System.out.println("fileNameParts[0] is: " + fileNameParts[0]);
        this.setImageName(fileNameParts[0]);//set image name for preview
        FileOutputStream out = new FileOutputStream(result);

        int a;
        int BUFFER_SIZE = 8192;
        byte[] buffer = new byte[BUFFER_SIZE];

        InputStream is = event.getFile().getInputstream();
        String fileName = result.getName();
        this.setFileName(fileName);
        promotionSessionBean.uploadImage(promotionId, fileName);
        System.err.println(fileName);
        while (true) {
            a = is.read(buffer);
            if (a < 0) {
                break;
            }
            out.write(buffer, 0, a);
            out.flush();
        }

        out.close();
        is.close();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Image has been uploaded", ""));
    }

    public MemberSessionBean getMemberSessionBean() {
        return memberSessionBean;
    }

    public void setMemberSessionBean(MemberSessionBean memberSessionBean) {
        this.memberSessionBean = memberSessionBean;
    }

    public VIPSessionBean getVipSessionBean() {
        return vipSessionBean;
    }

    public void setVipSessionBean(VIPSessionBean vipSessionBean) {
        this.vipSessionBean = vipSessionBean;
    }

    public String getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
    }

    public PromotionSessionBean getPromotionSessionBean() {
        return promotionSessionBean;
    }

    public void setPromotionSessionBean(PromotionSessionBean promotionSessionBean) {
        this.promotionSessionBean = promotionSessionBean;
    }

    public List<PromotionEntity> getPromotions() {
        return promotions;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setPromotions(List<PromotionEntity> promotions) {
        this.promotions = promotions;
    }

    public PromotionEntity getNewPromotion() {
        return newPromotion;
    }

    public PromotionEntity getThisPromotion() {
        System.out.println("target promotion: "+ thisPromotion.getPromotionTitle());
        return thisPromotion;
    }

    public void setThisPromotion(PromotionEntity thisPromotion) {
        this.thisPromotion = thisPromotion;
    }
    
    

    public void setNewPromotion(PromotionEntity newPromotion) {
        this.newPromotion = newPromotion;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public EvaluationSessionBean getEvaluationSessionBean() {
        return evaluationSessionBean;
    }

    public void setEvaluationSessionBean(EvaluationSessionBean evaluationSessionBean) {
        this.evaluationSessionBean = evaluationSessionBean;
    }

    public String getImageName() {
        System.out.println("image name is : " +imageName);
        return imageName;
    }

    public void setImageName(String imageName) {
        System.out.println("imageName set to: " +imageName);
        this.imageName = imageName;
    }
    
    

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public List<MemberEntity> getParticipateMembers() {
        return participateMembers;
    }

    public void setParticipateMembers(List<MemberEntity> participateMembers) {
        this.participateMembers = participateMembers;
    }

    public List<PromotionEntity> getExclusivePromotions() {
        return exclusivePromotions;
    }

    public void setExclusivePromotions(List<PromotionEntity> exclusivePromotions) {
        this.exclusivePromotions = exclusivePromotions;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

}
