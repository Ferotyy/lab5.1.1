package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;

import java.util.ArrayList;

public class RemoveGreaterKeyCommand extends CommandAbstract {
    public RemoveGreaterKeyCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }

    @Override
    public void execute() {
        Long id = Long.valueOf(getMatchGroup(1));
        ArrayList<String> arraylist = new ArrayList<>();
        for(String key : smCollection.keySet()){
            if(smCollection.getSpaceMarine(key).getId() > id){
                arraylist.add(key);
            }
        }
        for (String s : arraylist){
            smCollection.remove(s);
        }
        System.out.println("Удалены все элементы, ключ которых превышает заданный");
    }
    @Override
    protected String getCommandRegex() {
        return "[Rr][Ee][Mm][Oo][Vv][Ee]_[Gg][Rr][Ee][Aa][Tt][Ee][Rr]_[Kk][Ee][Yy]\\s+([\\w\\d]+)";
    }

}
