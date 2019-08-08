package com.team2052.deepspace.auto.modes.LeftHatchPickUp;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftCloseHatchCompoundPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class LeftSecondHatchLeftClose extends AutoMode {
    public LeftSecondHatchLeftClose(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                        new FollowPathListAction(new LHatchPickUpStartLeftCloseHatchCompoundPath().getPaths())
                )),
                new VisionAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
        )));
    }
}
