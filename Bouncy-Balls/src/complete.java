import java.awt.Color;
import java.util.ArrayList;

public class complete extends Master {

	public void makeManyBalls() {
		ballList=new ArrayList<Ball>();
		for (int i=1;i<=numbBalls;i++) {
			int randomX=(int) (Math.random()*((230)+20));
			int randomY=(int) (Math.random()*((230)+20));
			//int randomX=80;
			//int randomY=80;
			int randomVeloX=2;
			int randomVeloY=2;
			//int randomVeloX=0;
			//int randomVeloY=0;
			ballList.add(new Ball(randomX+100, randomY+100, randomVeloX, randomVeloY,new Color(randomX, randomY, 2), randomVeloY));
			//ballList.add(new Ball(randomX, randomY, randomVeloX, randomVeloY, newColor, manyBallsRad));

		}
	}

	public boolean isDead() {
		//return health<=0;	
		return false;
	}

	public boolean testWinner() {
		return false;
	}

	public void detectHit() {
	}
}
