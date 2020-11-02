package Client;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
/**
 * Класс с информацией о клиенте со свойствами <b>id</b>, <b>firstName</b>, <b>lastName</b>, <b>dateOfBirth</b>, <b>gender</b> и <b>passport</b>.
 */

public class ClientInfo {
    /** Поле id клиента */
    private UUID id;
    /** Поле имя клиента */
    private String firstName;
    /** Поле фамилия клиента */
    private String lastName;
    /** Поле дата рождения клиента */
    private Date dateOfBirth;
    /** Поле пол клиента */
    private String gender;
    /** Поле паспортные данные клиента */
    private String passport;
    /** Поле возраст клиента */
    private int age;

    /**
     * Конструктор - создание нового объекта с определенными значениями, поле id формируется с помощью java.util.UUID
     *@param firstName  имя клиента
     *@param lastName фамилия клиента
     *@param dateOfBirth дата рождения клиента в формате "dd.MM.yyyy"
     *@param gender пол клиента ("man"/"woman")
     *@param passport серия и номер паспорта клиента
     */
    public ClientInfo( String firstName, String lastName, String dateOfBirth, String gender, String passport)  {
        try {
            this.id = UUID.randomUUID();
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateOfBirth = new SimpleDateFormat( "dd.MM.yyyy" ).parse(dateOfBirth);
            this.gender = gender;
            this.passport = passport;
            this.updateAge();
        } catch (ParseException exception){
            exception.printStackTrace();
            System.out.println("Возникла ошибка при обработке даты рождения клиента. Используйте формат - dd.MM.yyyy ");
        }
    }

    /**
     * Переопределенный метод toString для вывода в консоль информации о клиенте
     */
    @Override
    public String toString() {
        return "ClientInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
    /**
     * Переопределенный метод equals. В сравнении участвуют все поля объекта - id, firstName, lastName, dateOfBirth, gender, passport
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientInfo)) return false;
        ClientInfo that = (ClientInfo) o;
        return id.equals(that.id) &&
                firstName.equals(that.firstName) &&
                lastName.equals(that.lastName) &&
                dateOfBirth.equals(that.dateOfBirth) &&
                gender.equals(that.gender) &&
                passport.equals(that.passport);
    }
    /**
     * Переопределенный метод hashCode. В составлении хеша участвую все поля объекта - id, firstName, lastName, dateOfBirth, gender, passport
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, gender, passport);
    }
    /**
     * Геттер для имени клиента
     * @return  имя клиента. Пример - "Иван"
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Сеттер для имени клиента
     * @param firstName имя клиента. Пример - "Иван"
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Геттер для фамилии клиента
     * @return фамилия клиента. Пример - "Иванов"
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Сеттер для фамилии клиента
     * @param lastName фамилия клиента. Пример - "Иванов"
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Геттер для даты рождения клиента
     * @return дата рождения клиента. Пример - Wed Jan 28 00:00:00 MSK 2004
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    /**
     * Сеттер для даты рождения клиента
     * @param dateOfBirth дата рождения клиента. Пример - "28.01.2004"
     * @throws ParseException при вводе неверного формата даты
     */
    public void setDateOfBirth(String dateOfBirth) throws ParseException {
        this.dateOfBirth = new SimpleDateFormat( "dd.MM.yyyy" ).parse(dateOfBirth);
    }
    /**
     * Геттер для пола клиента
     * @return дата рождения клиента. Пример - "man"/"woman"
     */
    public String getGender() {
        return gender;
    }
    /**
     * Сеттер для пола клиента
     * @param gender пол клиента. Пример - "man"/"woman"
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    /**
     * Геттер для серии и номера паспорта клиента
     * @return серия и номер паспорта клиента. Пример - "1104000000"
     */
    public String getPassport() {
        return passport;
    }
    /**
     * Сеттер для серии и номера паспорта клиента
     * @param passport фамилия клиента. Пример - "1104000000"
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }
    /**
     * Геттер для возраста клиента
     * @return возраст клиента. Пример - 20
     */
    public int getAge() {
        return age;
    }
    /**
     * Метод обновления возраста клиента
     */
    public void updateAge(){
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        this.age = Period.between(this.dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getYears();
    }

}
