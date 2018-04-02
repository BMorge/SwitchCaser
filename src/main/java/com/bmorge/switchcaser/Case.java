package com.bmorge.switchcaser;

import java.util.Collection;
import java.util.Comparator;
import java.util.function.Supplier;

public class Case {
    private final Object sample;
    private boolean isEquals;
    private boolean fulfilledBefore;

    private Case() {
        this.sample = null;
    }

    Case(Object sample) {
        this.sample = sample;
    }

    public Case onCase(Object caser) {
        if (fulfilledBefore || isEquals) return this;
        mainEquals(caser);
        return this;
    }

    public Case onCase(Object... caser) {
        if (fulfilledBefore || isEquals) return this;
        for (Object example : caser) {
            mainEquals(example);
            if (isEquals) break;
        }
        return this;
    }

    public Case onCase(Collection<Object> caser) {
        if (fulfilledBefore || isEquals) return this;
        for (Object example : caser) {
            mainEquals(example);
            if (isEquals) break;
        }
        return this;
    }

    public Case onCase(Object caser, Comparator<Object> comparator) {
        if (fulfilledBefore || isEquals) return this;
        customCompare(caser, comparator);
        return this;
    }

    public Case onCase(Collection<Object> caser, Comparator<Object> comparator) {
        if (fulfilledBefore || isEquals) return this;
        for (Object example : caser) {
            customCompare(example, comparator);
            if (isEquals) break;
        }
        return this;
    }

    public Case onCase(boolean ifTrue) {
        if (fulfilledBefore || isEquals) return this;
        if (ifTrue) {
            isEquals = true;
        }
        return this;
    }

    public Case onCase(Supplier<Boolean> ifTrue) {
        if (fulfilledBefore || isEquals) return this;
        if (ifTrue.get()) {
            isEquals = true;
        }
        return this;
    }

    private void customCompare(Object example, Comparator<Object> comparator) {
        if (sample == example) {
            isEquals = true;
            return;
        }
        if (sample == null) {
            return;
        }
        if (comparator.compare(sample, example) == 0) {
            isEquals = true;
        }
    }

    private void mainEquals(Object example) {
        if (sample == example) {
            isEquals = true;
            return;
        }
        if (sample == null) {
            return;
        }
        if (sample.equals(example)) {
            isEquals = true;
        }
    }

    public Case onBreak(Runnable runnable) {
        if (isEquals && !fulfilledBefore) {
            runnable.run();
            fulfilledBefore = true;
        }
        isEquals = false;
        return this;
    }

    public void onDefault(Runnable runnable) {
        if (!fulfilledBefore) {
            runnable.run();
        }
    }

}
