package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Bashekim;
import Model.Clinic;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;
public class BashekimGUI extends JFrame {
	
	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JPasswordField fld_dPass;
	private JTextField fld_doktorID;
	private JTable table_doktor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		
		//Doctor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC No";
		colDoctorName[3] = "Şifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		 for(int i=0;i<bashekim.getDoctorList().size();i++) {
		 		doctorData[0] = bashekim.getDoctorList().get(i).getId();
		 		doctorData[1] = bashekim.getDoctorList().get(i).getName();
		 		doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
		 		doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
		 		doctorModel.addRow(doctorData);
		 }	
			
		 //Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Poliklinik Adı";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		
		//Worker Model
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0]= "ID" ;
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];
		
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoş Geldiniz , Sayın " + bashekim.getName());
		lblNewLabel.setBounds(10, 10, 307, 42);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(588, 10, 127, 26);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 105, 716, 348);
		w_pane.add(w_tab);
		
		JPanel w_doktor = new JPanel();
		w_tab.addTab("Doktor Yönetimi", null, w_doktor, null);
		w_doktor.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad");
		label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		label.setBounds(531, 0, 142, 26);
		w_doktor.add(label);
		
		fld_dName = new JTextField();
		fld_dName.setBounds(531, 29, 142, 26);
		w_doktor.add(fld_dName);
		fld_dName.setColumns(10);
		
		JLabel label_1 = new JLabel("T.C. No");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		label_1.setBounds(531, 56, 142, 26);
		w_doktor.add(label_1);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(531, 81, 142, 26);
		w_doktor.add(fld_dTcno);
		
		JLabel label_2 = new JLabel("Şifre");
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		label_2.setBounds(531, 109, 142, 26);
		w_doktor.add(label_2);
		
		fld_dPass = new JPasswordField();
		fld_dPass.setBounds(531, 136, 142, 26);
		w_doktor.add(fld_dPass);
		
		JButton btnNewButton_1 = new JButton("Ekle");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fld_dName.getText().length()== 0 || fld_dPass.getText().length()==0 || fld_dTcno.getText().length()==0) {
					Helper.showMsg("fill");				
				} else {
					try {
						boolean control = bashekim.addDoctor(fld_dTcno.getText(), fld_dPass.getText(), fld_dName.getText());
						if(control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btnNewButton_1.setBounds(531, 172, 142, 26);
		w_doktor.add(btnNewButton_1);
		
		JLabel lblKullancId = new JLabel("Kullanıcı ID");
		lblKullancId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblKullancId.setBounds(531, 202, 142, 26);
		w_doktor.add(lblKullancId);
		
		fld_doktorID = new JTextField();
		fld_doktorID.setColumns(10);
		fld_doktorID.setBounds(531, 229, 142, 26);
		w_doktor.add(fld_doktorID);
		
		JButton btn_delDoctor = new JButton("Sil");
		btn_delDoctor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fld_doktorID.getText().length()== 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz ! ");
				} else {
					if(Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doktorID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if(control) {
								Helper.showMsg("success");
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btn_delDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_delDoctor.setBounds(531, 265, 142, 26);
		w_doktor.add(btn_delDoctor);
		
		JScrollPane w_scrollDoktor = new JScrollPane();
		w_scrollDoktor.setBounds(10, 10, 511, 311);
		w_doktor.add(w_scrollDoktor);
		
		table_doktor = new JTable(doctorModel);
		w_scrollDoktor.setViewportView(table_doktor);
		

		table_doktor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doktorID.setText(table_doktor.getValueAt(table_doktor.getSelectedRow(),0).toString());
					} catch(Exception e1){
					
					}
			}
			
		});
		
		
		table_doktor.getModel().addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()== TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_doktor.getValueAt(table_doktor.getSelectedRow(), 0).toString());
					String selectName = (table_doktor.getValueAt(table_doktor.getSelectedRow(), 1).toString());
					String selectTcno = (table_doktor.getValueAt(table_doktor.getSelectedRow(), 2).toString());
					String selectPass = (table_doktor.getValueAt(table_doktor.getSelectedRow(), 3).toString());
					
					try {
						boolean control = bashekim.updateDoctor(selectID, selectTcno, selectPass,selectName);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JPanel w_clinic = new JPanel();
		w_tab.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 10, 259, 311);
		w_clinic.add(w_scrollClinic);
		
		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override 
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		});
		
		deleteMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if(clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow,selectedRow);
			}
		});
		w_scrollClinic.setViewportView(table_clinic);
		
		
		JLabel lblPoliklinikAd = new JLabel("Poliklinik Adı");
		lblPoliklinikAd.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblPoliklinikAd.setBounds(279, 10, 153, 26);
		w_clinic.add(lblPoliklinikAd);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(279, 39, 153, 26);
		w_clinic.add(fld_clinicName);
		
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_clinicName.getText().length()==0) {
					Helper.showMsg("fill");
				} else {
					try {
						if(clinic.addClinicr(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addClinic.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_addClinic.setBounds(279, 75, 153, 40);
		w_clinic.add(btn_addClinic);
		
		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(442, 10, 259, 301);
		w_clinic.add(w_scrollWorker);
		
		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(279, 232, 153, 34);
		for(int i = 0 ; i < bashekim.getDoctorList().size() ; i++) {
			select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId() , bashekim.getDoctorList().get(i).getName()));			
		}
		select_doctor.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem() ;
			System.out.println(item.getKey() + " : " + item.getValue());
		});
		w_clinic.add(select_doctor);
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if(selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if(control) {
							Helper.showMsg("success");
							DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
							clearModel.setRowCount(0);
							for(int i=0;i<bashekim.getClinicDoctorList(selClinicID).size();i++) {
								workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
							table_worker.setModel(workerModel);
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}
		});
		btn_addWorker.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_addWorker.setBounds(279, 276, 153, 40);
		w_clinic.add(btn_addWorker);
		
		JLabel lblPoliklinikAd_1 = new JLabel("Poliklinik Adı");
		lblPoliklinikAd_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblPoliklinikAd_1.setBounds(279, 139, 153, 26);
		w_clinic.add(lblPoliklinikAd_1);
		
		JButton btn_workerSelect = new JButton("Seç");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if(selRow>=0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i=0;i<bashekim.getClinicDoctorList(selClinicID).size();i++) {
							workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				}else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz !");
				}
			}
		});
		btn_workerSelect.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		btn_workerSelect.setBounds(279, 169, 153, 40);
		w_clinic.add(btn_workerSelect);
		
	}
		 	
	public void updateDoctorModel() throws SQLException{
		 	DefaultTableModel clearModel = (DefaultTableModel) table_doktor.getModel();
		 	clearModel.setRowCount(0);
		 	for(int i=0;i<bashekim.getDoctorList().size();i++) {
					doctorData[0] = bashekim.getDoctorList().get(i).getId();
					doctorData[1] = bashekim.getDoctorList().get(i).getName();
		 			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
		 			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
		 			doctorModel.addRow(doctorData);
		 			
		 }
		 	
	}
	
	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<clinic.getList().size();i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}
}	
	

			



