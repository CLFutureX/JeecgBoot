package org.jeecg.modules.demo.customManager.service.impl;

import org.jeecg.modules.demo.customManager.entity.CustomInfo;
import org.jeecg.modules.demo.customManager.mapper.CustomInfoMapper;
import org.jeecg.modules.demo.customManager.service.ICustomInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户信息表
 * @Author: jeecg-boot
 * @Date:   2024-07-21
 * @Version: V1.0
 */
@Service
public class CustomInfoServiceImpl extends ServiceImpl<CustomInfoMapper, CustomInfo> implements ICustomInfoService {

}
