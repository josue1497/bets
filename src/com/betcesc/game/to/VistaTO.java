package com.betcesc.game.to;

import com.betcesc.game.interfaces.VistaIF;

public class VistaTO implements VistaIF {

	private String idVista;
	private String title;
	private String content;
	private String image;
	private String status;
	private String tipo;

	public VistaTO() {

	}

	public String getIdVista() {
		return idVista;
	}

	public void setIdVista(String idVista) {
		this.idVista = idVista;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
}
