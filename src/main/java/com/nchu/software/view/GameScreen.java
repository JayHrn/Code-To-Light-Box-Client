package com.nchu.software.view;

import com.nchu.software.controller.GameController;
import com.nchu.software.model.level.Level;
import com.nchu.software.pattern.factory.ConcreteProgram;
import com.nchu.software.pattern.singleton.LanguageManager;
import com.nchu.software.pattern.singleton.SceneManager;
import com.nchu.software.util.UTF8Control;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import lombok.Data;

import java.util.*;

/**
 * @Author JayHrn
 * @Date 2023-05-16 20:12
 * @Description
 */
@Data
public class GameScreen implements Screen {
    private final String stageTitle = "游戏界面";
    private Pane pane; // 容器
    private Level level; // 关卡
    private Scene gameScene; // 场景
    private Screen preScreen; // 上一个场景
    private Button backButton; // 返回按钮
    private Button restartButton; // 重新开始
    private Button startGameButton; // 启动机器人行走
    private ImageView robotImageView; // 机器人图片
    private ImageView mainProgramImageView; // 主函数图片
    private ImageView subProgramImageView; // 子函数图片
    private Button upButton; // 上
    private Button leftButton; // 左
    private Button rightButton; // 右
    private Button downButton; // 下
    private Button lightButton; // 点亮
    private Button p1Button; // 函数按钮
    private int currentProgram = 1; // 当前选中的程序，1：主函数，2：子函数
    private List<Button> mainProgramDirectives; // 主函数指令集合
    private List<Button> subProgramDirectives; //子函数指令集合
    private Timeline gameTimeline;// 游戏定时器
    private Label timeLabel; // 定时器标签
    private List<ImageView> mapImageView; // 地图所有图片

    /**
     * 初始化场景
     */
    public void initScene() {
        // 初始化指令集合
        mainProgramDirectives = new ArrayList<>();
        subProgramDirectives = new ArrayList<>();
        mapImageView = new ArrayList<>();
        // 容器面板
        pane = new Pane();
        // 返回按钮
        backButton = new Button();
        backButton.getStyleClass().add("backButton");
        // 重新开始
        restartButton = new Button();
        restartButton.getStyleClass().add("restartButton");
        // 开始游戏
        startGameButton = new Button();
        startGameButton.getStyleClass().add("startGameButton");
        // 指令
        upButton = new Button();
        upButton.getStyleClass().add("upButton");
        leftButton = new Button();
        leftButton.getStyleClass().add("leftButton");
        rightButton = new Button();
        rightButton.getStyleClass().add("rightButton");
        downButton = new Button();
        downButton.getStyleClass().add("downButton");
        p1Button = new Button();
        p1Button.getStyleClass().add("p1Button");
        lightButton = new Button();
        lightButton.getStyleClass().add("lightButton");

        // 添加组件
        pane.getChildren().addAll(
                backButton,
                startGameButton,
                restartButton,
                upButton,
                leftButton,
                rightButton,
                downButton,
                lightButton,
                p1Button
        );

        // 初始化场景
        gameScene = new Scene(pane, SceneManager.getInstance().getStage().getWidth(), SceneManager.getInstance().getStage().getHeight());
        // 去除光标聚焦
        gameScene.getRoot().requestFocus();
        // 添加css
        String cssFile = getClass().getResource("/css/game.css").toExternalForm();
        gameScene.getStylesheets().add(cssFile);

        // 绘制地图，初始化机器人
        drawMap(); // 绘制地图
        initRobot(); // 初始化机器人
        initProgram(); // 初始化程序
        drawGameTimeLine(); // 绘制计时器

        // 监听处理业务逻辑
        GameController gameController = new GameController(this);
        gameController.startEventListen();

        // 设置当前舞台标题和切换场景
        SceneManager.getInstance().setStageTitle(stageTitle).switchCurrentScene(gameScene);
    }

