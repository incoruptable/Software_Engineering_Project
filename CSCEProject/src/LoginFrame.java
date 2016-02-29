import java.awt.BorderLayout;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.awt.EventQueue;
import java.awt.Font;
import DAO.DAO;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Insets;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField passWord;

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
		
		DAO dao = new DAO();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panel.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel.setLayout(null);
		
		JPanel sign_in = new JPanel();
		sign_in.setBackground(Color.WHITE);
		sign_in.setBorder(new TitledBorder(null, "Sign in", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		sign_in.setBounds(26, 13, 454, 274);
		panel.add(sign_in);
		sign_in.setLayout(null);
		
		userName = new JTextField();
		userName.setBounds(224, 76, 143, 32);
		sign_in.add(userName);
		userName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(112, 76, 100, 32);
		sign_in.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(112, 140, 100, 32);
		sign_in.add(lblPassword);
		
		passWord = new JPasswordField();
		passWord.setBounds(224, 145, 143, 32);
		sign_in.add(passWord);
		
		JButton Login = new JButton("Log in");
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultSet rs;
				try {
					dao.setquery("SELECT password FROM dbo.LoginPage WHERE username = ?");
					dao.SetParameter(userName.getText());
					rs = dao.executeQuery();
					while(rs.next()){
							if(rs.getString(1).equals(passWord.getText()))
								System.out.println("Account accepted");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Incorrect Password", "Please Login again", JOptionPane.ERROR_MESSAGE);	
			
			}
		});
		Login.setBounds(255, 206, 112, 32);
		sign_in.add(Login);
		contentPane.setLayout(gl_contentPane);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}

