package breakout.bricks;

import java.awt.Color;

import breakout.balls.Ball;
import breakout.balls.WeakBallBehavior;
import breakout.math.Point;
import breakout.math.Rectangle;

/**
 * Represents brick that disappear after a single hit
 * and cause the ball to be temporarily strengthened.
 * See StrongBallBehavior for what this means.
 */
public class WeakeningBrick extends BallModifierBrick
{
    public static final Color COLOR = new Color(255, 0, 0);

    public static final String LABEL = "W";

    public WeakeningBrick(Rectangle geometry, Point gridPosition)
    {
        super(geometry, gridPosition);
    }

    @Override
    public Color getColor()
    {
        return WeakeningBrick.COLOR;
    }

    /**
     * Specifies what should happen to the ball when this brick is hit.
     * In the case of this class, the ball is given a new behavior, i.e., it is made weak.
     */
    @Override
    public void modifyBall(Ball ball)
    {
    }

    @Override
    public Color getLabelColor()
    {
        return WeakeningBrick.COLOR;
    }

    @Override
    public String getLabel()
    {
        return LABEL;
    }
}
