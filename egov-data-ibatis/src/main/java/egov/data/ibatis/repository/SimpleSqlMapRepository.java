/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egov.data.ibatis.repository;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * Default implementation of the {@link org.springframework.data.repository.Repository} interface. 
 * 
 * @author Yongkwon Park
 * @param <T> the type of the entity to handle
 * @param <ID> the type of the entity's identifier
 */
@Repository
@Transactional(readOnly=true)
public class SimpleSqlMapRepository<T, ID extends Serializable> implements SqlMapRepository<T, ID> {

	private SqlMapClient sqlMapClient;
	
	public SimpleSqlMapRepository(SqlMapClient sqlMapClient) {
		Assert.notNull(sqlMapClient, "SqlMapClient must not be null!");
		
		this.sqlMapClient = sqlMapClient;
	}
	
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}
	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	
}
