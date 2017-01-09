package org.manuel.teambuilting.core.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author manuel.doncel.martos
 * @since 9-1-2017
 */
@Aspect
@Component
public class NotVeryUsefulAspect {

	@AfterReturning("@annotation(org.manuel.teambuilting.core.aspects.UserDataSave)")
	public void test(final JoinPoint call) {
		System.out.println("testing the aspect " + call);
	}

}
