package com.indignia.robotchallenge.processor;

import com.indignia.robotchallenge.config.TableTopConfig;
import com.indignia.robotchallenge.model.Robot;
import com.indignia.robotchallenge.model.Direction;
import com.indignia.robotchallenge.model.Location;
import com.indignia.robotchallenge.model.RotationDirection;
import com.indignia.robotchallenge.model.RobotPosition;

import java.util.Optional;

/**
 * Process the command for current robot
 */
public class RobotProcessor {

    private Robot currentRobot;

    private TableTopConfig tableTopConfig;

    public Robot getCurrentRobot() {
        return currentRobot;
    }

    public void setCurrentRobot(Robot currentRobot) {
        this.currentRobot = currentRobot;
    }

    public RobotProcessor(Robot currentRobot, TableTopConfig tableTopConfig) {
        this.currentRobot = currentRobot;
        this.tableTopConfig = tableTopConfig;
    }

    public void place(Location location, Direction direction) {
        if (tableTopConfig.containsPoint(location)) {
            currentRobot.setLocation(location);
            currentRobot.setDirection(direction);
        }
    }
    public void move() {
        if (isPresent()) {
            Location latest = currentRobot.getLocation().add(currentRobot.getDirection().getUnit());
            if (tableTopConfig.containsPoint(latest)) {
                currentRobot.setLocation(latest);
            }
        }
    }

    private boolean isPresent() {
        return currentRobot.getLocation() != null && currentRobot.getDirection() != null;
    }

    public void rotate(RotationDirection rotation) {
        if (isPresent()) {
            int rotatedDegree = currentRobot.getDirection().getDegree() + rotation.getAngle();
            currentRobot.setDirection(Direction.of(rotatedDegree, rotation.getAngle()));
        }
    }

    public Optional<RobotPosition> report() {
        return isPresent() ? Optional.of(new RobotPosition(currentRobot.getLocation(), currentRobot.getDirection())) : Optional.empty();
    }
}
