package ValidatorResult;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для оформления сообщения от валидатора
 */
public class MyValidatorResult {
    /**
     * Поле со списком ошибок валидации
     */
    private List<String> errorMessages;
    /**
     * Поле с уровнем ошибки. Возможные значения - "low", "high", "ok"
     */
    private String riskValue;
    /**
     * Поле с описанием успещности проверки. Возможные значения - "success", "error"
     */
    private String result;
    /**
     * Конструктор для результата проверки со всеми заданными параметрами
     *
     * @param result результат проверки. Пример - "28.01.2004"
     * @param errorMessages  список ошибок валидации.
     * @param riskValue  уровень ошибки.
     */
    public MyValidatorResult(String result, List<String> errorMessages, String riskValue) {
        this.errorMessages = errorMessages;
        this.riskValue = riskValue;
        this.result = result;
    }

    /**
     * Конструктор по умолчанию. Создается с описанием успешной валидации
     */
    public MyValidatorResult() {
        this.errorMessages = new ArrayList<>();
        this.riskValue = "ok";
        this.result = "success";
    }
    /**
     * Геттер для получения списка ошибок валидации
     *
     * @return список ошибок валидации.
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }
    /**
     * Сеттер для установки списка ошибок валидации
     *
     * @param errorMessages список ошибок валидации.
     */
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * Геттер для уровня риска, обноруженного в процессе проверки.
     *
     * @return уровень риска, обноруженного в процессе проверки. Пример - "high"
     */
    public String getRiskValue() {
        return riskValue;
    }
    /**
     * Сеттер для уровня ошибки валидации
     *
     * @param riskValue список ошибок валидации. Пример - "low"
     */
    public void setRiskValue(String riskValue) {
        this.riskValue = riskValue;
    }
    /**
     * Геттер для получения успешности проверки
     *
     * @return успешность проверки. Пример - "success"
     */
    public String getResult() {
        return result;
    }

    /**
     * Сеттер для успешности проверки
     *
     * @param result список ошибок валидации. Пример - "error"
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Метод вывода в консоль информации об ошибках валидации
     *
     */
    public void printEr(){
        for (String errorMessage : this.errorMessages) {
            System.out.println(errorMessage);
        }
    }
}
