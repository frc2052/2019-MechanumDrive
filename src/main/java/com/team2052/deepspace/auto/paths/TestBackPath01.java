package com.team2052.deepspace.auto.paths;

import com.team2052.deepspace.Constants;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class TestBackPath01 extends Path{

    public TestBackPath01(Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(new Position2d(194,65), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(194,80),Constants.Autonomous.kAutoVelocity));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
