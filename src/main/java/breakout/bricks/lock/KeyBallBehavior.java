package breakout.bricks.lock;

import java.awt.Color;

import breakout.BreakoutState;
import breakout.BrickCollision;
import breakout.Collision;
import breakout.balls.Ball;
import breakout.balls.BallBehavior;
import breakout.math.Vector;
import breakout.ui.Canvas;

/**
 * OGP ONLY CLASS
 * 
 */
public class KeyBallBehavior extends BallBehavior
{

    LockedBrick lockedBrick;

    int speedModifier;

    public KeyBallBehavior(LockedBrick brick)
    {
        this.lockedBrick = brick;
        lockedBrick.keyBallBehavior = this;
        this.speedModifier = 0;
    }

   
    @Override
    public void bounceOffBrick(BreakoutState state, Ball ball, BrickCollision collision)
    {
        ball.move(collision.getMillisecondsUntilCollision());
    }

    @Override
    public void bounceOffWall(BreakoutState state, Ball ball, Collision collision)
    {
        ;
    }

    @Override
    /**
     * LEGIT
     */
    public void paint(Canvas canvas, Ball ball)
    {
        super.paint(canvas, ball);

        canvas.drawLine(Color.RED, lockedBrick.getGeometry().getCenter(), ball.getCenter());
        canvas.drawLabel(Color.WHITE, ""+speedModifier, ball.getCenter().add(new Vector(0,1000)));
    }

    @Override
    public Color getColor()
    {
        return Color.RED;
    }

    @Override
    public void ballLost(BreakoutState state, Ball ball)
    {
        super.ballLost(state, ball);
        lockedBrick.keyBallBehavior = null;
    }
    
    /**
     * see formula in assignment
     */
    int computeSpeedModifPkg() {
    	//computing sign.
    	int sign = 0;
    	if (lockedBrick.masterBricks.size()%2 == 0)
    		{ sign = -1; }
    	else
    		{sign = 1; }
    	//computing abs
    	int abs = 0;
    	for (MasterBrick mbrick : lockedBrick.masterBricks) {
    		if (mbrick.lockedBricks.size() > abs) { abs = mbrick.lockedBricks.size(); } 
    	}
    	return sign * abs;
    	
    	
    }
    
    
    public LockedBrick getLockedBrick() {
    	return lockedBrick;
    }
    
    /**
     * LEGIT
     */
    public int getSpeedModifier() {
    	return speedModifier;
    }
    
    
    
}
