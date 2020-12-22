package Reflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для отметки полей, которые надо вставить
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInjectable {
}
