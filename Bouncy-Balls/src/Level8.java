import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Level8 extends Master {
	private static Point suckerCenter;
	private static int SuckerRad=15;

	public void makeManyBalls() {
		ballList=new ArrayList<Ball>();
		for (int i=1;i<=numbBalls;i++) {
			int randomX=(int) (Math.random()*(frameWidth-450))+25;
			int randomY=(int) (Math.random()*(frameHeight-450))+25;
			int randomVeloX=(int) (Math.random()*2-1);
			int randomVeloY=(int) (Math.random()*2-1);
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
			ballList.add(new Ball(randomX, randomY, randomVeloX, randomVeloY, newColor, manyBallsRad));
		}
	}
	
	private void moveSucker(){
		SuckerRad=SuckerRad-1;
		if (SuckerRad==0) {
			SuckerRad=15;
			puffRad=0;
		}
	}

	protected void moveStuff() {
		super.moveStuff();
		moveSucker();
	}
	
	
	

	@Override public void paint(Graphics g2) {
		super.paint(g2);
		this.setBackground(new Color(255,228,178));
		getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.gray));
		Graphics2D g2d = (Graphics2D) g2;
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

		//draws theSucker
		Point suckerCenter = new Point (500,500);
		float[] dist2 = {0f, .9f};
		Color[] colors2 = {Color.RED,Color.WHITE };
		RadialGradientPaint p2 =
				new RadialGradientPaint(suckerCenter, 20, dist2 , colors2);
		g2d.setPaint(p2);
		g2d.setStroke(new BasicStroke(30));
		g2d.fillOval(500-SuckerRad, 500-SuckerRad,2*SuckerRad,2*SuckerRad);
	}

	@Override public void start(int myBallRad, int manyBallsRad, int numbBalls) throws InterruptedException {

		frameHeight=800;
		frameWidth=700;
		panelHeight=700;
		panelWidth=700;
		frame = new JFrame("Bouncy Balls");
		Master game = new Level8();
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
		panel.add(game);



		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.getContentPane().add(BorderLayout.SOUTH, bottemPanel);

		//frame.pack();

		//frame.add(game);
		//frame.add(healthLab,2);

		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
				cursorImg, new Point(0, 0), "blank cursor");


		frame.setSize(frameWidth, frameHeight);
		frame.getContentPane().setCursor(blankCursor);

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
		int timer=0;
		while (true) {
			mouse = MouseInfo.getPointerInfo().getLocation();


			if (timer==40) {
				conDiverge(500,500,1);
				timer=0;
			}
			timer++;
			mouseX=(int) (mouse.getX()-8);
			mouseY=(int) (mouse.getY()-31);
			mouseCent=new Point(mouseX,mouseY);
			game.moveStuff();
			detectHit();
			game.repaint();
			repaint();
			returnMouse();
			Thread.sleep(10);
			testSucker();
			if (testWinner()==true) {
				health=50;
				frame.dispose();
				break;
			}

			if (isDead()==true) {
				JOptionPane.showMessageDialog(null, "You are dead!", "", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				health=50;
				start(myBallRad, manyBallsRad, numbBalls);
			}
		} 
	}

	private void testSucker() {
		for (Ball ball:ballList) {
			if ((ball.getX()>=490&&ball.getX()<=510)&&(ball.getY()>=490&&ball.getY()<=510)) {
				health=health-10;
				removeBall=ball;
				ball.setColor(new Color(255,228,178));
			}
		}
		ballList.remove(removeBall);
	}
}
