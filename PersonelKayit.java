package com.bilgeadam.swing;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.Statement;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@SuppressWarnings("unused")
public class PersonelKayit {

	//başına private ya da public koymazsak friendly denen bir kullanım oluyor.
	private JFrame frame;
	private JTextField TxtTCKimlikNo;
	private JTextField TxtAdi;
	private JTextField TxtSoyadi;
	private JTextField TxtTelefon;
	public String Cinsiyet="";
	public String Egitim="";
	public Integer Departman=0;
	public Integer Gorev=0;
	public String Image="";
	@SuppressWarnings("rawtypes")
	private JComboBox cmbDepartmanAdi;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbGorev;	
	private String DepartmanAdi;
	private int departmanID;
	private int gorevID;
	static DatabaseConnection object;
    ResultSet result;
    DefaultTableModel table2;
    private JCheckBox chkIlkOgretim;
    private JCheckBox chkLise;
    private JCheckBox chkUniversite;
    private JCheckBox chkYuksekLisans;
    private JRadioButton rbtErkek;
    private JRadioButton rbtKadin;
    private JPanel lblFotograf;
    private JTable tblSonuc;
    public static int rowi=0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					//dc connectiondan bir tane nesne oluşturulmasını garanti altına alıp bunu kullanacak.
					object=DatabaseConnection.getInstance();
					PersonelKayit window = new PersonelKayit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */	public  void setSelectedValue(@SuppressWarnings("rawtypes") JComboBox comboBox, int value)
	    {
	        Item item;
	        for (int i = 0; i < comboBox.getItemCount(); i++)
	        {
	            item = (Item)comboBox.getItemAt(i);
	            if (item.getId()== value)
	            {
	                cmbGorev.setSelectedIndex(i);
	                break;
	            }
	        }
	    }
	public PersonelKayit() {
		initialize();
		PersonelGetir();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 911, 604);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(10, 11, 333, 254);
		panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kişisel Bilgiler", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		frame.getContentPane().add(panel1);
		panel1.setLayout(null);
		
		JLabel lblTCKN = new JLabel("TC Kimlik No:");
		lblTCKN.setBounds(10, 34, 84, 14);
		panel1.add(lblTCKN);
		
		JLabel lblAdi = new JLabel("Adı:");
		lblAdi.setBounds(10, 59, 84, 14);
		panel1.add(lblAdi);
		
		JLabel lblSoyadi = new JLabel("Soyadi:");
		lblSoyadi.setBounds(10, 102, 84, 14);
		panel1.add(lblSoyadi);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setBounds(10, 142, 84, 14);
		panel1.add(lblTelefon);
		
		TxtTCKimlikNo = new JTextField();
		TxtTCKimlikNo.setBounds(104, 23, 195, 26);
		panel1.add(TxtTCKimlikNo);
		TxtTCKimlikNo.setColumns(10);
		
		TxtAdi = new JTextField();
		TxtAdi.setBounds(104, 60, 195, 26);
		TxtAdi.setColumns(10);
		panel1.add(TxtAdi);
		
		TxtSoyadi = new JTextField();
		TxtSoyadi.setBounds(104, 97, 195, 24);
		TxtSoyadi.setColumns(10);
		panel1.add(TxtSoyadi);
		
		TxtTelefon = new JTextField();
		TxtTelefon.setBounds(104, 135, 195, 29);
		TxtTelefon.setColumns(10);
		panel1.add(TxtTelefon);
		
		JLabel lblCinsiyet = new JLabel("Cinsiyet");
		lblCinsiyet.setBounds(10, 175, 84, 14);
		panel1.add(lblCinsiyet);
		
		JRadioButton rbtErkek = new JRadioButton("Erkek");
		rbtErkek.setBounds(104, 171, 78, 23);
		panel1.add(rbtErkek);
		
		JRadioButton rbtKadin = new JRadioButton("Kadın");
		rbtKadin.setBounds(215, 171, 84, 23);
		panel1.add(rbtKadin);
		
