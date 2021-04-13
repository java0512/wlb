package com.hainet.his.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hainet.model.user.UserInfo;
import com.hainet.model.user.vo.UserInfoQueryVo;

import java.util.List;
import java.util.Map;

public interface UserInfoService extends IService<UserInfo> {

    IPage<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);

    void lock(Long userId, Integer status);

    Map<String, Object> show(Long userId);

    void approval(Long userId, Integer authStatus);
}
