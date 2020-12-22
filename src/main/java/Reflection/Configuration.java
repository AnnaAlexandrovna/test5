package Reflection;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для того, чтобы пометить пакеты, в которых надо искать классы для вставки
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {
    /**
     * Метод для указания, где искать классы для вставки
     * @return массив строк с пакетами, где искать классы для вставки
     */
    String[] packages();

}
