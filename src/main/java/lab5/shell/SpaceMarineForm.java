package lab5.shell;

import lab5.exceptions.ValidationError;
import lab5.structures.*;

import java.util.NoSuchElementException;

public class SpaceMarineForm {
    private SpaceMarine spaceMarine;
    private Shell shell;
    private boolean boundForm = false;

    public SpaceMarineForm(Shell shell) {
        this.boundForm = true;
        this.shell = shell;
        this.spaceMarine = new SpaceMarine();
        this.spaceMarine.setCoordinates(new Coordinates());
    }
    public SpaceMarineForm(Shell shell, SpaceMarine spaceMarine) {
        this.shell = shell;
        this.spaceMarine = spaceMarine;
    }
    public SpaceMarine getSpaceMarine() {
        fillSpaceMarine();
        spaceMarine.setCurrentDateAsCreationDate();
        spaceMarine.setId();
        return spaceMarine;
    }
    public void updateBoundSpaceMarine() {
        fillSpaceMarine();
    }

    protected void fillSpaceMarine(){
        fillSpaceMarineName();
        fillCoordinateX();
        fillCoordinateY();
        fillHealth();
        fillHeartCount();
        fillWeaponType();
        fillMeleeWeaponType();
        fillChapter();
    }
    protected void fillSpaceMarineName() {
        while (true) {
            try {
                String name = shell.getUserInput("Введите имя: ");
                spaceMarine.validateName(name);
                spaceMarine.setName(name);
                break;
            } catch (ValidationError e) {
                shell.printMessage("spaceMarineForm_invalidName");
            } catch (NoSuchElementException e){
                shell.SetScanner();
            }
        }
    }
    protected void fillCoordinateX() {
        while (true) {
            try {
                Float x = Float.parseFloat(shell.getUserInput("Введите координату x: "));
                spaceMarine.getCoordinates().validateX(x);
                spaceMarine.getCoordinates().setX(x);
                break;
            } catch (ValidationError | NumberFormatException e) {
                shell.printMessage("spaceMarineForm_invalidX");
            }
        }
    }
    protected void fillCoordinateY() {
        while (true) {
            try {
                Double y = Double.parseDouble(shell.getUserInput("Введите координату y: "));
                spaceMarine.getCoordinates().validateY(y);
                spaceMarine.getCoordinates().setY(y);
                break;
            } catch (ValidationError | NumberFormatException e) {
                shell.printMessage("spaceMarineForm_invalidY");
            }
        }
    }
    protected void fillHealth() {
        while (true) {
            try {
                String input = shell.getUserInput("Введите значение Health(Должно быть больше 0): ");
                Double health = null;
                if (!input.isEmpty()) {
                    health = Double.parseDouble(input);
                }

                spaceMarine.validateHealth(health);
                spaceMarine.setHealth(health);
                break;
            } catch (ValidationError | NumberFormatException e) {
                shell.printMessage("spaceMarineForm_invalidHealth");
            }
        }
    }

    protected void fillHeartCount() {
        while (true) {
            try {
                String input = shell.getUserInput("Введите значение HeartCount(Должно быть больше 0. Максимальное значение: 3):");
                Long heartCount = null;
                if (!input.isEmpty()) {
                    heartCount = Long.parseLong(input);
                }

                spaceMarine.validateHeartCount(heartCount);
                spaceMarine.setHeartCount(heartCount);
                break;
            } catch (ValidationError | NumberFormatException e) {
                shell.printMessage("spaceMarineForm_invalidHeartCount");
            }
        }
    }

    protected void fillChapter(){
        while (true) {
            String chapterName = shell.getUserInput("Введите значение Chapter:");
            String parentLegin = shell.getUserInput("Введите значение ParentLegion(или оставьте поле пустым):");
            Chapter chapter = new Chapter();
            if (chapterName != null && !chapterName.equals("")) {
                chapter.setName(chapterName);
                chapter.setParentLegion(parentLegin);
                spaceMarine.setChapter(chapter);
                break;
            }
            shell.printMessage("spaceMarineForm_invalidChapter");
        }
    }
    protected void fillMeleeWeaponType() {
        while (true) {
            String input = shell.getUserInput("Введите значение MeleeWeapon(доступные значения: CHAIN_SWORD, MANREAPER, LIGHTING_CLAW, POWER_BLADE):").toUpperCase();
            MeleeWeapon meleeWeaponType = null;

            if (!validateMeeleWeapon(input)) {
                spaceMarine.setMeleeWeapon(MeleeWeapon.valueOf(input));
                break;
            }
            shell.printMessage("spaceMarineForm_invalidMeleeWeaponType");
        }
    }

    protected void fillWeaponType(){
        while(true) {

            String input = shell.getUserInput("Введите значение Weapon(доступные значения: BOLTGUN, PLASMA_GUN, GRENADE_LAUNCHER, INFERNO_PISTOL, HEAVY_FLAMER):").toUpperCase();
            Weapon WeaponType = null;
            if (!validateWeaponType(input)) {
                spaceMarine.setWeaponType(Weapon.valueOf(input));
                break;
            }

            shell.printMessage("spaceMarineForm_invalidWeaponType");

        }
    }




    protected boolean validateWeaponType(String weaponType)throws IllegalArgumentException {
        boolean notVal = true;
        if (Weapon.BOLTGUN.toString().equals(weaponType)) {
            notVal = false;
        }
        if (Weapon.HEAVY_FLAMER.toString().equals(weaponType)) {
            notVal = false;
        }
        if (Weapon.GRENADE_LAUNCHER.toString().equals(weaponType)) {
            notVal = false;
        }
        if (Weapon.INFERNO_PISTOL.toString().equals(weaponType)) {
            notVal = false;
        }
        if (Weapon.PLASMA_GUN.toString().equals(weaponType)) {
            notVal = false;
        }
        return notVal;
    }
    protected boolean validateMeeleWeapon(String meleeWeaponType)throws IllegalArgumentException {
        boolean notVal = true;
        if (MeleeWeapon.CHAIN_SWORD.toString().equals(meleeWeaponType)) {
            notVal = false;
        }
        if (MeleeWeapon.LIGHTING_CLAW.toString().equals(meleeWeaponType)) {
            notVal = false;
        }
        if (MeleeWeapon.MANREAPER.toString().equals(meleeWeaponType)) {
            notVal = false;
        }
        if (MeleeWeapon.POWER_BLADE.toString().equals(meleeWeaponType)) {
            notVal = false;
        }
        return notVal;
    }

}
