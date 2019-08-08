package com.team2052.deepspace.auto.paths.Teleop;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LeftToPath extends Path{

    public LeftToPath(Position2d startPos) {
        setDirection(Direction.FORWARD);
        isHighGear = false;
        addWaypoint(new Waypoint(startPos,100));
        addWaypoint(new Waypoint(new Position2d(120,-80), Constants.Autonomous.kHighGearAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(100,-100), Constants.Autonomous.kHighGearAutoVelocity));
        forceQuickOptimization();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
