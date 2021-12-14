package ClasseDeDados;

public class ContaCorrente extends Conta {
	double taxa = 0.05;

	public ContaCorrente(String cpf_usuario, double saldo) throws Exception {
		super(cpf_usuario, saldo);
	}

	public ContaCorrente() {

	}

	public void sacar(double valor) {
		setSaldo(saldo = saldo - valor - taxa);
	}

	public void depositar(double valor) {
		setSaldo((saldo + valor) - taxa);
	}

	public void transferir(double valor, Conta destino) {
		sacar(valor);
		destino.depositar(valor);
	}
	
	public double getTaxa() {
		return taxa;
	}
}
