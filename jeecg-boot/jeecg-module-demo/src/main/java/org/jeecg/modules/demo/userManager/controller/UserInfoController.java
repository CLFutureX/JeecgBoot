package org.jeecg.modules.demo.userManager.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONUtil;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.userManager.entity.UserInfo;
import org.jeecg.modules.demo.userManager.service.IUserInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 大金用户信息表
 * @Author: jeecg-boot
 * @Date:   2024-07-21
 * @Version: V1.0
 */
@Api(tags="大金用户信息表")
@RestController
@RequestMapping("/userManager/userInfo")
@Slf4j
public class UserInfoController extends JeecgController<UserInfo, IUserInfoService> {
	@Autowired
	private IUserInfoService userInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param userInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "大金用户信息表-分页列表查询")
	@ApiOperation(value="大金用户信息表-分页列表查询", notes="大金用户信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<UserInfo>> queryPageList(UserInfo userInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		if(log.isInfoEnabled()){
			log.info(" queryPageList req:{}", JSONUtil.toJsonStr(userInfo));
		}
		QueryWrapper<UserInfo> queryWrapper = QueryGenerator.initQueryWrapper(userInfo, req.getParameterMap());
		Page<UserInfo> page = new Page<UserInfo>(pageNo, pageSize);
		IPage<UserInfo> pageList = userInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param userInfo
	 * @return
	 */
	@AutoLog(value = "大金用户信息表-添加")
	@ApiOperation(value="大金用户信息表-添加", notes="大金用户信息表-添加")
	@RequiresPermissions("userManager:dj_user_info:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody UserInfo userInfo) {
		userInfoService.save(userInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param userInfo
	 * @return
	 */
	@AutoLog(value = "大金用户信息表-编辑")
	@ApiOperation(value="大金用户信息表-编辑", notes="大金用户信息表-编辑")
	@RequiresPermissions("userManager:dj_user_info:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody UserInfo userInfo) {
		userInfoService.updateById(userInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "大金用户信息表-通过id删除")
	@ApiOperation(value="大金用户信息表-通过id删除", notes="大金用户信息表-通过id删除")
	@RequiresPermissions("userManager:dj_user_info:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		userInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "大金用户信息表-批量删除")
	@ApiOperation(value="大金用户信息表-批量删除", notes="大金用户信息表-批量删除")
	@RequiresPermissions("userManager:dj_user_info:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.userInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "大金用户信息表-通过id查询")
	@ApiOperation(value="大金用户信息表-通过id查询", notes="大金用户信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<UserInfo> queryById(@RequestParam(name="id",required=true) String id) {
		UserInfo userInfo = userInfoService.getById(id);
		if(userInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(userInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param userInfo
    */
    @RequiresPermissions("userManager:dj_user_info:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, UserInfo userInfo) {
        return super.exportXls(request, userInfo, UserInfo.class, "大金用户信息表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("userManager:dj_user_info:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, UserInfo.class);
    }

}
