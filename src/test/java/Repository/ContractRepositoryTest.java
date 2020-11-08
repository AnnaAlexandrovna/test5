package Repository;

import Client.ClientInfo;
import Contract.AbstractContract;
import Contract.CellularContract;
import Contract.InternetContract;
import Contract.TVContract;
import org.junit.Assert;

import java.util.UUID;

public class ContractRepositoryTest {


    @org.junit.Test
    public void getContractById()  {
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000","man", "2048 23423423424");
        AbstractContract contract1 = new InternetContract("28.01.2004","28.01.2024", client1, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000","20.10.2024", client1, 100, 6,150);
        UUID uuid = contract2.getId();
        AbstractContract contract3 = new TVContract("15.09.2008","20.12.2023",client1, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        Assert.assertEquals(contract2, repository.getContractById(uuid));
    }

    @org.junit.Test
    public void addContract() {
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000","man", "2048 23423423424");
        AbstractContract contract1 = new InternetContract("28.01.2004","28.01.2024", client1, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000","20.10.2024", client1, 100, 6,150);
        AbstractContract contract3 = new TVContract("15.09.2008","20.12.2023",client1, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        Assert.assertEquals(3, repository.getCounter());
    }

    @org.junit.Test
    public void removeContract()  {
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000","man", "2048 23423423424");
        AbstractContract contract1 = new InternetContract("28.01.2004","28.01.2024", client1, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000","20.10.2024", client1, 100, 6,150);
        UUID uuid = contract2.getId();
        AbstractContract contract3 = new TVContract("15.09.2008","20.12.2023", client1, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        Assert.assertEquals(3, repository.getCounter());
        repository.removeContract(uuid);
        Assert.assertEquals(2, repository.getCounter());
    }

    @org.junit.Test
    public void searchContract()  {
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000","man", "2048 23423423424");
        AbstractContract contract1 = new InternetContract("28.01.2004","28.01.2024", client1, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000","20.10.2024", client1, 100, 6,150);
        AbstractContract contract3 = new TVContract("15.09.2008","20.12.2023", client1, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        Assert.assertEquals(contract1, repository.searchContract("id", contract1.getId().toString()).get(0));
        Assert.assertEquals(contract2, repository.searchContract("startDate", "20.10.2000").get(0));
        Assert.assertEquals(contract2, repository.searchContract("smsNum", "150").get(0));
        Assert.assertEquals(contract1, repository.searchContract("maxSpeed", "350").get(0));
        Assert.assertEquals(contract2, repository.searchContract("minutesNum", "100").get(0));
        Assert.assertEquals(contract2, repository.searchContract("gbNum", "6").get(0));
        Assert.assertEquals(contract3, repository.searchContract("packageNumber", "6").get(0));
        Assert.assertEquals(3, repository.searchContract("ownerId", client1.getId().toString()).size());
        Assert.assertEquals(3, repository.searchContract("ownerAge", Integer.toString(client1.getAge())).size());
        Assert.assertEquals(3, repository.searchContract("ownerPassport", client1.getPassport()).size());
        Assert.assertEquals(3, repository.searchContract("ownerGender", client1.getGender()).size());
        Assert.assertEquals(3, repository.searchContract("ownerFirstName", client1.getFirstName()).size());
        Assert.assertEquals(3, repository.searchContract("ownerLastName", client1.getLastName()).size());
        Assert.assertEquals(3, repository.searchContract("ownerDateOfBirth", "01.05.2000").size());
    }

    @org.junit.Test
    public void bubbleSortOfContract(){
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000","man", "2048 23423423424");
        AbstractContract contract1 = new InternetContract("28.01.2004","28.01.2024", client1, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000","20.10.2024", client1, 100, 6,150);
        AbstractContract contract3 = new TVContract("15.09.2008","20.12.2023", client1, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        repository.bubbleSortOfContract("startDate");
        AbstractContract[] contracts = repository.getListOfContract();
        Assert.assertEquals(contract2, contracts[0]);
        Assert.assertEquals(contract1, contracts[1]);
        Assert.assertEquals(contract3, contracts[2]);
        repository.bubbleSortOfContract("stopDate");
        contracts = repository.getListOfContract();
        Assert.assertEquals(contract3, contracts[0]);
        Assert.assertEquals(contract1, contracts[1]);
        Assert.assertEquals(contract2, contracts[2]);

    }
    @org.junit.Test
    public void selectionSortOfContract(){
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000","man", "2048 23423423424");
        AbstractContract contract1 = new InternetContract("28.01.2004","28.01.2024", client1, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000","20.10.2024", client1, 100, 6,150);
        AbstractContract contract3 = new TVContract("15.09.2008","20.12.2023", client1, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        repository.selectionSortOfContract("startDate");
        AbstractContract[] contracts = repository.getListOfContract();
        Assert.assertEquals(contract2, contracts[0]);
        Assert.assertEquals(contract1, contracts[1]);
        Assert.assertEquals(contract3, contracts[2]);
        repository.selectionSortOfContract("stopDate");
        contracts = repository.getListOfContract();
        Assert.assertEquals(contract3, contracts[0]);
        Assert.assertEquals(contract1, contracts[1]);
        Assert.assertEquals(contract2, contracts[2]);
    }
}
