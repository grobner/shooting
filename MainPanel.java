package shooting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable,KeyListener {
	//パネルサイズ
	public static final int WIDTH=640;
	public static final int HEIGHT=480;
	//方向定数
	private static final int RIGHT=1;
	private static final int LEFT=0;
	private static final int UP=2;
	private static final int DOWN=3;
	//プレイヤー
	private Player player;
	//連続発�?できる弾の数
	private static final int NUM_SHOT=15;
	private static final int NUM_SHOT1=1000;
	private static final int NUM_SHOT2=1000;
	private static final int NUM_SHOT3=100;
	private static final int NUM_BOSS_SHOT=100;
	static final int FIRE_INTERVAL=100;
	static final int SHOT_INTERVAL=2000;
	//弾
	private Shot[] shots;
	private long lastFire=0;
	private long lastShot=0;
	private Shot1[] shots1;
	private Shot2[] shots2;
	private Shot3[] shots3;
	private BossShot[] bossShot;
	//敵の数
	private static final int NUM_ALIEN=50;
	private static final int NUM_ALIEN2=50;
	private static final int NUM_ALIEN3=50;
	//敵
	private Alien1[] aliens1;
	private Alien2[] aliens2;
	private Alien2[] aliens3;
	private Boss boss;
	//キーの状�?
	private boolean leftPressed=false;
	private boolean rightPressed=false;
	private boolean upPressed=false;
	private boolean downPressed=false;
	private boolean firePressed;
	//ゲー�?ループ用スレ�?�?
	private Thread gameLoop;
	//乱数発生器
	private Random rand;
	//進捗確認用変数
	private int stage=0;
	private int appear=0;
	//コンストラクタ
	public MainPanel() {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		initGame();
		Random random=new Random();
		addKeyListener(this);
		gameLoop=new Thread(this);
		gameLoop.start();
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// �?らな�?
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key =e.getKeyCode();
		if(key==KeyEvent.VK_RIGHT) {
			rightPressed=true;
		}
		if(key==KeyEvent.VK_LEFT) {
			leftPressed=true;
		}
		if(key==KeyEvent.VK_UP) {
			upPressed=true;
		}
		if(key==KeyEvent.VK_DOWN) {
			downPressed=true;
		}
		if(key==KeyEvent.VK_SPACE) {
			firePressed=true;
		}
		if(key==KeyEvent.VK_1) {
			stage=0;
			
		}
		if(key==KeyEvent.VK_2) {
			stage=1;
			
		}
		if(key==KeyEvent.VK_3) {
			stage=2;
			
		}
	}

	
	public void keyReleased(KeyEvent e) {
		int key =e.getKeyCode();
		if(key==KeyEvent.VK_RIGHT) {
			rightPressed=false;
		}
		if(key==KeyEvent.VK_LEFT) {
			leftPressed=false;
		}
		if(key==KeyEvent.VK_UP) {
			upPressed=false;
		}
		if (key==KeyEvent.VK_DOWN) {
			downPressed=false;
		}
		if(key==KeyEvent.VK_SPACE) {
			firePressed=false;
		}
	
		
	}

	public void run() {
		while(true) {
			//移�?
			alienAppear();
			move();
			//弾の発�?
			if(firePressed) {
				tryToFire();
			}
			//エイリアンの攻�?
			alienAttack();
			//衝突判�?
			collisionDetection();
			checkEnemy();
			repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
		
				e.printStackTrace();
			}
		}
		
	}
	//以下ゲー�?�?の処�?
	//ゲー�?の初期�?
	private void initGame(){
		//プレイヤーの作�??
		player=new Player(0, HEIGHT-20, this);
		//弾の作�??
		shots =new Shot[NUM_SHOT];
		for (int i = 0; i < shots.length; i++) {
			shots[i]=new Shot(this);
		}
		//エイリアンの作�??
		aliens1=new Alien1[NUM_ALIEN];
		for (int i = 0; i < aliens1.length; i++) {
			aliens1[i]=new Alien1(aliens1[i].TOMB.x,aliens1[i].TOMB.y,3,this);
		}
		aliens2=new Alien2[NUM_ALIEN2];
		for (int i = 0; i < aliens2.length; i++) {
			aliens2[i]=new Alien2(aliens2[i].TOMB.x,aliens2[i].TOMB.y, this);
		}
		aliens3=new Alien2[NUM_ALIEN3];
		for (int i = 0; i < aliens3.length; i++) {
			aliens3[i]=new Alien2(aliens3[i].TOMB.x,aliens3[i].TOMB.y, this);
		}
		boss=new Boss(boss.TOMB.x, boss.TOMB.y, this);
		//ショ�?�?1の作�??
		shots1=new Shot1[NUM_SHOT1];
		for (int i = 0; i < shots1.length; i++) {
			shots1[i]=new Shot1(this);
		}
		//ショ�?ト２�?�作�??
		shots2=new Shot2[NUM_SHOT2];
		for (int j = 0; j < shots2.length; j++) {
			shots2[j]=new Shot2(this);
		}
		//ショ�?�?3の作�??
		shots3=new Shot3[NUM_SHOT3];
		for (int i = 0; i < shots3.length; i++) {
			shots3[i]=new Shot3(this);
		}
		bossShot=new BossShot[NUM_BOSS_SHOT];
		for (int i = 0; i < bossShot.length; i++) {
			bossShot[i]=new BossShot(this);
		}
		appear=0;
	}
	private void gameClear() {
		System.out.println("bbbbb");
		stage=0;
		appear=0;
//		ImageIcon icon=new ImageIcon(getClass().getResource("image/clear.png"));
//		Icon image=(Icon) icon.getImage();
		JOptionPane.showMessageDialog(null, "GAME CLEAR"); 
		Invader.changeToTitle();
	}
	private void move() {
		//プレイヤーの移�?
		if(leftPressed) {
			player.move(LEFT);
		}
		if(rightPressed) {
			player.move(RIGHT);
		}
		if(upPressed) {
			player.move(UP);
		}
		if(downPressed) {
			player.move(DOWN);
		}
		//弾の移�?
		for (int i = 0; i < shots.length; i++) {
			shots[i].move();
		}
		if(stage==0) {
			for (int i = 0; i < aliens1.length; i++) {
				aliens1[i].move();
			}
		}
		
		for (int i = 0; i < aliens2.length; i++) {
			aliens2[i].move();
		}
		for (int i = 0; i < aliens3.length; i++) {
			aliens3[i].move();
		}
		for (int i = 0; i < shots1.length; i++) {
			shots1[i].move();
		}
		for (int i = 0; i < shots2.length; i++) {
			shots2[i].move();
		}
		for (int i = 0; i < shots3.length; i++) {
			shots3[i].move(player);
		}
		for (int i = 0; i < bossShot.length; i++) {
			bossShot[i].move();
		}
		
	}
	//衝突判�?
	private void collisionDetection() {
		for (int i = 0; i < aliens1.length; i++) {
			for (int j = 0; j < shots.length; j++) {
				if(aliens1[i].collideWith(shots[j])) {
					aliens1[i].die();
					shots[j].store();
					break;
				}
				
			}
		}
		for (int i = 0; i < aliens2.length; i++) {
			for (int j = 0; j < shots.length; j++) {
				if(aliens2[i].collideWith(shots[j])) {
					aliens2[i].loselife();
					shots[j].store();
					break;
				}
				
			}
		}
		for (int i = 0; i < aliens3.length; i++) {
			for (int j = 0; j < shots.length; j++) {
				if(aliens3[i].collideWith(shots[j])) {
					aliens3[i].loselife();
					shots[j].store();
					break;
				}
				
			}
		}
		for (int i = 0; i < shots.length; i++) {
			if(boss.collideWith(shots[i])) {
				boss.loselife();
				shots[i].store();
				break;
			}
		}
		for (int i = 0; i < shots1.length; i++) {
			if(player.collideWith(shots1[i])) {
				shots1[i].store();
				initGame();
			}
		}
		for (int i = 0; i < shots2.length; i++) {
			if(player.collideWith(shots2[i])) {
				shots2[i].store();
				initGame();
				
				
			}
		}
		for (int i = 0; i < shots3.length; i++) {
			if(player.collideWith(shots3[i])) {
				shots3[i].store();
				initGame();
			}
		}	
		for (int i = 0; i < bossShot.length; i++) {
			if(player.collideWith(bossShot[i])) {
				bossShot[i].store();
				initGame();
			}
		}
		for (int i = 0; i < aliens1.length; i++) {
			if(player.collideWith(aliens1[i])) {
				aliens1[i].die();
				initGame();
				
			}
		}
		for (int i = 0; i < aliens2.length; i++) {
			if(player.collideWith(aliens2[i])) {
				aliens2[i].die();
				initGame();
			}
		}
		for (int i = 0; i < aliens3.length; i++) {
			if(player.collideWith(aliens3[i])) {
				aliens3[i].die();
				initGame();
			}
		}
		if(player.collideWith(boss)) {
			boss.die();
			initGame();
		}
		
	}
	//敵を判�?
	private  void checkEnemy() {
		boolean b=true;
		boolean c=true;
		if(stage==0) {
			
			for (int i = 0; i < aliens1.length; i++) {
				b=b&&aliens1[i].isDead;
			}
			if(b) {
				stage=1;
				b=false;
				appear=0;
			}
		}else if(stage==1) {
			for (int i = 0; i < aliens2.length; i++) {
				c=c&&aliens2[i].isDead;
			}
			if(c) {
				stage=2;
				b=false;
				appear=0;
			}
		}else if(stage==2) {
			if(boss.isDead) {
				gameClear();
			}
		}
		
		
	}
	private void alienAppear() {
		if(stage==0) {
			if(appear>0) {
				return;
			}
			for (int i = 0; i < aliens1.length; i++) {
				aliens1[i].setPos((700*i)%640,-20*i);
				aliens1[i].isDead=false;
				appear=1;
			}
		}else if(stage==1) {
			if(appear>0) {
				return;
			}
			for (int i = 0; i < aliens2.length; i++) {
				aliens2[i].setPos((50*i*i)%640,-40*i);
				aliens2[i].isDead=false;
				appear=1;
			}
		}else if(stage==2) {
			if(appear>0) {
				return;
			}
			boss.setPos(WIDTH/2-boss.width/2, 40);
			boss.isDead=false;
			for(int k=0;k<6;k++) {
				for(int l=0;l<4;l++) {
					for(int i=0;i<4;i++) {
						for (int j = 0; j < bossShot.length; j++) {
							if(bossShot[j].isInstorage()) {
								bossShot[j].setPos(160+80*k, 160+80*l);
								bossShot[j].setSpeed(i);
								break;
							}
						}
					}
					appear=1;
				}
				}
			}
			
	}
	private void alienAttack() {
		if(stage==0) {
			if(System.currentTimeMillis()-lastShot<SHOT_INTERVAL) {
				return; 
			}
			for (int i = 0; i < aliens1.length; i++) {
				if(aliens1[i].isInStage()) {
					for(int j = 0;j<3;j++) {
						for (int j2 = 0; j2 < shots1.length; j2++) {
							if(shots1[j2].isInstorage()) {
							Point pos=aliens1[i].getPos();
							shots1[j2].setPos(pos.x, pos.y);
							shots1[j2].setSpeed(j);
							lastShot=System.currentTimeMillis();
							break;
							}
						}
					 }
				}
			}
		}else if(stage==1) {
			if(System.currentTimeMillis()-lastShot<SHOT_INTERVAL) {
				return;
			}
			for (int i = 0; i < aliens2.length; i++) {
				if(aliens2[i].isInStage()) {
					for(int k = 0;k<2;k++) {
						for (int j = 0; j < shots2.length; j++) {
							if(shots2[j].isInstorage()) {
								Point pos=aliens2[i].getPos();
								shots2[j].setPos(pos.x, pos.y);
								Point pos1=shots2[j].getPos();
								Point pos2=player.getPos();
								double degree=Math.atan2(pos2.y-pos1.y,pos2.x-pos1.x);
								shots2[j].setSpeed((int)Math.round(Math.cos(degree)*5), (int)Math.round(Math.cos(degree)*5));
								lastShot=System.currentTimeMillis();
								break;
							}
						}
					}
					
				}
				
			}
		}else if(stage==2) {
			if(System.currentTimeMillis()-lastShot<SHOT_INTERVAL) {
				return;
			}
			for(int i=0;i<5;i++) {
				for (int j = 0; j <shots3.length; j++) {
					if(shots3[j].isInstorage()) {
						shots3[j].setPos(160+80*i, 160);
						lastShot=System.currentTimeMillis();
						shots3[j].lastMove=System.currentTimeMillis();
						break;
					}
				}
			}
		}
	}	
		 private void tryToFire() {
			 // 前との発�?間隔がFIRE_INTERVAL以下だったら発�?できな�?
			if (System.currentTimeMillis() - lastFire < FIRE_INTERVAL) {
				return;
				}
	
			lastFire = System.currentTimeMillis();
			// 発�?されて�?な�?弾を見つける
			for (int i = 0; i < NUM_SHOT; i++) {
			if (shots[i].isInstorage()) {
			// 弾が保管庫にあれば発�?できる
			// 弾の座標をプレイヤーの座標にすれば発�?され�?
			Point pos = player.getPos();
			shots[i].setPos(pos.x + player.getWidth() / 2, pos.y);
			 // 1つ見つけたら発�?してbreakでループをぬける
			break;
						            }
						        }
					    }	
		 //描画処�?
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			//プレイヤーの描画
			player.draw(g);
			//弾の描画
			for (int i = 0; i < shots.length; i++) {
				shots[i].draw(g);
			}
			for (int i = 0; i < shots1.length; i++) {
				shots1[i].draw(g);
			}
			for (int i = 0; i < shots2.length; i++) {
				shots2[i].draw(g);
			}
			for (int i = 0; i < shots3.length; i++) {
				shots3[i].draw(g);
			}
			for (int i = 0; i < bossShot.length; i++) {
				bossShot[i].draw(g);
			}
			//エイリアンの描画
			for (int i = 0; i < aliens1.length; i++) {
				aliens1[i].draw(g);
			}
			for (int i = 0; i < aliens2.length; i++) {
				aliens2[i].draw(g);
			}
			for (int i = 0; i < aliens3.length; i++) {
				aliens3[i].draw(g);
			}
			boss.draw(g);
		}

}
