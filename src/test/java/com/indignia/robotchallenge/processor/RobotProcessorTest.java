package com.indignia.robotchallenge.processor;

import com.indignia.robotchallenge.config.TableTopConfig;
import com.indignia.robotchallenge.model.Direction;
import com.indignia.robotchallenge.model.Location;
import com.indignia.robotchallenge.model.Robot;
import com.indignia.robotchallenge.model.RotationDirection;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RobotProcessorTest {

    private Robot currentRobot;

    private TableTopConfig tableTopConfig;

    private RobotProcessor robotProcessor;

    @BeforeEach
    public void setUp() {
        tableTopConfig = createTableTopConfig();
        currentRobot = new Robot();
        robotProcessor = new RobotProcessor(currentRobot, tableTopConfig);
    }

    @Test
    public void testRobotMoveButIsNotInTheTable() {
        robotProcessor.move();
        assertThat(robotProcessor.report()).isEmpty();
    }

    @Test
    public void testRobotRotateLeftButIsNotInTheTable() {
        robotProcessor.rotate(RotationDirection.LEFT);
        assertThat(robotProcessor.report()).isEmpty();
    }

    @Test
    public void testRobotRotateRightButIsNotInTheTable() {
        robotProcessor.rotate(RotationDirection.RIGHT);
        assertThat(robotProcessor.report()).isEmpty();
    }

    @Test
    public void testRobotStartingPositionIsOutSide() {
        robotProcessor.place(new Location(1, -1), Direction.NORTH);
        assertThat(robotProcessor.report()).isEmpty();
    }

    /*
    PLACE 0,0,NORTH
    MOVE
    REPORT

    Output: 0,1,NORTH
    */

    @Test
    public void testRobotPlaceNorthAndMoveAndReport() throws Exception {
        Location location = new Location(0, 0);
        Direction facing = Direction.NORTH;
        robotProcessor.place(location, facing);
        robotProcessor.move();
        robotProcessor.report();
        assertThat(currentRobot.getLocation().x()).isEqualTo(0);
        assertThat(currentRobot.getLocation().y()).isEqualTo(1);
        assertThat(currentRobot.getDirection()).isEqualTo(Direction.NORTH);
    }

    /*
    PLACE 0,0,NORTH
    LEFT
    REPORT

    Output: 0,0,WEST

     */
    @Test
    public void testRobotPlaceNorthAndLeftAndReport() throws Exception {
        Location location = new Location(0, 0);
        Direction facing = Direction.NORTH;
        robotProcessor.place(location, facing);
        robotProcessor.rotate(RotationDirection.LEFT);
        assertThat(currentRobot.getLocation().x()).isEqualTo(0);
        assertThat(currentRobot.getLocation().y()).isEqualTo(0);
        assertThat(currentRobot.getDirection()).isEqualTo(Direction.WEST);
    }

    /*
    PLACE 1,2,EAST
    MOVE
    MOVE
    LEFT
    MOVE
    REPORT

    Output: 3,3,NORTH
     */
    @Test
    public void testRobotPlaceEastAndMOveAndMOveAndLeftAndMoveAndReport() throws Exception {
        Location location = new Location(1, 2);
        Direction facing = Direction.EAST;
        robotProcessor.place(location, facing);
        robotProcessor.move();
        robotProcessor.move();
        robotProcessor.rotate(RotationDirection.LEFT);
        robotProcessor.move();
        robotProcessor.report();
        assertThat(currentRobot.getLocation().x()).isEqualTo(3);
        assertThat(currentRobot.getLocation().y()).isEqualTo(3);
        assertThat(currentRobot.getDirection()).isEqualTo(Direction.NORTH);
    }


    private TableTopConfig createTableTopConfig() {
        TableTopConfig tableTopConfig = new TableTopConfig();
        tableTopConfig.setRows(5);
        tableTopConfig.setColumns(5);
        return tableTopConfig;
    }
}
