package lab5.shell.commands;

import lab5.shell.*;
import lab5.structures.SMCollection;
import lab5.structures.SpaceMarine;

public class InsertCommand extends CommandAbstract {
    public InsertCommand(Shell shell, SMCollection smCollection) {
        super(shell, smCollection);
    }
    @Override
    protected String getCommandRegex() {
        return "[Ii][Nn][Ss][Ee][Rr][Tt]\\s+(.+)";
    }

    @Override
    public void execute() {
        SpaceMarineForm spaceMarineForm = new SpaceMarineForm(shell);
        SpaceMarine spaceMarine;
        spaceMarine = spaceMarineForm.getSpaceMarine();
        smCollection.put(String.valueOf(spaceMarine.getId()),spaceMarine);
    }

}
