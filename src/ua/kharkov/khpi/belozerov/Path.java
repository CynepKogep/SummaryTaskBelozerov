package ua.kharkov.khpi.belozerov;

/**
 * Path holder (jsp pages, controller commands).
 * 
 * @author D.Kolesnikov
 * 
 */
public final class Path {
	
	// pages
	public static final String PAGE__LOGIN = "/login.jsp";
	public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
	public static final String PAGE__LIST_MENU = "/WEB-INF/jsp/client/list_menu.jsp";
	public static final String PAGE__LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
    // page after login -> administrator 
	public static final String PAGE__ADMIN_ONE = "/WEB-INF/jsp/admin/admin_one.jsp";
	public static final String PAGE__ADMIN_TWO = "/WEB-INF/jsp/admin/admin_two.jsp";
	public static final String PAGE__ADMIN_THREE = "/WEB-INF/jsp/admin/admin_three.jsp";
	// 
	public static final String PAGE__CLIENT_ONE = "/WEB-INF/jsp/client/client_one.jsp";
	public static final String PAGE__CLIENT_TWO = "/WEB-INF/jsp/client/client_two.jsp";
	public static final String PAGE__CLIENT_THREE = "/WEB-INF/jsp/client/client_three.jsp";
	
	//
	public static final String PAGE__SETTINGS = "/WEB-INF/jsp/settings.jsp";
	public static final String PAGE__REGISTRATION = "/WEB-INF/jsp/registration.jsp";

	// commands
	public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders";
	public static final String COMMAND__LIST_MENU = "/controller?command=listMenu";
	public static final String COMMAND__LIST_CARD_CLIENT = "/controller?command=listClientCards";
	public static final String COMMAND__LIST_ORDERS_A = "/controller?command=listOrdersA";
	public static final String COMMAND__LIST_MENU_A = "/controller?command=listMenuA";
	public static final String COMMAND__CARDS_FOR_ADMIN = "/controller?command=cardsAdminCommand";
	
	
	
}