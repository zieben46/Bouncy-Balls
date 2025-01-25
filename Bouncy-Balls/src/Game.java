
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Game extends JPanel {
	private static Ball ball;
	private static ArrayList<Ball> ballList;
	static JFrame frame;
	private static int frameWidth;
	private static int frameHeight;
	private static Ball myBall;
	private static PointerInfo pi;
	private static int counter;
	private static Point mouse;
	private static int mouseX;
	private static int mouseY;
	private static int health=100;
	private static int myBallRad;
	private static int manyBallsRad;
	private static Ball removeBall;


	private static void makeManyBalls() {
		ballList=new ArrayList<Ball>();
		for (int i=1;i<=100;i++) {
			int randomX=(int) (Math.random()*(frameWidth-100))+15;
			int randomY=(int) (Math.random()*(frameHeight-100))+15;
			//int randomX=80;
			//int randomY=80;
			//int randomVeloX=(int) (Math.random()*10-5);
			//int randomVeloY=(int) (Math.random()*10-5);
			int randomVeloX=0;
			int randomVeloY=0;
			int randColor1=(int) (Math.random()*255);
			int randColor2=(int) (Math.random()*255);
			int randColor3=(int) (Math.random()*255);

			//			if (randomVeloX==0) {
			//				randomVeloX=1;
			//			}
			//			if (randomVeloY==0) {
			//				randomVeloY=1;
			//			}

			Color newColor=Color.BLUE;
			if (i%2==1) {
				newColor=Color.RED;
			}
			//ballList.add(new Ball(randomX, randomY, randomVeloX, randomVeloY,new Color(randColor1, 50, 2)));
			ballList.add(new Ball(randomX, randomY, randomVeloX, randomVeloY, newColor, manyBallsRad));

		}
	}

	private void moveBall(Ball ball) {
		testWall(ball);
		int x=ball.getX();
		int y=ball.getY();
		float veloX=ball.getVeloX();
		float veloY=ball.getVeloY();
		ball.setX(Math.round((x+veloX)));
		ball.setY(Math.round(y+veloY));
	}

	private void moveStuff() {
		for (Ball ball:ballList) {
			moveBall(ball);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.WHITE);
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.gray));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		//ballList.remove(removeBall);

			for (Ball ball:ballList) {
				g2d.setColor(ball.getColor());
				g2d.fillOval(ball.getX()-manyBallsRad, ball.getY()-manyBallsRad, manyBallsRad*2, manyBallsRad*2);
			}
			g2d.setColor(Color.BLACK);
			g2d.fillOval(mouseX-myBallRad, mouseY-myBallRad, myBallRad*2, myBallRad*2);
		//} catch (ConcurrentModificationException e) {}
		//g2d.setColor(Color.BLACK);
		//g2d.fillOval(mouseX-myBallRad, mouseY-myBallRad, myBallRad*2, myBallRad*2);

