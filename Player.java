import java.awt.Color;
import java.awt.Graphics;

public class Player extends PolygonModel{
	
	static int[][] xStruct =
	{
		{-20, 20, 20, -20}, //top
		{-10, 10,  10, -10}, //cockpit
		{-30, -20, -20, -30}, //left tire
		{20, 30, 30, 20}, // right tire
		{-30, -20, 20, 30, 20, -20}, //back
		{-5, 5, 5, -5} //turret
	};
	
	static int [][] yStruct = 
	{
		{20, 20, -20, -20}, //top
		{10, 10, -10, -10}, //cockpit
		{0, -20, 20, 30}, //left tire
		{-20, 0, 30, 20}, // right tire
		{30, 40, 40, 30, 20, 20}, //back
		{-30 , -30, -20, -20} //turret

	};
	
	Bullet b;
	
	static int rectX = 60;
	static int rectY = 75;
	Rect r;
	
	static int[][] colors = {
			{0, 100, 50},
			{0,  50, 25},
			{150, 150, 0},
			{150, 150, 0},
			{0,  100,50},
			{0,  50, 25},
	};
	
	public Player(int x, int y) {
		super(x, y, xStruct, yStruct);
		r = new Rect(x - (rectX/2), y -(rectY/2) + 5, rectX, rectY);
		b = new Bullet(0,0,10,10,Color.red);
	}
	
	public void draw(Graphics g){
		//Draws bounding box for object
		r.setX(x - (rectX/2));
		r.setY(y -(rectY/2) + 5);
		//r.draw(g);
		
		//Draws the object on the window
		
		for(int poly = 0; poly < xStruct.length; poly++){
			
			int[] xpoints = new int[xStruct[poly].length];
			int[] ypoints = new int[yStruct[poly].length];
			
			for(int vert = 0; vert < xStruct[poly].length ; vert++){
				
				xpoints[vert] = xStruct[poly][vert] + x;
				ypoints[vert] = yStruct[poly][vert] + y;
			}
			g.setColor(new Color(colors[poly][0],colors[poly][1],colors[poly][2]));
			g.fillPolygon(xpoints, ypoints, xStruct[poly].length);
			g.setColor(Color.black);
			g.drawPolygon(xpoints, ypoints, xStruct[poly].length);
		}

	}
	
	
	public void moveLeftBy(int dx){
		x -= dx;
	}
	
	public void moveRighttBy(int dx){
		x += dx;
	}

	
}
