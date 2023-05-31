package com.nchu.software.pattern.xyh.observer;

/**
 * ClassName: Subject
 * Package: a.observer
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 17:57
 * @Version: v1.0
 */
public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
