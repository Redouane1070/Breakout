package breakout;

import java.util.ArrayList;
import java.util.Arrays;

import breakout.balls.Ball;
import breakout.balls.BallBehavior;
import breakout.bricks.Brick;
import breakout.math.Circle;
import breakout.math.Interval;
import breakout.math.Rectangle;
import breakout.math.Vector;
import breakout.paddles.Paddle;
import breakout.util.SpecUtil;
import breakout.walls.EastWall;
import breakout.walls.NorthWall;
import breakout.walls.Wall;
import breakout.walls.WestWall;

/**
 * Represents the current state of a breakout game.
 * 
 */
public class BreakoutState
{
    public static int MAXIMUM_TIME_DELTA = 20;

    /**
     * List of all balls.
     * 
     * @representationObject
     */
    private ArrayList<Ball> balls;


    private final BrickGrid bricks;


    private Paddle paddle;

    /**
     * @representationObject
     */
    private final ArrayList<Wall> walls;

    /**
     * Construct a new BreakoutState.
     *
     * LEGIT
     *
     * @post | getBrickGrid() == brickGrid
     * @post | getBalls().isEmpty()
     * @post | getWalls().size() == 3
     * @post | getPaddle() != null
     * 
     * @throws IllegalArgumentException
     *   | brickGrid == null
     * @throws IllegalArgumentException
     *   | initialPaddleHalfWidth <= 0
     * @throws IllegalArgumentException
     *   | paddleSpeed <= 0
     */
    public BreakoutState(BrickGrid brickGrid, long initialPaddleHalfWidth, long paddleSpeed)
    {
        if ( brickGrid == null )
        {
            throw new IllegalArgumentException();
        }

        this.balls = new ArrayList<>();
        this.bricks = brickGrid;
        this.paddle = createPaddle(brickGrid, initialPaddleHalfWidth, paddleSpeed);
        this.walls = createWalls(brickGrid);
    }

    /**
     * LEGIT
     */
    private static Paddle createPaddle(BrickGrid brickGrid, long initialPaddleHalfWidth, long speed)
    {
        var allowedInterval = new Interval(0, brickGrid.getWidth());
        var topCenter = brickGrid.getBoundingRectangle().getBottomCenter();

        return new Paddle(allowedInterval, topCenter, initialPaddleHalfWidth, speed);
    }

    /**
     * LEGIT
     */
    private static ArrayList<Wall> createWalls(BrickGrid brickGrid)
    {
        var right = brickGrid.getWidth();
        var topWall = new NorthWall(0);
        var rightWall = new EastWall(right);
        var leftWall = new WestWall(0);

        return new ArrayList<>(Arrays.asList(topWall, rightWall, leftWall));
    }

    /**
     * Returns the list of balls.
     */
    public ArrayList<Ball> getBalls()
    {
        return balls;
    }

    /**
     * Return the paddle of this BreakoutState.
     * 
     */
    public Paddle getPaddle()
    {
        return paddle;
    }

    /**
     * Private twin of getBoundingRectangle() so as to be usable internally.
     * 
     * LEGIT
     */
    private Rectangle getBoundingRectanglePrivate()
    {
        var left = 0;
        var top = 0;
        var width = this.bricks.getWidth();
        var height = this.bricks.getHeight() + this.paddle.getHeight();

        return new Rectangle(left, top, width, height);
    }

    /**
     * Return a rectangle representing the game field.
     *
     * LEGIT
     *
     * @post | result != null
     */
    public Rectangle getBoundingRectangle()
    {
        return getBoundingRectanglePrivate();
    }

    /**
     * Move all moving objects one step forward.
     * Cuts large elapsedMilliseconds in multiple smaller values.
     * 
     * LEGIT
     *
     * @pre | elapsedMilliseconds >= 0
     *
     * @mutates | this
     * @mutates | ...getBalls()
     */
    public void tick(long elapsedMilliseconds)
    {
        while ( elapsedMilliseconds > 0 )
        {
            var dt = Math.min(MAXIMUM_TIME_DELTA, elapsedMilliseconds);
            atomicTick(dt);
            elapsedMilliseconds -= dt;
        }
    }

    /**
     * LEGIT
     */
    private void atomicTick(long elapsedTime)
    {
        paddle.tick(this, elapsedTime);

        for ( var ball : new ArrayList<>(this.balls) )
        {
            ball.tick(this, elapsedTime);
        }
    }

    /**
     * Checks if the game has ended.
     *  
     */
    public boolean isGameOver()
    {
        return false;
    }

    /**
     * Checks whether the game has been won.
     * The game is won when there are no more bricks left.
     *
     */
    public boolean isGameWon()
    {
        return false;
    }

    /**
     * Checks whether the game has been lost.
     * The game is lost when there are no more balls left.
     *
     */
    public boolean isGameLost()
    {
        return false;
    }

    /**
     * Returns a list of walls.
     * 
     */
    public ArrayList<Wall> getWalls()
    {
        return walls;
    }

    /**
     * Returns a list of bricks.
     * 
     * LEGIT
     * 
     * @post | result != null
     * @post | result.stream().allMatch(brick -> brick != null)
     * @post | !SpecUtil.containsDuplicateObjects(result)
     */
    public ArrayList<Brick> getBricks()
    {
        return this.bricks.getBricks();
    }

    /**
     * Removes the given ball from the game.
     * 
     */
    public void removeBall(Ball ball)
    {
        this.balls.remove(ball);
    }

    /**
     * Checks if the ball is lost.
     * 
     * LEGIT
     * 
     * @pre | ball != null
     */
    public boolean isBallLost(Ball ball)
    {
        return !getBoundingRectangle().contains(ball.getCenter());
    }

    /**
     * Adds new ball to the game and returns the reference to this new ball.
     * 
     * @pre | geometry != null
     * @pre | velocity != null
     * @pre | behavior != null
     * @pre | getBoundingRectangle().growHeight(this.getBrickGrid().getBrickHeight()).contains(geometry)
     * @creates | result
     * @post | result != null
     * @post | SpecUtil.sameListsWithElementRemoved(getBalls(), old(new ArrayList<>(getBalls())), result)
     * @post | result.getGeometry().equals(geometry)
     * @post | result.getVelocity().equals(velocity)
     * @post | result.getBehavior() == behavior
     */
    public Ball addBall(Circle geometry, Vector velocity, BallBehavior behavior)
    {
        return null;
    }


    public BrickGrid getBrickGrid()
    {
        return null;
    }
}
