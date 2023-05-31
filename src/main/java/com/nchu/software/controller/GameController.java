package com.nchu.software.controller;

import com.nchu.software.common.Result;
import com.nchu.software.controller.network.LevelController;
import com.nchu.software.controller.network.RecordController;
import com.nchu.software.controller.network.UserController;
import com.nchu.software.dto.LevelDto;
import com.nchu.software.dto.RecordDto;
import com.nchu.software.dto.UserDto;
import com.nchu.software.model.Position;
import com.nchu.software.pattern.command.LightBoxCommand;
import com.nchu.software.pattern.command.MoveCommand;
import com.nchu.software.pattern.composite.Composite;
import com.nchu.software.pattern.composite.FunctionComponent;
import com.nchu.software.pattern.composite.Leaf;
import com.nchu.software.pattern.factory.ConcreteProgram;
import com.nchu.software.pattern.singleton.LanguageManager;
import com.nchu.software.pattern.singleton.SceneManager;
import com.nchu.software.pattern.strategy.*;
import com.nchu.software.util.LocalGameStorage;
import com.nchu.software.util.UTF8Control;
import com.nchu.software.view.AlertTip;
import com.nchu.software.view.GameScreen;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.*;

/**
 * @Author JayHrn
 * @Date 2023-05-16 20:46
 * @Description 游戏界面逻辑控制
 */
public class GameController {
    private GameScreen gameScreen;
    /**
     * 程序
     */
    private ConcreteProgram concreteProgram;

    public GameController(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        concreteProgram = (ConcreteProgram) gameScreen.getLevel().getGame().getProgram();
        initProgramList();
    }

    /**
     * 初始化程序函数命令列表
     */
    public void initProgramList() {
        // 主函数
        FunctionComponent mainFunction = new Composite();
        // 子函数
        FunctionComponent subFunction = new Composite();
        List<FunctionComponent> functionComponentList = new ArrayList<>();
        functionComponentList.add(mainFunction);
        functionComponentList.add(subFunction);
        concreteProgram.setFunctionComponents(functionComponentList);
    }

