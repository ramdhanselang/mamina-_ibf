package com.proyek.ibf.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseArtikel {

	@SerializedName("artikel")
	private List<ArtikelItem> artikel;

	@SerializedName("status")
	private boolean status;

	public void setArtikel(List<ArtikelItem> artikel){
		this.artikel = artikel;
	}

	public List<ArtikelItem> getArtikel(){
		return artikel;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseArtikel{" +
			"artikel = '" + artikel + '\'' +
			",status = '" + status + '\'' + 
			"}";
		}
}