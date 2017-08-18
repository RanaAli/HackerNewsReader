
package com.hacker.reader.domain.exception;

import com.hacker.reader.domain.exception.DefaultErrorBundle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultErrorBundleTest {
  private DefaultErrorBundle defaultErrorBundle;

  @Mock private Exception mockException;

  @Before
  public void setUp() {
    defaultErrorBundle = new DefaultErrorBundle(mockException);
  }

  @Test
  public void testGetErrorMessageInteraction() {
    defaultErrorBundle.getErrorMessage();

    verify(mockException).getMessage();
  }
}
