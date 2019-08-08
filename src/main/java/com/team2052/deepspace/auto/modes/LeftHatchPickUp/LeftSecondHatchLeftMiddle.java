package com.team2052.deepspace.auto.modes.LeftHatchPickUp;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.actions.WaitAction;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftMiddleHatchPathCompoundPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class LeftSecondHatchLeftMiddle extends AutoMode {
    public LeftSecondHatchLeftMiddle(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                new WaitAction(.25),
                new FollowPathListAction(new LHatchPickUpStartLeftMiddleHatchPathCompoundPath().getPaths())
        )));
    }
}
