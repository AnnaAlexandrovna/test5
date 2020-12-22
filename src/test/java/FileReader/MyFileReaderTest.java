package FileReader;

import Client.ClientInfo;
import Contract.AbstractContract;
import myRepository.ContractRepository;
import org.junit.Assert;



public class MyFileReaderTest {
    @org.junit.Test
    public void readFile() {
        ContractRepository contractRepository = new ContractRepository();
        MyFileReader myFileReader = new MyFileReader();
        myFileReader.readFileAndMakeRepo("src/main/resources/INFO.csv", contractRepository);
        System.out.println("Contracts:");
        for (AbstractContract c : contractRepository.getListOfContract()) {
            System.out.println(c);
        }
        Assert.assertEquals(3, contractRepository.getListOfContract().length);
        System.out.println("Owners:");
        for (ClientInfo owner : contractRepository.getAllOwners()) {
            System.out.println(owner);
        }
        Assert.assertEquals(2, contractRepository.getAllOwners().size());
    }
}
