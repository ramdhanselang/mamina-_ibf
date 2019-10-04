package com.proyek.ibf.response;

import com.google.gson.annotations.SerializedName;

public class KonselorItem{

	@SerializedName("deskripsi")
	private String dsskripsi;

	@SerializedName("nama")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("id")
	private String id;

	@SerializedName("status")
	private String status;

	public String getDsskripsi() {
		return dsskripsi;
	}

	public void setDskripsi(String dskripsi) {
		this.dsskripsi = dskripsi;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Override
	public String toString(){
		return
				"KonselorItem{" +
						"deskripsi= '" + dsskripsi + '\'' +
						",foto = '" + foto + '\'' +
						",id = '" + id + '\'' +
						",nama = '" + nama + '\'' +
						",status = '" + status + '\'' +
						"}";
	}
}