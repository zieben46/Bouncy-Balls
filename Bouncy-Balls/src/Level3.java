import java.awt.Color;

public class Level3 extends Master {
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
					ball.setColor(Color.WHITE);
					myBallRad=myBallRad+6;
				} else if (ball.getColor()==Color.RED) {
					health=health-10;
					//Thread.sleep(100);
					System.out.println("HIT!  YOUR HEALTH IS: "+health);
					ball.setColor(Color.WHITE);
				}
			}
		}
		ballList.remove(removeBall);
	}
}
