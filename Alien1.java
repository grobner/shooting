package shooting;

import javax.swing.ImageIcon;

public class Alien1 extends Enemy {
	//エイリアンが移動できる�?
		private static final int MOVE_WIDTH=100;
		private int left;
		private int right;
		private int speed;
	public Alien1(int x, int y,int speed, MainPanel panel) {
		super(x, y, panel);
		this.speed=speed;
		left=x;
		right=x+MOVE_WIDTH;
	}
	public void move() {
		speed=0;
		super.move();
	}
	@Override
	protected void loadImage() {
		ImageIcon icon =new ImageIcon(getClass().getResource("image/alien.gif"));
		image= icon.getImage();
		super.loadImage();
	}
	
}
	

