package Sorter;

import Contract.AbstractContract;

import java.util.Comparator;

public class SelectionSorter implements ISorter {
    /**
     * Метод сортировки
     *
     * @param comparator     для определения критерия сортировки и определения направления сортировки
     * @param listOfContract массив контрактов для сортировки
     * @return массив AbstractContract
     **/
    @Override
    public AbstractContract[] sort(Comparator<AbstractContract> comparator, AbstractContract[] listOfContract) {
        AbstractContract bufferValue;
        for (int left = 0; left < listOfContract.length; left++) {
            int minElementPosition = left;
            for (int i = minElementPosition; i < listOfContract.length; i++) {
                if (comparator.compare(listOfContract[i], listOfContract[minElementPosition]) < 0) {
                    minElementPosition = i;
                }
            }
            bufferValue = listOfContract[left];
            listOfContract[left] = listOfContract[minElementPosition];
            listOfContract[minElementPosition] = bufferValue;
        }
        return listOfContract;
    }
}
