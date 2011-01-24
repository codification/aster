package basics.skeleton;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageBoard extends JPanel {
	Image woof;
	
	public ImageBoard() {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/img/Woof.gif"));
        woof = ii.getImage();
	}
	
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(woof, 10, 10, null); 
    }
}
