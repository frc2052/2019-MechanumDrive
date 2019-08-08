package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class ForwardLeftToCenterLeft extends AutoMode {
    public ForwardLeftToCenterLeft(Position2d startPos){
        super(startPos);
        setStartDirection(AutoMode.StartDirection.FORWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new SeriesAction(Arrays.asList(
                                new WaitAction(1.4),
                                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN)
                        )),
                        new FollowPathAction(new LStartCenterLeftHatchPath(startingPos, Path.Direction.FORWARD))
                )),
                //Vision
                new ParallelWaitAction(Arrays.asList(
                        new VisionAction(true)
                ), new DriverButtonAction()),
                // when true, ground outtake action
                //new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
//                //Turns robot around and drives back towards loading station
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                        new FollowPathListAction(new CLeftHatchStartLeftHatchPickUpPathCompoundPath().getPaths())
                )),

                new ParallelWaitAction(Arrays.asList(
                        new VisionAction(true)
                ), new DriverButtonAction()),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE)
                //new WaitAction(1.0)
        )));
    }
}
