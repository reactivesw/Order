package io.reactivesw.order.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.reactivesw.model.Money;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceView {

  private String id;

  private Money value;

}
