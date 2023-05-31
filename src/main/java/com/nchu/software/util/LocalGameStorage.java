package com.nchu.software.util;

import com.nchu.software.dto.LevelDto;
import com.nchu.software.dto.UserDto;
import com.nchu.software.model.Game;
import lombok.Data;

/**
 * @Author JayHrn
 * @Date 2023-05-20 17:48
 * @Description
 */
@Data
public class LocalGameStorage {
    private UserDto currentUser;
    private LevelDto currentLevel;
    private static class LocalGameStorageHolder {
        // 在内部类中声明并初始化外部类的对象
        private static final LocalGameStorage INSTANCE = new LocalGameStorage();
    }

    /**
     * 提供公共的访问方式
     *
     * @return 游戏
     */
    public static LocalGameStorage getInstance() {

        return LocalGameStorage.LocalGameStorageHolder.INSTANCE;
    }
}
