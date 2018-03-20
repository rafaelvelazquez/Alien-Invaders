import java.awt.*;

public class PolygonModel {
	
	int x;
	int y;

	
	int [][] xStruct = {     
			
	};
	
	int [][] yStruct = {    
			
	};
	
	int rectX;
	int rectY;
	
	public PolygonModel(int x, int y, int [][]xStruct, int [][]yStruct){
		this.x = x;
		this.y = y;
		this.xStruct = xStruct;
		this.yStruct = yStruct;
		
	}
	
	public int getX(){
		return x;
	}
	public void setX(int var){
		x = var;
	}
	
	public int getY(){
		return y;
	}
	public void setY(int var){
		y = var;
	}
	
	public void moveBy(int dx, int dy){ 
		//Old move function
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics g){
		//Draws the object on the window
		
		for(int poly = 0; poly < xStruct.length; poly++){
			
			int[] xpoints = new int[xStruct[poly].length];
			int[] ypoints = new int[yStruct[poly].length];
			
			for(int vert = 0; vert < xStruct[poly].length ; vert++){
				
				xpoints[vert] = xStruct[poly][vert] + x;
				ypoints[vert] = yStruct[poly][vert] + y;
			}
			g.drawPolygon(xpoints, ypoints, xStruct[poly].length);
		}

	}
	
}
