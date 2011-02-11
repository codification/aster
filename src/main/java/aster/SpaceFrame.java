package aster;

import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpaceFrame extends JFrame {

	public SpaceFrame(JPanel asteroidSpace, KeyListener keyListener) {
		add(asteroidSpace);
        setTitle("Space");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        addKeyListener(keyListener);
	}
	
	public static void main(String[] args) {
		AsteroidSpace asteroidSpace = new AsteroidSpace();
		SpaceFrame space = new SpaceFrame(asteroidSpace, asteroidSpace);
	}
}
