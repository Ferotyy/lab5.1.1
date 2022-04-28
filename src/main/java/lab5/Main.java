package lab5;

import lab5.shell.Shell;
import lab5.exceptions.ParsingError;

import java.util.Scanner;


public class Main {
  public static void main(String[] args) throws ParsingError {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Введите переменную окружения \n >>>");
      String defaultFilePath = scanner.nextLine();
      Shell shell = new Shell(defaultFilePath);
      System.out.println("--------Добро пожаловать--------");
      shell.run();
    }
}
