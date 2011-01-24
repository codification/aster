package basics.skeleton;

import javax.swing.JFrame;

public class Skeleton extends JFrame {
	public Skeleton() {
		add(new ImageBoard());
        setTitle("Skeleton");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 280);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
	}
	
    public static void main(String[] args) {
        new Skeleton();
    }
}
