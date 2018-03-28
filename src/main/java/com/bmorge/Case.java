package com.bmorge;

class Case {
    private final Object sample;
    private boolean isEquals;
    private boolean fulfilledBefore;

    private Case() {
        this.sample = null;
    }

    Case(Object sample) {
        this.sample = sample;
    }

    public Case caser(Object caser) {
        if (fulfilledBefore || isEquals) return this;
        mainEquals(caser);
        return this;
    }

    public Case caser(Object... caser) {
        if (fulfilledBefore || isEquals) return this;
        for (Object example : caser) {
            mainEquals(example);
            if (isEquals) break;
        }
        return this;
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

    public Case breaker(Runnable runnable) {
        if (isEquals) {
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
