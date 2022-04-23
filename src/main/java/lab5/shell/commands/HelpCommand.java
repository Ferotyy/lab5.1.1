package lab5.shell.commands;

import lab5.shell.*;
import lab5.shell.CommandAbstract;
import lab5.structures.SMCollection;

public class HelpCommand extends CommandAbstract {

    public HelpCommand(Shell shell, SMCollection smCollection) {
        super(shell,smCollection);
    }

    @Override
    public void execute() {
        shell.printMessage("helpCommandText");
    }

    @Override
    protected String getCommandRegex() {
        return "[Hh][Ee][Ll][Pp]\\s*([\\w\\d]+)?\\s*";
    }

    @Override
    public String getCommandName() {
        return "help";
    }
}
