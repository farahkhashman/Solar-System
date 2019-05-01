import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;

public class Entity {

	int x, y;
	int vx, vy;
	int accx, accy;
	int m, rad;
	Image img;
	double bigG = 6.671e-11;
	
	public Entity(int xpos, int ypos, int vx, int vy, int mass, int radius, Image image) {
		x=xpos;
		y=ypos;
		this.vx = vx;
		this.vy = vy;
		m = mass;
		rad = radius;
		img = image;
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
	public boolean update() {
		x+= vx;
		y+=vy;
		vx+=accx;
		vy+=accy;
		return true;
	
	}
}
