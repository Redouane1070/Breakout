package breakout.bricks;

import java.awt.Color;

import breakout.BreakoutState;
import breakout.balls.Ball;
import breakout.math.Point;
import breakout.math.Rectangle;
import breakout.ui.Canvas;

/**
 * Superclass for all bricks.
 * 
 * All bricks have two properties:
 * - a rectangle (geometry), representing their position in the game world.
 * - a grid position, representing the position in the block grid
 */
public abstract class Brick
{
    private final Rectangle geometry;

    /**
     * @invar | gridPosition != null
     */
    private final Point gridPosition;

    /**
     * Constructor.
     */
    public Brick(Rectangle geometry, Point gridPosition)
    {
        this.geometry = geometry;
        this.gridPosition = gridPosition;
    }

    /**
     * Returns the grid position.
     */
    public Point getGridPosition()
    {
        return this.gridPosition;
    }

    /**
     * Returns the rectangle occupied by this brick in the game world.
     */
    public Rectangle getGeometry()
    {
        return geometry;
    }

    /**
     * Paints the brick using the canvas.
     * 
     * LEGIT
     * 
     * @pre | canvas != null
     * @mutates | canvas
     */
    public void paint(Canvas canvas)
    {
        canvas.drawRectangle(getColor(), getGeometry());
    }

    /**
     * Used in the paint method to determine the color of the rectangle on screen.
     * Can be overriden in the subclasses to give each brick their own color.
     */
    public Color getColor()
    {
        return Color.WHITE;
    }

    /**
     * Called when this brick has been hit by a ball.
     * It is given the full BreakoutState and the Ball which has hit the brick.
     * This method should update the state and/or ball,
     * e.g., remove the brick from the state, change the paddle's size, etc.
     */
    public abstract void hit(BreakoutState state, Ball ball);

    /**
     * Called when this brick has been hit by a strong ball.
     * Like the hit method, it should update the state/ball to reflect what
     * happens when this brick is hit by a strong ball. 
     * 
     * Returns true if brick survives, false otherwise.
     */
    public boolean strongHit(BreakoutState state, Ball ball)
    {
        this.hit(state, ball);

        return false;
    }
}
