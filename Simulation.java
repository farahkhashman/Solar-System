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
			//          new Entity(int xpos, int ypos, int vx, int vy, int mass, int radius, Image image)
			try {
				System.out.println(1);
				//sun
				entities.add(new Entity(width/2, height/2, 0, 0, 1.989e30, 695510, "Sun.png"));
				
				//earth
				entities.add(new Entity(width/3, height/2, 0, 10, 5.972e24, 6371, "Earth.png"));
				
				//moon
				//entities.add(new Entity(width/3, height/2-30, 2, 0, 7.348e22, 1737.1, "Moon.png"));
				System.out.println(0);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void run() {
			System.out.println(2);

			
			repaint();
			try {
				Thread.sleep(35);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public void paint(Graphics g) {
			System.out.println(3);

			g.drawImage(smallerimg,0,0,this);
			System.out.println(4);

			for(Entity pl: entities) {
				System.out.println(5);
				try {
				pl.update(entities);
				}catch(Exception e) {
					System.out.println(0);

					e.printStackTrace();
				}
				g.drawImage(pl.img,(int)(pl.x-pl.rad),(int) (pl.y-pl.rad),this);
				

			}

			
		}

		public static void main(String[] args) {
		
			Simulation test =  new Simulation();

		}

}