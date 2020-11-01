package com.groupten.statemachine.simulation.trading;

import com.groupten.IO.console.IConsole;
import com.groupten.injector.Injector;
import com.groupten.leagueobjectmodel.conference.Conference;
import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.gameconfig.GameConfig;
import com.groupten.leagueobjectmodel.league.League;
import com.groupten.leagueobjectmodel.leaguemodel.ILeagueModel;
import com.groupten.leagueobjectmodel.player.Player;
import com.groupten.leagueobjectmodel.team.Team;

import java.util.*;

public class Trading implements ITrading {
	private IConsole console = Injector.instance().getConsoleObject();
	private ILeagueModel leagueModel = Injector.instance().getLeagueModelObject();
	private League leagueLOM = leagueModel.getCurrentLeague();
	private Team tradeInitializingTeam = new Team();
	private Team tradeFinalizingTeam = new Team();
	private final GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
	private final int maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
	private final int lossPoint = tradingConfig.getLossPoint();
	private final double randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
	private final double randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();

	private final int teamSize = 20;
	private final boolean trade = false;
	private String playerName;
	
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
					 if(tradeInitializingTeam.isaITeam())
					 {
						 if(tradeInitializingTeam.getLossPoint() >= lossPoint)
						 {
							 tradeInitializingTeam = initializingTeam;
							 HashMap<Player, Double> initialPlayerStrength = new HashMap<Player, Double>();
							 for(Player players : tradeInitializingTeam.getPlayers())
							 {
								 double playerStrength = players.calculateStrength();
								 initialPlayerStrength.put(players, playerStrength);
							 }

							 
							 LinkedList strength = new LinkedList(initialPlayerStrength.entrySet());

							 LinkedList orderedStrength = sortByPlayerStrength(strength);

							 HashMap<Player,Double> initializingWeakestPlayers = getWeakestPlayers(orderedStrength);

							 if(UITradeOffer())
							 {
								 initiateTrading(initializingWeakestPlayers);
							 }

						 }
						 
					 }
				 }
				 
			 }
			 
		 }
		        
	}

	public void initiateTrading(HashMap<Player, Double> initializingWeakestPlayers ) {

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
					 LinkedList orderedStrength = sortByPlayerStrength(strength);

					 // Here I am copying the sorted list in HashMap
					 // using LinkedHashMap to preserve the insertion order
					 HashMap<Player,Double> finalizingPlayerStrength = new LinkedHashMap<Player,Double> ();
					 for (Iterator it = orderedStrength.iterator(); it.hasNext();) {
						 Map.Entry entry = (Map.Entry) it.next();
						 finalizingPlayerStrength.put((Player) entry.getKey(), (Double) entry.getValue());
					 }

					 HashMap<Player,Player> tradingPlayers = generatePlayersForTrading(initializingWeakestPlayers ,
							 															finalizingPlayerStrength);

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

	public HashMap<Player,Player> generatePlayersForTrading(HashMap<Player,Double> initializingWeakestPlayers,
										  HashMap<Player,Double> finalizingPlayerStrength){

		HashMap<Player,Player> tradingPlayers = new HashMap<Player,Player>();
		for(Map.Entry<Player, Double> weakPlayers : initializingWeakestPlayers.entrySet())
		{
			for (Map.Entry<Player, Double> strongPlayers : finalizingPlayerStrength.entrySet())
			{
				if(weakPlayers.getKey().getPosition() == strongPlayers.getKey().getPosition())
				{
					if(!tradingPlayers.containsKey(weakPlayers.getKey()))
					{
						tradingPlayers.put(weakPlayers.getKey(),strongPlayers.getKey());
						finalizingPlayerStrength.remove(strongPlayers.getKey());
					}
				}
			}
		}

		return tradingPlayers;
	}

	public boolean UITradeOffer(){

		Random random = new Random();
		int upperbound = 1;
		double randomTradeOfferChanceGenerated = random.nextDouble();

			if(randomTradeOfferChanceGenerated < randomTradeOfferChance)
			{
				trade = true;
			}

		return trade;
	}

	public void UITradeAccept(HashMap<Player,Player> tradingPlayers){

		Random random = new Random();
		double randomAcceptanceChanceGenerated = random.nextDouble();
		Player player1 = new Player();
		Player player2 = new Player();
		double tradeInitializingTeamStrength = 0.0;
		double afterTradeInitializingStrength = 0.0;
		double tradeFinalizingTeamStrength = 0.0;
		double afterTradeFinalizingStrength = 0.0;

		tradeInitializingTeam.calculateTeamStrength();
		tradeInitializingTeamStrength = tradeInitializingTeam.getTeamStrength();
		tradeFinalizingTeam.calculateTeamStrength();
		tradeFinalizingTeamStrength = tradeFinalizingTeam.getTeamStrength();

		for(Map.Entry<Player, Player> Players : tradingPlayers.entrySet())
		{
			player1 = Players.getKey();
			player2 = Players.getValue();
			tradeInitializingTeam.getPlayers().remove(player1);
			tradeInitializingTeam.getPlayers().add(player2);
			tradeFinalizingTeam.getPlayers().remove(player2);
			tradeFinalizingTeam.getPlayers().add(player1);
		}

		tradeInitializingTeam.setTeamStrength(0);
		tradeFinalizingTeam.setTeamStrength(0);
		tradeInitializingTeam.calculateTeamStrength();
		afterTradeInitializingStrength = tradeInitializingTeam.getTeamStrength();
		tradeFinalizingTeam.calculateTeamStrength();
		afterTradeFinalizingStrength = tradeFinalizingTeam.getTeamStrength();

		if(tradeInitializingTeamStrength < afterTradeInitializingStrength || tradeFinalizingTeamStrength < afterTradeFinalizingStrength)
		{
			if(randomAcceptanceChanceGenerated > randomAcceptanceChance)
			{
				console.printLine("Trade declined since its not beneficial.");
				tradeInitializingTeam.setLossPoint(0);
				tradeInitializingTeam.getPlayers().remove(player2);
				tradeInitializingTeam.getPlayers().add(player1);
				tradeFinalizingTeam.getPlayers().remove(player1);
				tradeFinalizingTeam.getPlayers().add(player2);
			}
			else
			{
				tradeInitializingTeam.setLossPoint(0);
				console.printLine("Trade successful!");
			}
		}
			tradeInitializingTeam.setLossPoint(0);
			adjustTeamPlayers();
	}

	public void UserTradeAccept(HashMap<Player,Player> tradingPlayers){

		int option = 0;
		Player player1 = new Player();
		Player player2 = new Player();

		console.printLine("Trade initiated!");
		for(Map.Entry<Player, Player> Players : tradingPlayers.entrySet()) {
			player1 = Players.getKey();
			player2 = Players.getValue();


			console.printLine("\n Player Name : " + player1.getPlayerName() + "\n Age : " + player1.getAge() + "\n Checking : " + player1.getChecking()
					+ "\n Saving : " + player1.getSaving() + "\n Shooting : " + player1.getShooting() + "\n Saving : " + player1.getSaving() + "\n");

			console.printLine("\n Player Name : " + player2.getPlayerName() + "\n Age : " + player2.getAge() + "\n Checking : " + player2.getChecking()
					+ "\n Saving : " + player2.getSaving() + "\n Shooting : " + player2.getShooting() + "\n Saving : " + player2.getSaving() + "\n");

			console.printLine("\n Do you want to trade " + player1.getPlayerName() + "with " + player2.getPlayerName());

			console.printLine("1. Yes \n 2. No \n Choose 1 or 2");
			option = console.readInteger();

			switch (option) {
				case 1:
					tradeInitializingTeam.getPlayers().remove(player1);
					tradeInitializingTeam.getPlayers().add(player2);
					tradeFinalizingTeam.getPlayers().remove(player2);
					tradeFinalizingTeam.getPlayers().add(player1);
					console.printLine(player1.getPlayerName() + " traded with " + player2.getPlayerName());
					break;

				case 2:
					console.printLine("Trade offer declined.");
					tradeInitializingTeam.setLossPoint(0);
					break;

				default:
					console.printLine("Invalid option!");
			}
			tradeInitializingTeam.setLossPoint(0);
		}

		adjustTeamPlayers();

	}

	public void adjustTeamPlayers(){

		int initializingTeamSize = 0;
		int finalizingTeamSize = 0;

		initializingTeamSize = tradeInitializingTeam.getPlayers().size();
		finalizingTeamSize = tradeFinalizingTeam.getPlayers().size();

		if(tradeInitializingTeam.isaITeam())
		{
			if(initializingTeamSize > teamSize)
			{
				tradeInitializingTeam = UIDropPlayers(tradeInitializingTeam);
			}
			else if (initializingTeamSize < teamSize)
			{
				tradeInitializingTeam = UIGetFromFreeAgents(tradeInitializingTeam);
			}
		}

		if(tradeFinalizingTeam.isaITeam())
		{
			if(finalizingTeamSize > teamSize)
			{
				tradeFinalizingTeam = UIDropPlayers(tradeFinalizingTeam);
			}

			if (finalizingTeamSize < teamSize)
			{
				tradeFinalizingTeam = UIGetFromFreeAgents(tradeFinalizingTeam);
			}
		}

		else
		{
			if (finalizingTeamSize > teamSize)
			{
				tradeFinalizingTeam = userDropPlayers(tradeFinalizingTeam);
			}

			if(finalizingTeamSize < teamSize)
			{
				tradeFinalizingTeam = userGetFromFreeAgents(tradeFinalizingTeam);
			}
		}

	}

	public Team UIDropPlayers(Team tradingTeam) {

		int goalieCount = 0;
		int skaterCount = 0;

		HashMap<Player, Double> teamPlayerStrength = new HashMap<Player, Double>();

		for(Player players : tradingTeam.getPlayers())
		{
			double playerStrength = players.calculateStrength();
			teamPlayerStrength.put(players, playerStrength);
		}


		LinkedList strength = new LinkedList(teamPlayerStrength.entrySet());

		LinkedList orderedStrength = sortByPlayerStrength(strength);
		Collections.reverse(orderedStrength);

		HashMap<Player,Double> orderedPlayerStrength = new LinkedHashMap<Player,Double>();

		for (Iterator it = orderedStrength.iterator(); it.hasNext();)
		{
			Map.Entry entry = (Map.Entry) it.next();
			orderedPlayerStrength.put((Player)entry.getKey(), (Double) entry.getValue());
		}


		for (Map.Entry<Player, Double> entry : orderedPlayerStrength.entrySet())
		{
			if(entry.getKey().getPosition() == "goalie")
			{
				goalieCount++;

				if(goalieCount > 2)
				{
					//orderedPlayerStrength.remove(entry.getKey());
					tradingTeam.getPlayers().remove(entry.getKey());
					leagueLOM.addFreeAgent(entry.getKey());
				}
			}
			else
			{
				skaterCount++;

				if(skaterCount > 18)
				{
					//orderedPlayerStrength.remove(entry.getKey());
					tradingTeam.getPlayers().remove(entry.getKey());
					leagueLOM.addFreeAgent(entry.getKey());
				}
			}
		}
		return tradingTeam;
	}

	public Team userDropPlayers(Team tradingTeam){

		int i = 1;
		int goalieCount = 0;
		int skaterCount = 0;
		List<Player> players = new ArrayList<>();

		for(Player player : tradingTeam.getPlayers())
		{
			if(player.getPosition() == "goalie")
			{
				goalieCount++;
			}
			else
			{
				skaterCount++;
			}
		}

		for(Player player : tradingTeam.getPlayers())
		{
			if (player.getPosition().equals("goalie"))
			{
				console.printLine(i+"\n Player Name : "+player.getPlayerName()+"\n Age : "+player.getAge()+"\n Checking : "+player.getChecking()
						+"\n Saving : "+player.getSaving()+"\n Shooting : "+player.getShooting()+"\n Saving : "+player.getSaving()+"\n");
				i++;
			}
		}

		while (goalieCount > 2)
		{
			try{
				console.printLine("Please choose a goalie (player name) to drop : \n");
				playerName = console.readLine();
			}
			catch (Exception e)
			{
				System.out.println("Invalid value!");
				break;
			}


			for (Player player : tradingTeam.getPlayers())
			{
				if(playerName.equals(player.getPlayerName()))
				{
					tradingTeam.getPlayers().remove(player);
					goalieCount--;
					leagueLOM.addFreeAgent(player);
				}
			}
		}

		for(Player player : tradingTeam.getPlayers())
		{
			if (player.getPosition().equals("forward") || player.getPosition().equals("defense"))
			{
				console.printLine(i+"\n Player Name : "+player.getPlayerName()+"\n Age : "+player.getAge()+"\n Checking : "+player.getChecking()
						+"\n Saving : "+player.getSaving()+"\n Shooting : "+player.getShooting()+"\n Saving : "+player.getSaving()+"\n");
				i++;
			}
		}

		while (skaterCount > 18)
		{
			try{
				console.printLine("Please choose a skater (player name) to drop : \n");
				playerName = console.readLine();
			}
			catch (Exception e)
			{
				System.out.println("Invalid value!");
				break;
			}


			for (Player player : tradingTeam.getPlayers())
			{
				if(playerName.equals(player.getPlayerName()))
				{
					tradingTeam.getPlayers().remove(player);
					skaterCount--;
					leagueLOM.getFreeAgentsGoalies().add(player);
				}
			}
		}

		return tradingTeam;
	}

	public Team UIGetFromFreeAgents(Team tradingTeam){

		int goalieCount = 0;
		int skaterCount = 0;
		HashMap<Player, Double> teamPlayerStrength = new HashMap<Player, Double>();

		for(Player player : tradingTeam.getPlayers())
		{
			if(player.getPosition() == "goalie")
			{
				goalieCount++;
			}
			else
			{
				skaterCount++;
			}
		}
		for(Player players : leagueLOM.getFreeAgentsGoalies())
		{
			double playerStrength = players.calculateStrength();
			teamPlayerStrength.put(players, playerStrength);
		}
		LinkedList goalieStrength = new LinkedList(teamPlayerStrength.entrySet());
		LinkedList orderedGoalieStrength = sortByPlayerStrength(goalieStrength);
		Collections.reverse(orderedGoalieStrength);
		HashMap<Player,Double> orderedGoaliePlayerStrength = new LinkedHashMap<Player,Double>();
		for (Iterator it = orderedGoalieStrength.iterator(); it.hasNext();)
		{
			Map.Entry entry = (Map.Entry) it.next();
			orderedGoaliePlayerStrength.put((Player)entry.getKey(), (Double) entry.getValue());
		}
		for (Map.Entry<Player, Double> entry : orderedGoaliePlayerStrength.entrySet())
		{
			if(goalieCount < 2)
			{
				if(entry.getKey().getPosition().equals("goalie"))
				{
					tradingTeam.getPlayers().add(entry.getKey());
					leagueLOM.getFreeAgentsGoalies().remove(entry.getKey());
					goalieCount++;
				}
			}
		}

		for(Player players : leagueLOM.getFreeAgentsSkaters())
		{
			double playerStrength = players.calculateStrength();
			teamPlayerStrength.put(players, playerStrength);
		}


		LinkedList skaterStrength = new LinkedList(teamPlayerStrength.entrySet());
		LinkedList orderedSkaterStrength = sortByPlayerStrength(skaterStrength);
		Collections.reverse(orderedSkaterStrength);
		HashMap<Player,Double> orderedSkaterPlayerStrength = new LinkedHashMap<Player,Double>();
		for (Iterator it = orderedSkaterStrength.iterator(); it.hasNext();)
		{
			Map.Entry entry = (Map.Entry) it.next();
			orderedSkaterPlayerStrength.put((Player)entry.getKey(), (Double) entry.getValue());
		}
		for (Map.Entry<Player, Double> entry : orderedSkaterPlayerStrength.entrySet())
		{
			if (skaterCount < 18)
			{
				if(entry.getKey().getPosition().equals("forward") || entry.getKey().getPosition().equals("defense"))
				{
					tradingTeam.getPlayers().add(entry.getKey());
					leagueLOM.getFreeAgentsSkaters().remove(entry.getKey());
					skaterCount++;
				}
			}
		}

		return tradingTeam;
	}

	public Team userGetFromFreeAgents(Team tradingTeam){
		int goalieCount = 0;
		int skaterCount = 0;
		List<Player> goalies = new ArrayList<>();
		List<Player> skaters = new ArrayList<>();
		int i = 1;
		HashMap<Player, Double> teamPlayerStrength = new HashMap<Player, Double>();

		for(Player player : tradingTeam.getPlayers())
		{
			if(player.getPosition() == "goalie")
			{
				goalieCount++;
			}
			else
			{
				skaterCount++;
			}
		}

		if (goalieCount < 2)
		{
			for (Player player : leagueLOM.getFreeAgentsGoalies())
			{
				console.printLine(i+"\n Player Name : "+player.getPlayerName()+"\n Age : "+player.getAge()+"\n Checking : "+player.getChecking()
						+"\n Saving : "+player.getSaving()+"\n Shooting : "+player.getShooting()+"\n Saving : "+player.getSaving()+"\n");
				i++;
			}


			while (goalieCount < 2)
			{
				try{
					console.printLine("Please choose a goalie (player name) from the given list.");
					playerName = console.readLine();
				}
				catch (Exception e){
					System.out.println("Invalid value");
					break;
				}


				for (Player player : leagueLOM.getFreeAgentsGoalies())
				{
					if(playerName.equals(player.getPlayerName()))
					{
						tradingTeam.addPlayer(player);
						leagueLOM.getFreeAgentsGoalies().remove(player);
						goalieCount++;
					}
				}
			}

		}

		if (skaterCount < 18)
		{
			for (Player player : leagueLOM.getFreeAgentsSkaters())
			{
				console.printLine(i+"\n Player Name : "+player.getPlayerName()+"\n Age : "+player.getAge()+"\n Checking : "+player.getChecking()
						+"\n Saving : "+player.getSaving()+"\n Shooting : "+player.getShooting()+"\n Saving : "+player.getSaving()+"\n");
				i++;
			}

			while (skaterCount < 18)
			{
				try{
					console.printLine("Please choose a skater (player name) from the given list.");
					playerName = console.readLine();
				}
				catch (Exception e){
					System.out.println("Invalid value");
					break;
				}


				for (Player player : leagueLOM.getFreeAgentsGoalies())
				{
					if (playerName.equals(player.getPlayerName()))
					{
						tradingTeam.addPlayer(player);
						leagueLOM.getFreeAgentsSkaters().remove(player);
						skaterCount++;
					}
				}
			}
		}

		return tradingTeam;

	}

	public LinkedList sortByPlayerStrength(LinkedList strength){

		Collections.sort(strength, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		return strength;
	}

	public HashMap<Player,Double> getWeakestPlayers(LinkedList orderedStrength){

		int i = 0;
		HashMap<Player,Double> initializingWeakestPlayers = new LinkedHashMap<Player,Double>();
		for (Iterator it = orderedStrength.descendingIterator(); it.hasNext();)
		{
			Map.Entry entry = (Map.Entry) it.next();
			if(i < maxPlayersPerTrade)
			{
				initializingWeakestPlayers.put((Player)entry.getKey(), (Double) entry.getValue());
				i++;
			}

		}
		return initializingWeakestPlayers;
	}
}
	


