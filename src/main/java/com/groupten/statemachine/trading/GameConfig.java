package com.groupten.statemachine.trading;

public class GameConfig implements IGameConfig {

	private int averageRetirementAge;
	private int maximumAge;
	private double randomWinChance;
	private double randomInjuryChance;
	private int injuryDaysLow;
	private int injuryDaysHigh;
	private int daysUntilStatIncreaseCheck;
	private int lossPoint;
	private double randomTradeOfferChance;
	private int maxPlayersPerTrade;
	private double randomAcceptanceChance;
	
	
	
	public GameConfig() {
		super();
	}


	public GameConfig(int averageRetirementAge, int maximumAge, double randomWinChance, double randomInjuryChance,
			int injuryDaysLow, int injuryDaysHigh, int daysUntilStatIncreaseCheck, int lossPoint,
			double randomTradeOfferChance, int maxPlayersPerTrade, double randomAcceptanceChance) {
		super();
		this.averageRetirementAge = averageRetirementAge;
		this.maximumAge = maximumAge;
		this.randomWinChance = randomWinChance;
		this.randomInjuryChance = randomInjuryChance;
		this.injuryDaysLow = injuryDaysLow;
		this.injuryDaysHigh = injuryDaysHigh;
		this.daysUntilStatIncreaseCheck = daysUntilStatIncreaseCheck;
		this.lossPoint = lossPoint;
		this.randomTradeOfferChance = randomTradeOfferChance;
		this.maxPlayersPerTrade = maxPlayersPerTrade;
		this.randomAcceptanceChance = randomAcceptanceChance;
	}
	
	
	public int getAverageRetirementAge() {
		return averageRetirementAge;
	}
	public void setAverageRetirementAge(int averageRetirementAge) {
		this.averageRetirementAge = averageRetirementAge;
	}
	public int getMaximumAge() {
		return maximumAge;
	}
	public void setMaximumAge(int maximumAge) {
		this.maximumAge = maximumAge;
	}
	public double getRandomWinChance() {
		return randomWinChance;
	}
	public void setRandomWinChance(double randomWinChance) {
		this.randomWinChance = randomWinChance;
	}
	public double getRandomInjuryChance() {
		return randomInjuryChance;
	}
	public void setRandomInjuryChance(double randomInjuryChance) {
		this.randomInjuryChance = randomInjuryChance;
	}
	public int getInjuryDaysLow() {
		return injuryDaysLow;
	}
	public void setInjuryDaysLow(int injuryDaysLow) {
		this.injuryDaysLow = injuryDaysLow;
	}
	public int getInjuryDaysHigh() {
		return injuryDaysHigh;
	}
	public void setInjuryDaysHigh(int injuryDaysHigh) {
		this.injuryDaysHigh = injuryDaysHigh;
	}
	public int getDaysUntilStatIncreaseCheck() {
		return daysUntilStatIncreaseCheck;
	}
	public void setDaysUntilStatIncreaseCheck(int daysUntilStatIncreaseCheck) {
		this.daysUntilStatIncreaseCheck = daysUntilStatIncreaseCheck;
	}
	public int getLossPoint() {
		return lossPoint;
	}
	public void setLossPoint(int lossPoint) {
		this.lossPoint = lossPoint;
	}
	public double getRandomTradeOfferChance() {
		return randomTradeOfferChance;
	}
	public void setRandomTradeOfferChance(double randomTradeOfferChance) {
		this.randomTradeOfferChance = randomTradeOfferChance;
	}
	public int getMaxPlayersPerTrade() {
		return maxPlayersPerTrade;
	}
	public void setMaxPlayersPerTrade(int maxPlayersPerTrade) {
		this.maxPlayersPerTrade = maxPlayersPerTrade;
	}
	public double getRandomAcceptanceChance() {
		return randomAcceptanceChance;
	}
	public void setRandomAcceptanceChance(double randomAcceptanceChance) {
		this.randomAcceptanceChance = randomAcceptanceChance;
	}
	
	
	
	
	
}
