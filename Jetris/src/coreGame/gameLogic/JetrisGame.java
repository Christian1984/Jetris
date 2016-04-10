package coreGame.gameLogic;

import coreGame.gameLogic.logicControllers.AutomaticTickController;
import coreGame.tetrominos.base.Tetromino;
import coreGame.block.Block;
import singleplayer.view.JetrisView;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class JetrisGame {
    private final int width;
    private final int height;

    private boolean gameStarted = false;
    private boolean gameOver = false;
	
	private Tetromino currTetro = null;

    //private Block[][] blocks;
    private Color[][] blocks;

	
	private int level = 1;	
	private int score = 0;
	private int linesCleared = 0;

	static final int SCOREDELTAROW = 100;
	static final int SCORETETROLANDED = 10;
	static final int LINESTOLEVELUP = 10;
	static final int INITIALTICKDELAY = 1000;
	static final double LEVELUPTIMESCALE = 0.75;
	static final boolean KNOWSSUPERSPIN = true;

    private LinkedList<JetrisView> views;

    private AutomaticTickController ticker;

    //constructors
    public JetrisGame(int width, int height) {
        this.width = width;
        this.height = height;

        views = new LinkedList<JetrisView>();

        //blocks = new Block[width][height];
        blocks = new Color[width][height];
    }

    public JetrisGame() {
        this(10, 20);
    }
	
	//getters
    //not synchronized - final
    public int getWidth() {
        return width;
	}
	
	public int getHeight() {
		return height;
	}

    //synchronized?
    public Block[] getCurrTetroBlocks() {
        return currTetro.getBlocks();
	}

    //public Block[][] getLandedBlocks() {
    public Color[][] getLandedBlocks() {
        return blocks;
    }
	
	public boolean isRunning() {
		return gameStarted;
	}

	/*public boolean isGameOver() {
        return gameOver;
	}*/

    public int getLevel() {
		return level;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLinesCleared() {
		return linesCleared;
	}
	
	//private methods
	private void spawn() {
		currTetro = Tetromino.makeRandomTetro(width / 2 - 1, 0);
		fireModelChanged();
	}
	
	private void moveTetro(int deltaX, int deltaY) {
		if (currTetro != null) {
			boolean canMove = false;
			
			if (deltaX != 0 && canTetroShift(deltaX)) {
				canMove = true;
			}
			
			if (deltaY != 0) {
				if (canTetroSink(deltaY)) {
					canMove = true;
				}
				else {
					lockTetro();
					
					if (!gameOver) {
						clearFullRows();
						spawn();
					}
				}
			}
			
			if (canMove) {
				currTetro.move(deltaX, deltaY);
			}
		}
		
		fireModelChanged();
	}
	
	private boolean isTetroColliding() {
		for (Block b : currTetro.getBlocks()) {
			if (b.getX() < 0 || b.getX() >= width
					|| b.getY() >= height) {
				return true;
			}
			
			if (b.inCanvas(width, height)) {
				if (blocks[b.getX()][b.getY()] != null) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean canTetroShift(int deltaX) {
		for (Block b : currTetro.getBlocks()) {
			if (b.getX() + deltaX < 0 || b.getX() + deltaX >= width) {
				return false;
			}
			
			if (b.getY() > 0) {
				if (blocks[b.getX() + deltaX][b.getY()] != null) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean canTetroSink(int deltaY) {
		for (Block b : currTetro.getBlocks()) {
			if (b.getY() + deltaY >= height) {
				return false;
			}
			
			if (blocks[b.getX()][b.getY() + deltaY] != null) {
				return false;
			}
		}
		
		return true;
	}
	
	private void lockTetro() {
		//Check for gameOver
        //and lock each block
        for (Block b : currTetro.getBlocks()) {
            if (b.getY() < 0) {
				gameOver();
				return;
			}

            //blocks[b.getX()][b.getY()] = b;
            blocks[b.getX()][b.getY()] = b.getColor();
        }

		score += SCORETETROLANDED;
		System.out.println("Tetro locked!");
	}

    protected int clearFullRows() {
        System.out.println("Clearing lines!");

        int rowCount = 0;
		
		for (int row = height - 1; row >= 0; row--) {
			boolean canClear = true;
			
			for (int col = 0; col < width; col++) {
				if (blocks[col][row] == null) {
					canClear = false;
					//System.out.println("Row " + row + " is not full: No Block found at (" + col + ", " + row + ")! Going to next row!");
					break;
				}
			}
			
			if (canClear) {
		        //clear row
                clearRow(row);
                
                rowCount++;
				row++; //check same row again
			}
		}
        
        //update score
        score += SCOREDELTAROW * rowCount * rowCount;
		
		System.out.println(this);

        return rowCount;
    }

    private void clearRow(int rowIndex) {
        //update stats and level up
        linesCleared++;
        
		if (linesCleared % LINESTOLEVELUP == 0) {
			levelUp();
		}
		
		//clear
		for (int row = rowIndex; row >= 0; row--) {
			boolean allClear = true;
			
			for (int col = 0; col < width; col++) {
				//System.out.println("Block [" + col + ", " + (row - 1) + "] (is " + blocks[col][row - 1] + ") copied to [" + col + ", " + row + "] (was " + blocks[col][row] + ")!");
				
				//update position in array
				if (row > 0) {
					blocks[col][row] = blocks[col][row - 1];
				}
				else {
					blocks[col][row] = null;
				}
				
				//update position stored in block
                /*if (blocks[col][row] != null) {
                    blocks[col][row].setPos(col, row);
				}*/

                if (blocks[col][row] != null) {
					allClear = false;
				}
			}
			
			if (allClear) {
				//System.out.println("Row " + row + " is all clear!");
				return;
			}
		}
	}

    protected void shiftUp(int rowsToMove) {
        //shift tetromino
        moveTetro(-rowsToMove, 0);

        //shift landed blocks
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row < height - rowsToMove) {
                    //move blocks up
                    blocks[col][row] = blocks[col][row + rowsToMove];
                } else {
                    //make sure that blocks below are deleted
                    blocks[col][row] = null;
                }
            }
        }
    }

    protected void fillRows(int rows) {
        //create a gap for all rows to come
        int gap = new Random().nextInt(getWidth());

        //fill all new blocks with same color
        Color color = Color.lightGray;

        for (int row = height - 1; row > height - (rows + 1); row--) {
            for (int col = 0; col < width; col++) {
                if (col != gap) {
                    blocks[col][row] = color;
                }
            }
        }
    }

    private synchronized void gameOver() {
        gameOver = true;
		gameStarted = false;
		currTetro = null;
		
		ticker.stopTicker();
		
		fireModelChanged();
	}
	
	private void levelUp() {
		level++;
		
		if (ticker != null) {
			ticker.levelUp();
		}
	}
	
	private void initTicker() {
		if (ticker != null) {
			ticker.stopTicker();
		}
			
		ticker = new AutomaticTickController(this, INITIALTICKDELAY, LEVELUPTIMESCALE);
	}
	
	//public methods
    public synchronized void start() {
        //blocks = new Block[width][height];
        blocks = new Color[width][height];

        gameOver = false;
        gameStarted = true;
		
		score = 0;
		linesCleared = 0;
		level = 1;

		spawn();
		
		initTicker();
	}

    public void shutdown() {
        if (ticker != null) {
            ticker.stopTicker();
		}
	}

    public synchronized void tick() {
        ticker.resetTimer();
        moveTetro(0, 1);
		//System.out.println("tick!");
	}

    public synchronized void rotate(boolean clockwise) {
        if (currTetro != null) {
            currTetro.rotate(clockwise);
			
			if (isTetroColliding()) {
				currTetro.undoRotation();
            } else if (KNOWSSUPERSPIN) {
                ticker.resetTimer();
            }
		}
		
		fireModelChanged();
	}

    public synchronized void left() {
        moveTetro(-1, 0);
    }

    public synchronized void right() {
        moveTetro(1, 0);
    }
	
	@Override
    public synchronized String toString() {
        String s = "===============================\n";

        for (int row = 0; row < height; row++) {
			if (row < 10) {
				s += " ";
			}
			
			s += row + "|"; 
			
			for (int col = 0; col < width; col++) {
				//draw landed blocks
				if (blocks[col][row] != null) {
					s += "X";
				}
				else {
					boolean xDrawed = false;
					
					//draw blocks of current tetro
					if (currTetro != null) {
						for (Block b : currTetro.getBlocks()) {
							if (col == b.getX() && row == b.getY()) {
								xDrawed = true;
								s += "X";
							}
						}
					}					

					if (!xDrawed) {
						s += " ";
					}
				}
			}
			
			s += "|\n"; 
		}
		
		s += "   ";
		
		for (int col = 0; col < width; col++) {
			s += col;
		}
		
		s += "\n===============================";
		s += "\nLines cleared: " + linesCleared;
		s += "\nScore: " + score;
		
		return s;
	}
	
	//mvc
    protected void fireModelChanged() {
        for (JetrisView v : views) {
            v.modelChanged();
        }
    }


    public void addJetrisView(JetrisView v) {
        views.add(v);
        v.modelChanged();
    }
}
