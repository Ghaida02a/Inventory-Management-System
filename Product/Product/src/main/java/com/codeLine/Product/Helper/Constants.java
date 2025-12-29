package com.codeLine.Product.Helper;

public class Constants {
    //Product constants
    public static final String PRODUCT_ID = "Product ID cannot be null";
    public static final String PRODUCT_IS_NOT_ACTIVE_OR_NOT_FOUND = "Product is either not active or not found";

    //Product create Request Validations
    public static final String PRODUCT_NOT_FOUND = "Product not found";
    public static final String PRODUCT_REQUEST_IS_NULL = "Product create request cannot be null";
    public static final String PRODUCT_IS_NULL = "Product cannot be null";
    public static final String PRODUCT_NAME_IS_NULL = "Product name cannot be null";
    public static final String PRODUCT_PRICE_IS_NEGATIVE = "Product price cannot be negative or zero";
    public static final Integer PRODUCT_PRICE_IS_LESS_THAN_ZERO = 0;
    public static final String PRODUCT_CATEGORY_IS_NULL = "Product category cannot be null";
    public static final String PRODUCT_AVAILABLE_QUANTITY_IS_NULL = "Product available quantity cannot be null";
    public static final Integer PRODUCT__AVAILABLE_QUANTITY_IS_LESS_THAN_ZERO = 0;
    public static final String PRODUCT_CREATE_RESPONSE_IS_NULL = "Product create response cannot be null";
    public static final String PRODUCT_ACTIVE_LIST_IS_EMPTY = "Product Active list is empty";

    //Product update Request Validations
    public static final String PRODUCT_ID_UPDATE_REQUEST_ID_IS_NULL = "Product ID request update cannot be null";
    public static final String PRODUCT_REQUEST_TO_UPDATE_IS_NULL = "Product update request cannot be null";
    public static final String PRODUCT_UPDATE_RESPONSE_IS_NULL = "Product update response cannot be null";
    public static final String PRODUCT_UPDATE_REQUEST_IS_NULL = "Product Update Request cannot be empty and must contain an ID";
    public static final String PRODUCT_UPDATE_REQUEST_ID_NOT_VALID = "Product ID in update request is either null or not active";



}
