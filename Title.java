package shooting;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Title extends JPanel implements KeyListener {
	public static final int WIDTH=640;
	public static final int HEIGHT=480;
	private Image image;
	public Title() {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		addKeyListener(this);
		ImageIcon icon=new ImageIcon(getClass().getResource("image/2xcluster-900x504.png"));
		image=icon.getImage();
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image,0,0,null);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_ENTER) {
			System.out.println("aaaa");
			Invader.changeToGame();
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソ�?ド�?�スタ�?
		
	}

	
}