    /**
     * 监听
     */
    public void startEventListen() {
        // 如下代码须在最前面
        // 创建一个后台线程进行循环判断指令是否被点击
        Thread loopThread = new Thread(() -> {
            while (true) {
                // 在JavaFX主线程中执行判断逻辑
                Platform.runLater(() -> {
                    // 遍历主函数指令
                    for (int i = 0; i < gameScreen.getMainProgramDirectives().size(); i++) {
                        Button button = gameScreen.getMainProgramDirectives().get(i);
                        final int index = i;
                        button.setOnAction(event -> {
                            gameScreen.getMainProgramDirectives().remove(button);// 指令列表移除
                            gameScreen.getPane().getChildren().remove(button); // 界面移除图形
                            removeDirectivesInComposite(index);
                            updateMainProgramMatrix(); // 更新二维矩阵
                            gameScreen.repaintDirectivesInMainProgram(); // 根据二维矩阵重新绘制
                        });
                    }

                    // 遍历子函数指令
                    for (int i = 0; i < gameScreen.getSubProgramDirectives().size(); i++) {
                        Button button = gameScreen.getSubProgramDirectives().get(i);
                        final int index = i;
                        button.setOnAction(event -> {
                            gameScreen.getSubProgramDirectives().remove(button); // 指令列表移除
                            gameScreen.getPane().getChildren().remove(button); // 界面移除图形
                            removeDirectivesInComposite(index);
                            updateSubProgramMatrix(); // 更新二维矩阵
                            gameScreen.repaintDirectivesInSubProgram(); // 根据二维矩阵重新绘制
                        });
                    }
                });
                try {
                    // 线程休眠一段时间，避免过于频繁的判断
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("指令线程被关闭");
                    return;
                }
            }
        });
        loopThread.start();


        // 监听窗口关闭所有线程
        SceneManager.getInstance().getStage().setOnCloseRequest(event -> {
            // 关闭窗口，所有的线程被关闭
            loopThread.interrupt();
            gameScreen.getGameTimeline().stop();
        });

        // 返回按钮
        gameScreen.getBackButton().setOnAction(event -> {
            SceneManager.getInstance().setStageTitle(gameScreen.getPreScreen().getStageTitle()).switchCurrentScene(gameScreen.getPreScreen().getScene());
            loopThread.interrupt();
            gameScreen.getGameTimeline().stop();
        });

        // 重新开始游戏
        gameScreen.getRestartButton().setOnAction(event -> {
            restartGame();
        });

        // 启动游戏
        gameScreen.getStartGameButton().setOnAction(event -> {
            // 编写启动游戏操作
            System.out.println("启动游戏");
            gameScreen.getGameTimeline().stop();

            Thread thread = new Thread(() -> {
                concreteProgram.execute();
                RecordDto record = new RecordDto();
                record.setUserId(LocalGameStorage.getInstance().getCurrentUser().getId());
                record.setLevelId(LocalGameStorage.getInstance().getCurrentLevel().getId());
                record.setTime(gameScreen.getLevel().getGame().getTime());


                //语言切换
                String lang = LanguageManager.getInstance().getLanguageSetting().getLanguage();

                ResourceBundle bundle;
                if (lang.equals("Chinese")) {
                    // 默认加载中文资源束
                    bundle = ResourceBundle.getBundle("language/messages", Locale.CHINA, new UTF8Control());
                } else {
                    // 默认加载英文资源束
                    bundle = ResourceBundle.getBundle("language/messages", Locale.ENGLISH, new UTF8Control());

                }

                if (isSuccessful()) {
                    RecordController recordController = new RecordController();
                    Result<RecordDto> result = recordController.queryRecordByUserId(record.getUserId(), record.getLevelId());
                    // 有闯关记录
                    if (result.getData() != null) {
                        // 更新
                        record.setId(result.getData().getId());
                        recordController.updateRecord(record);
                    } else {
                        // 插入
                        recordController.addRecord(record);
                    }
                    // 判断是否需要解锁下一个关卡，符合需要进行解锁
                    // 1. 根据当前关卡查询下一个关卡
                    LevelController levelController = new LevelController();
                    Result<LevelDto> recordResult = levelController.queryNextLevel(record.getLevelId());
                    // 2. 查询用户信息
                    UserController userController = new UserController();
                    Result<UserDto> userResult = userController.queryUserById(record.getUserId());
                    String unlockedLevel = userResult.getData().getUnlockedLevel();
                    // 不包含解锁关卡，更新解锁关卡数据
                    if (!unlockedLevel.contains(String.valueOf(recordResult.getData().getId()))) {
                        unlockedLevel = unlockedLevel + recordResult.getData().getId() + ";";
                    }
                    // 更新数据
                    userResult.getData().setUnlockedLevel(unlockedLevel);
                    // 解决时区报错
                    userResult.getData().setCreateTime(null);
                    userResult.getData().setUpdateTime(null);

                    userController.update(userResult.getData());

                    Thread alertThread = new Thread(() -> {
                        Platform.runLater(() -> {
                            //获取当前语言

                            AlertTip.showAlert(Alert.AlertType.INFORMATION, bundle.getString("playSuc"), bundle.getString("playSucMess"));
                        });
                    });
                    alertThread.start();

                } else {
                    Thread thread1 = new Thread(() -> {
                        Platform.runLater(() -> {
                            AlertTip.showAlert(Alert.AlertType.INFORMATION, bundle.getString("playFai"), bundle.getString("playFaiMess"));
                        });
                    });
                    thread1.start();
                }
                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(e -> {
                    AlertTip.getAlert().close();
                    SceneManager.getInstance().setStageTitle(gameScreen.getPreScreen().getStageTitle()).switchCurrentScene(gameScreen.getPreScreen().getScene());
                    loopThread.interrupt();
                });
                delay.play();
            });
            thread.start();
        });
        // 主函数选中
        gameScreen.getMainProgramImageView().setOnMouseClicked(event -> {
            // 点击说明当前是被选中的需要使用的函数
            ImageView clickedImageView = (ImageView) event.getSource();
            clickedImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/mainProgram1.png"))));
            gameScreen.getSubProgramImageView().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/subProgram2.png"))));
            // 设置当前选中程序
            gameScreen.setCurrentProgram(1);
        });

        // 子函数选中
        gameScreen.getSubProgramImageView().setOnMouseClicked(event -> {
            // 切换选中样式
            ImageView clickedImageView = (ImageView) event.getSource();
            clickedImageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/subProgram1.png"))));
            gameScreen.getMainProgramImageView().setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/mainProgram2.png"))));
            // 设置当前选中程序
            gameScreen.setCurrentProgram(2);
        });

        // 向上指令
        gameScreen.getUpButton().setOnAction(event -> {
            // 添加向上指令的图片
            addDirectives("up");
            // 添加逻辑命令
            addMoveCommand(new MoveForwardStrategy());
            // 点击生成一张图片，绘制向上按钮图片
            System.out.println("向上按钮");
        });
        // 向左指令
        gameScreen.getLeftButton().setOnAction(event -> {
            addDirectives("left");
            // 添加逻辑命令
            addMoveCommand(new MoveLeftStrategy());
            System.out.println("向左按钮");
        });
        // 向右指令
        gameScreen.getRightButton().setOnAction(event -> {
            addDirectives("right");
            // 添加逻辑命令
            addMoveCommand(new MoveRightStrategy());

            System.out.println("向右按钮");
        });
        // 向下指令
        gameScreen.getDownButton().setOnAction(event -> {
            addDirectives("down");
            addMoveCommand(new MoveDownStrategy());
            System.out.println("向下按钮");
        });
        // 点亮指令
        gameScreen.getLightButton().setOnAction(event -> {
            addDirectives("light");

            // 1. 获取主函数composite
            Composite composite = (Composite) concreteProgram.getFunctionComponents().get(gameScreen.getCurrentProgram() - 1);
            // 添加叶子节点，直接就是可以执行的命令(点亮小灯泡)
            Leaf leaf = new Leaf(new LightBoxCommand(gameScreen.getLevel().getGame().getRobot(), gameScreen.getLevel().getGame().getMapFacade().getMap(), gameScreen));
            composite.add(leaf);

            System.out.println("点亮小灯泡");
        });
        // 函数指令
        gameScreen.getP1Button().setOnAction(event -> {
            // 主函数才能添加p1
            if (gameScreen.getCurrentProgram() == 1) {
                addDirectives("p1");
                // 添加逻辑命令
                Composite mainComposite = (Composite) concreteProgram.getFunctionComponents().get(gameScreen.getCurrentProgram() - 1);
                Composite subComposite = (Composite) concreteProgram.getFunctionComponents().get(gameScreen.getCurrentProgram());
                // 主函数添加子函数
                mainComposite.add(subComposite);
                System.out.println("子函数");
            }
        });
    }

