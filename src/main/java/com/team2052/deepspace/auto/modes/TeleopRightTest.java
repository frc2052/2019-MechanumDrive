package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.Teleop.RightReturnToHatch;
import com.team2052.deepspace.auto.paths.Teleop.RightToPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class TeleopRightTest extends AutoMode {

    public TeleopRightTest(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    @Override
    protected void init() {

        System.out.println("###########################################init###########################################");

        setAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new RightToPath(startingPos)),
                new FollowPathAction(new RightReturnToHatch())
        )));
    }
}
