package com.team2052.deepspace.auto.paths;

import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class VisionPath extends Path{

    public VisionPath(Direction direction, Position2d target) {
        setDirection(direction);

        Position2d startPoint = new Position2d();
        Position2d endPoint = new Position2d();
        startPoint.setLateral(target.getLateral() + 50 * target.sin());
        startPoint.setForward(target.getForward() + 50 * target.cos());
        endPoint.setLateral(target.getLateral() - 50 * target.sin());
        endPoint.setForward(target.getForward() - 50 * target.cos());


        addWaypoint(new Waypoint(startPoint, 50));
        addWaypoint(new Waypoint(target, 50));
        forceQuickOptimization();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
