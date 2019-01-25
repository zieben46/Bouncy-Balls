import javax.swing.JOptionPane;

public class Beginning {
	public static int levelCount=1;
	public static void main(String[] args) throws InterruptedException {
		JOptionPane.showMessageDialog(null, "Eat the blue dots and dodge the red dots. "
				+ "Don't get hit more than 4 times.  Good luck!", "", JOptionPane.INFORMATION_MESSAGE);

		Master level1 = new Level1();
		level1.start(16,8,65);

		JOptionPane.showMessageDialog(null, "Good job! Easy huh? You have reached level 2.  "
				+ "Try the left and right mouse buttons (: Good luck!", "Level 1 complete!", JOptionPane.INFORMATION_MESSAGE);

		levelCount++;
		Master level2 = new Level2();
		level2.start(15,5,45);
		JOptionPane.showMessageDialog(null, "Piece of cake.  You have reached level 3.","", JOptionPane.INFORMATION_MESSAGE);

		levelCount++;
		Master level3 = new Level3();
		level3.start(5,25,30);

		JOptionPane.showMessageDialog(null, "Good job... Let's ramp it up.","", JOptionPane.INFORMATION_MESSAGE);

		levelCount++;
		Master level4 = new Level4();
		level4.start(5,50,40);
		
		JOptionPane.showMessageDialog(null, "Level 4 Complete","", JOptionPane.INFORMATION_MESSAGE);

		levelCount++;
		Master level5 = new Level5();
		level5.start(5,50,17);
		
		JOptionPane.showMessageDialog(null, "Level 5 Complete","", JOptionPane.INFORMATION_MESSAGE);
		
		levelCount++;
		Master level6 = new Level6();
		level6.start(5, 25,50);
		
		JOptionPane.showMessageDialog(null, "Level 6 Complete","", JOptionPane.INFORMATION_MESSAGE);
		
		levelCount++;
		Master level7 = new Level7();
		level7.start(10, 5,25);
		
		JOptionPane.showMessageDialog(null, "Level 7 Complete","", JOptionPane.INFORMATION_MESSAGE);
		
		levelCount++;
		Master level8 = new Level8();
		level8.start(10, 10,25);
		
		JOptionPane.showMessageDialog(null, "Level 8 Complete","", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Congrats!  That's all for now...","", JOptionPane.INFORMATION_MESSAGE);
		
		levelCount++;
		Master complete = new complete();
		complete.start(10, 10,500);
	}
}