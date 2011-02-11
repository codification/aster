package aster.painter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import aster.SpaceFrame;

public class AsteroidPainter {

	public static void main(String[] args) {
		PainterPanel panel = new PainterPanel();
		new SpaceFrame(panel, panel);
	}
}
