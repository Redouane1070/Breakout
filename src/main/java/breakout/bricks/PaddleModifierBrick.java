package breakout.bricks;

import breakout.BreakoutState;
import breakout.balls.Ball;
import breakout.math.Point;
import breakout.math.Rectangle;
import breakout.paddles.Paddle;

/**
 * Superclass for all bricks that modify the paddle (e.g., make it wider) when the brick is hit.
 * These bricks are also single-life, i.e., they disappear after one hit.
 */
public abstract class PaddleModifierBrick extends LabeledBrick
{
    public PaddleModifierBrick(Rectangle geometry, Point gridPosition)
    {
        super(geometry, gridPosition);
    }

    /**
     * Removes the brick from the game state and calls the modifyPaddle method.
     */
    @Override
    public void hit(BreakoutState state, Ball ball)
    {
        state.getBrickGrid().removeBrick(this);
        modifyPaddle(state.getPaddle());
    }

    /**
     * Called when a ball hits this brick.
     * Subclasses can override this method to specify what should happen with the ball.
     */
    public abstract void modifyPaddle(Paddle paddle);
}
