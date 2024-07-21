package org.jeecg.modules.demo.userManager.service.impl;

import org.jeecg.modules.demo.userManager.entity.UserInfo;
import org.jeecg.modules.demo.userManager.mapper.UserInfoMapper;
import org.jeecg.modules.demo.userManager.service.IUserInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 大金用户信息表
 * @Author: jeecg-boot
 * @Date:   2024-07-21
 * @Version: V1.0
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
