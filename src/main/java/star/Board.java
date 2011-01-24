package star;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private Image star;
	private Timer timer;
	private int y;
	private int x;

	public Board() {
		setBackground(Color.BLACK);
		
		ImageIcon ii = 
			new ImageIcon(this.getClass().getResource("/img/Woof.gif"));
		star = ii.getImage();
		
		setDoubleBuffered(true);
		
		x = y = 10;
		timer = new Timer(25, this);
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(star, x, y, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}



	public void actionPerformed(ActionEvent e) {
		x++;
		y++;
		
		if (y>240) {
			y = -45;
			x = -45;
		}
		repaint();
	}
}
