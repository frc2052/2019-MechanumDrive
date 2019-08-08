package com.team2052.deepspace.auto.paths;

import com.team2052.deepspace.Constants;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class SmoothTestCompoundPath extends CompoundPath {

    public SmoothTestCompoundPath() {
        addPath(new FirstHatch());
        addPath(new SecondHatchPickUp());
        addPath(new SecondHatchReverse());
        addPath(new SecondHatchForward());
    }

    class FirstHatch extends Path {
        public FirstHatch() {
            setDirection(Direction.BACKWARD);
            addWaypoint(new Waypoint(new Position2d(0,-47), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(50,-47), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(110,-78),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(125,-83),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(142,-85),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(175,-85),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(188,-82),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(194,-58),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(194,-47),Constants.Autonomous.kAutoVelocity));
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
            addWaypoint(new Waypoint(new Position2d(190,-60  ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(175,-72  ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(60,-115 ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(40,-120 ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(10,-125 ),Constants.Autonomous.kAutoVelocity));
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
            addWaypoint(new Waypoint(new Position2d(-14,-122),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(0, -115 ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(40, -88 ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(55, -83 ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(75,-80  ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(200, -80 ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(220,-85  ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(230, -100 ),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(230,-120  ),Constants.Autonomous.kAutoVelocity));
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
            addWaypoint(new Waypoint(new Position2d(230,-120),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(230,-47  ),Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }
}
