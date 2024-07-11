package breakout;

import breakout.bricks.Brick;
import breakout.math.Vector;

/**
 * Objects of this class contain data about the collision
 * between a ball and a brick.
 */
public class BrickCollision extends Collision
{
    private final Brick brick;

    public BrickCollision(long time, Vector kiloNormal, Brick brick)
    {
        super(0, null);
        this.brick = null;
    }

    public Brick getBrick()
    {
        return this.brick;
    }

    /**
     * LEGIT
     */
    @Override
    public String toString()
    {
        return String.format("Collision(t=%d, n=%s, p=%s)", getMillisecondsUntilCollision(), getKiloNormal(), getBrick().getGridPosition());
    }
}
