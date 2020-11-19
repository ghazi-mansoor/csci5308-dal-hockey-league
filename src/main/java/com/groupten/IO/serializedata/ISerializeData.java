package com.groupten.IO.serializedata;

import com.groupten.leagueobjectmodel.league.League;

public interface ISerializeData {

    boolean exportData(League leagueLOM, String path);

}
