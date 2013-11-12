/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ERMS.session;

import ACMS.entity.ReservationEntity;
import ACMS.entity.RoomEntity;
import ATMS.entity.AttrComboEntity;
import ATMS.entity.ExpressPassPurchaseEntity;
import ATMS.entity.TicketPurchaseEntity;
import CRMS.entity.MemberEntity;
import CRMS.entity.PromotionEntity;
import SMMS.entity.BillEntity;
import SMMS.entity.ContractEntity;
import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.ejb.Remote;

/**
 *
 * @author Diana Wang
 */
@Remote
public interface EmailSessionBeanRemote {
    
    public void sendBirthdayCongrats(MemberEntity member);
    
    public void sendPromotionToTargets(PromotionEntity promotion);
    
    public void sendPromotionToSubs(PromotionEntity promotion);
    
    void emailApprovalAction(String toEmailAdress, ContractEntity contract);

    void emailAttractionTicketCombo(String toEmailAdress, AttrComboEntity combo) throws IOException, FileNotFoundException, DocumentException;

    void emailAttractionTicketExpress(String toEmailAdress, ExpressPassPurchaseEntity eppe) throws IOException, FileNotFoundException, DocumentException;

    void emailAttractionTicketSingle(String toEmailAdress, TicketPurchaseEntity tpe) throws IOException, FileNotFoundException, DocumentException;

    void emailCorporateBill(String toEmailAdress, RoomEntity room) throws IOException, FileNotFoundException, DocumentException;

    void emailGeneratedPassword(String toEmailAdress, String initialPassword);

    void emailInitialPassward(String toEmailAdress, String initialPassword);

    void emailMerchantBill(String toEmailAdress, BillEntity bill);

    void emailRequest(String toEmailAdress, ContractEntity contract);

    void emailReservationConfirmation(String toEmailAdress, ReservationEntity newReservation) throws IOException, FileNotFoundException, DocumentException;
    
}
