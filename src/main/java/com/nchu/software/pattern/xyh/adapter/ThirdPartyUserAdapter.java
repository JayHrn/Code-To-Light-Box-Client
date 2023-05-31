package com.nchu.software.pattern.xyh.adapter;

/**
 * ClassName: ThirdPartyUserAdapter
 * Package: a.adapter
 * Description:
 *
 * @Author: xyh
 * @Create: 2023-05-28 17:06
 * @Version: v1.0
 */
// 第三方用户适配器类
public class ThirdPartyUserAdapter extends User {
    private ThirdPartyUser thirdPartyUser;

    public ThirdPartyUserAdapter(ThirdPartyUser thirdPartyUser) {
        this.thirdPartyUser = thirdPartyUser;
        setProperties();
    }

    // 将第三方用户信息转换为本地用户属性
    private void setProperties() {
        setEmail(thirdPartyUser.getEmail());
        setPassword(thirdPartyUser.getPassword());

        // 假设我们需要给新用户解锁前三个关卡
        setUnlockedLevel("1,2,3");

        // 生成一个唯一的用户名，例如 "FirstName.LastName123"
        setUsername(thirdPartyUser.getFirstName() + "." + thirdPartyUser.getLastName() + "123");
    }
}

