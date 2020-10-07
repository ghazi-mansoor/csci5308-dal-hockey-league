package com.groupten.leagueobjectmodel;

import java.util.List;

public class LeagueModel {
    private IPersistence persistenceAPI;
    private List<League> leagues;

    public LeagueModel(IPersistence per) {
        persistenceAPI = per;
    }



}
