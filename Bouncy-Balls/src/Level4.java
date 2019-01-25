import java.awt.Color;
import java.util.ArrayList;

public class Level4 extends Master {
	
	public void makeManyBalls() {
		ballList=new ArrayList<Ball>();
		for (int i=1;i<=numbBalls;i++) {
			int randomX=(int) (Math.random()*(panelWidth-450))+140;
			int randomY=(int) (Math.random()*(panelHeight-450))+140;
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

}
