package org.jeecg.modules.demo.operatorManager.controller;

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
import org.jeecg.modules.demo.operatorManager.entity.OperatorInfo;
import org.jeecg.modules.demo.operatorManager.service.IOperatorInfoService;

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
 * @Description: 大金操作记录表
 * @Author: jeecg-boot
 * @Date:   2024-07-21
 * @Version: V1.0
 */
@Api(tags="大金操作记录表")
@RestController
@RequestMapping("/operatorManager/operatorInfo")
@Slf4j
public class OperatorInfoController extends JeecgController<OperatorInfo, IOperatorInfoService> {
	@Autowired
	private IOperatorInfoService operatorInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param operatorInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "大金操作记录表-分页列表查询")
	@ApiOperation(value="大金操作记录表-分页列表查询", notes="大金操作记录表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<OperatorInfo>> queryPageList(OperatorInfo operatorInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		if(log.isInfoEnabled()){
			log.info(" queryPageList req:{}", JSONUtil.toJsonStr(operatorInfo));
		}
		QueryWrapper<OperatorInfo> queryWrapper = QueryGenerator.initQueryWrapper(operatorInfo, req.getParameterMap());
		Page<OperatorInfo> page = new Page<OperatorInfo>(pageNo, pageSize);
		IPage<OperatorInfo> pageList = operatorInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param operatorInfo
	 * @return
	 */
	@AutoLog(value = "大金操作记录表-添加")
	@ApiOperation(value="大金操作记录表-添加", notes="大金操作记录表-添加")
	@RequiresPermissions("operatorManager:dj_operator:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody OperatorInfo operatorInfo) {
		operatorInfoService.save(operatorInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param operatorInfo
	 * @return
	 */
	@AutoLog(value = "大金操作记录表-编辑")
	@ApiOperation(value="大金操作记录表-编辑", notes="大金操作记录表-编辑")
	@RequiresPermissions("operatorManager:dj_operator:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody OperatorInfo operatorInfo) {
		operatorInfoService.updateById(operatorInfo);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "大金操作记录表-通过id删除")
	@ApiOperation(value="大金操作记录表-通过id删除", notes="大金操作记录表-通过id删除")
	@RequiresPermissions("operatorManager:dj_operator:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		operatorInfoService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "大金操作记录表-批量删除")
	@ApiOperation(value="大金操作记录表-批量删除", notes="大金操作记录表-批量删除")
	@RequiresPermissions("operatorManager:dj_operator:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.operatorInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "大金操作记录表-通过id查询")
	@ApiOperation(value="大金操作记录表-通过id查询", notes="大金操作记录表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<OperatorInfo> queryById(@RequestParam(name="id",required=true) String id) {
		OperatorInfo operatorInfo = operatorInfoService.getById(id);
		if(operatorInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(operatorInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param operatorInfo
    */
    @RequiresPermissions("operatorManager:dj_operator:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OperatorInfo operatorInfo) {
        return super.exportXls(request, operatorInfo, OperatorInfo.class, "大金操作记录表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("operatorManager:dj_operator:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, OperatorInfo.class);
    }

}
