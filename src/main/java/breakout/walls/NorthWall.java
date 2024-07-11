package breakout.walls;

import breakout.Collision;
import breakout.balls.Ball;
import breakout.math.Vector;

/**
 * Represents the north wall.
 * It is infinitely long, i.e., it ranges from -infinity to +infinity in the horizontal direction.
 * It keeps balls from escaping the field through the top of the game field.
 */
public class NorthWall extends HorizontalWall
{
    public NorthWall(long yCoordinate)
    {
        super(yCoordinate);
    }

    /**
     * LEGIT
     */
    @Override
    public Collision findCollision(Ball ball)
    {
        var ballPosition = ball.getGeometry().getTopmostPoint();
        var ballVelocity = ball.getVelocity();

        if ( ballVelocity.y() < 0 && ballPosition.y() > getYCoordinate() )
        {
            var t = (ballPosition.y() - getYCoordinate()) / -ballVelocity.y();

            return new Collision(t, Vector.KILO_DOWN);
        }

        return null;
    }
    
    public Vector getNormal()
    {
        return Vector.DOWN;
    }
}
