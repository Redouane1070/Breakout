package breakout.bricks;

import java.awt.Color;

import breakout.balls.Ball;
import breakout.math.Point;
import breakout.math.Rectangle;

/**
 * Bricks of this type disappear after a single hit and cause the ball to slow down.
 */
public class SlowDownBrick extends BallModifierBrick
{
    public static final Color COLOR = new Color(0, 255, 0);

    public static final String LABEL = "<<<";

    public SlowDownBrick(Rectangle geometry, Point gridPosition)
    {
        super(geometry, gridPosition);
    }

    @Override
    public Color getColor()
    {
        return SlowDownBrick.COLOR;
    }

    /**
     * Specifies what should happen to the ball when this brick is hit.
     * In the case of this class, the ball slows down.
     * See {@link breakout.balls.Ball#slowDown()}.
     */
    @Override
    public void modifyBall(Ball ball)
    {
    }

    @Override
    public Color getLabelColor()
    {
        return SlowDownBrick.COLOR;
    }

    @Override
    public String getLabel()
    {
        return LABEL;
    }
}