package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LCloseHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class BackwardLeftToLeftClose extends AutoMode {

    public BackwardLeftToLeftClose(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.BACKWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new LStartSideLeftCloseHatchPath(startingPos, Path.Direction.BACKWARD)),
                //Vision
                new VisionAction(false),
                // when true, ground outtake action
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new WaitAction(.5),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN),

//                //Turns robot around and drives back towards loading station

                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                        new FollowPathAction(new LCloseHatchStartLeftHatchPickUpPath(Path.Direction.FORWARD))
                )),

                new VisionAction(false),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                new WaitAction(1.0)
        )));
    }
}
