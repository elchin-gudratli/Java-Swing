package com.bilgeadam.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.bilgeadam.oop.Insan;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class YasHesaplamaFormu {

	private JFrame frame;
	private JTextField txtYil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YasHesaplamaFormu window = new YasHesaplamaFormu();
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
	public YasHesaplamaFormu() {
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
		
		JLabel lblNewLabel = new JLabel("Dogum Yilinizi girin");
		lblNewLabel.setBounds(50, 45, 113, 14);
		frame.getContentPane().add(lblNewLabel);
		
	
		
		txtYil = new JTextField();
		txtYil.setBounds(187, 42, 148, 20);
		frame.getContentPane().add(txtYil);
		txtYil.setColumns(10);
		
		

		JLabel lblNewLabel_1 = new JLabel("Sonuc:");
		lblNewLabel_1.setBounds(50, 90, 83, 36);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		JLabel lblSonuc = new JLabel("Sonucu");
		lblSonuc.setBounds(187, 98, 148, 20);
		frame.getContentPane().add(lblSonuc);
		
		
		JButton btnHesapla = new JButton("Hesapla");
		btnHesapla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// Casting Convert Etme
				
				int gelenYil =Integer.valueOf(txtYil.getText());
				Insan  i=new Insan();
				
				lblSonuc.setText("Yasiniz: "+i.YasHesapla(gelenYil));
				//2-ci yontem
				//lblSonuc.setText(String.valueOf(i.YasHesapla(gelenYil)));
				
				JOptionPane.showMessageDialog(null, "Yasiniz: "+i.YasHesapla(gelenYil),"BilgeAdam",JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		btnHesapla.setBounds(204, 156, 154, 50);
		frame.getContentPane().add(btnHesapla);
		
		

	}
}
