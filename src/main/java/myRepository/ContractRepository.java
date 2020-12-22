package myRepository;

import Client.ClientInfo;
import Contract.AbstractContract;
import Reflection.AutoInjectable;
import Sorter.ISorter;

import java.util.*;
import java.util.function.Predicate;

/**
 * Класс с информацией о клиенте со свойствами <b>listOfContract</b>, <b>defaultNumOfContracts</b>, <b>counter</b>
 */
public class ContractRepository {
    /**
     * Поле для хранения массива контрактов
     */
    private AbstractContract[] listOfContract;
    /**
     * Поле для первоначального размера массива
     */
    private final int defaultNumOfContracts = 15;
    /**
     * Поле для счетчика добавленных контрактов
     */
    private int counter = 0;
    /**
     * Поле для учета всех сущетсвующих владельцев контрактов
     */
    private Set<ClientInfo> allOwners;
    /**
     * Поле для выбора способа сортировки контрактов
     */
 //@AutoInjectable
      ISorter sorter;

    @AutoInjectable
    List<ISorter> sorter1;

    /**
     * Конструктор - создание нового объекта с пустым массивом вместимостью defaultNumOfContracts элементов
     */
    public ContractRepository() {
        this.listOfContract = new AbstractContract[defaultNumOfContracts];
        this.allOwners = new HashSet<>();
    }

    /**
     * Конструктор - создание нового объекта с переданным массивом контрактов
     *
     * @param listOfContract список контрактов
     */
    public ContractRepository(AbstractContract[] listOfContract) {
        this.listOfContract = listOfContract;
        this.counter = listOfContract.length;
        this.allOwners = new HashSet<>();
        this.addAllOwner(listOfContract);
    }

    @Override
    public String toString() {
        return "ContractRepository{" +
                "listOfContract=" + Arrays.toString(listOfContract) +
                ", defaultNumOfContracts=" + defaultNumOfContracts +
                ", counter=" + counter +
                ", allOwners=" + allOwners +
                ", sorter=" + sorter +
                ", sorter1=" + sorter1 +
                '}';
    }

    /**
     * Метод добавления владельцев контрактов в Set
     *
     * @param listOfContract список контрактов
     */
    private void addAllOwner(AbstractContract[] listOfContract) {
        for (AbstractContract contract : listOfContract) {
            this.allOwners.add(contract.getOwner());
        }
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
     *
     * @return количество добавленных контрактов. Пример - 8
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
     *
     * @return массив добавленных контрактов.
     */
    public AbstractContract[] getListOfContract() {
        return Arrays.copyOfRange(this.listOfContract, 0, this.getCounter());
    }

    /**
     * Метод получения контарта по id
     *
     * @param id UUID контракта. Пример - 442b5d21-387e-42fb-ad86-8b231396a27b
     * @return найденный по id контракт или null
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
     *
     * @param abstractContract добавляемый контракт.
     */
    public void addContract(AbstractContract abstractContract) {
        if (counter >= listOfContract.length) {
            AbstractContract[] arr = new AbstractContract[listOfContract.length * 2];
            System.arraycopy(listOfContract, 0, arr, 0, listOfContract.length);
            listOfContract = arr;
        }

        listOfContract[counter] = abstractContract;
        allOwners.add(abstractContract.getOwner());
        counter++;
    }

    /**
     * Метод удаления контракта по id
     *
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

    /**
     * Метод поиска контракта.
     *
     * @param predicate по какому признаку будем искать контракт и как определять, удовлетворяет ли контракт условиям поиска
     * @return объект типа Репозиторий, удовлетворяюший условиям поиска
     */
    public ContractRepository searchContract(Predicate<AbstractContract> predicate) {

        List<AbstractContract> arrOfObj = new ArrayList<>();
        for (AbstractContract contract : this.getListOfContract()) {
            if (predicate.test(contract)) {
                arrOfObj.add(contract);
            }
        }
        System.out.println(arrOfObj.size());
        if (arrOfObj.size() == 0) {
            System.out.println("По условиям поиска контракт не найден");
        }
        return new ContractRepository(arrOfObj.toArray(new AbstractContract[arrOfObj.size()]));
    }

    /**
     * Сеттер для установки массива добавленных контрактов
     *
     * @param listOfContract массив добавленных контрактов.
     */
    public void setListOfContract(AbstractContract[] listOfContract) {
        this.listOfContract = listOfContract;
    }

    /**
     * Метод сортировки контрактов.
     *
     * @param comparator метод сравнения
     */
    public void sort(Comparator<AbstractContract> comparator) {
        this.listOfContract = sorter.sort(comparator, this.getListOfContract());
    }

    /**
     * Метод сортировки контрактов.
     *
     * @return список всех владельцев контрактов
     **/
    public Set<ClientInfo> getAllOwners() {
        return allOwners;
    }
}
