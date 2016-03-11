import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import DAO.DAO;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

public class Profile {
	private JFrame frmNewPatient;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField middleName;
	private JTextField address;
	private JTextField city;
	private JComboBox state;
	private JTextField zipCode;
	private JTextField phone;
	private JTextField email;
	private DAO dao;
	private JTextField altPhone;
	private JFormattedTextField ssn;

	
	/**
	 * Launch the application.
	 */
	public void CreateProfilePopUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile window = new Profile();
					window.frmNewPatient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Profile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		dao = new DAO();
		
		frmNewPatient = new JFrame();
		frmNewPatient.setResizable(false);
		frmNewPatient.setBounds(100, 100, 700, 700);
		frmNewPatient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frmNewPatient.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblProfileTitle = new JLabel("Patient Profile");
		lblProfileTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfileTitle.setBounds(300, 25, 100, 25);
		panel.add(lblProfileTitle);
		
		JLabel FirstNamelbl = new JLabel("First Name:");
		FirstNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		FirstNamelbl.setBounds(50, 75, 75, 20);
		panel.add(FirstNamelbl);
		
		firstName = new JTextField();
		firstName.setColumns(10);
		firstName.setBounds(130, 75, 100, 20);
		panel.add(firstName);
		
		JLabel LastNamelbl = new JLabel("Last Name:");
		LastNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		LastNamelbl.setBounds(235, 75, 60, 20);
		panel.add(LastNamelbl);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(300, 75, 100, 20);
		panel.add(lastName);
		
		JLabel MiddleNamelbl = new JLabel("Middle Name:");
		MiddleNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		MiddleNamelbl.setBounds(405, 75, 65, 20);
		panel.add(MiddleNamelbl);
		
		middleName = new JTextField();
		middleName.setColumns(10);
		middleName.setBounds(475, 75, 100, 20);
		panel.add(middleName);
		
		JLabel Addresslbl = new JLabel("Address:");
		Addresslbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Addresslbl.setBounds(50, 185, 75, 20);
		panel.add(Addresslbl);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(130, 185, 200, 20);
		panel.add(address);
		
		JLabel Citylbl = new JLabel("City:");
		Citylbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Citylbl.setBounds(100, 215, 25, 20);
		panel.add(Citylbl);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(130, 215, 150, 20);
		panel.add(city);
		
		JLabel Statelbl = new JLabel("State:");
		Statelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Statelbl.setBounds(285, 215, 35, 20);
		panel.add(Statelbl);
		
		String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
				"LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK",
				"OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		
		state = new JComboBox(states);
		state.setSelectedIndex(0);
		state.setMaximumRowCount(50);
		state.setBounds(325, 215, 50, 20);
		panel.add(state);
		
		JLabel ZipCodelbl = new JLabel("Zip Code:");
		ZipCodelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		ZipCodelbl.setBounds(380, 215, 50, 20);
		panel.add(ZipCodelbl);
		
		zipCode = new JTextField();
		zipCode.setColumns(10);
		zipCode.setBounds(435, 215, 40, 20);
		panel.add(zipCode);
		
		JLabel Phonelbl = new JLabel("Phone:");
		Phonelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Phonelbl.setBounds(85, 245, 40, 20);
		panel.add(Phonelbl);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(130, 245, 75, 20);
		panel.add(phone);
		
		JLabel altPhonelbl = new JLabel("Alt. Phone:");
		altPhonelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		altPhonelbl.setBounds(210, 245, 55, 20);
		panel.add(altPhonelbl);
		
		altPhone = new JTextField();
		altPhone.setColumns(10);
		altPhone.setBounds(270, 245, 75, 20);
		panel.add(altPhone);
		
		JLabel Emaillbl = new JLabel("Email:");
		Emaillbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Emaillbl.setBounds(36, 389, 75, 14);
		panel.add(Emaillbl);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(121, 386, 183, 20);
		panel.add(email);
		
		JLabel SSNlbl = new JLabel("SSN:");
		SSNlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		SSNlbl.setBounds(75, 105, 50, 20);
		panel.add(SSNlbl);
		
		ssn = new JFormattedTextField();
		ssn.setColumns(10);
		ssn.setBounds(130, 105, 75, 20);
		panel.add(ssn);
		
		JLabel DOBlbl = new JLabel("D.O.B:");
		DOBlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		DOBlbl.setBounds(260, 106, 35, 20);
		panel.add(DOBlbl);
		
		JComboBox dobMonth = new JComboBox();
		dobMonth.setMaximumRowCount(12);
		dobMonth.setBounds(305, 105, 50, 20);
		panel.add(dobMonth);
		
		JButton createBtn = new JButton("Create");
		createBtn.setBounds(50, 540, 139, 38);
		panel.add(createBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(210, 540, 139, 38);
		panel.add(cancelBtn);
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNewPatient.dispose();
			}
		});
		
		createBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				createProfile();
			}
		});
		} catch(SQLException es){
			es.printStackTrace();
		}
	}
	
	/* The create function below needs to be specifically implemented for the fields contained within the patient
	 * profile.  I don't know if the database is currently set up to handle these, nor do I know what order they
	 * would even be in for so I am leaving it all commented out until we sort that out.
	 */
	
	public void createProfile()
	{
		try{
			// Checking for empty fields
			if(firstName != null) {
						dao.setExpectRS(false);
						dao.setquery("INSERT INTO dbo.LoginPage (username, password, userid, securityQst, lastName, firstName, middleName, address, city, state, zipCode, phone, eMail)" +
								"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
						//dao.SetParameter(username.getText());
						//dao.SetParameter(String.valueOf(password.getPassword()));
						//dao.SetParameter(Integer.parseInt(userId.getText()));
						//dao.SetParameter(securityQst.getText());
						dao.SetParameter(lastName.getText());
						dao.SetParameter(firstName.getText());
						dao.SetParameter(middleName.getText());
						dao.SetParameter(address.getText());
						dao.SetParameter(city.getText());
					//	dao.SetParameter(state.getText());
						dao.SetParameter(Integer.parseInt(zipCode.getText()));
						dao.SetParameter(phone.getText());
						dao.SetParameter(email.getText());
						
						dao.executeQuery();
						}
			else{
				//FIELDS EMPTY M8
				JOptionPane.showMessageDialog(null, "Fields Incomplete", "Fill in empty feilds.", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}

