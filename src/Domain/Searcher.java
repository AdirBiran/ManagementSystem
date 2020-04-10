package Domain;

import Data.Database;
import Presentation.Fan;
import Presentation.Guest;
import java.util.List;

public class Searcher {

    private Database database;

    public Searcher(Database database) {
        this.database = database;
    }

    public void searchInfo(Guest g, String wordToSearch) {
        if(g instanceof Fan){
            ((Fan) g).addToSearchHistory(wordToSearch);
        }
        //search info about wordToSearch
    }

    public void viewInfoAbout(String viewAbout) {
        //what display to user??
    }

    public List<String> viewSearchHistory(Fan fan) {
        return fan.getSearchHistory();
    }
}
