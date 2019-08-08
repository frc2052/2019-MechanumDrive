package com.team2052.deepspace.auto.modes.TwoHatch;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartLeftHatchPickUpPathTwoHatchCompoundPath;
import com.team2052.deepspace.auto.paths.LeftTwoHatch.LHatchPickUpStartCenterLeftTwoHatchPathCompoundPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class CenterRightToCenterLeft2Hatch extends AutoMode {

    public CenterRightToCenterLeft2Hatch(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    @Override
    protected void init() {

        System.out.println("###########################################init###########################################");

        setAction(new SeriesAction(Arrays.asList(
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new WaitAction(.15),
                new FollowPathListAction(new CRightHatchStartLeftHatchPickUpPathTwoHatchCompoundPath().getPaths()),
                new DriverControlledAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                new WaitAction(.15),
                new FollowPathListAction(new LHatchPickUpStartCenterLeftTwoHatchPathCompoundPath().getPaths()),
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDNONTURNSPECIFIC, -10),
                new DriverControlledAction(false)
        )));
    }
}
