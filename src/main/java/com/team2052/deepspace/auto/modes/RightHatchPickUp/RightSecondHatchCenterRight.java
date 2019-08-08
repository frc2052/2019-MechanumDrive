package com.team2052.deepspace.auto.modes.RightHatchPickUp;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.ParallelAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpBackUp;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class RightSecondHatchCenterRight extends AutoMode {
    public RightSecondHatchCenterRight(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                        new FollowPathAction(new RHatchPickUpBackUp(Path.Direction.BACKWARD))
                ))
        )));
    }
}
