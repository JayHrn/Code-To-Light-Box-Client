package com.nchu.software.model;

import com.nchu.software.pattern.factory.BlockFactory;
import javafx.scene.Scene;
import lombok.Data;

/**
 * @Author JayHrn
 * @Date 2023-05-09 18:02
 * @Description 地图 外观模式，同时进行绘制地图
 */
@Data
public class MapFacade {
    private Map map;
    private BlockFactory blockFactory;

    public MapFacade(int[][] mapBlocks, Position startPosition) {
        this.map = new Map(mapBlocks, startPosition);
        blockFactory = new BlockFactory();
    }
}
