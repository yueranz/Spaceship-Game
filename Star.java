
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Star {
	private int xPos;
	private int yPos;
	Color starColor;
	
	public Star(int x, int y){
		xPos = x;
		yPos = y;
		starColor = Color.yellow;
	}
	
	public void drawStar(Graphics g){
		int[] xArr1 = {xPos, xPos+5, xPos+10};
		int[] yArr1 = {yPos + 10, yPos, yPos+10};
		int[] xArr2 = {xPos, xPos+10, xPos+5};
		int[] yArr2 = {yPos +5, yPos+5, yPos+10};
		
		g.setColor(starColor);
		g.fillPolygon(xArr1, yArr1, 3);
		g.fillPolygon(xArr2, yArr2, 3);	
	}
}

