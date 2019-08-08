package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.Teleop.LeftReturnToHatch;
import com.team2052.deepspace.auto.paths.Teleop.LeftToPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class TeleopLeftTest extends AutoMode {

    public TeleopLeftTest(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    @Override
    protected void init() {

        System.out.println("###########################################init###########################################");

        setAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new LeftToPath(startingPos)),
                new FollowPathAction(new LeftReturnToHatch())
        )));
    }
}
