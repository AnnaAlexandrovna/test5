import Client.ClientInfo;
import Contract.AbstractContract;
import Contract.CellularContract;
import Contract.InternetContract;
import Contract.TVContract;
import Repository.ContractRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Класс для запуска кода
 */
public class Main {
    public static void main(String[] args) throws ParseException {
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000","man", "2048 23423423424");
        List<ClientInfo> listOfClient = new ArrayList<ClientInfo>();
        listOfClient.add(client1);
        AbstractContract contract1 = new InternetContract("28.01.2004","28.01.2024", listOfClient, 350);
        AbstractContract contract2 = new CellularContract("20.10.2000","20.10.2024", listOfClient, 100, 6,150);
        UUID uuid = contract2.getId();
        AbstractContract contract3 = new TVContract("15.09.2008","20.12.2023",listOfClient, 6);
        ContractRepository repository = new ContractRepository();
        repository.addContract(contract1);
        repository.addContract(contract2);
        repository.addContract(contract3);
        AbstractContract [] rep = repository.getListOfContract();
        for (int i=0; i<repository.getCounter(); i++){
            System.out.println(rep[i]);
        }
        System.out.println("getContractById - " + repository.getContractById(uuid));
        repository.removeContract(uuid);
        rep = repository.getListOfContract();
        for (int i=0; i<repository.getCounter(); i++){
            System.out.println(rep[i]);
        }
    }
}
