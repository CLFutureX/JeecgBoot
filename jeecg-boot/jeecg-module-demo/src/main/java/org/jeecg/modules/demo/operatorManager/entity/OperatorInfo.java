package org.jeecg.modules.demo.operatorManager.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 大金操作记录表
 * @Author: jeecg-boot
 * @Date:   2024-07-21
 * @Version: V1.0
 */
@Data
@TableName("dj_operator")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="dj_operator对象", description="大金操作记录表")
public class OperatorInfo implements Serializable {
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
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**操作人*/
	@Excel(name = "操作人", width = 15)
    @ApiModelProperty(value = "操作人")
    private String userName;
	/**操作人id*/
	@Excel(name = "操作人id", width = 15)
    @ApiModelProperty(value = "操作人id")
    private String userId;
	/**操作类型*/
	@Excel(name = "操作类型", width = 15)
    @ApiModelProperty(value = "操作类型")
    private String operatorType;
	/**操作前数据*/
	@Excel(name = "操作前数据", width = 15)
    @ApiModelProperty(value = "操作前数据")
    private String operatorBefore;
	/**操作后数据*/
	@Excel(name = "操作后数据", width = 15)
    @ApiModelProperty(value = "操作后数据")
    private String operatorAfter;
	/**操作数据标识*/
	@Excel(name = "操作数据标识", width = 15)
    @ApiModelProperty(value = "操作数据标识")
    private String operatorDataId;
	/**数据类型*/
	@Excel(name = "数据类型", width = 15)
    @ApiModelProperty(value = "数据类型")
    private Integer type;
}
