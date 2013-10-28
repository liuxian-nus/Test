/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.MemberSessionBean;
import Exception.ExistException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author liuxian
 */
@Stateless
@Path("rest")
public class RestResource {
    @EJB
    MemberSessionBean memberSessionBean;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestResource
     */
    public RestResource() {
    }

    @GET
    @Path("member")
    @Produces(MediaType.APPLICATION_XML)
    public MemberEntity getMember(@QueryParam("email") String email)
    {
        if(memberSessionBean == null)
            System.err.println("memberSessionBean is null");
        MemberEntity member = memberSessionBean.getMemberByEmail(email);
        if(member!=null){
            return member;
        }
        else 
            throw new WebApplicationException(404);
    }

    @GET
    @Path("member/transactions")
    @Produces(MediaType.APPLICATION_XML)
    public List<MemberTransactionEntity> getMemberTransaction(@QueryParam("email") String email)
    {
        return memberSessionBean.getAllTransactions(email);
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

    private MemberSessionBean lookupMemberSessionBeanBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (MemberSessionBean) c.lookup("java:global/IRMS/IRMS-ejb/MemberSessionBean!CRMS.session.MemberSessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
