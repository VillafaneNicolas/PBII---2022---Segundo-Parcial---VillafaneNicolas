package ar.edu.unlam.pb2;

public class PagoDeServicio extends Monetaria implements Monitoreable, Alertable{

	private String nroTx;
	private String servicio;
	private double monto;
	
	//Constructores
	
	public PagoDeServicio(Dispositivo dispositivo,Cliente cliente, Banco banco) {
		super(dispositivo,cliente, banco); 	
	}
	
	

	public PagoDeServicio(Dispositivo dispositivo,Cliente cliente, Banco banco, String nroTx, String servicio, double monto) {
		super(dispositivo,cliente, banco); 
		this.nroTx = nroTx;
		this.servicio = servicio;
		this.monto = monto;
		this.getBanco().validarScore(this);
	}
	
	//getters and Setters
	public String getNroTx() {
		return nroTx;
	}



	public void setNroTx(String nroTx) {
		this.nroTx = nroTx;
	}



	public String getServicio() {
		return servicio;
	}



	public void setServicio(String servicio) {
		this.servicio = servicio;
	}



	public double getMonto() {
		return monto;
	}



	public void setMonto(double monto) {
		this.monto = monto;
	}




	//Alertable

	@Override
	public void marcarComoCasoSospechoso() {
		if(this.getCliente().getScore()>60 && this.getCliente().getScore()<79) {
			this.getBanco().ingresarAnalizable(this);
			System.out.println("El caso se estara analizando.");
		}
		System.out.println("Transaccion Aprobada");
		monitorear();
	}
	
	@Override
	public void confirmarSiFueFraude() {
		try {
			if(this.getCliente().getScore()>=80) {
				throw new FraudeException("La transaccion resulto fraudulenta");
			}
		}catch (Exception e) {
			this.getBanco().ingresarFraude(this.getDispositivo());
			System.out.println("Chequear que la lista de fraudes tenga algo");
			e.printStackTrace();
		}
	}
	
	
	
	

}
