package shooting;

import javax.swing.ImageIcon;

public class Shot2 extends HyperShot {
	int i=0;
	public Shot2(MainPanel panel) {
		super(panel);
	}
	public void move() {
		if(isInstorage()) {
			return;
		}
		
		if(i==0) {
			speed_y=5;
			if(x<0||x>panel.WIDTH) {
				speed_x=-speed_x;
				i++;
			}
			if(y<0||y>panel.HEIGHT) {
				speed_y=-speed_y;
				i++;
			}
		}else if(i==1) {
			if((x<0||y<0)||(y>panel.HEIGHT||x>panel.WIDTH)) {
				store();
			}
		}
		x+=speed_x;
		y+=speed_y;
	}
	public void loadImage() {
		ImageIcon icon=new ImageIcon(getClass().getResource("image/shot2.gif"));
		image=icon.getImage();
		super.loadImage();
	}
}
