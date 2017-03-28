package io.reactivesw.order.domain.service

import io.reactivesw.exception.ConflictException
import io.reactivesw.exception.NotExistException
import io.reactivesw.model.Money
import io.reactivesw.order.application.model.CartView
import io.reactivesw.order.application.model.LineItemView
import io.reactivesw.order.application.model.mapper.OrderMapper
import io.reactivesw.order.domain.model.Order
import io.reactivesw.order.infrastructure.repository.OrderRepository
import io.reactivesw.order.infrastructure.update.UpdateAction
import io.reactivesw.order.infrastructure.update.UpdaterService
import spock.lang.Specification

/**
 * order service test.
 */
class OrderServiceTest extends Specification {

    def orderRepository = Mock(OrderRepository)

    def orderUpdater = Mock(UpdaterService)

    OrderService orderService

    CartView cartView

    LineItemView lineItemView

    Order order;

    def setup() {
        lineItemView = new LineItemView(id: "lineItemId", productId: "productId", variantId: 1, price: new Money("USD", 100), totalPrice: new Money("USD", 100), quantity: 1)
        List<LineItemView> lineItemViewList = new ArrayList<>()
        lineItemViewList.add(lineItemView)
        cartView = new CartView(id: "cartId", customerId: "customerId", lineItems: lineItemViewList, totalPrice: new Money("USD", 100), country: "USA", currencyCode: "USD")

        order = OrderMapper.build(cartView)
        order.setId("orderId")
        order.setVersion(1)
        orderService = new OrderService(orderRepository: orderRepository, orderUpdater: orderUpdater)
    }

    def "Test 1.1: create with sample"() {
        when:
        orderRepository.save(_) >> order
        orderService.createWithSample(order)
        then:
        noExceptionThrown()
    }

    def "Test 2.1: get by order id"() {
        when:
        orderRepository.findOne(_) >> order
        orderService.getById("orderId")
        then:
        noExceptionThrown()
    }

    def "Test 2.2: get by order id that not exist"() {
        when:
        orderRepository.findOne(_) >> null
        orderService.getById("orderId")
        then:
        thrown(NotExistException)
    }

    def "Test 3.1: get by customer id"() {
        List<Order> orders = new ArrayList<>()
        orders.add(order)
        when:
        orderRepository.findByCustomerId("customerId") >> orders
        orderService.getByCustomerId("customerId")
        then:
        noExceptionThrown()
    }

    def "Test 4.1: update order with actions"() {
        when:
        orderRepository.findOne(_) >> order
        orderService.updateOrder("orderId", 1, new ArrayList<UpdateAction>())
        then:
        noExceptionThrown()
    }

    def "Test 5.1: delete order"() {
        when:
        orderRepository.findOne(_) >> order
        orderService.deleteOrder("orderId", 1)
        then:
        noExceptionThrown()
    }

    def "Test 5.2: delete order"() {
        when:
        orderRepository.findOne(_) >> order
        orderService.deleteOrder("orderId", 2)
        then:
        thrown(ConflictException)
    }

}
