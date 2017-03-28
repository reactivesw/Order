package io.reactivesw.order.application.service

import io.reactivesw.model.Money
import io.reactivesw.order.application.model.CartView
import io.reactivesw.order.application.model.LineItemView
import io.reactivesw.order.application.model.mapper.OrderMapper
import io.reactivesw.order.domain.model.Order
import io.reactivesw.order.domain.service.OrderService
import io.reactivesw.order.infrastructure.exception.CheckoutFailedException
import io.reactivesw.order.infrastructure.update.UpdateAction
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification

/**
 * order application test.
 */
class OrderApplicationTest extends Specification {

    def restClient = Mock(OrderRestClient)

    def orderService = Mock(OrderService)

    OrderApplication application;

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

        application = new OrderApplication(restClient: restClient, orderService: orderService)
    }

    def "Test 1.1: Checkout correct."() {
        when:
        restClient.getCart(_) >> cartView
        orderService.createWithSample(_) >> order
        application.checkout("cartId")
        then:
        noExceptionThrown()
    }

    def "Test 1.2: Checkout with cart not exits."() {
        when:
        restClient.getCart(_) >> { throw new HttpClientErrorException(HttpStatus.NOT_FOUND) }
        application.checkout("cartId")
        then:
        thrown(CheckoutFailedException)
    }

    def "Test 2.1 update order with actions"() {
        when:
        orderService.updateOrder(_, _, _) >> order
        application.updateOrder("orderId", 1, new ArrayList<UpdateAction>())
        then:
        noExceptionThrown()
    }


}