package coreGame.tetrominos.base;

import java.awt.Color;
import java.util.Random;

import coreGame.tetrominos.*;
import coreGame.block.Block;

public abstract class Tetromino {
	protected Block[] blocks;
	
	protected int x;
	protected int y;
	protected TetrominoDirection currDir = TetrominoDirection.NORTH;
	protected TetrominoDirection prevDir = currDir;
	
	//constructor
	public Tetromino(int x, int y) {
		this.x = x;
		this.y = y;
		
		blocks = new Block[4];
		for (int i = 0; i < 4; i++) {
			blocks[i] = new Block();
		}
		
		updateBlocks();
	}
	
	//methods
	public Block[] getBlocks() {
		return blocks;
	}
	
	public void setColor(Color color) {		
		for (Block b : blocks) {
			b.setColor(color);
		}
	}
	
	/*public boolean canMove(int deltaX, int deltaY) {
		//TODO
		return true;
	}*/
	
	/*public boolean isColliding(Block otherBlock) {
		for (Block b : canvas) {
			return b.isColliding(otherBlock);
		}
		
		return false;
	}*/
	
	public final void rotate(boolean clockwise) {		
		int curr = currDir.ordinal();
		int next = clockwise ? (curr + 1) : (curr - 1);
		
		if (next < 0) {
			next = 3;
		}
		else if (next > 3) {
			next = 0;
		}
		
		prevDir = currDir;
		currDir = TetrominoDirection.values()[next];
		updateBlocks();
		
		//System.out.println("Tetromino rotated:\n" + toString());
	}	
	
	public final void move(int deltaX, int deltaY) {
		x += deltaX;
		y += deltaY;
		
		updateBlocks();
		
		//System.out.println("Tetromino moved:\n" + toString());
	}
	
	public final void down() {
		move(0, 1);
	}
	
	public final void left() {
		move(-1, 0);
	}
	
	public final void right() {
		move(1, 0);
	}
	
	public final void undoRotation() {
		currDir = prevDir;
		updateBlocks();
	}

	//abstract methods
	protected abstract void updateBlocks();
	
	//factory
	public final static Tetromino makeRandomTetro(int x, int y) {
		TetrominoType[] types = TetrominoType.values();
		
		Random r = new Random();
		int pick = r.nextInt(types.length);
		TetrominoType t = types[pick];
		
		Tetromino tetro = null;
		
		switch (t) {
		case I:
			tetro = new TetroI(x, y);
			break;
		case J:
			tetro = new TetroJ(x, y);
			break;
		case L:
			tetro = new TetroL(x, y);
			break;
		case O:
			tetro = new TetroO(x, y);
			break;
		case S:
			tetro = new TetroS(x, y);
			break;
		case T:
			tetro = new TetroT(x, y);
			break;
		case Z:
			tetro = new TetroZ(x, y);
			break;

		default:
			tetro = new TetroL(x, y);
			break;
		}
		
		System.out.println("Tetromino created:\n" + tetro.toString());
		
		return tetro;
	}
	
	public final static Tetromino makeRandomTetro() {
		return makeRandomTetro(0, 0);
	}
	
	//dump
	@Override
	public String toString() {
		String s = "";
		
		s += "\nDirection " + currDir + "\n";
		s += blocks[0].toString() + " | " 
				+ blocks[1].toString() + " | " 
				+ blocks[2].toString() + " | " 
				+ blocks[3].toString();
		s += "\n--------------------------";
		
		return s;
	}
}
