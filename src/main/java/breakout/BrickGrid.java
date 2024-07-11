package breakout;

import java.util.ArrayList;
import java.util.stream.Stream;

import breakout.balls.Ball;
import breakout.bricks.Brick;
import breakout.bricks.GrowPaddleBrick;
import breakout.bricks.ShrinkPaddleBrick;
import breakout.bricks.SlowDownBrick;
import breakout.bricks.SpeedUpBrick;
import breakout.bricks.StandardBrick;
import breakout.bricks.StrengtheningBrick;
import breakout.bricks.SturdyBrick;
import breakout.bricks.WeakeningBrick;
import breakout.bricks.lock.LockedBrick;
import breakout.bricks.lock.MasterBrick;
import breakout.math.Point;
import breakout.math.Rectangle;
import breakout.math.Vector;
import breakout.util.Grid;
import breakout.util.SpecUtil;

public class BrickGrid
{
    private final Grid<Brick> grid;

    private final int brickWidth;

    private final int brickHeight;

    public BrickGrid(int columnCount, int rowCount, int brickWidth, int brickHeight)
    {
        this.brickWidth = brickWidth;
        this.brickHeight = brickHeight;
        this.grid = new Grid<Brick>(columnCount, rowCount);
    }

    public int getBrickWidth()
    {
        return 0;
    }

    public int getBrickHeight()
    {
        return 0;
    }

    public int getColumnCount()
    {
        return 0;
    }

    public int getRowCount()
    {
        return 0;
    }

    public int getWidth()
    {
        return this.getColumnCount() * this.brickWidth;
    }

    public int getHeight()
    {
        return this.getRowCount() * this.brickHeight;
    }

    public Brick getBrickAt(Point gridPosition)
    {
        return null;
    }

    public boolean isValidGridPosition(Point gridPosition)
    {
        return this.grid.isValidPosition(gridPosition);
    }

    /**
     * Returns the brick at the given {@code gridPosition} if this {@code gridPosition}
     * falls within the borders of the playing field. If not, {@code null} is returned.
     */
    public Brick getBrickAtGridPositionOrNull(Point gridPosition)
    {
        if ( this.grid.isValidPosition(gridPosition) )
        {
            return this.getBrickAt(gridPosition);
        }
        else
        {
            return null;
        }
    }

    /**
     * LEGIT
     */
    public BrickCollision findEarliestCollision(Ball ball)
    {
        var earliestHorizontalCollision = findEarliestHorizontalCollision(ball);
        var earliestVerticalCollision = findEarliestVerticalCollision(ball);

        return Collision.getEarliestCollision(earliestHorizontalCollision, earliestVerticalCollision);
    }

    /**
     * LEGIT
     */
    private BrickCollision findEarliestVerticalCollision(Ball ball)
    {
        if ( ball.getVelocity().y() < 0 )
        {
            return findEarliestUpwardsCollision(ball);
        }
        else if ( ball.getVelocity().y() > 0 )
        {
            return findEarliestDownwardsCollision(ball);
        }
        else
        {
            return null;
        }
    }

    /**
     * LEGIT
     */
    private BrickCollision findEarliestHorizontalCollision(Ball ball)
    {
        if ( ball.getVelocity().x() < 0 )
        {
            return findEarliestLeftwardsCollision(ball);
        }
        else if ( ball.getVelocity().x() > 0 )
        {
            return findEarliestRightwardsCollision(ball);
        }
        else
        {
            return null;
        }
    }

    /**
     * LEGIT
     */
    private BrickCollision findEarliestUpwardsCollision(Ball ball)
    {
        var v = ball.getVelocity();
        var p = ball.getGeometry().getPointInDirection(v);
        var y = p.y() / this.brickHeight * this.brickHeight;

        while ( y > 0 )
        {
            var preciseT = (y - p.y()) * 1000 / v.y();
            var x = p.x() + v.x() * preciseT / 1000;
            var brickGridPosition = new Point(Math.floorDiv(x, brickWidth), Math.floorDiv(y, brickHeight) - 1);
            var brick = getBrickAtGridPositionOrNull(brickGridPosition);

            if ( brick != null )
            {
                return new BrickCollision(preciseT / 1000, Vector.KILO_DOWN, brick);
            }

            y -= this.brickHeight;
        }

        return null;
    }

    /**
     * LEGIT
     */
    private BrickCollision findEarliestDownwardsCollision(Ball ball)
    {
        var v = ball.getVelocity();
        var p = ball.getGeometry().getPointInDirection(v);
        var y = (p.y() + this.brickHeight - 1) / this.brickHeight * this.brickHeight;
        var yMax = this.getHeight();

        while ( y < yMax )
        {
            var preciseT = (y - p.y()) * 1000 / v.y();
            var x = p.x() + v.x() * preciseT / 1000;
            var brickGridPosition = new Point(Math.floorDiv(x, brickWidth), Math.floorDiv(y, brickHeight));
            var brick = getBrickAtGridPositionOrNull(brickGridPosition);

            if ( brick != null )
            {
                return new BrickCollision(preciseT / 1000, Vector.KILO_UP, brick);
            }

            y += this.brickHeight;
        }

        return null;
    }

