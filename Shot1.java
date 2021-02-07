package shooting;

import javax.swing.ImageIcon;

public class Shot1 extends HyperShot {
	private static final int SPEED=5;
	public Shot1(MainPanel panel) {
		super(panel);
	}
	public void move() {
		speed_y=SPEED;
		super.move();
	
	}
	
	protected void loadImage() {
		ImageIcon icon=new ImageIcon(getClass().getResource("image/shot1.gif"));
		image=icon.getImage();
		super.loadImage();
	}
	
}
