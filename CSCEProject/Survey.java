import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

import DAO.DAO;

public class Survey {
	
	// Name of shot for survey
	private String shotName;
	// Question count
	private int qCount;
	// Current patient
	private Patient patient;
	private String[] shotList;
	private ArrayList<String> shotNames;
	private ArrayList<Shot> shots;
	private Shot selectedShot;
	
	private JFrame frmSurvey;
	private DAO dao;
	
	/**
	 * Launch the application
	 */
	public void CreateProfilePopUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					Survey window = new Survey(patient);
					window.frmSurvey.setVisible(true);
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application
	 */
	public Survey(Patient patient){
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try {
		dao = new DAO();
		shots = new ArrayList<Shot>();
		shotNames = new ArrayList<String>();
		
		this.getShots();
		
		frmSurvey = new JFrame();
		frmSurvey.setResizable(false);
		frmSurvey.setBounds(100, 100, 500, 200);
		frmSurvey.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frmSurvey.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox invComboBox = new JComboBox(shotNames.toArray(new String[shotNames.size()]));
		invComboBox.setEditable(true);
		invComboBox.setSelectedItem("Select Shot");
		invComboBox.setEditable(false);
		invComboBox.setBounds(50, 25, 200, 20);
		panel.add(invComboBox);
		invComboBox.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						JComboBox comboBox = (JComboBox)e.getSource();
						selectedShot = new Shot();
						selectedShot = shots.get(comboBox.getSelectedIndex());
						System.out.print(comboBox.getSelectedIndex());
						System.out.print(shots.size());
						if(selectedShot != null) {
							/*
							manufacturer.setText(selectedShot.getManufacturer());
							vendor.setText(selectedShot.getVendor());
							quantity.setText(String.valueOf(selectedShot.getQuantity()));
							notes.setText(selectedShot.getNotes());
							panel.add(updateBtn);
							frmInventory.repaint();
							*/
						}
					}
				});
		
		
		JButton btnGenerate = new JButton("Start Survey");
		btnGenerate.setBounds(265, 25, 100, 20);
		panel.add(btnGenerate);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(375, 25, 75, 20);
		panel.add(btnExit);
				
		btnExit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				frmSurvey.dispose();
			}
		});
				
		} catch(SQLException es){
			es.printStackTrace();
		}
	}
	
	
	private void getShots() throws SQLException {
		dao.setquery("SELECT * FROM dbo.SHOTS WHERE active = 1");
		dao.setExpectRS(true);
		ResultSet rs = dao.executeQuery();
		while(rs.next()){
			Shot shot = new Shot();
			shot.setShotID(rs.getString(1));
			shot.setManufacturer(rs.getString(2));
			shot.setManufacturerPN(rs.getString(3));
			shot.setVendor(rs.getString(4));
			shot.setVendorPN(rs.getString(5));
			shot.setCommonName(rs.getString(6));
			shot.setQuantity(rs.getInt(7));
			shot.setParLevel(rs.getInt(8));
			shot.setReorderLevel(rs.getInt(9));
			shot.setPhaseOut(rs.getBoolean(10));
			shot.setActive(rs.getBoolean(11));
			shot.setNotes(rs.getString(12));
			
			shots.add(shot);
			shotNames.add(shot.getCommonName());
		}
		
	}
}
