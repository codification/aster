package aster;

import javax.swing.JFrame;

public class SpaceFrame extends JFrame {

	public SpaceFrame() {
		AsteroidSpace asteroids = new AsteroidSpace();
		add(asteroids);
        setTitle("Space");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        addKeyListener(asteroids);
	}
	
	public static void main(String[] args) {
		SpaceFrame space = new SpaceFrame();
	}
}
