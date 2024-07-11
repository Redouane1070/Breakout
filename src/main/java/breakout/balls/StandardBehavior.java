package breakout.balls;

import java.awt.Color;
import java.util.ArrayList;

import breakout.BreakoutState;
import breakout.BrickCollision;
import breakout.Collision;
import breakout.util.SpecUtil;

/**
 * Implements the standard behavior of a ball.
 * 
 */
public class StandardBehavior extends BallBehavior
{
    public final static Color COLOR = Color.WHITE;
    
    /**
     * This method computes what happens to the ball in the next elapsedMilliseconds.
     */
    public void update(BreakoutState state, Ball ball, long elapsedMilliseconds)
    {
        super.update(state,  ball,  elapsedMilliseconds);
    }

    /**
     * Moves the ball to the point of impact with the wall and updates the velocity.
     */
    @Override
    public void bounceOffWall(BreakoutState state, Ball ball, Collision collision)
    {
        super.bounceOffWall(state, ball, collision);
    }

    /**
     * Moves the ball to the point of impact with the paddle and updates the velocity.
     */
    @Override
    public void bounceOffPaddle(BreakoutState state, Ball ball, Collision collision)
    {
        super.bounceOffPaddle(state, ball, collision);
    }

    /**
     * Moves the ball to the point of impact with the brick, hits the brick and updates the velocity.
     */
    @Override
    public void bounceOffBrick(BreakoutState state, Ball ball, BrickCollision collision)
    {
        super.bounceOffBrick(state, ball, collision);
    }

    /**
     * Called when the ball is lost, i.e., ventures outside the playing field.
     * A ball with standard behavior removes itself from the game state.
     */
    @Override
    public void ballLost(BreakoutState state, Ball ball)
    {
        super.ballLost(state, ball);
    }

    /**
     * Standard behavior is represented by the color white.
     * See {@link #COLOR}.
     */
    public Color getColor()
    {
        return COLOR;
    }
}
