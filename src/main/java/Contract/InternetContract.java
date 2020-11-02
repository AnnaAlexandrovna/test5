package Contract;

import Client.ClientInfo;

import java.text.ParseException;
import java.util.List;

/**
 * Класс с информацией о контракте типа "Интернет" со свойством <b>maxSpeed</b>
 */
public class InternetContract extends AbstractContract{
    /** Поле максимальной скорости интернета по контракту */
    private int maxSpeed;

    /**
     * Конструктор для создания контракта с предзаполненными параметрами
     * @param startDate дата начала действия контракта. Пример - "28.01.2004"
     * @param stopDate дата окончания дейтсвия контракта. Пример - "28.01.2004"
     * @param owner владелец контракта.
     * @param maxSpeed максимальная скорость работы интернета по контракту. Пример - 350
     */
    public InternetContract(String startDate, String stopDate, ClientInfo owner, int maxSpeed)  {
        super(startDate, stopDate, owner);
        this.maxSpeed = maxSpeed;
    }
    /**
     * Переопределенный метод toString для печати детального описания по контракту в консоль
     * @return детальное описание контракта. Пример - InternetContract{maxSpeed=350}AbstractContract{id=442b5d21-387e-42fb-ad86-8b231396a27b, startDate=Wed Jan 28 00:00:00 MSK 2004, stopDate=Sun Jan 28 00:00:00 MSK 2024, owner=ClientInfo{id=9d6f8362-c165-4c5e-aac4-1cadd2e85b99, firstName='test', lastName='test', dateOfBirth=Mon May 01 00:00:00 MSD 2000, gender='man', passport='2048 23423423424'}}
     */
    @Override
    public String toString() {
        return "InternetContract{" +
                "maxSpeed=" + maxSpeed +
                '}'+ super.toString();
    }
    /**
     * Геттер для максимальной скорости работы интернета по контракту
     * @return  максимальная скорость работы интернета по контракту. Пример - 100
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }
    /**
     * Сеттер для максимальной скорости работы интернета по контракту
     * @param maxSpeed максимальная скорость работы интернета по контракту. Пример - 100
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
