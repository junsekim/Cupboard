package integeration;

import com.icemelon404.cupboard.annotations.Component;
import com.icemelon404.cupboard.bean.BeanSource;
import com.icemelon404.cupboard.bean.source.SimpleBeanSource;
import com.icemelon404.cupboard.exception.BeanNotFoundException;
import com.icemelon404.cupboard.reader.BeanReader;
import com.icemelon404.cupboard.reader.impl.ClassScanner;
import com.icemelon404.cupboard.reader.impl.ComponentScanner;
import com.icemelon404.cupboard.reader.impl.factory.SimpleBeanFactory;
import com.icemelon404.cupboard.reader.impl.scanner.AnnotatedClassScanner;
import integeration.components.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class SimpleBeanSourceTest {

    @Test
    public void requestByClass_Success() {
        TestComponent component = createTestSource().requestBean(TestComponent.class);
        Assertions.assertNotNull(component);
        Assertions.assertNotNull(component.component2);
        Assertions.assertNotNull(component.component3);
    }

    @Test
    public void requestByInterface_Success() {
        TestComponent component = (TestComponent) createTestSource().requestBean(TestInterface.class);
        Assertions.assertNotNull(component);
        Assertions.assertNotNull(component.component2);
        Assertions.assertNotNull(component.component3);
    }

    @Test
    public void requestByParentClass_Success() {
        TestParent component = createTestSource().requestBean(TestParent.class);
        Assertions.assertNotNull(component);
    }

    @Test
    public void duplicateRequestAndCompare_Success() {
        BeanSource source = createTestSource();
        TestComponent first = source.requestBean(TestComponent.class);
        TestComponent second = source.requestBean(TestComponent.class);
        Assertions.assertEquals(first, second);
    }

    @Test
    public void requestNotExistingBean_Fail() {
        BeanSource source = createTestSource();
        Assertions.assertThrows(BeanNotFoundException.class, ()-> {
            source.requestBean(NonComponent.class);
        });
    }

    private BeanSource createTestSource() {
        ClassScanner scanner = new AnnotatedClassScanner(Arrays.asList("integeration.components"), Component.class);
        BeanReader reader = new ComponentScanner(scanner, new SimpleBeanFactory());
        return new SimpleBeanSource(reader);
    }

}