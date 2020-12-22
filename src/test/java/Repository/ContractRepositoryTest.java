package Repository;

import Client.ClientInfo;
import Contract.AbstractContract;
import Contract.CellularContract;
import Contract.InternetContract;
import Contract.TVContract;
import Reflection.Injector;
import myRepository.ContractRepository;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.UUID;
import java.util.function.Predicate;

public class ContractRepositoryTest {


    @org.junit.Test
    public void getContractById() {
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000", "man", "2048 23423423424");
        AbstractContract contract1 = new InternetContract("28.01.2004", "28.01.2024", client1, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000", "20.10.2024", client1, 100, 6, 150);
        UUID uuid = contract2.getId();
        AbstractContract contract3 = new TVContract("15.09.2008", "20.12.2023", client1, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        Assert.assertEquals(contract2, repository.getContractById(uuid));
    }

    @org.junit.Test
    public void addContract() {
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000", "man", "2048 23423423424");
        AbstractContract contract1 = new InternetContract("28.01.2004", "28.01.2024", client1, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000", "20.10.2024", client1, 100, 6, 150);
        AbstractContract contract3 = new TVContract("15.09.2008", "20.12.2023", client1, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        Assert.assertEquals(3, repository.getCounter());
    }

    @org.junit.Test
    public void removeContract() {
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000", "man", "2048 23423423424");
        AbstractContract contract1 = new InternetContract("28.01.2004", "28.01.2024", client1, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000", "20.10.2024", client1, 100, 6, 150);
        UUID uuid = contract2.getId();
        AbstractContract contract3 = new TVContract("15.09.2008", "20.12.2023", client1, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        Assert.assertEquals(3, repository.getCounter());
        repository.removeContract(uuid);
        Assert.assertEquals(2, repository.getCounter());
    }

    @org.junit.Test
    public void searchContract() {
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000", "man", "2048 23423423424");
        AbstractContract contract1 = new InternetContract("28.01.2004", "28.01.2024", client1, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000", "20.10.2024", client1, 100, 6, 150);
        AbstractContract contract3 = new TVContract("15.09.2008", "20.12.2023", client1, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        Assert.assertEquals(contract1, repository.searchContract(new Predicate<AbstractContract>() {
            @Override
            public boolean test(AbstractContract contract) {
                return contract.getId().equals(contract1.getId());
            }
        }).getListOfContract()[0]);
        Assert.assertEquals(contract1, repository.searchContract(new Predicate<AbstractContract>() {
            @Override
            public boolean test(AbstractContract contract) {
                String date = "28.01.2004";
                String formattedDate = new SimpleDateFormat("dd.MM.yyyy").format(contract.getStartDate());
                return date.equals(formattedDate);
            }
        }).getListOfContract()[0]);
    }

    @org.junit.Test
    public void sort() {
        try {
            Injector injector = new Injector();
            ContractRepository repository = new ContractRepository();
            injector.inject(repository);
            ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000", "man", "2048 23423423424");
            AbstractContract contract1 = new InternetContract("28.01.2004", "28.01.2024", client1, 350);
            AbstractContract contract2 = new CellularContract("20.10.2000", "20.10.2024", client1, 100, 6, 150);
            AbstractContract contract3 = new TVContract("15.09.2008", "20.12.2023", client1, 6);
            repository.addContract(contract1);
            repository.addContract(contract2);
            repository.addContract(contract3);
            repository.sort(new Comparator<AbstractContract>() {
                @Override
                public int compare(AbstractContract o1, AbstractContract o2) {
                    return o1.getStartDate().compareTo(o2.getStartDate());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
