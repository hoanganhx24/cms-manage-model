package com.example.cmsmanagemodel.service.Auth;

import com.example.cmsmanagemodel.repository.InvalidateTokenRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    @NonFinal
    @Value("${jwt.signerkey}")
    private String signerKey;

    @NonFinal
    @Value("${jwt.valid.duration}")
    private int validDuration;

    @NonFinal
    @Value("${jwt.refreshable.duration}")
    private int refreshableDuration;

    private final InvalidateTokenRepository invalidateTokenRepository;

    public AuthServiceImpl(InvalidateTokenRepository invalidateTokenRepository) {
        this.invalidateTokenRepository = invalidateTokenRepository;
    }

    @Override
    public SignedJWT verifyToken(String token, boolean isRefresh) throws AuthenticationException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            JWSVerifier verifier = new MACVerifier(signerKey.getBytes());
            boolean isSignatureValid = signedJWT.verify(verifier);
            if (!isSignatureValid) {
                throw new AuthenticationException("Invalid JWT signature");
            }

            Date expiryTime = (isRefresh)
                    ? new Date(signedJWT.getJWTClaimsSet()
                    .getIssueTime()
                    .toInstant()
                    .plus(refreshableDuration, ChronoUnit.SECONDS).toEpochMilli())
                    :signedJWT.getJWTClaimsSet().getExpirationTime();

            if ((expiryTime == null || !expiryTime.after(new Date())) && !isRefresh) {
                throw new AuthenticationException("Expired JWT token");
            }

            if (invalidateTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
                throw  new AuthenticationException("Invalid JWT token");
            }

            return signedJWT;
        } catch (ParseException e) {
            throw new AuthenticationException("Invalid JWT format");
        } catch (JOSEException e) {
            throw new AuthenticationException("JWT verification failed");
        }
    }
}
