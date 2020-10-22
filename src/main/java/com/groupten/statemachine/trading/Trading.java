package com.groupten.statemachine.trading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

public class Trading implements ITrading {
	
	
	public void startTrading() {
		
		GameConfig gc = new GameConfig();
		League league = new League();
		ArrayList<Conference> conferences = new ArrayList<Conference>();
		
		 for (Map.Entry<String, Conference> entry : league.getConferences().entrySet()) {
			 conferences.add(entry.getValue());
			}
		 
		 for(Conference c : conferences)
		 {
			 ArrayList<Division> divisions = new ArrayList<Division>();
			 for (Map.Entry<String, Division> entry : c.getDivisions().entrySet()) {
				 divisions.add(entry.getValue());
				}
			 
			 for(Division d : divisions)
			 {
				 ArrayList<Team> teams = new ArrayList<Team>();
				 for (Map.Entry<String, Team> entry : d.getTeams().entrySet()) {
					 teams.add(entry.getValue());
					}
				 
				 for(Team t : teams)
				 {
					 if(t.isAITeam()) {
						 if(t.getLossPoint() >= gc.getLossPoint())
						 {
							 HashMap<String, Integer> playerStrengthDetails = new HashMap<String, Integer>();
							 for(Player players : t.getPlayers())
							 {
								 String playerName = players.getPlayerName();
								 int playerStrength = players.getPlayerStrength();
								 
								 playerStrengthDetails.put(playerName, playerStrength);
							 }
							 
							 
							 LinkedList strength = new LinkedList(playerStrengthDetails.entrySet());
						       // Defined Custom Comparator here
						       Collections.sort(strength, new Comparator() {
						            public int compare(Object o1, Object o2) {
						               return ((Comparable) ((Map.Entry) (o2)).getValue())
						                  .compareTo(((Map.Entry) (o1)).getValue());
						            }
						       });

						       // Here I am copying the sorted list in HashMap
						       // using LinkedHashMap to preserve the insertion order
						       HashMap initializingPlayerStrength = new LinkedHashMap();
						       for (Iterator it = strength.iterator(); it.hasNext();) {
						              Map.Entry entry = (Map.Entry) it.next();
						              initializingPlayerStrength.put(entry.getKey(), entry.getValue());
						       } 
							 							 
							 initiateTrading(t , initializingPlayerStrength);
						 }
						 
					 }
				 }
				 
			 }
			 
		 }
		        
	}
	
	public static void initiateTrading(Team tradeInitializingTeam , HashMap<String, Integer> initializingPlayerStrength ) {
		
		GameConfig gc = new GameConfig();
		League league = new League();
		ArrayList<Conference> conferences = new ArrayList<Conference>();
		
		 for (Map.Entry<String, Conference> entry : league.getConferences().entrySet()) {
			 conferences.add(entry.getValue());
			}
		 
		 for(Conference c : conferences)
		 {
			 ArrayList<Division> divisions = new ArrayList<Division>();
			 for (Map.Entry<String, Division> entry : c.getDivisions().entrySet()) {
				 divisions.add(entry.getValue());
				}
			 
			 for(Division d : divisions)
			 {
				 ArrayList<Team> teams = new ArrayList<Team>();
				 for (Map.Entry<String, Team> entry : d.getTeams().entrySet()) {
					 teams.add(entry.getValue());
					}
				 
				 for(Team tradeFinalizingTeam : teams)
				 {
					if(tradeFinalizingTeam != tradeInitializingTeam ) 
					{
						// sorted hashmap of players according to player strength
						 HashMap<String, Integer> playerStrength = new HashMap<String, Integer>();
						 for(Player players : tradeFinalizingTeam.getPlayers())
						 {
							 String playerName = players.getPlayerName();
							 int strength = players.getPlayerStrength();
							 
							 playerStrength.put(playerName, strength);
						 }
						 
						 LinkedList strength = new LinkedList(playerStrength.entrySet());
					       // Defined Custom Comparator here
					       Collections.sort(strength, new Comparator() {
					            public int compare(Object o1, Object o2) {
					               return ((Comparable) ((Map.Entry) (o1)).getValue())
					                  .compareTo(((Map.Entry) (o2)).getValue());
					            }
					       });

					       // Here I am copying the sorted list in HashMap
					       // using LinkedHashMap to preserve the insertion order
					       HashMap finalizingPlayerStrength = new LinkedHashMap();
					       for (Iterator it = strength.iterator(); it.hasNext();) {
					              Map.Entry entry = (Map.Entry) it.next();
					              finalizingPlayerStrength.put(entry.getKey(), entry.getValue());
					       } 
						
					       
						for(int i=0; i<gc.getMaxPlayersPerTrade();i++)
						{
							// get weakest player == maxPlayersPerTrade
							
						}
						
						
					}
				 }
				 
			 }
			 
		 }
		
		
	}
	
}
