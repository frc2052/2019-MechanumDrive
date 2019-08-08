package com.team2052.deepspace.auto.paths;

import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class ForwardPath extends Path{

    public ForwardPath(Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(new Position2d(0,0),20));
        addWaypoint(new Waypoint(new Position2d(12,0),20, "down"));
        addWaypoint(new Waypoint(new Position2d(24,0),20));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
