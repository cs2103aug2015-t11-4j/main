import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextArea;

//controller to tell project what to do
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private DetailPanel detailPanel;
	
	public MainFrame(String title) {
		super(title);
		
		//set layout manager
		setLayout(new BorderLayout());
		
		//create swing component
		JTextArea textArea = new JTextArea();
		
		detailPanel = new DetailPanel();
		
		detailPanel.addDetailListener(new DetailListener() {
			public void detailEventOccurred(DetailEvent event) {
				String text = event.getText();
				
				textArea.append(text);
			}
		});
		
		//add swing components to content pane
		Container c = getContentPane();
		
		c.add(textArea, BorderLayout.CENTER);
		c.add(detailPanel, BorderLayout.WEST);
		
	}
}
