package shooting;

import javax.swing.ImageIcon;

public class Alien2 extends Enemy {
	private int life=0;

	public Alien2(int x, int y, MainPanel panel) {
		super(x, y, panel);
	}
	public void loselife() {
		if(life<1) {
			life+=1;
		}else {
			die();
		}
	}
	public void loadImage() {
		ImageIcon icon =new ImageIcon(getClass().getResource("image/alien.png"));
		image= icon.getImage();
		super.loadImage();
	}
	
}
