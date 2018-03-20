import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GameOver {
	
	Rect retry = new Rect(150, 620, 125, 50);
	Rect back = new Rect(750, 620, 100, 50);

	public Rect getRetry(){
		return retry;
	}
	
	public Rect getBack(){
		return back;
	}
	
	public void draw(Graphics g){
		Font font1 = new Font("arial", 1, 40);
		
		Image image = Toolkit.getDefaultToolkit().getImage("gameover.png");
		g.drawImage(image, 0, 0, 1000, 800, null);
		
		g.setColor(Color.white);
		g.setFont(font1);
		//retry.draw(g);
		g.drawString("Retry?", 150, 660);
		
		//back.draw(g);
		g.drawString("Back", 750, 660);
		
	}

}