    /**
     * 绘制游戏计时
     */
    public void drawGameTimeLine() {

        //获取当前语言
        String lang = LanguageManager.getInstance().getLanguageSetting().getLanguage();

        ResourceBundle bundle;
        if (lang.equals("Chinese")) {
            // 默认加载中文资源束
            bundle = ResourceBundle.getBundle("language/messages", Locale.CHINA, new UTF8Control());
        } else {
            // 默认加载英文资源束
            bundle = ResourceBundle.getBundle("language/messages", Locale.ENGLISH, new UTF8Control());

        }
        timeLabel = new Label(bundle.getString("gametime") + "0" + " s");
        // 创建计时器
        gameTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            level.getGame().setTime(level.getGame().getTime() + 1);
            timeLabel.setText(bundle.getString("gametime") + level.getGame().getTime() + " s");
        }));
        gameTimeline.setCycleCount(Animation.INDEFINITE); // 让计时器无限循环
        // 设置样式
        timeLabel.setLayoutX(400);
        timeLabel.setLayoutY(20);
        timeLabel.setFont(new Font("Sans-serif", 35));
        timeLabel.setStyle("-fx-text-fill: #48b8ca");
        pane.getChildren().addAll(timeLabel);
        // 启动计时器
        gameTimeline.play();
    }

    /**
     * 绘制初始化地图
     */
    public void drawMap() {
        for (int i = 0; i < level.getGame().getMapFacade().getMap().getMapBlocks().length; i++) {
            for (int j = 0; j < level.getGame().getMapFacade().getMap().getMapBlocks()[0].length; j++) {
                if (level.getGame().getMapFacade().getMap().getMapBlocks()[i][j] != 0) {
                    ImageView imageView = new ImageView();
                    setImage(imageView, level.getGame().getMapFacade().getMap().getMapBlocks()[i][j]);
                    imageView.setLayoutX(j * 80 + 200);
                    imageView.setLayoutY(i * 80 + 200);
                    mapImageView.add(imageView);
                    this.pane.getChildren().add(imageView);
                }
            }
        }
    }

    /**
     * 设置方块图片
     *
     * @param imageView
     * @param value
     */
    public void setImage(ImageView imageView, int value) {
        String imagePath = value == 1 ? "/img/map/block1.png" : "/img/map/block2.png";
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        imageView.setImage(image);
    }

    /**
     * 初始化机器人
     */
    public void initRobot() {
        robotImageView = new ImageView();
        Image image = new Image(getClass().getResourceAsStream("/img/map/robot.png"));
        robotImageView.setImage(image);
        robotImageView.setLayoutX(level.getGame().getRobot().getPosition().getY() * 80 + 205);
        robotImageView.setLayoutY(level.getGame().getRobot().getPosition().getX() * 80 + 120);
        pane.getChildren().add(robotImageView);
    }

    /**
     * 初始化主函数
     */
    public void initProgram() {
        // 主函数
        mainProgramImageView = new ImageView();
        Image mainProgramImage = new Image(getClass().getResourceAsStream("/img/mainProgram1.png"));
        mainProgramImageView.setImage(mainProgramImage);
        mainProgramImageView.setLayoutX(820);
        mainProgramImageView.setLayoutY(150);
        // 子函数
        subProgramImageView = new ImageView();
        Image subProgramImage = new Image(getClass().getResourceAsStream("/img/subProgram2.png"));
        subProgramImageView.setImage(subProgramImage);
        subProgramImageView.setLayoutX(820);
        subProgramImageView.setLayoutY(450);
        // 绘制
        pane.getChildren().addAll(mainProgramImageView, subProgramImageView);
    }

    /**
     * 更新机器人位置，移动机器人
     */
    public void updateRobot() {
        // 创建动画，改变机器人的位置
        robotImageView.setLayoutX(level.getGame().getRobot().getPosition().getY() * 80 + 205);
        robotImageView.setLayoutY(level.getGame().getRobot().getPosition().getX() * 80 + 120);
        robotImageView.getParent().requestLayout();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * 点亮地图
     */
    public void lightMap() {
        // 遍历所有图片，找到当前位置的图片，进行点亮
        for (ImageView imageView : mapImageView) {
            int x = (int) imageView.getLayoutX();
            int y = (int) imageView.getLayoutY();
            if (x == level.getGame().getRobot().getPosition().getY() * 80 + 200 && y == level.getGame().getRobot().getPosition().getX() * 80 + 200) {
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/map/block3.png")));
                imageView.setImage(image);
                // 表示点亮成功，该位置不需要再进行点亮
                level.getGame().getMapFacade().getMap().getMapBlocks()[level.getGame().getRobot().getPosition().getX()][level.getGame().getRobot().getPosition().getY()] = 1;
                break;
            }
        }
    }

    /**
     * 绘制图片进主函数
     *
     * @param x    x轴
     * @param y    y轴
     * @param type 图片类型
     */
    public void addDirectivesInMainProgram(int x, int y, String type) {
        String imgPath = "";
        switch (type) {
            case "up":
                imgPath = "/img/directives/up.png";
                break;
            case "left":
                imgPath = "/img/directives/left.png";
                break;
            case "right":
                imgPath = "/img/directives/right.png";
                break;
            case "down":
                imgPath = "/img/directives/down.png";
                break;
            case "light":
                imgPath = "/img/directives/light.png";
                break;
            case "p1":
                imgPath = "/img/directives/p1.png";
                break;
            default:
                break;
        }
        // 添加指令按钮
        Button button = new Button();
        button.getStyleClass().add("directives");
        Image backgroundImage = new Image(imgPath);
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, null, null, null, null);
        Background background = new Background(backgroundImg);
        button.setBackground(background);
        button.setLayoutX(y * 85 + 825);
        button.setLayoutY(x * 85 + 175);
        this.mainProgramDirectives.add(button);
        // 将还没有添加进面板的指令添加进去，并且添加点击事件
        for (Button mainProgramDirective : this.mainProgramDirectives) {
            if (!pane.getChildren().contains(mainProgramDirective)) {
                pane.getChildren().addAll(mainProgramDirective);
            }
        }
    }

    /**
     * 重新绘制主函数指令图片
     */
    public void repaintDirectivesInMainProgram() {
        // 获取程序
        ConcreteProgram concreteProgram = (ConcreteProgram) level.getGame().getProgram();
        // 根据程序的二维数组进行重新绘制图形位置
        for (int i = 0; i < concreteProgram.getMainProgram().length; i++) {
            for (int j = 0; j < concreteProgram.getMainProgram()[0].length; j++) {
                if (concreteProgram.getMainProgram()[i][j] == 1) {
                    // 一行四个指令，计算重新绘制
                    Button button = mainProgramDirectives.get(i * 4 + j);
                    button.setLayoutX(j * 85 + 825);
                    button.setLayoutY(i * 85 + 175);
                }
            }
        }
    }

    /**
     * 重新绘制子函数指令图片
     */
    public void repaintDirectivesInSubProgram() {
        // 获取程序
        ConcreteProgram concreteProgram = (ConcreteProgram) level.getGame().getProgram();
        // 根据程序的二维数组进行重新绘制图形位置
        for (int i = 0; i < concreteProgram.getSubProgram().length; i++) {
            for (int j = 0; j < concreteProgram.getSubProgram()[0].length; j++) {
                if (concreteProgram.getSubProgram()[i][j] == 1) {
                    Button button = subProgramDirectives.get(i * 4 + j);
                    button.setLayoutX(j * 85 + 825);
                    button.setLayoutY(i * 85 + 475);
                }
            }
        }
    }

    /**
     * 添加指令进子程序
     *
     * @param x    x轴
     * @param y    y轴
     * @param type 指令类型
     */
    public void addDirectivesInSubProgram(int x, int y, String type) {
        String imgPath = "";
        switch (type) {
            case "up":
                imgPath = "/img/directives/up.png";
                break;
            case "left":
                imgPath = "/img/directives/left.png";
                break;
            case "right":
                imgPath = "/img/directives/right.png";
                break;
            case "down":
                imgPath = "/img/directives/down.png";
                break;
            case "light":
                imgPath = "/img/directives/light.png";
                break;
            default:
                break;
        }
        // 添加指令按钮
        Button button = new Button();
        button.getStyleClass().add("directives");
        Image backgroundImage = new Image(imgPath);
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, null, null, null, null);
        Background background = new Background(backgroundImg);
        button.setBackground(background);
        button.setLayoutX(y * 85 + 825);
        button.setLayoutY(x * 85 + 475);
        this.subProgramDirectives.add(button);
        // 判断还没有添加进去的指令，添加进去
        for (Button subProgramDirective : this.subProgramDirectives) {
            if (!this.pane.getChildren().contains(subProgramDirective)) {
                this.pane.getChildren().add(subProgramDirective);
            }
        }
    }


    @Override
    public String getStageTitle() {
        return stageTitle;
    }

    public Screen getPreScreen() {
        return preScreen;
    }

    public Scene getScene() {
        return gameScene;
    }
}
