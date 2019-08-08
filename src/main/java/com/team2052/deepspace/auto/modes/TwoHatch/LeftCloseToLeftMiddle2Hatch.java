package com.team2052.deepspace.auto.modes.TwoHatch;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.LeftTwoHatch.LCloseHatchBackup;
import com.team2052.deepspace.auto.paths.LeftTwoHatch.LCloseHatchStartLeft2HatchPickUpPath;
import com.team2052.deepspace.auto.paths.LeftTwoHatch.LHatchPickUpStartLeftMiddle2HatchPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class LeftCloseToLeftMiddle2Hatch extends AutoMode {

    public LeftCloseToLeftMiddle2Hatch(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new WaitAction(.15),
                new FollowPathAction(new LCloseHatchBackup()),
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, 180),
                new FollowPathAction(new LCloseHatchStartLeft2HatchPickUpPath()),
                new DriverControlledAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                new WaitAction(.15),
                new FollowPathAction(new LHatchPickUpStartLeftMiddle2HatchPath()),
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, 100),
                new DriverControlledAction(false)
        )));
    }
}
