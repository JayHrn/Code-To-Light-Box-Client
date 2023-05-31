package com.nchu.software.pattern.xyh.decorator;

import com.nchu.software.pattern.xyh.singleton.GameRankingList;

import java.util.List;

/**
 * ClassName: RankingList
 * Package: a.decorator
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 16:28
 * @Version: v1.0
 */
public interface RankingList {
    List<GameRankingList> getRankingList();
}
