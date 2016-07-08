package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;

//This class is pretty much the same as the one in guided step 1.
public class ChipWindowMaker extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChipWindowMaker(ChipPanel p){
		super("Chip's Challenge");
		final ChipPanel panel = p;
		setLocationRelativeTo(null);
		add(panel, BorderLayout.CENTER);
	}
	
}
