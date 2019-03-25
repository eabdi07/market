package com.kata;

import com.kata.enums.MarketErrorCodeEnum;
import com.kata.exception.BadRequestException;
import com.kata.exception.MarketException;
import com.kata.model.Product;
import com.kata.service.SimplePriceService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SimplePriceTest
{
    private SimplePriceService simplePriceService;

    @Test
    public void should_return_10_when_default_price_is_5_and_quantity_is_2() throws MarketException
    {
        //given
        simplePriceService = new SimplePriceService();
        Product product = Product.builder().name("Bean").price(5F).build();
        int quantity = 2;

        //when
        float price = simplePriceService.calculatePrice(product, quantity);

        //then
        assertTrue( price == 10 );
    }

    @Test
    public void should_throw_bad_request_exception_with_message_mrkt_00001_when_product_is_null() throws MarketException {
        //given
        simplePriceService = new SimplePriceService();

        //when
        BadRequestException exception = Assertions.assertThrows(BadRequestException.class,
                () -> simplePriceService.calculatePrice(null, 2));
        //then
        assertTrue(exception.getMessage().equals(MarketErrorCodeEnum.MRKT_00001.name()));
    }
}
