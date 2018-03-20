import java.awt.Color;
import java.awt.Graphics;

public class Robot extends PolygonModel {

	static int[][] xStruct =
	{
		{-15, 15,  15, -15}, //upper body
		{-15, 15, 15, -15}, //lower body
		{-25, -15, -15, -25}, //left arm
		{15, 25, 25, 15}, // right arm
		{-15, -5, -5, -15}, //left leg
		{5, 15, 15, 5}, //right leg
		{-10, 10, 10, -10}, //head
		{0,0} //antenna
	};
	
	static int [][] yStruct = 
	{
		{-20, -20,  0, 0}, //upper body
		{0, 0, 20, 20}, //lower body
		{-10, -10, 10, 10}, //left arm
		{-10, -10, 10, 10}, // right arm
		{20, 20, 30, 30}, //left leg
		{20, 20, 30, 30}, //right leg
		{-30, -30, -20, -20}, //head
		{-30,-35} //antenna
	};
	
	static int rectX = 50;
	static int rectY = 70;
	
	Rect r;
	
	int health = 3;
	boolean alive = true;
	
	int crashDMG = 70;
	
	public Robot(int x, int y) {
		super(x, y, xStruct, yStruct);
		r = new Rect(x - (rectX/2), y -(rectY/2) - 5, rectX, rectY);

	}
	
	public void hit(){
		health--;
		if(health <= 0)
			alive = false;
	}
	
	public int getCrashDMG(){
		return crashDMG;
	}
	
	public void draw(Graphics g){
		//Draws bounding box for object
		r.setX(x - (rectX/2));
		r.setY(y -(rectY/2) - 5);
		//r.draw(g);
		
		//Draws the object on the window
		
		for(int poly = 0; poly < xStruct.length; poly++){
			
			int[] xpoints = new int[xStruct[poly].length];
			int[] ypoints = new int[yStruct[poly].length];
			
			for(int vert = 0; vert < xStruct[poly].length ; vert++){
				
				xpoints[vert] = xStruct[poly][vert] + x;
				ypoints[vert] = yStruct[poly][vert] + y;
			}
			g.setColor(Color.gray);
			g.fillPolygon(xpoints, ypoints, xStruct[poly].length);
			g.setColor(Color.black);
			g.drawPolygon(xpoints, ypoints, xStruct[poly].length);
		}
		
		g.setColor(Color.red);
		g.fillOval(x - 2, y - 40, 5, 5);
		g.setColor(Color.black);

	}
	
	public void moveDownBy(int var){
		y += var;
	}
	
}
