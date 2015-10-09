package main.java.ui;
import java.util.EventListener;

/**
 * 
 * @author Yu Ju
 *
 */

public interface DetailListener extends EventListener{
	public void detailEventOccurred(DetailEvent event);
}