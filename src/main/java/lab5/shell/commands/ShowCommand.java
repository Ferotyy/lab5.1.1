package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;

public class ShowCommand extends CommandAbstract {

    public ShowCommand(Shell shell, SMCollection smCollection) {
        super(shell,smCollection);
    }

    @Override
    public void execute() {
        if(smCollection.isEmpty()){
            System.out.println("Коллекция пуста");
        } else{
            for (String key : smCollection.keySet()){
                System.out.println(smCollection.getSpaceMarine(key).toString(key));
            }
        }
    }

    @Override
    protected String getCommandRegex() {
        return "[Ss][Hh][Oo][Ww]";
    }

    @Override
    public String getCommandName() {
        return "show";
    }
}
