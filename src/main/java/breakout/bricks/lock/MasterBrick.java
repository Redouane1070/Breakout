package breakout.bricks.lock;

import java.awt.Color;
import java.util.ArrayList;

import breakout.BreakoutState;
import breakout.balls.Ball;
import breakout.bricks.Brick;
import breakout.math.Point;
import breakout.math.Rectangle;
import breakout.math.Vector;
import breakout.ui.Canvas;

/**
 * OGP ONLY CLASS
 * 
 */
public class MasterBrick extends Brick
{
    public static final Color COLOR = new Color(0, 255, 0);

    /**
     * @peerObjects
     * @representationObject     
     */
    final ArrayList<LockedBrick> lockedBricks;

    /**
     * Constructs a MasterBrick linked to the bricks in lockedBricks
     */
    public MasterBrick(Rectangle geometry, Point gridPosition, ArrayList<LockedBrick> lockedBricks)
    {
        super(geometry, gridPosition);
        this.lockedBricks = null;
    }

    /**
     * @post | result.equals(MasterBrick.COLOR)
     */
    @Override
    public Color getColor()
    {
        return COLOR;
    }

    @Override
    public void hit(BreakoutState state, Ball ball)
    {
        if ( this.lockedBricks.isEmpty() )
        {
            state.getBrickGrid().removeBrick(this);
        }
        else
        {
        	//new keyballs are generated at ball's position, with these speeds (you can cycle through this list if more than 4 need
        	//to be generated)
        	//Vector[] speeds = { new Vector(-5 , 30) , new Vector( -2 , 30) , new Vector (2,30) , new Vector(5,30) };
        }
    }
    
    
    @Override
    public boolean strongHit(BreakoutState state, Ball ball) {
    	return false;
    }


    /**
     * LEGIT
     * @inspects | canvas
     * @pre | canvas != null
     */
    @Override
    public void paint(Canvas canvas)
    {
        super.paint(canvas);

        String label = "🔑";
        canvas.drawLabel(getColor(), label, getGeometry().getCenter());
    }
    
    public ArrayList<LockedBrick> getLockedBricks() {
    	return lockedBricks;
    }
    
    
    public void linkLock(LockedBrick lbrick) {
		lockedBricks.add(lbrick);
		lbrick.masterBricks.add(this);
		var peerBalls = computePeerBalls();
		for (var pbal : peerBalls) {
			pbal.computeSpeedModifPkg();
		}
    }
    
    public void unlinkLock(LockedBrick lbrick) {
    	;
    }
    
    /**
     * Returns the list of key balls that are peer to this MasterBrick
     */
    private ArrayList<KeyBallBehavior> computePeerBalls() {
    	return null;
    }
    
    
    
    
    
    
}
