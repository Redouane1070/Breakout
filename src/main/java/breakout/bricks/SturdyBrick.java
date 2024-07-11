package breakout.bricks;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import breakout.BreakoutState;
import breakout.balls.Ball;
import breakout.math.Point;
import breakout.math.Rectangle;
import breakout.util.SpecUtil;

/**
 * A sturdy brick has multiple lives: it takes more than one hit to make it disappear.
 */
public class SturdyBrick extends LabeledBrick
{
    public static final List<Color> COLORS = Collections.unmodifiableList(Arrays.asList(new Color(160, 82, 45), new Color(123, 63, 0), new Color(92, 64, 51)));

    public static final Color LABEL_COLOR = Color.WHITE;

    private int livesLeft;

    public SturdyBrick(Rectangle geometry, Point gridPosition, int lives)
    {
        super(geometry, gridPosition);
    }

    public int getLivesLeft()
    {
        return livesLeft;
    }

    @Override
    public Color getColor()
    {
        return COLORS.get(this.livesLeft - 1);
    }

    /**
     * Specifies what should happen when this brick is hit.
     * In the case of this class, the brick should lose one life.
     * When it has no lives left, it should disappear.
     */
    @Override
    public void hit(BreakoutState state, Ball ball)
    {
        livesLeft--;

        if ( livesLeft == 0 )
        {
            state.getBrickGrid().removeBrick(this);
        }
    }

    /**
     * Specifies what should happen when this brick is hit by a strong ball.
     * In the case of this class, the lives should go straight to zero and the brick should disappear.
     */
    @Override
    public boolean strongHit(BreakoutState state, Ball ball)
    {
        livesLeft = 0;
        state.getBrickGrid().removeBrick(this);

        return false;
    }

    @Override
    public String getLabel()
    {
        return Integer.toString(livesLeft);
    }

    @Override
    public Color getLabelColor()
    {
        return SturdyBrick.LABEL_COLOR;
    }
}
