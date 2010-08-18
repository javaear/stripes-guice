package com.silvermindsoftware.stripes.integration.guice;

import java.text.MessageFormat;

public class GuiceUtils {

    public static final String GUICE_MODULES_PARAM = "Guice.Modules";
    public static String GUICE_INJECTOR_FACTORY_CLASS_NAME = "GuiceInjectorFactory.Class";
    public static String DEFAULT_GUICE_INJECTOR_FACTORY_CLASS_NAME = "com.silvermindsoftware.stripes.integration.guice.DefaultGuiceInjectorFactory";

    public static String[] splitClasses(String moduleClasses) {
        return moduleClasses.split(",");
    }

    @SuppressWarnings("unchecked")
    public static <T> T createClass(String className, Class<T> targetType) throws CreateClassException {
        try {
            return (T) Class.forName(className).newInstance();
        } catch (ClassCastException e) {
            throw wrapToCreateClassExcpetion(e, className);
        } catch (ClassNotFoundException e) {
            throw wrapToCreateClassExcpetion(e, className);
        } catch (IllegalAccessException e) {
            throw wrapToCreateClassExcpetion(e, className);
        } catch (InstantiationException e) {
            throw wrapToCreateClassExcpetion(e, className);
        }
    }

    private static CreateClassException wrapToCreateClassExcpetion(Exception e, String className) {
        return new CreateClassException(MessageFormat.format(
                "{0} thrown during instantiation of {1} with message {2}", e.getClass().getName(), className, e.getMessage()), e);
    }

}