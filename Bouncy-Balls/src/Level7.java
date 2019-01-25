import java.awt.Color;
import java.util.ArrayList;

public class Level7 extends Master {

	public void makeManyBalls() {
		ballList=new ArrayList<Ball>();
		for (int i=1;i<=numbBalls;i++) {
			for (int j=1;j<=5;j++) {
				Color newColor=Color.BLUE;
				if (j%2==1) {
					newColor=Color.RED;
				}

				ballList.add(new Ball((10*i+31*j), (10+12*i), -1, -2, newColor, manyBallsRad));

			}
		}
		for (int i=1;i<=numbBalls;i++) {
			for (int j=1;j<=5;j++) {
				Color newColor=Color.BLUE;
				if (j%2==1) {
					newColor=Color.RED;
				}

				ballList.add(new Ball((10*i+31*j), (170+12*i), -1, -2, newColor, manyBallsRad));
			}
		}
		
		for (int i=1;i<=numbBalls;i++) {
			for (int j=1;j<=5;j++) {
				Color newColor=Color.BLUE;
				if (j%2==1) {
					newColor=Color.RED;
				}

				ballList.add(new Ball((10*i+31*j), (340+12*i), -1, -2, newColor, manyBallsRad));
			}
		}
	}

}
