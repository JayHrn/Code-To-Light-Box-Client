package com.nchu.software.pattern.xyh.decorator;

import com.nchu.software.pattern.xyh.singleton.GameRankingList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ClassName: SortByTimeDecorator
 * Package: a.decorator
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 16:29
 * @Version: v1.0
 */
public class SortByTimeDecorator extends RankingListDecorator {
    public SortByTimeDecorator(RankingList decoratedRankingList) {
        super(decoratedRankingList);
    }

    @Override
    public List<GameRankingList> getRankingList() {
        List<GameRankingList> rankingList = decoratedRankingList.getRankingList();
        Collections.sort(rankingList, Comparator.comparing(GameRankingList::getTime));
        return rankingList;
    }
}
