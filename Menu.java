import java.awt.*;

public class Menu {
	
	Rect start = new Rect(50, 200, 200, 100);
	Rect help = new Rect(50, 400, 200, 100);
	Rect quit = new Rect(50,600, 200, 100);
	
	public Rect getStart(){
		return start;
	}
	
	public Rect getHelp(){
		return help;
	}
	
	public Rect getQuit(){
		return quit;
	}
	
	public void draw(Graphics g){
		Font font1 = new Font("arial", 1, 80);
		Font font2 = new Font("arial", 1, 40);
		Font font3 = new Font("arial", 1, 20);
		
		g.setColor(Color.black);
		g.setFont(font1);
		g.drawString("ALIEN INVASION", 200, 100);
		
		g.setFont(font2);
		start.draw(g);
		g.drawString("Start", 100, 275);
		
		help.draw(g);
		g.drawString("Help", 100, 475);
		
		quit.draw(g);
		g.drawString("Quit", 100, 675);
		
		g.setFont(font3);
		g.drawString("All music and images belong to their respectful owners.", 380, 730);
		g.drawString("No copyright infringement intended." , 380, 760);

		
		Image image = Toolkit.getDefaultToolkit().getImage("alien-head.png");
		g.drawImage(image, 400, 180, 500, 500, null);
	}

}
