package shooting;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player {
	private static final int RIGHT=1;
	private static final int LEFT=0;
	private static final int UP=2;
	private static final int DOWN=3;
	private static final int SPEED=5;
	private int x;
	private int y;
	private int width;
	private int height;
	private Image image;
	private MainPanel panel;
	private int life=5;
	public Player(int x,int y,MainPanel panel) {
		this.x=x;
		this.y=y;
		this.panel=panel;
		loadImage();
		
	}
	public void move(int dir) {
		if(dir==LEFT) {
			x=x-SPEED;
		}
		if(dir==RIGHT) {
			x=x+SPEED;
		}
		if(dir==UP) {
			y=y-SPEED;
		}
		if(dir==DOWN) {
			y=y+SPEED;
		}
		if(x<0) {
			x=0;
		}
		if(x>MainPanel.WIDTH-width) {
			x=MainPanel.WIDTH-width;
		}
		if(y<0) {
			y=0;
		}
		if(y>MainPanel.HEIGHT-height) {
			y=MainPanel.HEIGHT-height;
		}
	}
	public boolean collideWith(HyperShot shot) {
		Rectangle rectPlayer=new Rectangle(x+2, y+2, width-2, height-2);
		Point pos =shot.getPos();
		Rectangle rectShot =new Rectangle(pos.x, pos.y, shot.getWidth(), shot.geyHeight());
		
		return rectPlayer.intersects(rectShot);
	}
	public boolean collideWith(Enemy enemy) {
		Rectangle rectPlayer=new Rectangle(x+5, y+5, width-5, height-5);
		Point pos1=enemy.getPos();
		Rectangle rectEnemy=new Rectangle(pos1.x,pos1.y,enemy.getWidth(),enemy.getHeight());
		return rectPlayer.intersects(rectEnemy);
	}
	public Point getPos(){
		return new Point(x, y);
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void draw(Graphics g) {
		g.drawImage(image,x,y,null);
	}
	
	public int getLife() {
		return life;
	}
	public void loselife() {
		life-=1;
	}
	private void loadImage() {
		ImageIcon icon =new ImageIcon(getClass().getResource("image/player.gif"));
		image = icon.getImage();
		width = image.getWidth(panel);
	    height = image.getHeight(panel);
	}
	
	
	
}
