package io.spotnext.test.serialzation;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.Assert;
import org.junit.Test;

import io.spotnext.core.infrastructure.exception.DeserializationException;
import io.spotnext.core.infrastructure.strategy.impl.DefaultXmlSerializationStrategy;
import io.spotnext.core.testing.AbstractIntegrationTest;
import io.spotnext.itemtype.core.user.User;

public class DefaultXmlSerializationStrategyIT extends AbstractIntegrationTest {

	@Autowired
	DefaultXmlSerializationStrategy xmlSerializationStrategy;

	@Override
	protected void prepareTest() {
		//
	}

	@Override
	protected void teardownTest() {
		//
	}

	@Test
	public void test_itemWithRelations() throws DeserializationException {
		final User user = modelService.get(User.class, Collections.singletonMap(User.PROPERTY_UID, "tester1"));

		String xml = xmlSerializationStrategy.serialize(user);

		User deserializedUser = xmlSerializationStrategy.deserialize(xml, User.class);

		Assert.assertEquals(user.getId(), deserializedUser.getId());
		Assert.assertEquals(user.getUid(), deserializedUser.getUid());
		Assert.assertEquals(user.getShortName(), deserializedUser.getShortName());
		Assert.assertEquals(user.getPassword(), deserializedUser.getPassword());
		Assert.assertEquals(user.getEmailAddress(), deserializedUser.getEmailAddress());
		Assert.assertEquals(user.getShortName(), deserializedUser.getShortName());
		
		Assert.assertEquals(user.getGroups().size(), deserializedUser.getGroups().size());
	}

}
