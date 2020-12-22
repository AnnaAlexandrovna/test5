package Reflection;


import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;


/**
 * Класс, для внедрения значений полей
 */
@Configuration(packages = {"Sorter"})
public class Injector {

    /**
     * Метож, для внедрения значений полей
     *
     * @param object класс, куда надо внедрять зависимости
     * @param <T> This is the type parameter
     * @return sdgsdgsdg
     * @throws java.io.IOException в случаях если не нашли классов для вставки или поле для вставки простое, а классов несколько
     */
    public <T> T inject(T object) throws Exception {
        //забираем пакеты, в которых нужно искать классы из аннотации
        String[] pack = Injector.class.getAnnotation(Configuration.class).packages();
        List<Class<?>> scanned = new ArrayList<>();

        for (String s : pack) {
            //дикий фильтр отедактировала (https://github.com/ronmamo/reflections/issues/234)
            final ConfigurationBuilder config = new ConfigurationBuilder()
                    .setScanners(new ResourcesScanner(), new SubTypesScanner(false))
                    .setUrls(ClasspathHelper.forPackage(s))
                    .filterInputsBy(new FilterBuilder().includePackage(s));
            final Reflections reflect = new Reflections(config);
            scanned.addAll(reflect.getSubTypesOf(Object.class));
        }
        //смотрим, вообще какие-то классы нашлись или нет
        if (scanned.size() != 0) {
            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    List<Class<?>> suitableClasses = new ArrayList<>();
                    boolean isFieldCollection = false;
                    if (field.getType().toString().contains("java.util")) {
                        isFieldCollection = true;
                    }
                    boolean hasNeededInterface = false;

                    if (isFieldCollection) {
                        ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
                        System.out.println(stringListType);
                        Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
                        System.out.println(stringListClass);
                        for (Class<?> scannedCl : scanned) {
                            Class<?>[] interfaces = scannedCl.getInterfaces();
                            //System.out.println("----------");
                            for (Class<?> anInterface : interfaces) {
                                //System.out.println("anInterface " + anInterface);
                                if (stringListClass.equals(anInterface)) {
                                    hasNeededInterface = true;
                                    break;
                                }
                            }
                            //System.out.println("hasNeededInterface - " + hasNeededInterface);
                            if (hasNeededInterface) {
                                suitableClasses.add(scannedCl);
                            }
                        }
                    } else {
                        for (Class<?> scannedCl : scanned) {
                            Class<?>[] interfaces = scannedCl.getInterfaces();
                            for (Class<?> anInterface : interfaces) {
                                if (field.getType().equals(anInterface)) {
                                    hasNeededInterface = true;
                                    break;
                                }
                            }
                            if (hasNeededInterface) {
                                suitableClasses.add(scannedCl);
                            }
                        }
                    }


                    //если подходит один класс и поле простое - ок
                    //если подходит несколько классов и поле простое - ошибка
                    //если классы не найдены - ошибка
                    //если поле не простое - записываем их в поле
                    if (suitableClasses.size() <= 0) {
                        throw new Exception("Не найдено классов для вставки");
                    } else if (suitableClasses.size() == 1 && !isFieldCollection) {
                        field.setAccessible(true);
                        field.set(object, suitableClasses.get(0).newInstance());
                    } else if (suitableClasses.size() > 1 && !isFieldCollection) {
                        throw new Exception("Классов для вставки больше одного, а поле простое");
                    } else if (isFieldCollection) {
                        try {
                            field.setAccessible(true);
                            field.set(object, suitableClasses);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } else {
            throw new Exception("Не найдено классов для поиска зависимостей");
        }
        return object;
    }
}
