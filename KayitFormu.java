package com.bilgeadam.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class KayitFormu {

	private JFrame frame;
	private JTextField textHastaAd;
	private JTextField textHastaSoyad;
	private JTextField textTC;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbBrans;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbDoktor;
	private int BransID;
	private int DoktorID;
	private JTable table;
    ResultSet result;
	DefaultTableModel table2;
	static DatabaseConnection object;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					object=DatabaseConnection.getInstance();
					KayitFormu window = new KayitFormu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KayitFormu() {
		initialize();
		RandevuGetir();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 530);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "RANDEVU KAYIT FORMU", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 414, 470);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bran :");
		lblNewLabel.setBounds(10, 25, 100, 25);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Doktor Adi :");
		lblNewLabel_1.setBounds(10, 61, 100, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Hasta Adi :");
		lblNewLabel_2.setBounds(10, 97, 100, 25);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Hasta Soyadi :");
		lblNewLabel_3.setBounds(10, 133, 100, 25);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Hasta T.C Kimlik Numarasi :");
		lblNewLabel_4.setBounds(10, 169, 150, 25);
		panel.add(lblNewLabel_4);
		
		cmbBrans = new JComboBox();
		cmbBrans.setBounds(229, 25, 157, 25);
		cmbBrans.addActionListener(e->
		{
			JComboBox c=(JComboBox) e.getSource();
			Item item=(Item) c.getSelectedItem();
			System.out.println(item.getId()+" : "+item.getDescription());
			
			BransID=item.getId();

			if(BransID>0) {
			   DoktorGetir(BransID);	
			}
		});
		panel.add(cmbBrans);
		
		cmbDoktor = new JComboBox();
		cmbDoktor.setBounds(229, 61, 157, 25);
		cmbDoktor.addActionListener(e->
		{
			JComboBox c=(JComboBox) e.getSource();
			Item item=(Item) c.getSelectedItem();
			System.out.println(item.getId()+" : "+item.getDescription());
			DoktorID = item.getId();

		});
		panel.add(cmbDoktor);
		
		textHastaAd = new JTextField();
		textHastaAd.setBounds(229, 97, 157, 25);
		panel.add(textHastaAd);
		textHastaAd.setColumns(10);
		
		textHastaSoyad = new JTextField();
		textHastaSoyad.setBounds(229, 133, 157, 25);
		panel.add(textHastaSoyad);
		textHastaSoyad.setColumns(10);
		
		textTC = new JTextField();
		textTC.setBounds(229, 169, 157, 25);
		panel.add(textTC);
		textTC.setColumns(10);
		
		JButton btnKaydet = new JButton("KAYDET");
		btnKaydet.setBounds(249, 227, 100, 37);
		btnKaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textHastaSoyad.getText().length()==0 && 
						textHastaAd.getText().length()==0){
					JOptionPane.showMessageDialog(null, " Lutfen tum alanlari doldurunuz!","Uyari!",
							JOptionPane.ERROR_MESSAGE);
				}
				
				
				else {
					
				
				
				try
				{
				
					
					 String myDriver="com.mysql.jdbc.Driver";
		        	 String db="jdbc:mysql://46.28.239.130/opendart_";
		        	 Class.forName(myDriver);
		        	 Connection conn=DriverManager.getConnection(db, "opendart","Hercai123");
		        	 
		        	 
		        	 PreparedStatement preStmt;
		        	 preStmt=(PreparedStatement) conn.prepareStatement("Insert INTO Randevu(BransID,DoktorID,HastaAdi,HastaSoyadi,HastaTcKimlikNo) Values(?,?,?,?,?)");
		        	 preStmt.setInt(1, BransID);
		        	 preStmt.setInt(2, DoktorID);
		        	 preStmt.setString(3, String.valueOf(textHastaAd.getText()));
		        	 preStmt.setString(4, String.valueOf(textHastaSoyad.getText()));
		        	 preStmt.setString(5, String.valueOf(textTC.getText()));
		        	 preStmt.executeUpdate();
		        	 preStmt.close();
		        	 conn.close();
		        	 System.out.println("Kaydinizi Veritabanina Yapildi");
				}
				
				
				catch(Exception a) {
					System.err.println("Hata ! ");
					System.err.println(a.getMessage());
				   }
				
			    }
			
			}
		});
		panel.add(btnKaydet);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 450, 394, -170);
		panel.add(panel_1);
		
		table = new JTable();
		table.setBounds(10, 279, 394, 160);
		panel.add(table);
		BransGetir();
		
		
		
	}
	@SuppressWarnings("unchecked")
	public void BransGetir() {
		try {
			String myDriver="com.mysql.jdbc.Driver";
			String db="jdbc:mysql://46.28.239.130/opendart_";
			Class.forName(myDriver);
			Connection  conn=DriverManager.getConnection(db,"opendart","Hercai123");
			String sorgu="SELECT * FROM branslar";
			Statement st=(Statement) conn.createStatement();
			ResultSet rs=st.executeQuery(sorgu);
			cmbBrans.addItem(new Item(-1,"Brans Seciniz."));
			while (rs.next()) {
				cmbBrans.addItem(new Item(Integer.valueOf(rs.getString("Id")), rs.getString("BransAdi")));
			}
			st.close();
			conn.close();
		}
		catch(Exception a) {
			System.err.println("HATA");
			System.err.println(a.getMessage());
		}
	}
		
		@SuppressWarnings("unchecked")
		public void DoktorGetir(int bransID) {
			try {
				String myDriver="com.mysql.jdbc.Driver";
				String db="jdbc:mysql://46.28.239.130/opendart_";
				Class.forName(myDriver);
				Connection  conn=DriverManager.getConnection(db,"opendart","Hercai123");
				String sorgu="SELECT * FROM doktorlar where bransID="+bransID;
				Statement st=(Statement) conn.createStatement();
				ResultSet rs=st.executeQuery(sorgu);
				cmbDoktor.removeAllItems();
				cmbDoktor.addItem(new Item(-1,"Doktor Seciniz."));
				while (rs.next()) {
					cmbDoktor.addItem(new Item(Integer.valueOf(rs.getString("Id")),rs.getString("Unvani")+" "+rs.getString("Adi")+" "+ rs.getString("Soyadi")));
				}
				st.close();
				conn.close();
			}
			catch(Exception a) {
				System.err.println("HATA");
				System.err.println(a.getMessage());
			}
		
	}

	public void RandevuGetir() {
		try {
			Statement statement;
			int sutunSayisi;
			
			
			statement = (Statement)  object.getConnection().createStatement();
			result = statement.executeQuery(
					"select r.HastaAdi,r.HastaSoyadi,r.HastaTcKimlikNo,d.Adi,d.Soyadi,b.BransAdi,d.Unvani from randevu as r join doktorlar as d on r.DoktorID = d.Id\r\n" + 
					"join branslar as b on d.BransID =  b.Id"					
					);
			sutunSayisi=result.getMetaData().getColumnCount();
			table2 = new DefaultTableModel();
			for(int i=1;i<=sutunSayisi; i++)
				table2.addColumn(result.getMetaData().getColumnName(i));
			while(result.next()) {
				Object[] row=new Object[sutunSayisi];
				for(int i=1; i<=sutunSayisi; i++)
					row[i-1]=result.getObject(i);
				table2.addRow(row);
			}

			table.setModel(table2);
			result.close();
			
			
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Veritabani Baglanti Hatasi");
		
		}
	}
}
