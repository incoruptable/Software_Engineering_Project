import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;

import DAO.DAO;

public class Survey {
	
	private String shotName = new String();
	// Question count
	private int qCount;
	private int sCount;
	// Current patient
	private Patient patient;
	private ArrayList<String> shotNames;
	private ArrayList<Shot> shots;
	private ArrayList<JLabel> questions;
	private Shot selectedShot;
	
	private JFrame frmSurvey;
	private JPanel panel;
	private JButton btnExit;
	private JButton btnGenerate;
	private JButton btnComplete;
	private JButton btnCancel;
	private DAO dao;
	private ArrayList<YesorNoBox> yesOrNoBoxes;
	
	
	class YesorNoBox {
		
		private ArrayList<JRadioButton> buttons;
		private JRadioButton yesButton;
		private JRadioButton noButton;
		
		public YesorNoBox(int height){
			buttons = new ArrayList<JRadioButton>();
			yesButton = new JRadioButton("Yes");
			yesButton.setBackground(Color.WHITE);
			yesButton.setBounds(10, height + 50, 100, 20);
			yesButton.setSelected(true);
			yesButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(yesButton.isSelected()){
						noButton.setSelected(false);
					}
					else
						yesButton.setSelected(true);
				}
				
			});
			
			noButton = new JRadioButton("No");
			noButton.setBackground(Color.WHITE);
			noButton.setBounds(110, height + 50, 100, 20);
			noButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(noButton.isSelected()){
						yesButton.setSelected(false);
					}
					else
						noButton.setSelected(true);
				}
				
			});
			buttons.add(yesButton);
			buttons.add(noButton);
		}
		
		public JRadioButton getNoButton(){
			return buttons.get(1);
		}
		
		public JRadioButton getYesButton(){
			return buttons.get(0);
		}
	};
	
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
	public Survey(Patient p){
		patient = p;
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
		questions = new ArrayList<JLabel>();
		yesOrNoBoxes = new ArrayList<YesorNoBox>();
		
		this.setQCount();
		this.getShots();
		
		frmSurvey = new JFrame();
		frmSurvey.setTitle("Patient: " + patient.getFirstName() + " " + patient.getLastName() + "  (" + patient.getSSN() + ")");
		frmSurvey.setResizable(false);
		frmSurvey.setBounds(100, 100, 500, 200);
		frmSurvey.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frmSurvey.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox invComboBox = new JComboBox(shotNames.toArray(new String[shotNames.size()]));
		invComboBox.setEditable(true);
		invComboBox.setSelectedItem("Select Shot (" + qCount + ")");
		invComboBox.setEditable(false);
		invComboBox.setBounds(37, 25, 200, 20);
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
							try {
							getQuestions(selectedShot.getCommonName());
							} catch (Exception eq){
								eq.printStackTrace();
							}
						}
					}
				});
		
		
		btnGenerate = new JButton("Start Survey");
		btnGenerate.setBounds(252, 25, 125, 20);
		panel.add(btnGenerate);
		
		btnExit = new JButton("Exit");
		btnExit.setBounds(387, 25, 75, 20);
		panel.add(btnExit);
		
		btnComplete = new JButton("Complete");

		btnCancel = new JButton("Cancel");

		
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				generateQBlock();
				} catch (Exception e){
					e.printStackTrace();
				}
				btnGenerate.setEnabled(false);
				btnExit.setEnabled(false);
				resizeFrame(sCount);
			}
		});
		
		btnExit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				frmSurvey.dispose();
			}
		});
		
		btnComplete.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				// This will make all the checks for whether a shot should be administered (Check Allergies)
			}
		});
		
		btnCancel.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				for(JLabel question: questions){
					panel.remove(question);
				}
				for(YesorNoBox box: yesOrNoBoxes){
					panel.remove(box.getNoButton());
					panel.remove(box.getYesButton());
				}
				panel.remove(btnComplete);
				panel.remove(btnCancel);
				resizeFrame(0);
				frmSurvey.repaint();
				btnGenerate.setEnabled(true);
				btnExit.setEnabled(true);
			}
		});
				
		} catch(SQLException es){
			es.printStackTrace();
		}
	}
	
	private void resizeFrame(int c){
		frmSurvey.setBounds(100, 100, 500, 200 + 100 * c);
		frmSurvey.repaint();
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
	
	// Gets and sets local total question count
	private void setQCount() throws SQLException{
		dao.setquery("SELECT COUNT(*) FROM dbo.SHOTS");
		dao.setExpectRS(true);
		ResultSet rs = dao.executeQuery();
		while(rs.next()){
			qCount = rs.getInt(1);
		}
	}
	
	// Gets specific number of questions for currently selected shot
	private void getQuestions(String q) throws SQLException{
		dao.setquery("SELECT numQuestions FROM dbo.SHOTS WHERE commonName = ?");
		dao.SetParameter(q);
		dao.setExpectRS(true);
		ResultSet srs = dao.executeQuery();
		
		while(srs.next()){
			sCount = srs.getInt(1);
		}
		// sets current shot name
		shotName = selectedShot.getCommonName();

		// Updates Exit button to show functionality working currently
		btnExit.setText("Exit (" + sCount + ")");
	}
	
	
	private void generateQBlock() throws SQLException{
		int offset = 0;
		String qSet = new String();	

		dao.setquery("SELECT questions FROM dbo.SHOTS WHERE commonName = ?");
		dao.SetParameter(shotName);
		dao.setExpectRS(true);
		ResultSet rs = dao.executeQuery();
		
		while(rs.next()){
			qSet = rs.getString(1);
		}
				
		if(!qSet.isEmpty()){	
			for (int i = 1; i <= qCount; i++){
				String qNum = new String();
				qNum = "" + String.valueOf(i);
				
				// Checking if current question number is included
				if(qSet.contains(qNum)) {
					String questionText = new String();
					dao.setquery("SELECT qText FROM dbo.SURVEY_QUESTIONS WHERE questionID = ?");
					dao.SetParameter(i);
					dao.setExpectRS(true);
					ResultSet qrs = dao.executeQuery();
					while(qrs.next()){
						questionText = qrs.getString(1);
					}
					
					JLabel question = new JLabel("<html>" + questionText + "</html>");
					question.setFont(new Font("Tahoma", Font.BOLD, 12));
					question.setHorizontalAlignment(SwingConstants.CENTER);
					question.setBounds(10, 75 + offset, 400 , 45);
					questions.add(question);
					YesorNoBox box = new YesorNoBox(75 + offset);
					yesOrNoBoxes.add(box);
					panel.add(box.getYesButton());
					panel.add(box.getNoButton());
					panel.add(question);
					
					offset += 75;
				}
			}
			btnComplete.setBounds(252, yesOrNoBoxes.get(yesOrNoBoxes.size()-1).getYesButton().getY() + 50, 125, 20);
			btnCancel.setBounds(387, yesOrNoBoxes.get(yesOrNoBoxes.size()-1).getYesButton().getY() + 50, 75, 20);
			panel.add(btnComplete);
			panel.add(btnCancel);
		
		}
	} 	

}

