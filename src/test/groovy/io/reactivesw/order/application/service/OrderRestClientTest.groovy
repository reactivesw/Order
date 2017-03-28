package io.reactivesw.order.application.service

import io.reactivesw.exception.NotExistException
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
        thrown(NotExistException)
    }

    def "Test 1.3: get cart failed"() {
        when:
        restTemplate.getForObject(_, _) >> { throw new HttpClientErrorException(HttpStatus.CONFLICT) }
        orderRestClient.getCart("cartId")
        then:
        thrown(HttpClientErrorException)
    }

    def "Test 2.1: get product"() {
        when:
        restTemplate.getForObject(_, _) >> cart
        orderRestClient.getProduct("productId", 1)
        then:
        noExceptionThrown()
    }

    def "Test 2.2: get product failed"() {
        when:
        restTemplate.getForObject(_, _) >> { throw new HttpClientErrorException(HttpStatus.NOT_FOUND) }
        orderRestClient.getProduct("cartId", 1)
        then:
        thrown(NotExistException)
    }

    def "Test 2.3: get product failed"() {
        when:
        restTemplate.getForObject(_, _) >> { throw new HttpClientErrorException(HttpStatus.CONFLICT) }
        orderRestClient.getProduct("cartId", 1)
        then:
        thrown(HttpClientErrorException)
    }
}
