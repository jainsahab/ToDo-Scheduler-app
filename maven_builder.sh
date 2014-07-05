#!/bin/sh
projectDirectory=$1
packageStructure=`echo $2 | sed -e "s/\./\//"`
pomFile="${projectDirectory}/pom.xml"
beansFile="${projectDirectory}/src/main/resources/Beans.xml"
persistenceXmlFile="${projectDirectory}/src/main/resources/META-INF/persistence.xml"
persistencePropertiesFile="${projectDirectory}/src/main/resources/properties/persistence.properties"

mkdir -p ${projectDirectory}/src/main/java/${packageStructure}
mkdir -p ${projectDirectory}/src/test/java/
mkdir -p ${projectDirectory}/src/main/resources/db/migration
mkdir -p ${projectDirectory}/src/main/resources/META-INF
mkdir -p ${projectDirectory}/src/main/resources/properties
mkdir -p ${projectDirectory}/src/main/webapp

cat > $pomFile <<END_OF_POM_FILE
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>$2</groupId>
    <artifactId>$1</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>

    	<dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
        </dependency>

    </dependencies>

</project>
END_OF_POM_FILE

cat > $beansFile <<END_OF_BEANS_FILE
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:jpa="http://www.springframework.org/schema/data/jpa"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
      http://www.springframework.org/schema/data/jpa http://www.springframework.org/schema/data/jpa/spring-jpa.xsd">
  <jpa:repositories base-package="com.prateekj.repositories"/>

  <bean id="configurationEncryptor" class="org.jasypt.encryption.pbe.StandardPBEStringEncryptor">
    <property name="config">
      <bean class="org.jasypt.encryption.pbe.config.SimpleStringPBEConfig">
        <property name="algorithm" value="PBEWithMD5AndDES"/>
        <property name="password" value="password"/>
      </bean>
    </property>
  </bean>

  <bean id="propertyConfigurer"
        class="org.jasypt.spring31.properties.EncryptablePropertyPlaceholderConfigurer">
    <constructor-arg ref="configurationEncryptor"/>
    <property name="locations">
      <list>
        <value>classpath:properties/persistence.properties</value>
      </list>
    </property>
  </bean>


  <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
    <property name="entityManagerFactory" ref="entityManagerFactory"/>
  </bean>

  <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="jpaVendorAdapter" ref="jpaVendorAdapter"/>
    <property name="persistenceUnitName" value="mysql-core"/>
  </bean>

  <bean id="jpaVendorAdapter" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
    <property name="database" value="MYSQL"/>
    <property name="databasePlatform" value="org.hibernate.dialect.MySQL5Dialect"/>
    <property name="showSql" value="false"/>
  </bean>

  <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource" destroy-method="close">
    <property name="driverClass" value="\${com.prateekj.jdbc.driver}"/>
    <property name="jdbcUrl" value="\${com.prateekj.jdbc.url}"/>
    <property name="user" value="\${com.prateekj.jdbc.user}"/>
    <property name="password" value="\${com.prateekj.jdbc.password}"/>
  </bean>


</beans>
END_OF_BEANS_FILE

cat > $persistenceXmlFile <<END_OF_PERSISTENCE_FILE
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
  <persistence-unit name="mysql-core" transaction-type="RESOURCE_LOCAL">
    <provider>org.hibernate.ejb.HibernatePersistence</provider>
    <!--<class>com.cat.pscs.api.model.User</class>-->
    <shared-cache-mode>ENABLE_SELECTIVE</shared-cache-mode>
  </persistence-unit>
</persistence>
END_OF_PERSISTENCE_FILE

cat > $persistencePropertiesFile <<END_OF_PROPERTIES_FILE
END_OF_PROPERTIES_FILE