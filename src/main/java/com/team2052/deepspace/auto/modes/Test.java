package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.actions.TurnInPlaceAction;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.NotSmoothTestCompoundPath;
import com.team2052.deepspace.auto.paths.SmoothTestCompoundPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class Test extends AutoMode {

    public Test(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    CompoundPath p1 = new NotSmoothTestCompoundPath();
    CompoundPath p2 = new SmoothTestCompoundPath();

    @Override
    protected void init() {

        System.out.println("###########################################init###########################################");

       setAction(new SeriesAction(Arrays.asList(
                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC,-90)

//        new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, 90),
//                new DriverControlledAction(false),
//                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, 0)

        )));
    }
}
