package br.com.Vendas.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.util.HibernateUtil;

public class FuncionariosDAO {
	public void salvar (Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
		
		Transaction transacao = null; 
		
		try{
			transacao = sessao.beginTransaction(); 
			sessao.save(funcionario); 
			transacao.commit(); 
		}catch (RuntimeException ex){
			if (transacao != null){
				transacao.rollback(); 
			}
		}
		finally{
			sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Funcionario> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
		
		List<Funcionario> funcionario = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Funcionario.listar");
			funcionario = consulta.list(); 
		}catch (RuntimeException ex){
			throw ex;
		}
		finally{
			sessao.close();
		}
		return funcionario;
	}
	
	public Funcionario buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
		
		Funcionario funcionario = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Funcionario.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			funcionario = (Funcionario) consulta.uniqueResult();
		}catch (RuntimeException ex){
			throw ex;
		}
		finally{
			sessao.close();
		}
		return funcionario;
	}
	
	public void excluir (Funcionario funcionario) throws RuntimeException{
		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
		
		Transaction transacao = null; 
		
		try{
			transacao = sessao.beginTransaction(); 
			sessao.delete(funcionario); 
			transacao.commit(); 
		}catch (HibernateException ex){
			if (transacao != null){
				transacao.rollback(); 
			}
			throw new RuntimeException();
		}
		finally{
			sessao.close();
		}
		
	}
	
	public void editar (Funcionario funcionario) {
		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
		
		Transaction transacao = null; 
		
		try{
			transacao = sessao.beginTransaction(); 
			sessao.update(funcionario); 
			transacao.commit(); 
		}catch (RuntimeException ex){
			if (transacao != null){
				transacao.rollback(); 
			}
		}
		finally{
			sessao.close();
		}
		
	}
	
	public Funcionario autenticar(String nome, String senha){
		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
		Funcionario funcionario = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Funcionario.autenticar");
			consulta.setString("nome", nome);
			consulta.setString("senha", senha);
			funcionario = (Funcionario) consulta.uniqueResult();
		}catch (HibernateException ex){
			throw ex;
		}
		finally{
			sessao.close();
		}
		return funcionario;
	}
}
