package com.kata.service;

import com.kata.enums.MarketErrorCodeEnum;
import com.kata.exception.BadRequestException;
import com.kata.exception.MarketException;
import com.kata.model.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimplePriceService {
    public float calculatePrice(Product product, int productQuantity) throws MarketException {
        if(null == product){
            log.error(MarketErrorCodeEnum.MRKT_00001.getCode(), MarketErrorCodeEnum.MRKT_00001.name());
            throw new BadRequestException(MarketErrorCodeEnum.MRKT_00001.name());
        }
        return product.getPrice()*productQuantity;
    }
}
