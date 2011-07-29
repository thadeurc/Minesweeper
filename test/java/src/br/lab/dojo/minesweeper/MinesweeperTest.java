package br.lab.dojo.minesweeper;

import junit.framework.Assert;

import org.junit.Test;


public class MinesweeperTest {

	@Test
	public void shouldNotExposeState(){
		char [][] matrix = new char[][]{{Minesweeper.BOMB}};
		Minesweeper mine = new Minesweeper(matrix);
		matrix[0][0] = Minesweeper.CLEAR;
		char [][] result = mine.process();
		Assert.assertEquals(result[0][0], Minesweeper.BOMB);
		Assert.assertNotSame(matrix, result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldRejectNotValidChar(){
		char [][] matrix = new char[][]{{','}};
		new Minesweeper(matrix);				
	}
	
	@Test
	public void shouldAcceptValidChar(){
		char [][] matrix = new char[][]{{Minesweeper.BOMB}, {Minesweeper.CLEAR}};
		new Minesweeper(matrix);
		Assert.assertTrue(true);
	}
	
	@Test
	public void testIsBomb(){
		char [][] matrix = new char[][]{{Minesweeper.BOMB}, {Minesweeper.CLEAR}};
		Minesweeper mine = new Minesweeper(matrix);
		Assert.assertTrue(mine.isBomb(0,0));
	}
	
	@Test
	public void testIsNotBomb(){
		char [][] matrix = new char[][]{{Minesweeper.BOMB}, {Minesweeper.CLEAR}};
		Minesweeper mine = new Minesweeper(matrix);
		Assert.assertFalse(mine.isBomb(1,0));
	}
	
	@Test
	public void isInvalidPosition() {
		char [][] matrix = new char[][]{
				{Minesweeper.BOMB, Minesweeper.CLEAR},
				{Minesweeper.BOMB, Minesweeper.CLEAR}
				};
		Minesweeper mine = new Minesweeper(matrix);
		
		Assert.assertTrue(mine.isValidPosition(0,1));
	}
}
