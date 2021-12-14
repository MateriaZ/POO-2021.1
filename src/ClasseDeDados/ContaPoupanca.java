package ClasseDeDados;

public class ContaPoupanca extends Conta {

	public ContaPoupanca(String cpf_usuario, double saldo) throws Exception {
		super(cpf_usuario, saldo);
	}

	public ContaPoupanca() {

	}

	public void atualizaSaldo(double percentual) {
		double TempSaldo;
		TempSaldo = (percentual * getSaldo() / 100) + getSaldo();
		setSaldo(TempSaldo);
	}

	public void transferir(double valor, Conta destino) {
		sacar(valor);
		destino.depositar(valor);
	}

}
