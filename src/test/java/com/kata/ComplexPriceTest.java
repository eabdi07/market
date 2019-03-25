package com.kata;

import com.kata.enums.MarketErrorCodeEnum;
import com.kata.exception.BadRequestException;
import com.kata.exception.MarketException;
import com.kata.model.Discount;
import com.kata.model.Product;
import com.kata.service.ComplexPriceService;
import com.kata.service.SimplePriceService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ComplexPriceTest {

    @Mock
    private SimplePriceService simplePriceService;

    private ComplexPriceService complexPriceService;

    @Test
    public void should_return_5_as_price_when_product_quantity_is_2_and_exist_discount_5_for_2_product_and_unitary_price_is_3() throws MarketException {
        //given
        complexPriceService = new ComplexPriceService(simplePriceService);

        Product product = Product.builder()
                .name("apple")
                .price(3F)
                .discounts(Discount.builder().number(2).value(5F).build())
                .build();
        int quantity = 2;

        //when
        float price = complexPriceService.calculatePrice(product, quantity);

        //then
        assertTrue( price == 5 );
    }

    @Test
    public void should_throw_bad_request_exception_with_message_mrkt_00001_when_product_is_null() {
        //given
        complexPriceService = new ComplexPriceService(simplePriceService);

        //when
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class,
                () -> complexPriceService.calculatePrice(null, 2));
        //then
        assertTrue(exception.getMessage().equals(MarketErrorCodeEnum.MRKT_00001.name()));
    }

    @Test
    public void should_throw_bad_request_exception_with_message_mrkt_00002_when_simple_price_service_dependency_is_unsatisfied() {
        //given
        complexPriceService = new ComplexPriceService(null);
        Product product = Product.builder()
                .name("apple")
                .discounts(Discount.builder().number(3).value(1F).build())
                .build();
        //when
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class,
                () -> complexPriceService.calculatePrice(product, 2));
        //then
        assertTrue(exception.getMessage().equals(MarketErrorCodeEnum.MRKT_00002.name()));
    }
}
