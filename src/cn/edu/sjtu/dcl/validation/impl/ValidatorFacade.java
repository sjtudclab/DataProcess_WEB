package cn.edu.sjtu.dcl.validation.impl;

import java.util.Vector;

import cn.edu.sjtu.dcl.validation.IFacade;
import cn.edu.sjtu.dcl.validation.IValidator;

public class ValidatorFacade implements IFacade
{
	private Vector<IValidator> validators;
	
//	public ValidatorFacade()
//	{
//		validators = new Vector<IValidator>();
//		validators.add(new SchemaValidator(schema));
//		validators.add(new LoopValidator());
//	}
	
	public ValidatorFacade(String schema)
	{
		validators = new Vector<IValidator>();
		validators.add(new SchemaValidator(schema));
		validators.add(new LoopValidator());
	}
	
	public boolean validate(String jcdlPath)
	{
		for(IValidator v : validators)
		{
			if(!v.validate(jcdlPath))
			{
				return false;
			}
		}
		return true;
	}
}
