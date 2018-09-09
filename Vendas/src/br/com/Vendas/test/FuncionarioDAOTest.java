package br.com.Vendas.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.Vendas.DAO.FuncionariosDAO;
import br.com.Vendas.domain.Funcionario;


public class FuncionarioDAOTest {
	
	@Test
	@Ignore
	public void salvar (){
		Funcionario f1 = new Funcionario();
		f1.setNome("Pedro Duarte");
		f1.setCpf("09090127924");
		f1.setSenha("123123");
		f1.setFuncao("Balconista");
		
		FuncionariosDAO dao = new FuncionariosDAO();
		dao.salvar(f1);
	}
	
	@Test
	@Ignore
	public void listar() {
		FuncionariosDAO dao = new FuncionariosDAO();
		List<Funcionario> funcionarios = dao.listar();
		
		for(Funcionario funcionario : funcionarios){
			System.out.println(funcionario);
		}
	}
	
	@Test
	@Ignore
	public void buscarProCodigo() {
		FuncionariosDAO dao = new FuncionariosDAO();
		Funcionario f1 = dao.buscarPorCodigo(3L);
		
		System.out.println(f1);
		
	}
	
	@Test
	@Ignore
	public void excluir() {
		FuncionariosDAO dao = new FuncionariosDAO();
		Funcionario funcionario = dao.buscarPorCodigo(3L);
		dao.excluir(funcionario);		
	}
	
//	@Test
//	@Ignore
//	public void excluirPorCodigo() {
//		FornecedoresDAO dao = new FornecedoresDAO();
//		dao.excluir(2L);
//		
//	}
	
	@Test
	@Ignore
	public void editar() {
		FuncionariosDAO dao = new FuncionariosDAO();
		Funcionario funcionario = dao.buscarPorCodigo(4L);
		funcionario.setNome("Larissa Graci");
		funcionario.setSenha("123123");
		funcionario.setCpf("19911919191");
		funcionario.setFuncao("Gerente");
		dao.editar(funcionario);
		
	}
	
	@Test
	@Ignore
	public void autenticar(){
		FuncionariosDAO funcionarioDAO = new FuncionariosDAO();
		
		Funcionario funcionario = funcionarioDAO.autenticar("Larissa Graci", "123123");
		
		Assert.assertNotNull(funcionario);
	}
}
