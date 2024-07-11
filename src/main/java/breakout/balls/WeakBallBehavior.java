package breakout.balls;

import java.awt.Color;

import breakout.BreakoutState;
import breakout.BrickCollision;

/**
 * A weak ball does not call a Brick's hit method.
 * This causes the brick to keep existing.
 * A weak ball therefore merely bounces off objects in the game field.
 * 
 * A weak ball reverts to standard behavior after a certain time.
 */
public class WeakBallBehavior extends TemporaryBehavior
{
    /**
     * Color of balls with weak behavior.
     */
    public static final Color COLOR = Color.GRAY;

    /**
     * How long weak behavior stays active in milliseconds.
     */
    public static final int DURATION = 5000;

    public WeakBallBehavior()
    {
        super(DURATION);
    }

    /**
     * Moves ball to point of impact.
     * Does not call brick's hit method.
     * Updates velocity.
     */
    @Override
    public void bounceOffBrick(BreakoutState state, Ball ball, BrickCollision collision)
    {
    }

    /**
     * Weak balls are colored gray.
     */
    @Override
    public Color getColor()
    {
        return WeakBallBehavior.COLOR;
    }
}
