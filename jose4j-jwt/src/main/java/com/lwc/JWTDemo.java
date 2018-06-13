package com.lwc;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.ErrorCodes;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Created by liwenchao on 2018-06-11.
 *
 * @author liwenchao
 */
public class JWTDemo {

    public static void main(String[] args) throws Exception {

        /**
         * JSON Web Token是一种紧凑的url安全的方式，用于表示要在双方之间传输的声明/属性。//这个示例演示了生成和使用签名的JWT
         */

        RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);

        // 生成一个RSA密钥对，它将被用于对JWT的签名和验证，包在JWK中。
        rsaJsonWebKey.setKeyId("k1");

        // 创建声明，这将是JWT的内容
        JwtClaims claims = new JwtClaims();
        claims.setIssuer("Issuer"); // 谁创建了这个标记并签名
        claims.setAudience("Audience"); // 令牌寄给谁
        claims.setExpirationTimeMinutesInTheFuture(10); // 令牌过期的时间(10分钟后)
        claims.setGeneratedJwtId(); // 令牌的唯一标识符
        claims.setIssuedAtToNow(); // 什么时间发出/创建令牌(现在)
        claims.setNotBeforeMinutesInThePast(2); // 令牌失效的时间(2分钟前)
        claims.setSubject("subject"); // 主题/主体是令牌的对象
        claims.setClaim("email", "mail@example.com"); // 可以添加关于主题的其他声明/属性
        List<String> groups = Arrays.asList("group-one", "other-group", "group-three");
        claims.setStringListClaim("groups", groups); // 多值声明也可以工作，最终将作为JSON数组


        // JWT是JWS和/或JWE，以JSON声明为有效负载。
        // 在这个示例中，它是一个JWS，因此我们创建了一个JsonWebSignature对象。
        JsonWebSignature jws = new JsonWebSignature();

        // JWS的有效负载是JWT声明的JSON内容
        jws.setPayload(claims.toJson());

        // JWT是使用私钥签署的。
        jws.setKey(rsaJsonWebKey.getPrivateKey());

        /**
         * 设置键ID (kid)头，因为这样做很礼貌。
         * 在这个例子中，我们只有一个键，但是使用一个键ID可以帮助实现平滑的键滚动过程。
         */
        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

        // 在JWT/JWS上设置签名算法，以完整性保护声明
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        // 签署JWS并生成紧凑的串行化或完整的JWT/JWS
        // 表示，它是一个字符串，在form header . load中由三个点('.')分隔
        // base64url编码的部分组成。Header.Payload.Signature
        // 如果想要对其进行加密，只需将这个jwt设置为JsonWebEncryption对象的有效负载，并将cty(内容类型)标头设置为“jwt”
        String jwt = jws.getCompactSerialization();

        // 现在你可以用JWT做点什么了。就像把它发送给其他的一方, 通过云和网络。
        System.out.println("JWT: " + jwt);


        // 使用JwtConsumerBuilder构造一个合适的JwtConsumer，
        // 它将用于验证和处理JWT。
        // JWT的特定验证需求依赖于上下文，
        // 然而通常建议需要(合理的)过期时间、受信任的发行者和以及将系统标识为预期接收者的受众。
        // 如果JWT也被加密，那么只需向构建器提供一个解密密钥或解密密钥解析器。
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // JWT必须有一个有效期
                .setAllowedClockSkewInSeconds(30) // 允许在验证基于时间的声明中有一些余地来解释时钟偏差
                .setRequireSubject() // JWT必须有一个主题要求。
                .setExpectedIssuer("Issuer") // JWT需要由谁发出
                .setExpectedAudience("Audience") // JWT是给谁的
                .setVerificationKey(rsaJsonWebKey.getKey()) // 使用公钥验证签名
                .setJwsAlgorithmConstraints( // 只允许在给定的上下文中使用期望的签名算法
                        new AlgorithmConstraints(ConstraintType.WHITELIST,
                                AlgorithmIdentifiers.RSA_USING_SHA256)) //这里只有RS256
                .build(); // 创建JwtConsumer实例

        try {
            // 验证JWT并将其处理为声明
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            System.out.println("JWT validation succeeded! " + jwtClaims);
        } catch (InvalidJwtException e) {

            // 如果JWT处理或验证失败，将抛出InvalidJwtException。
            // 希望能对出了什么问题给出有意义的解释。
            System.out.println("Invalid JWT! " + e);

            // 编程访问(一些)JWT失效的特定原因也是可能的
            // 如果您希望在某些条件下使用不同的错误处理行为。
            // JWT是否已过期是失效的一个常见原因
            if (e.hasExpired()) {
                System.out.println("JWT expried at " + e.getJwtContext().getJwtClaims().getExpirationTime());
            }

            // 或者观众是无效的
            if (e.hasErrorCode(ErrorCodes.AUDIENCE_INVALID)) {
                System.out.println("JWT had wrong audience: " + e.getJwtContext().getJwtClaims().getAudience());
            }
        }
    }
}
