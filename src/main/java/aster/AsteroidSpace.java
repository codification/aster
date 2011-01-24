package aster;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class AsteroidSpace extends JPanel implements KeyListener {

	private Shape ship;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		setHints(g2);

		
		if (ship == null) {
			int tip = 200;
			int halfWidth = 25;
			int height = 75;
			Polygon poly = new Polygon(new int[] { tip, tip - halfWidth, tip,
					tip + halfWidth }, new int[] { tip, tip + height,
					tip + 2 * height / 3, tip + height }, 4);
			this.ship = poly;
		}
		g.setColor(Color.GRAY);
		g2.draw(ship);
		g2.fill(ship);
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
		double anchorx = ship.getBounds2D().getCenterX();
		double anchory = ship.getBounds2D().getCenterY();
		AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(degrees), anchorx, anchory);
		ship = transform.createTransformedShape(ship);
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
	}
}
