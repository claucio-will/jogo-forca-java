package br.com.claucio.dev.jogoforca.core;

import br.com.claucio.dev.jogoforca.game.GameException;
import br.com.claucio.dev.jogoforca.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDictionary extends Dictionary{

    private static final String FILE_NAME = "dicionario.txt";


    private List<String> words = new ArrayList<>();

    public FileDictionary() {
        load();
    }


    private void load(){
       //Lendo os dados do arquivo
       try(Scanner scanner = new Scanner(getClass().getResourceAsStream("/" + FILE_NAME))){

           /*
            Enquanto tiver linha eu pego as palavras da linhas e coloco dentro da variavel word,
            depois pego essa variavel e adiciono dentro da lista de words
            */
           while (scanner.hasNextLine()){
               String word = scanner.nextLine().trim();
               words.add(word);
           }

           if (words.size() == 0){
                throw  new GameException("A lista de palavras não pode ser vázia");
           }

       }
    }

    @Override
    public Word nextWord(){
     int pos = RandomUtils.newRandomNumber(0,words.size());
     return new Word(words.get(pos));
    }

    @Override
    public String getName() {
        return "Arquivo " + FILE_NAME;
    }
}
