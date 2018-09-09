package br.com.Vendas.util;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.Vendas.Bean.AutenticacaoBean;
import br.com.Vendas.domain.Funcionario;

@SuppressWarnings("serial")
public class AutenticacaoPhaseListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		UIViewRoot uiViewRoot = facesContext.getViewRoot();
		String paginaAtual = uiViewRoot.getViewId();
		
		boolean ePaginaAutenticacao = paginaAtual.contains("autenticacao.xhtml");
		
		if (!ePaginaAutenticacao){
			ExternalContext externalContext = facesContext.getExternalContext();
			Map<String, Object> mapa = externalContext.getSessionMap();
			AutenticacaoBean autenticacaoBean =  (AutenticacaoBean)mapa.get("autenticacaoBean");
			
			Funcionario funcionario = autenticacaoBean.getFuncionarioLogado();
			
			if (funcionario.getFuncao() == null){
				JSFUtil.adicionaMensagemErro("Funcionario não autenticado!");
				
				Application aplication = facesContext.getApplication();
				NavigationHandler navigationHandler = aplication.getNavigationHandler();
				navigationHandler.handleNavigation(facesContext, null, "/pages/autenticacao.xhtml?faces-redirect=true");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	
}
