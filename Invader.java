package shooting;

import javax.swing.JFrame;

public class Invader extends JFrame{
	public static Title title;
	public static MainPanel panel;
	public static Invader frame;
	public Invader() {
		setTitle("Kodaira Wars!!");
		setResizable(false);
		title=new Title();
		add(title);
		//パネルサイズに合わせてフレー�?サイズを�?�動設�?
        pack();
	}
	public static void main(String[] args) {
		frame=new Invader();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public static void changeToTitle() {
		frame.remove(panel);
		frame.add(title);
		frame.repaint();
		frame.validate();
		title.requestFocus();
	}
	public static void changeToGame() {
		frame.remove(title);
		panel=new MainPanel();
		frame.add(panel);
		frame.repaint();
		frame.validate();
		panel.requestFocus();
		
	}
	
}
 