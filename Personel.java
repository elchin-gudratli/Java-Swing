package com.bilgeadam.swing;

import java.util.ArrayList;

public class Personel {
	
	private String tcKimlikNo;
	private String adi;
	private String telefon;
	private String cinsiyet;
	private Integer departman;
	private String egitim;
	private String soyadi;
	private String image;
	private Integer gorev;
	public static ArrayList<Personel> personeller=new ArrayList<Personel>();
	public Personel()
	{
		
	}
	public Personel(String tcKimlikNo, String adi,String soyadi,String telefon,
			String cinsiyet,Integer departman,String egitim,String image,Integer gorev)
	{
		super();
		this.tcKimlikNo=tcKimlikNo;
		this.adi=adi;
		this.soyadi=soyadi;
		this.telefon=telefon;
		this.cinsiyet=cinsiyet;
		this.departman=departman;
		this.egitim=egitim;
		this.image=image;
		this.gorev=gorev;
	}
	public String getTcKimlikNo() {
		return tcKimlikNo;
	}
	public void setTcKimlikNo(String tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}
	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getCinsiyet() {
		return cinsiyet;
	}
	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}
	public Integer getDepartman() {
		return departman;
	}
	public void setDepartman(Integer departman) {
		this.departman = departman;
	}
	public String getEgitim() {
		return egitim;
	}
	public void setEgitim(String egitim) {
		this.egitim = egitim;
	}
	public String getSoyadi() {
		return soyadi;
	}
	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getGorev() {
		return gorev;
	}
	public void setGorev(Integer gorev) {
		this.gorev = gorev;
	}
	public static ArrayList<Personel> getPersoneller() {
		return personeller;
	}
	public static void setPersoneller(ArrayList<Personel> personeller) {
		Personel.personeller = personeller;
	}

	

}
