

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class SpaceShip {
	private int xPos;
	private int yPos;
	private boolean shooting;
	private final int WIDTH=100;
	private final int HEIGHT=100;
	private Color clr=Color.blue;
	
	public SpaceShip() {
		Random r = new Random();
		xPos = r.nextInt(501);
		yPos = r.nextInt(501);
		clr=Color.blue;
		shooting = false;
	}
	
	
	public void setColor(Color color){
		clr=color;
	}
	
	public void setShooting(boolean s){
		shooting = s;
	}
	public boolean returnShooting(){
		return shooting;
	}
			
	public void draw(Graphics g){
		g.setColor(clr);
		g.fillOval(xPos-20, yPos+30,WIDTH,30);
		g.fillOval(xPos, yPos, 60, 60);
		g.drawLine(xPos+10, yPos+50, xPos-10, yPos+80);
		g.drawLine(xPos+50, yPos+50, xPos+70, yPos+80);
		g.setColor(Color.yellow);
		g.drawArc(xPos, yPos+8,59,30,180,180);
		g.setColor(Color.black);
		g.fillRect(xPos+60, yPos+40, 9, 9);
		g.fillRect(xPos+40, yPos+45, 9, 9);
		g.fillRect(xPos+17, yPos+45, 9, 9);
		g.fillRect(xPos-5, yPos+40, 9, 9);
		g.setColor(Color.green);
		if (shooting==true){//= means giving value,== means equal
			g.drawLine(xPos+60, yPos+30, 60000000, yPos+30);
		}
		
		
	}
	public String toString(){
		return "xPos: " + xPos + "\nyPos: " + yPos + 
				"\nShooting?: " + shooting;
	}

    public void move(int x, int y){
	   this.xPos = x;
	   this.yPos = y;
   }
    
    public int getxPos(){
    	return xPos;
    }
    
    public int getyPos(){
    	return yPos;
    }
    public int getWidth(){
    	return WIDTH;
    }
    public int getHeight(){
    	return HEIGHT;
    }

    
    
}