    /**
     * LEGIT
     */
    private BrickCollision findEarliestLeftwardsCollision(Ball ball)
    {
        var v = ball.getVelocity();
        var p = ball.getGeometry().getPointInDirection(v);
        var x = p.x() / this.brickWidth * this.brickWidth;

        while ( x > 0 )
        {
            var preciseT = (x - p.x()) * 1000 / v.x();
            var y = p.y() + v.y() * preciseT / 1000;
            var brickGridPosition = new Point(Math.floorDiv(x, brickWidth) - 1, Math.floorDiv(y, brickHeight));
            var brick = getBrickAtGridPositionOrNull(brickGridPosition);

            if ( brick != null )
            {
                return new BrickCollision(preciseT / 1000, Vector.KILO_RIGHT, brick);
            }

            x -= this.brickWidth;
        }

        return null;
    }

    /**
     * LEGIT
     */
    private BrickCollision findEarliestRightwardsCollision(Ball ball)
    {
        var v = ball.getVelocity();
        var p = ball.getGeometry().getPointInDirection(v);
        var x = (p.x() + this.brickWidth - 1) / this.brickWidth * this.brickWidth;
        var xMax = this.getWidth();

        while ( x < xMax )
        {
            var preciseT = (x - p.x()) * 1000 / v.x();
            var y = p.y() + v.y() * preciseT / 1000;
            var brickGridPosition = new Point(Math.floorDiv(x, brickWidth), Math.floorDiv(y, brickHeight));
            var brick = getBrickAtGridPositionOrNull(brickGridPosition);

            if ( brick != null )
            {
                return new BrickCollision(preciseT / 1000, Vector.KILO_LEFT, brick);
            }

            x += this.brickWidth;
        }

        return null;
    }

    /**
     * Checks whether there is a brick at the given position.
     * This method returns {@code false} for positions outside the grid.
     */
    public boolean containsBrickAt(Point gridPosition)
    {
        return this.getBrickAtGridPositionOrNull(gridPosition) != null;
    }

    public StandardBrick addStandardBrick(Point gridPosition)
    {
        var rectangle = getBrickRectangle(gridPosition);
        var brick = new StandardBrick(rectangle, gridPosition);
        this.grid.setAt(gridPosition, brick);
        return brick;
    }

    public SturdyBrick addSturdyBrick(Point gridPosition, int lives)
    {
        return null;
    }

    public GrowPaddleBrick addGrowPaddleBrick(Point gridPosition)
    {
        return null;
    }

    public ShrinkPaddleBrick addShrinkPaddleBrick(Point gridPosition)
    {
        return null;
    }

    public WeakeningBrick addWeakeningBrick(Point gridPosition)
    {
        return null;
    }

    public StrengtheningBrick addStrengtheningBrick(Point gridPosition)
    {
        return null;
    }

    public SpeedUpBrick addSpeedUpBrick(Point gridPosition)
    {
        return null;
    }

    public SlowDownBrick addSlowDownBrick(Point gridPosition)
    {
        return null;
    }

    /**
     * For OGP students only; can be ignored by OOP-students.
     */
    public MasterBrick addMasterBrick(Point gridPosition, ArrayList<LockedBrick> lockedBricks)
    {
        return null;
    }

    /**
     * For OGP students only; can be ignored by OOP-students.
     */
    public LockedBrick addLockedBrick(Point gridPosition)
    {
        return null;
    }

    public Rectangle getBrickRectangle(Point gridCoordinates)
    {
        var left = gridCoordinates.x() * brickWidth;
        var top = gridCoordinates.y() * brickHeight;

        return new Rectangle(left, top, brickWidth, brickHeight);
    }

    /**
     * Checks whether there are any bricks left.
     */
    public boolean isEmpty()
    {
        return !grid.getPositionStream().anyMatch(this::containsBrickAt);
    }

    /**
     * Returns all bricks from the grid in a list.
     */
    public ArrayList<Brick> getBricks()
    {
        return null;
    }

    /**
     * Returns the smallest rectangle that encompasses the entire grid.
     */
    public Rectangle getBoundingRectangle()
    {
        return new Rectangle(0, 0, getWidth(), getHeight());
    }

    /**
     * Removes the brick at the given {@code gridPosition}.
     */
    public void removeBrickAt(Point gridPosition)
    {
    }

    /**
     * Removes the brick from the grid.
     */
    public void removeBrick(Brick brick)
    {
        // Hint: brick.getGridPosition()
    }

    /**
     * LEGIT
     */
    public Stream<Point> enumerateGridPositions()
    {
        return this.grid.getPositionStream();
    }
}
