/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamvnt.utils;

/**
 *
 * @author LamVo
 */
public class MyAppConstants { // mapping all features strings 
    public class DispatchFeaturess {
        public static final String LOGIN_PAGE = "";
        //...
    }
    public class LoginFeatures {
        public static final String INVALID_PAGE = "loginJsp";
        public static final String SEARCH_PAGE = "searchPage";
    }
    public class AddBookFeatures {
        public static final String SHOW_PRODUCT_LIST_CONTROLLER = "showProductList";
    }
    public class CheckOutFeatures {
        public static final String ERROR_PAGE = "errorsPage";
        public static final String SHOW_PRODUCT_LIST_CONTROLLER = "showProductList";
    }
    public class CreateAccFeatures {
        public static final String ERROR_PAGE = "createAccountJsp";
        public static final String LOGIN_PAGE = "";
    }
    public class DeleteAccFeatures {
        public static final String ERROR_PAGE = "errorsPage";
    }
    public class LogOutFeatures {
        public static final String LOGIN_PAGE = "";
    }
    public class SearchFeatures {
        public static final String SEARCH_PAGE = "searchPage";
        public static final String SEARCH_RESULT_PAGE = "searchPage";
    }
    public class ShowProductFeatures {
        public static final String SHOPPING_PAGE = "shoppingPage";
    }
    public class StartAppFeatures {
        public static final String LOGIN_PAGE = "";
        public static final String SEARCH_PAGE = "searchPage";
    }
    public class UpdateAccFeatures {       
        public static final String ERROR_PAGE = "errorsPage";
    }
    public class ViewCartFeatures {        
        public static final String CART_PAGE="cartPage";
    }
}
