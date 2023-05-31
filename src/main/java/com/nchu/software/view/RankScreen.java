package com.nchu.software.view;

import com.nchu.software.common.Result;
import com.nchu.software.controller.RankController;
import com.nchu.software.controller.network.LevelController;
import com.nchu.software.controller.network.RecordController;
import com.nchu.software.controller.network.UserController;
import com.nchu.software.dto.LevelDto;
import com.nchu.software.dto.UserDto;
import com.nchu.software.dto.UserRecord;
import com.nchu.software.pattern.singleton.SceneManager;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import lombok.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author JayHrn
 * @Date 2023-05-16 19:16
 * @Description 排行榜界面
 */
@Data
public class RankScreen implements Screen {
    private final String stageTitle = "排行榜界面";
    private Scene rankScene; // 场景
    private Screen preScreen; // 上一个场景
    private Button backButton; // 启动游戏按钮
    private Pane pane; // 全局面板
    private Pane levelRankPane; // 关卡排行榜面板
    private ComboBox<String> selectLevelComboBox; //关卡选择下拉框


    public void initScene() {
        // 启动游戏按钮
        backButton = new Button();
        backButton.getStyleClass().add("backButton");

        selectLevelComboBox = new ComboBox<>();
        selectLevelComboBox.getStyleClass().add("selectLevelComboBox");
        selectLevelComboBox.setItems(FXCollections.observableArrayList(
                "第一关", "第二关", "第三关"));
        selectLevelComboBox.setValue("第一关");

        // 容器面板
        pane = new Pane();
        // 添加组件
        pane.getChildren().addAll(backButton, selectLevelComboBox);

        // 初始化场景
        rankScene = new Scene(pane, SceneManager.getInstance().getStage().getWidth(), SceneManager.getInstance().getStage().getHeight());
        // 去除光标聚焦
        rankScene.getRoot().requestFocus();
        // 添加css
        String cssFile = getClass().getResource("/css/rank.css").toExternalForm();
        rankScene.getStylesheets().add(cssFile);

        loadAllRank();
        loadLevelRank(selectLevelComboBox.getSelectionModel().getSelectedItem());

        // 监听处理业务逻辑
        RankController rankController = new RankController(this);
        rankController.startEventListen();
        // 设置当前舞台标题和切换场景
        SceneManager.getInstance().setStageTitle(stageTitle).switchCurrentScene(rankScene);
    }

    /**
     * 加载总排行榜
     */
    public void loadAllRank() {
        Label labelTitle = new Label("总排行榜");
        labelTitle.setLayoutX(260);
        labelTitle.setLayoutY(100);
        labelTitle.setFont(new Font("Sans-serif", 35));
        labelTitle.setStyle("-fx-text-fill: #48b8ca");
        pane.getChildren().add(labelTitle);

        // 查询数据
        UserController userController = new UserController();
        Result<List<UserDto>> result = userController.queryUser();
        List<UserDto> userDtoList = result.getData();
        // 根据解锁关卡数目长度排序
        Collections.sort(userDtoList, new Comparator<UserDto>() {
            @Override
            public int compare(UserDto o1, UserDto o2) {
                int length1 = o1.getUnlockedLevel().length();
                int length2 = o2.getUnlockedLevel().length();
                return Integer.compare(length2, length1);
            }
        });

        // 排行榜表头
        Label userNameTitle = new Label("用户名");
        Label unlockedLevelNumTitle = new Label("解锁关卡数");
        Label rankNumTitle = new Label("排名");
        userNameTitle.setFont(new Font("Sans-serif", 18));
        unlockedLevelNumTitle.setFont(new Font("Sans-serif", 18));
        rankNumTitle.setFont(new Font("Sans-serif", 18));
        userNameTitle.setLayoutX(100);
        userNameTitle.setLayoutY(200);
        unlockedLevelNumTitle.setLayoutX(280);
        unlockedLevelNumTitle.setLayoutY(200);
        rankNumTitle.setLayoutX(460);
        rankNumTitle.setLayoutY(200);
        pane.getChildren().addAll(userNameTitle, unlockedLevelNumTitle, rankNumTitle);

        int rank = 1;
        for (int i = 0; i < userDtoList.size(); i++) {
            Label username = new Label(userDtoList.get(i).getUsername());
            Label unlockedLevelNum = new Label(String.valueOf(userDtoList.get(i).getUnlockedLevel().split(";").length));
            if (i == 0 || userDtoList.get(i).getUnlockedLevel().split(";").length < userDtoList.get(i - 1).getUnlockedLevel().split(";").length) {
                rank = i + 1;
            }
            Label rankNum = new Label(String.valueOf(rank));
            username.setLayoutX(100);
            username.setLayoutY(i * 45 + 245);
            unlockedLevelNum.setLayoutX(280);
            unlockedLevelNum.setLayoutY(i * 45 + 245);
            rankNum.setLayoutX(460);
            rankNum.setLayoutY(i * 45 + 245);
            username.setFont(new Font("Sans-serif", 18));
            unlockedLevelNum.setFont(new Font("Sans-serif", 18));
            rankNum.setFont(new Font("Sans-serif", 18));
            pane.getChildren().addAll(username, unlockedLevelNum, rankNum);
        }
    }

