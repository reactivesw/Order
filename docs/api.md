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

## 2.5 PlaceOrderRequest
| field name        | field type        | comments |
|----|----|----|
| customerId        | String            | NOT NULL|
| addressId         | String            | NOT NULL|
| creditCardId      | String            | NOT NULL|
| cartId            | String            | NOT NULL|



# 3 Api
## 3.1 get order by orderId
- Path: /{orderId}
- Method: GET
- Payload: orderId -- NotNull
- Response: OrderView

## 3.2 get order list by customerId
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

## 3.4 Place order
when the order been placed, then the customer can not change it anymore.
- Path: /
- Method: POST
- Payload: PlaceOrderRequest
- Response: order
- Exceptions

| Exception         | means        | http code | customized code|
|----|----|----|
| BuildOrderException | build order failed. | 500| 60001
| ChangeInventoryException | build order failed. | 500| 60002
| PayOrderException | build order failed. | 500| 60003

- Exception body:
```json
{
  "code": customized code,
  "message": customized message
}
```


# 4 Actions
Actions for update order
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