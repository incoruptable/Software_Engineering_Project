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

public class Reports {

	private JFrame frmReports;
	private DAO dao;  // Won't do anything until we implement the actual server queries
	// Reports will need to be decided on, these are for demo purpose
	private String[] reportList = {"Call List", "Report Type 2", "Report Type 3"};

	/**
	 * Launch the application.
	 */
	public void CreateProfilePopUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reports window = new Reports();
					window.frmReports.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Reports() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		dao = new DAO();
		
		frmReports = new JFrame();
		frmReports.setResizable(false);
		frmReports.setBounds(100, 100, 500, 200);
		frmReports.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frmReports.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox invComboBox = new JComboBox(reportList);
		invComboBox.setEditable(true);
		invComboBox.setSelectedItem("Select Report");
		invComboBox.setEditable(false);
		invComboBox.setBounds(50, 25, 200, 20);
		panel.add(invComboBox);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(265, 25, 100, 20);
		panel.add(btnGenerate);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(375, 25, 75, 20);
		panel.add(btnExit);
				
		btnExit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				frmReports.dispose();
			}
		});
				
		} catch(SQLException es){
			es.printStackTrace();
		}
	}

}


