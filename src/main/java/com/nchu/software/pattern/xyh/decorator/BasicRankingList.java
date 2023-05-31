package com.nchu.software.pattern.xyh.decorator;

import com.nchu.software.pattern.xyh.singleton.GameRankingList;

import java.util.List;

/**
 * ClassName: BasicRankingList
 * Package: a.decorator
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 16:28
 * @Version: v1.0
 */
public class BasicRankingList implements RankingList {
    private List<GameRankingList> rankingList;

    public BasicRankingList(List<GameRankingList> rankingList) {
        this.rankingList = rankingList;
    }

    @Override
    public List<GameRankingList> getRankingList() {
        return rankingList;
    }
}
