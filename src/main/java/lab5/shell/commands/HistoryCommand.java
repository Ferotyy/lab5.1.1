package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;


public class HistoryCommand extends CommandAbstract {


    public HistoryCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }

    @Override
    public void execute() {
        shell.printMessage("historyCommandText");
        shell.printf(shell.getCommandHistory().toString());

    }

    @Override
    protected String getCommandRegex() {
        return "[Hh][Ii][Ss][Tt][Oo][Rr][Yy]";
    }

    @Override
    public String getCommandName() {
        return "history";
    }
}

