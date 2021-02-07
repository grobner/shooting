package shooting;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;


public class Shot  {
	private static final int SPEED=10;
	private static final Point STORAGE=new Point(-20, -20);
	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	private MainPanel panel;
	public Shot(MainPanel panel) {
		x=STORAGE.x;
		y=STORAGE.y;
		this.panel=panel;
		loadImage();
	}
	private void loadImage() {
		ImageIcon icon=new ImageIcon(getClass().getResource("image/shot.gif"));
		image=icon.getImage();
		width=image.getWidth(panel);
		height=image.getHeight(panel);
	}
	public void move() {
		if(isInstorage()) {
			return;
		}
		y=y-SPEED;
		if(y<0) {
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
	public int getHeight() {
		return height;
	}

}
