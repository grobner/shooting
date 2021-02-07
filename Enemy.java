package shooting;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Enemy {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Image image;
	protected MainPanel panel;
	protected boolean isDead=false;
	protected static final int SCROLL_SPEED=1;
	//敵の�?
	protected static final Point TOMB=new Point(-100, -100);
	public boolean isAlive=true;
	public Enemy(int x,int y,MainPanel panel) {
		
		this.x=x;
		this.y=y;
		this.panel=panel;
		loadImage();
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Point getPos() {
		return new Point(x,y);
	}
	public void setPos(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public void draw(Graphics g) {
		g.drawImage(image,x,y,null);
	}
	protected void loadImage() {
		width=image.getWidth(panel);
		height=image.getHeight(panel);
	}
	public boolean collideWith(Shot shot) {
	    // エイリアンの矩形を求め�?
	    Rectangle rectAlien = new Rectangle(x, y, width, height);
	    // 弾の矩形を求め�?
	    Point pos = shot.getPos();
	    Rectangle rectShot = new Rectangle(pos.x, pos.y, 
	            shot.getWidth(), shot.getHeight());
	
	    // 矩形同士が重なって�?るか調べ�?
	    // 重なって�?たら衝突して�?�?
	    return rectAlien.intersects(rectShot);
	}
	public void move() {
		y=y+SCROLL_SPEED;
		if(y>panel.HEIGHT) {
			die();
		}
	}

	public void die() {
		x=TOMB.x;
		y=TOMB.y;
		isDead=true;
	}
	public boolean isInStage() {
		// TODO 自動生成されたメソ�?ド�?�スタ�?
		return y>0;
	}
	
}
