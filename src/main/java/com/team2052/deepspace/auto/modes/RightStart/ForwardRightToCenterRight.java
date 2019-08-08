package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightStart.RStartCenterRightHatchPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class ForwardRightToCenterRight extends AutoMode {
    public ForwardRightToCenterRight(Position2d startPos){
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
                        new FollowPathAction(new RStartCenterRightHatchPath(startingPos, Path.Direction.FORWARD))
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
                        new FollowPathListAction(new CRightHatchStartRightHatchPickUpPathCompoundPath().getPaths())
                )),

                new ParallelWaitAction(Arrays.asList(
                        new VisionAction(true)
                ), new DriverButtonAction()),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE)
        )));
    }
}
