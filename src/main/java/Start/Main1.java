package Start;

import Client.ClientInfo;
import Contract.AbstractContract;
import Contract.CellularContract;
import Contract.InternetContract;
import Contract.TVContract;
import Reflection.Injector;
import myRepository.ContractRepository;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * Класс для запуска кода
 */
final class Main1 {

    private  Main1() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Логгер
     */
    static final Logger logger = Logger.getLogger(Main1.class.toString());

    /**
     * Запуск программы
     * @param args запуск
     */
    public static void main(String[] args) {
        try {
            Injector injector = new Injector();
            ContractRepository repository = new ContractRepository();
            injector.inject(repository);
            repository.toString();
            ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000", "man", "2048123456");
            AbstractContract contract1 = new InternetContract("28.01.2004", "28.01.2024", client1, 350);
            AbstractContract contract2 = new CellularContract("20.10.2000", "20.10.2024", client1, 100, 6, 150);
            UUID uuid = contract2.getId();
            AbstractContract contract3 = new TVContract("15.09.2008", "20.12.2023", client1, 6);
            repository.addContract(contract1);
            repository.addContract(contract2);
            repository.addContract(contract3);
            AbstractContract[] rep = repository.getListOfContract();
            for (int i = 0; i < repository.getCounter(); i++) {
                System.out.println(rep[i]);
                logger.info(rep[i].toString());
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
