package lab5;

import lab5.shell.Shell;
import lab5.exceptions.ParsingError;


public class Main {
  public static void main(String[] args) throws ParsingError {
      final String defaultFilePath = "/Users/artemiy/IdeaProjects/lab5.1.1/src/main/resources/CollectionXML.xml";
      Shell shell = new Shell(defaultFilePath);
      System.out.println("--------Добро пожаловать--------");
      shell.run();
    }
}