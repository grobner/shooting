package shooting;

import java.awt.Point;

import javax.swing.ImageIcon;

public class Shot3 extends HyperShot{
	protected double speed_x=0;
	protected double speed_y=5;
	private static final int MOVW_TIME=1200;
	public long lastMove=0;
	public Shot3(MainPanel panel) {
		super(panel);
	}
	public void move(Player player) {
		if(lastMove==0) {
			lastMove=System.currentTimeMillis();
		}
		if((System.currentTimeMillis()-lastMove)>MOVW_TIME) {
			
		}else {
			Point pos=player.getPos();
			double degree=Math.atan2(pos.y-y,pos.x-x);
			speed_x=Math.cos(degree)*5;
			speed_y=Math.sin(degree)*5;
		
		}
	
		x+=speed_x;
		y+=speed_y;
		super.move();
		
	}
	public void loadImage() {
		ImageIcon icon=new ImageIcon(getClass().getResource("image/shot3.gif"));
		image=icon.getImage();
		super.loadImage();
	}
}
