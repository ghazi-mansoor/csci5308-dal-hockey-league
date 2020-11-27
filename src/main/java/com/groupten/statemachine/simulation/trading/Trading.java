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
import java.util.stream.Collectors;

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
	private String generalManagerPersonality;
	private double generalManagerPersonalityValue = 0.0;
	private double randomTradeOfferChance = 0.0;
	private double randomAcceptanceChance = 0.0;
	private boolean trade = false;
	private String playerName;
	private double averageGoalieStrength = 0.0;
	private double averageDefenseStrength = 0.0;
	private double averageForwardStrength = 0.0;
	private final int teamSize = 20;
	private final int numberOfGoalies = 4;
	private final int numberOfForwards = 16;
	private final int numberOfDefenses = 10;
	ArrayList<Player> playersToTrade1 = new ArrayList<>();
	ArrayList<Player> playersToTrade2 = new ArrayList<>();
	HashMap<ArrayList<Player>,ArrayList<Player>> playersToTrade= new HashMap<>();

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

	public int getNumberOfForwards() {
		return numberOfForwards;
	}

	public int getNumberOfDefenses() {
		return numberOfDefenses;
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
	public void getInitialTeam() {
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
							getFinalTeam();
						}
					}
				}
			}
		}
	}

	@Override
	public String getWeakSection() {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		int numberOfGoalies = 0;
		int numberOfForwards = 0;
		int numberOfDefenses = 0;
		double goalieStrength = 0.0;
		double forwardStrength = 0.0;
		double defenseStrength = 0.0;
		double avgGoalieStrength = 0.0;
		double avgForwardStrength = 0.0;
		double avgDefenseStrength = 0.0;
		double goalieStrengthDifference = 0.0;
		double forwardStrengthDifference = 0.0;
		double defenseStrengthDifference = 0.0;
		String weakSection = null;

		for(Player player : tradeInitializingTeam.getPlayers()) {
			if(player.getPosition().equals("goalie")) {
				goalieStrength = goalieStrength + player.calculateStrength();
				numberOfGoalies = numberOfGoalies + 1;
			}
			else if(player.getPosition().equals("forward")) {
				forwardStrength = forwardStrength + player.calculateStrength();
				numberOfForwards = numberOfForwards + 1;
			}
			else if(player.getPosition().equals("defense")) {
				defenseStrength = defenseStrength + player.calculateStrength();
				numberOfDefenses = numberOfDefenses + 1;
			}
		}
		avgGoalieStrength = goalieStrength / numberOfGoalies;
		goalieStrengthDifference = avgGoalieStrength - averageGoalieStrength;
		avgForwardStrength = forwardStrength / numberOfForwards;
		forwardStrengthDifference = avgForwardStrength - averageForwardStrength;
		avgDefenseStrength = defenseStrength / numberOfDefenses;
		defenseStrengthDifference = avgDefenseStrength - averageDefenseStrength;
		if(goalieStrengthDifference < forwardStrengthDifference && goalieStrengthDifference < defenseStrengthDifference) {
			weakSection = "goalie";
		}
		else if(forwardStrengthDifference < goalieStrengthDifference && forwardStrengthDifference < defenseStrengthDifference) {
			weakSection = "forward";
		}
		else {
			weakSection = "defense";
		}
		return weakSection;
	}

	@Override
	public void getFinalTeam() {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		String weakSection = null;
		int numberOfPlayers = 0;
		double finalizingTeamStrength = 0.0;
		double playerStrength = 0.0;
		double averageStrength = 0.0;
		double prevAverageStrength = 0.0;

		for (Conference c : leagueLOM.getConferences().values()) {
			for (Division d : c.getDivisions().values()) {
				for (Team finalizingTeam : d.getTeams().values()) {
					if (finalizingTeam == tradeInitializingTeam) {
						return;
					} else {
						weakSection = getWeakSection();
						for(Player player : finalizingTeam.getPlayers()) {
							if(player.getPosition().equals(weakSection)) {
								playerStrength = playerStrength + player.calculateStrength();
								numberOfPlayers = numberOfPlayers + 1;
							}
						}
						averageStrength = playerStrength / numberOfPlayers;
						if(weakSection.equals("goalie")) {
							if(averageStrength > averageGoalieStrength && averageStrength > prevAverageStrength) {
								prevAverageStrength = averageStrength;
								tradeFinalizingTeam = finalizingTeam;
							}
						}
						else if(weakSection.equals("forward")) {
							if(averageStrength > averageForwardStrength && averageStrength > prevAverageStrength) {
								prevAverageStrength = averageStrength;
								tradeFinalizingTeam = finalizingTeam;
							}
						}
						else if(weakSection.equals("defense")) {
							if(averageStrength > averageDefenseStrength && averageStrength > prevAverageStrength) {
								prevAverageStrength = averageStrength;
								tradeFinalizingTeam = finalizingTeam;
							}
						}
					}
					averageStrength = 0.0;
					playerStrength = 0.0;
					numberOfPlayers = 0;
				}
			}
		}
	}

	@Override
	public double computePlayerTradeOffers(String weakSection) {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		double playerTradeStrength = 0.0;
		double initialTeamStrength = 0.0;
		double finalTeamStrength = 0.0;
		HashMap<Player,Double> initialTradingPlayers = new HashMap<>();
		HashMap<Player,Double> finalTradingPlayers = new HashMap<>();
		HashMap<Player,Player> tradingPlayers = new HashMap<>();
		HashMap<HashMap<Player,Player>,Double> tempInitialTeamStrength = new HashMap<>();
		HashMap<HashMap<Player,Player>,Double> tempFinalTeamStrength = new HashMap<>();

		for(Player player : tradeInitializingTeam.getPlayers()) {
			if(player.getPosition().equals(weakSection)) {
			}
			else {
				initialTradingPlayers.put(player,player.calculateStrength());
			}
		}
		for(Player player : tradeFinalizingTeam.getPlayers()) {
			if(player.getPosition().equals(weakSection)) {
				finalTradingPlayers.put(player,player.calculateStrength());
			}
		}
		for(Player player1 : initialTradingPlayers.keySet() ) {
			for(Player player2 : finalTradingPlayers.keySet()) {
				initialTeamStrength = tradeInitializingTeam.calculateTeamStrength();
				finalTeamStrength = tradeFinalizingTeam.calculateTeamStrength();
				tradeInitializingTeam.getPlayers().remove(player1);
				tradeInitializingTeam.getPlayers().add(player2);
				tradeFinalizingTeam.getPlayers().remove(player2);
				tradeFinalizingTeam.getPlayers().add(player1);

				if(tradeInitializingTeam.calculateTeamStrength() > initialTeamStrength &&
						tradeFinalizingTeam.calculateTeamStrength() > finalTeamStrength) {
					tradingPlayers.put(player1,player2);
					tempInitialTeamStrength.put(tradingPlayers,tradeInitializingTeam.calculateTeamStrength());
					tempFinalTeamStrength.put(tradingPlayers,tradeFinalizingTeam.calculateTeamStrength());
				}
				else {
					tradeInitializingTeam.getPlayers().remove(player2);
					tradeInitializingTeam.getPlayers().add(player1);
					tradeFinalizingTeam.getPlayers().remove(player1);
					tradeFinalizingTeam.getPlayers().add(player2);
				}
			}
		}
		Map<HashMap<Player,Player>,Double> initialSortedTeamStrength = tempInitialTeamStrength.entrySet() .stream()
				.sorted(Map.Entry.<HashMap<Player,Player>,Double>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		Map.Entry<HashMap<Player,Player>,Double> entry = initialSortedTeamStrength.entrySet().iterator().next();
		playerTradeStrength = entry.getValue();
		return playerTradeStrength;
		/*Map.Entry<HashMap<Player,Player>,Double> entry = initialSortedTeamStrength.entrySet().iterator().next();
		Map.Entry<Player,Player> playersEntry = entry.getKey().entrySet().iterator().next();
		playersToTrade1.add(playersEntry.getKey());
		playersToTrade2.add(playersEntry.getValue());*/
	}

	@Override
	public double computePlayersTradeOffers(String weakSection) {
		console = Injector.instance().getConsoleObject();
		leagueModel = Injector.instance().getLeagueModelObject();
		leagueLOM = leagueModel.getCurrentLeague();
		GameConfig.Trading tradingConfig = leagueLOM.getTradingConfig();
		maxPlayersPerTrade = tradingConfig.getMaxPlayersPerTrade();
		lossPoint = tradingConfig.getLossPoint();
		randomTradeOfferChance = tradingConfig.getRandomTradeOfferChance();
		randomAcceptanceChance = tradingConfig.getRandomAcceptanceChance();
		double playersTradeStrength = 0.0;
		int maximumPlayerPerTrade = 1;
		int playersChosen = 0;
		double initialTeamStrength = 0.0;
		double finalTeamStrength = 0.0;
		ArrayList<Player> initialTradingPlayers = new ArrayList<>();
		ArrayList<Player> finalTradingPlayers = new ArrayList<>();
		ArrayList<ArrayList<Player>> initialCombinationsOfPlayers = new ArrayList<>();
		/*ArrayList<ArrayList<Player>> initialCombinationsOfPlayers = new ArrayList<>();
		HashMap<ArrayList<Player>,Player> playersToTrade = new HashMap<>();*/
		//ArrayList<Player> finalPlayersToTrade = new ArrayList<>();

		for(Player player : tradeInitializingTeam.getPlayers()) {
			if(player.getPosition().equals(weakSection)) {
			}
			else {
				initialTradingPlayers.add(player);
			}
		}
		for(Player player : tradeFinalizingTeam.getPlayers()) {
			if(player.getPosition().equals(weakSection)) {
				finalTradingPlayers.add(player);
			}
		}


		if(maximumPlayerPerTrade < maxPlayersPerTrade) {
			for(int j = 1; j <= initialTradingPlayers.size() ; j++) {
				ArrayList<Player> playerCombination = new ArrayList<>();
					for(int k = j+1 ; k < initialTradingPlayers.size() && playersChosen < maximumPlayerPerTrade ; k++) {
						if(playerCombination.contains(initialTradingPlayers.get(j))) {

						}
						else {
							playerCombination.add(initialTradingPlayers.get(j));
							playersChosen++;
						}
						playerCombination.add(initialTradingPlayers.get(k));
						playersChosen++;
					}
					if(playerCombination.isEmpty()) {

					}
					else {
						initialCombinationsOfPlayers.add(playerCombination);
						playerCombination.clear();
					}
			}
			maximumPlayerPerTrade++;
		}

		ArrayList<Double> freeAgentGoalieStrength = new ArrayList<>();
		ArrayList<Double> freeAgentForwardStrength = new ArrayList<>();
		ArrayList<Double> freeAgentDefenseStrength = new ArrayList<>();

		for(Player player : leagueLOM.getFreeAgentsGoalies()) {
			freeAgentGoalieStrength.add(player.calculateStrength());
		}
		for(Player player : leagueLOM.getFreeAgentsSkaters()) {
			if(player.getPosition().equals("forward")) {
				freeAgentForwardStrength.add(player.calculateStrength());
			}
			else if (player.getPosition().equals("defense")) {
				freeAgentDefenseStrength.add(player.calculateStrength());
			}
		}
		Collections.sort(freeAgentGoalieStrength);
		Collections.reverse(freeAgentGoalieStrength);
		Collections.sort(freeAgentForwardStrength);
		Collections.reverse(freeAgentForwardStrength);
		Collections.sort(freeAgentDefenseStrength);
		Collections.reverse(freeAgentDefenseStrength);

		for(ArrayList<Player> initialPlayersToTrade : initialCombinationsOfPlayers ) {
			initialTeamStrength = tradeInitializingTeam.calculateTeamStrength();
			finalTeamStrength = tradeInitializingTeam.calculateTeamStrength();
			for(Player finalPlayerToTrade : finalTradingPlayers) {
				for(int i=0;i<initialPlayersToTrade.size();i++) {
					initialTeamStrength = initialTeamStrength - initialPlayersToTrade.get(i).calculateStrength();
					finalTeamStrength = finalTeamStrength + initialPlayersToTrade.get(i).calculateStrength();
				}
				initialTeamStrength = initialTeamStrength + finalPlayerToTrade.calculateStrength();
				finalTeamStrength = finalTeamStrength - finalPlayerToTrade.calculateStrength();
			}

		}



		return playersTradeStrength;
	}

	@Override
	public void generateTradeOffers() {

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

		for(Map.Entry<String, Double> personality : tradingConfig.getGmTable().entrySet())
		{
			if(tradeFinalizingTeam.getGeneralManager().getManagerPersonality().equals(personality.getKey())) {
				generalManagerPersonalityValue = personality.getValue();
			}
		}


		tradeInitializingTeam.calculateTeamStrength();
		tradeInitializingTeamStrength = tradeInitializingTeam.getTeamStrength();
		tradeFinalizingTeam.calculateTeamStrength();
		tradeFinalizingTeamStrength = tradeFinalizingTeam.getTeamStrength();
		for (Map.Entry<Player, Player> Players : tradingPlayers.entrySet()) {
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
		if (tradeInitializingTeamStrength < afterTradeInitializingStrength || tradeFinalizingTeamStrength < afterTradeFinalizingStrength) {
			if ((randomAcceptanceChanceGenerated + generalManagerPersonalityValue ) > randomAcceptanceChance) {
				console.printLine("Trade declined since its not beneficial.");
				tradeInitializingTeam.setLossPoint(0);
				tradeInitializingTeam.getPlayers().remove(player2);
				tradeInitializingTeam.getPlayers().add(player1);
				tradeFinalizingTeam.getPlayers().remove(player1);
				tradeFinalizingTeam.getPlayers().add(player2);
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

		initializingTeamSize = tradeInitializingTeam.getPlayers().size();
		finalizingTeamSize = tradeFinalizingTeam.getPlayers().size();

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
		int forwardCount = 0;
		int defenseCount = 0;

		HashMap<Player, Double> teamPlayerStrength = new HashMap<Player, Double>();
		for (Player players : tradingTeam.getPlayers()) {
			double playerStrength = players.calculateStrength();
			teamPlayerStrength.put(players, playerStrength);
		}

		Map<Player,Double> sortedTeamPlayerStrength = teamPlayerStrength.entrySet() .stream()
				.sorted(Map.Entry.<Player,Double>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


		for (Map.Entry<Player, Double> entry : sortedTeamPlayerStrength.entrySet()) {
			if (entry.getKey().getPosition() == "goalie") {
				goalieCount++;

				if (goalieCount > numberOfGoalies) {
					tradingTeam.getPlayers().remove(entry.getKey());
					leagueLOM.addFreeAgent(entry.getKey());
				}
			} else if (entry.getKey().getPosition() == "forward"){
				forwardCount++;

				if (forwardCount > numberOfForwards) {
					tradingTeam.getPlayers().remove(entry.getKey());
					leagueLOM.addFreeAgent(entry.getKey());
				}
			} else if (entry.getKey().getPosition() == "defense") {
				defenseCount++;

				if (defenseCount > numberOfDefenses) {
					tradingTeam.getPlayers().remove(entry.getKey());
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
		int forwardCount = 0;
		int defenseCount = 0;

		for (Player player : tradingTeam.getPlayers()) {
			if (player.getPosition() == "goalie") {
				goalieCount++;
			} else if(player.getPosition() == "forward"){
				forwardCount++;
			} else if(player.getPosition() == "defense"){
				defenseCount++;
			}
		}
		for (Player player : tradingTeam.getPlayers()) {
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
			for (Player player : tradingTeam.getPlayers()) {
				if (playerName.equals(player.getPlayerName())) {
					goalieCount--;
					leagueLOM.addFreeAgent(player);
				}
				else {
					updatedPlayersList.add(player);
				}
			}
			tradingTeam.setPlayers(updatedPlayersList);
		}
		for (Player player : tradingTeam.getPlayers()) {
			i = 1;
			if (player.getPosition().equals("forward")) {
				console.printLine(i + "\n Player Name : " + player.getPlayerName() + "\n Age : " + player.getAge() + "\n Checking : " + player.getChecking()
						+ "\n Saving : " + player.getSaving() + "\n Shooting : " + player.getShooting() + "\n Saving : " + player.getSaving() + "\n");
				i++;
			}
		}
		while (forwardCount > numberOfForwards) {
			List<Player> updatedPlayersList = new ArrayList<Player>();
			try {
				console.printLine("Please choose a forward (player name) to drop : \n");
				playerName = console.readLine();
			} catch (Exception e) {
				System.out.println("Invalid value!");
				break;
			}
			for (Player player : tradingTeam.getPlayers()) {
				if (playerName.equals(player.getPlayerName())) {
					forwardCount--;
					leagueLOM.addFreeAgent(player);
				}
				else {
					updatedPlayersList.add(player);
				}
			}
			tradingTeam.setPlayers(updatedPlayersList);
		}
		for (Player player : tradingTeam.getPlayers()) {
			i = 1;
			if (player.getPosition().equals("defense")) {
				console.printLine(i + "\n Player Name : " + player.getPlayerName() + "\n Age : " + player.getAge() + "\n Checking : " + player.getChecking()
						+ "\n Saving : " + player.getSaving() + "\n Shooting : " + player.getShooting() + "\n Saving : " + player.getSaving() + "\n");
				i++;
			}
		}
		while (defenseCount > numberOfDefenses) {
			List<Player> updatedPlayersList = new ArrayList<Player>();
			try {
				console.printLine("Please choose a defensemen (player name) to drop : \n");
				playerName = console.readLine();
			} catch (Exception e) {
				System.out.println("Invalid value!");
				break;
			}
			for (Player player : tradingTeam.getPlayers()) {
				if (playerName.equals(player.getPlayerName())) {
					defenseCount--;
					leagueLOM.addFreeAgent(player);
				}
				else {
					updatedPlayersList.add(player);
				}
			}
			tradingTeam.setPlayers(updatedPlayersList);
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
		HashMap<Player, Double> goaliePlayerStrength = new HashMap<Player, Double>();
		HashMap<Player, Double> forwardPlayerStrength = new HashMap<Player, Double>();
		HashMap<Player, Double> defensePlayerStrength = new HashMap<Player, Double>();
		int goalieCount = 0;
		int forwardCount = 0;
		int defenseCount = 0;

		for (Player player : tradingTeam.getPlayers()) {
			if (player.getPosition() == "goalie") {
				goalieCount++;
			} else if (player.getPosition() == "forward"){
				forwardCount++;
			} else if (player.getPosition() == "defense"){
				defenseCount++;
			}
		}
		for (Player players : leagueLOM.getFreeAgentsGoalies()) {
			double playerStrength = players.calculateStrength();
			goaliePlayerStrength.put(players, playerStrength);
		}
		Map<Player,Double> sortedGoaliePlayerStrength = goaliePlayerStrength.entrySet() .stream()
				.sorted(Map.Entry.<Player,Double>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		for (Map.Entry<Player, Double> entry : sortedGoaliePlayerStrength.entrySet()) {
			if (goalieCount < numberOfGoalies) {
				if (entry.getKey().getPosition().equals("goalie")) {
					tradingTeam.getPlayers().add(entry.getKey());
					leagueLOM.getFreeAgentsGoalies().remove(entry.getKey());
					goalieCount++;
				}
			}
		}
		for (Player players : leagueLOM.getFreeAgentsSkaters()) {
			if(players.getPosition().equals("forward")) {
				forwardPlayerStrength.put(players,players.calculateStrength());
			}
			else if (players.getPosition().equals("defense")) {
				defensePlayerStrength.put(players,players.calculateStrength());
			}
		}
		Map<Player,Double> sortedForwardPlayerStrength = forwardPlayerStrength.entrySet() .stream()
				.sorted(Map.Entry.<Player,Double>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		for (Map.Entry<Player, Double> entry : sortedForwardPlayerStrength.entrySet()) {
			if (forwardCount < numberOfForwards) {
				if (entry.getKey().getPosition().equals("forward") || entry.getKey().getPosition().equals("defense")) {
					tradingTeam.getPlayers().add(entry.getKey());
					leagueLOM.getFreeAgentsSkaters().remove(entry.getKey());
					forwardCount++;
				}
			}
		}
		Map<Player,Double> sortedDefensePlayerStrength = defensePlayerStrength.entrySet() .stream()
				.sorted(Map.Entry.<Player,Double>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		for (Map.Entry<Player, Double> entry : sortedDefensePlayerStrength.entrySet()) {
			if (defenseCount < numberOfDefenses) {
				if (entry.getKey().getPosition().equals("forward") || entry.getKey().getPosition().equals("defense")) {
					tradingTeam.getPlayers().add(entry.getKey());
					leagueLOM.getFreeAgentsSkaters().remove(entry.getKey());
					defenseCount++;
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
		int forwardCount = 0;
		int defenseCount = 0;
		int i = 1;

		for (Player player : tradingTeam.getPlayers()) {
			if (player.getPosition() == "goalie") {
				goalieCount++;
			} else if(player.getPosition() == "forward"){
				forwardCount++;
			} else if(player.getPosition() == "defense"){
				defenseCount++;
			}
		}
		if (goalieCount < numberOfGoalies) {
			List<Player> updatedPlayersList = new ArrayList<Player>();
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
						tradingTeam.addPlayer(player);
						goalieCount++;
					}
					else {
						updatedPlayersList.add(player);
					}
				}
			}
			for(Player player : updatedPlayersList) {
				leagueLOM.addFreeAgent(player);
			}
		}
		if (defenseCount < numberOfDefenses) {
			i = 1;
			List<Player> updatedPlayersList = new ArrayList<Player>();
			for (Player player : leagueLOM.getFreeAgentsSkaters()) {
				if(player.getPosition().equals("defense")){
					console.printLine(i + "\n Player Name : " + player.getPlayerName() + "\n Age : " + player.getAge() + "\n Checking : " + player.getChecking()
							+ "\n Saving : " + player.getSaving() + "\n Shooting : " + player.getShooting() + "\n Saving : " + player.getSaving() + "\n");
					i++;
				}
			}
			while (defenseCount < numberOfDefenses) {
				try {
					console.printLine("Please choose a defensemen (player name) from the given list.");
					playerName = console.readLine();
				} catch (Exception e) {
					System.out.println("Invalid value");
					break;
				}
				for (Player player : leagueLOM.getFreeAgentsSkaters()) {
					if (playerName.equals(player.getPlayerName())) {
						tradingTeam.addPlayer(player);
						defenseCount++;
					} else {
						updatedPlayersList.add(player);
					}
				}
			}
			for(Player player : updatedPlayersList) {
				leagueLOM.addFreeAgent(player);
			}
		}
		if (forwardCount < numberOfForwards) {
			i = 1;
			List<Player> updatedPlayersList = new ArrayList<Player>();
			for (Player player : leagueLOM.getFreeAgentsSkaters()) {
				if(player.getPosition().equals("forward")){
					console.printLine(i + "\n Player Name : " + player.getPlayerName() + "\n Age : " + player.getAge() + "\n Checking : " + player.getChecking()
							+ "\n Saving : " + player.getSaving() + "\n Shooting : " + player.getShooting() + "\n Saving : " + player.getSaving() + "\n");
					i++;
				}
			}
			while (forwardCount < numberOfForwards) {
				try {
					console.printLine("Please choose a forward (player name) from the given list.");
					playerName = console.readLine();
				} catch (Exception e) {
					System.out.println("Invalid value");
					break;
				}
				for (Player player : leagueLOM.getFreeAgentsSkaters()) {
					if (playerName.equals(player.getPlayerName())) {
						tradingTeam.addPlayer(player);
						forwardCount++;
					} else {
						updatedPlayersList.add(player);
					}
				}
			}
			for(Player player : updatedPlayersList) {
				leagueLOM.addFreeAgent(player);
			}
		}
		return tradingTeam;
	}

	@Override
	public LinkedList sortByStrength(LinkedList strength) {
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
	public void getAveragePlayerStrength(){
		int numberOfGoalies = 0;
		int numberOfForwards = 0;
		int numberOfDefenses = 0;
		double goalieStrength = 0.0;
		double defenseStrength = 0.0;
		double forwardStrength = 0.0;

		for(Conference conference : leagueLOM.getConferences().values()){
			for(Division division : conference.getDivisions().values()){
				for(Team team : division.getTeams().values()){
					for(Player player : team.getPlayers()){
						if(player.getPosition().equals("goalie"))
						{
							goalieStrength = goalieStrength + player.calculateStrength();
							numberOfGoalies = numberOfGoalies + 1;
						}
						else if(player.getPosition().equals("defense"))
						{
							defenseStrength = defenseStrength + player.calculateStrength();
							numberOfDefenses = numberOfDefenses + 1;
						}
						else if(player.getPosition().equals("forward"))
						{
							forwardStrength = forwardStrength + player.calculateStrength();
							numberOfForwards = numberOfForwards + 1;
						}
					}
				}
			}
		}
		averageGoalieStrength = goalieStrength / numberOfGoalies;
		averageForwardStrength = forwardStrength / numberOfForwards;
		averageDefenseStrength = defenseStrength / numberOfDefenses;

		getInitialTeam();
	}


}



