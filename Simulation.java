import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Simulation extends JPanel {

		
	private final int width = 1000, height = 700;
	Image img, smallerimg;
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<ArrayList<Point>> list_orbit = new ArrayList<ArrayList<Point>>();
	ArrayList<Point> orbit;
	
	//Variables to display the simulation
	double timescale = 36500;
	double GraphicsScaling = 3e9/5;
	double Distance = 0;
	
		public Simulation() {
			BoxLayout boxlayout = new BoxLayout(this, BoxLayout.Y_AXIS);
			setLayout(boxlayout);
			JTextArea displayarea = new JTextArea();
			displayarea.setEditable(false);
			
			JFrame frame = new JFrame();
			frame.setSize(width, height);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(this);
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			frame.setVisible(true);
			this.setFocusable(true);
			
			img = Toolkit.getDefaultToolkit().getImage("spaceb.png");
			smallerimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			
			//Creating the list of planets or objects
			//               new Entity(int xpos, int ypos, int vx, int vy, int mass, int radius, String image)
			try {
				orbit = new ArrayList<Point>();
				//sun
				entities.add(new Entity(0, 0, 0, 0, 1.989e30, 695510, "Sun.png"));
				
				//earth
				entities.add(new Entity(0, -149.6e9, 3e4, 0, 5.972e24, 6371, "Earth.png"));
				
				//saturn
				entities.add(new Entity(0, -1427e9,  9680, 0, 5.683e26, 58232, "Saturn.png"));
				
				//moon
				//entities.add(new Entity(0 ,-149.6e9 - 384400, 32000, 0, 7.348e22, 1737.1, "Moon.png"));
				
				//mercury
				entities.add(new Entity(0, -57.9e9, 48e3, 0, 3.285e23, 2439.7, "Mercury.png")); 
				
				//jupiter
				entities.add(new Entity(0, -778.3e9,  13070, 0, 1.89813e27, 69911 , "Jupiter.png"));
				
				//mars
				entities.add(new Entity(0, -227.9e9, 24007, 0, 6.39e23, 3389.5 , "Mars.png"));
				
				//neptune
				entities.add(new Entity(0, -4497.1e9, 5430, 0, 1.024e26, 24622, "3D_Neptune.png"));
				
				//pluto
				entities.add(new Entity(0, -5913e9, 4670, 0, 1.30900e22, 1188.3, "Pluto.png"));
				
				//uranus
				entities.add(new Entity(0, -2871.0e9, 6800, 0, 8.681e25, 25362, "Uranus.png"));
							
				//venus
				entities.add(new Entity(0, -108.2e9, 35020, 0, 4.867e24, 6051.8, "Venus.png"));
				
			//gets the largest distance and uses that in the scaling calculation
				for(Entity pl : entities) {
					if(pl.y < Distance) {
						Distance = pl.y;
					}
				}
		
				System.out.println("Distance: "+Distance);
				GraphicsScaling = Distance/500;
				
				//the scaling is using a ratio, but we should try to make it logarithmically instead
				run();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void run() {
			
			list_orbit.add(orbit);
			
			while(true) {
				repaint();
				try {
					Thread.sleep(35);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public void paint(Graphics g) {

			//g.drawImage(smallerimg,0,0,this);


			for(Entity pl: entities) {
				
				//this updates the position of the planets
				//the for loop makes the simulation go faster depending on the timeScale value, the higher the timeScale the faster
				for(int i=0;i<timescale; i++) {
					pl.update(entities);
				}
				
				if(! pl.name.equals("Sun")) {
				Point c = new Point((int)((pl.x/GraphicsScaling)+width/2),(int)((pl.y/GraphicsScaling)+height/2));
				orbit.add(c);
				}
				

				
				
				g.setColor(Color.RED);
				
				//The GraphicsScaling variable is a number that makes the distances between the planets fit on the screen.
				//
				// In our calculations, the sun is at (0,0) and the other planets are referenced to that point also.
				//  So (0,0) is shifted to the center of the screen by adding width/2 to x, and height/2 to y after scaling down the universe to screen-size
				g.fillOval((int)((pl.x/GraphicsScaling)+width/2), (int)((pl.y/GraphicsScaling)+height/2), (int)(Math.log(pl.rad)), (int)(Math.log(pl.rad)) );
				
				
				System.out.println((pl.x/GraphicsScaling+width/2) +" and " + (pl.y/GraphicsScaling+height/2));
				

				}
				for(int j = 0; j < list_orbit.size(); j++) {
				
				//System.out.println(list_orbit.get(j));
				
				for(int i = 0; i < list_orbit.get(j).size()-1; i+=1) {
				
				 Graphics2D g2 = (Graphics2D) g;
				 g2.setStroke(new BasicStroke(3));
				 g2.setColor(Color.green);
				 g2.drawLine(list_orbit.get(j).get(i).x, list_orbit.get(j).get(i).y, list_orbit.get(j).get(i+1).x, list_orbit.get(j).get(i+1).y);
			}
		
			}

			
		}

		public static void main(String[] args) {
		
			Simulation test =  new Simulation();

		}

}
