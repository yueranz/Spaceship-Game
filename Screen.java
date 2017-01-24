//Name: Yueran Zhao
//Asteroids move randomly within the screen.
//When spaceship start shooting or collide with the asteroids, asteroids disappear.
//Score will be added by one if an asteroid is shot
//The timer is counting down from 15 seconds, when time runs out, you lost the game
//One (faster) timer controlling the asteroid movements.
//The original color of the ship is blue, and it will change to yellow when you have 2 lives left and change to red for one life
//and change back to blue when you lost the game.
//You can use the keyboard to control the shooting. 

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Iterator;


public class Screen extends JPanel 
implements ActionListener, MouseListener, MouseMotionListener,KeyListener,ComponentListener{
	private  SpaceShip ship1;
	private Star stars[] = new Star[100];
	private ArrayList<Asteroids> ast = new ArrayList<Asteroids>();
	private int Num_Asteroids = 15;
	private Timer timer,timer1;
	private int Width=800;
	private int Height=800;
	private final int DELAY = 30, DLY=60;
	private JLabel msg,msgtime,msgscore,msglife;
	private int hp=3;
	private long gstart, gleft,htime; 
	private boolean alive=true;
	private int score=0;
	
	public Screen(){
		ship1 = new SpaceShip(); //create a single spaceship;
		int a = 0;
		while (a< Num_Asteroids ){
			int x = (int)( Width * Math.random());
			int y = (int)( Height * Math.random());//make asteroids move randomly
			ast.add(new Asteroids(x,y));
			a ++;	
		}
		// create Asteroids; because it is a array list,so we use add instead of draw;
		setLayout(null);
		
		// create random stars
		 int i = 0;
			while(i<stars.length){
				int x = (int)( Width * Math.random());
				int y = (int)( Height * Math.random());	
				stars[i]=new Star(x,y);
				i ++;
			}
      
		// setting the text for score, lives left and timer;	
		msg = new JLabel("");
		msgtime = new JLabel("");
		msgscore = new JLabel("");
		msglife = new JLabel("");
		msg.setForeground(Color.red);
		msgtime.setForeground(Color.red);
		msgscore.setForeground(Color.red);
		msglife.setForeground(Color.red);
		msgtime.setBounds(10,0,1000,100);
		msglife.setBounds(10,35,1000,100);
		msgscore.setBounds(10,70,1000,100);
		msg.setFont(new Font("Sans Serif",Font.PLAIN,25));
		msgtime.setFont(new Font("Sans Serif",Font.PLAIN,20));
		msglife.setFont(new Font("Sans Serif",Font.PLAIN,20));
		msgscore.setFont(new Font("Sans Serif",Font.PLAIN,20));
		add(msg);
		add(msgtime);
		add(msglife);
		add(msgscore);
		
		
		setBackground (Color.black);
	    setPreferredSize (new Dimension(Width,Height));
	    
	    //setting 2 different timers
	    timer = new Timer(DELAY,this);//faster Timer timer
	    timer1 = new Timer(DLY,this);//slower Timer timer1
	    addMouseListener(this);
	    addMouseMotionListener(this);
	    addComponentListener(this);
	    addKeyListener(this);
	    timer.start(); 
	    timer1.start(); 
	    
	    //setting the keyboard function;
	    setFocusable(true);
	    
	    // getting the current time;
	    gstart = System.currentTimeMillis();     	    
	    
	}
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(Color.black);
        
       //draw the stars,asteroids and spaceship; 
        int i = 0;
		while(i<stars.length){
			
			stars[i].drawStar(g);
			i ++;	
		}
		
		for (Asteroids asteroid1:ast)
			asteroid1.draw(g);
		
		
		ship1.draw(g);
		
		
	}
	
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Space Ships");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		Screen panel = new Screen();
		frame.getContentPane().add(panel);

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void componentResized(ComponentEvent e) {
		
		// each time resize the screen, repaint the stars and let the stars randomly fall in the "new" screen, 
		
		this.Width = getWidth();
		this.Height = getHeight();
		int i = 0;
		while( i< stars.length){
			int x = (int)(Width * Math.random());
			int y = (int)(Height * Math.random());
			i ++;
			stars[i-1] = new Star(x, y);
			repaint();
		}
	}


	@Override
	public void componentMoved(ComponentEvent e) {
		
	}


	@Override
	public void componentShown(ComponentEvent e) {
		
	}


	@Override
	public void componentHidden(ComponentEvent e) {
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		//freeze the screen if the lives left is 0;
		//activate mousedrag event so that you can shoot the asteroids while dragging the mouse.
		if(hp>=1){
		ship1.setShooting(true);
		int x, y;
		x = e.getX();
		y = e.getY();
		ship1.move(x, y);
		repaint();
		}
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// freeze the screen when lives left is 0;
		if(hp>=1){
		int x, y;
		x = e.getX();
		y = e.getY();
		if(alive){
			ship1.move(x, y);
		}
		}
		repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
		// shoot the laser when mouse is clicked 
		if(hp<1){
			ship1.setShooting(false);}
			else{
			ship1.setShooting(true);
			}
		repaint();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// stop shooting when mouse is released 
		ship1.setShooting(false);
		repaint();
	}
  
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
	}


	@Override   
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == timer1){ 
			gleft= 15- (System.currentTimeMillis()-gstart)/1000;
		}
		
	//One faster timer controlling the asteroid movements
		else if(e.getSource()==timer){
		    for (Asteroids asteroid1 : ast){
			    int move=(int)(100*Math.random()+1); // make the move a integer to implement 30% of the time to move.
      			if (move <= 30){
					int x = asteroid1.getX()+asteroid1.getDeltaX();
					int y = asteroid1.getY()+asteroid1.getDeltaY();
					asteroid1.move(x, y);
					
					
	// locate the new asteroid and make sure it is in the screen.
	//firstly check the xpos of the asteroids, if its width exceed the width of screen, reset the velocity to make it come back
			       if ( x <= 0 || x>= Width-asteroid1.getwidth()){
			    	   
			    	   if (x<=0 && asteroid1.getDeltaX()<0){
			    		   asteroid1.setDeltaX(asteroid1.getDeltaX()*-1);  
			    	   }
			    	   if (x>=Width-asteroid1.getwidth()&& asteroid1.getDeltaX()>0){
			    		   asteroid1.setDeltaX(asteroid1.getDeltaX()*-1);  
			    	   }    	      
			       }
			       
   // check the ypos of the asteroids, if its length exceed the height of screen,reset the velocity     
			       else if (y<=0|| y >= Height-asteroid1.getheight() ){
			    	   
			    	   if(y<=0 && asteroid1.getDeltaY()<0){
			    		  asteroid1.setDeltaY(asteroid1.getDeltaY()*-1);}
			    		  
			    	   if(y>= Height-asteroid1.getheight()&&asteroid1.getDeltaY()>0){
			    		   asteroid1.setDeltaY(asteroid1.getDeltaY()*-1);   
			    	   }
			       }    
			       }
			}  
		}
		
	//when the laser shoot the asteroids, remove the asteroid shot and add current score by 1.
	// then break out of the mission to start a new round.
			if (ship1.returnShooting()){
				for (Iterator<Asteroids> iterator = ast.iterator(); iterator.hasNext(); ){
					Asteroids it = iterator.next();
					int a = ship1.getyPos()+30;
					for(int i =ship1.getxPos(); i < Width; i++){
						if ( it.getPolygon().contains(i,a)){
							iterator.remove();
							msg.setText("HIT");
							htime = System.currentTimeMillis() + 1*1000;
							score++;
							msg.setBounds(it.getX(), it.getY(), 2000, 100);
						    break;	
						}	 
					}
					
				}
			}
			
			
			//make sure the "HIT" only appear on the screen for one second. 	
			if (System.currentTimeMillis()>htime){
				msg.setText(" ");	
			}		
			
		//When game over, text shows up, set the time left to 0,Spaceship cannot shoot and the asteroids are frozen.
			if(hp<1){
				msg.setText("Game over.Loser!");
				msg.setFont(new Font("Serif",Font.PLAIN,45));
				msg.setBounds(200,200,1500,200);
				msglife.setText("Time Left: 0 ");
				alive=false;
				ship1.setShooting(false);
				for (Asteroids asteroid1:ast){
					asteroid1.setDeltaX(0);
					asteroid1.setDeltaY(0);
				}	
			}
			
	//change the color of the spaceship when there is only one life left. change back to the original color when game is over.
			if(hp == 1){
				ship1.setColor(Color.red);
				repaint();
			}
			
			if(hp == 0){
				ship1.setColor(Color.blue);
				repaint();
			}
			
		//15 seconds count down and show the current time left on the screen if the game is still on.
		    gleft= 15- (System.currentTimeMillis()-gstart)/1000;  
			
			if((gleft>0)&&(hp>0)){
			msgtime.setText("Time Left: "+ gleft);
			}
			
			else{
				hp=0;
				msgtime.setText("Time Left: 0 ");
			}
			
	//when the spaceship and asteroids collide, substract lives by 1, remove the asteroid,change the color of the ship and show the text on the screen
	//after that, break out of the mission to start another round.
			for (Iterator<Asteroids> iterator = ast.iterator(); iterator.hasNext(); ){
				Asteroids it = iterator.next();
				for(int i = ship1.getxPos(); i < ship1.getxPos()+ship1.getWidth(); i++){
					boolean collision=false;
					for(int a = ship1.getyPos(); a < ship1.getyPos()+ship1.getHeight(); a++){
						if ( it.getPolygon().contains(i,a)){
							collision=true;
							iterator.remove();
							hp--;
							ship1.setColor(Color.yellow);
							repaint();
							msg.setText("Ouch!");
							htime = System.currentTimeMillis() + 1*1000;
							msg.setBounds(it.getX(), it.getY(), 2000, 100);
							break;
							
				        }
					}
					if(collision) break;
				}	
			}
			
			msgscore.setText("Current Score: "+ score);
			msglife.setText("Lives Left: "+hp);
			repaint(); 
	}



	@Override
	public void keyTyped(KeyEvent e) {
		
	}


//when using the space on keyboard, the spaceship can also shoot the laser;
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE){
			ship1.setShooting(true);
			repaint();
		}
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		ship1.setShooting(false);
		repaint();
		
		
		
	}
}
