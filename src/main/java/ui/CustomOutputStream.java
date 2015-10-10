package main.java.ui;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

/**
 * 
 * @author Yu Ju
 *
 */

public class CustomOutputStream extends OutputStream {

	private JTextArea textArea;
    
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
    
    /**
     * @param b the byte to be written as character to the JTextArea
     */
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        //textArea.setCaretPosition(textArea.getDocument().getLength());
    }

}
