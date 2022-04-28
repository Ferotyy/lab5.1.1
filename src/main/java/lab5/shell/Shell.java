package lab5.shell;

import lab5.exceptions.FileNotExistsException;
import lab5.exceptions.FileWrongPermissionsException;
import lab5.exceptions.ParsingError;
import lab5.shell.commands.*;
import lab5.structures.SMCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс управляет вводом и выводом информации, управляет обработкой вводимых команд.
 * Класс хранит историю команд, доступные команды и рабочую коллекцию SpaceMarines
 * @see CommandHistory
 * @see Command
 * @see SMCollection
 */

public class Shell {
    private CommandHistory commandHistory = new CommandHistory();
    private SMCollection smCollection;
    private ArrayList<Command> availableCommands;
    private boolean printHelpMessage = true;
    Scanner in;
    private boolean isShellActive = true;
    /**
     * Конструктор для создания "виртуальной" консоли,
     * которая не выводит вспомогательные сообщения при вводе,
     * также информация для ввода берется из указанного файла.
     * Ссылки на объекты, история и коллекция копируются из переданного экземпляра shell.
     * @param shell текущий рабочий класс
     * @param inputFilename имя файла хранящий в себе консольный скрипт
     * @throws FileNotFoundException в случае если файла не существует
     */
    public Shell(Shell shell, String inputFilename) throws FileNotFoundException {
        printHelpMessage = false;
        smCollection = shell.smCollection;
        commandHistory = shell.commandHistory;
        in = new Scanner(new File(inputFilename));
        prepareAvailableCommand();

    }
    /**
     * Конструктор по умолчанию
     * @param filename имя файла, из которого требуется получить коллекцию билетов.
     * @see SMCollection
     */
    public Shell (String filename) throws ParsingError {
        in = new Scanner(System.in);
        prepareSMCollection(filename);
        prepareAvailableCommand();

    }

    /**
     * Подготавливает список доступных команд
     */

    public void prepareAvailableCommand() {
        availableCommands = new ArrayList<>();
        availableCommands.add(new HelpCommand(this, smCollection));
        availableCommands.add(new InfoCommand(this, smCollection));
        availableCommands.add(new ShowCommand(this, smCollection));
        availableCommands.add(new ClearCommand(this, smCollection));
        availableCommands.add(new SaveCommand(this, smCollection));
        availableCommands.add(new HistoryCommand(this, smCollection));
        availableCommands.add(new InsertCommand(this, smCollection));
        availableCommands.add(new UpdateCommand(this, smCollection));
        availableCommands.add(new RemoveKeyCommand(this, smCollection));
        availableCommands.add(new ExecuteScriptCommand(this, smCollection));
        availableCommands.add(new PrintUniqueMeleeWeaponCommand(this, smCollection));
        availableCommands.add(new RemoveAllByMeleeWeaponCommand(this,smCollection));
        availableCommands.add(new CountLessThanHeartCountCommand(this,smCollection));
        availableCommands.add(new RemoveGreaterKeyCommand(this,smCollection));
        availableCommands.add(new RemoveGreaterCommand(this,smCollection));
        availableCommands.add(new ExitCommand(this,smCollection));
    }
    /**
     * Подготавливает коллекцию SpaceMarin'ов: выполняет парсинг данных из файла,
     * в случае некорректного имени файла либо возникновения ошибки при парсинге,
     * запрашивает новое имя файла у пользователя.
     * @param filename имя файла, из которого требуется получить коллекцию SpaceMarin'ов.
     */
    public void prepareSMCollection(String filename) throws ParsingError {
        while (true) {
            if (filename != null && !filename.isEmpty()) {
                String path = System.getenv(filename);
                String[] checkPath = path.split(";");
                if (checkPath.length == 1) {

                    try {
                        File file = new File(path);
                        if (!file.exists()) throw new FileNotExistsException();
                        if (!file.canRead()) throw new FileWrongPermissionsException("cannot read file");
                        smCollection = new SMCollection();
                        smCollection.loadFromXML(path);
                        break;
                    } catch (Exception ignored) {
                    }
                }
            }
                System.out.println(Message.getMessage("incorrectFileName"));
                filename = getUserInput();

        }
    }
    /**
     * Выводит в консоль сообщение.
     * @param messageCode код сообщения.
     */
    public void printMessage(String messageCode) {
        println(Message.getMessage(messageCode));
    }
    /**
     * Выводит в консоль форматированное сообщение.
     * @param messageCode код шаблона сообщения.
     * @param objects объекты для форматирования.
     */
    public void printfMessage(String messageCode, Object... objects) {
        printf(Message.getMessage(messageCode), objects);
    }

    public void printf(String format, Object... objects) {
        System.out.printf(format, objects);
    }

    /**
     * Выводит объект в консоль
     * @param message
     */
    public void println(Object message) {
        System.out.println(message);
    }
    public void SetScanner(){
        in = new Scanner(System.in);
    }

    /**
     * @return пользовательский ввод
     */
    public String getUserInput() {
        return getUserInput(Message.getMessage("consolePrefix"));
    }
    /**
     * Выводит вспомогательное сообщени и получает пользовательский ввод
     * @param helpText код вспомогательного сообщения при вводе
     * @return пользовательский ввод
     */
    public String getUserInput(String helpText) {
        if (printHelpMessage) {
            System.out.print(helpText);
        }
        try {
            return in.nextLine().trim();
        } catch (NoSuchElementException e) {
            stop();
            in = new Scanner(System.in);
            return "";
        }
    }

    /**
     * Проверяет, являются ли введенные данные командой, и если является то запускает программу
     * @param cmd введенное значение
     * @return
     */
    private boolean executeCommand(String cmd) {
        if (isShellActive) {
            for (Command command : availableCommands) {
                if (command.isThisCommand(cmd)) {
                    command.execute();
                    commandHistory.add(command);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Запускает консоль и считывает команды, до введения команды exit
     */
    public void run() {
        while (isShellActive) {
            String userInput = getUserInput();
            if (!executeCommand(userInput) && printHelpMessage) {
                printMessage("availableCommand");
            }
        }
    }

    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    public void stop() {
        isShellActive = false;
    }
}
