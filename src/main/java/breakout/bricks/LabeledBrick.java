package breakout.bricks;

import java.awt.Color;

import breakout.math.Point;
import breakout.math.Rectangle;
import breakout.ui.Canvas;

/**
 * Convenience class for helping implement Brick classes that are
 * visually represented by a rectangle surrounding a label.
 *
 * This class provides a template to simplify defining such classes.
 */
public abstract class LabeledBrick extends Brick
{
    /**
     * Constructor.
     */
    public LabeledBrick(Rectangle geometry, Point gridPosition)
    {
        super(geometry, gridPosition);
    }

    /**
     * Draws a rectangle with a label inside of it.
     * The label is determined by the getLabel method.
     * The label's color is determined by the getLabelColor method.
     *
     * LEGIT
     *
     * @pre | canvas != null
     * @mutates | canvas
     */
    @Override
    public void paint(Canvas canvas)
    {
        super.paint(canvas);

        canvas.drawLabel(getLabelColor(), getLabel(), getGeometry().getCenter());
    }

    /**
     * Subtypes should implement this method to specify which color to use to paint the label with.
     */
    public abstract Color getLabelColor();

    /**
     * Subtypes should implement this method to specify which label to show on screen when rendering this brick.
     */
    public abstract String getLabel();
}
