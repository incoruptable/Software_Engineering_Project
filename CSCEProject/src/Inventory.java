import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

import DAO.DAO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;

public class Inventory {

	private JFrame frmInventory;
	private DAO dao;  // Won't do anything until we implement the actual server queries.
	// locations will need to be dynamically created for Deliverable 3, this is for demo purpose
	private String[] locations = {"Store 1", "Store 2", "Store 3"};

	/**
	 * Launch the application.
	 */
	public void CreateProfilePopUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory window = new Inventory();
					window.frmInventory.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Inventory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		dao = new DAO();
		
		frmInventory = new JFrame();
		frmInventory.setResizable(false);
		frmInventory.setBounds(100, 100, 500, 500);
		frmInventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frmInventory.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox invComboBox = new JComboBox(locations);
		invComboBox.setEditable(true);
		invComboBox.setSelectedItem("Select Location");
		invComboBox.setEditable(false);
		invComboBox.setBounds(50, 25, 200, 20);
		panel.add(invComboBox);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(375, 25, 75, 20);
		panel.add(btnExit);
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setBounds(50, 60, 400, 360);
		panel.add(scrollPane);
		
		
		btnExit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				frmInventory.dispose();
			}
		});
				
		} catch(SQLException es){
			es.printStackTrace();
		}
	}
	
	// Retrieves the list of locations from the database
	private void getLocations() {
		
	}
}


