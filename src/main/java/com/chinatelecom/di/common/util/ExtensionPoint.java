package com.chinatelecom.di.common.util;


import com.chinatelecom.di.common.settings.Settings;
import com.google.inject.Binder;
import com.google.inject.multibindings.Multibinder;

import java.util.*;

/**
 * Created by song on 2018/1/9.
 */
public abstract class ExtensionPoint {

    protected final String name;

    protected  final Class<?>[] singletons;


    /**
     * create o new extension point
     * @param name
     * @param singletons
     */
    public ExtensionPoint(String name,Class<?>... singletons){
        this.name=name;
        this.singletons=singletons;

    }


    public final void bind(Binder binder){
        for(Class<?> c :singletons){
            binder.bind(c).asEagerSingleton();
        }
        bindExtensions(binder);
    }

    protected abstract void bindExtensions(Binder binder);

    /**
     * ClassMap
     * @param <T>
     */
    public static class ClassMap<T> extends ExtensionPoint{
        protected final Class<T> extensionClass;
        protected final Map<String,Class<? extends T>> extensions=new HashMap<>();
        protected final Set<String> reversedKeys;


        public ClassMap(String name, Class<T> extensionClass, Set<String> reservedKeys, Class<?>... singletons) {
            super(name, singletons);
            this.extensionClass=extensionClass;
            this.reversedKeys=reservedKeys;
        }


        public Class<? extends T> getExtension(String type){
            return extensions.get(type);
        }


        public final void registerExtension(String key,Class<? extends T > extension)
        {
            if(extensions.containsKey(key)||reversedKeys.contains(key)){
                throw  new IllegalArgumentException("Cant't register the same ["+ this.name+"] more than once for ["+key+"]");
            }

            extensions.put(key,extension);
        }

        @Override
        protected void bindExtensions(Binder binder) {

        }
    }

    /**
     * ClassSet
     * @param <T>
     */
    public final static class ClassSet<T> extends ExtensionPoint{


        protected  final Class<T> extensionClass;

        private final Set<Class<? extends  T> > extensions=new HashSet<>();

        public ClassSet(String name, Class<T> extensionClass ,Class<?>... singletons) {
            super(name, singletons);
            this.extensionClass=extensionClass;
        }

        public final void registerExtension(Class<? extends T> extension){
            if(extensions.contains(extension)){
                throw new IllegalArgumentException("Cant`t register the same "+this.name+"more than once for "+extension.getName()+"]");
            }
            extensions.add(extension);
        }

        @Override
        protected void bindExtensions(Binder binder) {
            Multibinder<T> allocationMultibinder=Multibinder.newSetBinder(binder,extensionClass);
            for(Class<? extends T> clazz: extensions)
            {
                binder.bind(clazz).asEagerSingleton();
                allocationMultibinder.addBinding().to(clazz);
            }
        }
    }


    /**
     * SeletedType
     * @param <T>
     */
    public static final class SeletedType<T> extends ClassMap<T>
    {

        public SeletedType(String name, Class<T> extensionClass) {
            super(name, extensionClass, Collections.EMPTY_SET);
        }

        public String bindType(Binder binder, Settings settings, String settingsKey, String defaultValue){
            final String type =settings.get(settingsKey,defaultValue);

            if(type ==null ){
                throw  new IllegalArgumentException("Missing setting ["+settingsKey+"]");
            }

            final Class<? extends T> instanse=getExtension(type);
            if(instanse==null){
                throw new IllegalArgumentException("UKOWN [ "+this.name+"] type ["+type+"]");
            }
            if(extensionClass == instanse)
            {
                binder.bind(extensionClass).asEagerSingleton();
            }
            else {
                binder.bind(extensionClass).to(instanse).asEagerSingleton();
            }
            return type;
        }
    }
}
