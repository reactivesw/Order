# Order
This doc describes the design of order service.

## 1. Basic Features


## 2. Model Design
- View model: [View Model](./api.md)

## 3. Workflow

## 4. Event Design
This part describes the events that this service produce or consume.

### 4.1 Producer Of Place Order Event
When the customer place an order, then this system will produce a event.
#### 4.1.1  Topic Name
`reactivesw-order-created`
#### 4.1.2 Topic Model
- Message Data:
```java
  String orderId;

  String shippingAddressId;
  
  String paymentId;
  
  String shippingAddressId;
  
  List<InventoryRequest> iventoryRequests;
  
  Money totalAmount;
```
- InventoryRequest:
```java
  String sku;
  Integer quantity;
```
- Money:
```java
  String currencyCode;
  String centAmount;
```


