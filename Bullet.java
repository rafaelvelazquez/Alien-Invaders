import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends Rect {
	
	int w ;
	int h ;
	
	boolean onScreen = false;
	boolean hasCollided = false;
	Color c;
	
	public Bullet(int x, int y, int w, int h, Color c) {
		super(x,y,w,h);
		this.h = h;
		this.w = w;
		this.c = c;
	}
	
	public void setX(int var){
		x = var;
	}
	
	public int getX(){
		return x;
	}
	
	public void setY(int var){
		y = var;
	}
	
	public int getY(){
		return y;
	}
	public void moveUpBy(int dy){
		y += dy;
	}
	
	public void draw(Graphics g){
		g.setColor(c);
	    g.fillRect(x, y, w, h);
	    g.setColor(Color.black);
	    g.drawRect(x, y, w, h);
	}


}
