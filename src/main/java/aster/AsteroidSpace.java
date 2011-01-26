package aster;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class AsteroidSpace extends JPanel implements KeyListener {

	private Shape ship;
	private AffineTransform transform;
	private Point shipPosition;
	
	

	public AsteroidSpace() {
		setBackground(Color.BLACK);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		setHints(g2);

		
		if (ship == null) {
			shipPosition = new Point(200,200);
			int tipx = 0;
			int tipy = 0;
			int halfWidth = 25;
			int height = 75;
			Polygon poly = new Polygon(
					new int[] { tipx, tipx - halfWidth, tipx, tipx + halfWidth }, 
					new int[] { tipy - height/2, tipy + height/2, tipy + -height/2 + 2 * height / 3, tipy + height/2 }, 
					4);
			transform = AffineTransform.getTranslateInstance(shipPosition.getX(), shipPosition.getY());
			ship = transform.createTransformedShape(poly);
		}
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
		
		
	}

	private void rotateShip(int degrees) {
		int anchorx = shipPosition.x;
		int anchory = shipPosition.y;
		AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(degrees), anchorx, anchory);
		ship = rotation.createTransformedShape(ship);
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
	}
}
