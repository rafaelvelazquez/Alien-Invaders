import java.awt.Color;
import java.awt.Graphics;

public class UFO extends PolygonModel {
	
	int velX;
	
	static int[][] xStruct = {
	};
	
	static int [][] yStruct = {
	};
	
	int rectX = 60;
	int rectY = 75;
	
	Rect r;
	
	int health = 3;
	boolean alive = true;
	
	int crashDMG = 60;

	public UFO(int x, int y, int velX) {
		super(x, y, xStruct, yStruct);
		this.velX = velX;
		r = new Rect(x - (rectX/2), y -(rectY/2) - 5, rectX, rectY);
		
	}
	
	public int getVelX(){
		return velX;
	}
	public void setVelX(int var){
		velX = var;
	}
	
	public int getCrashDMG(){
		return crashDMG;
	}
	
	public void hit(){
		health--;
		if(health <= 0)
			alive = false;
	}
	
	public void moveDownBy(int var){
		y += var;
	}
	
	public void moveSidewaysBy(int var){
		x += var;
	}
	
	public void draw(Graphics g){
		//Draws bounding box for object
		r.setX(x - (rectX/2));
		r.setY(y -(rectY/2) - 5);
		//r.draw(g);
		
		//Draws the object on the window
		
		//top
		g.setColor(Color.white);
		g.fillOval(x - rectX/3, y -(rectY/2) - 5, 40, 60);
		g.setColor(Color.black);
		g.drawOval(x - rectX/3, y -(rectY/2) - 5, 40, 60);
		
		//bottom
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x - rectX/2, y - 10, 60, 40);
		
		//orb
		g.setColor(Color.green);
		g.fillOval(x + 15, y + 5, 12, 12);
		g.fillOval(x - 5, y + 5, 12, 12);
		g.fillOval(x - 25, y + 5, 12, 12);
		
		g.setColor(Color.BLACK);

	}

}
