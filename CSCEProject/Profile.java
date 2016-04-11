import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import DAO.DAO;
import DateFormatter.DateLabelFormatter;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Font;


public class Profile {
	private JFrame frmNewPatient;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField middleName;
	private JTextField address;
	private JTextField city;
	private JComboBox state;
	private JFormattedTextField zipCode;
	private JFormattedTextField phone;
	private JTextField email;
	private JDatePickerImpl datePicker;
	private JTextField DOBfield;
	private DAO dao;
	private JFormattedTextField altPhone;
	private JFormattedTextField ssn;
	private String allergyList = "";
	private JRadioButton rdbtnPenicillin;
	private JRadioButton rdbtnSulfonamides;
	private JRadioButton rdbtnGelatin;
	private JRadioButton rdbtnNeomycin;
	private JRadioButton rdbtnYeast;
	private JTextPane notes;

	private MaskFormatter phoneFormatter;
	private MaskFormatter zipFormatter;
	private MaskFormatter ssnFormatter;
	
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
	
	public void CreateProfilePopUpWithPatient(final Patient patient) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Profile window = new Profile(patient);
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

	public Profile(Patient patient) {
		initialize(patient);
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

		// Formatting masks for input fields
		phoneFormatter = new MaskFormatter("##########");
		phoneFormatter.setValidCharacters("0123456789");
		zipFormatter = new MaskFormatter("#####");
		zipFormatter.setValidCharacters("0123456789");
		ssnFormatter = new MaskFormatter("#########");
		ssnFormatter.setValidCharacters("0123456789");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frmNewPatient.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		// Title Label
		JLabel lblProfileTitle = new JLabel("Patient Profile");
		lblProfileTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProfileTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfileTitle.setBounds(300, 25, 100, 25);
		panel.add(lblProfileTitle);
		
		JLabel FirstNamelbl = new JLabel("* First Name:");
		FirstNamelbl.setVerticalAlignment(SwingConstants.TOP);
		FirstNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		FirstNamelbl.setBounds(50, 75, 75, 20);
		panel.add(FirstNamelbl);
		
		firstName = new JTextField();
		firstName.setColumns(10);
		firstName.setBounds(130, 75, 100, 20);
		panel.add(firstName);
		
		JLabel LastNamelbl = new JLabel("* Last Name:");
		LastNamelbl.setVerticalAlignment(SwingConstants.TOP);
		LastNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		LastNamelbl.setBounds(235, 75, 80, 20);
		panel.add(LastNamelbl);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(325, 75, 100, 20);
		panel.add(lastName);
		
		JLabel MiddleNamelbl = new JLabel("Middle Name:");
		MiddleNamelbl.setVerticalAlignment(SwingConstants.TOP);
		MiddleNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		MiddleNamelbl.setBounds(420, 75, 90, 20);
		panel.add(MiddleNamelbl);
		
		middleName = new JTextField();
		middleName.setColumns(10);
		middleName.setBounds(515, 75, 100, 20);
		panel.add(middleName);
		
		JLabel SSNlbl = new JLabel("* SSN:");
		SSNlbl.setVerticalAlignment(SwingConstants.TOP);
		SSNlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		SSNlbl.setBounds(75, 105, 50, 20);
		panel.add(SSNlbl);
		
		ssn = new JFormattedTextField(ssnFormatter);
		ssn.setColumns(10);
		ssn.setBounds(130, 105, 75, 20);
		panel.add(ssn);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		datePicker.setBounds(new Rectangle(295, 105, 165, 25));
		datePicker.setLocation(new Point(295, 105));
		panel.add(datePicker);
		
		JLabel DOBlbl = new JLabel("* D.O.B:");
		DOBlbl.setVerticalAlignment(SwingConstants.TOP);
		DOBlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		DOBlbl.setBounds(235, 105, 50, 20);
		panel.add(DOBlbl);
		
		JLabel lblSex = new JLabel("* Sex:");
		lblSex.setVerticalAlignment(SwingConstants.TOP);
		lblSex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSex.setBounds(475, 105, 35, 20);
		panel.add(lblSex);
		
		String[] sexOptions = {"Male", "Female"};	
		JComboBox sex = new JComboBox(sexOptions);
		sex.setSelectedIndex(-1);
		sex.setBounds(515, 105, 75, 20);
		panel.add(sex);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(50, 150, 600, 1);
		panel.add(separator1);
		
		JLabel Addresslbl = new JLabel("* Address:");
		Addresslbl.setVerticalAlignment(SwingConstants.TOP);
		Addresslbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Addresslbl.setBounds(50, 175, 75, 20);
		panel.add(Addresslbl);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(130, 175, 200, 20);
		panel.add(address);
		
		JLabel Citylbl = new JLabel("* City:");
		Citylbl.setVerticalAlignment(SwingConstants.TOP);
		Citylbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Citylbl.setBounds(85, 205, 40, 20);
		panel.add(Citylbl);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(130, 205, 150, 20);
		panel.add(city);
		
		JLabel Statelbl = new JLabel("* State:");
		Statelbl.setVerticalAlignment(SwingConstants.TOP);
		Statelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Statelbl.setBounds(275, 205, 50, 20);
		panel.add(Statelbl);
		
		String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
				"LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK",
				"OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		
		state = new JComboBox(states);
		state.setSelectedIndex(-1);
		state.setMaximumRowCount(15);
		state.setBounds(330, 205, 50, 20);
		panel.add(state);
		
		JLabel ZipCodelbl = new JLabel("* Zip Code:");
		ZipCodelbl.setVerticalAlignment(SwingConstants.TOP);
		ZipCodelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		ZipCodelbl.setBounds(385, 205, 60, 20);
		panel.add(ZipCodelbl);
		
		zipCode = new JFormattedTextField(zipFormatter);
		zipCode.setColumns(10);
		zipCode.setBounds(450, 205, 40, 20);
		panel.add(zipCode);
		
		JLabel Phonelbl = new JLabel("* Phone:");
		Phonelbl.setVerticalAlignment(SwingConstants.TOP);
		Phonelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Phonelbl.setBounds(75, 235, 50, 20);
		panel.add(Phonelbl);
		
		phone = new JFormattedTextField(phoneFormatter);
		phone.setColumns(10);
		phone.setBounds(130, 235, 75, 20);
		panel.add(phone);
		
		JLabel altPhonelbl = new JLabel("Alt. Phone:");
		altPhonelbl.setVerticalAlignment(SwingConstants.TOP);
		altPhonelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		altPhonelbl.setBounds(210, 235, 65, 20);
		panel.add(altPhonelbl);
		
		altPhone = new JFormattedTextField(phoneFormatter);
		altPhone.setColumns(10);
		altPhone.setBounds(280, 235, 75, 20);
		panel.add(altPhone);
		
		JLabel Emaillbl = new JLabel("* E-mail:");
		Emaillbl.setVerticalAlignment(SwingConstants.TOP);
		Emaillbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Emaillbl.setBounds(355, 235, 50, 20);
		panel.add(Emaillbl);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(410, 235, 180, 20);
		panel.add(email);
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(50, 280, 600, 1);
		panel.add(separator2);
				
		JLabel lblDrugAllergies = new JLabel("Drug Allergies");
		lblDrugAllergies.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDrugAllergies.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrugAllergies.setBounds(300, 300, 100, 20);
		panel.add(lblDrugAllergies);
		
		JRadioButton rdbtnPenicillin = new JRadioButton("Penicillin");
		rdbtnPenicillin.setBackground(new Color(255, 255, 255));
		rdbtnPenicillin.setBounds(100, 330, 100, 20);
		panel.add(rdbtnPenicillin);
		
		JRadioButton rdbtnSulfonamides = new JRadioButton("Sulfonamides");
		rdbtnSulfonamides.setBackground(Color.WHITE);
		rdbtnSulfonamides.setBounds(200, 330, 110, 20);
		panel.add(rdbtnSulfonamides);
		
		JRadioButton rdbtnGelatin = new JRadioButton("Gelatin");
		rdbtnGelatin.setBackground(Color.WHITE);
		rdbtnGelatin.setBounds(315, 330, 85, 20);
		panel.add(rdbtnGelatin);
		
		JRadioButton rdbtnNeomycin = new JRadioButton("Neomycin");
		rdbtnNeomycin.setBackground(Color.WHITE);
		rdbtnNeomycin.setBounds(400, 330, 100, 20);
		panel.add(rdbtnNeomycin);
		
		JRadioButton rdbtnYeast = new JRadioButton("Yeast");
		rdbtnYeast.setBackground(Color.WHITE);
		rdbtnYeast.setBounds(500, 330, 100, 20);
		panel.add(rdbtnYeast);
		
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(50, 370, 600, 1);
		panel.add(separator3);
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNotes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotes.setBounds(300, 390, 100, 20);
		panel.add(lblNotes);
		
		JTextPane textPaneNotes = new JTextPane();
		textPaneNotes.setBorder(new LineBorder(new Color(0, 0, 0)));
		textPaneNotes.setBackground(new Color(255, 255, 240));
		textPaneNotes.setBounds(75, 420, 550, 120);
		panel.add(textPaneNotes);
		
		JButton createBtn = new JButton("Create");
		createBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createBtn.setBounds(110, 600, 150, 50);
		panel.add(createBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelBtn.setBounds(440, 600, 150, 50);
		panel.add(cancelBtn);
		
		JLabel lblRequired = new JLabel("* Required Fields");
		lblRequired.setBounds(75, 570, 120, 20);
		panel.add(lblRequired);
		
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void initialize(Patient patient) {
		try {
		dao = new DAO();
		
		frmNewPatient = new JFrame();
		frmNewPatient.setResizable(false);
		frmNewPatient.setBounds(100, 100, 700, 700);
		frmNewPatient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// Formatting masks for input fields
		phoneFormatter = new MaskFormatter("##########");
		phoneFormatter.setValidCharacters("0123456789");
		zipFormatter = new MaskFormatter("#####");
		zipFormatter.setValidCharacters("0123456789");
		ssnFormatter = new MaskFormatter("#########");
		ssnFormatter.setValidCharacters("0123456789");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frmNewPatient.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		// Title Label
		JLabel lblProfileTitle = new JLabel("Patient Profile");
		lblProfileTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblProfileTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfileTitle.setBounds(300, 25, 100, 25);
		panel.add(lblProfileTitle);
		
		JLabel FirstNamelbl = new JLabel("* First Name:");
		FirstNamelbl.setVerticalAlignment(SwingConstants.TOP);
		FirstNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		FirstNamelbl.setBounds(50, 75, 75, 20);
		panel.add(FirstNamelbl);
		
		firstName = new JTextField();
		firstName.setColumns(10);
		firstName.setBounds(130, 75, 100, 20);
		panel.add(firstName);
		firstName.setText(patient.getFirstName());
		firstName.setEditable(false);
		
		JLabel LastNamelbl = new JLabel("* Last Name:");
		LastNamelbl.setVerticalAlignment(SwingConstants.TOP);
		LastNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		LastNamelbl.setBounds(235, 75, 80, 20);
		panel.add(LastNamelbl);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(325, 75, 100, 20);
		panel.add(lastName);
		lastName.setText(patient.getLastName());
		lastName.setEditable(false);
		
		JLabel MiddleNamelbl = new JLabel("Middle Name:");
		MiddleNamelbl.setVerticalAlignment(SwingConstants.TOP);
		MiddleNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		MiddleNamelbl.setBounds(420, 75, 90, 20);
		panel.add(MiddleNamelbl);
		
		middleName = new JTextField();
		middleName.setColumns(10);
		middleName.setBounds(515, 75, 100, 20);
		panel.add(middleName);
		middleName.setText(patient.getMiddleName());
		middleName.setEditable(false);
		
		JLabel SSNlbl = new JLabel("* SSN:");
		SSNlbl.setVerticalAlignment(SwingConstants.TOP);
		SSNlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		SSNlbl.setBounds(75, 105, 50, 20);
		panel.add(SSNlbl);
		
		ssn = new JFormattedTextField(ssnFormatter);
		ssn.setColumns(10);
		ssn.setBounds(130, 105, 75, 20);
		panel.add(ssn);
		ssn.setText(patient.getSSN());
		ssn.setEditable(false);
		
		
		DOBfield = new JTextField();
		DOBfield.setBounds(new Rectangle(295, 105, 165, 25));
		DOBfield.setLocation(new Point(295, 105));
		panel.add(DOBfield);
		DOBfield.setText(patient.getDOB().toString());
		DOBfield.setEditable(false);
		
		
		JLabel DOBlbl = new JLabel("* D.O.B:");
		DOBlbl.setVerticalAlignment(SwingConstants.TOP);
		DOBlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		DOBlbl.setBounds(235, 105, 50, 20);
		panel.add(DOBlbl);
		
		JLabel lblSex = new JLabel("* Sex:");
		lblSex.setVerticalAlignment(SwingConstants.TOP);
		lblSex.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSex.setBounds(475, 105, 35, 20);
		panel.add(lblSex);
		
		String[] sexOptions = {"Male", "Female"};	
		JComboBox sex = new JComboBox(sexOptions);
		if("Male".equals(patient.getSex())) {
			sex.setSelectedIndex(0);
		}
		if("Female".equals(patient.getSex())) {
			sex.setSelectedIndex(1);
		}
		sex.setBounds(515, 105, 75, 20);
		panel.add(sex);
		sex.setEnabled(false);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(50, 150, 600, 1);
		panel.add(separator1);
		
		JLabel Addresslbl = new JLabel("* Address:");
		Addresslbl.setVerticalAlignment(SwingConstants.TOP);
		Addresslbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Addresslbl.setBounds(50, 175, 75, 20);
		panel.add(Addresslbl);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(130, 175, 200, 20);
		panel.add(address);
		address.setText(patient.getAddress());
		
		JLabel Citylbl = new JLabel("* City:");
		Citylbl.setVerticalAlignment(SwingConstants.TOP);
		Citylbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Citylbl.setBounds(85, 205, 40, 20);
		panel.add(Citylbl);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(130, 205, 150, 20);
		panel.add(city);
		city.setText(patient.getCity());
		
		JLabel Statelbl = new JLabel("* State:");
		Statelbl.setVerticalAlignment(SwingConstants.TOP);
		Statelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Statelbl.setBounds(275, 205, 50, 20);
		panel.add(Statelbl);
		
		String[] states = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY",
				"LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK",
				"OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
		int patientStateIndex = -1;
		List<String> statesList = Arrays.asList(states);
		for(int i = 0; i < statesList.size(); i++) {
			if(statesList.get(i).equals(patient.getState())) {
				patientStateIndex = i;
				break;
			}
		}
		state = new JComboBox(states);
		state.setSelectedIndex(patientStateIndex);
		state.setMaximumRowCount(15);
		state.setBounds(330, 205, 50, 20);
		panel.add(state);
		
		JLabel ZipCodelbl = new JLabel("* Zip Code:");
		ZipCodelbl.setVerticalAlignment(SwingConstants.TOP);
		ZipCodelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		ZipCodelbl.setBounds(385, 205, 60, 20);
		panel.add(ZipCodelbl);
		
		zipCode = new JFormattedTextField(zipFormatter);
		zipCode.setColumns(10);
		zipCode.setBounds(450, 205, 40, 20);
		panel.add(zipCode);
		zipCode.setText(patient.getZipCode());
		
		JLabel Phonelbl = new JLabel("* Phone:");
		Phonelbl.setVerticalAlignment(SwingConstants.TOP);
		Phonelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Phonelbl.setBounds(75, 235, 50, 20);
		panel.add(Phonelbl);
		
		phone = new JFormattedTextField(phoneFormatter);
		phone.setColumns(10);
		phone.setBounds(130, 235, 75, 20);
		panel.add(phone);
		phone.setText(patient.getPhone());
		
		JLabel altPhonelbl = new JLabel("Alt. Phone:");
		altPhonelbl.setVerticalAlignment(SwingConstants.TOP);
		altPhonelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		altPhonelbl.setBounds(210, 235, 65, 20);
		panel.add(altPhonelbl);
		
		altPhone = new JFormattedTextField(phoneFormatter);
		altPhone.setColumns(10);
		altPhone.setBounds(280, 235, 75, 20);
		panel.add(altPhone);
		altPhone.setText(patient.getAltPhone());
		
		JLabel Emaillbl = new JLabel("* E-mail:");
		Emaillbl.setVerticalAlignment(SwingConstants.TOP);
		Emaillbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Emaillbl.setBounds(355, 235, 50, 20);
		panel.add(Emaillbl);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(410, 235, 180, 20);
		panel.add(email);
		email.setText(patient.getEmail());
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(50, 280, 600, 1);
		panel.add(separator2);
				
		JLabel lblDrugAllergies = new JLabel("Drug Allergies");
		lblDrugAllergies.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDrugAllergies.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrugAllergies.setBounds(300, 300, 100, 20);
		panel.add(lblDrugAllergies);
		
		rdbtnPenicillin = new JRadioButton("Penicillin");
		rdbtnPenicillin.setBackground(new Color(255, 255, 255));
		rdbtnPenicillin.setBounds(100, 330, 100, 20);
		panel.add(rdbtnPenicillin);
		if(patient.getAllergies().contains("Penicillin")) {
			rdbtnPenicillin.setSelected(true);
		}
		
		rdbtnSulfonamides = new JRadioButton("Sulfonamides");
		rdbtnSulfonamides.setBackground(Color.WHITE);
		rdbtnSulfonamides.setBounds(200, 330, 110, 20);
		panel.add(rdbtnSulfonamides);
		if(patient.getAllergies().contains("Sulfonamides")) {
			rdbtnSulfonamides.setSelected(true);
		}
		
		rdbtnGelatin = new JRadioButton("Gelatin");
		rdbtnGelatin.setBackground(Color.WHITE);
		rdbtnGelatin.setBounds(315, 330, 85, 20);
		panel.add(rdbtnGelatin);
		if(patient.getAllergies().contains("Gelatin")) {
			rdbtnGelatin.setSelected(true);
		}
		
		rdbtnNeomycin = new JRadioButton("Neomycin");
		rdbtnNeomycin.setBackground(Color.WHITE);
		rdbtnNeomycin.setBounds(400, 330, 100, 20);
		panel.add(rdbtnNeomycin);
		if(patient.getAllergies().contains("Neomycin")) {
			rdbtnNeomycin.setSelected(true);
		}
		
		rdbtnYeast = new JRadioButton("Yeast");
		rdbtnYeast.setBackground(Color.WHITE);
		rdbtnYeast.setBounds(500, 330, 100, 20);
		panel.add(rdbtnYeast);
		if(patient.getAllergies().contains("Yeast")) {
			rdbtnYeast.setSelected(true);
		}
		
		JSeparator separator3 = new JSeparator();
		separator3.setBounds(50, 370, 600, 1);
		panel.add(separator3);
		
		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNotes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotes.setBounds(300, 390, 100, 20);
		panel.add(lblNotes);
		
		notes = new JTextPane();
		notes.setBorder(new LineBorder(new Color(0, 0, 0)));
		notes.setBackground(new Color(255, 255, 240));
		notes.setBounds(75, 420, 550, 120);
		panel.add(notes);
		notes.setText(patient.getNotes());
		
		JButton createBtn = new JButton("Edit");
		createBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createBtn.setBounds(110, 600, 150, 50);
		panel.add(createBtn);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cancelBtn.setBounds(440, 600, 150, 50);
		panel.add(cancelBtn);
		
		JLabel lblRequired = new JLabel("* Required Fields");
		lblRequired.setBounds(75, 570, 120, 20);
		panel.add(lblRequired);
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNewPatient.dispose();
			}
		});
		
		createBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				generateAllergyList();
				editProfile();
			}
		});
		} catch(SQLException es){
			es.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* The create function below needs to be specifically implemented for the fields contained within the patient
	 * profile.  I don't know if the database is currently set up to handle these, nor do I know what order they
	 * would even be in for so I am leaving it all commented out until we sort that out.
	 */
	
	private void generateAllergyList(){
		boolean isSelected = rdbtnPenicillin.isSelected();
		if (isSelected){
			allergyList += "Penicillin, ";
		}
		isSelected = rdbtnSulfonamides.isSelected();
		if (isSelected){
			allergyList += "Sulfonamides, ";
		}
		isSelected = rdbtnGelatin.isSelected();
		if (isSelected){
			allergyList += "Gelatin, ";
		}
		isSelected = rdbtnNeomycin.isSelected();
		if (isSelected){
			allergyList += "Neomycin, ";
		}
		isSelected = rdbtnYeast.isSelected();
		if (isSelected){
			allergyList += "Yeast, ";
		}
		// Removes last space and comma if a string has been created of any length
		if (allergyList.length() > 0){
			allergyList = allergyList.substring(0,allergyList.length()-2);
		}
	}

	
	public void createProfile()
	{
		
		System.out.println(phone.getText());
		
		try{
			// Checking for empty fields
			if(!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !ssn.getText().isEmpty() && datePicker.getModel().getValue() != null && !address.getText().isEmpty() && !city.getText().isEmpty() && state.getModel().getSelectedItem() != null && !zipCode.getText().isEmpty() && !phone.getText().isEmpty() && !email.getText().isEmpty()){
						dao.setExpectRS(false);
						dao.setquery("INSERT INTO dbo.PatientTable (firstName, lastName, middleName, SSN, DOB, address, city, state, zipCode, phone, altPhone, email, allergies, notes)" +
								"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
						dao.SetParameter(firstName.getText());
						dao.SetParameter(lastName.getText());
						dao.SetParameter(middleName.getText());
						dao.SetParameter(Integer.parseInt(ssn.getText()));
						java.util.Date utilDate = (Date) datePicker.getModel().getValue();
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
						dao.SetParameter(sqlDate);
						dao.SetParameter(address.getText());
						dao.SetParameter(city.getText());
						dao.SetParameter((String) state.getModel().getSelectedItem());
						dao.SetParameter(Integer.parseInt(zipCode.getText()));
						dao.SetParameter(phone.getText());
						dao.SetParameter(altPhone.getText());
						dao.SetParameter(email.getText());
						dao.SetParameter(allergyList);
						dao.SetParameter(notes.getText());
						dao.executeQuery();
						frmNewPatient.dispose();
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
	public void editProfile()
	{

		try{
			// Checking for empty fields
			if(!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !ssn.getText().isEmpty() && !address.getText().isEmpty() && !city.getText().isEmpty() && state.getModel().getSelectedItem() != null && !zipCode.getText().isEmpty() && !phone.getText().isEmpty() && !email.getText().isEmpty()){
						dao.setExpectRS(false);
						dao.setquery("UPDATE dbo.PatientTable SET firstName=?, lastName=?, middleName=?, SSN=?, DOB=?, address=?, city=?, state=?, zipCode=?, phone=?, altPhone=?, email=?, allergies=?, notes=? WHERE lastName = ? AND firstName = ? AND middleName = ?");
						dao.SetParameter(firstName.getText());
						dao.SetParameter(lastName.getText());
						dao.SetParameter(middleName.getText());
						dao.SetParameter(Integer.parseInt(ssn.getText()));
						dao.SetParameter(DOBfield.getText());
						dao.SetParameter(address.getText());
						dao.SetParameter(city.getText());
						dao.SetParameter((String) state.getModel().getSelectedItem());
						dao.SetParameter(Integer.parseInt(zipCode.getText()));
						dao.SetParameter(phone.getText());
						dao.SetParameter(altPhone.getText());
						dao.SetParameter(email.getText());
						dao.SetParameter(allergyList);
						dao.SetParameter(notes.getText());
						dao.SetParameter(lastName.getText());
						dao.SetParameter(firstName.getText());
						dao.SetParameter(middleName.getText());
						dao.executeQuery();			
						frmNewPatient.dispose();
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

