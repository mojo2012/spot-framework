package at.spot.core.infrastructure.aspect;

import org.aopalliance.aop.AspectException;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import at.spot.core.infrastructure.annotation.logging.Log;
import at.spot.core.infrastructure.service.LoggingService;

@Aspect
public class LogAspect extends AbstractBaseAspect {

	@Autowired
	protected LoggingService loggingService;

	/**
	 * Define the pointcut for all methods that are annotated with {@link Log}.
	 */
	@Pointcut("@annotation(at.spot.core.infrastructure.annotation.logging.Log) && execution(* *.*(..))")
	protected void logAnnotation() {
	};

	@Before("logAnnotation()")
	public void logBefore(JoinPoint joinPoint) {
		Log ann = getAnnotation(joinPoint, Log.class);

		if (ann != null && ann.before()) {
			loggingService.log(ann.logLevel(), createLogMessage(joinPoint, true), joinPoint.getTarget().getClass());
		}
	}

	@After("logAnnotation()")
	public void logAfter(JoinPoint joinPoint) throws AspectException {
		Log ann = getAnnotation(joinPoint, Log.class);

		if (ann != null && ann.after()) {
			loggingService.log(ann.logLevel(), createLogMessage(joinPoint, false), joinPoint.getTarget().getClass());
		}
	}

	protected String createLogMessage(JoinPoint joinPoint, boolean useAnnotationMessage) {
		Log ann = getAnnotation(joinPoint, Log.class);

		String msg = String.format("Before %s.%s", joinPoint.getTarget().getClass().getSimpleName(),
				joinPoint.getSignature().getName());

		if (useAnnotationMessage && ann != null && StringUtils.isNotBlank(ann.message())) {
			msg = ann.message();
		}

		return msg;
	}
}