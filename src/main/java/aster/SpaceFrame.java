package aster;

import javax.swing.JFrame;

import basics.skeleton.ImageBoard;

public class SpaceFrame extends JFrame {

	public SpaceFrame() {
		AsteroidSpace asteroids = new AsteroidSpace();
		add(asteroids);
        setTitle("Space");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
        addKeyListener(asteroids);
	}
	
	public static void main(String[] args) {
		SpaceFrame space = new SpaceFrame();
	}
}
