package bank.commands;

import java.io.Serializable;

public interface ReturnValue extends Serializable {
	public boolean isOK();
	public Exception getException();
	public Serializable getResult();
	public void setOK(boolean isOK);
	public void setException(Exception exception);
	public void setResult(Serializable result);
}