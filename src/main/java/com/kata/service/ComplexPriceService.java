package com.kata.service;

import com.kata.enums.MarketErrorCodeEnum;
import com.kata.exception.BadRequestException;
import com.kata.exception.MarketException;
import com.kata.model.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComplexPriceService {

    private final SimplePriceService simplePriceService;

    public ComplexPriceService(SimplePriceService simplePriceService) {
        this.simplePriceService = simplePriceService;
    }

    public float calculatePrice(Product product, int productQuantity) throws MarketException {
        if (null == product) {
            log.error(MarketErrorCodeEnum.MRKT_00001.getCode(), MarketErrorCodeEnum.MRKT_00001.name());
            throw new BadRequestException(MarketErrorCodeEnum.MRKT_00001.name());
        }
        if (null == simplePriceService) {
            log.error(MarketErrorCodeEnum.MRKT_00002.getCode(), MarketErrorCodeEnum.MRKT_00002.name());
            throw new BadRequestException(MarketErrorCodeEnum.MRKT_00002.name());
        }
        return isSimpleProduct(product, productQuantity)
                ? simplePriceService.calculatePrice(product, productQuantity)
                : calculateComplexPrice(product, productQuantity);
    }

    private boolean isSimpleProduct(Product product, int productQuantity) {
        return null == product.getDiscounts()
                || product.getDiscounts().getNumber() == 0
                || product.getDiscounts().getNumber() > productQuantity;
    }

    private float calculateComplexPrice(Product product, int productQuantity) throws MarketException {
        int productQuantityWithoutDiscount = productQuantity % product.getDiscounts().getNumber();
        float productQuantityWithoutDiscountPrice = simplePriceService.calculatePrice(product, productQuantityWithoutDiscount);
        float productQuantityWithDiscountPrice =
                (productQuantity - productQuantityWithoutDiscount) * product.getDiscounts().getValue()
                        / product.getDiscounts().getNumber();
        return productQuantityWithDiscountPrice + productQuantityWithoutDiscountPrice;
    }
}
