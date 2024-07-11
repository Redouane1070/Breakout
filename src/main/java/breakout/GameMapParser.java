package breakout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import breakout.balls.StandardBehavior;
import breakout.bricks.lock.LockedBrick;
import breakout.math.Circle;
import breakout.math.Point;
import breakout.math.Vector;

public class GameMapParser
{
    private GameMapParser()
    {
        // NOP
    }

    /**
     * Create a fresh BreakoutState using a game field described by strings. 
     *
     * @pre | lines != null
     * @pre | Arrays.stream(lines).allMatch(line -> line != null)
     * @pre | lines.length >= 1
     * @pre | IntStream.range(1, lines.length).allMatch(i -> lines[0].length() == lines[i].length())
     * @pre | brickWidth > 0
     * @pre | brickHeight > 0
     * @post | result != null
     * @post | result.getBrickGrid().getRowCount() == lines.length
     * @post | result.getBrickGrid().getColumnCount() == lines[0].length()
     * @post | result.getBrickGrid().getBrickWidth() == brickWidth
     * @post | result.getBrickGrid().getBrickHeight() == brickHeight
     */
    public static BreakoutState parse(String[] lines, int brickWidth, int brickHeight)
    {
        var brickGrid = parseBrickGrid(lines, brickWidth, brickHeight);
        var paddleHalfWidth = brickWidth;
        var paddleSpeed = brickWidth / 100;
        var state = new BreakoutState(brickGrid, paddleHalfWidth, paddleSpeed);
        addBalls(state);

        return state;
    }

    /*
     * LEGIT
     * 
     * #:standard
	 * S:sturdy
	 * +: grow paddle
	 * -: shrink paddle
	 * W/F: weakening/strengthening brick
	 * >/<: speed up /slow down brick
	 * M/m: master/master2
	 * L/l: locked/locked2
     */
    private static BrickGrid parseBrickGrid(String[] lines, int brickWidth, int brickHeight)
    {
        var width = lines[0].length();
        var height = lines.length;
        var brickGrid = new BrickGrid(width, height, brickWidth, brickHeight);
        var masterBricksPositions = new ArrayList<Point>();
        var masterBricksPositions2 = new ArrayList<Point>();
        var lockedBricks = new ArrayList<LockedBrick>();
        var lockedBricks2 = new ArrayList<LockedBrick>();

        for ( int y = 0; y != height; ++y )
        {
            for ( int x = 0; x != width; ++x )
            {
                var gridPosition = new Point(x, y);

                switch ( lines[y].charAt(x) )
                {
                case ' ':
                    // NOP
                    break;

                case '#':
                    brickGrid.addStandardBrick(gridPosition);
                    break;

                case 'S':
                    brickGrid.addSturdyBrick(gridPosition, 3);
                    break;

                case '+':
                    brickGrid.addGrowPaddleBrick(gridPosition);
                    break;

                case '-':
                    brickGrid.addShrinkPaddleBrick(gridPosition);
                    break;

                case 'W':
                    brickGrid.addWeakeningBrick(gridPosition);
                    break;

                case 'F':
                    brickGrid.addStrengtheningBrick(gridPosition);
                    break;

                case '>':
                    brickGrid.addSpeedUpBrick(gridPosition);
                    break;

                case '<':
                    brickGrid.addSlowDownBrick(gridPosition);
                    break;

                case 'M':
                    masterBricksPositions.add(gridPosition);
                    break;

                case 'm':
                    masterBricksPositions2.add(gridPosition);
                    break;
                    
                case 'L':
                {
                    var brick = brickGrid.addLockedBrick(gridPosition);
                    lockedBricks.add(brick);
                    break;
                }
                    
                case 'l':
                {
                    var brick = brickGrid.addLockedBrick(gridPosition);
                    lockedBricks2.add(brick);
                    break;
                }

                default:
                    throw new IllegalArgumentException();
                }
            }
        }

        if ( masterBricksPositions.isEmpty() && !lockedBricks.isEmpty() )
        {
            throw new IllegalArgumentException("Cannot have locked bricks without master brick");
        }
        
        if ( masterBricksPositions2.isEmpty() && !lockedBricks2.isEmpty() )
        {
            throw new IllegalArgumentException("Cannot have locked bricks without master brick");
        }

        for ( var masterBrickPosition : masterBricksPositions )
        {
            brickGrid.addMasterBrick(masterBrickPosition, lockedBricks);
        }
        
        for ( var masterBrickPosition : masterBricksPositions2 )
        {
            brickGrid.addMasterBrick(masterBrickPosition, lockedBricks2);
        }

        return brickGrid;
    }

    /**
     * LEGIT
     */
    private static void addBalls(BreakoutState state)
    {
        var brickGrid = state.getBrickGrid();
        var ballRadius = 500;
        var ballPosition = brickGrid.getBoundingRectangle().getBottomCenter().add(new Vector(0, -2 * ballRadius));
        var geometry = new Circle(ballPosition, ballRadius);
        var velocity = new Vector(25, -25);
        var behavior = new StandardBehavior();
        
        state.addBall(geometry, velocity, behavior);
    }
    
    public final static String[] descr1 = new String[] {
		"L       l",
		"M       m",
		"         ",
		"         ",
		"         ",
		"         ",
		"         ",
		"         ",    		
    };
    
    /**
     * #:standard
	 * S:sturdy
	 * +: grow paddle
	 * -: shrink paddle
	 * W/F: weakening/strengthening brick
	 * >/<: speed up /slow down brick
	 * M/m: master/master2
	 * L/l: locked/locked2
     */
    public final static String[] descr2 = new String[] {
    	"#####",
    	"SSSSS",
    	"++#--",
    	"W###F",
    	">###<",
    	"##M#L",
    	"     ",
    	"     ",
    	"     ",
    	"     ",
    	"     ",
    	"     ",
    	"     ",
    	"     ",
    	"     ",
    	"     ",
    	"     ",
    };
    
    public final static String[] descr3 = new String[] {
            "#########",
            "FFFFWWWW ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
    };
    
    
    public final static String[] descr4 = new String[] {
    		"SSSSS",
    		"     ",
    		"     ",
    		"     ",
    		"     ",
    };
    
    public final static String[] descr5 = new String[] {
    		"S#S#SS#S#SS",
    		"S#S#S#M#L#S",
    		"           ",
    		"           ",
    		"           ",
    		"           ",
    		"           ",
    		"           ",
    };
    
    public final static String[] descr6 = new String[] {
    	">###>##>#",
    	"#########",
    	"<##<#<###",
    	"###<#####",
    	"         ",
    	"         ",
    	"         ",
    	"         ",
    	"         ",
    	"         ",
    	"         ",
    };
    

    public final static String[] descr7 = new String[] {
    	"FFMMLM##L#FF",
    	"FFMmlm#m#MFF",
    	"FFM      MFF",
    	"            ",
    	"            ",
    	"            ",
    	"            ",
    	"            ",
    	"            ",
    	"            ",
    	"            ",    	
    };
    

    public final static String[] OGP_MAP = new String[] {
            "M          L",
            "  #      #  ",
            "  #      #  ",
            ">>#      #<<",
            "++#  SS  #++",
            "            ",
            "FFFFFFFFFFFF",
            "            ",
            "            ",
            "            ",
            "            "     
        };
    
    public final static String[] OOP_MAP = new String[] {
            "            ",
            "  #      #  ",
            "  #      #  ",
            ">>#      #<<",
            "++#  SS  #++",
            "            ",
            "FFFFFFFFFFFF",
            "            ",
            "            ",
            "            ",
            "            "    
        };
}
