import java.awt.Color;
import java.util.ArrayList;

public class Level5 extends Master {
	
	public void makeManyBalls() {
		this.ballList=new ArrayList<Ball>();
		for (int i=1;i<=numbBalls;i++) {
			int randomVeloX=2;
			int randomVeloY=2;

			Color newColor=Color.BLUE;
			if (i%2==1) {
				newColor=Color.RED;
			}
			//ballList.add(new Ball(randomX, randomY, randomVeloX, randomVeloY,new Color(randColor1, 50, 2)));
			ballList.add(new Ball(85+20*i,85+20*i, randomVeloX, randomVeloY, newColor, manyBallsRad));

		}
		for (int i=1;i<=numbBalls-1;i++) {
			int randomVeloX=-2;
			int randomVeloY=-2;

			Color newColor=Color.BLUE;
			if (i%2==1) {
				newColor=Color.RED;
			}
			//ballList.add(new Ball(randomX, randomY, randomVeloX, randomVeloY,new Color(randColor1, 50, 2)));
			ballList.add(new Ball(500-20*i,500-20*i, randomVeloX, randomVeloY, newColor, manyBallsRad));
		}
	}
}
