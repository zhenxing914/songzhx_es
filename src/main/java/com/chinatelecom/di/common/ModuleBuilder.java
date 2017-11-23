package com.chinatelecom.di.common;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2017/10/16.
 */

public class ModuleBuilder {

    private final List<Module> modules=new ArrayList<Module>();


    public Injector createInjector(){
        Injector injector=Guice.createInjector(modules);

        return injector;
    }

    public void add(Module... newModules) {
        for(Module module:newModules)
        {
            modules.add(module);
        }
    }
}

