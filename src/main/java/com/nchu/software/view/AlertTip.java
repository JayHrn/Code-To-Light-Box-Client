package com.nchu.software.view;

import javafx.scene.control.Alert;

/**
 * @Author JayHrn
 * @Date 2023-05-15 20:15
 * @Description 自定义组件，弹窗Alert（看函数说明使用）
 */
public class AlertTip {
    /**
     * 弹窗
     */
    private static Alert alert;

    /**
     * 展示提示框
     * 会阻塞，必须用户点击确认或者关闭按钮关闭
     *
     * @param alertType 弹窗类型
     * @param title     标题
     * @param msg       内容消息
     */
    public static void showAlert(Alert.AlertType alertType, String title, String msg) {
        alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        // 显示提示框
        alert.showAndWait();
    }

    /**
     * 展示提示框
     * 不会阻塞，可以通过函数调用进行关闭
     *
     * @param alertType 弹窗类型
     * @param title     标题
     * @param msg       内容消息
     */
    public static void showAutoAlert(Alert.AlertType alertType, String title, String msg) {
        alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        // 显示提示框
        alert.show();
    }

    /**
     * 获取弹窗对象，必须先调用展示提示框函数，否则alert对象没有被创建
     *
     * @return Alert
     */
    public static Alert getAlert() {
        return alert;
    }
}
