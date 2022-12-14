package FinalProject;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton; 
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

public class WelcomeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
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
	public WelcomeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome To GOT Database");
		lblNewLabel.setBounds(134, 27, 174, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Initiate Connection");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startTableUI();
				dispose();
			}
		});
		btnNewButton.setBounds(134, 192, 164, 31);
		contentPane.add(btnNewButton);
		
	}
	
	public void startTableUI() {
		TablesFrame tf = new TablesFrame();
		tf.init();
	}
}
