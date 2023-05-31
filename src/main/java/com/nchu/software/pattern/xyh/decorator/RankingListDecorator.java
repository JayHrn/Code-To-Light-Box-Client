package com.nchu.software.pattern.xyh.decorator;


import com.nchu.software.pattern.xyh.singleton.GameRankingList;

import java.util.List;

/**
 * ClassName: RankingListDecorator
 * Package: a.decorator
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 16:29
 * @Version: v1.0
 */
public abstract class RankingListDecorator implements RankingList {
    protected RankingList decoratedRankingList;

    public RankingListDecorator(RankingList decoratedRankingList) {
        this.decoratedRankingList = decoratedRankingList;
    }

    @Override
    public List<GameRankingList> getRankingList() {
        return decoratedRankingList.getRankingList();
    }
}
