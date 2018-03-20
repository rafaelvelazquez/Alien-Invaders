import java.awt.Color;
import java.awt.Graphics;

public class Boss extends PolygonModel {
	
	int velX = 1;
	Bullet bleft;
	Bullet bmid;
	Bullet bright;
	
	int sideSpeed = 4;
	int midSpeed = 6;
	boolean upgrade1 = false;
	boolean upgrade2 = false;
	boolean upgrade3 = false;
	
	static int[][] xStruct =
	{
	};
	
	static int [][] yStruct = 
	{
	};
	
	int rectX = 180;
	int rectY = 180;
	
	Rect r;
	
	int health = 50;
	boolean alive = true;
	
	int crashDMG = 60;

	public Boss(int x, int y) {
		super(x, y, xStruct, yStruct);
		r = new Rect(x - (rectX/2), y -(rectY/2) - 5, rectX, rectY);
		bleft = new Bullet(0,0,15,15,Color.yellow);
		bmid = new Bullet(0,0, 25,25,Color.cyan);
		bright = new Bullet(0,0,15,15,Color.yellow);	
	}
	
	public int getSideSpeed(){
		return sideSpeed;
	}
	public void increaseSide(int var){
		sideSpeed += var;
	}
	
	public int getMidSpeed(){
		return midSpeed;
	}
	public void increaseMid(int var){
		midSpeed += var;
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
	
	public int getHealth(){
		return health;
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
		g.setColor(new Color(150,255,150));
		g.fillOval(x - 70, y -  50, 40, 80);
		g.fillOval(x - 30, y - 100, 60, 120);
		g.fillOval(x + 30, y - 50, 40, 80);
		g.setColor(Color.black);
		g.drawOval(x - 70, y -  50, 40, 80);
		g.drawOval(x - 30, y - 100, 60, 120);
		g.drawOval(x + 30, y - 50, 40, 80);
		
		//bottom
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x - 90, y - 10, 180, 90);
		
		//orbs
		g.setColor(Color.yellow);
		g.fillOval(x + 60, y + 20, 20, 20);
		g.fillOval(x + 30, y + 20, 20, 20);
		g.fillOval(x - 50, y + 20, 20, 20);
		g.fillOval(x - 80, y + 20, 20, 20);
		
		g.setColor(Color.cyan);
		g.fillOval(x - 20 , y + 10, 40, 40);

	}

}
