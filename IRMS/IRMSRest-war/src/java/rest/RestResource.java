/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import CRMS.entity.MemberEntity;
import CRMS.entity.MemberTransactionEntity;
import CRMS.session.MemberSessionBean;
import Exception.ExistException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
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

    public RestResource() {
    }

    @GET
    @Path("member")
    @Produces(MediaType.APPLICATION_JSON)
    public MemberEntity getMember(@QueryParam("email") String email) {
        System.err.println("Email is: " + email);
        if (memberSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        MemberEntity member = memberSessionBean.getMemberByEmail(email);
        if (member != null) {
            System.err.println("member name is: " + member.getMemberName());
            return member;
        } else {
            throw new WebApplicationException(404);
        }
    }

    @GET
    @Path("member/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public MemberEntity login(@QueryParam("email") String email, @QueryParam("password") String password) {
        System.out.println("Email is: " + email);
        System.out.println("Password is: " + password);
        if (memberSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        MemberEntity member = memberSessionBean.checkLogIn(email, password);
        if (member != null) {
            System.err.println("member name is: " + member.getMemberName());
            return member;
        } else {
            throw new WebApplicationException(404);
        }
    }

    @GET
    @Path("member/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MemberTransactionEntity> getMemberTransaction(@QueryParam("email") String email) {
        if (memberSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        List<MemberTransactionEntity> mtlist = memberSessionBean.getAllTransactions(email);
        if (mtlist != null) {
            return mtlist;
        } else {
            throw new WebApplicationException(404);
        }
    }

    @PUT
    @Path("member/{email}")
    //   @Consumes(MediaType.APPLICATION_JSON)
    //   @Produces(MediaType.APPLICATION_JSON)
    public void updateMember(@PathParam("email") String email,
            @FormParam("memberName") String memberName,
            @FormParam("memberHP") String memberHP,
            @FormParam("memberDob") String memberDob,
            @FormParam("memberGender") String memberGender,
            @FormParam("maritalStatus") String maritalStatus) throws ExistException, ParseException {
        if (memberSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        System.err.println("email is" + email);
        System.err.println("memberName is " + memberName);
        memberSessionBean.updateMember(email, memberName, memberHP, memberDob, maritalStatus, memberGender);
    }

    @PUT
    @Path("member/password/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void updatePassword(@PathParam("email") String email, @FormParam("memberPassword") String memberPassword) {
        if (memberSessionBean == null) {
            System.err.println("memberSessionBean is null");
        }
        System.err.println("email is: " + email);
        System.err.println("member Password is " + memberPassword);
        memberSessionBean.updatePassword(email, memberPassword);
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
