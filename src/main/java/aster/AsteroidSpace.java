package aster;

import java.awt.Color;
import java.awt.Dimension;
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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AsteroidSpace extends JPanel implements KeyListener {

	private Shape ship;
	private AffineTransform transform;
	private Point2D shipPosition;
	private Shape flame;
	private boolean burning = false;
	private boolean blasting;
	private Shape deathRay;
	private int shipHeight;
	private Shape[] stars;
	
	

	public AsteroidSpace() {
		setBackground(Color.BLACK);
		shipPosition = new Point2D.Double(400,300);

		flame = new Ellipse2D.Double(shipPosition.getX() - 10, shipPosition.getY(), 20, 30 );
		double tipOfShip = shipPosition.getY() - (double) shipHeight/2;
		deathRay = new Line2D.Double(shipPosition.getX(), tipOfShip, shipPosition.getX(), tipOfShip - 200);

		burning = false;
		blasting = false;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		setHints(g2);		
		
		paintStars(g2);
		
		if (ship == null) {
			int centerX = 0;
			int centerY = 0;
			int halfWidth = 25;
			shipHeight = 75;
			Polygon poly = new Polygon(
					new int[] { centerX, centerX - halfWidth, centerX, centerX + halfWidth }, 
					new int[] { centerY - shipHeight/2, centerY + shipHeight/2, centerY - shipHeight/2 + 2 * shipHeight / 3, centerY + shipHeight/2 }, 
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
		
		// Draw blast
		if( blasting) {
			g2.setColor(Color.RED);
			g2.draw(deathRay);
		}
		
		// Draw the ship
		g.setColor(Color.GRAY);
		g2.fill(ship);
		g2.draw(ship);
	}

	private void paintStars(Graphics2D g2) {
		if (stars == null) {
			stars = new Shape[100];
			for (int i = 0; i < 100; i++) {
				stars[i] = new Ellipse2D.Double(Math.random()
						* getSize().getWidth(), Math.random()
						* getSize().getHeight(), 1, 1);
			}
		}
		for (int i = 0; i < 100; i++) {
			g2.setColor(Color.WHITE);
			g2.draw(stars[i]);
		}
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
		if(arg0.getKeyCode() == KeyEvent.VK_UP) {
			burnBaby();
			repaint();
		}
		if(arg0.getKeyCode() == KeyEvent.VK_SPACE) {
			blast();
			repaint();
		}
		
		
	}

	private void blast() {
		blasting = true;
		new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				blasting = false;
				repaint();
			}
		}).start();
	}

	private void burnBaby() {
		burning = true;
		new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				burning = false;
				repaint();
			}
		}).start();
	}

	private void rotateShip(int degrees) {
		double anchorx = shipPosition.getX();
		double anchory = shipPosition.getY();
		AffineTransform rotation = AffineTransform.getRotateInstance(Math.toRadians(degrees), anchorx, anchory);
		ship = rotation.createTransformedShape(ship);
		flame = rotation.createTransformedShape(flame);
		deathRay = rotation.createTransformedShape(deathRay);
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}
