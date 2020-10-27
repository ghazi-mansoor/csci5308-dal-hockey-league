package com.groupten.statemachine.simulation;

import java.util.*;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

public class Trading implements ITrading {

	IConsole console = Injector.injector().getConsoleObject();
	ILeagueModel leagueModel = Injector.injector().getLeagueModelObject();
	League leagueLOM = leagueModel.getCurrentLeague();
	Team tradeInitializingTeam = new Team();
	Team tradeFinalizingTeam = new Team();
	
	public void startTrading() {
		
		ArrayList<Conference> conferences = new ArrayList<Conference>();
		
		 for (Map.Entry<String, Conference> entry : leagueLOM.getConferences().entrySet()) {
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
				 
				 for(Team initializingTeam : teams)
				 {
					 if(tradeInitializingTeam.isaITeam()) {
						 if(tradeInitializingTeam.getLossPoint() >= leagueLOM.getLossPoint())
						 {
							 tradeInitializingTeam = initializingTeam;
							 HashMap<Player, Double> initialPlayerStrength = new HashMap<Player, Double>();
							 for(Player players : tradeInitializingTeam.getPlayers())
							 {
								 double playerStrength = players.calculateStrength();
								 
								 initialPlayerStrength.put(players, playerStrength);
							 }

							 
							 LinkedList strength = new LinkedList(initialPlayerStrength.entrySet());
						       // Defined Custom Comparator here
						       Collections.sort(strength, new Comparator() {
						            public int compare(Object o1, Object o2) {
						               return ((Comparable) ((Map.Entry) (o1)).getValue())
						                  .compareTo(((Map.Entry) (o2)).getValue());
						            }
						       });

						       HashMap<Player,Double> initializingWeakestPlayers = new HashMap<Player,Double>();
						       for(int i=1; i < leagueLOM.getMaxPlayersPerTrade();i++)
								{
									if(i > 20 - leagueLOM.getMaxPlayersPerTrade() )
									{
										  initializingWeakestPlayers = new LinkedHashMap();
									       for (Iterator it = strength.iterator(); it.hasNext();) {
									              Map.Entry entry = (Map.Entry) it.next();
									              initializingWeakestPlayers.put((Player)entry.getKey(), (Double) entry.getValue());
									       }
									}

								}
							 if(UITradeOffer())
							 {
								 initiateTrading(tradeInitializingTeam , initializingWeakestPlayers);
							 }

						 }
						 
					 }
				 }
				 
			 }
			 
		 }
		        
	}
	
	public void initiateTrading(Team tradeInitializingTeam , HashMap<Player, Double> initializingWeakestPlayers ) {

		ArrayList<Conference> conferences = new ArrayList<Conference>();
		
		 for (Map.Entry<String, Conference> entry : leagueLOM.getConferences().entrySet()) {
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
				 
				 for(Team finalizingTeam : teams)
				 {
					 ArrayList<String> weakestplayerPositions = new ArrayList<String>();
					 ArrayList<Player> strongestplayerPositions = new ArrayList<Player>();
					 tradeFinalizingTeam = finalizingTeam;
					if(tradeFinalizingTeam != tradeInitializingTeam ) 
					{

						for (Player player :  initializingWeakestPlayers.keySet())
						{
							weakestplayerPositions.add(player.getPosition());
						}
								
					}

					 HashMap<Player, Double> finalPlayerStrength = new HashMap<Player, Double>();
					 for(Player players : tradeFinalizingTeam.getPlayers())
					 {
						 double playersStrength = players.calculateStrength();

						 finalPlayerStrength.put(players, playersStrength);
					 }

					 LinkedList strength = new LinkedList(finalPlayerStrength.entrySet());
					 // Defined Custom Comparator here
					 Collections.sort(strength, new Comparator() {
						 public int compare(Object o1, Object o2) {
							 return ((Comparable) ((Map.Entry) (o1)).getValue())
									 .compareTo(((Map.Entry) (o2)).getValue());
						 }
					 });

					 // Here I am copying the sorted list in HashMap
					 // using LinkedHashMap to preserve the insertion order
					 HashMap<Player,Double> finalizingPlayerStrength = new LinkedHashMap<Player,Double> ();
					 for (Iterator it = strength.iterator(); it.hasNext();) {
						 Map.Entry entry = (Map.Entry) it.next();
						 finalizingPlayerStrength.put((Player) entry.getKey(), (Double) entry.getValue());
					 }

					 HashMap<Player,Player> tradingPlayers = new HashMap<Player,Player>();
					 for(Map.Entry<Player, Double> weakPlayers : initializingWeakestPlayers.entrySet())
					 {
						 for (Map.Entry<Player, Double> strongPlayers : finalizingPlayerStrength.entrySet())
						 {
							if(weakPlayers.getKey().getPosition() == strongPlayers.getKey().getPosition())
							{
								tradingPlayers.put(weakPlayers.getKey(),strongPlayers.getKey());
								finalizingPlayerStrength.remove(strongPlayers.getKey());
							}
						 }
					 }

					 if(tradeFinalizingTeam.isaITeam())
					 {
						 UITradeAccept(tradingPlayers);
					 }
					 else
					 {
						 UserTradeAccept(tradingPlayers);
					 }

				 }

			 }
				 
		 }
			 
	}

	public boolean UITradeOffer(){

		Random random = new Random();
		int upperbound = 1;
		float randomTradeOfferChance = random.nextFloat();
		boolean trade = false;

			if(randomTradeOfferChance < leagueLOM.getRandomTradeOfferChance())
			{
				trade = true;
			}

		return trade;
	}

	public void UITradeAccept(HashMap<Player,Player> tradingPlayers){

		Random random = new Random();
		int upperbound = 1;
		float randomAcceptanceChance = random.nextFloat();

		if(randomAcceptanceChance < leagueLOM.getRandomAcceptanceChance())
		{
			Player player1 = new Player();
			Player player2 = new Player();
			double tradeInitializingTeamStrength = tradeInitializingTeam.getTeamStrength();
			double tradeFinalizingTeamStrength = tradeFinalizingTeam.getTeamStrength();
			double afterTradeInitializingStrength = 0.0;
			double afterTradeFinalizingStrength = 0.0;

			for(Map.Entry<Player, Player> Players : tradingPlayers.entrySet())
			{
				player1 = Players.getKey();
				player2 = Players.getValue();
				tradeInitializingTeam.getPlayers().remove(player1);
				tradeInitializingTeam.getPlayers().add(player2);
				tradeInitializingTeam.getPlayers().remove(player2);
				tradeFinalizingTeam.getPlayers().add(player1);
				if(tradeInitializingTeamStrength < afterTradeInitializingStrength)
				{
					console.printLine("Trade declined since its not beneficial.");
					tradeInitializingTeam.getPlayers().remove(player2);
					tradeInitializingTeam.getPlayers().add(player1);
					tradeInitializingTeam.getPlayers().remove(player1);
					tradeFinalizingTeam.getPlayers().add(player2);
				}
			}

		}



	}

	public void UserTradeAccept(HashMap<Player,Player> tradingPlayers){

		int option = 0;

		Player player1 = new Player();
		Player player2 = new Player();

		console.printLine("Trade initiated!");
		for(Map.Entry<Player, Player> Players : tradingPlayers.entrySet())
		{
			player1 = Players.getKey();
			player2 = Players.getValue();

			console.printLine("Do you want to trade "+player1.getPlayerName()+"with "+player2.getPlayerName());
			console.printLine("1. Yes \n 2. No \n Choose 1 or 2");
			option = console.readInteger();

			switch (option)
			{
				case 1 : tradeInitializingTeam.getPlayers().remove(player1);
					     tradeInitializingTeam.getPlayers().add(player2);
						 tradeInitializingTeam.getPlayers().remove(player2);
						 tradeFinalizingTeam.getPlayers().add(player1);
					     console.printLine(player1.getPlayerName()+ " traded with "+player2.getPlayerName());
					     break;

				case 2 : console.printLine("Trade offer declined.");
						 break;

				default: console.printLine("Invalid option!");
			}


		}

	}
}
	


