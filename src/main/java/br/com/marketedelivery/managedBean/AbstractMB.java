package br.com.marketedelivery.managedBean;

import org.primefaces.context.RequestContext;

import br.com.marketedelivery.util.JSFMessageUtil;

public class AbstractMB {
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
	
	public AbstractMB() {
        super();
    }
	
		protected void displayErrorMessageToUser(String message) {
			JSFMessageUtil messagem =  new JSFMessageUtil();
			messagem.sendErrorMessageToUser(message);
		}
		protected void displayInfoMessageToUser(String message) {
			JSFMessageUtil messagem = new JSFMessageUtil();
			messagem.sendInfoMessageToUser(message);
		}
		protected void closeDialog(){
			getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
		}
		protected void keepDialogOpen(){
			getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
		}
		protected RequestContext getRequestContext(){
			return RequestContext.getCurrentInstance();
		}
		
}
