package shooting;

import javax.swing.ImageIcon;

public class BossShot extends HyperShot {
	private double speed_x;
	private double speed_y;
	private static int MOVE_INTERVAL=25;
	private int count=0;
	public BossShot(MainPanel panel) {
		super(panel);
	}
	public void move() {
		if(isInstorage()) {
			return;
		}
		if(count<MOVE_INTERVAL) {
			count+=1;
		}else {
			speed_x=-speed_x;
			speed_y=-speed_y;
			count=0;
		}
		x+=speed_x;
		y+=speed_y;
	
	}
	@Override
	public void setSpeed(int i) {
		speed_x=4*Math.cos((i*Math.PI)/2);
		speed_y=4*Math.sin((i*Math.PI)/2);
	}
	protected void loadImage() {
		ImageIcon icon=new ImageIcon(getClass().getResource("image/shot1.gif"));
		image=icon.getImage();
		super.loadImage();
	}
}
