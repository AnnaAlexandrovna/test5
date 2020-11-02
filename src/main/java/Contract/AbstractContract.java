package Contract;

import Client.ClientInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Класс с общей информацией о контрактах со свойствами <b>id</b>, <b>startDate</b>, <b>stopDate</b> и <b>listOfClient</b>
 */
public class AbstractContract {
    /** Поле id контракта */
    private UUID id;
    /** Поле начала действия контракта */
    private Date startDate;
    /** Поле окончания действия контракта */
    private Date stopDate;
    /** Поле с владельцем контракта*/
    private ClientInfo owner;

    /**
     * Конструктор для создания контракта с предзаполненными параметрами
     * @param startDate дата начала действия контракта. Пример - "28.01.2004"
     * @param stopDate дата окончания дейтсвия контракта. Пример - "28.01.2004"
     * @param owner владелец контракта.
     */
    public AbstractContract(String startDate, String stopDate, ClientInfo owner)  {
        try {
            this.startDate = new SimpleDateFormat( "dd.MM.yyyy" ).parse(startDate);
            this.stopDate = new SimpleDateFormat( "dd.MM.yyyy" ).parse(stopDate);
            this.owner = owner;
            this.id = UUID.randomUUID();
        } catch (ParseException exception){
            exception.printStackTrace();
            System.out.println("Возникла ошибка при обработке даты начала/прекращения действия контракта. Используйте формат - dd.MM.yyyy ");
        }
    }

    /**
     * Переопределенный метод toString для печати детального описания по контракту в консоль
     * @return детальное описание контракта. Пример - AbstractContract{id=442b5d21-387e-42fb-ad86-8b231396a27b, startDate=Wed Jan 28 00:00:00 MSK 2004, stopDate=Sun Jan 28 00:00:00 MSK 2024, owner=ClientInfo{id=9d6f8362-c165-4c5e-aac4-1cadd2e85b99, firstName='test', lastName='test', dateOfBirth=Mon May 01 00:00:00 MSD 2000, gender='man', passport='204823423423424'}}
     */
    @Override
    public String toString() {
        return "AbstractContract{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", stopDate=" + stopDate +
                ", listOfClient=" + owner +
                '}';
    }
    /**
     * Переопределенный метод equals. В расчете участвуют все поля - id, startDate, stopDate,  owner
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractContract)) return false;
        AbstractContract that = (AbstractContract) o;
        return id.equals(that.id) &&
                startDate.equals(that.startDate) &&
                stopDate.equals(that.stopDate) &&
                owner.equals(that.owner);
    }
    /**
     * Переопределенный метод hashCode. В расчете участвуют все поля - id, startDate, stopDate,  owner
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, stopDate, owner);
    }
    /**
     * Геттер для имени клиента
     * @return  id контракта. Пример - "9d6f8362-c165-4c5e-aac4-1cadd2e85b99"
     */
    public UUID getId() {
        return id;
    }
    /**
     * Геттер для даты начала действия контракта
     * @return  дата начала действия контракта. Пример - "Sun Oct 20 00:00:00 MSK 2024"
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * Сеттер для даты начала действия контракта
     * @param startDate дата начала действия контракта. Пример - "28.01.2001"
     * @throws ParseException если дата введена неверно
     */
    public void setStartDate(String startDate) throws ParseException {
        this.startDate = new SimpleDateFormat( "dd.MM.yyyy" ).parse(startDate);
    }
    /**
     * Геттер для даты окончания действия контракта
     * @return  имя клиента. Пример - "Sun Oct 20 00:00:00 MSK 2024"
     */
    public Date getStopDate() {
        return stopDate;
    }
    /**
     * Сеттер для даты окончания действия контракта
     * @param stopDate дата окончания дейтсвия контракта. Пример - "28.01.2001"
     * @throws ParseException при вводе неверного формата даты
     */
    public void setStopDate(String stopDate) throws ParseException {
        this.stopDate = new SimpleDateFormat( "dd.MM.yyyy" ).parse(stopDate);
    }
    /**
     * Геттер для получения владельца
     * @return  владелец контракта.
     */
    public ClientInfo getListOfClient() {
        return owner;
    }
    /**
     * Сеттер для установки владельца
     * @param owner список клиентов.
     */
    public void setListOfClient(ClientInfo owner) {
        this.owner= owner;
    }

}
