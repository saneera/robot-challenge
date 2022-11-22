package com.indignia.robotchallenge.config;

import com.indignia.robotchallenge.model.Location;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "table-config")
public class TableTopConfig {
    private int rows;
    private int columns;


    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public boolean containsPoint(Location location) {
        return location.x() >= 0 && location.x() < columns &&
                location.y() >= 0 && location.y() < rows;
    }
}
