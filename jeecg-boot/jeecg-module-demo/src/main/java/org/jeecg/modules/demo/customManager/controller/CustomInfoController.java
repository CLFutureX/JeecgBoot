package org.jeecg.modules.demo.customManager.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.json.JSONUtil;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.customManager.entity.CustomInfo;
import org.jeecg.modules.demo.customManager.service.ICustomInfoService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 客户信息表
 * @Author: jeecg-boot
 * @Date:   2024-07-21
 * @Version: V1.0
 */
@Api(tags="客户信息表")
@RestController
@RequestMapping("/customManager/customInfo")
@Slf4j
public class CustomInfoController extends JeecgController<CustomInfo, ICustomInfoService> {
	@Autowired
	private ICustomInfoService customInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param customInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "客户信息表-分页列表查询")
	@ApiOperation(value="客户信息表-分页列表查询", notes="客户信息表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<CustomInfo>> queryPageList(CustomInfo customInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		if(log.isInfoEnabled()){
			log.info("queryPageList req:{}", JSONUtil.toJsonStr(customInfo));
		}
		QueryWrapper<CustomInfo> queryWrapper = QueryGenerator.initQueryWrapper(customInfo, req.getParameterMap());
		Page<CustomInfo> page = new Page<CustomInfo>(pageNo, pageSize);
		IPage<CustomInfo> pageList = customInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param customInfo
	 * @return
	 */
	@AutoLog(value = "客户信息表-添加")
	@ApiOperation(value="客户信息表-添加", notes="客户信息表-添加")
	@RequiresPermissions("customManager:custom_info:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody CustomInfo customInfo) {
		customInfoService.save(customInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param customInfo
	 * @return
	 */
	@AutoLog(value = "客户信息表-编辑")
	@ApiOperation(value="客户信息表-编辑", notes="客户信息表-编辑")
	@RequiresPermissions("customManager:custom_info:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody CustomInfo customInfo) {
		customInfoService.updateById(customInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "客户信息表-通过id删除")
	@ApiOperation(value="客户信息表-通过id删除", notes="客户信息表-通过id删除")
	@RequiresPermissions("customManager:custom_info:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		customInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "客户信息表-批量删除")
	@ApiOperation(value="客户信息表-批量删除", notes="客户信息表-批量删除")
	@RequiresPermissions("customManager:custom_info:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.customInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "客户信息表-通过id查询")
	@ApiOperation(value="客户信息表-通过id查询", notes="客户信息表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<CustomInfo> queryById(@RequestParam(name="id",required=true) String id) {
		CustomInfo customInfo = customInfoService.getById(id);
		if(customInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(customInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param customInfo
    */
    @RequiresPermissions("customManager:custom_info:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CustomInfo customInfo) {
        return super.exportXls(request, customInfo, CustomInfo.class, "客户信息表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("customManager:custom_info:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CustomInfo.class);
    }

}
