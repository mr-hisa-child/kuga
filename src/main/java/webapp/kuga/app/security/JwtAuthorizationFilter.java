package webapp.kuga.app.security;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import webapp.kuga.util.StringUtil;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    final private Algorithm algorithm;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.algorithm = Algorithm.HMAC512("test");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstant.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        // AuthorizationヘッダのBearer Prefixである場合
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        if (Objects.isNull(authentication)) {
            // 401エラー
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtil.isEmpty(token)) {
            return null;
        }

        JWTVerifier verifier = JWT.require(this.algorithm).build();

        try {
            DecodedJWT jwt = verifier.verify(token);

            return new UsernamePasswordAuthenticationToken(
                    new LoginUser(jwt.getClaim("id").asString()), null,
                    null);
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstant.HEADER_STRING);
        if (token == null || !token.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            return null;
        }
        return token.substring(SecurityConstant.TOKEN_PREFIX.length());
    }
}
