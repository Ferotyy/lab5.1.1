package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;

import java.util.ArrayList;

public class RemoveGreaterCommand extends CommandAbstract {
    public RemoveGreaterCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }

    @Override
    public void execute() {
        Double health = Double.parseDouble(getMatchGroup(1));
        ArrayList<String> arraylist = new ArrayList<>();
        for(String key : smCollection.keySet()){
            if(smCollection.getSpaceMarine(key).getHealth() > health){
                arraylist.add(key);
            }
        }
        for (String s : arraylist){
            smCollection.remove(s);
        }
        System.out.println("Удалены все элементы, значение Health которых превышает заданный");
    }
    @Override
    protected String getCommandRegex() {
        return "[Rr][Ee][Mm][Oo][Vv][Ee]_[Gg][Rr][Ee][Aa][Tt][Ee][Rr]\\s+(\\d*\\.?\\d+)\\s*";
    }
}
