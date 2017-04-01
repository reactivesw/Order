package io.reactivesw.order.domain.service

import io.reactivesw.order.domain.model.value.LineItem
import io.reactivesw.order.domain.model.value.MoneyValue
import spock.lang.Specification

/**
 * line item service test.
 */
class LineItemServiceTest extends Specification {

    LineItemService lineItemService = new LineItemService()

    LineItem lineItem

    def setup() {
        lineItem = new LineItem(id: "lineItemId", productId: "productId", variantId: 1, price: new MoneyValue("USD", 100), totalPrice: new MoneyValue("USD", 100), quantity: 1)

        lineItemService = new LineItemService()
    }

    def "Test 1.1: calculate line item"() {
        when:
        lineItemService.calculateItemPrice(lineItem)
        then:
        noExceptionThrown()
    }

    def "Test 1.2: calculate line item with price null"() {
        lineItem.setPrice(null)
        when:
        lineItemService.calculateItemPrice(lineItem)
        then:
        noExceptionThrown()
    }

    def "Test 1.3: calculate line item with quantity 0"() {
        lineItem.setQuantity(null)
        when:
        lineItemService.calculateItemPrice(lineItem)
        then:
        noExceptionThrown()
    }

}
