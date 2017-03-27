# 1 Introduction
Order service.

# 2 Model
## 2.1 OrderView
| field name        | field type        | comments  |
|----|----|----|
| id                | String            | uuid      |
| createdAt         | ZonedDateTime     | |
| lastModifiedAt    | ZonedDateTime     | |
| completedAt       | ZonedDateTime     | |
| customerId        | String            | | 
| lineItems         | List<LineItemView>| |
| totalPrice        | Money             | |
| shippingAddress   | AddressView       | |
| orderState        | OrderState        | |

## 2.2 LineItemView 
| field name        | field type        | comments  |
|----|----|----|
| id                | String            | uuid      |
| productId         | String            | |
| name              | LocalizedString   | |
| variantId         | Integer           | | 
| sku               | String            | |
| images            | List<ImageView>   | |
| price             | Money             | |
| totalPrice        | Money             | |
| quantity          | Integer           | |

## 2.3 ImageView
| field name        | field type        | comments |
|----|----|----|
| url               | String            | |
| label             | String            | |

## 2.4 AddressView
| field name        | field type        | comments |
|----|----|----|
| id                | String            | |
| createdAt         | ZonedDateTime            | |
| lastModifiedAt    | ZonedDateTime            | |
| fullName          | String            | |
| zip               | String            | |
| phone             | String            | |
| firstLine         | String            | |
| secondLine        | String            | |
| country           | String            | |
| state             | String            | |
| city              | String            | |

# 3 Api
## 3.1 get order by orderId
- Path: /{orderId}
- Method: GET
- Payload: orderId -- NotNull
- Response: OrderView

## 3.2 get order by customerId
- Path: /
- Method: GET
- Payload: customerId -- NotNull
- Response: List<OrderView>
- Sample: {RootUrl}?customerId=ERTAYDASD-ADAVFCA-SADSDASDA-SCAS

## 3.3 update order
- Path: /{orderId}
- Method: PUT
- Payload: orderId --NotNull, UpdateRequest --NotNull
- Response: OrderView

## 3.3 delete order
- Path: /{orderId}
- Method: DELETE
- Payload: orderId --NotNull
- Response: NONE

# 4 Actions
Actions for update cart
## 4.1 AddLineItem
```Java

  @NotNull
  private String productId;

  @NotNull
  private Integer variantId;

  @NotNull
  @Min(1)
  private Integer quantity;
```
## 4.2 RemoveLineItem
```Java

  @NotNull
  private String lineItemId;

  private Integer quantity;
```
## 4.3 SetLineItemQuantity
```Java
  @NotNull
  private String lineItemId;

  @NotNull
  @Min(1)
  private Integer quantity;
```
## 4.4 SetShippingAddress
```Java
  @NotNull
  private String addressId;
```