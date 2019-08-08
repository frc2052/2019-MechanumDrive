package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpBackUp;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseRocketPath;
import com.team2052.deepspace.auto.paths.LeftStartRocket.LRocketCloseStartHatchPickUpCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class ForwardLeftToRocketClose extends AutoMode {
    public ForwardLeftToRocketClose(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN),
                        new FollowPathAction(new LStartSideLeftCloseRocketPath(startingPos, Path.Direction.FORWARD))
                )),
                //Vision
                new VisionAction(true),
                // when true, ground outtake action
                //new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
//                //Turns robot around and drives back towards loading station
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                        new FollowPathListAction(new LRocketCloseStartHatchPickUpCompoundPath().getPaths())
                )),

                new VisionAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE)
        )));
    }
}
