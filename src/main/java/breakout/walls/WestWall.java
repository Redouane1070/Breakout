package breakout.walls;

import breakout.Collision;
import breakout.balls.Ball;
import breakout.math.Vector;

/**
 * Represents the west wall.
 * It is infinitely long, i.e., it ranges from -infinity to +infinity in the vertical direction.
 * It keeps balls from escaping the field through the left side of the game field.
 */
public class WestWall extends VerticalWall
{
    /**
     * Constructor.
     */
    public WestWall(long xCoordinate)
    {
        super(xCoordinate);
    }

    /**
     * LEGIT
     */
    @Override
    public Collision findCollision(Ball ball)
    {
        var ballPosition = ball.getGeometry().getLeftmostPoint();
        var ballVelocity = ball.getVelocity();

        if ( ballVelocity.x() < 0 && ballPosition.x() >= getXCoordinate() )
        {
            var t = (ballPosition.x() - getXCoordinate()) / -ballVelocity.x();
            
            return new Collision(t, Vector.KILO_LEFT);
        }

        return null;
    }
    
    public Vector getNormal()
    {
        return Vector.RIGHT;
    }
}
