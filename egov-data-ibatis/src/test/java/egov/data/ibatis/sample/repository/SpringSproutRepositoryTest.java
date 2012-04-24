package egov.data.ibatis.sample.repository;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import egov.data.ibatis.sample.domain.SpringSprout;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class SpringSproutRepositoryTest {
	
	@Autowired JdbcTemplate jdbcTemplate;
	@Autowired SpringSproutRepository repository;
	
	SpringSprout first, second, third;
	
	@Before
	public void setup() {
		first = new SpringSprout("miracle");
		second = new SpringSprout("daclouds");
		third = new SpringSprout("yunseok");
	}
	
	@Test
	public void testInsert() {
		int before = count();
		
		registerTestSpringSprouts();
		checkIdInjected();
		
		assertThat(count(), is(before + 3));
	}
	
	@Test
	public void testFindByName() {
		registerTestSpringSprouts();
		
		SpringSprout springSprout = repository.findByName(first.getName());
		
		assertThat(springSprout, is(first));
	}
	
	@Test
	public void testFindAll() {
		registerTestSpringSprouts();
		
		List<SpringSprout> springSprouts = repository.findAll();
		
		assertThat(springSprouts, is(hasItems(first, second, third)));
	}
	
	@Test
	public void testUpdate() {
		registerTestSpringSprouts();
		
		SpringSprout miracle = repository.findByName(first.getName());
		miracle.setName("update");
		
		int updateCount = repository.update(miracle);
		SpringSprout update = repository.findByName(miracle.getName());
		
		assertThat(updateCount, is(1));
		assertThat(update, is(miracle));
	}
	
	@Test
	public void testDelete() {
		registerTestSpringSprouts();
		
		int before = count();
		
		SpringSprout miracle = repository.findByName(first.getName());
		int deleteCount = repository.delete(miracle.getId());
		
		assertThat(deleteCount, is(1));
		assertThat(count(), is(before - deleteCount));
	}
	
	
	int count() {
		return jdbcTemplate.queryForInt("SELECT count(ID) FROM SPRINGSPROUT");
	}

	void registerTestSpringSprouts() {
		first = repository.insert(first);
		second = repository.insert(second);
		third = repository.insert(third);
	}
	
	void checkIdInjected() {
		assertThat(first.getId(), is(not(0L)));
		assertThat(second.getId(), is(not(0L)));
		assertThat(third.getId(), is(not(0L)));
	}

}