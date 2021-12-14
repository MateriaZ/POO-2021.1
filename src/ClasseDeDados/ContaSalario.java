package ClasseDeDados;

public class ContaSalario extends ContaCorrente {
	double taxa = 0.01;

	public ContaSalario(String cpf_usuario, double saldo) throws Exception {
		super(cpf_usuario, saldo);
	}

	public ContaSalario() {
		// TODO Auto-generated constructor stub
	}

	public void sacar(double valor) {
		setSaldo(saldo = saldo - valor - taxa);
	}

	public void depositar(double valor) {
		setSaldo(saldo = saldo + valor - taxa);
	}

	public void transferir(double valor, Conta destino) {
		sacar(valor);
		destino.depositar(valor);
	}

}
