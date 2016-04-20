import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

import DAO.DAO;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.swing.text.PlainDocument;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class Inventory {

	private JFrame frmInventory;
	private DAO dao;  // Won't do anything until we implement the actual server queries.
	// locations will need to be dynamically created for Deliverable 3, this is for demo purpose
	private ArrayList<String> shotNames;
	private ArrayList<Shot> shots;
	private Shot selectedShot;
	private JTextField quantity;
	private JLabel manufacturer;
	private JLabel vendor;
	private JTextPane notes;
	private JButton updateBtn;
	private JPanel panel;
	/**
	 * Launch the application.
	 */
	public void CreateProfilePopUp() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory window = new Inventory();
					window.frmInventory.setVisible(true);
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
	public Inventory() {
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
		
		frmInventory = new JFrame();
		frmInventory.setResizable(false);
		frmInventory.setBounds(100, 100, 500, 400);
		frmInventory.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		frmInventory.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox invComboBox = new JComboBox(shotNames.toArray(new String[shotNames.size()]));
		invComboBox.setEditable(true);
		invComboBox.setSelectedItem("Select a Shot");
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
							manufacturer.setText(selectedShot.getManufacturer());
							vendor.setText(selectedShot.getVendor());
							quantity.setText(String.valueOf(selectedShot.getQuantity()));
							notes.setText(selectedShot.getNotes());
							panel.add(updateBtn);
							frmInventory.repaint();
						}
					}
				});
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(375, 25, 75, 20);
		panel.add(btnExit);
		
		JLabel lblManufacturer = new JLabel("Manufacturer:");
		lblManufacturer.setHorizontalAlignment(SwingConstants.RIGHT);
		lblManufacturer.setBounds(50, 60, 85, 20);
		panel.add(lblManufacturer);
		
		manufacturer = new JLabel("");
		manufacturer.setBounds(140, 60, 310, 20);
		panel.add(manufacturer);
		
		JLabel lblVendor = new JLabel("Vendor:");
		lblVendor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVendor.setBounds(50, 85, 85, 20);
		panel.add(lblVendor);
		
		vendor = new JLabel("");
		vendor.setBounds(140, 85, 310, 20);
		panel.add(vendor);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setHorizontalAlignment(SwingConstants.TRAILING);
		lblQuantity.setBounds(50, 110, 85, 20);
		panel.add(lblQuantity);
		
		quantity = new JTextField();
		quantity.setBounds(140, 110, 80, 20);
		panel.add(quantity);
		quantity.setColumns(10);
		
		PlainDocument doc = (PlainDocument) quantity.getDocument();
		doc.setDocumentFilter(new IntegerFilter());
		

		JLabel lblNotes = new JLabel("Notes");
		lblNotes.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotes.setBounds(50, 150, 400, 20);
		panel.add(lblNotes);
		
		notes = new JTextPane();
		notes.setBorder(new LineBorder(new Color(0, 0, 0)));
		notes.setBackground(new Color(255, 255, 240));
		notes.setBounds(50, 170, 400, 100);
		panel.add(notes);
		
		updateBtn = new JButton("Update");
		updateBtn.setBounds(200, 300, 100, 40);
		
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					updateShot();
					frmInventory.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnExit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {
				frmInventory.dispose();
			}
		});
		
		
				
		} catch(SQLException es){
			es.printStackTrace();
		}
		
	}
	

	// Retrieves the list of shots from the database
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
	
	private void updateShot() throws SQLException {
		dao.setquery("UPDATE dbo.SHOTS SET qty = ?, notes = ? WHERE shotID = ?");
		dao.SetParameter(Integer.parseInt(quantity.getText()));
		dao.SetParameter(notes.getText());
		dao.SetParameter(selectedShot.getShotID());
		dao.setExpectRS(false);
		dao.executeQuery();
	}
}


