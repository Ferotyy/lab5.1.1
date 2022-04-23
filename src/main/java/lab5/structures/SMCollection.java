package lab5.structures;

import lab5.exceptions.EmptyPathException;
import lab5.exceptions.FileNotExistsException;
import lab5.exceptions.FileWrongPermissionsException;
import lab5.exceptions.ParsingError;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Set;

public class SMCollection {
    private String loadedFrom;
    private Hashtable<String, SpaceMarine> spaceMarines = new Hashtable<>();
    private LocalDateTime lastSaveDate = LocalDateTime.now();

    /**
     * Проверяет пустая ли коллекция
     */
    public boolean isEmpty() {
        return spaceMarines.isEmpty();
    }

    /**
     * Возвращает коллекцию SMCollection
     */
    public Hashtable<String, SpaceMarine> getSpaceMarines(){
        return spaceMarines;
    }

    /**
     * Возвращает SpaceMarine из коллекции по ее ключу
     * @param key ключ
     */
    public SpaceMarine getSpaceMarine(String key) {
        return spaceMarines.get(key);
    }

    /**
     * Возвращает множество ключей коллекции
     */
    public Set<String> keySet() {
        return spaceMarines.keySet();
    }

    /**
     * Добавляет в коллекцию элемент по ключу
     * @param key ключ
     * @param spaceMarine элемент
     */
    public void put(String key, SpaceMarine spaceMarine) {
        spaceMarines.put(key, spaceMarine);
    }

    /**
     * Возвращает откуда была загружена коллекция
     */
    public String  getLoadedFrom(){
        return loadedFrom;
    }

    /**
     * Удаляет элемент из коллекции по ее ключу
     * @param key ключ
     */
    public void remove(String key) {
        spaceMarines.remove(key);
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        spaceMarines.clear();
    }

    /**
     * возвращает размер коллекции
     */
    public int getSize() {
        return spaceMarines.size();
    }

    /**
     * Возвращает дату создания коллекции
     */
    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        return lastSaveDate.format(formatter)+" "+lastSaveDate.getHour()+":"+lastSaveDate.getMinute()+":"+lastSaveDate.getSecond();
    }

    /**
     * присваивает дате создания коллекции значение текущей даты
     */
    public void setDate() {
        lastSaveDate = LocalDateTime.now();
    }

    /**
     * Вызывает функцию для парсинга коллекции и записывает в файл, из которого была загружена коллекция
     * @throws IOException Если такого файла не существует или не хватает прав для записи в файл
     * @throws ParsingError Если возникают ошибки при парсинге в XML
     */
    public void dumpToXMLile() throws IOException, ParsingError {
        dumpToXMLFile(loadedFrom);
    }

    /**
     * Парсит коллекцию и записывает в файл, из которого была загружена коллекция
     * @param filename имя файла, в который записывается коллекция
     * @throws IOException если такого файла не существует или не хватает прав для записи в файл
     */
    protected void dumpToXMLFile(String filename) throws  IOException {
        if (filename == null) throw new EmptyPathException();
        File file = new File(filename);
        if (!file.exists()) throw new FileNotExistsException();
        if(!file.canRead()) throw new FileWrongPermissionsException("cannot read file");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("<spaceMarines>\n");
        for (String s : spaceMarines.keySet()) {
            SpaceMarine spaceMarine = spaceMarines.get(s);
            Coordinates coordinates = spaceMarine.getCoordinates();
            LocalDateTime creationDate = spaceMarine.getCreationDate();

            fileWriter.write("\t<spacemarine  name = \"" + spaceMarine.getName() + "\" id = \"" + spaceMarine.getId() +
                    "\" x = \"" + coordinates.getX() + "\" y = \"" + coordinates.getY() + "\" creationdate = \"" +
                    creationDate.toString() + "\" health = \"" + spaceMarine.getHealth() + "\" heartcount = \"" +
                    spaceMarine.getHeartCount() + "\" weapon = \"" + spaceMarine.getWeapon().toString() +
                    "\" meeleweapon = \"" + spaceMarine.getMeleeWeapon().toString() + "\" " + spaceMarine.getChapter().toString() +
                    "/>\n");
        }
        fileWriter.write("</spaceMarines>\n");
        fileWriter.flush();
        fileWriter.close();
        System.out.println("Коллекция сохранена");
    }

    /**
     * Парсит файл и заполняет коллекцию. Возвращает заполненную коллекцию.
     * @param filename имя файла
     * @throws ParsingError Если при парсинге возникли ошибки
     * @throws EmptyPathException Если путь к файлу пустой
     * @throws FileNotFoundException Если файл по заданному имени файла не найден
     * @throws FileWrongPermissionsException Если не хватает прав для чтения файла
     */
    protected Hashtable<String, SpaceMarine> parseXMLFile(String filename) throws ParsingError {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Hashtable<String, SpaceMarine> spaceMarines1 = new Hashtable<>();
        try {
            if (filename == null) throw new EmptyPathException();
            File file = new File(filename);
            if (!file.exists()) throw new FileNotExistsException();
            if(!file.canRead()) throw new FileWrongPermissionsException("cannot read file");
            InputStream is = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(bis);

            NodeList list = doc.getElementsByTagName("spacemarine");

            for (int temp = 0; temp < list.getLength(); temp++) {
                SpaceMarine spaceMarine;
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // get marine's attribute
                    String name = element.getAttribute("name");
                    String x = element.getAttribute("x");
                    String y = element.getAttribute("y");
                    String health = element.getAttribute("health");
                    String heartcount = element.getAttribute("heartcount");
                    String weapon = element.getAttribute("weapon");
                    String meeleweapon = element.getAttribute("meeleweapon");
                    String chapter = element.getAttribute("chapter");
                    String parentLegion = element.getAttribute("parentLegion");

                     spaceMarine= new SpaceMarine(name,
                             new Coordinates(Float.parseFloat(x), Double.parseDouble(y)),Double.parseDouble(health),
                             Long.parseLong(heartcount), weapon, meeleweapon, chapter,parentLegion);
                     spaceMarine.initialize();

                    if(spaceMarine.isValid()){
                        spaceMarines1.put(String.valueOf(spaceMarine.getId()),spaceMarine);

                    } else {
                        throw  new ParsingError("Загружены неверные или поврежденные данные");
                    }

                }
            }

        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
            e.printStackTrace();
        }

        return spaceMarines1;
    }


    /**
     * Загружает коллекцию из XML файла
     * @param fileName имя файла
     * @throws ParsingError Если возникли ошибки при парсинге.
     */
    public void loadFromXML(String fileName) throws ParsingError {
        this.spaceMarines = parseXMLFile(fileName);
        loadedFrom = fileName;
        setDate();
    }

}