    public void restartGame() {
        // 方案一
//            GameScreen reGameScreen=new GameScreen();
//            reGameScreen.setPreScreen(gameScreen.getPreScreen());
//            gameScreen.getGameTimeline().stop();
//            Level level=gameScreen.getLevel();
//            level.initGame();
//            reGameScreen.setLevel(level);
//            reGameScreen.initScene();

        // 方案二，游戏对象不变，暂时机器人对象和地图没更新
        // 1. 终止游戏时间
        gameScreen.getGameTimeline().stop();
        // 重置游戏时长
        gameScreen.getLevel().getGame().resetTime();
        // 重置游戏对象
        gameScreen.getLevel().initGame();
        // 初始化界面
        gameScreen.initScene();
    }

    /**
     * 判断游戏结束
     *
     * @return boolean
     */
    public boolean isSuccessful() {
        int[][] arr = gameScreen.getLevel().getGame().getMapFacade().getMap().getMapBlocks();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 2) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 添加行走的指令
     *
     * @param moveStrategy 移动策略
     */
    public void addMoveCommand(MoveStrategy moveStrategy) {
        // 添加向行走的逻辑命令
        // 1. 获取主函数composite
        Composite composite = (Composite) concreteProgram.getFunctionComponents().get(gameScreen.getCurrentProgram() - 1);
        // 添加叶子节点，直接就是可以执行的命令
        Leaf leaf = new Leaf(new MoveCommand(gameScreen.getLevel().getGame().getRobot(), gameScreen.getLevel().getGame().getMapFacade().getMap(), moveStrategy, gameScreen));
        composite.add(leaf);
    }

