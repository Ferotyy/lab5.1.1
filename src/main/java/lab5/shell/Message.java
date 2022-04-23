package lab5.shell;

import java.util.Hashtable;


import java.util.Set;

public class Message {
    protected static Set<String> availableLanguages;
    protected static String currentLanguage = "ru";
    private static Hashtable<String,String> hashtable;
    static {
        Hashtable<String, String> hashtable1 = new Hashtable();
        hashtable1.put("helloMessage","--------Добро пожаловать--------\nВведите help для просмотра команд");
        hashtable1.put("consolePrefix",">>> ");
        hashtable1.put("incorrectFileName","Некорректное имя файла.\nВведите новое имя файла: ");
        hashtable1.put("helpCommandText","help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "insert null {element} : добавить новый элемент с заданным ключом \n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному \n" +
                "remove_key null : удалить элемент из коллекции по его ключу \n" +
                "clear : очистить коллекцию \n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. \n" +
                "exit : завершить программу (без сохранения в файл) \n" +
                "remove_greater {Health} : удалить из коллекции все элементы, превышающие заданный \n" +
                "history : вывести последние 12 команд (без их аргументов) \n" +
                "remove_greater_key null : удалить из коллекции все элементы, ключ которых превышает заданный \n" +
                "remove_all_by_melee_weapon meleeWeapon : удалить из коллекции все элементы, значение поля meleeWeapon которого " +
                "эквивалентно заданному \n" +
                "count_less_than_heart_count heartCount : вывести количество элементов, значение поля heartCount которых меньше заданного \n" +
                "print_unique_melee_weapon : вывести уникальные значения поля meleeWeapon всех элементов в коллекции \n");
        hashtable1.put("historyCommandText", "Последние выполненные команды: ");
        hashtable1.put("infoCommandFormat","=== Информация о коллекции ===\nПоследнее сохранение: %s;\nКоличество элементов: %d;\nРабочий файл: %s\n");

        hashtable1.put("spaceMarineForm_invalidName", "Имя не должно быть пустым.\n");
        hashtable1.put("spaceMarineForm_invalidX","Неверное значение координаты x.\n");
        hashtable1.put("spaceMarineForm_invalidY","Неверное значение координаты y.\n");
        hashtable1.put("spaceMarineForm_invalidHealth","Неверное значение Health\n");
        hashtable1.put("spaceMarineForm_invalidHeartCount","Неверное значение HeartCount\n");
        hashtable1.put("spaceMarineForm_invalidChapter","Неверное значение Chapter\n");
        hashtable1.put("spaceMarineForm_invalidWeaponType","Неверное значение WeaponType\n");
        hashtable1.put("spaceMarineForm_invalidMeleeWeaponType","Неверное значение MeleeWeapon\n");
        hashtable1.put("availableCommand","Недоступная команда. \n Введите \"help\" для справки.\n");
        hashtable1.put("incorrectScriptFileName","Некорректное имя файла");
        hashtable1.put("recursion","При выполнении скрипта возникает рекурсия");
        hashtable = hashtable1;
    }





    static String getMessage(String messageCode) {
        return hashtable.get(messageCode);
    }

    static void changeLanguage(String language) {
        if (availableLanguages.contains(language)) {
            currentLanguage = language;
        }
    }

}
