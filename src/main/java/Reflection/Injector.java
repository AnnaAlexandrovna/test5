package Reflection;


import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
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
     * @return ....
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
        for (Class<?> clss : scanned) {
            System.out.println(clss);
        }
        //смотрим, вообще какие-то классы нашлись или нет
        if (scanned.size() != 0) {

            for (Field field : object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(AutoInjectable.class)) {
                    System.out.println(field.getGenericType().toString());
                    String fieldType = field.getType().toString();
                    boolean hasNeededInterface = false;
                    List<Class<?>> suitableClasses = new ArrayList<>();

                    //проверяем наличие инт
                    for (Class<?> scannedCl : scanned) {
                        Class mClassObject = scannedCl;
                        Class[] interfaces = mClassObject.getInterfaces();
                        //System.out.println("----------");
                        for (Class anInterface : interfaces) {
                            //System.out.println("anInterface " + anInterface);
                            if (fieldType.equals(anInterface.toString())) {
                                hasNeededInterface = true;
                                break;
                            }
                        }
                        //System.out.println("hasNeededInterface - " + hasNeededInterface);
                        if (hasNeededInterface) {
                            suitableClasses.add(scannedCl);
                        }
                    }
                    boolean isFieldCollection = false;
                    if (field.getType().toString().contains("java.util")) {
                        isFieldCollection = true;
                    } else {
                        isFieldCollection = false;
                    }
                    //если подходит один класс и поле простое - ок
                    //если подходит несколько классов и поле простое - ошибка
                    //если классы не найдены - ошибка
                    //если поле не простое - записываем их в поле
                    if (suitableClasses.size() <= 0) {
                        throw new Exception("Не найдено классов для вставки");
                    } else if (suitableClasses.size() == 1) {
                        field.setAccessible(true);
                        field.set(object, suitableClasses.get(0).newInstance());
                    } else if (suitableClasses.size() > 1 && !isFieldCollection) {
                        throw new Exception("Классов для вставки больше одного, а поле простое");
                    } else if (isFieldCollection) {
                        try {
                            Class cl = Class.forName(suitableClasses.get(0).toString());
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
