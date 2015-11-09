/*
 * @@author A0131300-unused 
 * Reason: Change of plans (change from Swing to JavaFX)
 *
package main.java.ui;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;


public class CustomOutputStream extends OutputStream {

	private JTextArea textArea;
    
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
    
    //b is the byte to be written as character to the JTextArea
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        //textArea.setCaretPosition(textArea.getDocument().getLength());
    }

}*/
