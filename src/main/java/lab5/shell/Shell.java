//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package lab5.shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import lab5.exceptions.FileNotExistsException;
import lab5.exceptions.FileWrongPermissionsException;
import lab5.exceptions.ParsingError;
import lab5.shell.commands.ClearCommand;
import lab5.shell.commands.CountLessThanHeartCountCommand;
import lab5.shell.commands.ExecuteScriptCommand;
import lab5.shell.commands.ExitCommand;
import lab5.shell.commands.HelpCommand;
import lab5.shell.commands.HistoryCommand;
import lab5.shell.commands.InfoCommand;
import lab5.shell.commands.InsertCommand;
import lab5.shell.commands.PrintUniqueMeleeWeaponCommand;
import lab5.shell.commands.RemoveAllByMeleeWeaponCommand;
import lab5.shell.commands.RemoveGreaterCommand;
import lab5.shell.commands.RemoveGreaterKeyCommand;
import lab5.shell.commands.RemoveKeyCommand;
import lab5.shell.commands.SaveCommand;
import lab5.shell.commands.ShowCommand;
import lab5.shell.commands.UpdateCommand;
import lab5.structures.SMCollection;

public class Shell {
    private CommandHistory commandHistory = new CommandHistory();
    private SMCollection smCollection;
    private ArrayList<Command> availableCommands;
    private boolean printHelpMessage = true;
    Scanner in;
    private boolean isShellActive = true;

    public Shell(Shell shell, String inputFilename) throws FileNotFoundException {
        this.printHelpMessage = false;
        this.smCollection = shell.smCollection;
        this.commandHistory = shell.commandHistory;
        this.in = new Scanner(new File(inputFilename));
        this.prepareAvailableCommand();
    }

    public Shell(String filename) throws ParsingError {
        this.in = new Scanner(System.in);
        this.prepareSMCollection(filename);
        this.prepareAvailableCommand();
    }

    public void prepareAvailableCommand() {
        this.availableCommands = new ArrayList();
        this.availableCommands.add(new HelpCommand(this, this.smCollection));
        this.availableCommands.add(new InfoCommand(this, this.smCollection));
        this.availableCommands.add(new ShowCommand(this, this.smCollection));
        this.availableCommands.add(new ClearCommand(this, this.smCollection));
        this.availableCommands.add(new SaveCommand(this, this.smCollection));
        this.availableCommands.add(new HistoryCommand(this, this.smCollection));
        this.availableCommands.add(new InsertCommand(this, this.smCollection));
        this.availableCommands.add(new UpdateCommand(this, this.smCollection));
        this.availableCommands.add(new RemoveKeyCommand(this, this.smCollection));
        this.availableCommands.add(new ExecuteScriptCommand(this, this.smCollection));
        this.availableCommands.add(new PrintUniqueMeleeWeaponCommand(this, this.smCollection));
        this.availableCommands.add(new RemoveAllByMeleeWeaponCommand(this, this.smCollection));
        this.availableCommands.add(new CountLessThanHeartCountCommand(this, this.smCollection));
        this.availableCommands.add(new RemoveGreaterKeyCommand(this, this.smCollection));
        this.availableCommands.add(new RemoveGreaterCommand(this, this.smCollection));
        this.availableCommands.add(new ExitCommand(this, this.smCollection));
    }

    public void prepareSMCollection(String filename) throws ParsingError {
        while(true) {
            if (filename != null && !filename.isEmpty()) {
                String path = System.getenv(filename);
                String[] checkPath = path.split(";");
                if (checkPath.length == 1) {
                    try {
                        File file = new File(path);
                        if (!file.exists()) {
                            throw new FileNotExistsException();
                        }

                        if (!file.canRead()) {
                            throw new FileWrongPermissionsException("cannot read file");
                        }

                        this.smCollection = new SMCollection();
                        this.smCollection.loadFromXML(path);
                        return;
                    } catch (Exception var5) {
                    }
                }
            }

            System.out.println(Message.getMessage("incorrectFileName"));
            filename = this.getUserInput();
        }
    }

    public void printMessage(String messageCode) {
        this.println(Message.getMessage(messageCode));
    }

    public void printfMessage(String messageCode, Object... objects) {
        this.printf(Message.getMessage(messageCode), objects);
    }

    public void printf(String format, Object... objects) {
        System.out.printf(format, objects);
    }

    public void println(Object message) {
        System.out.println(message);
    }

    public void SetScanner() {
        this.in = new Scanner(System.in);
    }

    public String getUserInput() {
        return this.getUserInput(Message.getMessage("consolePrefix"));
    }

    public String getUserInput(String helpText) {
        if (this.printHelpMessage) {
            System.out.print(helpText);
        }

        try {
            return this.in.nextLine().trim();
        } catch (NoSuchElementException var3) {
            this.stop();
            this.in = new Scanner(System.in);
            return "";
        }
    }

    private boolean executeCommand(String cmd) {
        if (this.isShellActive) {
            Iterator var2 = this.availableCommands.iterator();

            while(var2.hasNext()) {
                Command command = (Command)var2.next();
                if (command.isThisCommand(cmd)) {
                    command.execute();
                    this.commandHistory.add(command);
                    return true;
                }
            }
        }

        return false;
    }

    public void run() {
        while(this.isShellActive) {
            String userInput = this.getUserInput();
            if (!this.executeCommand(userInput) && this.printHelpMessage) {
                this.printMessage("availableCommand");
            }
        }

    }

    public CommandHistory getCommandHistory() {
        return this.commandHistory;
    }

    public void stop() {
        this.isShellActive = false;
    }
}
