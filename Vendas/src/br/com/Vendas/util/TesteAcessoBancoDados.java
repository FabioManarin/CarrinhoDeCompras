package br.com.Vendas.util;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.Vendas.domain.Funcionario;

public class TesteAcessoBancoDados implements Runnable {

    @Override
    public void run() {

        while (true) {

            //if(ServicosUtil.contador>200)
            ServicosUtil.noUsuarios = acessoAoBanco();
            System.out.println("Teste");
            ServicosUtil.contador++;
            try {
				Thread.sleep(60000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

    }

    public int acessoAoBanco() {
        List<Funcionario> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {

            session.beginTransaction();
            list = session.createQuery("select a from Funcionario a").list();
            session.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println("Erro executando comando" + e.getMessage());
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }

        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

}
