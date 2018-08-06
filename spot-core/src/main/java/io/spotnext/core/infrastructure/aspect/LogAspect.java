package io.spotnext.core.infrastructure.aspect;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import io.spotnext.core.infrastructure.annotation.logging.Log;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

@Aspect
@SuppressFBWarnings("MS_SHOULD_BE_FINAL")
public class LogAspect extends AbstractBaseAspect {

	@PostConstruct
	public void init() {
		getLoggingService().debug("Initialized logging aspect.");
	}

	/**
	 * Define the pointcut for all methods that are annotated with {@link Log}.
	 */
	@Pointcut("@annotation(io.spotnext.core.infrastructure.annotation.logging.Log) && execution(* *.*(..))")
	final protected void logAnnotation() {
	};

	@Around("logAnnotation()")
	public Object logAround(final ProceedingJoinPoint joinPoint) throws Throwable {
		final Log ann = getAnnotation(joinPoint, Log.class);

		final long startTime = System.currentTimeMillis();

		if (ann != null && ann.before()) {
			getLoggingService().log(ann.logLevel(),
					createLogMessage(joinPoint, "Before", ann.message(), ann.messageArguments(), null), null, null,
					joinPoint.getTarget().getClass());
		}

		final Object ret = joinPoint.proceed(joinPoint.getArgs());

		if (ann != null && ann.after()) {
			final Long runDuration = ann.measureTime() ? (System.currentTimeMillis() - startTime) : null;

			getLoggingService().log(ann.logLevel(),
					createLogMessage(joinPoint, "After", null, ann.messageArguments(), runDuration), null, null,
					joinPoint.getTarget().getClass());
		}

		return ret;
	}

	protected String createLogMessage(final JoinPoint joinPoint, final String marker, final String message,
			final String[] arguments, final Long duration) {

		String msg = String.format("%s %s.%s", marker, joinPoint.getTarget().getClass().getSimpleName(),
				joinPoint.getSignature().getName());

		if (StringUtils.isNotBlank(message)) {
			msg = String.format(message, arguments);
		}

		if (duration != null) {
			msg += String.format(" (%s ms)", duration);
		}

		return msg;
	}
}