package breakout.paddles;

import java.awt.Color;

import breakout.BreakoutState;
import breakout.Collision;
import breakout.balls.Ball;
import breakout.math.Interval;
import breakout.math.Point;
import breakout.math.Rectangle;
import breakout.math.Vector;
import breakout.ui.Canvas;

/**
 * Represents the paddle in the breakout game.
 *
 * @invar | getTopCenter() != null
 * @invar | getAllowedInterval().isInside(getTopCenter().x() - getHalfWidth())
 * @invar | getAllowedInterval().isInside(getTopCenter().x() + getHalfWidth())
 */
public class Paddle
{
    public static final int HEIGHT = 1000;

    public static final int GROW_FACTOR = 1100;

    public static final int SHRINK_FACTOR = 900;

    /**
     * @invar | topCenter != null
     */
    private Point topCenter;

    /**
     * @invar | halfWidth > 0
     */
    private long halfWidth;

    /**
     * @invar | motionDirection != null
     */
    private PaddleMotionDirection motionDirection;

    /**
     * Speed at which paddle moves.
     * Whether the paddle actually moves is determined by motionDirection,
     * but if the paddle moves, it is at this speed.
     */
    private final long speed;

    /**
     * The entire width of the paddle must fit inside this interval.
     */
    private final Interval allowedInterval;

    /**
     * Construct a paddle located around a given center in the field.
     * Note that we specify its half size instead of its full size.
     * This is to avoid the rounding errors that would occur if its size were odd.
     */
    public Paddle(Interval allowedInterval, Point topCenter, long halfWidth, long speed)
    {
        this.topCenter = topCenter;
        this.halfWidth = halfWidth;
        this.motionDirection = PaddleMotionDirection.STATIONARY;
        this.speed = speed;
        this.allowedInterval = allowedInterval;
    }

    public PaddleMotionDirection getMotionDirection()
    {
        return this.motionDirection;
    }

    public void setMotionDirection(PaddleMotionDirection direction)
    {
        this.motionDirection = direction;
    }

    /**
     * Return the center point of this paddle.
     */
    public Point getTopCenter()
    {
        return topCenter;
    }

    public Interval getAllowedInterval()
    {
        return this.allowedInterval;
    }

    public long getHalfWidth()
    {
        return this.halfWidth;
    }

    public long getWidth()
    {
        return this.halfWidth * 2;
    }

    /**
     * Returns the paddle's height.
     */
    public long getHeight()
    {
        return HEIGHT;
    }

    /**
     * Returns the paddle's speed.
     */
    public long getSpeed()
    {
        return speed;
    }

    /**
     * Returns a rectangle representing the paddle's shape.
     */
    public Rectangle getGeometry()
    {
        return null;
    }

    /**
     * Moves the paddle so that its top center x coordinate equals the given x.
     * Ensures that the paddle does not go outside the allowed area.
     */
    public void setTopCenterX(long x)
    {
    }

    /**
     * Moves this Paddle a certain distance.
     * Ensures that the paddle remains within the allowed area.
     */
    public void move(long distance)
    {
    }

    /**
     * Computes the new state of the paddle in elapsedMilliseconds, i.e.,
     * moves the paddle a certain distance, determined by its motion direction
     * and speed.
     */
    public void tick(BreakoutState state, long elapsedMilliseconds)
    {
        var distance = computeMovementDistance(elapsedMilliseconds);
        this.move(distance);
    }

    /**
     * Computes how much distance the paddle travels in the given time.
     * It is dependent on the paddle's motion direction and speed.
     */
    public long computeMovementDistance(long elapsedMilliseconds)
    {
        return 0;
    }

    /**
     * Computes a "corrected" x-coordinate for the paddle's top center.
     * Say we want the paddle's top center to move to position x,
     * this method checks whether this move would be allowed, i.e.,
     * if the paddle will still fit inside its allowed range.
     * If not, this method returns a fixed x so that this is the case.
     *
     * LEGIT
     */
    public long clamp(long x)
    {
        return clampPrivate(x);
    }

    /**
     * Private twin of clamp.
     * Its purpose is to be callable while the Paddle object is in state violating its invariant.
     *
     * LEGIT
     */
    private long clampPrivate(long x)
    {
        if ( x - halfWidth < allowedInterval.getLowerBound() )
        {
            return allowedInterval.getLowerBound() + halfWidth;
        }

        if ( x + halfWidth > allowedInterval.getUpperBound() )
        {
            return allowedInterval.getUpperBound() - halfWidth;
        }

        return x;
    }

    /**
     * Returns a corrected version of the position so that
     * the paddle fits within the allowed range.
     */
    public Point clamp(Point position)
    {
        return clampPrivate(position);
    }

    /**
     * Private twin of clamp.
     * Its purpose is to be callable while the Paddle object is in state violating its invariant.
     */
    private Point clampPrivate(Point position)
    {
        var x = clampPrivate(position.x());
        var y = position.y();

        return new Point(x, y);
    }

    /**
     * Finds the collision between this paddle and the given ball.
     * Can return null if no such collision occurs.
     *
     * LEGIT
     *
     * @pre | ball != null
     * @inspects | ball
     */
    public Collision findCollision(Ball ball)
    {
        var ballVelocity = ball.getVelocity();
        var ballPosition = ball.getGeometry().getBottommostPoint();

        if ( ballVelocity.y() > 0 && ballPosition.y() < getTopCenter().y() )
        {
            var t = (getTopCenter().y() - ballPosition.y()) / ballVelocity.y();
            var x = ballPosition.x() + t * ballVelocity.x();

            if ( topCenter.x() - halfWidth <= x && x <= topCenter.x() + halfWidth )
            {
                return new Collision(t, getKiloNormal(x));
            }
        }

        return null;
    }

    /**
     * Paints the paddle onto the canvas.
     *
     * LEGIT
     *
     * @pre | canvas != null
     * @mutates | canvas
     */
    public void paint(Canvas canvas)
    {
        canvas.drawFilledRectangle(Color.WHITE, getGeometry());
    }

    /**
     * Changes the paddle's size.
     * The parameter kilofactor is equal to the actual scale factor times 1000,
     * so as to allow more fine grained scaling.
     *
     * If necessary, the paddle's position is updated to ensure that the paddle still lies
     * within its allowed range.
     *
     * The paddle's size cannot exceed the allowed interval's width.
     * If the scaling factor is too high, the paddle's size is set to its maximally allowed value.
     */
    public void scale(long kilofactor)
    {
    }

    /**
     * Grows the paddle by a factor GROW_FACTOR.
     *
     * LEGIT
     */
    public void grow()
    {
        scale(GROW_FACTOR);
    }

    /**
     * Shrinks the paddle by a factor SHRINK_FACTOR.
     *
     * LEGIT
     */
    public void shrink()
    {
        scale(SHRINK_FACTOR);
    }

    /**
     * Returns normal vector on paddle at position x.
     * Though the paddle is drawn as a rectangle, you have to imagine
     * it has a curved top.
     *
     * LEGIT
     */
    public Vector getKiloNormal(long x)
    {
        var relativePosition = (x - this.getTopCenter().x()) * 1000 / halfWidth;

        assert -1000 <= relativePosition;
        assert relativePosition <= 1000;

        return new Vector(relativePosition / 3, -1000).rescale(1000);
    }
}
