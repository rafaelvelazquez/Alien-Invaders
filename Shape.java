import java.awt.*;

public abstract class Shape
{
   int x;
   int y;


   public Shape(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   public void moveBy(int dx, int dy)
   {
      x += dx;
      y += dy;
   }


   public abstract void draw(Graphics win);
   public abstract void fill(Graphics win);
   public abstract boolean contains(int mx, int my);

}