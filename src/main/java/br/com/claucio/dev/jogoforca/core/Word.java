package br.com.claucio.dev.jogoforca.core;

import java.util.HashSet;
import java.util.Set;

public class Word {

    private static final char SECRET_CHAR = '_';

    private String originalWord;
    private Set<Character> foundChars = new HashSet<>();
    private Set<Character> wordChars = new HashSet<>();



    public Word(String originalWord) {
        this.originalWord = originalWord.toUpperCase();

        //Pegando caractere por caractere
        char[] chars = this.originalWord.toCharArray();

        //Adicionando todos os caractere da palavra original EX:("casa") e colocando no conjunto wordChars
        for (char c : chars){
            wordChars.add(c);
        }
    }

    //Retorna o tamanho da letra
    public int size() {
        return originalWord.length();
    }


    public boolean hasChar(char c){
        c = Character.toUpperCase(c);

        /*
         * Verficicando se dentro da palavra contem o caractere que foi passado por parametro,
         *  se retorna um valor diferente de -1 quer dizer que tem
         *  quando acha a palavra coloca dentro do conjunto foundChars.
         */
        if(originalWord.indexOf(c) > -1){
            foundChars.add(c);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        char[] charArray = originalWord.toCharArray();

        for(int i = 0; i < charArray.length; i++){
            char c = charArray[i];

            if(foundChars.contains(c)){
                sb.append(c);
            }else {
                sb.append(SECRET_CHAR);
            }
            sb.append(" ");
        }
        return sb.toString();
    }


    public boolean discovered(){
        return foundChars.equals(wordChars);
    }

    public String getOriginalWord() {
        return originalWord;
    }
}
