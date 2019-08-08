package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RCloseHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightCloseHatchPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class BackwardRightToRightClose extends AutoMode {

    public BackwardRightToRightClose(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.BACKWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new RStartSideRightCloseHatchPath(startingPos, Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(false),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                new ParallelAction(Arrays.asList(
                        //Turns robot around and drives back towards loading station
                        new FollowPathAction(new RCloseHatchStartRightHatchPickUpPath()),
                        new GroundIntakeAction(false)
                ))
        )));
    }
}
