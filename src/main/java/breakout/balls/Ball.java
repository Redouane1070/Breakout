package breakout.balls;

import java.awt.Color;

import breakout.BreakoutState;
import breakout.math.Circle;
import breakout.math.Point;
import breakout.math.Rectangle;
import breakout.math.Vector;
import breakout.ui.Canvas;

/**
 * This class represents a ball.
 * A ball has a geometry (represents the shape and position of the ball),
 * a velocity and a behavior.
 * 
 * The behavior of a ball defines how it interacts with the game field.
 * See for example StandardBehavior, WeakBallBehavior and StrongBallBehavior. 
 */
public class Ball
{
    /**
     * Determines by how much the speed goes up when the ball speeds up.
     * See {@link #speedUp()}.
     */
    public static final int SPEED_UP_FACTOR = 1050;
    
    /**
     * Determines by how much the speed goes down when the ball slows down.
     * See {@link #slowDown()}.
     */
    public static final int SLOW_DOWN_FACTOR = 950;
    
    /**
     * Slowdowns (using {@link #slowDown()}) are only applied if the new speed ends up higher than this value.
     * Note that it is allowed for a ball to have a lower speed, e.g., when using the constructor or {@link #setVelocity(Vector)}. 
     */
    public static final int MINIMUM_SLOWDOWN_SQUARED_SPEED = 5 * 5;
    
    /**
     * Speedups are only applied if the new speed ends up lower than this value.
     * Note that it is allowed for a ball to have a higher speed, e.g., when using the constructor or {@link #setVelocity(Vector)}.
     */
    public static final int MAXIMUM_SPEEDUP_SQUARED_SPEED = 80 * 80;

    /**
     * Determines shape and position of the ball.
     */
    private Circle geometry;

    /**
     * Expressed in distance per milliseconds.
     */
    private Vector velocity;

    /**
     * Determines how the ball behaves.
     * See subtypes of BallBehavior.
     */
    private BallBehavior behavior;

    /**
     * The ball must at all times fit inside this rectangle.
     * @representationObject
     */
    private final Rectangle allowedArea;

    /**
     * Constructor.
     * Note that the constructor does not enforce any limitations on the speed of the ball:
     * {@link #MINIMUM_SLOWDOWN_SQUARED_SPEED} and {@link #MAXIMUM_SPEEDUP_SQUARED_SPEED} are not taken into account. 
     */
    public Ball(Rectangle allowedArea, Circle geometry, Vector velocity, BallBehavior behavior)
    {
        this.allowedArea = allowedArea;
        this.geometry = geometry;
        this.velocity = velocity;
        this.behavior = behavior;
    }

    /**
     * Returns this ball's location.
     */
    public Circle getGeometry()
    {
        return this.geometry;
    }

    /**
     * Returns this ball's velocity.
     */
    public Vector getVelocity()
    {
        return this.velocity;
    }

    /**
     * Returns the ball's allowed area.
     */
    public Rectangle getAllowedArea()
    {
        return this.allowedArea;
    }

    /**
     * Returns this ball's behavior.
     */
    public BallBehavior getBehavior()
    {
        return this.behavior;
    }

    /**
     * Returns the color this ball should be painted in.
     * Subclasses should override this to make different types of ball visually distuingishable.
     */
    public Color getColor()
    {
        return Color.WHITE;
    }

    /**
     * Returns this ball's center.
     */
    public Point getCenter()
    {
        return getGeometry().getCenter();
    }

    /**
     * Updates the ball's state.
     * 
     * LEGIT
     * 
     * @pre | state != null
     * @pre | elapsedMilliseconds >= 0
     * @mutates | this
     * @mutates | state
     */
    public void tick(BreakoutState state, long elapsedMilliseconds)
    {
        this.behavior.update(state, this, elapsedMilliseconds);
    }

    /**
     * Moves the ball elapsedMilliseconds into the future.
     * This method does not take into account collisions with other elements:
     * it simply moves the ball in a straight line.
     */
    public void move(long elapsedMilliseconds)
    {
        setGeometry(computeDestination(elapsedMilliseconds));
    }

