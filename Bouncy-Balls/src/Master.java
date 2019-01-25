
import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Master extends JPanel implements KeyListener {
	protected static Ball ball;
	protected static ArrayList<Ball> ballList;
	protected static JFrame frame;
	protected static int frameWidth;
	protected static int frameHeight;
	protected static Ball myBall;
	protected static PointerInfo pi;
	protected static int counter;
	protected static Point mouse;
	protected static int mouseX;
	protected static int mouseY;
	protected static Integer health=50;
	protected static int myBallRad;
	protected static int manyBallsRad;
	protected static Ball removeBall;
	protected static int numbBalls;
	protected static int puffRad=0;
	protected static boolean puffConv=false;
	protected static boolean puffDiverg=false;
	protected static Point mouseCent;
	protected static int panelWidth;
	protected static int panelHeight;


	public void makeManyBalls() {
		ballList=new ArrayList<Ball>();
		for (int i=1;i<=numbBalls;i++) {
			int randomX=(int) (Math.random()*(panelWidth-450))+40;
			int randomY=(int) (Math.random()*(panelHeight-450))+40;
			//int randomX=80;
			//int randomY=80;
			int randomVeloX=(int) (Math.random()*10-5);
			int randomVeloY=(int) (Math.random()*10-5);
			//int randomVeloX=0;
			//int randomVeloY=0;

			if (randomVeloX==0) {
				randomVeloX=1;
			}
			if (randomVeloY==0) {
				randomVeloY=1;
			}

			Color newColor=Color.BLUE;
			if (i%2==1) {
				newColor=Color.RED;
			}
			//ballList.add(new Ball(randomX, randomY, randomVeloX, randomVeloY,new Color(randColor1, 50, 2)));
			ballList.add(new Ball(randomX, randomY, randomVeloX, randomVeloY, newColor, manyBallsRad));

		}
	}

	private void moveBall(Ball ball) {
		wallBounce(ball);
		int x=ball.getX();
		int y=ball.getY();
		float veloX=ball.getVeloX();
		float veloY=ball.getVeloY();
		ball.setX(Math.round((x+veloX)));
		ball.setY(Math.round(y+veloY));
	}

	private void moveConvPuff(){
		puffRad=puffRad+3;
		if (puffRad>myBallRad+30) {
			puffConv=false;
			puffRad=0;
		}
	}

	private void moveDivergPuff(){
		puffRad=puffRad-3;
		if (puffRad<myBallRad) {
			puffDiverg=false;
			puffRad=0;
		}
	}

	protected void moveStuff() {
		for (Ball ball:ballList) {
			moveBall(ball);
		}
		if 	(puffConv==true) {
			moveConvPuff();
		}
		if 	(puffDiverg==true) {
			moveDivergPuff();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(new Color(255,228,178));
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.gray));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

		//draws manyBalls
		for (Ball ball:ballList) {
			GradientPaint gradient = new GradientPaint(ball.getX()-manyBallsRad,ball.getY()-manyBallsRad,ball.getColor(),ball.getX()+manyBallsRad,ball.getY()+manyBallsRad,Color.WHITE);
			g2d.setPaint(gradient);
			g2d.fillOval(ball.getX()-manyBallsRad, ball.getY()-manyBallsRad, manyBallsRad*2, manyBallsRad*2);
		}

		//draws puff
		float[] dist = {0f, .9f};
		Color[] colors = {Color.RED,Color.WHITE };
		RadialGradientPaint p =
				new RadialGradientPaint(mouseCent, myBallRad+60, dist , colors);
		g2d.setPaint(p);
		g2d.setStroke(new BasicStroke(30));
		g2d.drawOval(mouseX-puffRad,mouseY-puffRad, 2*puffRad, 2*puffRad);

		//draws myBall
		GradientPaint gradient = new GradientPaint(mouseX+myBallRad,mouseY+myBallRad,Color.WHITE,mouseX-myBallRad,mouseY-myBallRad,Color.BLACK);
		g2d.setPaint(gradient);
		g2d.fillOval(mouseX-myBallRad, mouseY-myBallRad, 2*myBallRad, 2*myBallRad);

		//draws current health
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Verdana", Font.BOLD, 16));
		g2d.drawString("HEALTH: "+health.toString(), 300, 695);
	}

	public void start(int myBallRad, int manyBallsRad, int numbBalls) throws InterruptedException {

		frameHeight=800;
		frameWidth=700;
		panelHeight=700;
		panelWidth=690;
		health=50;
		frame = new JFrame("Bouncy Balls");
		Master game = new Master();
		Master.myBallRad=myBallRad;
		Master.manyBallsRad=manyBallsRad;
		Master.numbBalls=numbBalls;

		JLabel levelNumb = new JLabel("LEVEL: "+Beginning.levelCount);

		JPanel bottemPanel= new JPanel();
		bottemPanel.setLayout(new GridLayout());
		bottemPanel.add(levelNumb);

		game.setMinimumSize(new Dimension(700,700));
		game.setPreferredSize(new Dimension(700, 700));

		frame.setLayout(new FlowLayout());
		JPanel panel=new JPanel();
		game.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(game);

		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, bottemPanel);
		frame.pack();

		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		//Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");


		frame.setSize(frameWidth, frameHeight);
		//frame.getContentPane().setCursor(blankCursor);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);

		makeManyBalls();


		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("MOUSE X : "+mouseX);
				System.out.println("MOUSE Y : "+mouseY);
				//System.out.println("myBall X : "+myBall.getX());
				//System.out.println("myBall Y : "+myBall.getY());

				//System.out.println("MY CLICK IS HERE:"+e.getX()+"  "+e.getY());
				//System.out.println("MY DOT IS HERE:"+x+"  "+y);
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					conDiverge(mouseX,mouseY,1);
					puffRad=Master.myBallRad+30;
					puffDiverg=true;
				} else {
					conDiverge(mouseX,mouseY,-1.05);
					puffRad=Master.myBallRad;
					puffConv=true;
				}
			}
			public void mouseReleased(MouseEvent e) {
			}
		});

		while (true) {
			mouse = MouseInfo.getPointerInfo().getLocation();

			mouseX=(int) (mouse.getX()-8);
			mouseY=(int) (mouse.getY()-31);
			mouseCent=new Point(mouseX,mouseY);
			game.moveStuff();
			detectHit();
			game.repaint();
			returnMouse();
			Thread.sleep(10);
			if (testWinner()==true) {
				frame.dispose();
				break;
			}
			if (isDead()==true) {
				JOptionPane.showMessageDialog(null, "You are dead!", "", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				start(myBallRad, manyBallsRad, numbBalls);
			}
		} 
	}

	public void wallBounce(Ball ball) {
		int x=(int) (ball.getX()+ball.getVeloX());
		int y=(int) (ball.getY()+ball.getVeloY());
		if (x>=panelWidth-manyBallsRad||x<=0+manyBallsRad+7) {

			ball.setVeloX((-ball.getVeloX()));
		}
		if (y>=panelHeight-manyBallsRad||y<=0+manyBallsRad) {
			ball.setVeloY((-ball.getVeloY()));
		}
		//			System.out.println("X POSITION: "+ball.getX());
		//			System.out.println("Y POSITION: "+ball.getY());
		//			System.out.println("X VELOCITY: "+ball.getVeloX());
		//			System.out.println("Y VELOCITY: "+ball.getVeloY());
		//System.out.println("VELOCITY: "+Math.sqrt(ball.getVeloY()*ball.getVeloY()+ball.getVeloX()*ball.getVeloX()));	
	}

	//	public void detectHit() {
	//		for (Ball ball:ballList){
	//			int ballX=ball.getX();
	//			int ballY=ball.getY();
	//			int deltaX=ballX-mouseX;
	//			int deltaY=ballY-mouseY;
	//			double distance=Math.sqrt(deltaX*deltaX+deltaY*deltaY);
	//			double gap=distance-myBallRad-manyBallsRad;
	//			if (gap<=0) {
	//				removeBall=ball;
	//				if (ball.getColor()==Color.BLUE) {
	//					ball.setColor(new Color(255,228,178));
	//				} else if (ball.getColor()==Color.RED) {
	//					ball.setColor(new Color(255,228,178));
	//					health=health-10;
	//					System.out.println("HIT!  YOUR HEALTH IS: "+health);
	//				}
	//			}
	//		}
	//		ballList.remove(removeBall);
	//	}


	public void detectHit() {
		Iterator<Ball> it = ballList.iterator();
		while (it.hasNext()) {
			Ball ball = it.next();
			int ballX=ball.getX();
			int ballY=ball.getY();
			int deltaX=ballX-mouseX;
			int deltaY=ballY-mouseY;
			double distance=Math.sqrt(deltaX*deltaX+deltaY*deltaY);
			double gap=distance-myBallRad-manyBallsRad;
			if (gap<=0) {
				if (ball.getColor()==Color.BLUE) {
					ball.setColor(new Color(255,228,178));
					it.remove();
				} else if (ball.getColor()==Color.RED) {
					ball.setColor(new Color(255,228,178));
					health=health-10;
					System.out.println("HIT!  YOUR HEALTH IS: "+health);
					it.remove();
				}
				//it.remove();
			}
		}
	}

	public boolean testWinner() {
		boolean winner=true;
		for (Ball ball:ballList) {
			if (ball.getColor()==Color.BLUE) {
				winner=false;
			}
		}
		return winner;
	}

	public boolean isDead() {
		return health<=0;	
		//return false;
	}

	public void conDiverge(int clickedX, int clickedY, double inward) {
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

	public void returnMouse() {
		if (mouse.getX()>panelWidth) {
			Robot r;
			try {
				r = new Robot();
				r.mouseMove(panelWidth,(int) mouse.getY());
				//r.mouseMove(40,40);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}

		if (mouse.getY()>panelHeight+20) {
			Robot r;
			try {
				r = new Robot();
				r.mouseMove((int) mouse.getX(),panelHeight+20);
				//r.mouseMove(40,40);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}

		if (mouse.getX()<20) {
			Robot r;
			try {
				r = new Robot();
				r.mouseMove(20,(int) (mouse.getY()));
				//r.mouseMove(40,40);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}

		if (mouse.getY()<40) {
			Robot r;
			try {
				r = new Robot();
				r.mouseMove((int) mouse.getX(),40);
				//r.mouseMove(40,40);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}
	//how to cheat

	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e.getKeyCode());
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.print(e.getKeyChar());
		if (e.getKeyChar()=='C') {
			for (Ball ball: ballList) {
				ball.setColor(Color.RED);
			}
		} else if (e.getKeyChar()=='') {
			System.exit(0);
		}
	}
}
