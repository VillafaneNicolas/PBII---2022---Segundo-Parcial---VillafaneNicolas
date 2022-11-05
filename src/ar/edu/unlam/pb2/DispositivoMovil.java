package ar.edu.unlam.pb2;

public class DispositivoMovil extends Dispositivo{
	
	private String imei;
	private Boolean biometrico;
	
	
	//getters n' setters
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public Boolean getBiometrico() {
		return biometrico;
	}
	public void setBiometrico(Boolean biometrico) {
		this.biometrico = biometrico;
	}
	public DispositivoMovil(String imei, Boolean biometrico) {
		super();
		this.imei = imei;
		this.biometrico = biometrico;
	}
	public DispositivoMovil() {
		super();
	}
	
	
	

}
