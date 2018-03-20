import java.awt.*;

public class Help {
	Rect back = new Rect(700, 600, 200, 100);
	
	public Rect getBack(){
		return back;
	}
	
	public void draw(Graphics g){
		Font font0 = new Font("arial", 1, 60);
		Font font1 = new Font("arial", 1, 40);
		Font font2 = new Font("arial", 1, 30);
		Font font3 = new Font("arial", 1, 20);
		
		g.setColor(Color.black);
		g.setFont(font2);
		g.drawString("Objective", 50, 150);
		g.drawString("How-To-Play", 50, 320);
		g.drawString("Win/Lose Conditions", 50, 530);
		
		g.setFont(font3);
		g.drawString("Earth has been invaded by aliens and it's up to you to stop them.", 50, 180);
		g.drawString("Use the left and right arrow keys to move the player.", 50, 360);
		g.drawString("Use the spacebar to make the player shoot.", 50, 390);
		g.drawString("Destroy all the alien forces and you win!", 50, 560);
		g.drawString("But if you run out of health or let the enemies slip past you...", 50, 600);
		g.drawString("then its GAME OVER!", 50, 640);

		g.setFont(font0);
		g.drawString("HELP", 400, 60);
		
		g.setFont(font1);
		back.draw(g);
		g.drawString("Back", 750, 660);
		
		Image image = Toolkit.getDefaultToolkit().getImage("cartoon_tank.png");
		g.drawImage(image, 650, 200, 300, 300, null);
	}
}
