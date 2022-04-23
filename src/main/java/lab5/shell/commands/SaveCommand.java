package lab5.shell.commands;

import lab5.shell.*;
import lab5.exceptions.ParsingError;

import lab5.structures.SMCollection;

import java.io.IOException;


public class SaveCommand extends CommandAbstract {

    public SaveCommand(Shell shell, SMCollection smCollection) {
        super(shell,smCollection);
    }

    @Override
    public void execute() {
        try {
            smCollection.dumpToXMLile();
        } catch (ParsingError e) {
            shell.printMessage("Файл не сохранен");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getCommandRegex() {
        return "[Ss][Aa][Vv][Ee]";
    }


    @Override
    public String getCommandName() {
        return "save";
    }
}
