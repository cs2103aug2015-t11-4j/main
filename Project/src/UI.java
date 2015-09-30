import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class UI {
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new MainFrame("Calendar");
				frame.setSize(800, 650);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);				
			}
		});
	}
}
