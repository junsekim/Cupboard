package integeration.components;

import com.icemelon404.cupboard.annotations.Component;

@Component
public class TestComponent extends TestParent implements TestInterface {

    public TestComponent2 component2;
    public TestComponent3 component3;

    public TestComponent(TestComponent2 component2, TestComponent3 component3) {
        this.component2 = component2;
        this.component3 = component3;
    }
}
