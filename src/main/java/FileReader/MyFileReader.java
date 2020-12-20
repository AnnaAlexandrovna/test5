package FileReader;

import Client.ClientInfo;
import Contract.AbstractContract;
import Contract.CellularContract;
import Contract.InternetContract;
import Contract.TVContract;
import MyValidator.MyValidator;
import Repository.ContractRepository;
import ValidatorResult.MyValidatorResult;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для чтения cvs файла и записи в определенный репозиторий
 */
public class MyFileReader {
    /**
     * Поле влидатора
     */
    private MyValidator validator = new MyValidator();

    /**
     * Метод для заполнения репозитория из файла
     *
     * @param passToFile         путь до файла
     * @param contractRepository репозиторий, куда надо добавлять контракты
     */
    public void readFileAndMakeRepo(String passToFile, ContractRepository contractRepository) {
        makeRepo(makeListFromFile(passToFile), contractRepository);
    }

    /**
     * Метод для составления списка строк из файла
     *
     * @param passToFile путь до файла
     * @return список контрактов
     */
    private List<String[]> makeListFromFile(String passToFile) {
        List<String[]> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(passToFile);
            CSVReader csvReader = new CSVReader(fileReader);
            list = csvReader.readAll();
            fileReader.close();
            csvReader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return list;

    }

    /**
     * Метод для составления списка строк из файла
     *
     * @param list               строковый список контрактов
     * @param contractRepository репозиторий, куда надо вносить контракты
     */
    private void makeRepo(List<String[]> list, ContractRepository contractRepository) {
        for (String[] strings : list) {
            for (int stringNum = 0; stringNum < strings.length; stringNum++) {
                String[] mass = strings[stringNum].split(";\"|;");
                if (mass.length >= 8) {

                    ClientInfo clientInfo = new ClientInfo(mass[2], mass[3], mass[4], mass[5], mass[6]);

                    if (contractRepository.getCounter() > 0) {
                        for (ClientInfo owner : contractRepository.getAllOwners()) {
                            if (clientInfo.equals(owner)) {
                                clientInfo = owner;
                            }
                        }
                    }
                    if (mass[7].equals("internet")) {
                        AbstractContract contract = new InternetContract(mass[0], mass[1], clientInfo, Integer.parseInt(mass[8]));
                        MyValidatorResult result = validator.makeValidate(contract);
                        if (!result.getRiskValue().equals("high")) {
                            contractRepository.addContract(contract);
                        }
                        if (!result.getRiskValue().equals("ok")) {
                            result.printEr();
                        }
                    } else if (mass[7].equals("cell") && mass.length >= 10) {
                        AbstractContract contract = new CellularContract(mass[0], mass[1], clientInfo, Integer.parseInt(mass[8]), Integer.parseInt(mass[9]), Integer.parseInt(mass[10]));
                        MyValidatorResult result = validator.makeValidate(contract);
                        if (!result.getRiskValue().equals("high")) {
                            contractRepository.addContract(contract);
                        }
                        if (!result.getRiskValue().equals("ok")) {
                            result.printEr();
                        }
                    } else if (mass[7].equals("tv")) {
                        AbstractContract contract = new TVContract(mass[0], mass[1], clientInfo, Integer.parseInt(mass[8]));
                        MyValidatorResult result = validator.makeValidate(contract);
                        if (!result.getRiskValue().equals("high")) {
                            contractRepository.addContract(contract);
                        }
                        if (!result.getRiskValue().equals("ok")) {
                            result.printEr();
                        }
                    } else {
                        System.out.println("Введена неполная информация о контракте типа мобильная связь");
                    }
                } else {
                    System.out.println("Введена неполная информация о контракте");
                }
            }
        }
    }

}
