package Contract;

import Client.ClientInfo;


/**
 * Класс с информацией о котракте типа "Сотовая связь" со свойствами <b>minutesNum</b>, <b>gbNum</b> и <b>smsNum</b>.
 */
public class CellularContract extends AbstractContract {
    /**
     * Поле количества минут по контракту
     */
    private int minutesNum;
    /**
     * Поле количества Гб интернета по контракту
     */
    private int gbNum;
    /**
     * Поле количества смс по контракту
     */
    private int smsNum;

    /**
     * Конструктор для создания контракта с предзаполненными параметрами
     *
     * @param startDate  дата начала действия контракта. Пример - "28.01.2004"
     * @param stopDate   дата окончания дейтсвия контракта. Пример - "28.01.2004"
     * @param owner      владелец контракта.
     * @param minutesNum количество минут по контракту. Пример - 100
     * @param gbNum      количество Гб Интернета по контракту. Пример - 6
     * @param smsNum     количество смс по контракту. Пример - 150
     */
    public CellularContract(String startDate, String stopDate, ClientInfo owner, int minutesNum, int gbNum, int smsNum) {
        super(startDate, stopDate, owner);
        this.minutesNum = minutesNum;
        this.gbNum = gbNum;
        this.smsNum = smsNum;
    }

    /**
     * Переопределенный метод toString для печати детального описания контракта в консоль
     *
     * @return детальное описание контракта. Пример - CellularContract{minutesNum=100, gbNum=6, smsNum=150}AbstractContract{id=2340e487-05b8-4ece-b570-d1d9dbe55418, startDate=Fri Oct 20 00:00:00 MSD 2000, stopDate=Sun Oct 20 00:00:00 MSK 2024, owner=ClientInfo{id=9d6f8362-c165-4c5e-aac4-1cadd2e85b99, firstName='test', lastName='test', dateOfBirth=Mon May 01 00:00:00 MSD 2000, gender='man', passport='204823423423424'}}
     */
    @Override
    public String toString() {
        return "CellularContract{" +
                "minutesNum=" + minutesNum +
                ", gbNum=" + gbNum +
                ", smsNum=" + smsNum +
                '}' + super.toString();
    }

    /**
     * Геттер для количества минут по контракту
     *
     * @return количество минут по контракту. Пример - 100
     */
    public int getMinutesNum() {
        return minutesNum;
    }

    /**
     * Сеттер для количества минут по контракту
     *
     * @param minutesNum дата начала действия контракта. Пример - 100
     */
    public void setMinutesNum(int minutesNum) {
        this.minutesNum = minutesNum;
    }

    /**
     * Геттер для количества Гб Интернета по контракту
     *
     * @return количество Гб Интернета по контракту. Пример - 6
     */
    public int getGbNum() {
        return gbNum;
    }

    /**
     * Сеттер для количества Гб Интернета по контракту
     *
     * @param gbNum количество Гб Интернета по контракту. Пример - 6
     */
    public void setGbNum(int gbNum) {
        this.gbNum = gbNum;
    }

    /**
     * Геттер для количества смс по контракту
     *
     * @return количество смс по контракту. Пример - 100
     */
    public int getSmsNum() {
        return smsNum;
    }

    /**
     * Сеттер для количества смс по контракту
     *
     * @param smsNum количество смс по контракту. Пример - 100
     */
    public void setSmsNum(int smsNum) {
        this.smsNum = smsNum;
    }
    /**
     * Метод для получения полей для поика
     *
     */
    @Override
    protected void fillAllField() {
        super.fillAllField();
        this.allField.add("minutesNum");
        this.allField.add("gbNum");
        this.allField.add("smsNum");
    }
}
