package Domain;

import java.util.List;

public class Season {

    private int year;
    private List<League> leagues;

    public Season()
    {

    }


    // ++++++++++++++++++++++++++++ Functions ++++++++++++++++++++++++++++


    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @return
     */
    public List<League> getLeagues() {
        return leagues;
    }
}
