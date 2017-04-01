/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package speedyroadie.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Louis Dhanis
 */
public class SpeedyPanel extends JPanel{
   public SpeedyPanel(){
   }
   
   @Override
    public void paintComponent(Graphics g){
        try {
            Image img = ImageIO.read(new File("welcomeBG.jpg"));
            g.drawImage(img, 0, 0, this);
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }                
    }  
}
