package breakout.bricks;

import java.awt.Color;

import breakout.BreakoutState;
import breakout.balls.Ball;
import breakout.math.Point;
import breakout.math.Rectangle;

/**
 * Represents bricks that disappear after a single hit and have no kind of effect other than that,
 * e.g., they don't change the paddle or ball in any way.
 */
public class StandardBrick extends Brick
{
    public static final Color COLOR = new Color(128, 128, 128);

    public StandardBrick(Rectangle geometry, Point gridPosition)
    {
        super(geometry, gridPosition);
    }

    @Override
    public Color getColor()
    {
        return StandardBrick.COLOR;
    }

    /**
     * Called when a ball hits this brick.
     * Removes the brick from the state.
     */
    @Override
    public void hit(BreakoutState state, Ball ball)
    {
    }
}
