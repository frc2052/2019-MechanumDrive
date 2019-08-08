package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class BackwardLeftToCenterLeft extends AutoMode {
    public BackwardLeftToCenterLeft(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.BACKWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartCenterLeftHatchPath(startingPos, Path.Direction.BACKWARD)),
                //Vision
                new VisionAction(false),
                // when true, ground outtake action
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),

                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                        new FollowPathAction(new CLeftHatchStartLeftHatchPickUpPath())
                )),

                new VisionAction(false),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE)
                //new WaitAction(1.0)

        )));
    }
}
