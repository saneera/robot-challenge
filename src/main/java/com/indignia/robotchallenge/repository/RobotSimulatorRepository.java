package com.indignia.robotchallenge.repository;

import com.indignia.robotchallenge.model.Robot;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class RobotSimulatorRepository {

    private List<Robot> list = new ArrayList<Robot>();

    public int getNextSequence() {
        return list.size()+1;
    }

    public void save(Robot robot) {
        var existing = list.stream().filter(row -> row.getId() == robot.getId()).findFirst();
        if(existing.isPresent()) {
            list.stream().map(row -> {
                if(row.getId() == robot.getId()) {
                    return robot;
                } else {
                    row.setActive(false);
                    return row;
                }
            }).collect(Collectors.toList());
        } else {
            list.stream().map(row -> {
                    row.setActive(false);
                    return row;
            }).collect(Collectors.toList());
            list.add(robot);
        }
    }

    public Optional<Robot> getRobotById(int id) {
        return list.stream().filter(row -> row.getId() == id).findFirst();
    }

    public List<Robot> getAll() {
        return list;
    }

}
