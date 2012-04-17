package bank.commands;

public abstract class AbstractCmd implements Command {
	protected ReturnValue retval;

	public AbstractCmd() {
		retval = new ReturnValueDefault();
	}

	@Override
	public ReturnValue getResult() {
		return retval;
	}
}