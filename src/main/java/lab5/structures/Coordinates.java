package lab5.structures;

import lab5.exceptions.ValidationError;

public class Coordinates {
    private Float x; //Максимальное значение поля: 893, Поле не может быть null
    private Double y; //Поле не может быть null

    /**
     * Пустой конструктор
     */
    public Coordinates() {
    }

    /**
     * конструктор класса coordinates
     * @param x координата x
     * @param y координата y
     */
    public Coordinates(Float x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает значение координаты x
     * @return
     */
    public Float getX() {
        return x;
    }

    /**
     * Задает значение координаты x
     * @param x координата x
     */
    public void setX(Float x) {
        this.x = x;
    }

    /**
     * возвращает значение координаты y
     * @return
     */
    public Double getY() {
        return y;
    }

    /**
     * Задает значение координаты y
     * @param y координата y
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * Проверяет на валидность координату X
     * @throws ValidationError Если координта X не валидна
     */
    public void validateX() throws ValidationError {
        validateX(this.x);
    }

    public void validateX(Float x) throws ValidationError {
        if (x == null) {
            throw new ValidationError("X must be not null");
        }
    }
    /**
     * Проверяет на валидность координату Y
     * @throws ValidationError Если координта Y не валидна
     */
    public void validateY() throws ValidationError {
        validateY(this.y);
    }

    public void validateY(Double y) throws ValidationError {
        if (y == null) {
            throw new ValidationError("Y must be not null");
        }
    }

    /**
     * Проверяет значения координат на валидность
     * @return
     */
    public boolean isValid() {
        try {
            validateX();
            validateY();
            return true;
        } catch (ValidationError e) {
            return false;
        }
    }

    /**
     * Переопределение метода toString
     * @return
     */
    @Override
    public String toString() {
        return "(" + x +  ", " + y + ")";
    }
}