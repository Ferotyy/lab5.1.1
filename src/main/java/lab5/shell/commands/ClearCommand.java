package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;

public class ClearCommand extends CommandAbstract {

    public ClearCommand(Shell shell, SMCollection smCollection) {
        super(shell,smCollection);
    }

    @Override
    public void execute() {
        smCollection.clear();
    }

    @Override
    protected String getCommandRegex() {
        return "[Cc][Ll][Ee][Aa][Rr]";
    }

    @Override
    public String getCommandName() {
        return "clear";
    }
}
