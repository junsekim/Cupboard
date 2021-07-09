package integeration.components;

import com.icemelon404.cupboard.annotations.Component;

@Component
public class TestComponent3 {

    public TestComponent4 component4;

    public TestComponent3(TestComponent4 component4) {
        this.component4 = component4;
    }
}
