package aster.painter;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

final class PainterPanel extends JPanel implements MouseListener, KeyListener {
	private static final int DOT_DIAMETER = 10;
	private LinkedList<Point2D> dots;

	public PainterPanel() {
		setBackground(Color.BLACK);
		dots = new LinkedList<Point2D>();
		addMouseListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);

		Polygon polygon = new Polygon();
		for (Point2D point : dots) {
			int DOT_RADIUS = DOT_DIAMETER/2;
			g2.drawOval((int)point.getX() - DOT_RADIUS,(int) point.getY() - DOT_RADIUS, DOT_DIAMETER, DOT_DIAMETER);
			polygon.addPoint((int)point.getX(), (int)point.getY());
		}
		g2.drawPolygon(polygon);
		
		printPoints(new PrintWriter(System.out));
	}

	private void printPoints(PrintWriter out) {
		out.println("Point2D[] points = new Point2[] {");
		for (Iterator<Point2D> it = dots.iterator(); it.hasNext(); ) {
			Point2D point = it.next();
			out.print("new Point2D.Double(" + point.getX() + ", " + point.getY() + ")");
			if (it.hasNext()) {
				out.print(",");
			}
			out.print("\n");
		}
		out.println("}");
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.isShiftDown()) {
			if (!dots.isEmpty()) {
				dots.removeLast();
			}
		} else {
			dots.add(new Point2D.Double(arg0.getX(), arg0.getY()));
		}
		repaint();

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == ' ') {
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			printPoints(printWriter);
			JOptionPane.showInputDialog(this, "Asteroid:", "The Points", JOptionPane.PLAIN_MESSAGE, null, null, stringWriter.toString());
		}
	}
	
	
}