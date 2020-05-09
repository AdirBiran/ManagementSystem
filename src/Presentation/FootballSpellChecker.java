package Presentation;
/*import com.swabunga.spell.engine.SpellDictionary;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.StringWordTokenizer;
import com.swabunga.spell.event.SpellChecker;

import java.util.Iterator;
import java.util.List;

public class FootballSpellChecker implements SpellCheckListener
{

    private SpellChecker  spellCheck;
    private SpellDictionary dictionary;

    public FootballSpellChecker() {

        try {
            dictionary = new SpellDictionaryHashMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
        spellCheck = new SpellChecker (dictionary);
            spellCheck.addSpellCheckListener(this);
    }

    public void addWord(String word){
        if(word.length()>0)
            dictionary.addWord(word);
    }


    public void checkSpelling(String line){
        if(line.length()>0){
            System.out.println(spellCheck.checkSpelling(new StringWordTokenizer(line)));
        }
    }

    public void spellingError(SpellCheckEvent event) {
        List suggestions = event.getSuggestions();
        if (suggestions.size() > 0) {
            System.out.println("MISSPELT WORD: " + event.getInvalidWord());
            for (Iterator suggestedWord = suggestions.iterator(); suggestedWord.hasNext();) {
                System.out.println("\tSuggested Word: " + suggestedWord.next());
            }
        } else {
            System.out.println("MISSPELT WORD: " + event.getInvalidWord());
            System.out.println("\tNo suggestions");
        }
        //Null actions
    }

}*/

