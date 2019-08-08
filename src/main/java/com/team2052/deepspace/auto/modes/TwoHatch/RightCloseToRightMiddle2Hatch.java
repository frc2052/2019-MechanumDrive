package com.team2052.deepspace.auto.modes.TwoHatch;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.RightTwoHatch.RCloseHatchBackup;
import com.team2052.deepspace.auto.paths.RightTwoHatch.RCloseHatchStartRight2HatchPickUpPath;
import com.team2052.deepspace.auto.paths.RightTwoHatch.RHatchPickUpStartRightMiddle2HatchPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class RightCloseToRightMiddle2Hatch extends AutoMode {

    public RightCloseToRightMiddle2Hatch(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new WaitAction(.15),
                new FollowPathAction(new RCloseHatchBackup()),
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, -180),
                new FollowPathAction(new RCloseHatchStartRight2HatchPickUpPath()),
                new DriverControlledAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                new WaitAction(.15),
                new FollowPathAction(new RHatchPickUpStartRightMiddle2HatchPath()),
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, -110),
                new DriverControlledAction(false)
        )));
    }
}
