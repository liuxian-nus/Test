/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ESMS.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Ser3na
 */
@Entity
public class ShowEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showId;
    private String showName;
    private String showDescription;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowDescription() {
        return showDescription;
    }

    public void setShowDescription(String showDescription) {
        this.showDescription = showDescription;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }
    
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (showId != null ? showId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ShowEntity)) {
            return false;
        }
        ShowEntity other = (ShowEntity) object;
        if ((this.showId == null && other.showId != null) || (this.showId != null && !this.showId.equals(other.showId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ESMS.entity.showEntity[ id=" + showId + " ]";
    }
    
}
