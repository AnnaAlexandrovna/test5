package Sorter;

import Contract.AbstractContract;

import java.util.Comparator;

public class BubbleSorter implements ISorter {
    /**
     * Метод сортировки
     *
     * @param comparator     для определения критерия сортировки и определения направления сортировки
     * @param listOfContract массив контрактов для сортировки
     * @return массив AbstractContract
     **/
    @Override
    public AbstractContract[] sort(Comparator<AbstractContract> comparator, AbstractContract[] listOfContract) {
        boolean isSorted = false;
        AbstractContract bufferValue;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < listOfContract.length - 1; i++) {
                if (comparator.compare(listOfContract[i], listOfContract[i + 1]) > 0) {
                    isSorted = false;
                    bufferValue = listOfContract[i];
                    listOfContract[i] = listOfContract[i + 1];
                    listOfContract[i + 1] = bufferValue;
                }
            }
        }
        return listOfContract;
    }


}
