package com.nchu.software.controller;

import com.nchu.software.pattern.singleton.SceneManager;
import com.nchu.software.view.RankScreen;

/**
 * @Author JayHrn
 * @Date 2023-05-16 19:19
 * @Description 排行榜界面逻辑控制
 */
public class RankController {
    private RankScreen rankScreen;

    public RankController(RankScreen rankScreen) {
        this.rankScreen = rankScreen;
    }

    /**
     * 监听
     */
    public void startEventListen() {
        rankScreen.getBackButton().setOnAction(event -> {
            SceneManager.getInstance().setStageTitle(rankScreen.getPreScreen().getStageTitle()).switchCurrentScene(rankScreen.getPreScreen().getScene());
        });
        rankScreen.getSelectLevelComboBox().setOnAction(event -> {
            String selectedItem = rankScreen.getSelectLevelComboBox().getSelectionModel().getSelectedItem();
            rankScreen.getLevelRankPane().getChildren().clear();
            rankScreen.loadLevelRank(selectedItem);
        });
    }
}
