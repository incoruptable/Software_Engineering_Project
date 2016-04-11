import DAO.DAO;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.UIManager;
import java.awt.Font;


public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField passWord;
	private final DAO dao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public LoginFrame() throws SQLException {
		
		dao = new DAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 320);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setBackground(Color.LIGHT_GRAY);
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JPanel sign_in = new JPanel();
		sign_in.setBackground(Color.WHITE);
		sign_in.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sign in", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoPlaceholder.png"));
		logo.setBackground(Color.LIGHT_GRAY);
		logo.setBounds(90, 15, 200, 50);
		panel.add(logo);

		sign_in.setBounds(25, 80, 335, 180);
		panel.add(sign_in);
		sign_in.setLayout(null);
		
		userName = new JTextField();
		userName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userName.setBounds(130, 35, 170, 35);
		sign_in.add(userName);
		userName.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(25, 35, 100, 35);
		sign_in.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(25, 75, 100, 35);
		sign_in.add(lblPassword);
		
		// Password field with associated action listener
		passWord = new JPasswordField();
		passWord.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passWord.setBounds(130, 75, 170, 35);
		sign_in.add(passWord);
		passWord.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				logIn();
			}
		});
		
		// Login Button with associated action listener
		JButton Login = new JButton("Log in");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logIn();		
			}
		});
		Login.setBounds(25, 125, 110, 30);
		sign_in.add(Login);
		
		JButton forgotPassword = new JButton("Forgot Password?");
		forgotPassword.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)	{
				ForgottenPassword fgpw;
				try {
					fgpw = new ForgottenPassword();
					fgpw.popUp();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		forgotPassword.setBounds(160, 125, 140, 30);
		sign_in.add(forgotPassword);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void logIn(){
		ResultSet rs;
		try {
			dao.setquery("SELECT * FROM dbo.LoginPage WHERE username = ? AND password = ?");
			dao.SetParameter(userName.getText());
			dao.SetParameter(String.valueOf(passWord.getPassword()));
			dao.setExpectRS(true);
			rs = dao.executeQuery();
			if(rs.next()) {
				do{
					System.out.println("Inside while loop");
						if(rs.getString(2).equals(String.valueOf(passWord.getPassword()))){
							System.out.println("Account accepted");
							JOptionPane.showMessageDialog(null, "Account Accepted", "", JOptionPane.INFORMATION_MESSAGE);
							MainFrame mainframe;
							mainframe = new MainFrame();
							mainframe.mainWindow();
							dispose();
							return;
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Username or Password Incorrect", "Please try again.", JOptionPane.ERROR_MESSAGE);
						}
						
				} while(rs.next());
			}
			else{
				JOptionPane.showMessageDialog(null, "Username or Password Incorrect", "Please try again.", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

