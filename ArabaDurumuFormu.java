package com.bilgeadam.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.bilgeadam.oop2.Araba;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ArabaDurumuFormu {

	private JFrame frame;
	private JTextField textYil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArabaDurumuFormu window = new ArabaDurumuFormu();
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
	public ArabaDurumuFormu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblArabaYiliGiriniz = new JLabel("Araba Yili Giriniz:");
		lblArabaYiliGiriniz.setBounds(53, 44, 130, 30);
		frame.getContentPane().add(lblArabaYiliGiriniz);
		
		textYil = new JTextField();
		textYil.setBounds(240, 44, 120, 30);
		frame.getContentPane().add(textYil);
		textYil.setColumns(10);
		
		JLabel lblDurumu = new JLabel("Durum:");
		lblDurumu.setBounds(63, 90, 80, 40);
		frame.getContentPane().add(lblDurumu);
		
		JLabel lblsonuc = new JLabel("Sonuc:");
		lblsonuc.setHorizontalAlignment(SwingConstants.LEFT);
		lblsonuc.setBounds(241, 95, 120, 30);
		frame.getContentPane().add(lblsonuc);
		
		JButton btnHesapla = new JButton("Hesapla");
		btnHesapla.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				int gelenYil=Integer.valueOf(textYil.getText());
				
				Araba      a=new Araba();
				lblsonuc.setText("Arabanin Yasi: "+a.Arabayashesapla(gelenYil));
				JOptionPane.showMessageDialog(null, "Yasi: "+a.Arabayashesapla(gelenYil),"BilgeAdam",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnHesapla.setBounds(241, 174, 120, 40);
		frame.getContentPane().add(btnHesapla);
		
		
		
	
	}
}
