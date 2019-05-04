import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Entity extends JPanel{

	int x, y;
	int vx, vy;
	int accx, accy;
	double m, rad;
	Image img;
	double bigG = 6.671e-11;
	
	public Entity(int xpos, int ypos, int vx, int vy, double d, double radius, String string) throws IOException {
		x=xpos;
		y=ypos;
		this.vx = vx;
		this.vy = vy;
		m = d;
		rad = radius;
		img = this.getToolkit().getImage(string).getScaledInstance((int)radius, (int)radius, Image.SCALE_SMOOTH);
		
	}
	
	
	//edited
	//calculates the x and y components of the net force
	public boolean calcForces(ArrayList<Entity> entities){
		accx = 0;
		accy = 0;
		for(Entity e : entities) {
			
			if(e.equals(this)) {
				continue;
			}
			
			if(x>e.x) 
				accx-=(bigG* e.m)/((x-e.x)*(x-e.x));
			else
				accx+=(bigG* e.m)/((x-e.x)*(x-e.x));
			
			if(y>e.y)
				accy-=(bigG* e.m)/((y-e.y)*(y-e.y));
			else
				accy+=(bigG* e.m)/((y-e.y)*(y-e.y));
			
		}
		
		
		return true;
	}
	
	//updates the positions and speeds of the planet
	public boolean update(ArrayList<Entity> entities) {
		
		//recalc forces every call
		calcForces(entities);
		x+= vx;
		y+=vy;
		vx+=accx;
		vy+=accy;
		return true;
	}
	
	
}
