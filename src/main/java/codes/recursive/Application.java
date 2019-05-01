package codes.recursive;

import io.micronaut.configuration.hibernate.jpa.condition.EntitiesInPackageCondition;
import io.micronaut.core.annotation.TypeHint;
import io.micronaut.jdbc.spring.HibernatePresenceCondition;
import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import oracle.jdbc.OracleStatement;
import oracle.jdbc.driver.OracleDriver;
import oracle.security.crypto.cert.ext.*;
import oracle.security.crypto.core.*;
import oracle.security.pki.OraclePKI;
import oracle.security.pki.OracleSSOKeyStoreSpi;
import org.apache.tomcat.jdbc.pool.ConnectionPool;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.dialect.Oracle12cDialect;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.hql.internal.ast.ASTQueryTranslatorFactory;
import org.hibernate.hql.internal.ast.HqlToken;
import org.hibernate.hql.internal.ast.tree.*;
import org.hibernate.id.IdentityGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.persister.collection.BasicCollectionPersister;
import org.hibernate.persister.collection.OneToManyPersister;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.hibernate.query.spi.QueryParameterBindings;
import org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorBuilderImpl;
import org.hibernate.resource.transaction.spi.TransactionCoordinatorBuilder;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.tuple.entity.PojoEntityTuplizer;
import org.hibernate.type.descriptor.WrapperOptions;
import org.springframework.orm.hibernate5.SpringSessionContext;

import java.security.Security;
import java.sql.ResultSet;

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
@TypeHint(value = {
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
        SelectClause.class,
        DotNode.class,
        IdentNode.class,
        FromElement.class,
        ASTQueryTranslatorFactory.class,
        QueryParameterBindings.class,
        SharedSessionContractImplementor.class,
        SequenceStyleGenerator.class,
        OracleStatement.class,
        ResultSet.class,
        WrapperOptions.class,
        HibernatePresenceCondition.class,
        EntitiesInPackageCondition.class,
        IdentityGenerator.class,
        BasicCollectionPersister.class,
        OneToManyPersister.class,
        SingleTableEntityPersister.class,
        EntityMetamodel.class,
        PojoEntityTuplizer.class,
        SpringSessionContext.class,
        AggregateNode.class,
        AssignmentSpecification.class,
        BetweenOperatorNode.class,
        BinaryArithmeticOperatorNode.class,
        BinaryLogicOperatorNode.class,
        BooleanLiteralNode.class,
        CastFunctionNode.class,
        CollectionFunction.class,
        ComponentJoin.class,
        ConstructorNode.class,
        CountNode.class,
        DeleteStatement.class,
        DotNode.class,
        EntityJoinFromElement.class,
        FromClause.class,
        FromElement.class,
        FromElementFactory.class,
        FromReferenceNode.class,
        HqlSqlWalkerNode.class,
        IdentNode.class,
        ImpliedFromElement.class,
        IndexNode.class,
        InLogicOperatorNode.class,
        InsertStatement.class,
        IntoClause.class,
        IsNotNullLogicOperatorNode.class,
        IsNullLogicOperatorNode.class,
        JavaConstantNode.class,
        LiteralNode.class,
        MapEntryNode.class,
        MapKeyEntityFromElement.class,
        MapKeyNode.class,
        MapValueNode.class,
        MethodNode.class,
        Node.class,
        NullNode.class,
        OrderByClause.class,
        ParameterNode.class,
        QueryNode.class,
        ResultVariableRefNode.class,
        SearchedCaseNode.class,
        SelectClause.class,
        SelectExpressionImpl.class,
        SelectExpressionList.class,
        SimpleCaseNode.class,
        SqlFragment.class,
        SqlNode.class,
        UnaryArithmeticNode.class,
        UnaryLogicOperatorNode.class,
        UpdateStatement.class,
        ConnectionPool.class,
        JdbcResourceLocalTransactionCoordinatorBuilderImpl.class,
        TransactionCoordinatorBuilder.class
}, accessType =  TypeHint.AccessType.ALL_PUBLIC)
public class Application {
    public static void main(String[] args) {
        Security.addProvider(new oracle.security.pki.OraclePKIProvider());
        Micronaut.run(Application.class);
    }
}