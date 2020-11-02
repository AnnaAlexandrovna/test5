package Repository;

import Client.ClientInfo;
import Contract.AbstractContract;
import Contract.CellularContract;
import Contract.InternetContract;
import Contract.TVContract;
import org.junit.Assert;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ContractRepositoryTest {


    @org.junit.Test
    public void getContractById() throws ParseException {
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
    public void addContract() throws ParseException {
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
    public void removeContract() throws ParseException {
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
}
