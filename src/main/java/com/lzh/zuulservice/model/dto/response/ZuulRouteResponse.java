package com.lzh.zuulservice.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lzh.common.model.dto.response.RestfulResponse;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 
 */
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class ZuulRouteResponse extends RestfulResponse {
    private static final long serialVersionUID = 7883775815440213351L;

//    @JsonProperty("mobile")
////    @ApiModelProperty(value = "手机号", example = "18888888888", required = true)
//    private String mobile;

//    @JsonProperty("balance")
////    @ApiModelProperty(value = "用户的初始化余额", example = "100000000", required = true)
//    private Long balance;
    
	@JsonProperty("id")    
    private Long id;

	@JsonProperty("path") 
    private String path;

	@JsonProperty("serviceId") 
    private String serviceId;

	@JsonProperty("url") 
    private String url;

	@JsonProperty("retryable") 
    private Boolean retryable;

	@JsonProperty("enabled") 
    private Boolean enabled;

	@JsonProperty("stripPrefix") 
    private Boolean stripPrefix;

	@JsonProperty("remarks") 
    private String remarks;

}
