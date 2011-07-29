package br.lab.dojo.minesweeper;

import java.util.Arrays;

public class Minesweeper {

	public static final char BOMB = '*';
	public static final char CLEAR = '.';
	private char[][] matrix;
	private int [][] delta = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {1, 1}, {-1, 1}, {-1, -1}};
	
	
	protected void validateArray(char[] array) {
		for (int i = 0; i < array.length; i++) {			
			if (array[i] != BOMB && array[i] != CLEAR) {
				throw new IllegalArgumentException();
			}
			if(array[i] == CLEAR) array[i] = '0';
		}
	}

	public Minesweeper(char[][] matrix) {
		char[][] newMatrix = new char[matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			char[] c = matrix[i];
			validateArray(c);
			newMatrix[i] = Arrays.copyOf(c, c.length);
			
		}
		this.matrix = newMatrix;
	}

	public boolean isBomb(int row, int col) {
		return this.matrix[row][col] == BOMB;
	}

	public char[][] process() {
		/* algorithm is O(lines * column) - delta has constant length */
		for(int i = 0; i < matrix.length; i++){
			for(int j = 0; j < matrix[i].length; j++){
				if(!isBomb(i, j)){
					for(int k = 0; k < delta.length; k++){
						int row = i + delta[k][0];
						int col = j + delta[k][1];
						if(isValidPosition(row, col)){
							if(isBomb(row, col)){
								matrix[i][j]++;
							}
						}
					}
				}
			}
		}
		return this.matrix;
	}

	public boolean isValidPosition(int row, int col) {
		return row >= 0 && col >= 0 && row < matrix.length && col < matrix[row].length; 
	}
}
