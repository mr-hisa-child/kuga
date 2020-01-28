package webapp.kuga.app.security;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import webapp.kuga.util.JsonUtil;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(100L);

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(SecurityConstant.LOGIN_URL, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) {

        try {
            Credentials principal = JsonUtil.convert(request.getInputStream(), Credentials.class);

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    principal.getEmail(), principal.getPassword());

            setDetails(request, authRequest);

            return authenticationManager.authenticate(authRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 認証に成功した場合の処理
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        AuthUser authUser = (AuthUser) auth.getPrincipal();
        Date issuedAt = new Date();
        Date notBefore = new Date(issuedAt.getTime());
        Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);
        String token = JWT.create()
                .withIssuedAt(issuedAt)
                .withNotBefore(notBefore)
                .withExpiresAt(expiresAt)
                .withClaim("id", authUser.getAccountId())
                .sign(Algorithm.HMAC512("test"));

        res.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX + token);
    }
}
