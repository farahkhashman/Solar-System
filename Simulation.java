import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Simulation extends JPanel {
	private final int width = 1000, height = 700;
	
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
		
	
	}
	
	public void run() {
		
	}

	public static void main(String[] args) {
		Simulation test =  new Simulation();

	}

}
