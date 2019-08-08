package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.LineUpAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class BackwardCenterToCenterLeft extends AutoMode {

    public BackwardCenterToCenterLeft(Position2d startPos) {
        super(startPos);
        setStartDirection(StartDirection.BACKWARD);
    }

    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new CStartCenterLeftHatchPath(startingPos, Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(false),
                // when true, ground outtake action
                new GroundIntakeAction(true)
//                //Turns robot around and drives back towards loading station
//                new ParallelAction(Arrays.asList(
//                        new FollowPathListAction(new CLeftHatchStartLeftHatchPickUpPathCompoundPath().getPaths()),
//                        new GroundIntakeAction(false)
//                ))
        )));
    }
}
