package io.reactivesw.order.application.service

import io.reactivesw.order.application.model.InventoryRequest
import io.reactivesw.order.application.model.PayRequest
import io.reactivesw.order.infrastructure.exception.*
import org.springframework.http.HttpStatus
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

/**
 * order rest client test.
 */
class OrderRestClientTest extends Specification {

    def restTemplate = Mock(RestTemplate)

    OrderRestClient orderRestClient;

    def cart

    def product

    def address

    def paymentView

    def setup() {
        orderRestClient = new OrderRestClient(restTemplate: restTemplate)
    }

    def "Test 1.1: get cart"() {
        when:
        restTemplate.getForObject(_, _) >> cart
        orderRestClient.getCart("cartId")
        then:
        noExceptionThrown()
    }

    def "Test 1.2: get cart failed"() {
        when:
        restTemplate.getForObject(_, _) >> { throw new HttpClientErrorException(HttpStatus.NOT_FOUND) }
        orderRestClient.getCart("cartId")
        then:
        thrown(CheckoutCartException)
    }

    def "Test 1.3: get cart failed"() {
        when:
        restTemplate.getForObject(_, _) >> { throw new HttpClientErrorException(HttpStatus.CONFLICT) }
        orderRestClient.getCart("cartId")
        then:
        thrown(CheckoutCartException)
    }

    def "Test 2.1: get product"() {
        when:
        restTemplate.getForObject(_, _) >> product
        orderRestClient.getProduct("productId", 1)
        then:
        noExceptionThrown()
    }

    def "Test 2.2: get product failed"() {
        when:
        restTemplate.getForObject(_, _) >> { throw new HttpClientErrorException(HttpStatus.NOT_FOUND) }
        orderRestClient.getProduct("productId", 1)
        then:
        thrown(GetProductException)
    }

    def "Test 2.3: get product failed"() {
        when:
        restTemplate.getForObject(_, _) >> { throw new HttpClientErrorException(HttpStatus.CONFLICT) }
        orderRestClient.getProduct("productId", 1)
        then:
        thrown(GetProductException)
    }

    def "Test 3.1: get Address"() {
        when:
        restTemplate.getForObject(_, _) >> address
        orderRestClient.getAddress("addressId")
        then:
        noExceptionThrown()
    }

    def "Test 3.2: get address failed"() {
        when:
        restTemplate.getForObject(_, _) >> { throw new HttpClientErrorException(HttpStatus.NOT_FOUND) }
        orderRestClient.getAddress("addressId")
        then:
        thrown(GetAddressException)
    }

    def "Test 3.3: get address failed"() {
        when:
        restTemplate.getForObject(_, _) >> { throw new HttpClientErrorException(HttpStatus.CONFLICT) }
        orderRestClient.getAddress("addressId")
        then:
        thrown(GetAddressException)
    }

    def "Test 4.1: change product inventory"() {
        when:
        orderRestClient.changeProductInventory(new ArrayList<InventoryRequest>())
        then:
        noExceptionThrown()
    }

    def "Test 4.2: change product inventory failed"() {
        when:
        restTemplate.put(_, _) >> { throw new HttpClientErrorException(HttpStatus.NOT_FOUND) }
        orderRestClient.changeProductInventory(new ArrayList<InventoryRequest>())
        then:
        thrown(ReserveInventoryException)
    }

    def "Test 5.1: pay order "() {
        when:
        restTemplate.postForObject(_, _, _) >> paymentView
        orderRestClient.pay(new PayRequest())
        then:
        noExceptionThrown()
    }

    def "Test 5.2: pay order failed "() {
        when:
        restTemplate.postForObject(_, _, _) >> { throw new HttpClientErrorException(HttpStatus.NOT_FOUND) }
        orderRestClient.pay(new PayRequest())
        then:
        thrown(PayOrderException)
    }
}
