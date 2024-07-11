package breakout.bricks;

import java.awt.Color;

import breakout.balls.Ball;
import breakout.balls.StrongBallBehavior;
import breakout.math.Point;
import breakout.math.Rectangle;

/**
 * Represents brick that disappear after a single hit
 * and cause the ball to be temporarily weakened.
 * See WeakBallBehavior for what this means.
 */
public class StrengtheningBrick extends BallModifierBrick
{
    public static final Color COLOR = new Color(0, 0, 255);

    public static final String LABEL = "F";

    public StrengtheningBrick(Rectangle geometry, Point gridPosition)
    {
        super(geometry, gridPosition);
    }

    @Override
    public Color getColor()
    {
        return StrengtheningBrick.COLOR;
    }

    /**
     * Specifies what should happen to the ball when this brick is hit.
     * In the case of this class, the ball is given a new behavior, i.e., it is made strong.
     */
    @Override
    public void modifyBall(Ball ball)
    {
    }

    @Override
    public Color getLabelColor()
    {
        return StrengtheningBrick.COLOR;
    }

    @Override
    public String getLabel()
    {
        return LABEL;
    }
}
