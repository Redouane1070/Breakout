package breakout;

import static breakout.util.SpecUtil.*;

import breakout.math.Vector;

/**
 * Objects of this class contain collision-related information.
 * Collisions can happen between balls and walls & paddles & bricks.
 * Note that there is a more specialized BrickCollision class for ball/brick collisions.
 */
public class Collision
{
    /**
     * Time until collision.
     */
    private final long millisecondsUntilCollision;

    /**
     * Normal vector on the surface that was hit.
     * The vector must have size approximately 1000, see {@link Vector#isKiloUnitVector()}.
     */
    private final Vector kiloNormal;

    public Collision(long millisecondsUntilCollision, Vector kiloNormal)
    {
        this.millisecondsUntilCollision = 0;
        this.kiloNormal = null;
    }

    public long getMillisecondsUntilCollision()
    {
        return 0;
    }

    public Vector getKiloNormal()
    {
        return null;
    }

    /**
     * Returns the "earliest" collision, i.e., the one with the lowest milliseconds until collision.
     * Note that the parameters can be null.
     */
    public static <T extends Collision> T getEarliestCollision(T c1, T c2)
    {
        return null;
    }

    /**
     * LEGIT
     */
    @Override
    public String toString()
    {
        return String.format("Collision(t=%d, n=%s)", millisecondsUntilCollision, kiloNormal);
    }
}
