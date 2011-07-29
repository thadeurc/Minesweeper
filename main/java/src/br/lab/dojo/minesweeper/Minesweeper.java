package br.lab.dojo.minesweeper;

import java.util.Arrays;

public class Minesweeper {

	public static final char BOMB = '*';
	public static final char CLEAR = '.';
	private char[][] matrix;

	public void validateArray(char[] array) {
		for (int i = 0; i < array.length; i++) {			
			if (array[i] != BOMB && array[i] != CLEAR) {
				throw new IllegalArgumentException();
			}
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

	public boolean isBomb(int coll, int row) {
		return this.matrix[coll][row] == BOMB;
	}

	public char[][] process() {
		return this.matrix;
	}

	public boolean isValidPosition(int row, int col) {
		if (col < 0 && row < 0) {
			return false;
		} 
		if (col >= matrix.length && row >= matrix[0].length) {
			return false;
		}
		return true;
	}
}
