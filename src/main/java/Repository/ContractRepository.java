package Repository;

import Contract.AbstractContract;

import java.util.Arrays;
import java.util.UUID;
/**
 * Класс с информацией о клиенте со свойствами <b>listOfContract</b>, <b>defaultNumOfContracts</b>, <b>counter</b>
 */
public class ContractRepository  {
    /** Поле для хранения массива контрактов */
    private AbstractContract[] listOfContract;
    /** Поле для первоначального размера массива */
    private final int defaultNumOfContracts = 15;
    /** Поле для счетчика добавленных контрактов */
    private int counter = 0;
    /**
     * Конструктор - создание нового объекта с пустым массивом вместимостью defaultNumOfContracts элементов
     */
    public ContractRepository() {
        this.listOfContract = new AbstractContract[defaultNumOfContracts];
    }
    /**
     * Переопределенный метод equals. В сравнении участвует поле listOfContract
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContractRepository)) return false;
        ContractRepository that = (ContractRepository) o;
        return Arrays.equals(listOfContract, that.listOfContract);
    }
    /**
     * Геттер для количества добавленных контрактов
     * @return  количество добавленных контрактов. Пример - 8
     */
    public int getCounter() {
        return counter;
    }
    /**
     * Переопределенный метод hashCode. В сравнении участвует поле listOfContract
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(listOfContract);
    }

    /**
     * Геттер для получения массива добавленных контрактов
     * @return  массив добавленных контрактов.
     */
    public AbstractContract[] getListOfContract() {
        return listOfContract;
    }

    /**
     * Метод получения контарта по id
     * @param id UUID контракта. Пример - 442b5d21-387e-42fb-ad86-8b231396a27b
     * @return  найденный по id контракт или null
     */
    public AbstractContract getContractById(UUID id) {

        for (AbstractContract abstractContract : listOfContract) {
            if (abstractContract.getId().equals(id)) {
                return abstractContract;
            }
        }
        return null;
    }
    /**
     * Метод добавления контракта
     * @param abstractContract добавляемый контракт.
     */
    public void addContract(AbstractContract abstractContract) {
        if (counter >= listOfContract.length) {
            AbstractContract[] arr = new AbstractContract[listOfContract.length * 2];
            System.arraycopy(listOfContract, 0, arr, 0, listOfContract.length);
            listOfContract = arr;
        }

        listOfContract[counter] = abstractContract;
        counter++;
    }
    /**
     * Метод удаления контракта по id
     * @param id UUID контракта.
     */
    public void removeContract(UUID id) {
        for (int i = 0; i < listOfContract.length; i++) {
            if (listOfContract[i].getId().equals(id)) {
                System.arraycopy(listOfContract, i + 1, listOfContract, i, counter - i);
                counter--;
                break;
            }
        }
    }

}
