package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;

public class CountLessThanHeartCountCommand extends CommandAbstract {
    public CountLessThanHeartCountCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }

    @Override
    public void execute() {
        Long heartCount = Long.parseLong(getMatchGroup(1));
        Long i = 0L;
        for(String key : smCollection.keySet()){
            if(smCollection.getSpaceMarine(key).getHeartCount() < heartCount){
                i++;
            }
        }
        System.out.println("Количество элементов, значение поля heartCount меньше заданного: "+i);
    }
    @Override
    protected String getCommandRegex() {
        return "[Cc][Oo][Uu][Nn][Tt]_[Ll][Ee][Ss][Ss]_[Tt][Hh][Aa][Nn]_[Hh][Ee][Aa][Rr][Tt]_[Cc][Oo][Uu][Nn][Tt]\\s+([\\d])+";
    }
}
