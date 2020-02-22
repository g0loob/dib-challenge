package com.example.dibchallenge;

import com.example.dibchallenge.beer.BeerStrategy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Component
public class BeerStrategyRegistry {

    private Map<String, Class<? extends BeerStrategy>> registry = new HashMap<>();

    public void add(String key, Class<? extends BeerStrategy> clazz) {
        registry.putIfAbsent(key, clazz);
    }

    public BeerStrategy get(String key) {
        Class<? extends BeerStrategy> clazz = registry.get(key); // TODO: throw exception if no class in registry
        Constructor<?> ctor;
        try {
            ctor = clazz.getConstructor();
            return (BeerStrategy) ctor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

}
