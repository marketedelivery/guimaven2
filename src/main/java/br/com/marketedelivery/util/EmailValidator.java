package br.com.marketedelivery.util;

import java.util.Map;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.validate.ClientValidator;
/**
 * Custom JSF Validator for Email input
 */
@FacesValidator("custom.emailValidator")
public class EmailValidator implements Validator, ClientValidator {

	JSFMessageUtil mensagemUtil;
	private Pattern pattern;
	private static final String EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
										+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@SuppressWarnings("static-access")
	public EmailValidator() {
		pattern = pattern.compile(EMAIL);
		mensagemUtil = new JSFMessageUtil();
		}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}

	@Override
	public String getValidatorId() {
		return null;
	}

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		if(value == null){
			return;
		}
		if(!pattern.matcher(value.toString()).matches()){
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "email incorreto", 
							value + " Insira um emial valido."));
		}
	}

	
}