//				for (int i=1;i<50;i++) {
//					g2d.drawLine(0, 20*i, 700, 20*i);
//				}
//				
//				for (int i=1;i<50;i++) {
//					g2d.drawLine(20*i, 0, 20*i, 700);
//				}

		//g2d.setColor(Spring.getColor());
		//g2d.fillOval(Spring.getX(), Spring.getY(), 11, 10);
	}

	// public static void main(String[] args) throws InterruptedException {
	// 	frameHeight=700;
	// 	frameWidth=700;
	// 	frame = new JFrame("Bouncy Balls");
	// 	Game game = new Game();git config --global user.name
	// 	myBallRad=1;
	// 	manyBallsRad=35;
	// 	frame.add(game);

	// 	frame.setSize(frameWidth, frameHeight);

	// 	BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	// 	Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
	// 			cursorImg, new Point(0, 0), "blank cursor");
	// 	frame.getContentPane().setCursor(blankCursor);

	// 	frame.setVisible(true);
	// 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// 	makeManyBalls();
	// 	//myBall=new Ball(400,400,Color.BLACK);
	// 	//pi=new PointerInfo(frame,null);
	// 	//makeFireBall();
	// 	//Spring = new Spring(30,30,2,2,Color.BLACK);

	// 	frame.addMouseListener(new MouseListener() {

	// 		@Override
	// 		public void mouseClicked(MouseEvent e) {
	// 			System.out.println("MOUSE X : "+mouseX);
	// 			System.out.println("MOUSE Y : "+mouseY);
	// 			//System.out.println("myBall X : "+myBall.getX());
	// 			//System.out.println("myBall Y : "+myBall.getY());

	// 			//System.out.println("MY CLICK IS HERE:"+e.getX()+"  "+e.getY());
	// 			//System.out.println("MY DOT IS HERE:"+x+"  "+y);
	// 		}
	// 		public void mouseEntered(MouseEvent e) {
	// 			//converge(e.getX(),e.getY());
	// 			//myBall.setX(e.getX());
	// 			//myBall.setY(e.getY());
	// 		}
	// 		public void mouseExited(MouseEvent e) {
	// 		}
	// 		public void mousePressed(MouseEvent e) {
	// 			//converge(e.getX(),e.getY());

	// 			if (SwingUtilities.isLeftMouseButton(e)) {
	// 				converge(mouseX,mouseY,1);
	// 			} else {
	// 				converge(mouseX,mouseY,-1.05);
	// 			}

	// 		}
	// 		public void mouseReleased(MouseEvent e) {
	// 		}
	// 	});
	// 	JOptionPane.showMessageDialog(null, "Eat the blue dots and dodge the red dots.  Left click brings 'em closer,"
	// 			+ " right click repels.  You have 100 health.  Good luck!", "", JOptionPane.INFORMATION_MESSAGE);
	// 	while (true) {
	// 		mouse = MouseInfo.getPointerInfo().getLocation();
	// 		mouseX=(int) (mouse.getX()-8);
	// 		mouseY=(int) (mouse.getY()-31);
	// 		//myBall.setX(mouseX);
	// 		//myBall.setY(mouseY);
	// 		game.moveStuff();
	// 		detectHit();
	// 		game.repaint();

	// 		Thread.sleep(10);
	// 		//ballList.remove(removeBall);
	// 		if (testWinner()==true) {
	// 			JOptionPane.showMessageDialog(null, "You win!", "", JOptionPane.INFORMATION_MESSAGE);
	// 			break;
	// 		}
	// 		if (isDead()==true) {
	// 			JOptionPane.showMessageDialog(null, "You are dead!", "", JOptionPane.INFORMATION_MESSAGE);
	// 			break;
	// 		}
	// 	} 
	// }

	public static void testWall() {
		for (Ball ball:ballList) {
			int x=ball.getX();
			int y=ball.getY();
			if (x>=frameWidth-30||x<=0) {
				ball.setVeloX((-ball.getVeloX()));
			}
			if (y>=frameHeight-80||y<=0) {
				ball.setVeloY((-ball.getVeloY()));
			}
		}
	}

	public static void testWall(Ball ball) {
		int x=(int) (ball.getX()+ball.getVeloX());
		int y=(int) (ball.getY()+ball.getVeloY());
		if (x>=frameWidth-30||x<=0) {
			ball.setVeloX((-ball.getVeloX()));
		}
		if (y>=frameHeight-55||y<=0) {
			ball.setVeloY((-ball.getVeloY()));
		}
		//			System.out.println("X POSITION: "+ball.getX());
		//			System.out.println("Y POSITION: "+ball.getY());
		//			System.out.println("X VELOCITY: "+ball.getVeloX());
		//			System.out.println("Y VELOCITY: "+ball.getVeloY());
		//System.out.println("VELOCITY: "+Math.sqrt(ball.getVeloY()*ball.getVeloY()+ball.getVeloX()*ball.getVeloX()));	
	}

	private static void detectHit() {
		removeBall=null;
		for (Ball ball:ballList){
			int ballX=ball.getX();
			int ballY=ball.getY();
			int deltaX=ballX-mouseX;
			int deltaY=ballY-mouseY;
			double distance=Math.sqrt(deltaX*deltaX+deltaY*deltaY);
			double gap=distance-myBallRad-manyBallsRad;
			if (gap<=0) {
				removeBall=ball;

				if (ball.getColor()==Color.BLUE) {
					ball.setColor(Color.WHITE);
					myBallRad=myBallRad+1;
				} else if (ball.getColor()==Color.RED) {
					health=health-10;
					//Thread.sleep(100);
					System.out.println("HIT!  YOUR HEALTH IS: "+health);
					ball.setColor(Color.WHITE);
				}
				//removeBall=null;
			}
		}
		//removeBall=null;
		//ballList.remove(removeBall);
		//if (ball.getX()>mouseX-myBallWidth||ball.getX()<mouseX+myBallWidth)
	}

	private static boolean testWinner() {
		boolean winner=true;
		for (Ball ball:ballList) {
			if (ball.getColor()==Color.BLUE) {
				winner=false;
			}
		}
		return winner;
	}

	private static boolean isDead() {
		//return health<=0;	
		return false;
	}

	public static void converge(int clickedX, int clickedY, double inward) {
		for (Ball ball:ballList) {
			int x=ball.getX();
			int y=ball.getY();
			int deltaX=clickedX-x;
			int deltaY=clickedY-y;
			float veloX=ball.getVeloX();
			float veloY=ball.getVeloY();
			float speed = (float) Math.sqrt(veloX*veloX+veloY*veloY);
			float distance=(float) Math.sqrt(deltaX*deltaX+deltaY*deltaY);
			ball.setVeloX((float) ((deltaX/distance)*speed*inward));
			ball.setVeloY((float) ((deltaY/distance)*speed*inward));
		}
	}
}





//private static void makeFireBall() {
//	ballList=new ArrayList<Ball>();
//	int randomX=(int) (Math.random()*frameWidth+1);
//	int randomY=(int) (Math.random()*frameHeight+1);
//	//int randomVeloX=(int) (Math.random()*10-5);
//	//int randomVeloY=(int) (Math.random()*10-5);
//	int randomVeloX=4;
//	int randomVeloY=3;
//	int randColor1=(int) (Math.random()*150);
//	if (randomVeloX==0) {
//		randomVeloX=1;
//	}
//	if (randomVeloY==0) {
//		randomVeloY=1;
//	}
//	double slope=randomVeloY/randomVeloX;
//	for (int i=1;i<=10;i++) {
//		//ballList.add(new Ball(randomX+2*i,(int) Math.sqrt(2*i-randomX*randomX), randomVeloX, randomVeloY,new Color(20+15*i, 50, 2)));
//		ballList.add(new Ball(250-i*3,(int) (250-(slope*i*3)),randomVeloX,randomVeloY,new Color(250-10*i, 250-10*i, 2),15));
//	}
//}
