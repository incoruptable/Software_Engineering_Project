import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import DAO.DAO;


public class ForgottenPassword {

	private DAO dao;
	private JFrame frame;
	private JPasswordField securityAnswerField;
	private JPasswordField newPasswordField;
	private JPasswordField confirmPasswordField;
	private JButton enterBtn;
	private JLabel usernamelbl;
	private JTextField username;

	/**
	 * Launch the application.
	 */
	public static void popUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgottenPassword window = new ForgottenPassword();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public ForgottenPassword() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		dao = new DAO();
		frame = new JFrame();
		frame.setBounds(100, 100, 542, 408);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel securityQuestionlbl = new JLabel("Security Answer:");
		securityQuestionlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		securityQuestionlbl.setFont(new Font("Arial", Font.PLAIN, 13));
		securityQuestionlbl.setBounds(48, 151, 117, 14);
		frame.getContentPane().add(securityQuestionlbl);
		
		JLabel newPasswordlbl = new JLabel("New Password:");
		newPasswordlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		newPasswordlbl.setFont(new Font("Arial", Font.PLAIN, 13));
		newPasswordlbl.setBounds(75, 202, 90, 14);
		frame.getContentPane().add(newPasswordlbl);
		
		JLabel confirmPasswordlbl = new JLabel("Confirm Password:");
		confirmPasswordlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		confirmPasswordlbl.setFont(new Font("Arial", Font.PLAIN, 13));
		confirmPasswordlbl.setBounds(48, 253, 117, 14);
		frame.getContentPane().add(confirmPasswordlbl);
		
		securityAnswerField = new JPasswordField();
		securityAnswerField.setBounds(175, 139, 264, 40);
		frame.getContentPane().add(securityAnswerField);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(175, 190, 264, 40);
		frame.getContentPane().add(newPasswordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(175, 241, 264, 40);
		frame.getContentPane().add(confirmPasswordField);
		
		enterBtn = new JButton("Change Password");
		enterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs;
				try {
					if(username.getText() != null && String.valueOf(securityAnswerField.getPassword()) != null && String.valueOf(newPasswordField.getPassword()) != null && String.valueOf(confirmPasswordField.getPassword()) != null)
					{
							dao.setquery("SELECT Username, securityQst  FROM dbo.LoginPage WHERE Username = ?");
							dao.SetParameter(username.getText());
							dao.setExpectRS(true);
							rs = dao.executeQuery();
							while(rs.next()){
								if(rs.getString(2).equals(String.valueOf(securityAnswerField.getPassword())))
								{
									if(String.valueOf(newPasswordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword()))){
										dao.setquery("UPDATE dbo.LoginPage SET Password = ? WHERE Username = ?");
										dao.SetParameter(String.valueOf(newPasswordField.getPassword()));
										dao.SetParameter(username.getText());
										dao.setExpectRS(false);
										dao.executeQuery();
										
										JOptionPane.showMessageDialog(null, "Password Changed Successfully", "", JOptionPane.INFORMATION_MESSAGE);
										frame.dispose();
									}
									else{
										JOptionPane.showMessageDialog(null, "Passwords Don't Match", "Correct the new password and confirm password to match.", JOptionPane.ERROR_MESSAGE);
									}
								}
								else{
									JOptionPane.showMessageDialog(null, "Security Question Incorrect", "Try again.", JOptionPane.ERROR_MESSAGE);
								}
							}
					}
					else{
						JOptionPane.showMessageDialog(null, "Fields Incomplete", "Fill in empty feilds.", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		enterBtn.setBounds(213, 303, 147, 40);
		frame.getContentPane().add(enterBtn);
		
		usernamelbl = new JLabel("Username:");
		usernamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		usernamelbl.setFont(new Font("Arial", Font.PLAIN, 13));
		usernamelbl.setBounds(75, 75, 85, 14);
		frame.getContentPane().add(usernamelbl);
		
		username = new JTextField();
		username.setBounds(175, 63, 264, 40);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("What is your favorite animal?");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(175, 114, 323, 14);
		frame.getContentPane().add(lblNewLabel);
	}
}
