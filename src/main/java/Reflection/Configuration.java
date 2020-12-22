package Reflection;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для того, чтобы пометить пакеты, в которых надо искать классы для вставки
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {
    /**
     * Метод для добавления и получений полей для вставки
     */
    String[] packages();

}
