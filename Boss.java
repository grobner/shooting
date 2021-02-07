package shooting;

import javax.swing.ImageIcon;

public class Boss extends Enemy {
	private int life=0;
	public Boss(int x,int y,MainPanel panel) {
		super(x, y, panel);
		
	}
	public void loselife() {
		if(life<100) {
			life+=1;
		}else {
			die();
		}
	}
	public void loadImage() {
		ImageIcon icon =new ImageIcon(getClass().getResource("image/boss.png"));
		image= icon.getImage();
		super.loadImage();
	}
}
