import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Simulation extends JPanel {

		
	private final int width = 1000, height = 700;
	Image img, smallerimg;
	ArrayList<Entity> entities = new ArrayList<Entity>();
		
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
			//          new Entity(int xpos, int ypos, int vx, int vy, int mass, int radius, String image)
			try {
				
				//sun
				entities.add(new Entity(width*2e9/2, height*2e9/2, 0, 0, 1.989e30, 695510, "Sun.png"));
				
				//earth
				entities.add(new Entity(width*2e9/3, height*2e9/2, 0, 3e6, 5.972e24, 6371, "Earth.png"));
				
				//moon
				//entities.add(new Entity(width/3, height/2-30, 2, 0, 7.348e22, 1737.1, "Moon.png"));
				
				
				run();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void run() {

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
				g.setColor(Color.RED);
				g.fillOval((int)((pl.x-pl.rad)/2e9),(int) ((pl.y-pl.rad)/2e9),(int) Math.log(pl.rad), (int) Math.log(pl.rad/1000));
				System.out.println(pl.x/2e9);
				pl.update(entities);
				

			}

			
		}

		public static void main(String[] args) {
		
			Simulation test =  new Simulation();

		}

}