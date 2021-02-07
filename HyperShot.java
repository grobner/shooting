package shooting;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;


public class HyperShot  {
	protected static final Point STORAGE=new Point(-20, -20);
	protected int x;
	protected int y;
	protected int width;
	protected int height; 
	protected int speed_x=0;
	protected int speed_y=0;
	protected Image image;
	protected MainPanel panel;
	public HyperShot(MainPanel panel) {
		x=STORAGE.x;
		y=STORAGE.y;
		this.panel=panel;
		loadImage();
	}
	protected void loadImage() {
		width=image.getWidth(panel);
		height=image.getHeight(panel);
	}
	public void setSpeed(int d,int e) {
		speed_x=d;
		speed_y=e;
	}
	public void setSpeed(int i) {
		if(i==0) {
			setSpeed(0,5);
		}else if(i==1) {
			setSpeed(4, 3);
		}else if(i==2) {
			setSpeed(-4, 3);
		}
	}
	public void move() {
		if(isInstorage()) {
			return;
		}
		x+=speed_x;
		y+=speed_y;
		if(x<0||y<0||y>panel.HEIGHT||x>panel.WIDTH) {
			store();
		}
	}
	public Point getPos() {
		return new Point(x,y);
	}
	public void setPos(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public void store() {
		x=STORAGE.x;
		y=STORAGE.y;
		
	}
	public boolean isInstorage() {
		if(x==STORAGE.x&&y==STORAGE.y) {
			return true;
		}else {
			return false;
		}
	}
	public void draw(Graphics g) {
		g.drawImage(image,x,y,null);
	}
	public int getWidth() {
		return width;
	}
	public int geyHeight() {
		return height;
	}

}