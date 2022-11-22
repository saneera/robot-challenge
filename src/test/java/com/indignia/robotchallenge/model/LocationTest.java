package com.indignia.robotchallenge.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LocationTest {

    @Test
    public void shouldReturnNewPointByAddingToCurrentXY() {
        Location currentLocation = new Location(0, 0);

        Location actual1 = currentLocation.add(new Location(2, 3));
        assertThat(actual1).isEqualTo(new Location(2, 3));

        Location actual2 = currentLocation.add(new Location(1, 0));
        assertThat(actual2).isEqualTo(new Location(1, 0));
    }
}
