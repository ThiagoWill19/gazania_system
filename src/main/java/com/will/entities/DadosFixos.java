package com.will.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DadosFixos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double prolabore;
	private double RGPS;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getProlabore() {
		return prolabore;
	}
	public void setProlabore(double prolabore) {
		this.prolabore = prolabore;
	}
	public double getRGPS() {
		return RGPS;
	}
	public void setRGPS(double rGPS) {
		RGPS = rGPS;
	}


}
