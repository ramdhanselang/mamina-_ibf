package com.proyek.ibf.response;

import com.google.gson.annotations.SerializedName;

public class ArtikelItem{

	@SerializedName("penulis")
	private String penulis;

	@SerializedName("foto")
	private String foto;

	@SerializedName("id")
	private String id;

	@SerializedName("judul")
	private String judul;

	@SerializedName("tanggal_posting")
	private String tanggalPosting;

	@SerializedName("summary")
	private String summary;

	@SerializedName("content")
	private String content;

	public String getPenulis() {
		return penulis;
	}

	public void setPenulis(String penulis) {
		this.penulis = penulis;
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

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getTanggalPosting() {
		return tanggalPosting;
	}

	public void setTanggalPosting(String tanggalPosting) {
		this.tanggalPosting = tanggalPosting;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString(){
		return
				"ArtikelItem{" +
						"penulis = '" + penulis + '\'' +
						",foto = '" + foto + '\'' +
						",id = '" + id + '\'' +
						",judul = '" + judul + '\'' +
						",summary = '" + summary + '\'' +
						",tanggal_posting = '" + tanggalPosting + '\'' +
						",content = '" + content+ '\'' +
						"}";
	}
}