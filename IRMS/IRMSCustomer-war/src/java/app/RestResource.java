/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.entity.PromotionEntity;
import CRMS.session.MemberManagementSessionBean;
import CRMS.session.MemberSessionBean;
import CRMS.session.MemberTransactionSessionBean;
import Exception.ExistException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author liuxian
 */
@Stateless
@Path("rest")
public class App {
//    @EJB
 //   private MemberTransactionSessionBean mtSessionBean;
    @EJB
    private MemberManagementSessionBean memberManagementSessionBean;
    @EJB
    private MemberSessionBean memberSessionBean;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public App() {
    }

    /**
     * Retrieves representation of an instance of app.GenericResource
     * @return an instance of java.lang.String
     */
    
    @GET
    @Path("member")
    @Produces(MediaType.APPLICATION_XML)
    public MemberEntity getMember(@QueryParam("email") String email)
    {
        if(email!=null){
            return memberSessionBean.getMemberByEmail(email);
        }
        else 
            return null;
    }

    @GET
    @Path("member/transactions")
    @Produces(MediaType.APPLICATION_XML)
    public List<MemberTransactionEntity> getMemberTransaction(@QueryParam("email") String email)
    {
        return memberSessionBean.getAllTransactions(email);
    }
    
    @GET
    @Path("member/bundles")
    @Produces(MediaType.APPLICATION_XML)
    public List<PromotionEntity> getBundles()
    {
        return null;
    }
    
    @GET
    @Path("member/promotions")
    @Produces(MediaType.APPLICATION_XML)
    public List<PromotionEntity> getPromotions()
    {
        return null;
    }
    
    
    
    @DELETE
    @Path("member")
    public void deleteMember(@QueryParam("email") String email) throws ExistException
    {
        if(email!=null){
            memberSessionBean.removeMember(email);
        }
    }
    
    @POST
    @Path("member")
    public void updateMember(@FormParam("email") String email,
                                @FormParam("memberName") String memberName,
                                @FormParam("memberHP") String memberHP,
                                @FormParam("memberDob") Date memberDob,
                                @FormParam("memberGender") String memberGender,
                                @FormParam("maritalStatus") String maritalStatus,
                                @FormParam("isSubscriber") boolean isSubscriber) throws ExistException
    {
        memberSessionBean.updateMember(email,memberName,memberHP,memberDob,maritalStatus,memberGender,isSubscriber);
    }
     
    
    @POST
    @Path("member/password")
    public void updatePassword(@FormParam("memberPassword") String memberPassword) 
    {
        memberSessionBean.updatePassword(memberPassword);
    }
}
