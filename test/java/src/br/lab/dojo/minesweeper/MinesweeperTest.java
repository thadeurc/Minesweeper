package br.lab.dojo.minesweeper;

import java.util.Arrays;

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
	public void shouldIdentifyBomb(){
		char [][] matrix = new char[][]{{Minesweeper.BOMB}, {Minesweeper.CLEAR}};
		Minesweeper mine = new Minesweeper(matrix);
		Assert.assertTrue(mine.isBomb(0,0));
	}
	
	@Test
	public void shouldIdentifyNotBomb(){
		char [][] matrix = new char[][]{{Minesweeper.BOMB}, {Minesweeper.CLEAR}};
		Minesweeper mine = new Minesweeper(matrix);
		Assert.assertFalse(mine.isBomb(1,0));
	}
	
	@Test
	public void shouldHaveIdentifyAnInvalidPosition() {
		char [][] matrix = new char[][]{
				{Minesweeper.BOMB, Minesweeper.CLEAR},
				{Minesweeper.BOMB, Minesweeper.CLEAR}
				};
		Minesweeper mine = new Minesweeper(matrix);		
		Assert.assertTrue(mine.isValidPosition(0,1));
	}
	
	@Test
	public void shouldReturnAnMatrixWithZerosIfThereAreNoBombs(){
		char [][] matrix = new char[2][2];
		for(char [] c : matrix){
			Arrays.fill(c, Minesweeper.CLEAR);
		}		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		for(char [] c : result){
			for(char v : c){
				Assert.assertTrue(v == '0');
			}
		}
	}
	
	@Test
	public void shouldIncrementAClearPositionIfHasBombAtRight(){
		// case P*
		char [][] matrix =  new char[][]{{Minesweeper.CLEAR, Minesweeper.BOMB}};		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('1', result[0][0]);
	}
	
	@Test
	public void shouldIncrementAClearPositionIfHasBombAtLeft(){
		// case *P
		char [][] matrix =  new char[][]{{Minesweeper.BOMB, Minesweeper.CLEAR}};		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('1', result[0][1]);
	}
	
	@Test
	public void shouldIncrementAClearPositionIfHasBombAbove(){
		// case *
		//      P
		char [][] matrix =  new char[][]{{Minesweeper.BOMB}, {Minesweeper.CLEAR}};		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('1', result[1][0]);
	}
	
	@Test
	public void shouldIncrementAClearPositionIfHasBombBelow(){
		// case P
		//      *
		char [][] matrix =  new char[][]{{Minesweeper.CLEAR}, {Minesweeper.BOMB}};		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('1', result[0][0]);
	}

	@Test
	public void shouldIncrementAClearPositionIfHasBombBelowLeft(){
		// case .P
		//      *.
		char[][] matrix = new char[][] {
				{ Minesweeper.CLEAR, Minesweeper.CLEAR },
				{ Minesweeper.BOMB, Minesweeper.CLEAR } };		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('1', result[0][1]);
	}
	
	@Test
	public void shouldIncrementAClearPositionIfHasBombBelowRight(){
		// case P.
		//      .*
		char[][] matrix = new char[][] {
				{ Minesweeper.CLEAR, Minesweeper.CLEAR },
				{ Minesweeper.CLEAR, Minesweeper.BOMB } };		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('1', result[0][0]);
	}

	@Test
	public void shouldIncrementAClearPositionIfHasBombAboveLeft(){
		// case *.
		//      .P
		char[][] matrix = new char[][] {
				{ Minesweeper.BOMB, Minesweeper.CLEAR },
				{ Minesweeper.CLEAR, Minesweeper.CLEAR } };		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('1', result[1][1]);
	}
	
	@Test
	public void shouldIncrementAClearPositionIfHasBombAboveRight(){
		// case .*
		//      P.
		char[][] matrix = new char[][] {
				{ Minesweeper.CLEAR, Minesweeper.BOMB },
				{ Minesweeper.CLEAR, Minesweeper.CLEAR } };		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('1', result[1][0]);
	}
	
	@Test
	public void shouldIncrementAClearPositionIfHasBombAboveAndBelow(){
		// case .*
		//      .P
		//		.*
		char[][] matrix = new char[][] {
				{ Minesweeper.CLEAR, Minesweeper.BOMB },
				{ Minesweeper.CLEAR, Minesweeper.CLEAR },
				{ Minesweeper.CLEAR, Minesweeper.BOMB } };		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('2', result[1][1]);
	}
	
	@Test
	public void shouldIncrementAClearPositionIfHasBombLeftAndRight(){
		// case ...
		//      *P*
		//		...
		char[][] matrix = new char[][] {
				{ Minesweeper.CLEAR, Minesweeper.CLEAR, Minesweeper.CLEAR },
				{ Minesweeper.BOMB, Minesweeper.CLEAR, Minesweeper.BOMB},
				{ Minesweeper.CLEAR, Minesweeper.CLEAR, Minesweeper.CLEAR }};		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('2', result[1][1]);
	}
	
	@Test
	public void shouldIncrementAClearPositionIfHasBombLeftBelowAndRightAbove(){
		// case ..*
		//      .P.
		//		*..
		char[][] matrix = new char[][] {
				{ Minesweeper.CLEAR, Minesweeper.CLEAR, Minesweeper.BOMB },
				{ Minesweeper.CLEAR, Minesweeper.CLEAR, Minesweeper.CLEAR},
				{ Minesweeper.BOMB, Minesweeper.CLEAR, Minesweeper.CLEAR }};		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('2', result[1][1]);
	}
	
	@Test
	public void shouldIncrementAClearPositionIfHasBombLeftAboveAndRightBelow(){
		// case *..
		//      .P.
		//		..*
		char[][] matrix = new char[][] {
				{ Minesweeper.BOMB, Minesweeper.CLEAR, Minesweeper.CLEAR },
				{ Minesweeper.CLEAR, Minesweeper.CLEAR, Minesweeper.CLEAR},
				{ Minesweeper.CLEAR, Minesweeper.CLEAR, Minesweeper.BOMB }};		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('2', result[1][1]);
	}
	
	
	@Test
	public void shouldIncrementAClearPositionIfIsSurrendedByBombs(){
		// case ***
		//      *P*
		//		***
		char[][] matrix = new char[][] {
				{ Minesweeper.BOMB, Minesweeper.BOMB, Minesweeper.BOMB },
				{ Minesweeper.BOMB, Minesweeper.CLEAR, Minesweeper.BOMB},
				{ Minesweeper.BOMB, Minesweeper.BOMB, Minesweeper.BOMB }};		
		Minesweeper mine = new Minesweeper(matrix);
		char [][] result = mine.process();
		Assert.assertEquals('8', result[1][1]);
	}
}
