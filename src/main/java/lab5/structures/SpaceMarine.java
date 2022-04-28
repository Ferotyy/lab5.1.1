package lab5.structures;

import lab5.exceptions.IdentifierError;
import lab5.exceptions.ValidationError;

import java.time.LocalDateTime;


public class SpaceMarine {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double health; //Поле не может быть null, Значение поля должно быть больше 0
    private Long heartCount; //Поле не может быть null, Значение поля должно быть больше 0, Максимальное значение поля: 3
    private Weapon weaponType; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле не может быть null

    private String weapon;
    private String melee;
    private static Long lastSetID = 0L;
    private String valChapter = null;

    /**
     * Пустой конструктор класса spaceMarine
     */
    public SpaceMarine(){}

    /**
     *Конструктор для создания класса SpaceMarine
     * @param name имя
     * @param coordinates координаты
     * @param health здоровье
     * @param heartcount количество сердец
     * @param weapontype тип оружия
     * @param meleeweapon тип оружия ближнего боя
     * @param chapter глава
     * @param parentLegion подглава
     */
    public SpaceMarine(String name, Coordinates coordinates, Double health, Long heartcount, String weapontype, String meleeweapon, String chapter,String parentLegion) {
        this.name = name;
        this.coordinates = coordinates;
        this.health = health;
        this.heartCount = heartcount;
        this.weapon = weapontype;
        this.melee = meleeweapon;
        this.chapter = new Chapter(chapter, parentLegion);
        this.valChapter = chapter;
    }

    /**
     * Возвращает значение id
     */
    public long getId(){
        return id;
    }

    /**
     * Возвращает имя
     */
    public String getName(){
        return name;
    }

    /**
     * Возвращает координаты

     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * возвращает дату создания
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * возвращает здоровье
     */
    public Double getHealth() {
        return health;
    }

    /**
     * возвращает количество сердец
     */
    public Long getHeartCount() {
        return heartCount;
    }

    /**
     * возвращает оружие
     */
    public Weapon getWeapon() {
        return weaponType;
    }

    /**
     * возвращает оружие ближнего боя
     */
    public MeleeWeapon getMeleeWeapon(){
        return meleeWeapon;
    }

    /**
     * возвращает главу
     */
    public Chapter getChapter() {
        return chapter;
    }

