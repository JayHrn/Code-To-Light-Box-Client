package com.nchu.software.model;

import lombok.Data;

/**
 * @Author JayHrn
 * @Date 2023-05-09 13:54
 * @Description 地图
 */
@Data
public class Map {
    private int[][] mapBlocks;
    private Position startPosition;

    public Map(int[][] mapBlocks, Position startPosition) {
        this.mapBlocks = mapBlocks;
        this.startPosition = startPosition;
    }
}
