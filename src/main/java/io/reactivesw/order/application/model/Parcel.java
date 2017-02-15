package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.ZonedDateTime;

/**
 * Created by umasuo on 16/11/17.
 */
@ApiModel(description = "A parcel stores the information about the appearance and the movement of" +
    " a parcel")
@Data
public class Parcel {

  @ApiModelProperty(value = "Unique id of the parcel", required = true)
  private String id;

  @ApiModelProperty(required = true)
  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
  private ZonedDateTime createdAt;

  @ApiModelProperty(required = false)
  private ParcelMeasurements measurements;

  @ApiModelProperty(required = false)
  private TrackingData trackingData;

}