    /**
     * задает значение chapter
     * @param chapter глава
     */
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
    /**
     * задает значение name
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * задает значение health
     * @param health здоровье
     */
    public void setHealth(Double health) {
        this.health = health;
    }
    /**
     * задает значение heartCount
     * @param heartCount количество сердец
     */
    public void setHeartCount(Long heartCount) {
        this.heartCount = heartCount;
    }
    /**
     * задает значение Weapon
     * @param weaponType оружие
     */
    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
    }
    /**
     * задает значение meleeWeapon
     * @param meleeWeapon оружие ближнего боя
     */
    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }
    /**
     * задает значение coordinates
     * @param coordinates координаты
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Задает значение id
     */
    public void setId(){
        id= ++lastSetID;
    }

    /**
     * Инициализирует значения id и creationDate
     */
    public void initialize() {
        if (!isInitialized() && isValid()) {
            setCurrentDateAsCreationDate();
            generateAndSetID();
        }
    }
    public void setCurrentDateAsCreationDate() {
        if (creationDate == null) {
            creationDate = LocalDateTime.now();
        }
    }
    public boolean isInitialized() {
        return id > 0;
    }
    private void generateAndSetID() {
        if (!isInitialized() && isExistsUnusedIDs()) {
            id = ++lastSetID;
        } else {
            throw new IdentifierError();
        }
    }

    /**
     * Проверяет можно ли добавить новые элементы в коллекцию
     */
    private boolean isExistsUnusedIDs() {
        return lastSetID + 1L > 0L;
    }

    /**
     * проверяет поля класса на валидность
     */
    public boolean isValid() {
        try {
            validateName();
            validateCoordinates();
            validateHealth();
            validateHeartCount();
            validateWeaponType();
            validateMeeleWeapon();
            validateChapter();
            return true;
        } catch (ValidationError e) {
            return false;
        }
    }
    public void validateChapter() throws ValidationError {
        chapter.validateName(this.valChapter);
    }
    public void validateHealth() throws ValidationError {
        validateHealth(this.health);
    }

    public void validateWeaponType() throws  ValidationError{
        validateWeaponType(this.weapon);
    }
    public void validateWeaponType(String weaponType)throws ValidationError {
        boolean notVal = true;
        if (Weapon.BOLTGUN.toString().equals(weaponType)) {
            this.weaponType = Weapon.BOLTGUN;
            notVal = false;
        }
        if (Weapon.HEAVY_FLAMER.toString().equals(weaponType)) {
            this.weaponType = Weapon.HEAVY_FLAMER;
            notVal = false;
        }
        if (Weapon.GRENADE_LAUNCHER.toString().equals(weaponType)) {
            this.weaponType = Weapon.GRENADE_LAUNCHER;
            notVal = false;
        }
        if (Weapon.INFERNO_PISTOL.toString().equals(weaponType)) {
            this.weaponType = Weapon.INFERNO_PISTOL;
            notVal = false;
        }
        if (Weapon.PLASMA_GUN.toString().equals(weaponType)) {
            this.weaponType = Weapon.PLASMA_GUN;
            notVal = false;
        }
        if(notVal){
            throw new ValidationError("Weapon must be initialized");
        }
    }

    public void validateMeeleWeapon() throws  ValidationError{
        validateMeeleWeapon(this.melee);
    }

    public void validateMeeleWeapon(String meeleWeaponType)throws ValidationError {
        boolean notVal = true;
        if (MeleeWeapon.CHAIN_SWORD.toString().equals(meeleWeaponType)) {
            this.meleeWeapon = MeleeWeapon.CHAIN_SWORD;
            notVal = false;
        }
        if (MeleeWeapon.LIGHTING_CLAW.toString().equals(meeleWeaponType)) {
            this.meleeWeapon = MeleeWeapon.LIGHTING_CLAW;
            notVal = false;
        }
        if (MeleeWeapon.MANREAPER.toString().equals(meeleWeaponType)) {
            this.meleeWeapon = MeleeWeapon.MANREAPER;
            notVal = false;
        }
        if (MeleeWeapon.POWER_BLADE.toString().equals(meeleWeaponType)) {
            this.meleeWeapon = MeleeWeapon.POWER_BLADE;
            notVal = false;
        }
        if(notVal){
            throw new ValidationError("MeleeWeapon must be initialized");
        }
    }

    public void validateHealth(Double health) throws ValidationError {
        if (health == null || health <= 0) {
            throw new ValidationError("Health must be positive float number");
        }
    }
    public void validateHeartCount() throws ValidationError {
        validateHeartCount(this.heartCount);
    }

    public void validateHeartCount(Long heartCount) throws ValidationError {
        if (heartCount == null || heartCount < 0 || heartCount > 3) {
            throw new ValidationError("Heartcount must be initialized, positive and lower then 3");
        }
    }
    public void validateName() throws ValidationError {
        validateName(this.name);
    }

    public void validateName(String name) throws ValidationError {
        if (name == null || name.isEmpty()) {
            throw new ValidationError("Name must be not null or empty.");
        }
    }
    public void validateCoordinates() throws ValidationError {
        validateCoordinates(this.coordinates);
    }

    public void validateCoordinates(Coordinates coords) throws ValidationError {
        if (coords == null || !coords.isValid()) {
            throw new ValidationError("Coordinates must be not null.");
        }
    }


    @Override
    public String toString() {
        return name+"{"+
                "\n id = " + id +
                "\n coordinates= " + coordinates +
                "\n creationDate= " + creationDate +
                "\n health= " + health +
                "\n heartCount= "+heartCount+
                "\n "+ chapter+
                "\n weaponType= " + weaponType +
                "\n meeleweapon= " + meleeWeapon +
                "\n}\n----------------------------------";
    }

    public String toString(String key) {
        return name+"{"+
                "\n key= " + key +
                "\n id = " + id +
                "\n coordinates= " + coordinates +
                "\n creationDate= " + creationDate +
                "\n health= " + health +
                "\n heartCount= "+heartCount+
                "\n "+ chapter+
                "\n weaponType= " + weaponType +
                "\n meeleweapon= " + meleeWeapon +
                "\n}\n----------------------------------";
    }
}
