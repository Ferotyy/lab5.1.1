package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;


public class InfoCommand extends CommandAbstract {
    public InfoCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }

    @Override
    public void execute() {
        shell.printfMessage("infoCommandFormat",
                smCollection.getDate(), smCollection.getSize(), smCollection.getLoadedFrom());
    }

    @Override
    protected String getCommandRegex() {
        return "[Ii][Nn][Ff][Oo]";
    }

    @Override
    public String getCommandName() {
        return "info";
    }
}
