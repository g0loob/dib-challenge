package com.example.dibchallenge;

import com.example.dibchallenge.beer.strategy.BeerStrategy;
import com.example.dibchallenge.punk_api_lib.strategy.PunkApiBeerStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
        Class<? extends BeerStrategy> clazz = registry.get(key);

        if (clazz == null) { // TODO: throw exception if no class in registry
            return null;
        }

        Constructor<?> ctor;
        try {
            ctor = clazz.getConstructor();
            return (BeerStrategy) ctor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostConstruct
    public void init() {
        add(RegistryKeys.PUNK_API_BEER, PunkApiBeerStrategy.class);
    }

    public interface RegistryKeys {
        String PUNK_API_BEER = "PAB";
    }

}
