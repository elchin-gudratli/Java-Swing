package com.bilgeadam.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.bilgeadam.constructor3.*;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArabaKayitFormu {

	private JFrame frame;
	//global degisken olmuslar her yerden cağrılabilir...
	private JTextField txtPencere;
	private JTextField txtKapi;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbMarka;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbModel;
	private String Marka;
	private String Model;
	private int MarkaID;
	private DefaultTableModel table2;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArabaKayitFormu window = new ArabaKayitFormu();
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
	 * @throws ClassNotFoundException 
	 */
	public ArabaKayitFormu() throws ClassNotFoundException, SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() throws ClassNotFoundException, SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 613, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setForeground(Color.BLACK);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "ARABA KAYIT FORMU", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(0, 0, 597, 542);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMarkas = new JLabel(" Markas\u0131  :");
		lblMarkas.setFont(new Font("Segoe UI Semilight", Font.BOLD, 12));
		lblMarkas.setBounds(38, 54, 75, 35);
		panel.add(lblMarkas);
		
		JLabel lblModeli = new JLabel(" Modeli    :");
		lblModeli.setFont(new Font("Segoe UI Semilight", Font.BOLD, 12));
		lblModeli.setBounds(38, 113, 75, 35);
		panel.add(lblModeli);
		
		JLabel lblPencere = new JLabel(" Pencere   :");
		lblPencere.setFont(new Font("Segoe UI Semilight", Font.BOLD, 12));
		lblPencere.setBounds(38, 159, 75, 35);
		panel.add(lblPencere);
		
		JLabel lblKap = new JLabel(" Kap\u0131        :");
		lblKap.setFont(new Font("Segoe UI Semilight", Font.BOLD, 12));
		lblKap.setBounds(38, 215, 75, 35);
		panel.add(lblKap);
		
		txtPencere = new JTextField();
		txtPencere.setColumns(10);
		txtPencere.setBounds(203, 167, 150, 20);
		panel.add(txtPencere);
		
		txtKapi = new JTextField();
		txtKapi.setColumns(10);
		txtKapi.setBounds(203, 223, 150, 20);
		panel.add(txtKapi);
		
		JButton btnKaydet = new JButton("Kaydet");
		btnKaydet.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			if (txtPencere.getText().length() == 0 && txtKapi.getText().length() == 0 && 
					Marka.equals("Seciniz.") == true &&
					Model.equals("Model Seciniz.") == true ) 
			{
				JOptionPane.showMessageDialog(null, "Lütfen tüm alanlari doldurunuz!", "Uyari!", JOptionPane.ERROR_MESSAGE );
			}
			else if (txtPencere.getText().length() == 0 && txtKapi.getText().length() == 0 && 
					Marka.equals("Seciniz.") == true &&
					Model.equals(" ") == true ) 
			{
				JOptionPane.showMessageDialog(null, "Lütfen tüm alanlari doldurunuz!", "Uyari!", JOptionPane.ERROR_MESSAGE );
			}
			
		
			else {
			
			try {		
					
			
					
					String myDriver = "com.mysql.jdbc.Driver";
					String db = "jdbc:mysql://46.28.239.130/opendart_";
					Class.forName(myDriver);
					Connection conn = DriverManager.getConnection(db,"opendart", "Hercai123");
					
					PreparedStatement preStmt;
					preStmt = conn.prepareStatement("INSERT INTO Araba(Marka,Model,Pencere,Kapi) VALUES (?,?,?,?)");
					preStmt.setString(1, Marka);
					preStmt.setString(2, Model);
					preStmt.setInt(3, Integer.valueOf(txtPencere.getText()));
					preStmt.setInt(4, Integer.valueOf(txtKapi.getText()));
					preStmt.execute();
					preStmt.close();
					conn.close();
					System.out.println("Kaydiniz veritabanina yapildi.");
					
					}
				catch(Exception a) {
					System.err.println("Hata !");
					System.err.println(a.getMessage());
			}
				
				
			}	
				
				
			}
		});
		btnKaydet.setBounds(328, 272, 89, 23);
		panel.add(btnKaydet);
		
		cmbMarka = new JComboBox();
		cmbMarka.setBounds(203, 62, 150, 20);
		cmbMarka.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			//System.out.println(item.getId() + " : " + item.getDescription());
			MarkaID = item.getId();	
			Marka = item.getDescription();
			if(MarkaID > 0)
			{
                 ModelGetir(MarkaID);
			}
			
			});
		panel.add(cmbMarka);
		
		cmbModel = new JComboBox();
		cmbModel.setBounds(203, 121, 150, 20);
		cmbModel.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			
			Model = item.getDescription();
			
			});
		panel.add(cmbModel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GRAY);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Araba Listesi", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(27, 306, 548, 225);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 21, 548, 204);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				SeciliSatir();
			}
		});
		table.setFillsViewportHeight(true);
		scrollPane.setRowHeaderView(table);
		scrollPane.setViewportView(table);
		
		JButton btnGuncelle = new JButton("Guncelle");
		btnGuncelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int i = table.getSelectedRow(); //guncelle button
				 int column = 0 ;
				 int row = table.getSelectedRow();
				 int id = Integer.valueOf(table.getModel().getValueAt(row, column).toString());
				 
				 if(i>= 0) {
					 table2.setValueAt(Marka, i, 1);
					 table2.setValueAt(Model, i, 2);
					 table2.setValueAt(txtPencere.getText(), i, 3);
					 table2.setValueAt(txtKapi.getText(), i, 4);
					 
					 
					 
					 ArabaGuncelle(id);
				 }
				 else {
					 System.out.println("Guncelleme Hatasi");
					 
				 }
				
			}
		});
		btnGuncelle.setBounds(203, 272, 89, 23);
		panel.add(btnGuncelle);
		
		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = table.getSelectedRow();
				if (i >= 0) {

					Object[] objButtonTeks = { "Evet", "Hayır" };
					int iReply = JOptionPane.showOptionDialog(null, "Kayıt silinecektir. Devam etmek istiyor musunuz?",
							"Araba Kayıt", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							objButtonTeks, objButtonTeks[1]);
					if (iReply == 0) {


						int column = 0;
						int row = table.getSelectedRow();
						int id = Integer.valueOf(table.getModel().getValueAt(row, column).toString());

						ArabaSil(id);
						table2.removeRow(row);
						txtPencere.setText("");
						txtKapi.setText("");
						cmbMarka.setSelectedIndex(-1);
						cmbModel.setSelectedIndex(0);
						JOptionPane.showMessageDialog(null, "Araba Silinmiştir", "Uyarı!",
								JOptionPane.INFORMATION_MESSAGE);
						try {
							ArabalariGetir();
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}

				} else {
					System.out.println("Silme Hatası");
				}
			}
		});
		btnSil.setBounds(73, 272, 89, 23);
		panel.add(btnSil);
		
		
		MarkaGetir();
		
		txtPencere.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE || (c == KeyEvent.VK_DELETE )))) {
					e.consume();
				}
			}
		});
		
			
	try {
		ArabalariGetir();
	}catch (ClassNotFoundException e1) {
		
		e1.printStackTrace();
	}catch (SQLException e1) {
		
		e1.printStackTrace();
	}
		
	}
	public void ArabaSil(int id) {
		try {
			String myDriver = "com.mysql.jdbc.Driver";
			String db ="jdbc:mysql://46.28.239.130/opendart_";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(db,"opendart","Hercai123");
			
			PreparedStatement preStmt;
			String sql = "Delete FROM araba where Id=" + id;
			preStmt = conn.prepareStatement(sql);
			
			preStmt.executeUpdate();
			preStmt.close();
			conn.close();
			System.out.println("Kaydiniz Silinmistir.");
			}
		catch (Exception a) {
			System.err.println("Hata ! ");
			System.err.println(a.getMessage());
		}
		
		
	}
	public void ArabaGuncelle(int id) {
		try {
			String myDriver = "com.mysql.jdbc.Driver";
			String db ="jdbc:mysql://46.28.239.130/opendart_";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(db,"opendart","Hercai123");
			
			PreparedStatement preStmt;
			String sql = "UPDATE araba SET " + "Marka='" + Marka + "', " + "Model='"
			+ Model + "', " + "Pencere='" + Integer.valueOf(txtPencere.getText()) + "', " + "Kapi='"
			+ Integer.valueOf(txtKapi.getText()) + "' WHERE Id=" + id ;
			preStmt = conn.prepareStatement(sql);
			
			preStmt.execute();
			preStmt.close();
			conn.close();
			System.out.println("Kaydiniz guncellenmistir!");
		    }
		
         catch (Exception a) {
			System.err.println("Hata ! ");
			System.err.println(a.getMessage());
		}
	}
	
	public void SeciliSatir() {
		
		int i=table.getSelectedRow();
		txtPencere.setText(table.getValueAt(i, 3).toString());
		txtKapi.setText(table.getValueAt(i, 4).toString());
		//cmb.Marka.setSelectedIndex(Integer.valueOf(2));
	}
	
	public void SeciliSatir(int i) {
		txtPencere.setText(table.getValueAt(i, 3).toString());
		txtKapi.setText(table.getValueAt(i, 4).toString());
		//cmb.Marka.setSelectedIndex(Integer.valueOf(2));
	}
	
	
	@SuppressWarnings("unchecked")
	public void MarkaGetir() {
		try {
			String myDriver = "com.mysql.jdbc.Driver";
			String db ="jdbc:mysql://46.28.239.130/opendart_";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(db,"opendart","Hercai123");
			String sorgu = "SELECT * FROM marka";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sorgu);
			cmbMarka.addItem(new Item(-1, "Seciniz."));
			while(rs.next()) { 
				
				cmbMarka.addItem(new Item(Integer.valueOf(rs.getString("Id")),rs.getString("markasi")));
			}
			st.close();
			conn.close();
			
			}
		
		catch (Exception a) {
			System.err.println("Hata ! ");
			System.err.println(a.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	public void ModelGetir(int markaID) {
		try {
			String myDriver = "com.mysql.jdbc.Driver";
			String db ="jdbc:mysql://46.28.239.130/opendart_";
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(db,"opendart","Hercai123");
			String sorgu = "SELECT * FROM model where markaID="+markaID;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sorgu);
			cmbModel.removeAllItems();
			cmbModel.addItem(new Item(-1, "Model Seciniz."));
			while(rs.next()) {
				
				cmbModel.addItem(new Item(Integer.valueOf(rs.getString("Id")),rs.getString("modeli")));
			}
			st.close();
			conn.close();
			
			}
		
		catch (Exception a) {
			System.err.println("Hata ! ");
			System.err.println(a.getMessage());
		}
		
	}
	public void ArabalariGetir() throws ClassNotFoundException, SQLException{
		ArrayList<Araba> arabalar = new ArrayList<Araba>();
		
		String myDriver = "com.mysql.jdbc.Driver";
		String db ="jdbc:mysql://46.28.239.130/opendart_";
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(db,"opendart","Hercai123");
		String sorgu = "SELECT * FROM araba";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sorgu);
		
		while(rs.next()) {
		
			Marka marka = new Marka(rs.getString("Marka"));
			Model model = new Model(rs.getString("Model"));
			Pencere pencere = new Pencere(rs.getInt("Pencere"));
			Kapi kapi = new Kapi(rs.getInt("Kapi"));
			Kasa kasa = new Kasa(marka,model,kapi,pencere);
			Araba a = new Araba(rs.getInt("Id"),kasa);
			
			arabalar.add(a);
		}
		
        table2 = new DefaultTableModel();
        
        Object[] columnsName = new Object[5];
        
        columnsName[0] = "Id";
        columnsName[1] = "Marka";
        columnsName[2] = "Model";
        columnsName[3] = "Pencere";
        columnsName[4] = "Kapı";
        
        table2.setColumnIdentifiers(columnsName);
        
        Object[] rowData = new Object[5];
        
        for(int i = 0; i < arabalar.size(); i++){
            
            rowData[0] = arabalar.get(i).id;
            rowData[1] = arabalar.get(i).kasa.marka.markasi;
            rowData[2] = arabalar.get(i).kasa.model.modeli;
            rowData[3] = arabalar.get(i).kasa.pencere.pencereSayi;
            rowData[4] = arabalar.get(i).kasa.kapi.kapiSayisi;
               
            table2.addRow(rowData);
		}
        table.setModel(table2);
        conn.close();
    }
}