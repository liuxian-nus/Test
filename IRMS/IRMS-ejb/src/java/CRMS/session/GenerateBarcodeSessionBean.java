/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CRMS.session;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import java.awt.image.BufferedImage;  
import java.io.FileOutputStream;  
import org.jbarcode.JBarcode;  
import org.jbarcode.encode.EAN8Encoder;
import org.jbarcode.paint.EAN8TextPainter;
import org.jbarcode.paint.WidthCodedPainter;  
import org.jbarcode.util.ImageUtil;  

/**
 *
 * @author Jieqiong
 */
@Stateless
@LocalBean
public class GenerateBarcodeSessionBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public GenerateBarcodeSessionBean(){
        
    }
    
    public void generate(String content) {
        System.out.println("into generate");
        try {
            JBarcode localJBarcode = new JBarcode(EAN8Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN8TextPainter.getInstance());
            //String str = "2219644";
            System.out.println("content: "+content);
            BufferedImage localBufferedImage = localJBarcode.createBarcode(content);
            String name=content+".jpg";

        //    saveToJPEG(localBufferedImage, "EAN8.jpg");
            saveToJPEG(localBufferedImage, name);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
    
    public void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {
        System.out.println("into saveToJPEG");
        saveToFile(paramBufferedImage, paramString, "jpeg");
    } 
    
    public void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2) {
        System.out.println("into saveToFile");
        try {
            FileOutputStream localFileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop/" + paramString1);
            ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 96, 96);
            localFileOutputStream.close();
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    } 
    
    

}
