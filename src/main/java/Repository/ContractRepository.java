package Repository;

import Contract.AbstractContract;

import java.util.Arrays;
import java.util.UUID;

public class ContractRepository {

    private AbstractContract[] listOfContract;
    private final int defaultNumOfContracts = 15;
    private int counter = 0;

    public ContractRepository() {
        this.listOfContract = new AbstractContract[defaultNumOfContracts];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContractRepository)) return false;
        ContractRepository that = (ContractRepository) o;
        return Arrays.equals(listOfContract, that.listOfContract);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(listOfContract);
    }


    public void setListOfContract(AbstractContract[] listOfContract) {
        this.listOfContract = listOfContract;
    }

    public AbstractContract[] getListOfContract() {
        return listOfContract;
    }

    public AbstractContract getContractById(UUID id) {

        for (AbstractContract abstractContract : listOfContract) {
            if (abstractContract.getId().equals(id)) {
                return abstractContract;
            }
        }
        return null;
    }

    public void addContract(AbstractContract abstractContract) {
        if (counter >= listOfContract.length) {
            AbstractContract[] arr = new AbstractContract[listOfContract.length * 2];
            System.arraycopy(listOfContract, 0, arr, 0, listOfContract.length);
            listOfContract = arr;
        }

        listOfContract[counter] = abstractContract;
        counter++;
    }

}
