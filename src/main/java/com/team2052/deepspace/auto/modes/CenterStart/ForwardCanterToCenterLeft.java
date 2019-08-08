package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CenterBackupPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class ForwardCanterToCenterLeft extends AutoMode {

    public ForwardCanterToCenterLeft(Position2d startPos) {
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN),
                        //Starting path starts going backwards
                        new FollowPathAction(new CStartCenterLeftHatchPath(startingPos, Path.Direction.FORWARD))
                )),
                //Vision
                new ParallelWaitAction(Arrays.asList(
                        new VisionAction(true)
                ), new DriverButtonAction()),
                // when true, ground outtake action
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
//                //Turns robot around and drives back towards loading station
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                        new FollowPathAction(new CenterBackupPath())
                ))
        )));
    }
}
