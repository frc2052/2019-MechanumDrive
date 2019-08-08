package com.team2052.deepspace.auto.modes.TwoHatch;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.LeftTwoHatch.LStartSideLeftClose2HatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class LeftToLeftClose2Hatch extends AutoMode {

    public LeftToLeftClose2Hatch(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN),
                new FollowPathAction(new LStartSideLeftClose2HatchPath(startingPos, Path.Direction.FORWARD)),
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, 82),
                new DriverControlledAction(false),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
        )));
    }
}
