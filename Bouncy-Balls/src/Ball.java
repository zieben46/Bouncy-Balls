import java.awt.Color;

public class Ball {
	private int x;
	private int y;
	private float veloX;
	private float veloY;
	private Color ballColor;
	private int ballRadius;
	
	public Ball(int x, int y, Color ballColor){
		this.x=x;
		this.y=y;
		this.ballColor=ballColor;
	}
	
	public Ball(int x, int y, int veloX, int veloY, Color ballColor, int ballRadius){
		this.x=x-5;
		this.y=y-5;
		this.ballColor=ballColor;
		this.veloX=veloX;
		this.veloY=veloY;
		this.ballRadius=ballRadius;
	}
	
	protected void setX(int x) {
		this.x=x;
	}
		
	protected int getX() {
		return this.x;
	}
	
	protected int getY() {
		return this.y;
	}
	
	protected void setY(int y) {
		this.y=y;
	}
	
	protected float getVeloX() {
		return this.veloX;
	}
	
	public void setVeloX(float veloX) {
		this.veloX=veloX;
	}
	
	public float getVeloY() {
		return this.veloY;
	}
	
	public void setVeloY(float veloY) {
		this.veloY=veloY;
	}
	
	public Color getColor() {
		return this.ballColor;
	}
	
	public void setColor(Color ballColor) {
		this.ballColor=ballColor;
	}

	public void setColor(int translucent) {
		this.ballColor=new Color(0,0,0);
		// TODO Auto-generated method stub
	}
	
	public int getBalRadius() {
		return this.ballRadius;
	}
	
	public void setBallRadius(int ballRadius) {
		this.ballRadius=ballRadius;
	}

}
