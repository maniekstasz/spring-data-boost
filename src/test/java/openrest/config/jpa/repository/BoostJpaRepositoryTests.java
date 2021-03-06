package openrest.config.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import openrest.config.Application;
import openrest.config.domain.Product;
import openrest.httpquery.parser.Parsers;
import openrest.httpquery.parser.TempPart;
import openrest.jpa.repository.OpenRestRepository;
import openrest.query.filter.StaticFilterFactory;
import openrest.query.parameter.QueryParameterHolder;
import openrest.query.parameter.QueryParametersHolderBuilder;
import openrest.webmvc.ParsedRequestFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BoostJpaRepositoryTests {

	@Autowired
	private OpenRestRepository repository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PersistentEntities persistentEntities;

	@Autowired
	private ParsedRequestFactory requestFactory;

	@Autowired
	private StaticFilterFactory staticFilterFactory;

	@PersistenceContext
	private EntityManager em;

	QueryParameterHolder spec;

	Class<?> domainClass = Product.class;

	@Before
	public void setUp() {
		TempPart tempPart = Parsers.parseFilter("eq(user.id,1) ;and; between(price,1.00,2.00) ;or; eq(id,1) ;and; like(name,'asdf')", false);
		QueryParametersHolderBuilder builder = new QueryParametersHolderBuilder(persistentEntities.getPersistentEntity(domainClass), objectMapper, staticFilterFactory);
		builder.append(tempPart);
		spec = builder.build();
	}

	@Test
	public void doesReturnPageWhenPageableExists() {
		Iterable<Object> result = repository.findAll(spec, (Class<Object>) domainClass);
		assertTrue(result instanceof Page);
	}

//	@Test
//	public void doesNotReturnPageWhenPageableExists() {
//		Iterable<Object> result = repository.findAll(spec, (Class<Object>) domainClass);
//		assertTrue(!(result instanceof Page));
//	}
}
