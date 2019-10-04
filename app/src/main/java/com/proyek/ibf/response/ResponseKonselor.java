package com.proyek.ibf.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKonselor {

	@SerializedName("dokter")
	private List<KonselorItem> dokter;

	@SerializedName("status")
	private boolean status;

	public void setBerita(List<KonselorItem> dokter){
		this.dokter = dokter;
	}

	public List<KonselorItem> getDokter(){
		return dokter;
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
			"ResponseKonselor{" +
			"dokter= '" + dokter + '\'' +
			",status = '" + status + '\'' + 
			"}";
		}
}