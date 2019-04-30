package codes.recursive;

import io.micronaut.core.annotation.TypeHint;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import oracle.jdbc.driver.OracleDriver;
import oracle.security.crypto.cert.ext.*;
import oracle.security.crypto.core.*;
import oracle.security.pki.OraclePKI;
import oracle.security.pki.OracleSSOKeyStoreSpi;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.dialect.Oracle12cDialect;
import org.hibernate.hql.internal.ast.HqlToken;
import org.hibernate.hql.internal.ast.tree.*;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.tuple.entity.PojoEntityTuplizer;
import org.springframework.orm.hibernate5.SpringSessionContext;

import java.security.Security;

@OpenAPIDefinition(
        info = @Info(
                title = "Micronaut Java Hibernate Demo",
                version = "0.1",
                description = "Micronaut Java Hibernate Demo - Person API",
                contact = @Contact(url = "https://recursive.codes", name = "Todd Sharp", email = "todd.sharp@oracle.com")
        ),
        tags = {
                @Tag(name = "Person", description = "A service to manage persons.")
        }
)
@TypeHint({
        ImplicitNamingStrategyJpaCompliantImpl.class,
        Oracle12cDialect.class,
        OracleDriver.class,
        OracleSSOKeyStoreSpi.class,
        OraclePKI.class,
        PKCS12PBE.class,
        SHA.class,
        DES_EDE.class,
        RSAPrivateKey.class,
        RSAPublicKey.class,
        KeyUsageExtension.class,
        BasicConstraintsExtension.class,
        SubjectKeyIDExtension.class,
        CRLDistPointExtension.class,
        AuthorityKeyIDExtension.class,
        AuthorityInfoAccessExtension.class,
        CertificatePoliciesExtension.class,
        HqlToken.class,
        Node.class,
        QueryNode.class,
        SqlNode.class,
        FromClause.class,
        OrderByClause.class,
        DotNode.class,
        IdentNode.class,
        FromElement.class,
        SequenceStyleGenerator.class,
        SingleTableEntityPersister.class,
        EntityMetamodel.class,
        PojoEntityTuplizer.class,
        SpringSessionContext.class
})
public class Application {
    public static void main(String[] args) {
        Security.addProvider(new oracle.security.pki.OraclePKIProvider());
        Micronaut.run(Application.class);
    }
}