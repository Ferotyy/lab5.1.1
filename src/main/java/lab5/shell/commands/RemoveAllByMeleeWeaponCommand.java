package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;

import java.util.ArrayList;

public class RemoveAllByMeleeWeaponCommand extends CommandAbstract {
    public RemoveAllByMeleeWeaponCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }

    @Override
    public void execute() {
        String meleeWeapon = getMatchGroup(1);
        ArrayList<String> arraylist = new ArrayList<>();
        for(String key : smCollection.keySet()){
            if(smCollection.getSpaceMarine(key).getMeleeWeapon().toString().equals(meleeWeapon.toUpperCase())){
                arraylist.add(key);
            }
        }
        for(String s : arraylist){
            smCollection.remove(s);
        }
        System.out.println("Удалены все элементы, значение поля которых равно " + meleeWeapon.toUpperCase());
    }

    @Override
    protected String getCommandRegex() {
        return "[Rr][Ee][Mm][Oo][Vv][Ee]_[Aa][Ll][Ll]_[Bb][Yy]_[Mm][Ee][Ll][Ee][Ee]_[Ww][Ee][Aa][Pp][Oo][Nn]\\s+([\\w\\d]+)";
    }
}