    /**
     * Computes the position the ball would be after elapsedMilleconds time passes.
     * Does not take into account collisions with other elements. 
     */
    public Circle computeDestination(long elapsedMilliseconds)
    {
        return getGeometry().move(this.velocity.multiply(elapsedMilliseconds));
    }

    /**
     * Updates the ball's geometry.
     */
    public void setGeometry(Circle geometry)
    {
        this.geometry = geometry;
    }

    /**
     * Updates the ball's velocity.
     * Note that {@link #MINIMUM_SLOWDOWN_SQUARED_SPEED} and {@link #MAXIMUM_SPEEDUP_SQUARED_SPEED}
     * are not taken into account by this method.
     */
    public void setVelocity(Vector velocity)
    {
        this.velocity = velocity;
    }

    /**
     * Checks that the given {@code velocity} is between {@link #MINIMUM_SLOWDOWN_SQUARED_SPEED} and {@link #MAXIMUM_SPEEDUP_SQUARED_SPEED}.
     */
    public boolean isValidScaledVelocity(Vector velocity)
    {
        var squaredLength = velocity.getSquaredLength();

        return MINIMUM_SLOWDOWN_SQUARED_SPEED <= squaredLength && squaredLength <= MAXIMUM_SPEEDUP_SQUARED_SPEED;
    }

    /**
     * Paints this ball onto the canvas.
     * 
     * LEGIT
     * 
     * @pre | canvas != null
     * @mutates | canvas
     */
    public void paint(Canvas canvas)
    {
        this.behavior.paint(canvas, this);
    }

    /**
     * Sets the ball's behavior.
     */
    public void setBehavior(BallBehavior behavior)
    {
        this.behavior = behavior;
    }

    /**
     * Scales the ball's speed.
     * Only has an effect if the new speed would be between
     * {@link #MINIMUM_SLOWDOWN_SQUARED_SPEED} and {@link #MAXIMUM_SPEEDUP_SQUARED_SPEED}.
     * 
     * LEGIT
     */
    private void scaleVelocity(int kilofactor)
    {
        var scaledVelocity = this.velocity.multiply(kilofactor).divide(1000);

        if ( isValidScaledVelocity(scaledVelocity) )
        {
            setVelocity(this.velocity.multiply(kilofactor).divide(1000));
        }
    }

    /**
     * Increases this ball's speed by {@link #SPEED_UP_FACTOR} if the ball's updated speed speed would not exceed {@link #MAXIMUM_SPEEDUP_SQUARED_SPEED}.
     * 
     * LEGIT
     * 
     * @post | old(getVelocity().multiply(SPEED_UP_FACTOR).divide(1000).getSquaredLength()) <= MAXIMUM_SPEEDUP_SQUARED_SPEED ? getVelocity().equals(old(getVelocity().multiply(SPEED_UP_FACTOR).divide(1000))) : getVelocity().equals(old(getVelocity())) 
     * @mutates_properties | getVelocity()
     */
    public void speedUp()
    {
        scaleVelocity(SPEED_UP_FACTOR);
    }

    /**
     * Decreases this ball's speed by {@link #SLOW_DOWN_FACTOR} if the ball's updated speed would not be not lower than {@link #MINIMUM_SLOWDOWN_SQUARED_SPEED}.
     * 
     * LEGIT
     * 
     * @post | old(getVelocity().multiply(SLOW_DOWN_FACTOR).divide(1000).getSquaredLength()) >= MINIMUM_SLOWDOWN_SQUARED_SPEED ? getVelocity().equals(old(getVelocity().multiply(SLOW_DOWN_FACTOR).divide(1000))) : getVelocity().equals(old(getVelocity())) 
     * @mutates_properties | getVelocity()
     */
    public void slowDown()
    {
        scaleVelocity(SLOW_DOWN_FACTOR);
    }
}
