package breakout.bricks.lock;

import java.awt.Color;
import java.util.ArrayList;

import breakout.BreakoutState;
import breakout.balls.Ball;
import breakout.balls.StandardBehavior;
import breakout.bricks.Brick;
import breakout.math.Circle;
import breakout.math.Point;
import breakout.math.Rectangle;
import breakout.math.Vector;
import breakout.ui.Canvas;

/**
 * OGP ONLY CLASS
 * 
 */
public class LockedBrick extends Brick
{
    public static final Color COLOR = new Color(255, 0, 0);

    /**
     * @peerObjects
     * @representationObject
     */
    final ArrayList<MasterBrick> masterBricks;

    KeyBallBehavior keyBallBehavior;

    /**
     * Constructs a LockedBrick with no peers
     */
    public LockedBrick(Rectangle geometry, Point gridPosition)
    {
        super(geometry, gridPosition);
        this.masterBricks = null;
    }

    @Override
    public Color getColor()
    {
        return COLOR;
    }

    @Override
    public void hit(BreakoutState state, Ball ball)
    {
        //hint: if this LockedBrick is destroyed this can affect the speedMofidier field of several KeyBallBehavior's.
    }

    @Override
    public boolean strongHit(BreakoutState state, Ball ball)
    {
        return false;
    }



    @Override
    /**
     * LEGIT
     */
    public void paint(Canvas canvas)
    {
        super.paint(canvas);

        String label = "🔒";
        canvas.drawLabel(getColor(), label, getGeometry().getCenter());
    }
    
    public ArrayList<MasterBrick> getMasterBricks() {
    	return masterBricks;
    }
    
    public KeyBallBehavior getKeyBallBehavior() {
    	return keyBallBehavior;
    }
    
    /**
     * LEGIT
     * If getKeyBallBehavior == null, creates a new one
     * The behavior corresponds to a key ball spawned at the bottom right corner
     * of the game field (within it).
     */
    public void spawnKeyBall(Rectangle gameField) {
    	Point pos = gameField.getBottomRight().add(new Vector(-1,-1));
    	keyBallBehavior = new KeyBallBehavior(this);
    	Ball bal = new Ball(gameField,new Circle(pos, 500), new Vector(0,0),keyBallBehavior);
    }
    
    
    
    
    
}
