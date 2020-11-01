package Contract;

import java.text.ParseException;
import java.util.List;

/**
 * Класс с информацией о контракте типа "Цифровое ТВ" со свойством <b>packageNumber</b>
 */
public class TVContract extends AbstractContract {
    /** Поле номер пакета по контракту*/
    private int packageNumber;
    /**
     * Конструктор для создания контракта с предзаполненными параметрами
     * @param startDate дата начала действия контракта. Пример - "28.01.2004"
     * @param stopDate дата окончания дейтсвия контракта. Пример - "28.01.2004"
     * @param listOfClient список клиентов контракта.
     * @param packageNumber номер пакета по контракту. Пример - 6
     * @throws ParseException при неверном формате даты
     */
    public TVContract(String startDate, String stopDate, List listOfClient, int packageNumber) throws ParseException {
        super(startDate, stopDate, listOfClient);
        this.packageNumber = packageNumber;
    }
    /**
     * Переопределенный метод toString для печати детального описания по контракту в консоль
     * @return детальное описание контракта. Пример - TVContract{packageNumber=6}AbstractContract{id=d7d4abae-c9ae-4ed0-93a2-9a39eebe17c5, startDate=Mon Sep 15 00:00:00 MSD 2008, stopDate=Wed Dec 20 00:00:00 MSK 2023, listOfClient=[ClientInfo{id=9d6f8362-c165-4c5e-aac4-1cadd2e85b99, firstName='test', lastName='test', dateOfBirth=Mon May 01 00:00:00 MSD 2000, gender='man', passport='2048 23423423424'}]}
     */
    @Override
    public String toString() {
        return "TVContract{" +
                "packageNumber=" + packageNumber +
                '}' + super.toString();
    }
    /**
     * Геттер для номера пакета по контракту
     * @return  номер пакета по контракту. Пример - 6
     */
    public int getPackageNumber() {
        return packageNumber;
    }
    /**
     * Сеттер для номера пакета по контракту
     * @param packageNumber номер пакета по контракту. Пример - 6
     */
    public void setPackageNumber(int packageNumber) {
        this.packageNumber = packageNumber;
    }
}
