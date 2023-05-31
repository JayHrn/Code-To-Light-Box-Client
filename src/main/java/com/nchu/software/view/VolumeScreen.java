package com.nchu.software.view;

import com.nchu.software.controller.VolumeController;
import com.nchu.software.pattern.Decorator.MediaPlayerSound;
import com.nchu.software.pattern.Decorator.Sound;
import com.nchu.software.pattern.Decorator.VolumeChangeDecorator;
import com.nchu.software.pattern.singleton.LanguageManager;
import com.nchu.software.pattern.singleton.MediaPlayerChange;
import com.nchu.software.pattern.singleton.SceneManager;
import com.nchu.software.pattern.singleton.Volume;
import com.nchu.software.util.UTF8Control;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import lombok.Data;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Description 音量调节界面
 */
@Data
public class VolumeScreen {
    private final String stageTitle = "音量调节界面";
    private Scene volumeScene; // 场景
    private Slider volumeSlider;
    private Label volumeLabel;
    private Label volumeValueLabel;
    private MediaPlayer mediaPlayer;

    private MainScreen mainScreen;


    public void initScene() {

        // 创建音量滑动条
        volumeSlider = new Slider(0, 100, Volume.getInstance().getVolumeLevel());
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setShowTickLabels(true);
        volumeSlider.setOrientation(Orientation.HORIZONTAL);

        // 获取 MediaPlayer 实例
        mediaPlayer = MediaPlayerChange.getInstance().getMediaPlayer();

        // 创建音量滑动条
        volumeSlider = new Slider(0, 100, 50);
        volumeSlider.setShowTickMarks(true);
        volumeSlider.setShowTickLabels(true);

        Image volumeOnImage = new Image(getClass().getResourceAsStream("/img/volume.png"));
        Image volumeOffImage = new Image(getClass().getResourceAsStream("/img/volumeClose.png"));
        // 创建 MediaPlayerSound 实例
        Sound sound = new MediaPlayerSound(mediaPlayer);

        // 创建 VolumeChangeDecorator 实例
        Sound decoratedSound = new VolumeChangeDecorator(sound, volumeSlider, mainScreen.getVolumeButton(), volumeOnImage, volumeOffImage);

        // 创建扬声器图标
        String imagePath = "/img/volumeChange.png";
        ImageView speakerIcon = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        speakerIcon.setFitWidth(50);
        speakerIcon.setPreserveRatio(true);
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
        // 创建音量标签和音量数值显示标签
        volumeLabel = new Label(bundle.getString("volume"));
        volumeValueLabel = new Label();

        // 绑定音量数值显示标签的值与滑动条的值
        volumeValueLabel.textProperty().bind(volumeSlider.valueProperty().asString("%.0f"));

        decoratedSound.play();

        // 布局
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(speakerIcon, volumeLabel, volumeSlider, volumeValueLabel);


        // 初始化场景
        volumeScene = new Scene(vbox, SceneManager.getInstance().getStage().getWidth(), SceneManager.getInstance().getStage().getHeight());
        // 去除光标聚焦
        volumeScene.getRoot().requestFocus();
        // 添加css
        String cssFile = getClass().getResource("/css/volume.css").toExternalForm();
        volumeScene.getStylesheets().add(cssFile);
        // 监听处理业务逻辑
        VolumeController volumeController = new VolumeController(this);
        volumeController.startEventListen();
    }


}
