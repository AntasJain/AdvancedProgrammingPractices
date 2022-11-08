package FinalProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TablesFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane sp;
	CharacterMapperImpl impl = CharacterMapperImpl.getInstance();
	
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablesFrame frame = new TablesFrame();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				    frame.setSize(screenSize.width-100, screenSize.height-100);
				    frame.setLocation(screenSize.width/2-frame.getSize().width/2, screenSize.height/2-frame.getSize().height/2);
				    frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TablesFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Game Of Thrones Characters Database");
		lblNewLabel.setBounds(500, 40, 242, 16);
		contentPane.add(lblNewLabel);
		setTableData(impl.SELECT());
		

		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.HORIZONTAL);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("INSERT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				launchInsertDialog();
			}
		});
		btnNewButton.setBounds(420, 620, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnUpdate.setBounds(550, 620, 117, 29);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(680, 620, 117, 29);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				launchDeleteDialog();
				
			}
		});
		
		JButton btnNewButton_2_1 = new JButton("SELECT");
		btnNewButton_2_1.setBounds(810, 620, 117, 29);
		contentPane.add(btnNewButton_2_1);
	}
	public void setTableData(Characters[] obj) {

		String columnNames[]= {"ID","FIRSTNAME","LASTNAME","TITLE","FAMILY"};
		
		Object data[][] = new Object[obj.length][5];
		for(int i =0;i<obj.length;i++) {
			data[i][0]=obj[i].getId();
			data[i][1]=obj[i].getFIRSTNAME();
			data[i][2]=obj[i].getLASTNAME();
			data[i][3]=obj[i].getTITLE();
			data[i][4]=obj[i].getFAMILY();
		}
		
		table = new JTable(data, columnNames) {

	         private static final long serialVersionUID = 1L;

			public boolean editCellAt(int row, int column, java.util.EventObject e) {
	            return false;
	         }
	      };
	    table.setRowHeight(25);
		table.setBounds(160, 90, 700, 480);
		table.setShowGrid(true);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.setVisible(true);
		sp = new JScrollPane(table);
		sp.setBounds(150, 80, 1000, 480);
		sp.setVisible(true);
		contentPane.add(sp);
	}
	public void launchDeleteDialog() {
		JDialog d = new JDialog(this,"Delete Data!");
		d.setResizable(false);
		d.setLayout( new GridLayout(2,2));
		String cats[]= {"ID","FIRSTNAME","LASTNAME","TITLE","FAMILY"};
		JComboBox<String> categories = new JComboBox<String>(cats);
        d.add(categories);
        JTextField value = new JTextField("");
        d.add(value);
        JButton btnDeleteIt = new JButton("DELETE");
        d.add(btnDeleteIt);
        btnDeleteIt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//int row = Integer.valueOf(value.getText().toString())-1;
//				Characters characters=impl.SELECT()[row];
//				impl.DELETE(characters);
				impl.DELETE(categories.getItemAt(categories.getSelectedIndex()),value.getText().toString());
				table.setVisible(false);
				sp.remove(table);
				contentPane.remove(sp);
				setTableData(impl.SELECT());
				d.dispose();
			}
        	
        });
        d.setSize(300,100);    
        d.setVisible(true);  
	}
	
	public void launchInsertDialog() {
		JDialog d = new JDialog(this,"Insert New Data!");
		d.setResizable(false);;
		d.setLayout( new GridLayout(5,2) ); 
		JLabel fName = new JLabel("FirstName");
        d.add(fName);
        JTextField fname = new JTextField("");
        d.add(fname);
        JLabel lName = new JLabel("LastName");
        d.add(lName);
        JTextField lname = new JTextField("");
        d.add(lname);
        JLabel Title = new JLabel("Title");
        d.add(Title);
        JTextField title = new JTextField("");
        d.add(title);
        JLabel Family = new JLabel("Family");
        d.add(Family);
        JTextField family = new JTextField("");
        d.add(family);  
        JButton btnInsert = new JButton("INSERT");
        d.add(btnInsert);
        btnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Characters characters = new Characters(0,fname.getText().toString(),lname.getText().toString(),title.getText().toString(),family.getText().toString());
				impl.INSERT(characters);
				table.setVisible(false);
				sp.remove(table);
				contentPane.remove(sp);
				setTableData(impl.SELECT());
				d.dispose();
			}
        	
        });
        d.setSize(500,300);    
        d.setVisible(true);  
	}
}
