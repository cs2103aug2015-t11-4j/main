import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DetailPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	static JLabel labelMonth, labelYear;
	static JButton prevBtn, nextBtn, enterBtn;
	static JTextField commandField;
	static JTable tableCalendar;
	static JComboBox chooseYear;  //Choices of year
	static DefaultTableModel modelCalendar; //Table model
	static JScrollPane scrollCalendar; //The scrollpane
	static int realYear, realMonth, realDay, currentYear, currentMonth;

	public DetailPanel() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
		
		Dimension size = getPreferredSize();
		size.width = 470;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Calendar"));
		
		labelMonth = new JLabel ("January");
		labelYear = new JLabel ("Change year:");
		chooseYear = new JComboBox();
		prevBtn = new JButton ("<<");
		nextBtn = new JButton (">>");
		commandField = new JTextField(25);
		enterBtn = new JButton("Enter");
		modelCalendar = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		
		tableCalendar = new JTable(modelCalendar);
		scrollCalendar = new JScrollPane(tableCalendar);
		
		prevBtn.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (currentMonth == 0) { //Goes back one year
					currentMonth = 11;
					currentYear -= 1;
				}
				else { //Goes back one month
					currentMonth -= 1;
				}
				refreshCalendar(currentMonth, currentYear);
			}
		});
		
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				if (currentMonth == 11) { //Forward one year
					currentMonth = 0;
					currentYear += 1;
				}
				else { //Forward one month
					currentMonth += 1;
				}
				refreshCalendar(currentMonth, currentYear);
			}
		});
		
		chooseYear.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				if (chooseYear.getSelectedItem() != null) {
					String b = chooseYear.getSelectedItem().toString();
					currentYear = Integer.parseInt(b);
					refreshCalendar(currentMonth, currentYear);
				}
			}
		});
		
		
		//pass command to logic, create a logic object
		//Logic logic = new Logic();
		commandField.addActionListener(new ActionListener () {   //detect "enter" key being pressed
			public void actionPerformed(ActionEvent e) {
				//String command = commandField.getText();
				
				//String text = command + "\n";
				
				String commandLine = commandField.getText();
				String arr[] = commandLine.split(" ", 1);
				String op = arr[0];
				String text = commandLine + "\n";
				if(op.equals("add")) {
					fireDetailEvent(new DetailEvent(this, text));
				}

				fireDetailEvent(new DetailEvent(this, text));
			}
		});
		
		//delete afterwards
		enterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = commandField.getText();
				
				String text = command + "\n";

				fireDetailEvent(new DetailEvent(this, text));
			}
		});
		
		//Add controls to pane
		add(labelMonth);
		add(labelYear);
		add(chooseYear);
		add(prevBtn);
		add(nextBtn);
		add(scrollCalendar);
		add(commandField);
		add(enterBtn);
		
		//Set bounds
		labelMonth.setBounds(185-labelMonth.getPreferredSize().width/2, 25, 100, 25);  //position of month
		//labelYear.setBounds(10, 400, 80, 20);
		labelYear.setBounds(10, 400, 80, 20);
		chooseYear.setBounds(280, 400, 80, 20);  //scroll year
		//prevBtn.setBounds(10, 25, 60, 25);
		prevBtn.setBounds(50, 25, 60, 25);
		nextBtn.setBounds(400, 25, 60, 25);
		scrollCalendar.setBounds(10, 50, 350, 250);  //calendar size
		commandField.setBounds(10, 550, 27, 5);
		enterBtn.setBounds(40, 550, 10, 5);
		
		//Get real month/year
		GregorianCalendar cal = new GregorianCalendar(); //Create calendar
		realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
		realMonth = cal.get(GregorianCalendar.MONTH); //Get month
		realYear = cal.get(GregorianCalendar.YEAR); //Get year
		currentMonth = realMonth; //Match month and year
		currentYear = realYear;
		
		//Add headers of days
		String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		for (int i = 0; i < 7; i++) {
			modelCalendar.addColumn(headers[i]);
		}
		
		tableCalendar.getParent().setBackground(tableCalendar.getBackground()); //Set background

		//No resize/reorder
		tableCalendar.getTableHeader().setResizingAllowed(false);
		tableCalendar.getTableHeader().setReorderingAllowed(false);

		//Single cell selection
		tableCalendar.setColumnSelectionAllowed(true);
		tableCalendar.setRowSelectionAllowed(true);
		tableCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//Set row/column count
		tableCalendar.setRowHeight(38);
		modelCalendar.setColumnCount(7);
		modelCalendar.setRowCount(6);
		
		//Populate table
		for (int i = realYear-100; i <= realYear+100; i++) {
			chooseYear.addItem(String.valueOf(i));
		}
		
		//Refresh calendar
		refreshCalendar (realMonth, realYear); //Refresh calendar
		
	}
	
	public void fireDetailEvent(DetailEvent event) {
		Object[] listeners = listenerList.getListenerList();
		
		for(int i = 0; i < listeners.length; i += 2) {  //first is checking the class DetailListener.class
			if(listeners[i] == DetailListener.class) {
				((DetailListener)listeners[i+1]).detailEventOccurred(event);  //when it is from the class, cast it to detailListener
			}
		}
	}
	
	public void addDetailListener(DetailListener listener) {
		listenerList.add(DetailListener.class, listener);
	}
	
	public void removeDetailListener(DetailListener listener) {
		listenerList.remove(DetailListener.class, listener);
	}
	
	public static void refreshCalendar(int month, int year) {
		//Variables
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som; //Number Of Days, Start Of Month
			
		//Allow/disallow buttons
		prevBtn.setEnabled(true);
		nextBtn.setEnabled(true);
		if (month == 0 && year <= realYear-10) {
			prevBtn.setEnabled(false);
		} //Too early
		if (month == 11 && year >= realYear+100) {
			nextBtn.setEnabled(false);
		} //Too late
		labelMonth.setText(months[month]); //Refresh the month label (at the top)
		//lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
		labelMonth.setBounds(185-labelMonth.getPreferredSize().width/2, 25, 180, 25);
		chooseYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
		
		//Clear table
		for (int i = 0; i < 6; i++){
			for (int j = 0; j < 7; j++){
				modelCalendar.setValueAt(null, i, j);
			}
		}
		
		//Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		
		//Draw calendar
		for (int i = 1; i <= nod; i++){
			int row = new Integer((i + som - 2) / 7);
			int column  =  (i + som - 2) % 7;
			modelCalendar.setValueAt(i, row, column);
		}

		//Apply renderers
		tableCalendar.setDefaultRenderer(tableCalendar.getColumnClass(0), new tblCalendarRenderer());
	}

	static class tblCalendarRenderer extends DefaultTableCellRenderer {

		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			if (column == 0 || column == 6) { //Week-end
				setBackground(new Color(255, 220, 220));
			}
			else { //Week
				setBackground(new Color(255, 255, 255));
			}
			if (value != null) {
				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear) { //Today
					setBackground(new Color(220, 220, 255));
				}
			}
			setBorder(null);
			setForeground(Color.black);
			return this;  
		}
	}
}
