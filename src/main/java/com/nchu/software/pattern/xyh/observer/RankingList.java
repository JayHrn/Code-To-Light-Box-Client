package com.nchu.software.pattern.xyh.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: RankingList
 * Package: a.observer
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 17:58
 * @Version: v1.0
 */
public class RankingList implements Subject {
    private List<Observer> observers;
    private String unlockedLevel;

    public RankingList() {
        observers = new ArrayList<Observer>();
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(unlockedLevel);
        }
    }

    public void setUnlockedLevel(String unlockedLevel) {
        this.unlockedLevel = unlockedLevel;
        notifyObservers();
    }
}