		ButtonGroup bG=new ButtonGroup();
		bG.add(rbtErkek);
		bG.add(rbtKadin);
		rbtErkek.setSelected(true);
		frame.getContentPane().add(panel1);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(353, 11, 293, 94);
		panel2.setBorder(new TitledBorder(null, "Departman Bilgileri", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);
		
		JLabel lblDepartmanAdi = new JLabel("Departman Adı:");
		lblDepartmanAdi.setBounds(10, 26, 100, 14);
		panel2.add(lblDepartmanAdi);
		
		JLabel lblGorev = new JLabel("Görev: ");
		lblGorev.setBounds(10, 51, 63, 14);
		panel2.add(lblGorev);
		
		
		cmbDepartmanAdi = new JComboBox();
		cmbDepartmanAdi.setBounds(107, 17, 176, 23);
		cmbDepartmanAdi.addActionListener(e->
		{
			JComboBox c=(JComboBox) e.getSource();
			Item item= (Item) c.getSelectedItem();
			System.out.println(item.getId()+" : "+ item.getDescription());
			int departmanID=item.getId();		
			DepartmanAdi=item.getDescription();
			if (departmanID>0)
			{   
				//Marka seçilir seçilmez modeli getirdim
				GorevGetir(departmanID);
			}		
			
		});		
		
		panel2.add(cmbDepartmanAdi);
		
		cmbGorev = new JComboBox();
		cmbGorev.setBounds(107, 51, 176, 23);
		cmbGorev.addActionListener(e->
		{
			JComboBox c=(JComboBox) e.getSource();
			Item item= (Item) c.getSelectedItem();			
			int id=item.getId();
			id=gorevID;
			
			
		});
		panel2.add(cmbGorev);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(353, 116, 173, 149);
		panel_2.setBorder(new TitledBorder(null, "Eğitim Bilgileri", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JCheckBox chkIlkOgretim = new JCheckBox("İlk Öğretim");
		chkIlkOgretim.setBounds(0, 34, 97, 23);
		panel_2.add(chkIlkOgretim);
		
		JCheckBox chkLise = new JCheckBox("Lise");
		chkLise.setBounds(0, 62, 97, 23);
		panel_2.add(chkLise);
		
		JCheckBox chkUniversite = new JCheckBox("Üniversite");
		chkUniversite.setBounds(0, 88, 97, 23);
		panel_2.add(chkUniversite);
		
		JCheckBox chkYuksekLisans = new JCheckBox("Yüksek Lisans");
		chkYuksekLisans.setBounds(0, 114, 97, 23);
		panel_2.add(chkYuksekLisans);
		
		JPanel lblFotograf = new JPanel();
		lblFotograf.setBounds(656, 23, 209, 221);
		lblFotograf.setBorder(new TitledBorder(null, "Fotoğraf", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(lblFotograf);
		
		JButton btnYeniKayit = new JButton("YENİ KAYIT");
		btnYeniKayit.setBounds(536, 116, 110, 23);
		frame.getContentPane().add(btnYeniKayit);
		
		JButton btnSil = new JButton("SİL");
		btnSil.setBounds(536, 150, 110, 23);
		frame.getContentPane().add(btnSil);
		
		
		TxtTCKimlikNo.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)))
				{
					e.consume();
				}
			}
			
		});
		
		TxtTelefon.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)))
				{
					e.consume();
				}
			}
			
		});
		
		JButton btnKaydet = new JButton("KAYDET");
		btnKaydet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(TxtTCKimlikNo.getText().trim().length()==0 && TxtAdi.getText().length()==0
						&& TxtSoyadi.getText().length()==0 && TxtTelefon.getText().length()==0)
				{
					JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz!", "Uyarı!", JOptionPane.ERROR_MESSAGE);
				}
				else if (TxtTCKimlikNo.getText().length()>0 && TxtTCKimlikNo.getText().length()<11)
				{
					JOptionPane.showMessageDialog(null, "TC Kimlik Numaranız 11 karakterden az olamaz", "Uyarı!", JOptionPane.ERROR_MESSAGE);
				}
				/*
				else if (!isValidTckn(TxtTCKimlikNo.getText().toString()))
				{
					JOptionPane.showMessageDialog(null, "Geçersiz TC No girdiniz", "Uyarı!", JOptionPane.ERROR_MESSAGE);
				}
				*/
				else if (TxtAdi.getText().length()<3)
				{
					JOptionPane.showMessageDialog(null, "Adınız 3 karakterden az olamaz!", "Uyarı!", JOptionPane.ERROR_MESSAGE);
				}
				
				else if(TxtSoyadi.getText().length()<2)
				{
					JOptionPane.showMessageDialog(null, "Soyadiniz 2 karakterden az olamaz!", "Uyarı!", JOptionPane.ERROR_MESSAGE);
				}
				else if (TxtTelefon.getText().length()>0 && TxtTelefon.getText().length()<11) {
					
					JOptionPane.showMessageDialog(null, "Telefon numaranız 11 karakterden az olamaz", "Uyarı!", JOptionPane.ERROR_MESSAGE);
				}
				else if (cmbDepartmanAdi.toString().length()==0)
				{
					JOptionPane.showMessageDialog(null, "Lütfen departmanınızı giriniz", "Uyarı!", JOptionPane.ERROR_MESSAGE);
				}
				else if (chkIlkOgretim.isSelected()==false && chkLise.isSelected()==false
						&& chkUniversite.isSelected()==false && chkYuksekLisans.isSelected()==false )
				{
					JOptionPane.showMessageDialog(null, "Lütfen eğitim seçiniz", "Uyarı!", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if (rbtErkek.isSelected()) {
						Cinsiyet="Erkek";
					}
					if(rbtKadin.isSelected()) {
						Cinsiyet="Kadın";
					}
					if(chkIlkOgretim.isSelected()) {
						Egitim="İlk Öğretim";
					}
					if(chkLise.isSelected()) {
						Egitim="Lise";
					}
					if(chkUniversite.isSelected()) {
						Egitim="Üniversite";
					}
					if(chkYuksekLisans.isSelected()) {
						Egitim="Yüksek Lisans";
					}	
					Object [] objButtonTeks= {"Evet", "Hayır"};
					int iReply=JOptionPane.showOptionDialog(null,
							"Personel Kaydedilecektir. Kaydetmek İstiyor musunuz?", "Personel Kayıt", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, objButtonTeks, objButtonTeks[0]);
					if(iReply==0)
					{				
				
				Personel p= new Personel (TxtTCKimlikNo.getText(),TxtAdi.getText(), TxtSoyadi.getText(), TxtTelefon.getText(),
						Cinsiyet, departmanID,Egitim,Image,gorevID);
				
				if(PersonelVarmi(TxtTCKimlikNo.getText())==true)
				{
					JOptionPane.showMessageDialog(null, "Böyle bir TCKN personel sisteminde kayıtlıdır","Uyarı!", JOptionPane.ERROR_MESSAGE);
				}
				PersonelEkle(p);
				
				//PersonelGetir();
				TxtTCKimlikNo.setText("");
				TxtAdi.setText("");
				TxtSoyadi.setText("");
				cmbDepartmanAdi.setSelectedIndex(0);
				cmbGorev.setSelectedIndex(0);
				chkIlkOgretim.setSelected(false);
				chkLise.setSelected(false);
				chkUniversite.setSelected(false);
				chkYuksekLisans.setSelected(false);
			   }					
			   }
			   }
				
		});
	
		
		btnKaydet.setBounds(536, 181, 107, 23);
		frame.getContentPane().add(btnKaydet);
		
		JButton btnGuncelle = new JButton("GÜNCELLE");
		btnGuncelle.setBounds(536, 215, 110, 23);
		frame.getContentPane().add(btnGuncelle);
		
		JButton btnYenile = new JButton("YENİLE");
		btnYenile.setBounds(536, 249, 110, 23);
		frame.getContentPane().add(btnYenile);
		
		JPanel panel4 = new JPanel();
		panel4.setBorder(new TitledBorder(null, "Ki\u015Fisel Bilgiler", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel4.setBounds(10, 276, 910, 221);
		frame.getContentPane().add(panel4);
		panel4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 871, 191);
		panel4.add(scrollPane);
		
		tblSonuc = new JTable();
		tblSonuc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				SeciliSatir();
			}
		});
		scrollPane.setViewportView(tblSonuc);
		
		JButton btnIlkKayit = new JButton("İLK KAYIT");
		btnIlkKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblSonuc.changeSelection(0, 0, false, false);
				SeciliSatir(0);
			}
		});
		btnIlkKayit.setBounds(28, 517, 110, 38);
		frame.getContentPane().add(btnIlkKayit);
		
		JButton btnOnceki = new JButton("ÖNCEKİ");
		btnOnceki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rowi=tblSonuc.getSelectedRow();
				if(rowi ==1 || rowi ==0)
				{
					tblSonuc.changeSelection(0, 0, false, false);
					SeciliSatir();
					System.out.println("Ilk Kayida Ulastiniz");
				}
				else
				{
					tblSonuc.changeSelection(rowi-1, 0, false, false);
					SeciliSatir(rowi-1);
				}
			}
		});
		btnOnceki.setBounds(206, 517, 110, 38);
		frame.getContentPane().add(btnOnceki);
		
		JButton btnSonraki = new JButton("SONRAKİ");
		btnSonraki.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rowi = tblSonuc.getSelectedRow();
				int kayit=tblSonuc.getRowCount();
				if(rowi==kayit  -1)
				{
					tblSonuc.changeSelection(rowi, 0, false, false);
					SeciliSatir(rowi);
					System.out.println("Son Kayida Ulastiniz!!!");
				}
				else
				{
					tblSonuc.changeSelection(rowi+1, 0, false, false);
					SeciliSatir(rowi+1);
				}
			}
			
		});
		btnSonraki.setBounds(386, 517, 119, 38);
		frame.getContentPane().add(btnSonraki);
		
		JButton btnSonKayit = new JButton("SON KAYIT");
		btnSonKayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kayit= tblSonuc.getRowCount();
				tblSonuc.changeSelection(kayit-1, 0, false, false);
				SeciliSatir(kayit-1);
			}
		});
		btnSonKayit.setBounds(573, 517, 110, 38);
		frame.getContentPane().add(btnSonKayit);
		
		JButton btnExcelExport = new JButton("EXCEL EXPORT");
		btnExcelExport.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				try
				{
					JFileChooser fileChooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File","xls");
//					fileChooser.addChoosableFileFilter(filter);
					fileChooser.setFileFilter(filter);
					int rVal = fileChooser.showSaveDialog(null);
					if ( rVal == fileChooser.APPROVE_OPTION)
					{
						String filename =fileChooser.getSelectedFile().getName();
						String path = fileChooser.getSelectedFile().getParentFile().getPath();
						// mac icin exportTable(tblSonuc, new File(path + "/" + filename + ".xls"));
						exportTable(tblSonuc, new File(path +  ".xls"));  // windows için 
					}					
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		btnExcelExport.setBounds(740, 517, 125, 38);
		frame.getContentPane().add(btnExcelExport);		
		
		DepartmanGetir();			
	}
	
	
	private static boolean isValidTckn(String vkn) 
	{
		final Long tckn = Long.valueOf(vkn).longValue();
		try 
		{
			String tmp = tckn.toString();
			if (tmp.length() == 11) 
			{
				int totalOdd = 0;
	     		int totalEven = 0;
				for (int i = 0; i < 9; i++) 
				{
					int val = Integer.valueOf(tmp.substring(i, i + 1));
					if (i % 2 == 0) 
					{
						totalOdd += val;
        			} else 
        			{
						totalEven += val;
					}
				}
				int total = totalOdd + totalEven + Integer.valueOf(tmp.substring(9, 10));
				int lastDigit = total % 10;
				if (tmp.substring(10).equals(String.valueOf(lastDigit))) {
					int check = (totalOdd * 7 - totalEven) % 10;
					if (tmp.substring(9, 10).equals(String.valueOf(check))) 
					{
						return true;
					}
				}
			}			
	
		} 
		catch (Exception e) {
			// LOGGER.catching(e);
		}
		return false;
	}
	
	
		@SuppressWarnings ("unchecked")
	public void DepartmanGetir() {
		try
		{			
			String sorgu="SELECT * FROM departman";
			Statement st=(Statement) object.getConnection().createStatement();
			ResultSet rs=st.executeQuery(sorgu);
			cmbDepartmanAdi.addItem(new Item(-1,"Departman Seçiniz."));
			while (rs.next()) {
				cmbDepartmanAdi.addItem(new Item(Integer.valueOf(rs.getString("Id")), rs.getString("DepartmanAdi")));
			}
			st.close();
			//object.getConnection().close();
		}
		catch (Exception a) {
			System.err.println("Hata !");
			System.err.println(a.getMessage());
		}
	}	
	
	
	@SuppressWarnings("unchecked")
	public void GorevGetir(int departmanID) {
		try
		{			
			String sorgu="SELECT * FROM gorev where departmanID="+departmanID;
			Statement st=(Statement) object.getConnection().createStatement();
			ResultSet rs=st.executeQuery(sorgu);
			//Gorev yüklemeden önce tüm elemanları temizliyoruz. Tekrar tekrar gorev seçiniz eklemesin diye.
			cmbGorev.removeAllItems();
			cmbGorev.addItem(new Item(-1,"Görev Seçiniz."));
			while (rs.next()) {
				
				cmbGorev.addItem(new Item(Integer.valueOf(rs.getString("Id")), rs.getString("GorevAdi")));
			}
			st.close();
			//Bağlantı sürekli açık kalmasın diye kapattık
			//object.getConnection().close();
		}
		catch (Exception a) {
			System.err.println("Hata !");
			System.err.println(a.getMessage());
		}		
	}
	
	public void PersonelEkle (Personel p)
	{
		try
		{
			PreparedStatement preStmt;
			preStmt=object.getConnection().prepareStatement("INSERT INTO personel(TcKimlikNo,Adi,Soyadi,Telefon,Cinsiyet"
					+",departmanID,Egitim,Image,gorevID) VALUES (?,?,?,?,?,?,?,?,?)");
			preStmt.setString(1, p.getTcKimlikNo());
			preStmt.setString(2, p.getAdi());
			preStmt.setString(3, p.getSoyadi());			
			preStmt.setString(4, p.getTelefon());
			preStmt.setString(5, p.getCinsiyet());
			preStmt.setInt(6, p.getDepartman());
			preStmt.setString(7, p.getEgitim());
			preStmt.setString(8, "");
			preStmt.setInt(9, p.getGorev());
			preStmt.executeUpdate();
			preStmt.close();
			System.out.println("Kaydınız Veritabanına Yapıldı");
		}
		catch (Exception a)
		{
			System.err.println("Hata !");
			System.err.println(a.getMessage());
		}
	}
	
	public boolean PersonelVarmi(String TcKimlikNo)
	{
		boolean sonuc=false;
		try
		{
			String sql="SELECT * FROM personel where TcKimlikNo="+TcKimlikNo;
			Statement st=(Statement) object.getConnection().createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()==true)
			{
				sonuc=true;
			}
			st.close();
		}
		catch (Exception a)
		{
			System.err.println("Hata!");
			System.err.println(a.getMessage());
		}
		
		return sonuc;
	}
	public void PersonelGetir()
	{
		try {
			Statement statement;
			int sutunSayisi;
			statement = (Statement)  object.getConnection().createStatement();
			result=statement.executeQuery(
					"SELECT p.Id,p.TcKimlikNo,p.Adi,p.Soyadi,p.Telefon,p.Cinsiyet,d.DepartmanAdi as Departman," + 
					" p.Egitim,p.Image,g.GorevAdi as Gorev,g.Id as GorevID, d.Id as DepID" + 
					" FROM personel as p inner join departman as d on d.Id = p.DepartmanID " + 
					" inner join gorev as g on g.Id = p.GorevID" + 
					" order by p.Id desc");
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
			tblSonuc.setModel(table2);
			result.close();
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Veritabani Baglanti Hatasi");
					
		}
	}
	public void SeciliSatir(int i)
	{		
		TxtTCKimlikNo.setText(table2.getValueAt(i, 1).toString());
		TxtAdi.setText(table2.getValueAt(i, 2).toString());
		TxtSoyadi.setText(table2.getValueAt(i,3).toString());
		TxtTelefon.setText(table2.getValueAt(i, 4).toString());
		String cinsiyet = table2.getValueAt(i, 5).toString();
		if ( cinsiyet.equals("Erkek"))
		{
			Cinsiyet="Erkek";
			rbtErkek.setSelected(true);
			rbtKadin.setSelected(false);
		}
		if ( cinsiyet.equals("Kadın"))
		{
			Cinsiyet="Kadın";
			rbtErkek.setSelected(false);
			rbtKadin.setSelected(true);
		}
		String departman = table2.getValueAt(i, 6).toString();
		if ( departman.equals("Bilgi İşlem"))
		{
			cmbDepartmanAdi.setSelectedIndex(1);
		}
		if ( departman.equals("Arge"))
		{
			cmbDepartmanAdi.setSelectedIndex(2);
		}
		if ( departman.equals("İnsan Kaynakları"))
		{
			cmbDepartmanAdi.setSelectedIndex(4);
		}
		if ( departman.equals("Muhasebe"))
		{
			cmbDepartmanAdi.setSelectedIndex(3);
		}
		String egitim = table2.getValueAt(i, 7).toString();
		
		if ( egitim.equals("İlk Öğretim"))
		{
			Egitim = "İlk Öğretim";
			chkIlkOgretim.setSelected(true);
			chkLise.setSelected(false);
			chkUniversite.setSelected(false);
			chkYuksekLisans.setSelected(false);
		}
		if ( egitim.equals("Lise"))
		{
			Egitim = "Lise";
			chkIlkOgretim.setSelected(false);
			chkLise.setSelected(true);
			chkUniversite.setSelected(false);
			chkYuksekLisans.setSelected(false);
		}	
		if ( egitim.equals("Üniversite"))
		{
			Egitim = "Üniversite";
			chkIlkOgretim.setSelected(false);
			chkLise.setSelected(false);
			chkUniversite.setSelected(true);
			chkYuksekLisans.setSelected(false);
		}
		if ( egitim.equals("Yüksek Lisans"))
		{
			Egitim = "Yüksek Lisans";
			chkIlkOgretim.setSelected(false);
			chkLise.setSelected(false);
			chkUniversite.setSelected(false);
			chkYuksekLisans.setSelected(true);
		}
		Gorev = Integer.valueOf(table2.getValueAt(i,10).toString());
		setSelectedValue(cmbGorev, Gorev);
		ImageIcon ii = new ImageIcon("src\\images\\" + table2.getValueAt(i, 8).toString());
		
	}
	public void SeciliSatir()
	{		
		int i = tblSonuc.getSelectedRow();
		TxtTCKimlikNo.setText(table2.getValueAt(i, 1).toString());
		TxtAdi.setText(table2.getValueAt(i, 2).toString());
		TxtSoyadi.setText(table2.getValueAt(i,3).toString());
		TxtTelefon.setText(table2.getValueAt(i, 4).toString());
		String cinsiyet = table2.getValueAt(i, 5).toString();
		int sayi2;
		sayi2 = Integer.valueOf(String.valueOf(tblSonuc.getModel().getValueAt(i,11)));				
		cmbDepartmanAdi.setSelectedIndex(sayi2);
		
		
		Gorev = Integer.valueOf(table2.getValueAt(i, 10).toString());
		setSelectedValue(cmbGorev, Gorev);
		ImageIcon ii = new ImageIcon("src\\images\\" + table2.getValueAt(i, 8).toString());
		String egitim = table2.getValueAt(i, 7).toString();
		if (egitim.equals("İlk Öğretim"))
		{
			Egitim = "İlk Öğretim";
			chkIlkOgretim.setSelected(true);
			chkLise.setSelected(false);
			chkUniversite.setSelected(false);
			chkYuksekLisans.setSelected(false);
		}
		if (egitim.equals("Lise"))
		{
			Egitim = "Lise";
			chkIlkOgretim.setSelected(false);
			chkLise.setSelected(true);
			chkUniversite.setSelected(false);
			chkYuksekLisans.setSelected(false);
		}	
		if (egitim.equals("Üniversite"))
		{
			Egitim = "Üniversite";
			chkIlkOgretim.setSelected(false);
			chkLise.setSelected(false);
			chkUniversite.setSelected(true);
			chkYuksekLisans.setSelected(false);
		}
		if (egitim.equals("Yüksek Lisans"))
		{
			Egitim = "Yüksek Lisans";
			chkIlkOgretim.setSelected(false);
			chkLise.setSelected(false);
			chkUniversite.setSelected(false);
			chkYuksekLisans.setSelected(true);
		}	
		if ( cinsiyet.equals("Erkek"))
		{
			Cinsiyet="Erkek";
			rbtErkek.setSelected(true);
			rbtKadin.setSelected(false);
		}
		if ( cinsiyet.equals("Kadın"))
		{
			Cinsiyet="Kadın";
			rbtErkek.setSelected(false);
			rbtKadin.setSelected(true);
		}
	}
	public void exportTable(JTable table, File file) throws IOException
	{
		TableModel   model=table.getModel();
		FileWriter  out=new FileWriter(file);
		for(int i=0; i<model.getColumnCount(); i++)
		{
			out.write(model.getColumnName(i)+"\t");
		}
		out.write("\n");
		for(int  i=0; i<model.getRowCount(); i++)
		{
		for(int j=0; j<model.getColumnCount(); j++)
		{
			out.write(model.getValueAt(i, j).toString()+"\t");
		}
		out.write("\n");
	}
	out.close();
	System.out.println("write out to:"+file);
	JOptionPane.showMessageDialog(null,"Personel Excele Cikarilmistir.","Bilgi!",JOptionPane.INFORMATION_MESSAGE);
  }
}