package breakout.math;

import java.util.Objects;

/**
 * This class represents a point on a 2-dimensional integer grid.
 */
public class Point
{
    private final long x;
    private final long y;

    /**
     * Return a new Point with given x and y coordinates.
     */
    public Point(long x, long y)
    {
        this.x = x;
        this.y = y;
    }

    /** Return this point's x coordinate. */
    public long x()
    {
        return x;
    }

    /** Return this point's y coordinate. */
    public long y()
    {
        return y;
    }

    /**
     * Return the point obtained by adding vector `v` to this point.
     */
    public Point add(Vector v)
    {
        return new Point(x + v.x(), y + v.y());
    }

    /**
     * Return the point obtained by adding vector `v` to this point.
     */
    public Point subtract(Vector v)
    {
        return null;
    }

    public Point moveDown(int dy)
    {
        return new Point(x, y + dy);
    }

    public Point moveUp(int dy)
    {
        return new Point(x, y - dy);
    }

    public Point moveLeft(int dx)
    {
        return new Point(x - dx, y);
    }

    public Point moveRight(int dx)
    {
        return new Point(x + dx, y);
    }

    /**
     * LEGIT
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    /**
     * LEGIT
     */
    @Override
    public boolean equals(Object obj)
    {
        if ( obj instanceof Point that )
        {
            return this.x == that.x && this.y == that.y;
        }
        else
        {
            return false;
        }
    }

    /**
     * Return a string representation of this point.
     *
     * LEGIT
     */
    @Override
    public String toString()
    {
        return "(" + x + "," + y + ")";
    }
}
