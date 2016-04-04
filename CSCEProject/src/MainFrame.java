import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class MainFrame {

	private JFrame frmMainMenu;


	/**
	 * Launch the application.
	 */
	public static void mainWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmMainMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}
	/*
	public MainFrame(String u){
		String user = u;
		initialize(user);
	}*/

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainMenu = new JFrame();
		frmMainMenu.setResizable(false);
		frmMainMenu.setTitle("Shot Call v0.1");
		frmMainMenu.setBounds(100, 100, 450, 500);
		frmMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);
		panel.setForeground(Color.WHITE);
		frmMainMenu.getContentPane().add(panel, BorderLayout.CENTER);
		
		JButton createAccount = new JButton("Create New Account");
		createAccount.setBounds(50, 300, 150, 50);
		createAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAccount ca;
				ca = new CreateAccount();
				ca.CreateAccountPopUp();
			}
		});
		panel.setLayout(null);
		panel.add(createAccount);
		
		JButton btnReports = new JButton("Reports");
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnReports.setBounds(250, 150, 150, 50);
		panel.add(btnReports);
		
		JButton btnPatientSearch = new JButton("Patient Search");
		btnPatientSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			PatientSearch ps;
			ps = new PatientSearch();
			ps.CreateProfilePopUp();
			}
		});
		btnPatientSearch.setBounds(50, 150, 150, 50);
		panel.add(btnPatientSearch);
		
		JButton btnManageInventory = new JButton("Manage Inventory");
		btnManageInventory.setBounds(250, 225, 150, 50);
		panel.add(btnManageInventory);
		
		JButton btnNewPatient = new JButton("New Patient");
		btnNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile p;
				p = new Profile();
				p.CreateProfilePopUp();
			}
		});
		btnNewPatient.setBounds(50, 225, 150, 50);
		panel.add(btnNewPatient);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame l;
				try{
				l = new LoginFrame();
				l.main(null);
				frmMainMenu.dispose();
				} catch(SQLException ex) {
					ex.printStackTrace();
				}
			}	
		});
		btnLogout.setBounds(300, 425, 100, 25);
		panel.add(btnLogout);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("LogoPlaceholder.png"));
		logo.setBackground(Color.LIGHT_GRAY);
		logo.setBounds(25, 25, 400, 100);
		panel.add(logo);
		
		JLabel lblCurrentUser = new JLabel("");
		lblCurrentUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentUser.setBounds(50, 425, 100, 25);
		panel.add(lblCurrentUser);
	}
}
