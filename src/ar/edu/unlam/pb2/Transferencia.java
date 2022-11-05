package ar.edu.unlam.pb2;

public class Transferencia extends Monetaria implements Rechazable, Alertable{
	
	String numeroTransaccion;
	String cuentaDestino;
	double monto;
	
	

	public Transferencia(Dispositivo dispositivo, Cliente cliente, Banco banco) {
		super(dispositivo, cliente, banco);
	}

	

	public Transferencia(Dispositivo dispositivo,Cliente cliente, Banco banco, String numeroTransaccion, String cuentaDestino, double monto) {
		super(dispositivo, cliente,banco);
		this.numeroTransaccion = numeroTransaccion;
		this.cuentaDestino = cuentaDestino;
		this.monto = monto;
		this.getBanco().validarScore(this);
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
					this.getBanco().ingresarFraude(this.getCliente());
					System.out.println("Chequear que la lista de fraudes tenga algo");
					e.printStackTrace();
				}
			}
	
	
			//Rechazable
				@Override
				public Boolean monitorear(int i) throws FraudeException {
					confirmarSiFueFraude();
					marcarComoCasoSospechoso();
					monitorear();
							
					return false;
				}

}
