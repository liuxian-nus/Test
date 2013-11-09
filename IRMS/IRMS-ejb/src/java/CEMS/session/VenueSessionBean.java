/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CEMS.session;

import CEMS.entity.VenueEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ser3na
 */
@Stateless
public class VenueSessionBean {

    @PersistenceContext(unitName = "IRMS-ejbPU")
    private EntityManager em;
    private VenueEntity venue;

    public VenueSessionBean() {
    }

    public VenueEntity addVenue(VenueEntity venue) {
        em.persist(venue);
        return venue;
    }

    public boolean deleteVenue(Long venueId) {
        venue = em.find(VenueEntity.class, venueId);
        if (venue == null) {
            return false;
        }
        em.remove(venue);
        return true;
    }

    public boolean updateVenue(VenueEntity venue) {
        em.merge(venue);
        return true;
    }

    public List<VenueEntity> getAllVenues() throws NoResultException {
        Query q = em.createQuery("SELECT m FROM VenueEntity m");
        return q.getResultList();
    }
    
    public VenueEntity getVenueById(Long venueId) {
        return em.find(VenueEntity.class, venueId);
    }

    public VenueEntity getVenue() {
        return venue;
    }

    public void setVenue(VenueEntity venue) {
        this.venue = venue;
    }
}
