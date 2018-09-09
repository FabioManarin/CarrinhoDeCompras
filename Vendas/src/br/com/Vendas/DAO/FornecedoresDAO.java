package br.com.Vendas.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.Vendas.domain.Fornecedor;
import br.com.Vendas.util.HibernateUtil;

public class FornecedoresDAO {

	public void salvar (Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession(); //Abre e cria a conexão
		
		Transaction transacao = null; //inicia a transacao como null
		
		try{
			transacao = sessao.beginTransaction(); // abrindo a transacao
			sessao.save(fornecedor); // salva os dados
			transacao.commit(); // confirma a transacao
		}catch (RuntimeException ex){
			if (transacao != null){
				transacao.rollback(); // desfaz a transação
			}
		}
		finally{
			sessao.close();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> listar(){
		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
		
		List<Fornecedor> fornecedores = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Fornecedor.listar");
			fornecedores = consulta.list(); 
		}catch (RuntimeException ex){
			throw ex;
		}
		finally{
			sessao.close();
		}
		return fornecedores;
	}
	
	public Fornecedor buscarPorCodigo(Long codigo){
		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
		
		Fornecedor fornecedor = null;
		
		try{
			Query consulta = sessao.getNamedQuery("Fornecedor.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			fornecedor = (Fornecedor) consulta.uniqueResult();
		}catch (RuntimeException ex){
			throw ex;
		}
		finally{
			sessao.close();
		}
		return fornecedor;
	}
	
	public void excluir (Fornecedor fornecedor) throws RuntimeException {
		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
		
		Transaction transacao = null; 
		
		try{
			transacao = sessao.beginTransaction(); 
			sessao.delete(fornecedor); 
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
	
//	public void excluir (Long codigo) {
//		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
//		
//		Transaction transacao = null; 
//		
//		try{
//			transacao = sessao.beginTransaction(); 
//			Fornecedor fornecedor = buscarPorCodigo(codigo);
//			sessao.delete(fornecedor); 
//			transacao.commit(); 
//		}catch (RuntimeException ex){
//			if (transacao != null){
//				transacao.rollback(); 
//			}
//		}
//		finally{
//			sessao.close();
//		}
//	}
	
	public void editar (Fornecedor fornecedor) {
		Session sessao = HibernateUtil.getSessionFactory().openSession(); 
		
		Transaction transacao = null; 
		
		try{
			transacao = sessao.beginTransaction(); 
			sessao.update(fornecedor); 
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
}
