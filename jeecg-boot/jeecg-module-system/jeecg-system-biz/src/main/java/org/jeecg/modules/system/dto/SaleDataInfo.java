package org.jeecg.modules.system.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleDataInfo {

    private Integer type;

    private BigDecimal total;

    private BigDecimal value;

}