    /**
     * 根据下标移除组合模式中的指令
     *
     * @param index
     */
    public void removeDirectivesInComposite(int index) {
        Composite composite = (Composite) concreteProgram.getFunctionComponents().get(gameScreen.getCurrentProgram() - 1);
        composite.getChildren().remove(index);
    }

    /**
     * 添加指令
     *
     * @param type 指令类型
     */
    public void addDirectives(String type) {
        // 获取可以插入的位置
        Position position = getAvailableCoordinate(gameScreen.getCurrentProgram());
        int x = position.getX(), y = position.getY();
        // 找到了可以插入的位置
        if (x != -1 && y != -1) {
            // 是主函数
            if (gameScreen.getCurrentProgram() == 1) {
                gameScreen.addDirectivesInMainProgram(x, y, type);
            } else if (gameScreen.getCurrentProgram() == 2) { // 是子函数
                gameScreen.addDirectivesInSubProgram(x, y, type);
            }
        }
    }

    /**
     * 获取当前选中的程序可以插入的位置并且更新插入了图片
     *
     * @param type 程序类型（main，sub）
     * @return
     */
    public Position getAvailableCoordinate(int type) {
        // 判断是主函数还是子函数
        int[][] arr;
        // 主函数
        if (type == 1) {
            arr = concreteProgram.getMainProgram();
        } else { // 子函数
            arr = concreteProgram.getSubProgram();
        }
        Position position = new Position();
        // 数组之内可以找到，就返回
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 0) {
                    // 找到可以插入的位置
                    position.setX(i);
                    position.setY(j);
                    arr[i][j] = 1;
                    // 更新数组
                    if (type == 1) {
                        concreteProgram.setMainProgram(arr);
                    } else {
                        concreteProgram.setSubProgram(arr);
                    }
                    return position;
                }
            }
        }
        // 找不到返回-1
        position.setX(-1);
        position.setY(-1);
        return position;
    }

    /**
     * 更新主函数矩阵
     */
    public void updateMainProgramMatrix() {
        int num = 0;
        // 计算数字为1的个数
        for (int i = 0; i < concreteProgram.getMainProgram().length; i++) {
            for (int j = 0; j < concreteProgram.getMainProgram()[0].length; j++) {
                if (concreteProgram.getMainProgram()[i][j] == 1) {
                    concreteProgram.getMainProgram()[i][j] = 0;
                    num++;
                }
            }
        }
        // 由于点击图形少了一个1，减少一个
        num--;
        // 重新设置矩阵数字1的个数
        for (int i = 0; i < concreteProgram.getMainProgram().length; i++) {
            for (int j = 0; j < concreteProgram.getMainProgram()[0].length; j++) {
                if (num != 0) {
                    concreteProgram.getMainProgram()[i][j] = 1;
                    num--;
                } else {
                    // 退出循环
                    return;
                }
            }
        }
    }

    /**
     * 更新子函数矩阵
     */
    public void updateSubProgramMatrix() {
        int num = 0;
        // 计算数字为1的个数
        for (int i = 0; i < concreteProgram.getSubProgram().length; i++) {
            for (int j = 0; j < concreteProgram.getSubProgram()[0].length; j++) {
                if (concreteProgram.getSubProgram()[i][j] == 1) {
                    concreteProgram.getSubProgram()[i][j] = 0;
                    num++;
                }
            }
        }
        // 由于点击图形少了一个1，减少一个
        num--;
        // 重新设置矩阵数字1的个数
        for (int i = 0; i < concreteProgram.getSubProgram().length; i++) {
            for (int j = 0; j < concreteProgram.getSubProgram()[0].length; j++) {
                if (num != 0) {
                    concreteProgram.getSubProgram()[i][j] = 1;
                    num--;
                } else {
                    // 退出循环
                    return;
                }
            }
        }
    }
}
