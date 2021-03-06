package io.spotnext.core.persistence.query;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * This object holds the results of a persistence query.
 * 
 * @author mojo2012
 * @version 1.0
 * @since 1.0
 */
public class QueryResult<T> implements Pageable<T> {
	private final List<T> results;
	private final int pageSize;
	private final int page;
	private final Long totalCount;

	/**
	 * @param results the result list
	 * @param page the current page of the paged result
	 * @param pageSize the page size
	 */
	public QueryResult(final List<T> results, final int page, final int pageSize, final Long totalCount) {
		if (results != null) {
			this.results = results;
		} else {
			this.results = Collections.emptyList();
		}

		this.page = page;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}

	@Override
	public List<T> getResults() {
		return results;
	}

	/**
	 * @return a stream of the results.
	 */
	public Stream<T> getResultStream() {
		return results.stream();
	}

	/**
	 * @return returns true if the result is empty.
	 */
	public boolean isEmpty() {
		return totalCount == 0;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getPage() {
		return page;
	}

	@Override
	public Long getTotalCount() {
		return totalCount;
	}

	@Override
	public long getCount() {
		return results.size();
	}

}
