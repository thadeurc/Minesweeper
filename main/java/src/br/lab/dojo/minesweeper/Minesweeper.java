package br.lab.dojo.minesweeper;


public class Minesweeper {

	public static final char BOMB = '*';
	public static final char CLEAR = '.';	
	private char[][] matrix;

	public Minesweeper(char[][] matrix) {
		this.matrix = matrix; 
	}
}
