package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;


public class RemoveKeyCommand extends CommandAbstract {
    public RemoveKeyCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }

    @Override
    public void execute() {
        String key = getMatchGroup(1);

        smCollection.remove(key);

    }

    @Override
    protected String getCommandRegex() {
        return "^[Rr][Ee][Mm][Oo][Vv][Ee]_[Kk][Ee][Yy]\\s+([\\w\\d]+)$";
    }

    @Override
    public String getCommandName() {
        return "remove_key";
    }
}
