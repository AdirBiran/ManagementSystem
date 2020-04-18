package Domain;

import Data.Database;
import Presentation.Guest;

import java.util.List;

public class Searcher {

    private Database database;

    public Searcher(Database database) {
        this.database = database;
    }
    /*

     */
    public List<Object> searchInfo(Guest g, String wordToSearch) {
        if(g instanceof Fan){
            ((Fan) g).addToSearchHistory(wordToSearch);
        }
        return database.searchObject(wordToSearch);
        //search info about wordToSearch
    }
    /*

     */
    public void viewInfoAbout(String viewAbout) {
        //what display to user??
    }
    /*
    View fan search history
    */
    public List<String> viewSearchHistory(Fan fan) {
        return fan.getSearchHistory();
    }
}
