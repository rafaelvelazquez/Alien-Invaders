import java.awt.*;

public class Rect extends Shape
{
   int w;
   int h;


   public Rect(int x, int y, int w, int h)
   {
      super(x, y);

      this.w = w;
      this.h = h;
   }

   public void draw(Graphics win)
   {
      win.drawRect(x, y, w, h);
   }


   public void fill(Graphics win)
   {
      win.fillRect(x, y, w, h);
   }


   public boolean contains(int mx, int my)
   {
      return (mx >= x) && (mx <= x+w) && (my >= y) && (my <= y+h);
   }

   public boolean overlaps(Rect r)
   {
      return (r.x + r.w > x) && (r.y + r.h > y) && (x + w > r.x) && (y + h > r.y);
   }


   public void resizeBy(int dw, int dh)
   {
      w += dw;
      h += dh;
   }
   
   public void setX(int dx){
	   x = dx;
   }
   
   public void setY(int dy){
	   y = dy;
   }
   
   public void moveTo(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   public void setSize(int w, int h)
   {
      this.w = w;
      this.h = h;
   }
   
   public int distanceTo(Rect r)
   {
		// using Manhattan distance, calculate the distance between this and another object
		
		return Math.abs(x- r.x) + Math.abs(y- r.y);
		
   }


}
