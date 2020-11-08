package Repository;

import Contract.AbstractContract;
import Contract.CellularContract;
import Contract.InternetContract;
import Contract.TVContract;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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

    public List<AbstractContract> searchContract(String parOfSearch, String value){
        List<AbstractContract> arrOfObj = new ArrayList<AbstractContract>();
        for(int i=0; i<counter; i++){
            try {
                if (listOfContract[i].isTheFieldInContract(parOfSearch)){
                    if (parOfSearch.equals("id")){
                        if ((listOfContract[i].getId()).toString().equals(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if (parOfSearch.equals("startDate")){
                        String formatter = new SimpleDateFormat("dd.MM.yyyy").format(listOfContract[i].getStartDate());
                        if (formatter.equals(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    } else if (parOfSearch.equals("stopDate")){
                        String formatter = new SimpleDateFormat("dd.MM.yyyy").format(listOfContract[i].getStopDate());
                        if (formatter.equals(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    } else if(parOfSearch.equals("ownerId")){
                        if(listOfContract[i].getOwner().getId().toString().equals(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("ownerFirstName")){
                        if(listOfContract[i].getOwner().getFirstName().equals(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("ownerLastName")){
                        if(listOfContract[i].getOwner().getLastName().equals(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("ownerDateOfBirth")){
                        String formatter = new SimpleDateFormat("dd.MM.yyyy").format(listOfContract[i].getOwner().getDateOfBirth());
                        if(formatter.equals(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("ownerGender")){
                        if(listOfContract[i].getOwner().getGender().equals(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("ownerPassport")){
                        if(listOfContract[i].getOwner().getPassport().equals(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("ownerAge")){
                        if(listOfContract[i].getOwner().getAge() == Integer.parseInt(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("maxSpeed")){
                        InternetContract contract = (InternetContract) listOfContract[i];
                        if(contract.getMaxSpeed() == Integer.parseInt(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("minutesNum")){
                        CellularContract contract = (CellularContract ) listOfContract[i];
                        if(contract.getMinutesNum() == Integer.parseInt(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("gbNum")){
                        CellularContract contract = (CellularContract ) listOfContract[i];
                        if(contract.getGbNum() == Integer.parseInt(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("smsNum")){
                        CellularContract contract = (CellularContract ) listOfContract[i];
                        if(contract.getSmsNum() == Integer.parseInt(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }else if(parOfSearch.equals("packageNumber")){
                        TVContract contract = (TVContract) listOfContract[i];
                        if(contract.getPackageNumber() == Integer.parseInt(value)){
                            arrOfObj.add(listOfContract[i]);
                        }
                    }
                }else {
                    System.out.println("У контрактов нет заданного свойства");
                }
            }catch (Exception e){
                System.out.println("При поиске возникла ошибка");
                e.printStackTrace();
            }

        }
        System.out.println(arrOfObj.size());
        if(arrOfObj.size()==0){
            System.out.println("По условиям поиска контракт не найден");
        }
        return arrOfObj;
    }

    public List<AbstractContract> searchContract1(String parOfSort){
        List<AbstractContract> arrOfObj = new ArrayList<AbstractContract>();
        for(int i=0; i<counter; i++){
            try {
                if (listOfContract[i].isTheFieldInContract(parOfSort)){
                    if (parOfSort.equals("id")){

                    }else if (parOfSort.equals("startDate")){
                        String formatter = new SimpleDateFormat("dd.MM.yyyy").format(listOfContract[i].getStartDate());

                    } else if (parOfSort.equals("stopDate")){
                        String formatter = new SimpleDateFormat("dd.MM.yyyy").format(listOfContract[i].getStopDate());

                    } else if(parOfSort.equals("ownerId")){

                    }else if(parOfSort.equals("ownerFirstName")){

                    }else if(parOfSort.equals("ownerLastName")){

                    }else if(parOfSort.equals("ownerDateOfBirth")){
                        String formatter = new SimpleDateFormat("dd.MM.yyyy").format(listOfContract[i].getOwner().getDateOfBirth());

                    }else if(parOfSort.equals("ownerGender")){

                    }else if(parOfSort.equals("ownerPassport")){

                    }else if(parOfSort.equals("ownerAge")){

                    }else if(parOfSort.equals("maxSpeed")){
                        InternetContract contract = (InternetContract) listOfContract[i];

                    }else if(parOfSort.equals("minutesNum")){
                        CellularContract contract = (CellularContract ) listOfContract[i];

                    }else if(parOfSort.equals("gbNum")){
                        CellularContract contract = (CellularContract ) listOfContract[i];

                    }else if(parOfSort.equals("smsNum")){
                        CellularContract contract = (CellularContract ) listOfContract[i];

                    }else if(parOfSort.equals("packageNumber")){
                        TVContract contract = (TVContract) listOfContract[i];

                    }
                }else {
                    System.out.println("У контрактов нет заданного свойства");
                }
            }catch (Exception e){
                System.out.println("При поиске возникла ошибка");
                e.printStackTrace();
            }

        }
        return arrOfObj;
    }

}
