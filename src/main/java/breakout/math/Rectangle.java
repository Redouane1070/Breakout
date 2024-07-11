package breakout.math;

import java.util.Objects;

/**
 * Represents a rectangle in a 2-dimensional integer coordinate system.
 * The rectangle's sides are parallel to X- and Y-axis.
 * Mathematically, the rectangle can be written as
 * [left, left + width] x [top, top + bottom].
 */
public class Rectangle
{
    private long left;

    private long top;

    private long width;

    private long height;

    /**
     * Construct a new rectangle with given the coordinates of its top left
     * and bottom right corners.
     */
    public Rectangle(Point topLeft, Point bottomRight)
    {

    }

    /**
     * Create rectangle given the coordinates of its top left corner
     * and its dimensions.
     */
    public Rectangle(long left, long top, long width, long height)
    {

    }

    /**
     * Returns x-coordinate of the rectangle's left side.
     */
    public long getLeft()
    {
        return 0;
    }

    /**
     * Returns the y-coordinate of the rectangle's top side.
     * Note that since the Y-axis is pointing down,
     * the top side has a lower Y-coordinate than the bottom side.
     */
    public long getTop()
    {
        return 0;
    }

    /**
     * Returns the width of this rectangle.
     */
    public long getWidth()
    {
        return 0;
    }

    /**
     * Returns the height of this rectangle.
     */
    public long getHeight()
    {
        return 0;
    }

    /**
     * Returns the x-coordinate of the right side of this rectangle.
     */
    public long getRight()
    {
        return left + width;
    }

    /**
     * Returns the y-coordinate of the bottom side of this rectangle.
     */
    public long getBottom()
    {
        return top + height;
    }

    /**
     * Updates the x-coordinate of the left side of this rectangle.
     * The width of this rectangle remains unaffected, i.e., using this
     * method moves this rectangle horizontally.
     */
    public void setLeft(long left)
    {

    }

    /**
     * Updates the y-coordinate of the top side of this rectangle.
     * The height remains unaffected, i.e., using this method
     * moves the rectangle vertically.
     */
    public void setTop(long top)
    {

    }

    /**
     * Updates the width of this rectangle.
     * The width cannot be negative, but is allowed to be zero.
     */
    public void setWidth(long width)
    {

    }

    /**
     * Updates the height of this rectangle.
     * The height cannot be negative, but is allowed to be zero.
     */
    public void setHeight(long height)
    {

    }

    /** 
     * Return the top-left point of this rectangle
     */
    public Point getTopLeft()
    {
        return null;
    }

    /**
     * Return the bottom-right point of this rectangle
     */
    public Point getBottomRight()
    {
        return null;
    }

    /**
     * Returns whether given point is inside this rectangle.
     */
    public boolean contains(Point point)
    {
        return false;
    }

    /**
     * Return whether this rectangle contains a given circle.
     * 
     * LEGIT
     */
    public boolean contains(Circle circle)
    {
        return contains(circle.getBoundingRectangle());
    }

    /**
     * Return whether this rectangle contains a given other rectangle.
     */
    public boolean contains(Rectangle other)
    {
        return false;
    }

    /**
     * LEGIT
     */
    public Point getBottomCenter()
    {
        var x = left + width / 2;
        var y = top + height;

        return new Point(x, y);
    }

    /**
     * LEGIT
     */
    public Point getCenter()
    {
        var x = left + width / 2;
        var y = top + height / 2;

        return new Point(x, y);
    }

    /**
     * Returns a copy of this rectangle.
     */
    public Rectangle copy()
    {
        return null;
    }

    /**
     * Returns a new rectangle with same left, top and width properties.
     * The height equals this rectangle's height plus the given extra height.
     */
    public Rectangle growHeight(long extraHeight)
    {
        return null;
    }

    /**
     * LEGIT
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(left, top, width, height);
    }

    /**
     * LEGIT
     */
    @Override
    public boolean equals(Object obj)
    {
        if ( obj instanceof Rectangle that )
        {
            return this.left == that.left && this.top == that.top && this.width == that.width && this.height == that.height;
        }
        else
        {
            return false;
        }
    }

    /**
     * LEGIT
     */
    public String toString()
    {
        return String.format("Rectangle[left=%s, top=%s, width=%s, height=%s]", left, top, width, height);
    }
}
