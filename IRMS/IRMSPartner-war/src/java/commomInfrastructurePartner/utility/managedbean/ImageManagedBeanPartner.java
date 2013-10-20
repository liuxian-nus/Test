/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commomInfrastructurePartner.utility.managedbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ser3na
 */
@ManagedBean
@ViewScoped
public class ImageManagedBeanPartner {
    private List<String> images;  
  
    public ImageManagedBeanPartner() {  
        images = new ArrayList<String>();  
        images.add("hotel_1.png");  
        images.add("hotel_2.png");  
        images.add("hotel_3.png");  
        images.add("hotel_4.png");  
        images.add("hotel_5.png"); 
    }  
  
    public List<String> getImages() {  
        return images;  
    }  
}
