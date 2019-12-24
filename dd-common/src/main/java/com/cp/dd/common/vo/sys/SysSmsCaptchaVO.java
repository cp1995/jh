package com.cp.dd.common.vo.sys;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 短信验证码对象
 *
 * @author chengp
 */
@Data
@ApiModel(description = "短信验证码对象")
public class SysSmsCaptchaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("手机")
    private String phone;

    @ApiModelProperty("验证码")
    private String code;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @ApiModelProperty(value = "出生日期")
    private LocalDateTime createTime;

}
