package ClasseDeDados;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import Movimentos.Operacoes;

public class ClasseTesteJunit {
	@Test
	public void testaUsuarioCadastradoCorretamente() throws IOException{
		String[] dadosUsuarioParaCadastro= {"1","Nicolas Fernandes Sousa","(61) 6177-5641","187.667.429-60","M"};
		//cadastrando usuario com o construtor do objeto usuario
		Usuario u= new Usuario(dadosUsuarioParaCadastro[1],dadosUsuarioParaCadastro[2],dadosUsuarioParaCadastro[3],dadosUsuarioParaCadastro[4].charAt(0));
		Operacoes op = new Operacoes();
		//pegando os dados do usuario cadastrado no arquivo txt
		String[] dadosUsuarioCadastrado = op.consultaCPF("187.667.429-60", "Usuarios.txt");
		//conferindo se os dados passados para cadastro sao os mesmos pegados no arquivo depois de cadastrado
		Assert.assertEquals(dadosUsuarioParaCadastro, dadosUsuarioCadastrado);
	}
	@Test
	public void testeSacarContaCorrente(){
		String[] u={"1","Rafaela Costa","(12) 8269-8842","158.359.744-18","F"};
		ContaCorrente conta_titular = new ContaCorrente();
		conta_titular.recriarObjetoConta(1, "158.359.744-18",550.05, u);
		conta_titular.sacar(50);
		Assert.assertEquals(500, conta_titular.getSaldo(), 0);
	}
	
}