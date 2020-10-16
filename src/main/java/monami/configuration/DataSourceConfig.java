package monami.configuration;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class DataSourceConfig {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	DataSource dataSource;
	
	@Bean
	public PlatformTransactionManager transactionManager() throws Exception{
		return new DataSourceTransactionManager(dataSource);
	}
	
	
	///* MyBatis 설정
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
	    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	    factoryBean.setDataSource( dataSource );
	    factoryBean.setVfs(SpringBootVFS.class);
	      //매퍼설정
	    Resource[] mapperLocations = applicationContext.getResources("classpath:/mapper/**/mapper-*.xml");
	    factoryBean.setMapperLocations(mapperLocations);
	    return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
}
