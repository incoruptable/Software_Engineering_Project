import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.Box;
import java.awt.Container;
import java.awt.Component;

import DAO.DAO;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.List;

public class PatientSearch {

	private JFrame frmPatientSearch;
	private DAO dao;
	private JTextField searchTextField;
	private String[] searchParam = {"Last Name", "SSN"};
	private JComboBox searchComboBox;
	private Box resultsBox;
	private List patientList = new List();

	/**
	 * Launch the application.
	 */
	public void CreateProfilePopUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientSearch window = new PatientSearch();
					window.frmPatientSearch.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PatientSearch() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		dao = new DAO();
		
		frmPatientSearch = new JFrame();
		frmPatientSearch.setResizable(false);
		frmPatientSearch.setBounds(100, 100, 700, 700);
		frmPatientSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frmPatientSearch.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		// Search Parameter ComboBox information
		searchComboBox = new JComboBox(searchParam);
		pReset();
		searchComboBox.setBounds(50, 30, 150, 20);
		panel.add(searchComboBox);
				
		searchTextField = new JTextField();
		searchTextField.setBounds(210, 30, 180, 20);
		panel.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(400, 30, 75, 20);
		panel.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(488, 30, 74, 20);
		panel.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(575, 30, 75, 20);
		panel.add(btnExit);
		
		Box resultsBox = Box.createVerticalBox();
		resultsBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultsBox.setBounds(50, 75, 600, 550);
		panel.add(resultsBox);
		resultsBox.add(patientList);
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search();
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sReset();
			}
		});
		
		btnExit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				frmPatientSearch.dispose();
			}
		});
				
		} catch(SQLException es){
			es.printStackTrace();
		}
	}
	// The search function - presumably will have a single parameter to be passed to the database and queried upon
	private void search(){
		patientList.removeAll();
		String param = searchTextField.getText();
		addPatient(param);
		// For each returning true query create a new patient in the box
		
	}
	
	private void addPatient(String pInfo){
		patientList.add(pInfo);
	}
	
	// A full reset of all fields
	// Currently only resets the parameter dropdown and the text field but will eventually get rid of all the previous search results as well
	private void sReset(){
		pReset();
		searchTextField.setText("");
		patientList.removeAll();
	}
	
	// Resets the dropdown selection to a non-selectable descriptor option
	private void pReset(){
		searchComboBox.setEditable(true);
		searchComboBox.setSelectedItem("Search Parameter");
		searchComboBox.setEditable(false);
	}
}

