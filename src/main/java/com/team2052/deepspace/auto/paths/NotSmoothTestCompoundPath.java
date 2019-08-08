package com.team2052.deepspace.auto.paths;

import com.team2052.deepspace.Constants;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class NotSmoothTestCompoundPath extends CompoundPath {

    public NotSmoothTestCompoundPath() {
        addPath(new FirstHatch());
        addPath(new SecondHatchPickUp());
        addPath(new SecondHatchReverse());
        addPath(new SecondHatchForward());
    }

    class FirstHatch extends Path {
        public FirstHatch() {
            setDirection(Direction.BACKWARD);
            addWaypoint(new Waypoint(new Position2d(0,-47),60));
            addWaypoint(new Waypoint(new Position2d(50,-47), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(170,-65),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(194,-65),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(194,-55),Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class SecondHatchPickUp extends Path {
        public SecondHatchPickUp() {
            setDirection(Direction.FORWARD);
            addWaypoint(new Waypoint(new Position2d(194,-47),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(29,-125  ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(-30,-125  ),Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class SecondHatchReverse extends Path {
        public SecondHatchReverse() {
            setDirection(Direction.BACKWARD);
            addWaypoint(new Waypoint(new Position2d(-30,-125),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(60,-70),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(216,-60),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(216,-80  ),Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class SecondHatchForward extends Path {
        public SecondHatchForward() {
            setDirection(Direction.FORWARD);
            addWaypoint(new Waypoint(new Position2d(216,-80),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(216,-55  ),Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}
