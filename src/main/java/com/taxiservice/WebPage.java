package com.taxiservice;

/**
 * WebPage class that displays pages to specific resources.
 *
 * @author Maryna Lendiel
 */
public class WebPage {
    private final String pageUrl;
    private final boolean isRedirect;
    private String errorMessage;

    //common pages
    public static final String MAIN_PAGE = "/index.jsp";
    public static final String ERROR_PAGE = "/jsp/common/error.jsp";

    //client pages
    public static final String CUSTOMER_ACCOUNT_PAGE = "/jsp/client/clientAccount.jsp";
    public static final String CAR_INFO_PAGE = "/jsp/client/carInformation.jsp";
    public static final String ALTERNATIVE_ORDER_PAGE = "/jsp/client/alternativeOrder.jsp";
    public static final String SUCCESSFUL_ORDER_PAGE = "/jsp/client/successfulOrder.jsp";

    //admin pages
    public static final String ADMIN_ACCOUNT_PAGE = "/jsp/admin/adminAccount.jsp";
    public static final String AUTO_PARK_PAGE = "/jsp/admin/autoPark.jsp";
    public static final String ORDER_CARS_PAGE = "/jsp/admin/carsInOrderInformation.jsp";

    public WebPage(String pageUrl, boolean isRedirect) {
        this.pageUrl = pageUrl;
        this.isRedirect = isRedirect;
    }

    public WebPage(String pageUrl, boolean isRedirect, String errorMessage) {
        this.pageUrl = pageUrl;
        this.isRedirect = isRedirect;
        this.errorMessage = errorMessage;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
