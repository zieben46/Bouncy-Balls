import java.awt.Color;
import java.util.ArrayList;

public class Level6 extends Master {

	public void makeManyBalls() {
		this.ballList=new ArrayList<Ball>();
		for (int i=1;i<=numbBalls;i++) {
			int randomX=(int) (Math.random()*(panelWidth-100))+40;
			int randomY=(int) (Math.random()*(panelHeight-100))+40;
			//int randomX=80;
			//int randomY=80;
			int randomVeloX=(int) (Math.random()*2);
			int randomVeloY=(int) (Math.random()*2);
			//int randomVeloX=0;
			//int randomVeloY=0;

			if (randomVeloX==0) {
				if (i%3>=1) {
					randomVeloX=-1;
				} else {
					randomVeloX=1;
				}
			}
			
			if (randomVeloY==0) {
				if (i%3>=1) {
					randomVeloY=-1;
				} else {
					randomVeloY=1;
				}
			}

			Color newColor=Color.BLUE;
			if (i%2==1) {
				newColor=Color.RED;
			}
			ballList.add(new Ball(randomX, randomY, randomVeloX, randomVeloY, newColor, manyBallsRad));
		}
	}

	public void detectHit() {
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
					ball.setColor(new Color(255,228,178));
					Master.myBallRad=Master.myBallRad+3;
				} else if (ball.getColor()==Color.RED) {
					health=health-10;
					//Thread.sleep(100);
					System.out.println("HIT!  YOUR HEALTH IS: "+health);
				}
			}
		}
		ballList.remove(removeBall);
	}

}
