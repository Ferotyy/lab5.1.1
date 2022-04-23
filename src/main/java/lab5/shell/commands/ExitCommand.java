package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;

public class ExitCommand extends CommandAbstract {
    public ExitCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }

    @Override
    public void execute() {
        shell.stop();
    }
}
