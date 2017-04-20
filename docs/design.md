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
#### 4.1.1  Topic
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

### 4.2 Consumer Of Order Payed Event
When an order is payed by `payment` service, it will produce an event, the order consume this event, and change order's status.
#### 4.2.1 Subscribe
`order-payment-payed`
#### 4.2.2 Model

#### 4.2.3 Workflow
1. get order id, payment id, pay result and result message from event message
2. validate if the order with order id
3. change order's status and save the pay result

### 4.3 Consumer Of Order Reserved Event
When an order is reserved by inventory service, it will produce an event, the order consume this event, and change order's status.
#### 4.3.1 Subscribe
`order-inventory-reserved`
#### 4.3.2 Model

#### 4.3.3 Workflow
1. get order id, reserve result event message
2. validate if the order with order id
3. change order's status and save the reserved result
