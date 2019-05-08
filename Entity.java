import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Entity extends JPanel{

	double x, y;
	double vx, vy;
	double accx, accy;
	double m, rad;
	Image img;
	double bigG = 6.671e-11;
	
	public Entity(double xpos, double ypos, double vx, double vy, double mass, double radius, String string) throws IOException {
		x=xpos;
		y=ypos;
		this.vx = vx;
		this.vy = vy;
		m = mass;
		rad = radius;
		img = this.getToolkit().getImage(string).getScaledInstance((int)radius, (int)radius, Image.SCALE_SMOOTH);
		
	}
	
	
	//edited
	//calculates the x and y components of the net force
	public boolean calcForces(ArrayList<Entity> entities){
		
		accx = 0;
		accy = 0;
		
		for(Entity e : entities) {
			
			//Since we do not want to calculate a planet's gravity exerted on itself, we use this if statement to skip it
			if(this.equals(e)) {
				continue;
			}
			
			
			/* Equations
			 *         G * M1                        dx                          dy
			 * acc = ________       &&        cos@ = __         &&        sin@ = __       dx = (this.x - otherPlanet.x)
			 *          d^2                          d                           d
			 *          
			 *          
			 *          
			 *        G * M1   dx   G * M1 * dx               G * M1    dy     G * M1 * dy
			 * accx = ______ * __ = ___________       accy = ______  *  __   = ___________
			 *          d^2    d       d^3                     d^2      d         d^3     
			 *          
			 */
			
			accx-=(bigG * e.m * (x-e.x))  / Math.pow((x-e.x)*(x-e.x)+(y-e.y)*(y-e.y) ,1.5);
			accy+=(bigG * e.m * (y-e.y))  / Math.pow((x-e.x)*(x-e.x)+(y-e.y)*(y-e.y) ,1.5);
			
		}
		
		
		return true;
	}
	
	//updates the positions and speeds of the planet
	public boolean update(ArrayList<Entity> entities) {
		
		//recalc forces every call
		calcForces(entities);
		vx+=accx;
		vy+=accy;
		x+= vx;
		y-=vy;
		
		return true;
	}
	
	
}
