package main.java.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.java.logic.Alt4;
import main.java.logic.Logic;
import main.java.storage.Storage;

/**
 * 
 * @author Yu Ju
 *
 */

public class todoapp extends JFrame{
	
	private static final long serialVersionUID = 7154613852426382429L;
	
	// JLabel and JTextField to input a task   
	//static private JLabel taskJLabel;   
	static private JTextField taskJTextField;     
   
	// JTextArea to display the to-do list   
	static private JTextArea outputJTextArea; 
	
	static private String command;
	static private String commandField;
	static private String[] arr;
	static private String description;
	//static private String[] commandOp;
	static private String[] commandFieldArr;
	
	//static private BorderLayout bl;
	
	static private JTable table;
	static private JScrollPane sp;
	static private String [][] records;
	
	//static private String format = "%1$5s %2$-40s %3$-20s";
	//static private String line;
      
	// main method   
	public static void main( String[] args )   
	{   
		// Schedule a job for the event-dispatching thread: 
		// creating and showing this application's GUI. 
		javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			public void run() { 
				new todoapp(); 
				System.out.println("Welcome to ALT4, your personlized agenda manager");
			} 
		}); 

	}
		
	public todoapp() {
		
		JFrame mainFrame = new JFrame("Alt4");
		mainFrame.setSize(600, 600);
		mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
		// set properties of application’s window   
		mainFrame.setVisible(true);  // display window
		
		Container contentPane = mainFrame.getContentPane();   
        
		// enable explicit positioning of GUI components   
		contentPane.setLayout(null);     
        
		// set up taskJTextField   
		taskJTextField = new JTextField();   
		taskJTextField.setBounds(10, 480, 560, 40);   
		taskJTextField.setHorizontalAlignment(JTextField.LEFT);   
		contentPane.add(taskJTextField);      
		
		//to detect if the "enter" key is pressed
		taskJTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					commandField = taskJTextField.getText();
				}
				catch(NumberFormatException excep) {
					System.out.println("Please Enter the Right Information");
				}
		
				commandField = commandField.toLowerCase();
				
				if(commandField.equals("display")){
					Logic.takeAction(Logic.passToParser(commandField));
					String[] columns = {"Floating", "Event", "Due Date"};
					Object rows[][] = records;
					//Object rows[][] = Storage.display();
					table = new JTable(rows, columns);
					sp = new JScrollPane(table);
					contentPane.add(sp, BorderLayout.CENTER);

					/*catch(NullPointerException e1) {
						System.out.println("File is currently empty.");
					}*/
				}
				
				else {
					arr = commandField.split(" ", 2);
					command = arr[0];
					description = (arr[1]).trim();
					switch(command) {
					case "add":
						System.out.println("added: " + description);
						break;
					case "delete":
						System.out.println("deleted: " + description);
						break;
					case "update":
						System.out.println("updated: " + description);
						break;
					//case "display":
						//Logic.passToParser(command);
						//line = String.format(format, "1.", "meeting with bob", "09/10/15");
						//outputJTextArea.append(line + "\n");
						/*String [] columns= {"Tasks Completed", "Task To Be Done"};
						String [] columns= {"Tasks", "Due Date", "Time"};
			        	String array ="A&B&1&May 8 2011 12:17AM;;E&D&5&May 8 2011 12:43AM;;F&G&5&May 8 2011 7:06AM;;H&I&1&May 14 2011 11:57PM";
			        	records = to2dim (array ,";;","&");
			        	Object rows[][] = records;
						JTable table = new JTable(rows, columns);
			        	sp = new JScrollPane(table);
			        	contentPane.add(sp, BorderLayout.CENTER);*/
						//break;
					default:
						System.out.println("wrong command");
					}
					//System.out.println(text);
					taskJTextField.setText("");
					//commandOp = new String[] {command};
					//Alt4.main(commandOp);
					commandFieldArr = new String[] {commandField};
					//Alt4.main(commandFieldArr);
					ArrayList<String> contentList = new ArrayList<String>();
					contentList = Logic.passToParser(commandField);
					Logic.takeAction(contentList);
				}
			}
		});
               
		// set up outputJTextArea   
		outputJTextArea = new JTextArea();   
		outputJTextArea.setEditable(false);   
		
		contentPane.add(outputJTextArea, BorderLayout.CENTER);
  
		// set up JScrollPane to allow JTextArea scrolling   
		JScrollPane scrollJScrollPane =    
		new JScrollPane(outputJTextArea);   
		scrollJScrollPane.setBounds(10, 10, 560, 450);   
		contentPane.add(scrollJScrollPane);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(outputJTextArea));
         
        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);

        //textarea();
	} // end method main
	
	/*public static String [][] to2dim (String source , String outerdelim, String innerdelim) {

        String [][] result = new String [(source.split (outerdelim)).length][];
        int count = 0;

        System.out.println("result len "+result.length);
        for (String line : source.split (outerdelim ))
            {
                result [count++] = line.split (innerdelim);
            }

        System.out.println(Arrays.deepToString(result));
        return result;
    }*/

	/*private void textarea() throws Exception {
		Container contentPane;
		contentPane = (JPanel) this.getContentPane();
        outputJTextArea.setText("Items\tPrice\tQTY\tTotals\n");
        outputJTextArea.append("Pants\t5.89\t2\t?\n");
        outputJTextArea.append("Shoes\t2.33\t4\t?\n");
        outputJTextArea.append("Sandals\t3.00\t5\t?\n");
	    
        bl = new BorderLayout();
	    contentPane.setLayout(bl);
	    this.setSize(new Dimension(400, 300));
	    this.setTitle("JTextArea as JTable");
	    contentPane.add(outputJTextArea, BorderLayout.CENTER);
	}*/
	
	//currently not in use
	public static void welcome() {
		//System.out.println("Welcome to Alt4!");   //if this is used, text will be printed out in the eclipse console and not ui
		PrintStream standardOut = new PrintStream(new CustomOutputStream(outputJTextArea));
	}
   
	//currently not in use
	public static void feedbackWrongCommand() {
		//System.out.println("Wrong command");
		PrintStream standardOut = new PrintStream(new CustomOutputStream(outputJTextArea));
	}
}