    /**
     * 根据关卡名称加载关卡排行榜
     *
     * @param levelName 关卡名称
     */
    public void loadLevelRank(String levelName) {
        levelRankPane = new Pane();
        levelRankPane.setLayoutX(600);
        levelRankPane.setLayoutY(100);
        levelRankPane.setPrefSize(580, 600);
        Label labelTitle = new Label(levelName + "排行榜");
        labelTitle.setLayoutX(150);
        labelTitle.setLayoutY(0);
        labelTitle.setFont(new Font("Sans-serif", 35));
        labelTitle.setStyle("-fx-text-fill: #48b8ca");
        levelRankPane.getChildren().add(labelTitle);
        // 首先根据关卡名称，查出关卡数据
        LevelController levelController = new LevelController();
        Result<LevelDto> result = levelController.queryLevelByName(levelName);
        LevelDto levelDto = result.getData();

        // 根据关卡id查出这个关卡的闯关数据
        RecordController recordController = new RecordController();
        Result<List<UserRecord>> recordResult = recordController.queryRecordByLevelId(levelDto.getId());
        // 获取数据
        List<UserRecord> records = recordResult.getData();

        // 根据解锁关卡数目长度排序
        Collections.sort(records, new Comparator<UserRecord>() {
            @Override
            public int compare(UserRecord o1, UserRecord o2) {
                int length1 = o1.getTime();
                int length2 = o2.getTime();
                return Integer.compare(length1, length2);
            }
        });

        // 排行榜表头
        Label userNameTitle = new Label("用户名");
        Label unlockedLevelNumTitle = new Label("闯关用时");
        Label rankNumTitle = new Label("排名");
        userNameTitle.setFont(new Font("Sans-serif", 18));
        unlockedLevelNumTitle.setFont(new Font("Sans-serif", 18));
        rankNumTitle.setFont(new Font("Sans-serif", 18));
        userNameTitle.setLayoutX(20);
        userNameTitle.setLayoutY(100);
        unlockedLevelNumTitle.setLayoutX(200);
        unlockedLevelNumTitle.setLayoutY(100);
        rankNumTitle.setLayoutX(380);
        rankNumTitle.setLayoutY(100);
        levelRankPane.getChildren().addAll(userNameTitle, unlockedLevelNumTitle, rankNumTitle);

        int rank = 1;
        for (int i = 0; i < records.size(); i++) {
            Label username = new Label(records.get(i).getUser().getUsername());
            Label time = new Label(records.get(i).getTime() + "s");
            if (i == 0 || records.get(i).getTime() > records.get(i - 1).getTime()) {
                rank = i + 1;
            }
            Label rankNum = new Label(String.valueOf(rank));
            username.setLayoutX(20);
            username.setLayoutY(i * 45 + 145);
            time.setLayoutX(200);
            time.setLayoutY(i * 45 + 145);
            rankNum.setLayoutX(380);
            rankNum.setLayoutY(i * 45 + 145);
            username.setFont(new Font("Sans-serif", 18));
            time.setFont(new Font("Sans-serif", 18));
            rankNum.setFont(new Font("Sans-serif", 18));
            levelRankPane.getChildren().addAll(username, time, rankNum);
        }
        pane.getChildren().add(levelRankPane);

    }

    @Override
    public String getStageTitle() {
        return stageTitle;
    }

    public Screen getPreScreen() {
        return preScreen;
    }

    public Scene getScene() {
        return rankScene;
    }
}
