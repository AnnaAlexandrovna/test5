package Contract;

import Client.ClientInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Класс с общей информацией о контрактах со свойствами <b>id</b>, <b>startDate</b>, <b>stopDate</b> и <b>listOfClient</b>
 */
public class AbstractContract {
    /**
     * Поле id контракта
     */
    private UUID id;
    /**
     * Поле начала действия контракта
     */
    private Date startDate;
    /**
     * Поле окончания действия контракта
     */
    private Date stopDate;
    /**
     * Поле с владельцем контракта
     */
    private ClientInfo owner;
    /**
     * Поле с массивом всех доступных полей класса
     */
    protected List<String> allField = new ArrayList<>();
    /**
     * Поле с массивом общих полей классов
     */
    protected List<String> sortField = new ArrayList<>();

    /**
     * Конструктор для создания контракта с предзаполненными параметрами
     *
     * @param startDate дата начала действия контракта. Пример - "28.01.2004"
     * @param stopDate  дата окончания дейтсвия контракта. Пример - "28.01.2004"
     * @param owner     владелец контракта.
     */
    public AbstractContract(String startDate, String stopDate, ClientInfo owner) {
        try {
            this.startDate = new SimpleDateFormat("dd.MM.yyyy").parse(startDate);
            this.stopDate = new SimpleDateFormat("dd.MM.yyyy").parse(stopDate);
            this.owner = owner;
            this.id = UUID.randomUUID();
            this.fillAllField();
            this.fillSortField();
        } catch (ParseException exception) {
            exception.printStackTrace();
            System.out.println("Возникла ошибка при обработке даты начала/прекращения действия контракта. Используйте формат - dd.MM.yyyy ");
        }
    }

    /**
     * Переопределенный метод toString для печати детального описания по контракту в консоль
     *
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
     *
     * @return id контракта. Пример - "9d6f8362-c165-4c5e-aac4-1cadd2e85b99"
     */
    public UUID getId() {
        return id;
    }

    /**
     * Геттер для даты начала действия контракта
     *
     * @return дата начала действия контракта. Пример - "Sun Oct 20 00:00:00 MSK 2024"
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Сеттер для даты начала действия контракта
     *
     * @param startDate дата начала действия контракта. Пример - "28.01.2001"
     * @throws ParseException если дата введена неверно
     */
    public void setStartDate(String startDate) throws ParseException {
        this.startDate = new SimpleDateFormat("dd.MM.yyyy").parse(startDate);
    }

    /**
     * Геттер для даты окончания действия контракта
     *
     * @return имя клиента. Пример - "Sun Oct 20 00:00:00 MSK 2024"
     */
    public Date getStopDate() {
        return stopDate;
    }

    /**
     * Сеттер для даты окончания действия контракта
     *
     * @param stopDate дата окончания дейтсвия контракта. Пример - "28.01.2001"
     * @throws ParseException при вводе неверного формата даты
     */
    public void setStopDate(String stopDate) throws ParseException {
        this.stopDate = new SimpleDateFormat("dd.MM.yyyy").parse(stopDate);
    }


    /**
     * Сеттер для установки владельца
     *
     * @param owner список клиентов.
     */
    public void setListOfClient(ClientInfo owner) {
        this.owner = owner;
    }

    /**
     * Метод установки полей, по которым можно выполнять поиск
     *
     */
    protected void fillAllField() {
        this.allField.add("id");
        this.allField.add("startDate");
        this.allField.add("stopDate");
        this.allField.add("ownerId");
        this.allField.add("ownerFirstName");
        this.allField.add("ownerLastName");
        this.allField.add("ownerDateOfBirth");
        this.allField.add("ownerGender");
        this.allField.add("ownerPassport");
        this.allField.add("ownerAge");
    }
    /**
     * Метод установки полей, по которым можно выполнять сортировку. Сортировка имеет смысл только для общих значений
     *
     */
    protected void fillSortField() {
        this.sortField.add("id");
        this.sortField.add("startDate");
        this.sortField.add("stopDate");
    }
    /**
     * Метод получения полей для выполнения поиска
     *@return названия полей, по которым можно выполнять поиск
     */
    public List<String> getAllField() {
        return allField;
    }
    /**
     * Метод говорящий о том, можно ли выполнить поиск по полю
     *@param parOfSearching поле, по которому планируют выполнять поиск
     *@return можно ли выполнять поиск по parOfSearching
     */
    public boolean isTheFieldInContract(String parOfSearching) {
        return allField.contains(parOfSearching);
    }

    /**
     * Метод говорящий о том, можно ли выполнить сортировку по полю
     *@param parOfSort поле, по которому планируют выполнять сортировку
     *@return можно ли выполнять сортировку по parOfSort
     */
    public boolean isTheFieldInSortField(String parOfSort) {
        return sortField.contains(parOfSort);
    }
    /**
     * Геттер для получения владельца
     *
     * @return владелец контракта.
     */
    public ClientInfo getOwner() {
        return owner;
    }
    /**
     * Метод сравнения контракто при сортировке
     *@param contract контракт, с которым будем сравнивать текущий
     *@param parOfSort параметр сортировки
     *@return положительное значение, если контракт больше; отрицательное значение, если контракт меньше; 0, если равны
     */
    public int compare(AbstractContract contract, String parOfSort) {
        if (parOfSort.equals("startDate")) {
            return this.startDate.compareTo(contract.getStartDate());
        } else if (parOfSort.equals("stopDate")) {
            return this.stopDate.compareTo(contract.getStopDate());
        } else if (parOfSort.equals("id")) {
            return this.id.compareTo(contract.getId());
        }
        return 0;
    }


}
