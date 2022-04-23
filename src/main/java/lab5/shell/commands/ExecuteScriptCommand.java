package lab5.shell.commands;

import lab5.exceptions.RecursionException;
import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;

import java.io.FileNotFoundException;
import java.util.LinkedList;

public class ExecuteScriptCommand extends CommandAbstract {
    public ExecuteScriptCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }
    static LinkedList<String> scriptList = new LinkedList<>();
    public void execute() {
        try {
            Shell virtualShell = new Shell(shell, getMatchGroup(1));
            for (int i = 0; i < scriptList.size(); i++) {
                if (getMatchGroup(1).equals(scriptList.get(i))){
                    shell.printMessage("recursion");
                    throw new RecursionException();
                }
            }
            scriptList.addLast(getMatchGroup(1));
            virtualShell.run();
            scriptList.removeLast();
        } catch (FileNotFoundException | RecursionException e) {
            shell.printMessage("incorrectScriptFileName");
        }
    }

    @Override
    protected String getCommandRegex() {
        return "[Ee][Xx][Ee][Cc][Uu][Tt][Ee]_[Ss][Cc][Rr][Ii][Pp][Tt]\\s+(.+)";
    }
}
