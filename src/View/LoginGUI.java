package View;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;
import Helper.*;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JPasswordField fld_hastaPass;
	private JTextField fld_doktorTc;
	private JPasswordField fld_doktorPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemi'ne Hoş Geldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblNewLabel.setBounds(149, 46, 387, 58);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(72, 125, 545, 314);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(240, 240, 240));
		w_tabpane.addTab("Hasta Girişi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("T.C. Numaranız : ");
		lblTcNumaranz.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblTcNumaranz.setBounds(10, 30, 189, 58);
		w_hastaLogin.add(lblTcNumaranz);
		
		JLabel lblsifre = new JLabel("Şifre : ");
		lblsifre.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblsifre.setBounds(10, 83, 189, 58);
		w_hastaLogin.add(lblsifre);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setBounds(183, 48, 247, 34);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}

		});
		btn_register.setBounds(78, 196, 165, 58);
		w_hastaLogin.add(btn_register);
		
		JButton btn_hastaLogin = new JButton("Giriş Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastaTc.getText().length()==0 || fld_hastaPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					boolean key = true ;  
					try {
						Connection con = conn.connDb();
						java.sql.Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT*FROM user");
						while(rs.next()) {
							if(fld_hastaTc.getText().equals(rs.getString("tcno")) && fld_hastaPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword("password");
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}
					if(key) {
						Helper.showMsg("Böyle bir hasta bulunamadı , lütfen kayıt olunuz !");
					}
				}
			}
		});
		btn_hastaLogin.setBounds(298, 196, 165, 58);
		w_hastaLogin.add(btn_hastaLogin);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setBounds(183, 93, 247, 34);
		w_hastaLogin.add(fld_hastaPass);
		
		JPanel w_doktorLogin = new JPanel();
		w_tabpane.addTab("Doktor Girişi", null, w_doktorLogin, null);
		w_doktorLogin.setLayout(null);
		
		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaranız : ");
		lblTcNumaranz_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblTcNumaranz_1.setBounds(10, 29, 189, 58);
		w_doktorLogin.add(lblTcNumaranz_1);
		
		fld_doktorTc = new JTextField();
		fld_doktorTc.setColumns(10);
		fld_doktorTc.setBounds(183, 47, 247, 34);
		w_doktorLogin.add(fld_doktorTc);
		
		JLabel lblsifre_1 = new JLabel("Şifre : ");
		lblsifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lblsifre_1.setBounds(10, 82, 189, 58);
		w_doktorLogin.add(lblsifre_1);
		
		fld_doktorPass = new JPasswordField();
		fld_doktorPass.setBounds(183, 92, 247, 34);
		w_doktorLogin.add(fld_doktorPass);
		
		JButton btn_doktorLogin = new JButton("Giriş Yap");
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doktorTc.getText().length()==0 || fld_doktorPass.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con = conn.connDb();
						java.sql.Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT*FROM user");
						while(rs.next()) {
							if(fld_doktorTc.getText().equals(rs.getString("tcno")) && fld_doktorPass.getText().equals(rs.getString("password"))) {
								if(rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
							}
						}
					}catch(SQLException e1){
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorLogin.setBounds(88, 195, 375, 58);
		w_doktorLogin.add(btn_doktorLogin);
	}
}

