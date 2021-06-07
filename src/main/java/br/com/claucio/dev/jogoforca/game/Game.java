package br.com.claucio.dev.jogoforca.game;

import br.com.claucio.dev.jogoforca.core.Config;
import br.com.claucio.dev.jogoforca.core.Dictionary;
import br.com.claucio.dev.jogoforca.core.InvalidCharacterException;
import br.com.claucio.dev.jogoforca.core.Word;
import br.com.claucio.dev.jogoforca.ui.UI;

import java.util.HashSet;
import java.util.Set;

public class Game {



    public static void start() {

        Dictionary dictionary = Dictionary.getInstance();
        UI.printText("Dicionario usado " + dictionary.getName() );
        Word word = dictionary.nextWord();

        UI.printText("A palavra tem " + word.size() + " letras");

        Set<Character> usedChars = new HashSet<>();
        int erroCount = 0;

        int maxErrors = Integer.parseInt(Config.get("maxErrors"));
        UI.printText("Você pode errar no maximo " + maxErrors + " vezes");

        while (true) {
            UI.printText(word);
            UI.printNewLine();

            char c;
            try {
                c = UI.readChar("Digite a letra");
                if (usedChars.contains(c)) {
                    throw new InvalidCharacterException("Está letra já foi utilizada");
                }
                usedChars.add(c);

                if (word.hasChar(c)) {
                    UI.printText("Você acertou uma letra!");
                } else {
                    erroCount++;
                    if (erroCount < maxErrors) {
                        UI.printText("Você errou! Você pode errar " + (maxErrors - erroCount) + " vez(es)");

                    }
                }
                UI.printNewLine();

                if (word.discovered()) {
                    UI.printText("PARABÉNS! Você acertou a palavra correta: " + word.getOriginalWord());
                    UI.printText("FIM DO JOGO");
                    break;
                }

                if (erroCount == maxErrors) {
                    UI.printText("Você perdeu o jogo, a palavra era:" + word.getOriginalWord());
                    UI.printText("FIM DO JOGO");
                    break;
                }


            } catch (InvalidCharacterException e) {
                UI.printText("Erro: " + e.getMessage());
                UI.printNewLine();
            }
        }

    }
}
