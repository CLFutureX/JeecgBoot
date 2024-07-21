package org.jeecg.modules.demo.customManager.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 客户信息表
 * @Author: jeecg-boot
 * @Date:   2024-07-21
 * @Version: V1.0
 */
@Data
@TableName("custom_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="custom_info对象", description="客户信息表")
public class CustomInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
    private String orgCode;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String customName;
	/**客户联系方式*/
	@Excel(name = "客户联系方式", width = 15)
    @ApiModelProperty(value = "客户联系方式")
    private String customTel;
	/**客户地址*/
	@Excel(name = "客户地址", width = 15)
    @ApiModelProperty(value = "客户地址")
    private String customAddr;
	/**业务员名称*/
	@Excel(name = "业务员名称", width = 15)
    @ApiModelProperty(value = "业务员名称")
    private String businessName;
	/**类别/系别*/
	@Excel(name = "类别/系别", width = 15)
    @ApiModelProperty(value = "类别/系别")
    private Integer type;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
    private Integer status;
	/**检查项*/
	@Excel(name = "检查项", width = 15)
    @ApiModelProperty(value = "检查项")
    private Integer checkItem;
	/**是否建群*/
	@Excel(name = "是否建群", width = 15)
    @ApiModelProperty(value = "是否建群")
    private Integer createGroup;
	/**清洗情况*/
	@Excel(name = "清洗情况", width = 15)
    @ApiModelProperty(value = "清洗情况")
    private Integer clearType;
	/**维修情况*/
	@Excel(name = "维修情况", width = 15)
    @ApiModelProperty(value = "维修情况")
    private Integer repairType;
	/**安装情况*/
	@Excel(name = "安装情况", width = 15)
    @ApiModelProperty(value = "安装情况")
    private Integer installType;
	/**安装单位*/
	@Excel(name = "安装单位", width = 15)
    @ApiModelProperty(value = "安装单位")
    private String installCompany;
	/**项目经理*/
	@Excel(name = "项目经理", width = 15)
    @ApiModelProperty(value = "项目经理")
    private String projectManager;
}
