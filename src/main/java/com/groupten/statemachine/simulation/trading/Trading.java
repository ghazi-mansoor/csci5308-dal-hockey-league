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

	public Trading() {
	}

	private IConsole console;
	private ILeagueModel leagueModel;
	private League leagueLOM;
	private Team tradeInitializingTeam = new Team();
	private Team tradeFinalizingTeam = new Team();
	private int maxPlayersPerTrade = 0;
	private int lossPoint = 0;
	private double randomTradeOfferChance = 0.0;
	private double randomAcceptanceChance = 0.0;
	private boolean trade = false;
	private String playerName;
	private final int teamSize = 20;
	private final int numberOfGoalies = 2;
	private final int numberOfSkaters = 18;

	public Team getTradeInitializingTeam() {
		return tradeInitializingTeam;
	}

	public void setTradeInitializingTeam(Team tradeInitializingTeam) {
		this.tradeInitializingTeam = tradeInitializingTeam;
	}

	public Team getTradeFinalizingTeam() {
		return tradeFinalizingTeam;
	}

	public void setTradeFinalizingTeam(Team tradeFinalizingTeam) {
		this.tradeFinalizingTeam = tradeFinalizingTeam;
	}

	public int getTeamSize() {
		return teamSize;
	}

	public int getNumberOfGoalies() {
		return numberOfGoalies;
	}

	public int getNumberOfSkaters() {
		return numberOfSkaters;
	}

	public boolean isTrade() {
		return trade;
	}

	public void setTrade(boolean trade) {
		this.trade = trade;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public void startTrading() {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();

		for (Conference c : leagueLOM.getConferences().values()) {
			for (Division d : c.getDivisions().values()) {
				for (Team initializingTeam : d.getTeams().values()) {
					tradeInitializingTeam = initializingTeam;
					if (tradeInitializingTeam.isaITeam()) {
						if (tradeInitializingTeam.getLossPoint() >= lossPoint) {
							HashMap<Player, Double> initialPlayerStrength = new HashMap<Player, Double>();
							for (Player players : tradeInitializingTeam.getActivePlayers()) {
								double playerStrength = players.calculateStrength();
								initialPlayerStrength.put(players, playerStrength);
							}
							LinkedList strength = new LinkedList(initialPlayerStrength.entrySet());
							LinkedList orderedStrength = sortByPlayerStrength(strength);
							HashMap<Player, Double> initializingWeakestPlayers = getWeakestPlayers(orderedStrength);
							if (UITradeOffer()) {
								initiateTrading(initializingWeakestPlayers);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public void initiateTrading(HashMap<Player, Double> initializingWeakestPlayers) {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();

		for (Conference c : leagueLOM.getConferences().values()) {
			for (Division d : c.getDivisions().values()) {
				for (Team finalizingTeam : d.getTeams().values()) {
					ArrayList<String> weakestplayerPositions = new ArrayList<String>();
					tradeFinalizingTeam = finalizingTeam;
					if (tradeFinalizingTeam == tradeInitializingTeam) {
						return;
					} else {
						for (Player player : initializingWeakestPlayers.keySet()) {
							weakestplayerPositions.add(player.getPosition());
						}
						HashMap<Player, Double> finalPlayerStrength = new HashMap<Player, Double>();
						for (Player players : tradeFinalizingTeam.getActivePlayers()) {
							double playersStrength = players.calculateStrength();

							finalPlayerStrength.put(players, playersStrength);
						}
						LinkedList strength = new LinkedList(finalPlayerStrength.entrySet());
						LinkedList orderedStrength = sortByPlayerStrength(strength);
						HashMap<Player, Double> finalizingPlayerStrength = new LinkedHashMap<Player, Double>();
						for (Iterator it = orderedStrength.iterator(); it.hasNext(); ) {
							Map.Entry entry = (Map.Entry) it.next();
							finalizingPlayerStrength.put((Player) entry.getKey(), (Double) entry.getValue());
						}
						HashMap<Player, Player> tradingPlayers = generatePlayersForTrading(initializingWeakestPlayers,
								finalizingPlayerStrength);
						if (tradeFinalizingTeam.isaITeam()) {
							UITradeAccept(tradingPlayers);
						} else {
							userTradeAccept(tradingPlayers);
						}
					}
				}
			}
		}
	}

	@Override
	public HashMap<Player, Player> generatePlayersForTrading(HashMap<Player, Double> initializingWeakestPlayers,
															 HashMap<Player, Double> finalizingPlayerStrength) {

		HashMap<Player, Player> tradingPlayers = new HashMap<Player, Player>();
		for (Map.Entry<Player, Double> weakPlayers : initializingWeakestPlayers.entrySet()) {
			for (Map.Entry<Player, Double> strongPlayers : finalizingPlayerStrength.entrySet()) {
				if (weakPlayers.getKey().getPosition().equals(strongPlayers.getKey().getPosition())) {
					if (tradingPlayers.containsKey(weakPlayers.getKey())) {
						continue;
					}
					else{
						tradingPlayers.put(weakPlayers.getKey(), strongPlayers.getKey());
					}
				}
			}
		}
		return tradingPlayers;
	}

	@Override
	public boolean UITradeOffer() {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		Random random = new Random();
		int upperbound = 1;

		double randomTradeOfferChanceGenerated = random.nextDouble();
		if (randomTradeOfferChanceGenerated < randomTradeOfferChance) {
			trade = true;
		}
		return trade;
	}

	@Override
	public void UITradeAccept(HashMap<Player, Player> tradingPlayers) {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
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
		for (Map.Entry<Player, Player> Players : tradingPlayers.entrySet()) {
			player1 = Players.getKey();
			player2 = Players.getValue();
			tradeInitializingTeam.getActivePlayers().remove(player1);
			tradeInitializingTeam.getActivePlayers().add(player2);
			tradeFinalizingTeam.getActivePlayers().remove(player2);
			tradeFinalizingTeam.getActivePlayers().add(player1);
		}
		tradeInitializingTeam.setTeamStrength(0);
		tradeFinalizingTeam.setTeamStrength(0);
		tradeInitializingTeam.calculateTeamStrength();
		afterTradeInitializingStrength = tradeInitializingTeam.getTeamStrength();
		tradeFinalizingTeam.calculateTeamStrength();
		afterTradeFinalizingStrength = tradeFinalizingTeam.getTeamStrength();
		if (tradeInitializingTeamStrength < afterTradeInitializingStrength || tradeFinalizingTeamStrength < afterTradeFinalizingStrength) {
			if (randomAcceptanceChanceGenerated > randomAcceptanceChance) {
				console.printLine("Trade declined since its not beneficial.");
				tradeInitializingTeam.setLossPoint(0);
				tradeInitializingTeam.getActivePlayers().remove(player2);
				tradeInitializingTeam.getActivePlayers().add(player1);
				tradeFinalizingTeam.getActivePlayers().remove(player1);
				tradeFinalizingTeam.getActivePlayers().add(player2);
			} else {
				tradeInitializingTeam.setLossPoint(0);
				console.printLine("Trade successful!");
			}
		}
		tradeInitializingTeam.setLossPoint(0);
		adjustTeamPlayers();
	}

	@Override
	public void userTradeAccept(HashMap<Player, Player> tradingPlayers) {
		int option = 0;
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		Player player1 = new Player();
		Player player2 = new Player();
		int maximumPlayerPerTrade = 1;

		console.printLine("User trade initiated!");

		for (Map.Entry<Player, Player> Players : tradingPlayers.entrySet()) {
			if (maximumPlayerPerTrade > maxPlayersPerTrade) {
				break;
			}

			player1 = Players.getKey();
			player2 = Players.getValue();
			console.printLine("\n Player Name : " + player1.getPlayerName() + "\n Age : " + player1.getAge() + "\n Checking : " + player1.getChecking()
					+ "\n Saving : " + player1.getSaving() + "\n Shooting : " + player1.getShooting() + "\n Saving : " + player1.getSaving() + "\n");
			console.printLine("\n Player Name : " + player2.getPlayerName() + "\n Age : " + player2.getAge() + "\n Checking : " + player2.getChecking()
					+ "\n Saving : " + player2.getSaving() + "\n Shooting : " + player2.getShooting() + "\n Saving : " + player2.getSaving() + "\n");
			console.printLine("\n Do you want to trade " + player1.getPlayerName() + " with " + player2.getPlayerName());
			console.printLine("1. Yes \n 2. No \n Choose 1 or 2");
			option = console.readInteger();
			switch (option) {
				case 1:
					tradeInitializingTeam.getActivePlayers().remove(player1);
					tradeInitializingTeam.getActivePlayers().add(player2);
					tradeFinalizingTeam.getActivePlayers().remove(player2);
					tradeFinalizingTeam.getActivePlayers().add(player1);
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
			break;
		}
		adjustTeamPlayers();
	}

	@Override
	public void adjustTeamPlayers() {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		int initializingTeamSize = 0;
		int finalizingTeamSize = 0;

		initializingTeamSize = tradeInitializingTeam.getActivePlayers().size();
		finalizingTeamSize = tradeFinalizingTeam.getActivePlayers().size();

		if (tradeInitializingTeam.isaITeam()) {
			if (initializingTeamSize > teamSize) {
				tradeInitializingTeam = UIDropPlayers(tradeInitializingTeam);
			} else if (initializingTeamSize < teamSize) {
				tradeInitializingTeam = UIGetFromFreeAgents(tradeInitializingTeam);
			}
		}
		if (tradeFinalizingTeam.isaITeam()) {
			if (finalizingTeamSize > teamSize) {
				tradeFinalizingTeam = UIDropPlayers(tradeFinalizingTeam);
			}

			if (finalizingTeamSize < teamSize) {
				tradeFinalizingTeam = UIGetFromFreeAgents(tradeFinalizingTeam);
			}
		} else {
			if (finalizingTeamSize > teamSize) {
				tradeFinalizingTeam = userDropPlayers(tradeFinalizingTeam);
			}

			if (finalizingTeamSize < teamSize) {
				tradeFinalizingTeam = userGetFromFreeAgents(tradeFinalizingTeam);
			}
		}

	}

	@Override
	public Team UIDropPlayers(Team tradingTeam) {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		int goalieCount = 0;
		int skaterCount = 0;

		HashMap<Player, Double> teamPlayerStrength = new HashMap<Player, Double>();
		for (Player players : tradingTeam.getActivePlayers()) {
			double playerStrength = players.calculateStrength();
			teamPlayerStrength.put(players, playerStrength);
		}
		LinkedList strength = new LinkedList(teamPlayerStrength.entrySet());
		LinkedList orderedStrength = sortByPlayerStrength(strength);
		Collections.reverse(orderedStrength);
		HashMap<Player, Double> orderedPlayerStrength = new LinkedHashMap<Player, Double>();
		for (Iterator it = orderedStrength.iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry) it.next();
			orderedPlayerStrength.put((Player) entry.getKey(), (Double) entry.getValue());
		}
		for (Map.Entry<Player, Double> entry : orderedPlayerStrength.entrySet()) {
			if (entry.getKey().getPosition() == "goalie") {
				goalieCount++;

				if (goalieCount > numberOfGoalies) {
					tradingTeam.getActivePlayers().remove(entry.getKey());
					leagueLOM.addFreeAgent(entry.getKey());
				}
			} else {
				skaterCount++;

				if (skaterCount > numberOfSkaters) {
					tradingTeam.getActivePlayers().remove(entry.getKey());
					leagueLOM.addFreeAgent(entry.getKey());
				}
			}
		}
		return tradingTeam;
	}

	@Override
	public Team userDropPlayers(Team tradingTeam) {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		List<Player> players = new ArrayList<>();
		int i = 1;
		int goalieCount = 0;
		int skaterCount = 0;

		for (Player player : tradingTeam.getActivePlayers()) {
			if (player.getPosition() == "goalie") {
				goalieCount++;
			} else {
				skaterCount++;
			}
		}
		for (Player player : tradingTeam.getActivePlayers()) {
			if (player.getPosition().equals("goalie")) {
				console.printLine(i + "\n Player Name : " + player.getPlayerName() + "\n Age : " + player.getAge() + "\n Checking : " + player.getChecking()
						+ "\n Saving : " + player.getSaving() + "\n Shooting : " + player.getShooting() + "\n Saving : " + player.getSaving() + "\n");
				i++;
			}
		}
		while (goalieCount > numberOfGoalies) {
			List<Player> updatedPlayersList = new ArrayList<Player>();
			try {
				console.printLine("Please choose a goalie (player name) to drop : \n");
				playerName = console.readLine();
			} catch (Exception e) {
				System.out.println("Invalid value!");
				break;
			}
			for (Player player : tradingTeam.getActivePlayers()) {
				if (playerName.equals(player.getPlayerName())) {
					goalieCount--;
					leagueLOM.addFreeAgent(player);
				}
				else {
					updatedPlayersList.add(player);
				}
			}
			tradingTeam.setActivePlayers(updatedPlayersList);
		}
		for (Player player : tradingTeam.getActivePlayers()) {
			i = 0;
			if (player.getPosition().equals("forward") || player.getPosition().equals("defense")) {
				console.printLine(i + "\n Player Name : " + player.getPlayerName() + "\n Age : " + player.getAge() + "\n Checking : " + player.getChecking()
						+ "\n Saving : " + player.getSaving() + "\n Shooting : " + player.getShooting() + "\n Saving : " + player.getSaving() + "\n");
				i++;
			}
		}
		while (skaterCount > numberOfSkaters) {
			List<Player> updatedPlayersList = new ArrayList<Player>();
			try {
				console.printLine("Please choose a skater (player name) to drop : \n");
				playerName = console.readLine();
			} catch (Exception e) {
				System.out.println("Invalid value!");
				break;
			}
			for (Player player : tradingTeam.getActivePlayers()) {
				if (playerName.equals(player.getPlayerName())) {
					skaterCount--;
					leagueLOM.getFreeAgentsGoalies().add(player);
				}
				else {
					updatedPlayersList.add(player);
				}
			}
			tradingTeam.setActivePlayers(updatedPlayersList);
		}
		return tradingTeam;
	}

	@Override
	public Team UIGetFromFreeAgents(Team tradingTeam) {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		HashMap<Player, Double> teamPlayerStrength = new HashMap<Player, Double>();
		int goalieCount = 0;
		int skaterCount = 0;

		for (Player player : tradingTeam.getActivePlayers()) {
			if (player.getPosition() == "goalie") {
				goalieCount++;
			} else {
				skaterCount++;
			}
		}
		for (Player players : leagueLOM.getFreeAgentsGoalies()) {
			double playerStrength = players.calculateStrength();
			teamPlayerStrength.put(players, playerStrength);
		}
		LinkedList goalieStrength = new LinkedList(teamPlayerStrength.entrySet());
		LinkedList orderedGoalieStrength = sortByPlayerStrength(goalieStrength);
		Collections.reverse(orderedGoalieStrength);
		HashMap<Player, Double> orderedGoaliePlayerStrength = new LinkedHashMap<Player, Double>();
		for (Iterator it = orderedGoalieStrength.iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry) it.next();
			orderedGoaliePlayerStrength.put((Player) entry.getKey(), (Double) entry.getValue());
		}
		for (Map.Entry<Player, Double> entry : orderedGoaliePlayerStrength.entrySet()) {
			if (goalieCount < numberOfGoalies) {
				if (entry.getKey().getPosition().equals("goalie")) {
					tradingTeam.getActivePlayers().add(entry.getKey());
					leagueLOM.getFreeAgentsGoalies().remove(entry.getKey());
					goalieCount++;
				}
			}
		}
		for (Player players : leagueLOM.getFreeAgentsSkaters()) {
			double playerStrength = players.calculateStrength();
			teamPlayerStrength.put(players, playerStrength);
		}
		LinkedList skaterStrength = new LinkedList(teamPlayerStrength.entrySet());
		LinkedList orderedSkaterStrength = sortByPlayerStrength(skaterStrength);
		Collections.reverse(orderedSkaterStrength);
		HashMap<Player, Double> orderedSkaterPlayerStrength = new LinkedHashMap<Player, Double>();
		for (Iterator it = orderedSkaterStrength.iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry) it.next();
			orderedSkaterPlayerStrength.put((Player) entry.getKey(), (Double) entry.getValue());
		}
		for (Map.Entry<Player, Double> entry : orderedSkaterPlayerStrength.entrySet()) {
			if (skaterCount < numberOfSkaters) {
				if (entry.getKey().getPosition().equals("forward") || entry.getKey().getPosition().equals("defense")) {
					tradingTeam.getActivePlayers().add(entry.getKey());
					leagueLOM.getFreeAgentsSkaters().remove(entry.getKey());
					skaterCount++;
				}
			}
		}
		return tradingTeam;
	}

	@Override
	public Team userGetFromFreeAgents(Team tradingTeam) {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		HashMap<Player, Double> teamPlayerStrength = new HashMap<Player, Double>();
		List<Player> goalies = new ArrayList<>();
		List<Player> skaters = new ArrayList<>();
		int goalieCount = 0;
		int skaterCount = 0;
		int i = 1;

		for (Player player : tradingTeam.getActivePlayers()) {
			if (player.getPosition() == "goalie") {
				goalieCount++;
			} else {
				skaterCount++;
			}
		}
		if (goalieCount < 2) {
			for (Player player : leagueLOM.getFreeAgentsGoalies()) {
				console.printLine(i + "\n Player Name : " + player.getPlayerName() + "\n Age : " + player.getAge() + "\n Checking : " + player.getChecking()
						+ "\n Saving : " + player.getSaving() + "\n Shooting : " + player.getShooting() + "\n Saving : " + player.getSaving() + "\n");
				i++;
			}
			while (goalieCount < numberOfGoalies) {
				try {
					console.printLine("Please choose a goalie (player name) from the given list.");
					playerName = console.readLine();
				} catch (Exception e) {
					System.out.println("Invalid value");
					break;
				}
				for (Player player : leagueLOM.getFreeAgentsGoalies()) {
					if (playerName.equals(player.getPlayerName())) {
						tradingTeam.addActivePlayer(player);
						leagueLOM.getFreeAgentsGoalies().remove(player);
						goalieCount++;
					}
				}
			}
		}
		if (skaterCount < 18) {
			i = 0;
			for (Player player : leagueLOM.getFreeAgentsSkaters()) {
				console.printLine(i + "\n Player Name : " + player.getPlayerName() + "\n Age : " + player.getAge() + "\n Checking : " + player.getChecking()
						+ "\n Saving : " + player.getSaving() + "\n Shooting : " + player.getShooting() + "\n Saving : " + player.getSaving() + "\n");
				i++;
			}
			while (skaterCount < numberOfSkaters) {
				try {
					console.printLine("Please choose a skater (player name) from the given list.");
					playerName = console.readLine();
				} catch (Exception e) {
					System.out.println("Invalid value");
					break;
				}
				for (Player player : leagueLOM.getFreeAgentsGoalies()) {
					if (playerName.equals(player.getPlayerName())) {
						tradingTeam.addActivePlayer(player);
						leagueLOM.getFreeAgentsSkaters().remove(player);
						skaterCount++;
					}
				}
			}
		}
		return tradingTeam;
	}

	@Override
	public LinkedList sortByPlayerStrength(LinkedList strength) {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();

		Collections.sort(strength, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o1)).getValue())
						.compareTo(((Map.Entry) (o2)).getValue());
			}
		});
		return strength;
	}

	@Override
	public HashMap<Player, Double> getWeakestPlayers(LinkedList orderedStrength) {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		HashMap<Player, Double> initializingWeakestPlayers = new LinkedHashMap<Player, Double>();
		int i = 0;

		for (Iterator it = orderedStrength.descendingIterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry) it.next();
			if (i < maxPlayersPerTrade) {
				initializingWeakestPlayers.put((Player) entry.getKey(), (Double) entry.getValue());
				i++;
			}
		}
		return initializingWeakestPlayers;
	}
}



