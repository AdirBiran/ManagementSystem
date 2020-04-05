package Service;

import Domain.Searcher;
import Presentation.FootballManagementSystem;

public class SearchSystem {

    private Searcher searcher;
    private FootballManagementSystem system;

    public SearchSystem(Searcher searcher, FootballManagementSystem system) {
        this.searcher = searcher;
        this.system = system;
    }
}
