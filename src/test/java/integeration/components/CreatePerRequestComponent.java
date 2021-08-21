package integeration.components;

import com.icemelon404.cupboard.annotations.Component;
import com.icemelon404.cupboard.annotations.Policy;
import com.icemelon404.cupboard.bean.impl.policy.CreationPolicyType;

@Component
@Policy(CreationPolicyType.CREATE_PER_REQUEST)
public class CreatePerRequestComponent {
}
