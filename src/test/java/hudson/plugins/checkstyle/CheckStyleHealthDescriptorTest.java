package hudson.plugins.checkstyle;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import hudson.plugins.analysis.test.AbstractHealthDescriptorTest;
import hudson.plugins.analysis.util.AbstractHealthDescriptor;
import hudson.plugins.analysis.util.HealthDescriptor;
import hudson.plugins.analysis.util.NullHealthDescriptor;
import hudson.plugins.analysis.util.model.AnnotationProvider;

import org.junit.Test;
import org.jvnet.localizer.Localizable;

/**
 * Tests the class {@link CheckStyleHealthDescriptor}.
 *
 * @author Ulli Hafner
 */
public class CheckStyleHealthDescriptorTest extends AbstractHealthDescriptorTest {
    /**
     * Verify number of items.
     */
    @Test
    public void verifyNumberOfItems() {
        AnnotationProvider provider = mock(AnnotationProvider.class);
        CheckStyleHealthDescriptor healthDescriptor = new CheckStyleHealthDescriptor(NullHealthDescriptor.NULL_HEALTH_DESCRIPTOR);

        Localizable description = healthDescriptor.createDescription(provider);
        assertEquals(Messages.Checkstyle_ResultAction_HealthReportNoItem(), description.toString());

        when(provider.getNumberOfAnnotations()).thenReturn(1);
        description = healthDescriptor.createDescription(provider);
        assertEquals(Messages.Checkstyle_ResultAction_HealthReportSingleItem(), description.toString());

        when(provider.getNumberOfAnnotations()).thenReturn(2);
        description = healthDescriptor.createDescription(provider);
        assertEquals(Messages.Checkstyle_ResultAction_HealthReportMultipleItem(2), description.toString());
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractHealthDescriptor createHealthDescriptor(final HealthDescriptor healthDescriptor) {
        return new CheckStyleHealthDescriptor(healthDescriptor);
    }
}

