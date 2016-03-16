import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import DAO.DAO;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;

import sun.org.mozilla.javascript.internal.json.JsonParser.ParseException;


public class CreateAccount {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;
	private JPasswordField passwordVerify;
	private JTextField userId;
	private JTextField securityQst;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField middleName;
	private JTextField address;
	private JTextField city;
	private JTextField state;
	private JTextField email;
	private JTextField emailVerify;
	private JFormattedTextField zipCode;
	private JFormattedTextField phone;
	private DAO dao;
	private MaskFormatter phoneformatter;
	private MaskFormatter zipformatter;

	/**
	 * Launch the application.
	 */
	public void CreateAccountPopUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount window = new CreateAccount();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		dao = new DAO();
		frame = new JFrame();
		frame.setBounds(100, 100, 458, 627);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		phoneformatter = new MaskFormatter("##########");
		phoneformatter.setValidCharacters("0123456789");
		zipformatter = new MaskFormatter("#####");
		zipformatter.setValidCharacters("0123456789");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Create Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 442, 589);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel Usernamelbl = new JLabel("*Username:");
		Usernamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Usernamelbl.setBounds(33, 47, 75, 14);
		panel.add(Usernamelbl);
		
		username = new JTextField();
		username.setBounds(118, 44, 183, 20);
		panel.add(username);
		username.setColumns(10);
		
		JLabel Passwordlbl = new JLabel("*Password:");
		Passwordlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Passwordlbl.setBounds(33, 77, 75, 14);
		panel.add(Passwordlbl);
		
		password = new JPasswordField();
		password.setBounds(118, 75, 183, 20);
		panel.add(password);
		
		JLabel PasswordVerifylbl = new JLabel("*Password(Verify):");
		PasswordVerifylbl.setHorizontalAlignment(SwingConstants.RIGHT);
		PasswordVerifylbl.setBounds(0, 109, 108, 14);
		panel.add(PasswordVerifylbl);
		
		passwordVerify = new JPasswordField();
		passwordVerify.setBounds(118, 106, 183, 20);
		panel.add(passwordVerify);
		
		userId = new JTextField();
		userId.setColumns(10);
		userId.setBounds(118, 137, 183, 20);
		panel.add(userId);
		
		JLabel userIdlbl = new JLabel("*Account ID:");
		userIdlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		userIdlbl.setBounds(33, 140, 75, 14);
		panel.add(userIdlbl);
		
		securityQst = new JTextField();
		securityQst.setColumns(10);
		securityQst.setBounds(118, 191, 183, 20);
		panel.add(securityQst);
		
		JLabel lblAnswer = new JLabel("*Answer:");
		lblAnswer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnswer.setBounds(50, 194, 58, 14);
		panel.add(lblAnswer);
		
		JLabel SecurityQuestionlbl = new JLabel("What is your favorite animal?");
		SecurityQuestionlbl.setBounds(118, 168, 183, 14);
		panel.add(SecurityQuestionlbl);
		
		firstName = new JTextField();
		firstName.setColumns(10);
		firstName.setBounds(118, 222, 183, 20);
		panel.add(firstName);
		
		JLabel FirstNamelbl = new JLabel("*First Name:");
		FirstNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		FirstNamelbl.setBounds(33, 225, 75, 14);
		panel.add(FirstNamelbl);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(118, 253, 183, 20);
		panel.add(lastName);
		
		JLabel LastNamelbl = new JLabel("*Last Name:");
		LastNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		LastNamelbl.setBounds(33, 256, 75, 14);
		panel.add(LastNamelbl);
		
		middleName = new JTextField();
		middleName.setColumns(10);
		middleName.setBounds(118, 284, 183, 20);
		panel.add(middleName);
		
		JLabel MiddleNamelbl = new JLabel("Middle Name:");
		MiddleNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		MiddleNamelbl.setBounds(10, 287, 98, 14);
		panel.add(MiddleNamelbl);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(118, 315, 183, 20);
		panel.add(address);
		
		JLabel Addresslbl = new JLabel("*Address:");
		Addresslbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Addresslbl.setBounds(33, 318, 75, 14);
		panel.add(Addresslbl);
		
		city = new JTextField();
		city.setColumns(10);
		city.setBounds(118, 346, 183, 20);
		panel.add(city);
		
		JLabel Citylbl = new JLabel("*City:");
		Citylbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Citylbl.setBounds(33, 349, 75, 14);
		panel.add(Citylbl);
		
		state = new JTextField();
		state.setColumns(10);
		state.setBounds(118, 377, 183, 20);
		panel.add(state);
		
		JLabel Statelbl = new JLabel("*State:");
		Statelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Statelbl.setBounds(33, 380, 75, 14);
		panel.add(Statelbl);
		
		JLabel ZipCodelbl = new JLabel("*Zip Code:");
		ZipCodelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		ZipCodelbl.setBounds(33, 411, 75, 14);
		panel.add(ZipCodelbl);
		
		JLabel Phonelbl = new JLabel("Phone:");
		Phonelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Phonelbl.setBounds(33, 442, 75, 14);
		panel.add(Phonelbl);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(118, 470, 183, 20);
		panel.add(email);
		
		JLabel Emaillbl = new JLabel("*Email:");
		Emaillbl.setHorizontalAlignment(SwingConstants.RIGHT);
		Emaillbl.setBounds(33, 473, 75, 14);
		panel.add(Emaillbl);
		
		emailVerify = new JTextField();
		emailVerify.setColumns(10);
		emailVerify.setBounds(118, 501, 183, 20);
		panel.add(emailVerify);
		
		JLabel EmailVerifylbl = new JLabel("*Email(Verify):");
		EmailVerifylbl.setHorizontalAlignment(SwingConstants.RIGHT);
		EmailVerifylbl.setBounds(10, 504, 98, 14);
		panel.add(EmailVerifylbl);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(210, 540, 139, 38);
		panel.add(cancelBtn);
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		
		JButton createBtn = new JButton("Create");
		createBtn.setBounds(50, 540, 139, 38);
		panel.add(createBtn);
		
		JLabel lblRequired = new JLabel("Required = *");
		lblRequired.setBounds(50, 22, 70, 14);
		panel.add(lblRequired);
		
		
		phone = new JFormattedTextField(phoneformatter);
		phone.setBounds(118, 439, 183, 20);
		panel.add(phone);
		
		zipCode = new JFormattedTextField(zipformatter);
		zipCode.setBounds(118, 408, 183, 20);
		panel.add(zipCode);
		
		createBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				createFunction();
			}
		});
		} catch(SQLException es){
			es.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void createFunction()
	{
		try{
			// Checking for empty fields
			if(!username.getText().isEmpty() && !String.valueOf(password.getPassword()).isEmpty() && !userId.getText().isEmpty() && !securityQst.getText().isEmpty() && !lastName.getText().isEmpty() && !firstName.getText().isEmpty() && !address.getText().isEmpty() && !city.getText().isEmpty() && !state.getText().isEmpty() && !zipCode.getText().isEmpty() && !email.getText().isEmpty() && !emailVerify.getText().isEmpty()){
				if(verifyFields(String.valueOf(password.getPassword()),String.valueOf(passwordVerify.getPassword()))) {
					if(verifyFields(email.getText(), emailVerify.getText())){
						dao.setExpectRS(false);
						dao.setquery("INSERT INTO dbo.LoginPage (username, password, userid, securityQst, lastName, firstName, middleName, address, city, state, zipCode, phone, eMail)" +
								"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
						dao.SetParameter(username.getText());
						dao.SetParameter(String.valueOf(password.getPassword()));
						dao.SetParameter(Integer.parseInt(userId.getText()));
						dao.SetParameter(securityQst.getText());
						dao.SetParameter(lastName.getText());
						dao.SetParameter(firstName.getText());
						dao.SetParameter(middleName.getText());
						dao.SetParameter(address.getText());
						dao.SetParameter(city.getText());
						dao.SetParameter(state.getText());
						dao.SetParameter(Integer.parseInt(zipCode.getText()));
						dao.SetParameter(phone.getText());
						dao.SetParameter(email.getText());
						
						dao.executeQuery();
						}
					else
						JOptionPane.showMessageDialog(null, "Emails Don't Match", "Make emails match.", JOptionPane.ERROR_MESSAGE);
				}
				else
					JOptionPane.showMessageDialog(null, "Passwords Don't Match", "Make password's match", JOptionPane.ERROR_MESSAGE);	
			}
			else{
				//FIELDS EMPTY M8
				JOptionPane.showMessageDialog(null, "Fields Incomplete", "Fill in empty feilds.", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean verifyFields(String field1, String field2)
	{
		if(field1.equals(field2))
			return true;
		else
			return false;
	}
}
