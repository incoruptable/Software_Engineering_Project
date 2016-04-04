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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.Box;
import java.awt.Container;
import java.awt.Component;

import DAO.DAO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class PatientSearch {

	private JFrame frmPatientSearch;
	private DAO dao;
	private JTextField searchTextField;
	private String[] searchParam = {"Last Name", "SSN"};
	private JComboBox searchComboBox;
	private JTable resultTable;
	private String firstname, lastname, SSN, DOB, address, phone, email;
	private Model model;

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
	
	public class Model extends DefaultTableModel {
		
		Model(Object[][] data, String[] columnNames) {
			super(data, columnNames);
		}
		
		public boolean isCellEditable(int row, int column) {
			return false;
		}
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 61, 600, 600);
		panel.add(scrollPane);
		
		resultTable = new JTable();
		model = new Model(
				new Object[][] {
				},
				new String[] {
					"First Name", "Last Name", "SSN", "DOB", "Address", "Phone", "E-Mail"
				}
			);
		scrollPane.setViewportView(resultTable);
		resultTable.setModel(model);

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					search(searchTextField.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pReset();
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
	private void search(String param) throws SQLException{
		String p = param;
		sReset();
		if("Last Name".equals(String.valueOf(searchComboBox.getSelectedItem()))) {
			searchOnLastName(param);
		}
		else if("SSN".equals(String.valueOf(searchComboBox.getSelectedItem()))) {
			searchOnSSN(param);
		}
		else {
			JOptionPane.showMessageDialog(null, "Must select a search parameter first", "Select a search parameter", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void searchOnLastName(String param) throws SQLException {
		ResultSet rs;
		if(!param.isEmpty()){
			dao.setquery("SELECT firstName, lastName, SSN, DOB, address, phone, email FROM dbo.PatientTable WHERE lastName = ?");
			dao.SetParameter(param);
			dao.setExpectRS(true);
			rs = dao.executeQuery();
			if(rs.next()){
				do{
					this.firstname= rs.getString(1);
					this.lastname= rs.getString(2);
					this.SSN = rs.getString(3);
					this.DOB = rs.getString(4);
					this.address = rs.getString(5);
					this.phone = rs.getString(6);
					this.email = rs.getString(7);
					
					model.addRow(new Object[]{this.firstname, this.lastname, this.SSN, this.DOB, this.address, this.phone, this.email});
				}while(rs.next());
			}
			else{
				JOptionPane.showMessageDialog(null, "No Patients found with this Last Name", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please fill the parameter field", "Must insert a Last Name", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void searchOnSSN(String param) throws SQLException {
		ResultSet rs;
		if(!param.isEmpty()){
			dao.setquery("SELECT firstName, lastName, SSN, DOB, address, phone, email FROM dbo.PatientTable WHERE SSN = ?");
			dao.SetParameter(Integer.parseInt(param));
			dao.setExpectRS(true);
			rs = dao.executeQuery();
			if(rs.next()){
				do{
					this.firstname= rs.getString(1);
					this.lastname= rs.getString(2);
					this.SSN = rs.getString(3);
					this.DOB = rs.getString(4);
					this.address = rs.getString(5);
					this.phone = rs.getString(6);
					this.email = rs.getString(7);
					
					model.addRow(new Object[]{this.firstname, this.lastname, this.SSN, this.DOB, this.address, this.phone, this.email});
				}while(rs.next());
			}
			else{
				JOptionPane.showMessageDialog(null, "No Patients found with this SSN", "", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Please fill the parameter field", "Must insert a SSN", JOptionPane.ERROR_MESSAGE);
		}
	}
	// Resets the dropdown selection to a non-selectable descriptor option
	private void pReset(){
		searchComboBox.setEditable(true);
		searchComboBox.setSelectedItem("Search Parameter");
		searchComboBox.setEditable(false);
	}
	private void sReset(){
		for(int i = model.getRowCount()-1; i >= 0; i--){
			model.removeRow(i);
		}
	}
}

