package com.walmart.store.container.endpoint.v1;

import com.walmart.store.container.AbstractRestIntTest;
import com.walmart.store.container.data.Entity;
import com.walmart.store.container.domain.DomainService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

/**
 * Trivial integration test class that exercises the Junit spring runner and in container testing.
 * 
 * @author jevans
 *
 */
public class RestControllerIntTest extends AbstractRestIntTest {
	
	@Autowired
	private DomainService domainService;
	
	@Test
	public void testRestGetByIdMarshalsCorrectly() {
		Entity wireLoc = template.getForObject(rootUrl+ "/template/1", Entity.class);
		System.out.println(wireLoc);
		assertEqualsServiceValue(wireLoc);
	}

	private void assertEqualsServiceValue(Entity wireLoc) {
		assertNotNull("Expected supplied wire Model to be non-null.", wireLoc);
		
		long id = wireLoc.getId();
		Entity svcLoc = domainService.service(id);
		assertNotNull("Expected the entity with id '" + id + "'.", svcLoc);
		assertJsonEquals(svcLoc, wireLoc);
	}
	
}