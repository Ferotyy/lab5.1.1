package lab5.shell.commands;

import lab5.shell.*;
import lab5.exceptions.IdentifierDoesNotExistError;
import lab5.structures.SMCollection;
import lab5.structures.SpaceMarine;

public class UpdateCommand extends CommandAbstract{
    public UpdateCommand(Shell shell, SMCollection smCollection) {
        super(shell,smCollection);
    }

    @Override
    public void execute() {
        try {
            SpaceMarine spaceMarine = getSpaceMarineByID(getID().toString());
            SpaceMarineForm spaceMarineForm = new SpaceMarineForm(shell, spaceMarine);
            spaceMarineForm.updateBoundSpaceMarine();
        } catch (IdentifierDoesNotExistError e) {
            shell.printMessage("identifier_does_not_exist");
        }
    }

    protected Long getID() throws IdentifierDoesNotExistError {
        try {
            return Long.parseLong(getMatchGroup(1));
        } catch (NumberFormatException e) {
            throw new IdentifierDoesNotExistError();
        }
    }

    protected SpaceMarine getSpaceMarineByID(String id) throws IdentifierDoesNotExistError {
        for (String key : smCollection.keySet()) {
            SpaceMarine spaceMarine;
            spaceMarine = smCollection.getSpaceMarine(key);
            if (spaceMarine.getId() == Long.parseLong(id)) {
                return spaceMarine;
            }
        }
        throw new IdentifierDoesNotExistError();
    }

    @Override
    protected String getCommandRegex() {
        return "[Uu][Pp][Dd][Aa][Tt][Ee]\\s+(.+)";
    }
}
