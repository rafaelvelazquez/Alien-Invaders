import java.applet.Applet;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game extends Applet implements Runnable, KeyListener, MouseListener {
	
	//Controls
	boolean ltPressed = false;
	boolean rtPressed = false;
	boolean spacePressed = false;
	
	//Main thread
	Thread t;
	
	//Different Screens to be displayed
	Menu menu = new Menu();
	Help help = new Help();
	GameOver gameover = new GameOver();
	Victory victory = new Victory();
	
	//Player
	HUD hud = new HUD();
	Player p = new Player(500,700);
	
	//Enemies
	Robot[] robotArmy;
	UFO[] ufoArmy;
	Boss boss;
	
	//Sound files
	AudioPlayer blaster = new AudioPlayer("blaster.wav");
	AudioPlayer menuSel = new AudioPlayer("menu_select.wav");
	AudioPlayer menuCon = new AudioPlayer("menu_confirm.wav");
	AudioPlayer robotSound = new AudioPlayer("robot sound.wav");
	//AudioPlayer bgm1 = new AudioPlayer("Flying_Lab.wav");
	//AudioPlayer bgm2 = new AudioPlayer("oozla.wav");

	
	//Game States
	public enum STATE{
		MENU,
		HELP,
		GAME,
		GAMEOVER,
		VICTORY
	};
	
	//Starting game state
	public STATE gameState = STATE.MENU;

	//Double Buffer
	Image offscreen;
	Graphics offscreen_g;
	
	//Game background
	Image stage1;
	
	//delay
	int winDelay = 0;
	
	public void init(){
		
		//Create Window
		this.setSize(1000, 800);
	    Frame title = (Frame)this.getParent().getParent();
	    title.setTitle("Alien Invasion");
	    offscreen   = createImage(1000, 800);
		offscreen_g = offscreen.getGraphics();
		stage1 = Toolkit.getDefaultToolkit().getImage("nightmare_night.png");
		addKeyListener(this);
		addMouseListener(this);
		requestFocus();
		t = new Thread(this);
		t.start();
		
		robotArmy = new Robot[]{
				new Robot(200,-200), new Robot(700,-600),
				new Robot(500,-800), new Robot(200,-1000),
				new Robot(900,-1300), new Robot(600,-1600),
				new Robot(800,-1900), new Robot(300,-2200),
				new Robot(400,-2400), new Robot(500,-2800)
		};
		
		ufoArmy = new UFO[]{
				new UFO(700,-3000, 1),new UFO(900,-3200, 2),
				new UFO(100,-3500, 3),new UFO(600,-3800,-2),
				new UFO(800,-4000,-1),new UFO(200,-4200, 2),
				new UFO(400,-4500, 3),new UFO(300,-4700, 1),
				new UFO(400,-5000,-2),new UFO(600,-5100, 2)
		};
		
		boss = new Boss(500,-6000);
	}
	
	public void run(){
		
		while(true){
		//While the game is running...
			
			if(gameState == STATE.MENU){
			//Perform menu commands
				//bgm1.loop();
			}
			
			if((gameState == STATE.GAMEOVER) || (gameState == STATE.VICTORY)){
			//Reset game
				//bgm2.stop();
				hud.setHealth(hud.getMaxHealth());
				p.setX(500);
				robotArmy = new Robot[]{
						new Robot(200,-200), new Robot(700,-600),
						new Robot(500,-800), new Robot(200,-1000),
						new Robot(900,-1300), new Robot(600,-1600),
						new Robot(800,-1900), new Robot(300,-2200),
						new Robot(400,-2400), new Robot(500,-2800)
				};
				
				ufoArmy = new UFO[]{
						new UFO(700,-3000, 1),new UFO(900,-3200, 2),
						new UFO(100,-3500, 3),new UFO(600,-3800,-2),
						new UFO(800,-4000,-1),new UFO(200,-4200, 2),
						new UFO(400,-4500, 3),new UFO(300,-4700, 1),
						new UFO(400,-5000,-2),new UFO(600,-5100, 2)
				};
				
				boss = new Boss(500,-6000);
				
				winDelay = 0;
			}
			
			if(gameState == STATE.GAME){
			//Perform the in game actions
				//bgm2.loop();
				
				//PLAYER CONTROLS
				if(ltPressed){
					p.moveLeftBy(5);
					if (p.getX() - 30 < 0)
						p.setX(30);
				}
				
				if(rtPressed){
					p.moveRighttBy(5);
					if (p.getX() + 30 > getWidth())
						p.setX(getWidth() - 30);
				}
				
				if(spacePressed){
					if(p.b.onScreen == false){
						p.b.hasCollided = false;
						p.b.setX(p.getX() - 5);
						p.b.setY(p.getY() - 10);
						p.b.onScreen = true;
						blaster.play();
					}

				}
				
				//Bullet Mechanics
				if(p.b.onScreen){
					p.b.moveUpBy(-10);
					
					if(p.b.getY() < 10 || p.b.hasCollided){
						p.b.onScreen = false;
						p.b.setX(p.getX() - 5);
						p.b.setY(p.getY() - 10);
						blaster.stop();
					}
				}
			

				/*
				// Cheat Code, Health Regen
				if (hud.getHealth() < hud.getMaxHealth() && hud.getHealth() >= 1){
					hud.regen(.05);
					System.out.println(hud.getHealth());
				}
				*/
			
				//LEVEL 1
				for(int i = 0; i < robotArmy.length; i++){
					if (robotArmy[i].alive){
						robotArmy[i].moveDownBy(2);
						
						if(robotArmy[i].getY()  > 900)
							gameState = STATE.GAMEOVER;
						
						if (robotArmy[i].r.distanceTo(p.b) < 100){
							if (robotArmy[i].r.overlaps(p.b)){
							robotArmy[i].hit();
							p.b.hasCollided = true;
							}
						}
						
						if((robotArmy[i].r.distanceTo(p.r) < 100) && (robotArmy[i].getY() >= 630)){
							if(robotArmy[i].r.overlaps(p.r)){
								robotArmy[i].alive = false;
								hud.hit(robotArmy[i].getCrashDMG());
								
							}
						}
					}
					
				}
				
				//LEVEL 2
				for(int i = 0; i < ufoArmy.length; i++){
					if (ufoArmy[i].alive){
						ufoArmy[i].moveDownBy(2);
						ufoArmy[i].moveSidewaysBy(ufoArmy[i].getVelX());
						
						if(ufoArmy[i].getY() > 900)
							gameState = STATE.GAMEOVER;
						
						//if to the left of the screen
						if (ufoArmy[i].getX() - 25 <= 0)
							ufoArmy[i].setVelX(-(ufoArmy[i].getVelX()));
						
						//if to the right of the screen
						if (ufoArmy[i].getX() + 25 >= getWidth())
							ufoArmy[i].setVelX(-(ufoArmy[i].getVelX()));
						
						//if near the player's bullet
						if (ufoArmy[i].r.distanceTo(p.b) < 100){
							if (ufoArmy[i].r.overlaps(p.b)){
							ufoArmy[i].hit();
							p.b.hasCollided = true;
							}
						}
						
						if((ufoArmy[i].r.distanceTo(p.r) < 100) && (ufoArmy[i].getY() >= 630)){
							if(ufoArmy[i].r.overlaps(p.r)){
								ufoArmy[i].alive = false;
								hud.hit(ufoArmy[i].getCrashDMG());
								
							}
						}
					}
					
				}
				
				//LEVEL 3
				if (boss.alive){
					if(boss.getY() < 200)
						boss.moveDownBy(2);
				
					if(boss.getY() == -2)
						robotSound.play();
					
					//Boss collision
					if (boss.r.distanceTo(p.b) < 400){
						if (boss.r.overlaps(p.b)){
							boss.hit();
							p.b.hasCollided = true;
						}
					}
					
					//Boss Movement
					
					if (boss.getHealth() < 45)
						boss.moveSidewaysBy(boss.getVelX());
					
					if(boss.getHealth() == 40){
						if(boss.upgrade1 == false){
							boss.setVelX(boss.getVelX() *2);
							boss.upgrade1 = true;
						}
					}
					
					if(boss.getHealth() == 35){
						if(boss.upgrade2 == false){
						boss.setVelX(boss.getVelX() *2);
						boss.upgrade2 = true;
						}
					}
					
					if(boss.getHealth() == 30){
						if (boss.sideSpeed == 4)
							boss.increaseSide(2);
					}
					
					if(boss.getHealth() == 25){
						if (boss.midSpeed == 6)
							boss.increaseMid(2);
					}
					
					if(boss.getHealth() == 20){
						if (boss.sideSpeed == 6)
							boss.increaseSide(2);
					}
					
					if(boss.getHealth() == 15){
						if (boss.midSpeed == 8)
							boss.increaseMid(2);
					}
					
					if(boss.getHealth() == 5){
						if(boss.upgrade3 == false){
						boss.setVelX(boss.getVelX() *2);
						boss.upgrade3 = true;
						}
						
					}
					
					//if to the left of the screen
					if (boss.getX() - 75 <= 0)
						boss.setVelX(-(boss.getVelX()));
					
					//if to the right of the screen
					if (boss.getX() + 75 >= getWidth())
						boss.setVelX(-(boss.getVelX()));
					
					//Boss attack
					if(boss.getY() == 200){
						
						if(boss.bleft.onScreen == false){
							boss.bleft.hasCollided = false;
							boss.bleft.setX(boss.getX() - 50);
							boss.bleft.setY(boss.getY() + 100);
							boss.bleft.onScreen = true;
						}
						
						if(boss.bmid.onScreen == false){
							boss.bmid.hasCollided = false;
							boss.bmid.setX(boss.getX());
							boss.bmid.setY(boss.getY() + 100);
							boss.bmid.onScreen = true;
						}
						
						if(boss.bright.onScreen == false){
							boss.bright.hasCollided = false;
							boss.bright.setX(boss.getX() + 50);
							boss.bright.setY(boss.getY() + 100);
							boss.bright.onScreen = true;
						}
						
						if(boss.bleft.onScreen){
							boss.bleft.moveUpBy(boss.getSideSpeed());
							
							if((boss.bleft.distanceTo(p.r) < 100) && (boss.bleft.getY() >= 680)){
								if(boss.bleft.overlaps(p.r)){
									hud.hit(50);
									boss.bleft.hasCollided = true;
								}
							}
								
							if(boss.bleft.getY() > 790 || boss.bleft.hasCollided){
								boss.bleft.onScreen = false;
								boss.bleft.setX(boss.getX() - 50);
								boss.bleft.setY(boss.getY() + 100);
							}
						}
						
						if(boss.bmid.onScreen){
							boss.bmid.moveUpBy(boss.getMidSpeed());
							
							if((boss.bmid.distanceTo(p.r) < 100) && (boss.bmid.getY() >= 680)){
								if(boss.bmid.overlaps(p.r)){
									hud.hit(90);
									boss.bmid.hasCollided = true;
								}
							}
								
							if(boss.bmid.getY() > 790 || boss.bmid.hasCollided){
								boss.bmid.onScreen = false;
								boss.bmid.setX(boss.getX());
								boss.bmid.setY(boss.getY() + 100);
							}
						}
						
						if(boss.bright.onScreen){
							boss.bright.moveUpBy(boss.getSideSpeed());
							
							if((boss.bright.distanceTo(p.r) < 100) && (boss.bright.getY() >= 680)){
								if(boss.bright.overlaps(p.r)){
									hud.hit(50);
									boss.bright.hasCollided = true;
								}
							}
								
							if(boss.bright.getY() > 790 || boss.bright.hasCollided){
								boss.bright.onScreen = false;
								boss.bright.setX(boss.getX() + 50);
								boss.bright.setY(boss.getY() + 100);
							}
						}
					}
				}
				
				//Check if player has lost
				if (hud.getHealth() <= 0)
					gameState = STATE.GAMEOVER;
				
				//Check if the player won
				if(boss.alive == false)
					winDelay++;
				
				if(winDelay > 100)
					gameState = STATE.VICTORY;
			}


			
			repaint();
			
			try{
				Thread.sleep(16);				
			}
			catch(Exception x){};
			
		}
	}
	
	public void update(Graphics g){
		
		//Double Buffer
	    offscreen_g.clearRect(0, 0, 1000, 800);
	    paint(offscreen_g);
	    g.drawImage(offscreen, 0, 0, null);

	}
	
	public void paint(Graphics g){
		

		//Creates window to draw on
		if (gameState == STATE.MENU){
			menu.draw(g);
		}
		
		else if(gameState == STATE.HELP){
			help.draw(g);
		}
		
		else if(gameState == STATE.GAMEOVER){
			gameover.draw(g);
		}
		else if(gameState == STATE.VICTORY){
			victory.draw(g);
		}
		
		else if(gameState == STATE.GAME){ 
			g.drawImage(stage1, 0, 0, getWidth(), getHeight(), null);
			p.draw(g);
			if (p.b.onScreen)
				p.b.draw(g);
			//Level 1
			for(int i = 0; i < robotArmy.length; i++){
				if (robotArmy[i].alive && robotArmy[i].getY() >= 0)
					robotArmy[i].draw(g);
			}
			
			//Level 2
			for(int i = 0; i < ufoArmy.length; i++){
				if (ufoArmy[i].alive && ufoArmy[i].getY() >= 0)
					ufoArmy[i].draw(g);
			}
			//Level 3
			if(boss.alive){
				boss.draw(g);
			
				if (boss.bleft.onScreen)
					boss.bleft.draw(g);
				
				if (boss.bmid.onScreen)
					boss.bmid.draw(g);
				
				if (boss.bright.onScreen)
					boss.bright.draw(g);
			}
			//Draw heads up display last
			hud.draw(g);

		}
		else{
			//continue normally
		}
	}

	public void keyPressed(KeyEvent e){
		//Defines when a button is pressed/held
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_LEFT) 	ltPressed = true;
		if(code == KeyEvent.VK_RIGHT) 	rtPressed = true;
		if(code == KeyEvent.VK_SPACE) 	spacePressed = true;

	}
	
	public void keyReleased(KeyEvent e){
		//Defines when a button is released/let go
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_LEFT) 	ltPressed = false;
		if(code == KeyEvent.VK_RIGHT) 	rtPressed = false;
		if(code == KeyEvent.VK_SPACE) 	spacePressed = false;
	}
	
	public boolean mouseOver(int mx, int my, Rect r){
		if(mx > r.x && mx < r.x + r.w){
			if(my > r.y && my < r.y + r.h)
				return true;
			else
				return false;
		}else
			return false;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//System.out.println(Integer.toString(mx));
		//System.out.println(Integer.toString(my));
		
		if (gameState == STATE.MENU){
			if (mouseOver(mx, my, menu.getStart())){
				gameState = STATE.GAME;
				menuCon.play();
				//bgm1.stop();
			}
			
			else if(mouseOver(mx, my, menu.getHelp())){
				gameState = STATE.HELP;
				menuSel.play();
			}
		
			else if (mouseOver(mx, my, menu.getQuit())){
				System.exit(0);
			}
			
			else{
				//Just continue normally...
			}
		}
		
		if (gameState == STATE.HELP){
			if (mouseOver(mx, my, help.getBack())){
				gameState = STATE.MENU;
				menuSel.play();
			}
		}
		
		if(gameState == STATE.VICTORY){
			if (mouseOver(mx, my, victory.getBack())){
				gameState = STATE.MENU;
				menuSel.play();
			}
			
			if (mouseOver(mx, my, victory.getRetry())){
				gameState = STATE.GAME;
				menuCon.play();
			}
		} 
		if(gameState == STATE.GAMEOVER){
			if (mouseOver(mx, my, gameover.getBack())){
				gameState = STATE.MENU;
				menuSel.play();
			}
			
			if (mouseOver(mx, my, gameover.getRetry())){
				gameState = STATE.GAME;
				menuCon.play();
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}	
