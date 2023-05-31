package com.nchu.software.pattern.xyh.singleton;

/**
 * ClassName: GameRankingList
 * Package: a
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 15:02
 * @Version: v1.0
 */
public class GameRankingList {

    // 定义一个静态成员变量来存储唯一实例
    private static final GameRankingList instance = new GameRankingList();

    // 私有化构造函数，禁止外部直接调用构造函数创建新的对象
    private GameRankingList() {}

    // 提供一个公共的静态方法来获取唯一实例
    public static GameRankingList getInstance() {
        return instance;
    }

    // 对象的属性
    private Long id;
    private Long userId;
    private Long levelId;
    private Integer time;

    // 获得属性值的方法
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public Integer getTime() {
        return time;
    }

    // 设置属性值的方法
    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

}
