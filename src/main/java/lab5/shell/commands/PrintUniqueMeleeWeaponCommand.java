package lab5.shell.commands;

import lab5.shell.CommandAbstract;
import lab5.shell.Shell;
import lab5.structures.SMCollection;

import java.util.*;


public class PrintUniqueMeleeWeaponCommand extends CommandAbstract {


    public PrintUniqueMeleeWeaponCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }

    @Override
    public void execute() {

        Hashtable<String,String > hashtable = new Hashtable<>();
        for (String key : smCollection.keySet()){
            hashtable.put(smCollection.getSpaceMarine(key).getMeleeWeapon().toString(),smCollection.getSpaceMarine(key).getMeleeWeapon().toString());
        }
        System.out.println("Уникальные значения поля MeleeWeapon: ");
        for(String key : hashtable.keySet()){
            System.out.println(hashtable.get(key));
        }
    }
    @Override
    protected String getCommandRegex() {
        return "[Pp][Rr][Ii][Nn][Tt]_[Uu][Nn][Ii][Qq][Uu][Ee]_[Mm][Ee][Ll][Ee][Ee]_[Ww][Ee][Aa][Pp][Oo][Nn]";
    }
    @Override
    public String getCommandName() {
        return "print_unique_melee_weapon";
    }
}
