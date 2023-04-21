package test.io.smallrye.openapi.runtime.scanner.jakarta;

import java.util.List;
import java.util.Set;

class Foo {
}

public class Generics extends BaseModel {

    private Set<ID<Foo>> fooSet;

    private List<ID<Foo>> fooList;

    public Set<ID<Foo>> getFooSet() {
        return fooSet;
    }

    public void setFooSet(final Set<ID<Foo>> fooSet) {
        this.fooSet = fooSet;
    }

    public List<ID<Foo>> getFooList() {
        return fooList;
    }

    public void setFooList(final List<ID<Foo>> fooList) {
        this.fooList = fooList;
    }
}
