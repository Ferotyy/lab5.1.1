package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;

import java.io.FileNotFoundException;

public class ExecuteScriptCommand extends CommandAbstract {
    public ExecuteScriptCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }

    public void execute() {
        try {
            Shell virtualShell = new Shell(shell, getMatchGroup(1));
            virtualShell.run();
        } catch (FileNotFoundException e) {
            shell.printMessage("incorrectScriptFileName");
        }
    }

    @Override
    protected String getCommandRegex() {
        return "[Ee][Xx][Ee][Cc][Uu][Tt][Ee]_[Ss][Cc][Rr][Ii][Pp][Tt]\\s+(.+)";
    }
}
