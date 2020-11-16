package Sorter;

import Contract.AbstractContract;
import Repository.ContractRepository;

import java.util.Comparator;

public interface ISorter {
/**
 * Метод сортировки
 * @param comparator для определения критерия сортировки и определения направления сортировки
 * @param listOfContract массив контрактов для сортировки
 * @return массив AbstractContract
 **/
    AbstractContract[]  sort(Comparator<AbstractContract> comparator, AbstractContract[] listOfContract);
}
