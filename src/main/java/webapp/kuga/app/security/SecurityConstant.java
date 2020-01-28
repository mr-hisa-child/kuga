package webapp.kuga.app.security;

public class SecurityConstant {
    public static final String SECRET = "nyasbasamplesecret";
    public static final long ACCESS_EXPIRATION_TIME = 1_800_000;
    public static final long REFRESH_EXPIRATION_TIME = 86_400_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String HEADER_REFRESH_TOKEN = "X-Refresh-Token";
    public static final String SIGNUP_URL = "/user/signup";
    public static final String LOGIN_URL = "/signin";
    public static final String LOGIN_ID = "loginId"; // defalut:username
    public static final String PASSWORD = "pass"; // default:password
    public static final String[] PERMIT_PATH = { "/signup/temp", "/signup/regist", "/verify/**", "/password/**" };
    public static final String LOGOUT_URL = "/auth/logout";

}
