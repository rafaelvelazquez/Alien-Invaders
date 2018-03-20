import java.awt.*;

public class HUD {
	//Heads Up Display
	//This HUD is in response to the Player
	
	private int maxHealth = 200;
	private double health = 200;
	
	public void draw(Graphics g){
		
		g.setColor(Color.red);
		g.fillRect(10, 10, maxHealth, 20);
		g.setColor(Color.green);
		g.fillRect(10, 10, (int)health, 20);
		g.setColor(Color.black);
		g.drawRect(10, 10, 200, 20);
	}
	
	public double getHealth(){
		return health;
	}
	
	public void setHealth(int hp){
		health = hp;
	}
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public void hit(int var){
		health = health - var;
	}

	public void regen(double x){
		health += x;
		
		if(health >= maxHealth)
			health = maxHealth;
		
	}
}
