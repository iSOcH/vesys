package bank.commands;

import java.io.Serializable;

public class ReturnValueDefault implements ReturnValue {
	private boolean isOK = true;
	private Exception exception = null;
	private Serializable result = null;
	
	public ReturnValueDefault() { };
	
	public ReturnValueDefault(boolean isOK, Exception exception, Serializable result) {
		super();
		this.isOK = isOK;
		this.exception = exception;
		this.result = result;
	}

	@Override
	public void setOK(boolean isOK) {
		this.isOK = isOK;
	}

	@Override
	public void setException(Exception exception) {
		this.exception = exception;
	}

	@Override
	public void setResult(Serializable result) {
		this.result = result;
	}
	
	@Override
	public boolean isOK() {
		return isOK;
	}

	@Override
	public Exception getException() {
		return exception;
	}

	@Override
	public Serializable getResult() {
		return result;
	}
}