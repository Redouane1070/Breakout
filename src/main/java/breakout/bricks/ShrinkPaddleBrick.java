package breakout.bricks;

import java.awt.Color;

import breakout.math.Point;
import breakout.math.Rectangle;
import breakout.paddles.Paddle;

/**
 * This class represents bricks that disappear after a single hit
 * and cause the paddle to shrink.
 */
public class ShrinkPaddleBrick extends PaddleModifierBrick
{
    public static final Color COLOR = new Color(255, 0, 0);

    public static final String LABEL = "><";

    public ShrinkPaddleBrick(Rectangle geometry, Point gridPosition)
    {
        super(geometry, gridPosition);
    }

    @Override
    public Color getColor()
    {
        return GrowPaddleBrick.COLOR;
    }

    /**
     * This method specifies what must happen to the paddle when a ball hits this brick.
     * In the case of this class, the paddle shrinks. See {@link breakout.paddles.Paddle#shrink()}.
     */
    @Override
    public void modifyPaddle(Paddle paddle)
    {
    }

    /**
     * @post | result != null
     * @post | result.equals(ShrinkPaddleBrick.COLOR)
     */
    @Override
    public Color getLabelColor()
    {
        return ShrinkPaddleBrick.COLOR;
    }

    /**
     * @post | result != null
     * @post | result.equals(ShrinkPaddleBrick.LABEL)
     */
    @Override
    public String getLabel()
    {
        return ShrinkPaddleBrick.LABEL;
    }
}
