package FinalProject;

import java.awt.EventQueue;

//import java.io.File;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeFrame frame = new WelcomeFrame();
					frame.setResizable(false);
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	

}
