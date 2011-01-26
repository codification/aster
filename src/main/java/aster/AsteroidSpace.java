package aster;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AsteroidSpace extends JPanel implements KeyListener {

	private Shape ship;
	private AffineTransform transform;
	private Point shipPosition;
	private Shape flame;
	private boolean burning = false;
	
	

	public AsteroidSpace() {
		setBackground(Color.BLACK);
		shipPosition = new Point(200,200);

		flame = new Ellipse2D.Double(shipPosition.getX() - 10, shipPosition.getY(), 20, 30 );

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		setHints(g2);
		
		
		if (ship == null) {
			int centerX = 0;
			int centerY = 0;
			int halfWidth = 25;
			int height = 75;
			Polygon poly = new Polygon(
					new int[] { centerX, centerX - halfWidth, centerX, centerX + halfWidth }, 
					new int[] { centerY - height/2, centerY + height/2, centerY - height/2 + 2 * height / 3, centerY + height/2 }, 
					4);
			transform = AffineTransform.getTranslateInstance(shipPosition.getX(), shipPosition.getY());
			ship = transform.createTransformedShape(poly);
		}
		
		// Draw flame
		if (burning) {
			g2.setColor(Color.ORANGE);
			g2.fill(flame);
			g2.draw(flame);
		}
		// Draw the ship
		g.setColor(Color.GRAY);
		g2.fill(ship);
		g2.draw(ship);
	}

	private void setHints(Graphics2D g2) {
		RenderingHints rh =
			new RenderingHints(
					RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);
		
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHints(rh);
	}

	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			int degrees = -10;
			rotateShip(degrees);
			repaint();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			int degrees = 10;
			rotateShip(degrees);
			repaint();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			burnBaby();
			repaint();
		}
		
		
	}

	private void burnBaby() {
		burning = true;
		new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				burning = false;
			}
		}).start();
	}

	private void rotateShip(int degrees) {
		int anchorx = shipPosition.x;
		int anchory = shipPosition.y;
		AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(degrees), anchorx, anchory);
		ship = rotation.createTransformedShape(ship);
		flame = rotation.createTransformedShape(flame);
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
	}
}
