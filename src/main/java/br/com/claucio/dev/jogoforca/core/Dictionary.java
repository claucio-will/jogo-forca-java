package br.com.claucio.dev.jogoforca.core;

import br.com.claucio.dev.jogoforca.game.GameException;
import br.com.claucio.dev.jogoforca.utils.RandomUtils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public abstract class Dictionary {


    private static Dictionary instance;

    //pattern singleton
    public static Dictionary getInstance() {
        if(instance == null){
            try {
               String dictionaryClassName = Config.get("dictionaryClassName");
               Class<?> clazz = Class.forName(dictionaryClassName);
                Constructor<?> constructor = clazz.getConstructor();
                instance = (Dictionary) constructor.newInstance();
            }catch (Exception e){
                throw new RuntimeException(e);
            }

         }
        return instance;
    }


    public abstract Word nextWord();

    public abstract String getName();
}
