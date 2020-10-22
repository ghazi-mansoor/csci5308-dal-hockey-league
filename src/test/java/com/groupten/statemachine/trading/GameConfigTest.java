package com.groupten.statemachine.trading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.groupten.leagueobjectmodel.division.Division;
import com.groupten.leagueobjectmodel.team.Team;

public class GameConfigTest {

	@Test
    public void getAverageRetirementAgeTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getAverageRetirementAge(), 35);
    }
	
	@Test
	public void setAverageRetirementAgeTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setAverageRetirementAge(35);
		assertEquals(gameConfig.getAverageRetirementAge(), 35);	}
	
	@Test
	public void getMaximumAgeTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getMaximumAge(), 58);	}
	
	@Test
	public void setMaximumAgeTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setMaximumAge(58);
		assertEquals(gameConfig.getMaximumAge(), 58);	}	
	
	@Test
	public void getRandomWinChanceTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getRandomWinChance(), 0.2 , 0.0);	
	}
	
	@Test
	public void setRandomWinChanceTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setRandomWinChance(0.2);
		assertEquals(gameConfig.getRandomWinChance(), 0.2, 0.0);	
	}
	
	@Test
	public void getRandomInjuryChanceTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getRandomInjuryChance(), 0.05, 0.0);	
	}
	
	@Test
	public void setRandomInjuryChanceTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setRandomInjuryChance(0.05);
		assertEquals(gameConfig.getRandomInjuryChance(), 0.05, 0.0);
	}
	
	@Test
	public void getInjuryDaysLowTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getInjuryDaysLow(), 1);
	}
	
	@Test
	public void setInjuryDaysLowTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setInjuryDaysLow(1);
		assertEquals(gameConfig.getInjuryDaysLow(), 1);
	}
	
	@Test
	public void getInjuryDaysHighTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getInjuryDaysHigh(), 260);
	}
	
	@Test
	public void setInjuryDaysHighTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setInjuryDaysHigh(260);
		assertEquals(gameConfig.getInjuryDaysHigh(), 260);
	}
	
	@Test
	public void getDaysUntilStatIncreaseCheckTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getDaysUntilStatIncreaseCheck(), 100);
	}
	
	@Test
	public void setDaysUntilStatIncreaseCheckTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setDaysUntilStatIncreaseCheck(100);
		assertEquals(gameConfig.getDaysUntilStatIncreaseCheck(), 100);
	}
	
	@Test
	public void getLossPointTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getLossPoint(), 8);
	}
	
	@Test
	public void setLossPointTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setLossPoint(8);
		assertEquals(gameConfig.getLossPoint(), 8);
	}
	
	@Test
	public void getRandomTradeOfferChanceTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getRandomTradeOfferChance(), 0.05, 0.0);
	}
	
	@Test
	public void setRandomTradeOfferChanceTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setRandomTradeOfferChance(0.05);
		assertEquals(gameConfig.getRandomTradeOfferChance(), 0.05, 0.0);
	}
	
	@Test
	public void getMaxPlayersPerTradeTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getMaxPlayersPerTrade(), 2);
	}
	
	@Test
	public void setMaxPlayersPerTradeTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setMaxPlayersPerTrade(2);
		assertEquals(gameConfig.getMaxPlayersPerTrade(), 2);
	}
	
	@Test
	public void getRandomAcceptanceChanceTest() {
		GameConfig gameConfig = new GameConfig(35, 58, 0.2, 0.05, 1, 260, 100, 8, 0.05, 2, 0.05 );
		assertEquals(gameConfig.getRandomAcceptanceChance(), 0.05, 0.0);
	}
	
	@Test
	public void setRandomAcceptanceChanceTest() {
		GameConfig gameConfig = new GameConfig();
		gameConfig.setRandomAcceptanceChance(0.05);
		assertEquals(gameConfig.getRandomAcceptanceChance(), 0.05, 0.0);
	}
	
	
	
}
