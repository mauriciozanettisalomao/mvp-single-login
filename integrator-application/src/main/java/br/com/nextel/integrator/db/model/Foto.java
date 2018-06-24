package br.com.nextel.integrator.db.model;

public class Foto {

	public String numemp;
	public byte[] foto;
	
	public String getNumemp() {
		return numemp;
	}
	public void setNumemp(String numemp) {
		this.numemp = numemp;
	}
	public byte[] getFoto() {
		return foto;
	}
	public void setFoto(byte[] fileContent) {
		this.foto = fileContent;
	}
	
	
	
}
