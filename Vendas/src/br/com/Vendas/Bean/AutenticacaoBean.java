package br.com.Vendas.Bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.service.spi.ServiceRegistryAwareService;

import br.com.Vendas.DAO.FuncionariosDAO;
import br.com.Vendas.domain.Funcionario;
import br.com.Vendas.util.JSFUtil;
import br.com.Vendas.util.ServicosUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	
	private Funcionario funcionarioLogado;
	
	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null){
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}
	
	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}
	
	public String autenticar() {
		
		try{
			FuncionariosDAO funcionarioDAO = new FuncionariosDAO();
			funcionarioLogado = funcionarioDAO.autenticar(funcionarioLogado.getNome(), funcionarioLogado.getSenha());
			if (funcionarioLogado == null){
				JSFUtil.adicionaMensagemErro("Nome e/ou Senha inválidos!");
				return null;
			}else{
				JSFUtil.adicionarMensagemSucesso("Login efetuado com sucesso");
				ServicosUtil.startServices();
				return "/pages/principal.xhtml?faces-redirect=true";
			}
		} catch (RuntimeException e) {
			JSFUtil.adicionaMensagemErro("Erro ao tentar efetuar o login no sistema!");
			e.printStackTrace();
			return null;
		}
	}
	
	public String sair() {
		funcionarioLogado = null;
		JSFUtil.adicionarMensagemSucesso("Usuário deslogado com sucesso!");
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}
}
