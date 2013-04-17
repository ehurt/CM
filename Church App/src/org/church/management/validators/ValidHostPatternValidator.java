package org.church.management.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.log4j.Logger;
import org.church.management.utils.UrlUtils;

@FacesValidator("ValidHostPatternValidator")
public class ValidHostPatternValidator implements Validator
{
	private static final Logger logger = Logger.getLogger(ValidHostPatternValidator.class);
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException 
	{
		if(value.toString().trim().equals(""))
		{
			logger.debug("ValidHostPatternValidator.validate()- Could not be blank.");
			throw new ValidatorException(new FacesMessage("URL Pattern cannot be blank."));
		}
		
		else if(UrlUtils.validPattern(value.toString()) == false)
		{
			logger.debug("ValidHostPatternValidator.validate()- The pattern is not valid: "+value.toString()+".");
			throw new ValidatorException(new FacesMessage("The pattern is not valid: "+value.toString()+"."));
		}
	}
}
