package MyValidator;

import Client.ClientInfo;
import Contract.AbstractContract;
import ValidatorResult.MyValidatorResult;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Класс валидации информации о контракте
 */
public class MyValidator {
    /**
     * Метод для заполнения репозитория из файла
     *
     * @param contract контракт, поля которого надо проверить
     * @return MyValidatorResult c информацией о проверках
     */
    public MyValidatorResult makeValidate(AbstractContract contract) {
        MyValidatorResult result = new MyValidatorResult();
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ClientInfo>> validate = validator.validate(contract.getOwner());
        List<String> listOfErrors = new ArrayList();
        if (validate.size() != 0) {
            for (ConstraintViolation<ClientInfo> violation : validate) {
                listOfErrors.add(violation.getInvalidValue() + " " + violation.getMessage());
                if (violation.getConstraintDescriptor().getAnnotation().annotationType().toString().endsWith("Max") ||
                        violation.getConstraintDescriptor().getAnnotation().annotationType().toString().endsWith("Size")) {
                    result.setRiskValue("high");
                } else if (result.getRiskValue().equals("high")) {
                    result.setRiskValue("low");
                }
            }
        }


        Set<ConstraintViolation<AbstractContract>> validate1 = validator.validate(contract);
        if (validate1.size() != 0) {
            for (ConstraintViolation<AbstractContract> violation : validate1) {
                listOfErrors.add(violation.getInvalidValue() + " " + violation.getMessage());
                if (violation.getConstraintDescriptor().getAnnotation().annotationType().toString().endsWith("Max") ||
                        violation.getConstraintDescriptor().getAnnotation().annotationType().toString().endsWith("Size")) {
                    result.setRiskValue("high");
                } else if (result.getRiskValue().equals("high")) {
                    result.setRiskValue("low");
                }
            }
        }

        if (listOfErrors.size() != 0) {
            result.setErrorMessages(listOfErrors);
            result.setResult("error");
        }

        validatorFactory.close();
        return result;
    }

}
