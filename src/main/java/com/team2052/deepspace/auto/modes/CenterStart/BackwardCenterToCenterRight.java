package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterRightHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class BackwardCenterToCenterRight extends AutoMode {
    public BackwardCenterToCenterRight(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.BACKWARD);

    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new CStartCenterRightHatchPath(startingPos, Path.Direction.BACKWARD)),
                //Vision
                new VisionAction(false),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                //Turns robot around and drives back towards loading station
                new ParallelAction(Arrays.asList(
                        new FollowPathListAction(new CRightHatchStartRightHatchPickUpPathCompoundPath().getPaths()),
                        new GroundIntakeAction(false)
                ))
        )));
    }
}
