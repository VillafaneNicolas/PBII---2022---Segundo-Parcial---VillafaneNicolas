package ar.edu.unlam.pb2;

public class Pc extends Dispositivo{
	private String so;
	private String ip;
	private String localidad;
	
	//constructores
	
	public Pc(String so, String ip, String localidad) {
		super();
		this.so = so;
		this.ip = ip;
		this.localidad = localidad;
	}
	
	
	public Pc() {
		super();
	}
	
	//Getters n' Setters
	public String getSo() {
		return so;
	}
	
	public void setSo(String so) {
		this.so = so;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
		
}
