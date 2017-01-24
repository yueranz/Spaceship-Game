

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.Random;


public class Asteroids {
	private int xPos, yPos;
	private int width=(int)( 8 * Math.random()+7);
	private int height=(int)( 8 * Math.random()+7);//
	private int xPoints[];
	private int yPoints[];
	int nPoints = 6;
	private Color clr = Color.gray;
	private int deltaX = (int)( 20 * Math.random()-10);
	private int deltaY = (int)( 20 * Math.random()-10);
	
	public Asteroids(int x, int y){
		xPos = x;
		yPos = y;
		
        xPoints= new int[]{xPos-width,xPos,xPos+width,xPos+width,xPos,xPos-width};
		yPoints = new int[]{yPos-(2*height)/3,yPos-height,yPos-(2*height)/3,yPos+(2*height)/3,y+height,yPos+(2*height)/3};
		
	}
	
	public void draw(Graphics g)
	{
		g.setColor(clr);
		g.fillPolygon(xPoints, yPoints, nPoints);
	}
	
	public Polygon getPolygon(){
	    Polygon astr = new Polygon(xPoints,yPoints,xPoints.length);
	    return astr;
	    
		
	}
	
	public void move(int x, int y){
		xPos = x;
		yPos = y;
	
		xPoints= new int[]{xPos-width,xPos,xPos+width,xPos+width,xPos,xPos-width};
		yPoints = new int[]{yPos-(2*height)/3,yPos-height,yPos-(2*height)/3,yPos+(2*height)/3,y+height,yPos+(2*height)/3};
			
	}
	
	public int getX(){
		return xPos;
	}
	
	public int getY(){
		return yPos;
	}
	
	public int getheight(){
		return height;
	}
	
	public int getwidth(){
		return width;
	}

	public int getDeltaX(){
		return deltaX;
	}
	
	public int getDeltaY(){
		return deltaY;
	}
	
	public void setDeltaX(int x){
		this.deltaX = x;
	}
	
	public void setDeltaY(int y){
		this.deltaY = y;
	}
}
