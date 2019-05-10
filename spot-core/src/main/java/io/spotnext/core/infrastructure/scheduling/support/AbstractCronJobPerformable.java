package io.spotnext.core.infrastructure.scheduling.support;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;

import io.spotnext.core.infrastructure.scheduling.service.impl.CronJobService;
import io.spotnext.core.infrastructure.support.spring.PostConstructor;
import io.spotnext.itemtype.core.enumeration.CronJobResult;
import io.spotnext.itemtype.core.enumeration.CronJobStatus;
import io.spotnext.itemtype.core.scheduling.AbstractCronJob;

public abstract class AbstractCronJobPerformable<T extends AbstractCronJob> implements BeanNameAware, PostConstructor {

	@Autowired
	protected CronJobService cronJobService;

	protected String beanName;

	@Override
	public void setup() {
		cronJobService.registerPerformable(beanName, (AbstractCronJobPerformable<AbstractCronJob>) this);
	}

	/**
	 * Requests to abort the cronjob. The implementation can decide when or if at all it aborts.
	 * 
	 * @param force if true, the cronjob will be killed!
	 */
	public abstract void requestAbort(boolean force);

	/**
	 * The actual business logic of the cronjob performable.
	 * 
	 * @param cronJob the associated cronjob item.
	 * @return the result of the cronjob run.
	 */
	public abstract PerformResult perform(T cronJob);

	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public static class PerformResult {
		private final CronJobResult result;
		private final CronJobStatus status;

		public PerformResult(CronJobResult result, CronJobStatus status) {
			this.result = result;
			this.status = status;
		}

		public CronJobResult getResult() {
			return result;
		}

		public CronJobStatus getStatus() {
			return status;
		}
	}
}
